#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Función que toma un árbol de sintaxis abstracta y regresa su evaluación
;; interp : WAE -> number
(define (interp expr)
	(match expr
		[(id i) (error 'interp "Free identifier")]
		[(num n) n]
		[(binop f izq der)
			(f (interp izq) (interp der))]
		[(with id val body)
			(interp (subst body id val))]))

;; Función que toma un árbol de sintaxis abstracta y regresa su evaluación
;; interp : WAE -> number
(define (interp2 expr)
	(match expr
		[(id i) (error 'interp2 "Free identifier")]
		[(num n) n]
		[(binop f izq der)
			(f (interp2 izq) (interp2 der))]
		[(with bindings body)
			(interp2 (subst body id (num (interp2 val))))]))

;; Función que toma una expresión , un ident if icador y un valor
;; y regresa la sustitución en la expresión de los ident if icadores
;; con el valor correspondiente .
;; subst : WAE symbol WAE - > WAE
(define (subst expr sub-id val)
	(match expr
		[(id i) (if (symbol=? i sub-id) val expr)]
		[(num n) expr]
		[(binop f izq der)
			(binop f (subst izq sub-id val) (subst der sub-id val))]
		[(with bound-id named-expr body)
			(if (symbol=? bound-id sub-id)
				(with
					bound-id
					(subst named-expr sub-id val)
					body)
				(with
					bound-id
					(subst named-expr sub-id val)
					(subst body sub-id val)))]))

;; Ejercicio 6.2
;; (Ambos devuelven 6)
;; Entra en el then

;; Este caso ocurre cuando encuentra el id del primer with dentro de la asignación
;; del segundo with.
(interp (parse '{with {x 6}
                      {with {y x}
                            {+ y 4}}}))
;; Entre en el else

;; Este caso ocurre cuando no encuentra el id del primer with dentro de la asignación
;; del segundo with y sigue evaluando dentro del body.
(interp (parse '{with {x 3}
                        {with{y 5}
                             {+ x 4}}}))

;; Ejercicio 6.3
;; No truena

;; En el primer interp como usa evaluación perezosa, no se evalua la división entre cero
;; únicamente cuando se encuentra en el body, por lo tanto no lanza error
(interp (parse '{with {a {/ 10 0}}
                      {+ 2 10}}))

;; Truena

;; En este caso por la modificacion de la funcion se fuerza a utilizar evaluación glotona,
;; por lo que, aunque no utiliza nunca a la variable a, obtiene su valor que es la división
;; entre cero, forzando el error
(interp2 (parse '{with {a {/ 10 0}}
                       {+ 2 10}}))
