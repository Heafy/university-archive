#lang plai

(require "grammars.rkt")

;; Analizador sintáctico para CFWBAE/L.
;; Dada una s-expression, construye el árbol de sintaxis abstracta correspondiente.
;; parse: s-expression -> CFWBAE/L
(define (parse sexp)
   (match sexp
     ['true (boolS #t)]
     ['false (boolS #f)]
     [(? symbol?) (idS sexp)]
     [(? number?) (numS sexp)]
     [(list 'if test truth falsity) (ifS (parse test)
                                         (parse truth)
                                         (parse falsity))]
     [(cons 'cond resto) (condS (parse-cond resto))]
     [(list 'with bindings body) (withS (map parse-bind bindings) (parse body))]
     [(list 'with* bindings body) (withS* (map parse-bind bindings) (parse body))]
     [(list 'fun params fun-body) (funS params (parse fun-body))]
     [(cons (? op-valida?) xs) (opS (op-symbol (car sexp)) (map parse (cdr sexp) ))]
     [(? list?) (appS (parse (car sexp)) (map parse (cdr sexp)))]))

;; Función auxiliar que convierte a tipo binding las expresiones del tipo {id expr}
(define (parse-bind list)
  (binding (car list) (parse (second list))))

;; Función que convierte símbolos de función en procedimientos 
(define (op-symbol op)
  (match op
    ['+ +]
    ['- -]
    ['* *]
    ['/ /]
    ['% modulo]
    ['min min]
    ['max max]
    ['pow potencia]
    ['or or-aux]
    ['and and-aux]
    ['< <]
    ['<= <=]
    ['> >]
    ['>= >=]
    ['= =]
    ['!= not-equal-aux]
    ['neg not]))

;; Función que dice si un símbolo es una operación soportada
(define (op-valida? op)
  (match op
    ['+ true]
    ['- true]
    ['* true]
    ['/ true]
    ['% true]
    ['min true]
    ['max true]
    ['pow true]
    ['or true]
    ['and true]
    ['< true]
    ['<= true]
    ['> true]
    ['>= true]
    ['= true]
    ['!= true]
    ['neg true]
    [else false]))

;; Función que recibe como parámetros dos o más números de la forma 
;; (potencia n1 n2 n3...) y regresa (((n1^n2)^n3)...)
(define (potencia . lst)
  (match lst
    [(list x) x]
    [lst (expt (apply potencia (elim-ult lst)) (last lst))]))

;; Función auxiliar que elimina el último elemento de una lista
(define (elim-ult lst)
  (match lst
    ['() '()]
    [(cons x xs) (if (null? xs)
                     '()
                     (cons x (elim-ult xs)))]))

;; Función auxiliar que toma una lista de booleanos y regresa su disyunción
(define (or-aux . lst)
  (ormap (lambda (x) x) lst))

;; Función auxiliar que toma una lista de booleanos y regresa su conjunción
(define (and-aux . lst)
  (andmap (lambda (x) x) lst))

;; Función auxiliar que toma una lista de números y dice si son diferentes
(define (not-equal-aux . lst)
  (not (apply = lst)))

;; Función auxiliar para aplicar parse al resto de cond.
(define (parse-cond lst)
  (cond
    [(empty? lst) lst]
    [(and (symbol? (first (first lst))) (symbol=? (first (first lst)) 'else)) (cons (else-cond (parse (second (first lst))))
                                                                                    empty)]
    [else (cons (condition (parse (first (first lst))) (parse (second (first lst))))
                (parse-cond (rest lst)))]))

;; Función que elimina el azúcar sintáctica de las expresiones de CFWBAE/L, es decir las convierte a 
;; expresiones de CFBAE/L.
;; desugar: CFWBAE/L -> CFBAE/L
(define (desugar expr)
   (match expr
     [(idS i) (id i)]
     [(numS n) (num n)]
     [(boolS b) (bool b)]
     [(opS f args) (op f (map desugar args))]
     [(ifS test truth falsity) (iF (desugar test)
                                   (desugar truth)
                                   (desugar falsity))]
     [(condS cola) (desugar-cond cola)]
     [(funS params body) (fun params (desugar body))]
     [(appS fun-expr args) (app (desugar fun-expr) (map desugar args))]
     [(withS bindings body) (app
                             (fun
                              (bindings-ids bindings)
                              (desugar body))
                             (map desugar (bindings-values bindings)))]
     [(withS* bindings body) (desugar (with*-deconstruct expr))]))

;; Función que quita el azúcar sintáctica a condS
(define (desugar-cond lst)
  (if (empty? lst)
      false
      (type-case Condition (first lst)
        [condition (e t) (iF (desugar e)
                             (desugar t)
                             (desugar-cond (rest lst)))]
        [else-cond (e) (desugar e)])))

;; Función auxiliar que toma una lista de bindings y regresa sus id's
(define (bindings-ids bins)
  (match bins
    ['() '()]
    [(cons x xs) (cons (binding-name x) (bindings-ids xs))]))

;; Función auxiliar que toma una lista de bindings y regresa sus valores
(define (bindings-values bins)
  (match bins
    ['() '()]
    [(cons x xs) (cons (binding-value x) (bindings-values xs))]))

;; Función que quita la azucar sintáctica del with*
(define (with*-deconstruct with*exp)
  ;; if
  (if (eq? 1 (length (withS*-bindings with*exp)))
      ;; then
      (withS (withS*-bindings with*exp) (withS*-body with*exp))
      ;; else
      (withS
       (list (car (withS*-bindings with*exp)))
       (with*-deconstruct (withS*
                           (cdr (withS*-bindings with*exp))
                           (withS*-body with*exp))))))

