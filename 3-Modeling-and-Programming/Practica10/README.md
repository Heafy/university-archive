# Practica 10

## Reto buscaminas

Quien no ha jugado al Buscaminas? El objetivo del juego es encontrar
todas las minas ubicadas en un campo de dimensiones m renglones por n co-
lumnas. El juego muestra un número en un recuadro que indica la cantidad
de minas adyacentes a ese recuadro. Cada recuadro tiene, como mucho, ocho
recuadros adyacentes. Considera las siguientes matrices:
La matriz de tamaño 4x4 de la izquierda, contiene dos minas, cada una de
ellas representada por el caracter ”*”. Si representamos la misma matriz con los
números descritos anteriormente, tendremos la matriz de la derecha.

### Entrada:

La entrada constará de un número arbitario de campos. La primera lı́nea
de cada campo consta de dos números enteros m y n. n > 0 y m <= 100, que
representan respectivamente el número de lı́neas y columnas del campo. Los
recuadros sin mina están representados por ”.“ y los recuadros con minas por
”*”. La primera lı́nea descriptiva de un campo en la que n = m = 0 representa
el final de la entrada y no debe procesarse.

### Salida:

Para cada campo escribir el mensaje Field #x: en una lı́nea, donde x co-
rresponde al número del campo, empezando a contar desde 1. Las siguientes n
lı́neas deben contener el campo con los caracteres ”.“ sustituidos por el número
de minas adyacentes a ese recuadro. Debe haber una lı́nea en blanco entre los
distintos campos mostrados.
