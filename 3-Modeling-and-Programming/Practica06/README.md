# Practica 06
## Votaciones australianas

En las elecciones australianas los votantes deben valorar a todos los candidatos por orden de pre-
ferencia. Inicialmente sólo se considera la primera preferencia, y si uno de los candidatos recibe más
del 50 % de los votos, resulta elegido. Sin embargo, si ningún candidato logra ese 50 %, se eliminan
todos los candidatos empatados a menor número de votos. Las votaciones que sitúan a esos candidatos
como primera elección, se recuentan a favor de los no eliminados. Este proceso de eliminación de los
candidatos más débiles en favor de los no eliminados, continúa hasta que uno de ellos recibe más del
50 % de los votos, o hasta que todos los candidatos restantes están empatados.
Entrada
La entrada comienza con un entero positivo en una lı́nea que indica el número de casos que sigue,
describiéndose estos a continuación. Esta lı́nea va seguida por una en blanco. También hay una lı́nea
en blanco entre dos entradas consecutivas.
La primera lı́nea de cada caso es un entero n <= 20, que indica el número de candidatos. Las siguientes
n lı́neas incluyen los nombres de los candidatos en orden, cada uno de ellos con 80 caracteres como
máximo. A continuación se incluyen hasta 1,000 lı́neas, siendo cada una de ellas un voto. Cada voto
contiene números desde 1 hasta n en orden arbitrario. El primer número determina el candidato de
primera preferencia, el segundo al candidato de segunda, y ası́ sucesivamente.
Salida
La salida de cada caso de prueba consta de una única lı́nea que contiene el nombre del ganador, o de
varias lı́neas que contienen los nombres de los candidatos que han empatado. Cada caso está separado
del siguiente por una lı́nea en blanco.

###Ejemplo de entrada
1
3
John Doe
Jane Smith
Jane Austen
123
213
231
123
312

####Ejemplo de salida
John Doe
