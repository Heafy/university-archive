#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Función encargada de interpretar el árbol de sintaxis abstracta generado por el parser. El
;; intérprete requiere un ambiente de evaluación en esta versión para buscar el valor de los 
;; identificadores.
;; interp: ERCFBAEL/L Env -> ERCFBAEL/L-Value
(define (interp expr env)
   (match expr
     [(id i) (lookup i env)]
     [(num n) (numV n)]
     [(bool b) (boolV b)]
     [(iF cond then else) (if (and (boolV? (strict (interp cond env)))
                                   (false? (boolV-b (strict (interp cond env)))))
                              (interp else env) ;;then
                              (interp then env))] ;;else
     [(op f args) (if (boolean? (result-op f args env))
                      (boolV (result-op f args env));;then
                      (numV (result-op f args env)))];;else
     [(fun params body) (closureV params body env)]
     [(app fun-expr args) (interp
                             (closureV-body (strict (interp fun-expr env)))
                             (crea-env (closureV-params (strict (interp fun-expr env)))
                                            (map (lambda (x) (exprV x env)) args)
                                            (closureV-env (strict (interp fun-expr env)))                           
                                            ))]))

                                            
;; Función auxiliar para obtener el valor de un resultado de op
(define (result-op f args env) 
   (apply f (get-values (map (lambda (x) (strict(interp x env))) args))))

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
    ['() '()]
    [(cons (numV n) xs) (cons n (get-values xs))]
    [(cons (boolV b) xs) (cons b (get-values xs))]))


;; Funcion que busca en el ambiente la variable y regresa su valor
(define (lookup v env)
  (match env
    [(mtSub) (error 'interp "Free variable")]
    [(aSub name value env2) (if (symbol=? name v) value
                                (lookup v env2))]))

;; Funcion auxiliar que toma una lista de variables, una lista de valores y un ambiente
;; construye otro ambiente con las asignaciones correspondientes.
(define (crea-env ids vals env)
  (if(equal? ids '()) (env)
     (aSub (car ids)
           (if(null? vals)
              (error 'crea-env "Valores incorrectos")
              (car vals))
           (crea-env (cdr ids) (cdr vals) env))))
  

