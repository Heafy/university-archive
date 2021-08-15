#lang plai

;; Función para calcular el n-ésimo número de Tribonnacci recursivamente.
;; tribonnaci :: number -> number
(define (tribonacci n)
  (cond
    [(< n 2) 0]
    [(= n 2) 1]
    [else (+ (tribonacci (- n 1))
             (tribonacci (- n 2))
             (tribonacci (- n 3)))]))

;; Función para calcular el n-ésimo número de Tribonnacci usando recursión de cola.
;; tribonnaci-cola :: number -> number
(define (tribonacci-cola n)
  (trib/cola n 0 1 1))

;; Funcion auxiliar para el tribonacci de cola.
;; trib/cola :: number number number number -> number
(define (trib/cola n acc1 acc2 acc3)
  (cond
    [(< n 2) acc1]
    [(= n 2) acc2]
    [else (trib/cola (sub1 n) acc2 acc3 (+ (+ acc1 acc2) acc3))]))

;; Función para calcular el n-ésimo número de Tribonnacci usando memoización.
;; tribonnaci-memo :: number -> number
(define (tribonacci-memo n)
	(let ([resultado (hash-ref hash n 'ninguno)])
          (cond
            [(equal? resultado 'ninguno)
             (define value
               (+ (tribonacci (- n 1)) (+ (tribonacci (- n 2)) (tribonacci (- n 3)))))
             (hash-ref hash n value)
             value]
            [else resultado])))

;; Funcion auxiliar para los hash
(define hash
  (make-hash
   (list (cons 0 0) (cons 1 0) (cons 2 1))))