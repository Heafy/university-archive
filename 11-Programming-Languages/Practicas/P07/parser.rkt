#lang plai

(require "grammars.rkt")

;; Analizador sintáctico para ERCFWBAEL/L.
;; Dada una s-expression, costruye el árbol de sintaxis abstracta correspondiente.
;; parse: s-expression -> ERCFWBAEL/L.
(define (parse sexp)
   (match sexp
     ['true (boolS #t)]
     ['false (boolS #f)]
     [(? symbol?) (idS sexp)]
     [(? number?) (numS sexp)]
     [(list 'if cond then else) (ifS (parse cond) (parse then) (parse else))]
     [(cons 'cond cases) (condS (parse-cond cases))]
     [(list 'with bindings body) (withS (map parse-bind bindings) (parse body))]
     [(list 'with* bindings body) (withS* (map parse-bind bindings) (parse body))]
     [(list 'fun params fun-body) (funS params (parse fun-body))]
     [(cons (? valida?) xs) (opS (op-symbol (car sexp)) (map parse (cdr sexp) ))]
     [(cons 'throws exception-id) (throwsS )]
     [(? list?) (appS (parse (car sexp)) (map parse (cdr sexp)))]))

;; Función auxiliar que toma un [id expr] y las convierte en bindings
(define (parse-bind list)
  (binding (car list) (parse (second list))))

(define (op-symbol op)
  (match op
    ['+ +]
    ['- -]
    ['* *]
    ['/ /]
    ['% modulo]
    ['min min]
    ['max max]
    ['pow mexpt]
  ;;  ['and and] AÚN NO SE CAMBIA
   ;; ['or or]
    ['< <]
    ['<= <=]
    ['> >]
    ['>= >=]
    ['= =]
    ['!= nequal]
    ['neg not]))

;; Funcion auxiliar para la potencia multiple
;; eleva el primer numero a la potencia del siguiente
;; número y su resultado a la siguiente
(define (mexpt lst)
  (letrec ([exponer
            (lambda (elem lista)
              (cond
                [(empty? lista) elem]
                [else (exponer (expt elem (car lista))(cdr lista))]))])
    (exponer (car lst) (cdr lst))))


;; Funcion auxiliar para diferente
;; aplica la igualdad a una lista de elementos
;; si son diferentes el resultado es true
;; NO CAMBIADA
(define (nequal lst)
  (not (apply = lst)))

;; Función que dice si un símbolo es una operación soportada
(define (valida? op)
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


;; Función auxiliar para aplicar parse al resto de cond.
(define (parse-cond lst)
  (cond
    [(empty? lst) lst]
    [(and (symbol? (caar lst) (symbol=? (caar lst) 'else))
          (cons (else-cond (parse (cdar lst))) '()))]
    [else (cons (condition (parse (caar lst)) (parse (cdar lst)))
                (parse-cond (rest lst)))]))


;; Función que elimina el azúcar sintáctica de las expresiones de ERCFWBAEL/L, es decir las convierte a 
;; expresiones de ERCFBAEL/L.
;; desugar: ERCFWBAEL/L -> ERCFBAEL/L
(define (desugar expr)
   (match expr
     [(idS i) (id i)]
     [(numS n) (num n)]
     [(boolS b) (bool b)]
     [(opS f args) (op f (map desugar args))]
     [(ifS cond then else) (iF (desugar cond) (desugar then) (desugar else))]
     [(condS cases) (desugar-cond cases)]
     [(funS params body) (fun params (desugar body))]
     [(appS fun-expr args) (app (desugar fun-expr) (map desugar args))]
     [(withS bindings body) (desugar-with bindings body)]
     [(withS* bindings body) (desugar (desugar-with* expr))]))

;; Función auxiliar que regresa los id's de una lista de bindings
(define (bindings-ids binds)
  (match binds
    ['() '()]
    [(cons x xs) (cons (binding-name x) (bindings-ids xs))]))

;; Función auxiliar que regresa los valores de una lista de bindings
(define (bindings-values binds)
  (match binds
    ['() '()]
    [(cons x xs) (cons (binding-value x) (bindings-values xs))]))

;;Funcion auxiliar que quita el azucar sintáctica a withS
(define (desugar-with binds body)
  (app (fun (bindings-ids binds) (desugar body))
       (map desugar (bindings-values binds))))

;; Función que quita el azúcar sintáctica a condS
;;NO ESTÁ CAMBIADO
(define (desugar-cond lst)
  (if (empty? lst) false
      (type-case Condition (first lst)
        [condition (e t) (iF (desugar e) (desugar t) (desugar-cond (rest lst)))]
        [else-cond (e) (desugar e)])))

;; Función que quita la azucar sintáctica del with*
(define (desugar-with* with*exp)
  (if (equal? 1 (length (withS*-bindings with*exp)))
      (desugar-with (withS*-bindings with*exp) (withS*-body with*exp))
      (desugar-with
       (list (car (withS*-bindings with*exp)))
       (desugar-with (withS*
                      (cdr (withS*-bindings with*exp))
                      (withS*-body with*exp))))))
