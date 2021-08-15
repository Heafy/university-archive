Nombre: Jorge Yael Martínez Flores
Cuenta: 312128726

Programa que lee una serie de entradas para poder leer la tabla de un 
autómata finito determinista y lo simula mostrando su tabla de transiciones.
Además de que puede recibir un numero arbitrario de cadenas y nos muestra si
el autómata acepta o rechaza la cadena.

El programa se ejecutara como cualquier otro programa en Java desde la terminal,
mas este irá acompañado de un archivo de texto especificado mas adelante, en otro
caso el programa mostrará como debe de funcionar.

El archivo de texto debe de tener el siguiente formato para que el programa
pueda funcionar correctamente.

1ra linea: El numero de estados que tiene el autómata
2da linea: El alfabeto de entrada separado por comas
3ra linea: Una lista de los estados finales separados por comas
4ta linea en adelante: Cada renglón de la tabla de transiciones

Es importante mencionar que el nombre de los estados se generan automáticamente 
por el programa con la etiqueta 1 hasta n con n el número de estados introducidos.
Así que todos los estados son numeros y llevan a numeros las transiciones.

Una vez que el programa lea el archivo y cree la tabla de transiciones correspondiente
le mostrará al usuario dicha tabla y pasará a pedirle cadenas al usuario en la terminal.
Insertará cadenas y le mostrara al usuario si la cadena fue aceptada o no por el 
autómata un número arbitrario de veces hasta que el usuario le diga al programa 
que ya no quiere introducir mas cadenas.

En el proyecto se anexan archivos de ejemplo con el que se puede probar el proyecto
estructurado 
