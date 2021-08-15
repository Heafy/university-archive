#lang plai

(require "practica2.rkt")

(print-only-errors)

#| Módulo para pruebas unitarias de la práctica 2 |#

;; Pruebas para funcion->string
(test ((funcion->string (pot (cte 8) 6)))
      ("8 ^ 6"))

(test (funcion->string (sum (sum (cte 2) (x) ) (cte 6)))
      ("2 + x + 6"))

(test  (funcion->string (mul (cte 2) (x)))
       ("(2 * x)"))

(test (funcion->string (sum (mul (cte 2) (cte 1)) (mul (x) (cte 0))))
      ("2 * 1 + x * 0"))

(test (funcion->string (div (mul (cte 4) (x)) (x)))
      ("4 * x ÷ x"))

;; Pruebas para evalua

(test (evalua (mul (cte 2) (x)) 1729)
      (mul (cte 2) (cte 1729)))

(test (evalua (pot (cte 2) 3) 1729)
      (pot (cte 2) 3))

(test (evalua (div (mul (cte 10) (x)) (x)) 1729)
      (div (mul (cte 10) (cte 1729)) (cte 1729)))

(test (evalua (div (x) (x)) 1729)
      (div (cte 1729) (cte 1729)))

(test (evalua (sum (cte 8) (x)) 2048)
      (sum (cte 8) (cte 2048)))

;; Pruebas para deriva

#| ... Aquí van las pruebas (Borrar este comentario) ... |#

;; Pruebas para verifica

#| ... Aquí van las pruebas (Borrar este comentario) ... |#

;; Pruebas para acepta?

#| ... Aquí van las pruebas (Borrar este comentario) ... |#

;; Pruebas para calc-a

(test (calc-a (arrg boolean? 3 '(#t #t #t #f)))
      ('error: "Dimension invalida"))

(test (calc-a (agrega-a 2  (arrg number? 5 '(1 2 3 4 5)) 4))
      (arreglo number? 5 '(1 2 3 4 2)))

(test ((calc-a (arrg boolean? 4 '(1 2 3 4))))
      ('error: "Los elementos no son del tipo especificado"))

(test (calc-a  (arrg number? 7 '(2 3 4 5 8 2 3)))
      (arrg number? 7 '(2 3 4 5 8 2 3)))

(test (calc-a (agrega-a 2  (arrg number? 5 '(1 2 3 4 5)) 7))
      ('error: "Indice invalido"))

;; Pruebas para calc-c
#| ... Aquí van las pruebas (Borrar este comentario) ... |#

(test (calc-c (contiene a 1))
      (#t))

(test (calc-c (cjto '(1 7 2 9)))
       (cjto ’(1 7 2 9)))

(test (calc-c (agrega (cjto ’(1 7 2 9)) 9))
      (cjto ’(1 7 2 9)))

(test  (calc-c (agrega (cjto ’(1 7 2 9)) 9))
       (cjto ’(1 7 2 9)))

(test  (calc-c (interseccion  (cjto ’(1 7 2 9)) (cjto ’(1 2 3 4))))
       (cjto ’(1 2)))
