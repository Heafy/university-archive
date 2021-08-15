#lang plai

;; Funcion que define un tipo abstracto para trabajar con figuras geométricas
(define-type figura
  [triangulo (a real?) (b real?) (c real?)]
  [cuadrado (a real?)]
  [rectangulo (a real?) (b real?)]
  [rombo (a real?) (D real?) (d real?)]
  [paralelogramo (a real?) (b real?) (h real?)]
  [circulo (D real?)]
  [elipse (a real?) (b real?)])

;; Funcion que dada una figura regresa el perímetro de esta
;; perimetro:: figura -> number
(define (perimetro fig)
  (cond 
    [(triangulo? fig) (+ (triangulo-a fig)  (triangulo-b fig) (triangulo-c fig))]
    [(cuadrado? fig) (* (cuadrado-a fig) 4)]
    [(rectangulo? fig) (+ (* 2 (rectangulo-a fig)) (* 2 (rectangulo-b fig)))]
    [(rombo? fig) (* (rombo-a fig) 4)]
    [(paralelogramo? fig) (* 2 (+ (paralelogramo-a fig) (paralelogramo-b fig)))]
    [(circulo? fig) (* pi (circulo-D fig))]
    [(elipse? fig) (* (* 2 pi) (sqrt(/ (+ (expt (elipse-a fig) 2)(expt (elipse-b fig) 2)) 2)))]))

;; Funcion que dada una figura regresa el area de esta
;; area:: figura -> number
(define (area fig)
  (cond
    [(triangulo? fig) (let([s (/ (+ (triangulo-a fig) (triangulo-b fig) (triangulo-c fig) ) 2)])
                        (sqrt(* s (- s (triangulo-a fig))(- s (triangulo-b fig) )(- s (triangulo-c fig) ))))]
    [(cuadrado? fig) (expt (cuadrado-a fig) 2)]
    [(rectangulo? fig) (* (rectangulo-a fig) (rectangulo-a fig))]
    [(rombo? fig) (/ (* (rombo-D fig) (rombo-d fig)) 2)]
    [(paralelogramo? fig) (* (paralelogramo-b fig) (paralelogramo-h fig))]
    [(circulo? fig) (* pi (expt (/ (circulo-D fig) 2) 2))]
    [(elipse? fig) (* pi (elipse-a fig) (elipse-b fig))]))

