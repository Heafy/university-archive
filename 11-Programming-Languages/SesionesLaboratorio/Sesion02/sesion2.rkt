#lang plai

;; Funcion para definir un vector
(define (mvector . elems)
  elems)

;; Función (suma u v) que regrese la suma de dos vectores.
;; suma: mvector -> mvector -> mvector
(define (suma u v)
  (match u
      ['() empty]
    [(cons x xs) (cons (+ x (car v))
                       (suma xs (cdr v)))]))

;; Función (punto u v) que regrese el producto punto de dos vectores.
;; punto: mvector -> mvector -> number
(define (punto u v)
  (match u
      ['() 0]
    [(cons x xs) (+ (* x (car v))
                       (punto  xs (cdr v)))]))

;; Dunción (prod k u) que regrese el producto por escalar (k es el escalar).
;; prod: number -> mvector -> mvector
(define (prod k u)
  (map (lambda (u) (* k u)) u))

;; Función (norma u) que regrese la norma del vector
;; norma: mvector -> mvector
(define (norma u)
  (sqrt(foldr + 0 (map (lambda (u) (* u u)) u ))))

;; Función (angulo u v) que regresa el ángulo entre dos vectores
;; angulo: -> mvector -> mvector -> mvector
(define (angulo u v)
  (acos(/ (punto u v)(* (norma u)(norma v)))))

;(test (suma (mvector 1 2 3 4) (mvector 5 6 7 8)) (6 8 10 12))
;(test (punto (mvector 1 2 3 4 5) (mvector 6 7 8 9 10)) 130)
;(test (prod 2 (mvector 1 2 3 4 5 6)) (2 4 6 8 10 12))
;(test (norma (mvector 1 2 3 4 5 6 7)) 11.83)
;((angulo (mvector 1 2) (mvector 3 4)) 0.17)