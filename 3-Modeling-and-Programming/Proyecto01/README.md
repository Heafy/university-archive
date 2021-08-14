# Proyecto 01

## Juego del ahorcado con sockets

El proyecto consistirá en desarrollar una programa que simule el juego del “Ahorcado”
utilizando sockets en el lenguaje de programación Python.
Partiendo de un almacenamiento de nombres de frutas:

Ejemplo:MANGO, SANDIA, GUAYABA, TEJOCOTE, TORONJA, GUANABANA, HIGO, TAMARINDO

El servidor elegirá una de ellas al azar, supongamos que salió TORONJA, entonces
mostrará al cliente:
---------|
_ _ _ _ _ _ _

y luego el servidor pedirá al cliente que escriba letras para tratar de adivinar el nombre de la
fruta.

Supongamos el cliente escribe: A
Entonces el servidor le mostrará:
---------|
_ _ _ _ _ _ A

Si ahora escribe E, se mostrará:
---------|
O
_ _ _ _ _ _ A

si luego escribe M, se mostrará:
---------|
O
|
_ _ _ _ _ _ A

si luego escribe O, se mostrará:
---------|
O
|
_ O _ O _ _ A

A lo más, el usuario tendrá 6 oportunidades de adivinar, mostrando así, un muñeco de la
siguientes forma:
---------|
O
~|~
/ \
En este caso, el cliente pierde, si descifra la palabra secreta, entonces el jugador gana.
