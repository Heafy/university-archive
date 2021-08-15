#lang plai

;; Metodo auxiliar, encuentra la raiz con la formula general con la suma
;; formula-general number number number -> number
(define (formula-general-suma a b c)
  (/(+ (- b) (sqrt(discriminante a b c)))(* 2 a)))

;; Metodo auxiliar, encuentra la raiz con la formula general con la resta
;; formula-general-resta number number number -> number
(define (formula-general-resta a b c)
  (/(- (- b) (sqrt(discriminante a b c)))(* 2 a)))

;; Calcula el valor del discriminante
;; discriminante number number number -> number
(define (discriminante a b c)
  (- (expt b 2)(* 4 a c)))

;; Metodo principal que calcula la formula general, usa los auxiliares antes ocupados
;; formula-general number number number -> number
(define (formula-general a b c)
  (display (string-append (genera-mensaje a b c) "\n" "x1= " (number->string (formula-general-suma a b c)) "\n" "x2= "(number->string (formula-general-resta a b c)))))

;; Genera el mensaje dado el discriminante y determina el tipo de sus raices
(define (genera-mensaje a b c)
  (cond
    [(> (discriminante a b c) 0) "Raices reales y diferentes"]
    [(= (discriminante a b c) 0) "Raices reales e iguales"]
    [(< (discriminante a b c) 0) "Raices complejas y conjugadas"]))

;; Pruebas unitarias
;; (test (formula-general-suma 1 7 10) (-2))
;; (test (formula-general-resta 1 7 10) (-5))
;; (test (formula-general-suma 1 7 10) (-2))
;; (test (discriminante 1 7 10) (9))