INTEGRANTES DEL EQUIPO:
- Martínez Flores Jorge Yael
- Tadeo Guillen Diana Guadalupe

-------------------------------EJERCICIO 3.1--------------------------------------

1. Dos identificadores válidos:
(id 'a)
(id 'un-identificador-valido)

2. Cinco números válidos:
a) Un entero
(num 1729)

b) Un entero negativo
(num -2048)

c) Un racional
(num 3/16)

d) Un flotante (exact->inexact ayuda a que el numero sea un punto flotante 
y no una fraccion)
(num (exact->inexact(/ 4 3)))

e) Un complejo
(num 1+2i)

3. Al menos diez operaciones básicas
a) Una expresión por cada operación posible que al menos use tres operandos.
(op + (list (op + (list (num 1)(num 7)))(op + (list (num 2)(num 9)))))

(op - (list (op - (list (num 208)(num 23)))(op - (list (num 54)(num 21)))))

(op * (list (op * (list (num 18)(num 31)))(op * (list (num 1)(num 6)))))

(op / (list (op / (list (num 35)(num 2)))(op / (list (num 5)(num 20)))))

(op * (list (op * (list (op * (list (num 2014)(num 1729)))(num 2)))(op * (list (num 5)(op * (list (num 20)(num 6)))))))

b) Combinaciones de todas las operaciones posibles.
(op + (list (op * (list (num 1)(num 7)))(op - (list (num 2)(num 9)))))

(op - (list (op * (list (num 40)(num 12)))(op + (list (num 43)(num 21)))))

(op * (list (op - (list (op + (list (num 12) (num 21))) (op - (list (num 42) (num 12)))))
      (op + (list (num 24) (num 321)))))

(op * (list (op - (list (op + (list (num 12) (num 21)))
                              (op - (list (num 42) (num 12)))))
                  (op + (list (op - (list (num 21) (num 20)))(num 321)))))

(op modulo (list (num 1)
                       (op expt (list (num 7)
                                       (op max (list (num 2) (op min (list (num 9) (num 10)))))))))

4. Al menos tres expresiones with válidas:
a) Una expresión sencilla con identificador, valor y como cuerpo una expresión 
aritmética
(with (list (binding 'a (num 2))) (op + (list (id 'a)(num 4))))

b) Una expresión con identificador, valor y como cuerpo otra expresión with
(with (list (binding 'x (num 26))) (with (list (binding 'y (num 12))) (op - (list (id 'x)(id 'y)))))

c) Una expresión con identificador, como valor una expresión with y como 
cuerpo la anidación de tres with.
(with (list (binding 'a (num 10)))
        (with (list (binding 'b (num 20)))
              (with (list (binding 'c (num 30)))
                    (with (list (binding 'd (num 40)))
                          (op + (list (id 'a)
                                      (op - (list (id 'b)
                                                  (op * (list (id 'c)(id 'd)))))))))))

5. Al menos 3 expresiones with* válidas: se debe cumplir lo mismo que en el 
punto 3 además de que permita definir identificadores en términes de otros 
previamente definidos.
(with* (list (binding 'i (num 1)))
        (with* (list (binding 'j (num 7)))
              (with* (list (binding 'k (num 2)))
                    (with* (list (binding 'l (num 9)))
                          (op + (list (id 'k)
                                      (op - (list (id 'j)
                                                  (op * (list (id 'i)(id 'l)))))))))))

(with* (list (binding 'j (num 7)))
        (with* (list (binding 'o (num 14)))
              (with* (list (binding 'r (num 3)))
                    (with* (list (binding 'g (num 16)))
                          (op max (list (id 'j)
                                      (op + (list (id 'o)
                                                  (op + (list (id 'r)(id 'g)))))))))))

(with* (list (binding 'a (num 7)))
        (with* (list (binding 'b (id 'a)))
              (with* (list (binding 'c (id 'b)))
                    (with* (list (binding 'd (id 'c)))
                          (op * (list (id 'd)
                                      (op / (list (id 'c)
                                                  (op * (list (id 'b)(id 'a)))))))))))


-------------------------------EJERCICIO 3.2--------------------------------------

EL analizador léxico recibe una expresión y la separa en lexemas. En Racket podemos ahorrarnos
este análisis al usar una se sus primitivas.

¿Cuál es esta primitiva?
R= string-split

¿Cómo funciona?
R=Recibe como parámetro un String el cual es separado por sus blancos y regresa una lista de
substrings de este que contiene únicamente subcadenas sin blancos.

¿Por qué es útil para realizar el análisis léxico?
R= Porque los substrings que se obtienen al procesar la cadena inicial (expresión) son lexemas
y estos, al ser regresados en forma de lista, ya cumplen con los requerimientos para continuar
con la construcción del ASA.

