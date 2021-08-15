#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Función encargada de interpretar el árbol de sintaxis abstracta sin azúcar sintáctica generado por
;; la función desugar. Esta versión de interp usa alcance estático.
;; interp: CFBAE/L -> CFBAE/L-Value
(define (interp expr env)
   (match expr
     [(id i) (lookup i env)]
     [(num n) (numV n)]
     [(bool b) (boolV b)]
     [(iF expr then-expr else-expr) (if (boolV-false? (strict (interp expr env)))
                                        (interp else-expr env)
                                        (interp then-expr env))]
     [(op f args) (if (boolean? (result-op f args env)) (boolV (result-op f args env)) (numV (result-op f args env)))]
     [(fun params body) (closureV params body env)]
     [(app fun-expr args) (interp
                             (closureV-body (value-fun fun-expr env))
                             (nuevo-env (closureV-params (value-fun fun-expr env))
                                            (args-fun args env)
                                            (closureV-env (value-fun fun-expr env))                           
                                            ))]))

;; Función auxiliar para obtener el valor de un resultado de op
(define (result-op f args env) 
   (apply f (get-values (map (lambda (x) (strict(interp x env))) args))))

;; Funcion auxiliar que fuerza la evalucaión en los valores
;; de una aplicación de función
(define (value-fun fun-expr env)
  (strict(interp fun-expr env)))

;; Función auxiliar que fuerza la evaluación en los argumentos
;;de la aplicación de una función
(define (args-fun args env)
  (map (lambda (x) (exprV x env)) args))

;; Función que fuerza la evaluación de una expresión
;; NO CAMBIADA
(define (strict e)
  (match e
    [(exprV expr env) (strict (interp expr env))]
    [else e]))

;; Función auxiliar que toma una lista de FBAE-Value, puede regresar dos cosas
;; Su representación en número o su representación en booleanos
;;NO CAMBIADA
(define (get-values lst)
  (match lst
    ['() empty]
    [(cons (numV n) xs) (cons n (get-values xs))]
    [(cons (boolV b) xs) (cons b (get-values xs))]))

;; Funcion que busca en el ambiente la variable y regresa su valor
;;NO CAMBIADA
(define (lookup v env)
  (match env
    [(mtSub) (error 'interp "Free variable")]
    [(aSub name value ds) (if (symbol=? name v) value (lookup v ds))]))

;; Funcion auxiliar que toma una lista de variables, una lista de valores y un ambiente
;; construye otro ambiente con las asignaciones correspondientes.
;;NO CAMBIADA
(define (nuevo-env lst-id lst-val env)
  (match lst-id
    ['() env]
    [(cons x xs) (aSub x
                       (if(null? lst-val)
                          (error 'nuevo-env "Parametros insuficientes")
                          (car lst-val))
                       (nuevo-env xs (cdr lst-val) env))]))

;;No CAMBIADA
(define (boolV-false? expr)
  (if (and (boolV? expr) (false? (boolV-b expr)))
      true
      false))


