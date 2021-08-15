#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Función encargada de interpretar el árbol de sintaxis abstracta sin azúcar sintáctica generado por
;; la función desugar. Esta versión de interp usa alcance estático.
;; interp: FBAE -> FBAE-Value
(define (interp expr env)
   (match expr
     [(id i) (lookup i env)]
     [(num n) (numV n)]
     [(bool b) (boolV b)]
     ;; Usamos local define para obtener el tipo de elementos con el que se trabaja (numV o boolV)
     ;; Aunque no hemos visto local define, evita tener repetir esta operación varias veces
     [(op f args) (local [(define op-val
                            (apply
                             f
                             (get-values
                              (map (lambda (x) (interp x env)) args))))]
                    (if (boolean? op-val)
                        (boolV op-val)
                        (numV op-val)))]
     [(fun params body) (closureV params body env)]
     ;; Aplica el mismo caso para la aplicacion de funcion
     [(app fun-expr args) (local [(define fun-val (interp fun-expr env))]
                            (interp
                             (closureV-body fun-val)
                             (nuevo-env (closureV-params fun-val)
                                            (map (lambda (x) (interp x env)) args)
                                            (closureV-env fun-val) ;; Alcance estático
                                            ;env Alcance dinámico                                            
                                            )))]))

;; Función auxiliar que toma una lista de FBAE-Value, puede regresar dos cosas
;; Su representación en número o su representación en booleanos
(define (get-values lst)
  (match lst
    ['() empty]
    [(cons (numV n) xs) (cons n (get-values xs))]
    [(cons (boolV b) xs) (cons b (get-values xs))]))

;; Funcion que busca en el ambiente la variable y regresa su valor
(define (lookup v env)
  (match env
    [(mtSub) (error 'interp "Free variable")]
    [(aSub name value ds) (if (symbol=? name v)
                              value
                              (lookup v ds))]))

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

