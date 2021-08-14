# Practica 04

## El problema de 3n+1

Consideremos el siguiente algoritmo para generar una secuencia de números, comenzando con un
entero n, si n es par, se divide por 2, si n es impar, se multiplica por 3 y se suma 1. Este proceso
se debe repetir para cada valor de n, finalizando cuando n=1. Por ejemplo para n=22 se genera la
siguiente secuencia de números:

22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1

Para una entrada n, la longitud de ciclo de n es la cantidad de números generados desde n hasta 1,
incluyendo a n y a 1.

En el ejemplo anterior, la longitud de ciclo de 22 es 16.

Dados dos números cualesquiera, i y j, se debe determinar la máxima longitud de ciclo correspondiente
a un número entre i y j, incluyendo a i y j

Ejemplo de entrada
1 10
100 200
201 210
900 1000

Ejemplo de salida
1 10 20
100 200 125
201 210 89
900 1000 174
