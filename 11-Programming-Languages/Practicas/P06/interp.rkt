#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Función encargada de interpretar el árbol de sintaxis abstracta generado por el parser. El
;; intérprete requiere un ambiente de evaluación en esta versión para buscar el valor de los 
;; identificadores.
;; interp: RCFBAEL/L Env -> RCFBAEL/L-Value
(define (interp expr env)
  (match expr
    [(id i)(lookup i env)]
    [(num n) (numV n)]
    [(bool b) (boolV b)]
    [(lisT l) (listV (lstS-Val l env))]
    [(op f args) (local [(define op-val
                            (apply
                             f
                             (get-values
                              (map (lambda (x)
                                     (interp x env)) args))))]
                   (cond
                      [(boolean? op-val) (boolV op-val)]
                      [(number? op-val) (numV op-val)]
                      [else op-val]))]
    [(iF expr then-expr else-expr) (if (boolV-evalua? (interp expr env))
                                       (interp else-expr env)
                                       (interp then-expr env))]
    [(rec bindings body) (interp body
                                  (rec-bind-interp-list bindings env))]
    [(fun params body) (closureV params body env)]
    [(app fun-expr args) (local [(define fun-val (interp fun-expr env))]
                            (interp
                             (closureV-body fun-val)
                             ( (closureV-params fun-val)
                                            (map (lambda (x) (interp x env)) args)
                                            (closureV-env fun-val)                                     
                                            )))]))


;; Para representar listas como valor
(define-type ListaVal
   [emptyV]
   [consV (expr RCFBAEL/L-Value?) (xs RCFBAEL/L-Value?)])

;; Función que convierte una lista con azucar sintáctica
;; a una lista vista como un valor
(define (lstS-Val lst env)
  (match lst
    [(emptyL) (emptyV)]
    [(consL expr xs) (consV (interp expr env)
                            (interp xs env))]))

;; Función que busca en un ambiente una variable y regresa su valor
(define (lookup v env)
  (match env
    [(mtSub) (error 'interp "Free identifier")]
    [(aSub name value ds)(if (symbol=? name v)
                             value
                             (lookup v ds))]
    [(aRecSub name value env) (if (symbol=? name v)
                                  (unbox value)
                                  (lookup v env))]))

;; Función auxiliar que toma una lista de FBAE-Value, puede regresar dos cosas
;; Su representación en número o su representación en booleanos
(define (get-values lst)
  (match lst
    ['() empty]
    [(cons (numV n) xs) (cons n (get-values xs))]
    [(cons (boolV b) xs) (cons b (get-values xs))]))

;; Función auxiliar que dice si la expresión dada es de tipo booleano
;; y evalua como false
(define (boolV-evalua? expr)
  (if (and (boolV? expr) (false? (boolV-b expr)))
      true
      false))

;; Función que recibe un binding y un ambiente y crea otro
;; ambiente para definir funciones recursivas
(define (rec-bind-interp binding env)
  (local ([define guarda-valor (box (numV 1729))]
          [define nuevo-env (aRecSub (binding-name binding) guarda-valor env)]
          [define val-expr (interp (binding-value binding) nuevo-env)])
    (begin
      (set-box! guarda-valor val-expr)
      nuevo-env)))

;; Función anterior pero para una lista de bindings
(define (rec-bind-interp-list bindings env)
  (cond
    [(empty? bindings) env]
    [else (rec-bind-interp-list (cdr bindings)
                                (rec-bind-interp (car bindings) env))]))

;; Funcion auxiliar que toma una lista de variables, una lista de valores y un ambiente
;; construye otro ambiente con las asignaciones correspondientes.
(define (nuevo-env lst-id lst-val env)
  (match lst-id
    ['() env]
    [(cons x xs) (aSub x
                       (if(null? lst-val)
                          (error 'nuevo-env "Parametros insuficientes")
                          (car lst-val))
                       (nuevo-env xs (cdr lst-val) env))]))

