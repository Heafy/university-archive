# Ensayo

## Jorge Yael Martínez Flores

## Capítulo 1: Problemas y algoritmos

### Problema del alcance en gráficas

Una gráfica es un conjunto $G=(V,E)$ con $V$ el conjunto de  vértices y $E$ el conjunto de las aristas. El problema del alcance en gráficas nos expone que: Dada una gráfica $G$ y dos vertices $1, n \in V$ ¿Existe un camino de $1$ a $n$? Este problema tiene un conjunto infinito de posibles ejemplares de solución, pero a final de cuentas la respuesta puede recaer en un simple "si" o "no". A este tipo de problemas les llamaremos problemas de decisión, en vez de centrarnos en la solución del problema, nos centramos en si se puede resolver o no.

Algoritmo para resolver el problema del alcance en gráficas:

1. Sea $S=\{1\}$. Cada vértice puede estar *marcado* o *desmarcado*. Cada nodo $i$ *marcado* significa que $i$ ha estado en S.
2. Mientras $S \neq \emptyset$:
3. Escoje un vértice $i \in S$ y se remueve.
4. Recorre una por una las aristas $(i,j)$. Si $j$ está *desmarcado*, lo marca y lo agrega a $S$.
5. Si el nodo $n$ está *marcado* $\Rightarrow$ Regresa "Si".
6. En otro caso $\Rightarrow$ Regresa "No".

Si vemos la gráfica como una matriz de adyacencias, cada entrada se visitaríá solo una vez, cuando se elige el vértice correspondiente a su fila, esto nos toma $n^2$ operaciones procesando las aristas de los vértices seleccionados (a lo más existen $n^2$ aristas en una gráfica) y considerando las demas operaciones sencillas en tiempo constante, podemos concluir que dos vértices de una gráfica con $n$ vértices están conectados en tiempo proporcional de $\mathcal{O}(n^2)$

Para complementar con las nociones del espacio del algoritmo, este almacena el conjunto $S$ y el marcaje de los vértices. Existen a lo más $n$ marcas y $|S| \leq n$ entonces toma $\mathcal{O}(n)$ en espacio.

Una cota polinomial superaría eventualmente a una cota exponencial, por lo que al parecer estas podrían ser preferiibles para el conjunto de ejemplares del problema, pero trabajar con algoritmos con complejidades como $n^{80}$ son poco probables que sucedan. Normalmente los algoritmos polinomiales tienen pequeños exponentes con cremiento aceptable que nos indica que el problema ha sido solucionado satisfactoriamente. Una cota como $2^n$ o peor puede ser motivo de preocupación, ya que si persiste puede ya no ser un algoritmo de tiempo polinomial, en estos casos el algoritmo puede ser calificado como *intratable* ya que no tendría una solución eficiente.

El último punto a considerar sería analizar como se comporta el algoritmo en el peor de los casos. El desempeño del algoritmo en el peor de los casos sucede en una mínima fracción de las entradas del algoritmo, aunque el algoritmo tiene un desempeño satisfactorio en el caso promedio, un análisis del caso esperado puede darnos mayor información que uno del peor de los casos. Pero ya que no conocemos todas las entradas posibles del algoritmo ni la probabilidad de que ocurra cada una de estas entradas, esto hace imposible un análisis del caso promedio.

### Problema del flujo máximo

Una red es una quíntupla $N=(V,E,s,t,c)$ donde $(V,E)$ es una gráfica dirigida, $s$ es el vértice fuente y $t$ es el vértice sumidero. $s$ no tiene aristas entrantes y $t$ no tiene aristas salientes. $c$ es un valor entero que representa la capacidad que se le asigna a cada arista $c(i,j)$. Un flujo en $N$ es un valor entero no negativo $f(i,j) \leq c(i,j)$ para cada arista $(i,j)$ tal que para cada vértice diferente de $s$ y $t$, la suma de los flujos de las aristas entrantes es igual a la suma de los fluos de las aristas salientes. El valor de un flujo $f$ es igual a la suma de los flujos en las aristas partiendo de $s$. En resumen: Dado una red $N$, encontrar el flujo del mas largo valor posible.

Podemos transformar cualquier problema en uno de decisión equivalente agregando el valor al cual se quiere alcanzar y haciendo una pregunta con respuesta "Si" o "No". Dado una red $N$ y un entero $k$ ¿Existe un flujo mayor o igual a $k$?

Dado un flujo $f$, debemos decidir si es óptimo o no, que es lo mismo que encontrar un flujo con valores positivos en $N(f)$. Y encontrar dicho flujo en una red con capacidades positivas es lo mismo que encontrar un camino de $s$ a $t$, en otras palabras, el problema del flujo máximo es un ejemplar del problema del alcance en gráficas.

Algoritmo para resolver el problema del flujo máximo:

1. Empieza con un flujo $0$ en todos los valores de $N$.
2. Construir N(f) repetidamente
3. Buscar un camino de $s$ a $t$ en $N(f)$.
4. Si existe un camino encuentra la capacidad más pequeña entre sus aristas.
    1. Agrega la cantidad de flujo al valor $f$ en toda las aristas en ese camino
5. En otro caso, f es máximo
6. Regresa $f$.

Cada una de las iteraciones del algoritmo (Encontrar un camino y mostrar el flujo) toma $\mathcal{O}(n^2)$ y esto puede repetirse $nC$ veces, donde $C$ es la capacidad máxima de cada arista en la red y ya que el máximo flujo no puede ser mas largo que $nC$, habrá a lo más $nC$ iteraciones.

La repetición de $nC$ crea una dependencia en $C$ que puede llegar a volverse preocupante, ya que hace que el algoritmo no sea del todo polinomial, $C$ puede crecer en tiempo exponencial dependiendo del tamaño de la entrada. Una manera de deshacernos de este problema, es si partimos de suponer que no aumenta el flujo a lo largo de ningun camino de $s$ a $t$., si no en el camino mas corto ya que tiene menos aristas. Usando el algoritmo de búsqueda BFS, Una arista en el camino de $s$ a $t$ en $N(f)$ que tiene al menos capacidad $c'$ se le conoce como *cuello de botella*. Si probamos que siempre escogemos el camino mas corto para aumentar el flujo, cada arista puede ser un *cuello de botella* en a lo más $n$ iteraciones y como a lo ms existen $n^2$ aristas y cada iteración tiene a lo más un *cuello de botella*, el número de iteraciones en el algoritmo del camino mas corto debe ser de $n^3$. Entonces el algoritmo resuelve el problema de flujo máximo en $\mathcal{O}(n^5)$

### Problema del apareamiento bipartito

Una gráfica bipartita es uan tripleta $B=(U,V,E)$ donde $(U,V)$ son conjunto s de vértices que llamaremos *chicos* y *chicas* tal que $|U| = |V|$ y $E$ el conjunto de los vértices. Un apareamiento es una gráfica bipartita para $M \subseteq E$ tal que no hay dos aristas en $M$ adyacenes al mismo conjunto $U$ o $V$. El problema que queremos resolver es: Dado una gráfica bipartita $G$ ¿$G$ tiene un apareamiento?

Definimos un concepto muy importante que es la *reducción*. Una *reducción* es una algoritmo que resuelve un problema $A$ transformando un ejempkar de $A$ en un ejemplar equivalente previamente solucionado por $B$. Como en los apareamientos. Si creamos una red con $U \cup V \cup \{s,t\}$ donde $s$ es el vértice fuente y $t$ el vértice sumidero con $s \in U, t \in V$. De esta manera se puede ver que una gráfica bipartita tiene un apareamiento si y solo si la red resultante tiene un flujo tamaño $n$. Reducimos el problema del apareamiento al problema del flujo máximo, el cual se soluciona en tiempo polinomial. Por lo tanto, el problema del apareamiento puede ser resuelto en tiempo polinomial.

### El problema del agente viajero

Dadas $n$ ciudades y un entero no negativo $d_{ij}$ para representar las distancias enter las ciudades $i,j$. Se quiere encontrar el tour mas corto entre las ciudades. Llamamos TSP al problema. Su versión en problema de decisión TSP(D) se define análogamente con el problema de FlujoMaximo(D). Dado un entero $b$ (Llamemosle a $b$ el "presupuesto" del viajero) ¿Existe un tour de longitud a lo más $b$?

Podemos resolver el problema enumerando todas las posibles soluciones calculando el costo de cada una y escogiendo la mas óptima, pero esto toma tiempo proporcional a $n!$. A pesar de eso el algoritmo se comporta mejor en términos de esacio, a que requiere espacio proporcional a $n$. Solo guardamos la permutación actual y el mejor tour encontrado hasta el momento.

Existen heurísticas que pueden devolver tours que no se alejan tanto del óptimo cuanso se les dan ejemplares típicos. Pero estos algoritmos solo nos pueden ofrecer complejidades exponenciales. Solo nos queda suponer que no existe un algoritmo de tiempo polinomial para resolver TSP.