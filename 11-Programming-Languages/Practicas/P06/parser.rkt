#lang plai

(require "grammars.rkt")

;; Analizador sintáctico para RCFWBAEL/L.
;; Dada una s-expression, costruye el árbol de sintaxis abstracta correspondiente.
;; parse: s-expression -> RCFWBAEL/L.
(define (parse sexp)
  (match sexp
    ['true (boolS #t)]
    ['false (boolS #f)]
    ['empty (listS (emptyLS))]
    [(? symbol?) (idS sexp)]
    [(? number?) (numS sexp)]
    [(list 'cons exp xs) (listS (consLS (parse exp) (parse xs)))]
    [(list 'if cond then else) (ifS (parse cond) (parse then) (parse else))]
    [(cons 'cond xs) (condS (map parse-cond xs))]
    [(list 'with bindings body) (withS (map parse-bindS bindings) (parse body))]
    [(list 'with* bindings body) (withS* (map parse-bindS bindings) (parse body))]
    [(list 'rec bindings body) (recS (map parse-bindS bindings) (parse body))]
    [(list 'fun params fun-body) (funS params (parse fun-body))]
    [(cons (? op-valida?) xs) (opS (op-symbol (car sexp)) (map parse (cdr sexp) ))]
    [(? list?) (parse (car sexp)) (map parse (cdr sexp))]))

;; Tipo ListaS que representa listas con azucar sintáctica.
(define-type ListaS
   [emptyLS]
   [consLS (expr RCFWBAEL/L?) (xs RCFWBAEL/L?)])

;; Función auxiliar que convierte a tipo Condition las expresiones
;; de la forma {<expr/else> <expr>} donde
;; <expr/else> ::= <expr> | else
(define (parse-cond lst)
  (match (car lst)
    ['else  (else-cond (parse (cadr lst)))]
    [else (condition (parse (car lst)) (parse (cadr lst)))]))

;; Función auxiliar que convierte a tipo binding las expresiones del tipo {id expr}
(define (parse-bindS list)
  (bindingS (car list) (parse (second list))))

;; Función auxiliar que toma una lista de booleanos y regresa su disyunción
(define (or-aux . lst)
  (ormap (lambda (x) x) lst))

;; Función auxiliar que toma una lista de booleanos y regresa su conjunción
(define (and-aux . lst)
  (andmap (lambda (x) x) lst))

;; Función auxiliar que toma una lista de números y dice si son diferentes
(define (not-equal-aux . lst)
  (not (apply = lst)))

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

;; Función auxiliar que elimina el último elemento de una lista
(define (elim-ult lst)
  (match lst
    ['() '()]
    [(cons x xs) (if (null? xs)
                     '()
                     (cons x (elim-ult xs)))]))

;; Función que recibe como parámetros dos o más números de la forma 
;; (potencia n1 n2 n3...) y regresa (((n1^n2)^n3)...)
(define (potencia . lst)
  (match lst
    [(list x) x]
    [lst (expt (apply potencia (elim-ult lst)) (last lst))]))

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

;; Función que elimina el azúcar sintáctica de las expresiones de RCFWBAEL/L, es decir las convierte a 
;; expresiones de RCFBAEL/L.
;; desugar: RCFWBAEL/L -> RCFBAEL/L
(define (desugar expr)
   (match expr
     [(idS i) (id i)]
     [(numS n) (num n)]
     [(boolS b) (bool b)]
     [(listS l) (lisT (list-deconstruct l))]
     [(opS f args) (op f (map desugar args))]
     [(ifS eval then else) (iF (desugar eval) (desugar then) (desugar else))]
     [(condS cases) (desugar (cond-deconstruct cases))]
     [(funS params body) (fun params (desugar body))]
     [(appS fun-expr args) (app (desugar fun-expr) (map desugar args))]
     [(withS bindings body) (app
                             (fun
                              (bindings-ids bindings)
                              (desugar body))
                             (map desugar (bindings-values bindings)))]
     [(withS* bindings body) (desugar (with*-deconstruct expr))]
     [(recS bindings body) (rec (map desugar-binding bindings) (desugar body))]))

;; Tipo Lista que representa listas sin azucar sintáctica.
(define-type Lista
   [emptyL]
   [consL (expr RCFBAEL/L?) (xs RCFBAEL/L?)])

;; Función que hace el trabajo de quitar el azucar sintáctica de las listas
(define (list-deconstruct sugared-list)
  (cond
    [(emptyLS? sugared-list) (emptyL)]
    [else (consL (desugar (consLS-expr sugared-list)) (desugar (consLS-xs sugared-list)))]))

;; Función que hace el trabajo de quitar el azucar sintáctica del cond
(define (cond-deconstruct cases-list)
 (if (= 2 (length cases-list))
     (ifS (condition-expr (car cases-list))
         (condition-then-expr (car cases-list))
         (else-cond-else-expr (cadr cases-list)))
     (ifS (condition-expr (car cases-list))
         (condition-then-expr (car cases-list))
         (cond-deconstruct (cdr cases-list)))))


;; Función auxiliar que toma una lista de bindings y regresa sus id's
(define (bindings-ids bins)
  (match bins
    ['() '()]
    [(cons x xs) (cons (bindingS-name x) (bindings-ids xs))]))

;; Función auxiliar que toma una lista de bindings y regresa sus valores
(define (bindings-values bins)
  (match bins
    ['() '()]
    [(cons x xs) (cons (bindingS-value x) (bindings-values xs))]))

;; Función que hace el trabajo de quitar el azucar sintáctica del with*
(define (with*-deconstruct with*exp)
  (if (eq? 1 (length (withS*-bindings with*exp)))
      (withS (withS*-bindings with*exp) (withS*-body with*exp))
      (withS
       (list (car (withS*-bindings with*exp)))
       (with*-deconstruct (withS*
                           (cdr (withS*-bindings with*exp))
                           (withS*-body with*exp))))))

;; Función auxiliar que desendulza un binding dado
(define (desugar-binding sugared-binding)
  (binding (bindingS-name sugared-binding) (desugar (bindingS-value sugared-binding))))
