# Ensayo

## Jorge Yael Martínez Flores

### 2.4 La relación entre P y NP

Más de una vez nos han repetido que  $P \subseteq NP$. Un problema de decisión que puede ser resuelto por un algoritmo determinista en tiempo polinomial también puede ser resuelto por un algoritmo no determinista en el mismo tiempo. Para un problema  pi en P con su respectivo algoritmo determinista A. Obtenemos el algoritmo no determinista poniendo a A en la fase verificadora e ignorando la adivinadora. Y así $\pi \in P \rightarrow \pi \in NP$.

Y tambíen ya sabemos que $P \neq NP$ hasta que se demuestre lo contrario. Los problemas parecen intratables hasta que se encuentra un algoritmo que los resuelva. Aunque todavia no exite un método general para transformar algoritmos no deterministas de tiempo polinomial a algoritmos deterministas del mismo tiempo, nos conformamos con el siguiente teorema:

**Teorema:** Si $\pi \in NP$ entonces existe un polinomio $p$ tal que $\pi$ puede ser resuelto por un algoritmo determinista teniendo una complejidad $O(2^{p(n)})$

Omitiré la demostración ya que está en el libro (página 32-33), lo importante del teorema es la capacidad de un algoritmo no determinista para revisar un exponencial número de candidatos a ser solución, de ahí nace la creencia de que los algoritmos no deterministas de tiempo polinomial son mas capaces que los algoritmos deterministas en el mismo tiempo. Pero problemas tan conocidos como El Problema del Agente Viajero no tienen solución en tiempo polinomial. 

#### 2.5 Transformaciones Polinomiales y la NP-Completez

Los problemas en $P$ pueden ser resueltos con algoritmos de tiempo polinomial, mientras que $NP-P$ son intratables, la teoría de la NP-Completez va de demostrar cosas como: "Si $P \neq NP$ entonces el problema $A \in NP-P$". Definimos: Dado dos lenguajes $L_1 \subseteq \Sigma_1^*, L_2 \subseteq \Sigma_2^*$, una transformación polinomial es una función $f: \Sigma_1^* \rightarrow \Sigma_2^*$ que cumple:

1. Existe una máquina de Turing determinista para calcular $f$.
2. $\forall x \in \Sigma_1^*, x \in L_1$ si y solo sí $f(x) \in L_2$

Esto nos lleva al siguiente lema:

**Lema:** Si $L_1 \varpropto L_2$ (es decir, $L_1$ transforma a $L_2$) entonces $L_2 \in P$ implica $L_2 \in P$.

En este caso también omitiremos la demostración ya que puede ser consultada en el libro (página 35), en resumen, si tenemos dos problemas de decisión con sus esquemas de codificación correspondientes, el capítulo nos dice que existe una transformación polinomial $f$ de un problema a otro tal que: $f$ es calculable en tiempo polinomial y para todos los elementos de un esquema tienen transformación al otro problema.

Lo entendí mejor con el problema del circuito Hamiltoniano. Dado un ejemplar $G=(V,E)$ ¿G tiene un circuito Hamiltoniano? La transformación se hace con el problema del agente viajero. Una función 'mapea' cada ejemplar del circuito hamiltoniamo a su correspondiente del agente viajero. 

**Lema:** Si $L_1 \varpropto L_2$ y $L_2 \varpropto L_3$, entonces $L_2 \varpropto L_3$

Otra demostración omitida pero como la mayoría de las cosas en las que podemos suponer transitividad, funcionan. 

Informalmente un problema de decisión $\pi$ es NP-completo si el problema está en NP y para todos los problemas de decisión $\pi ' \in NP, \pi ' \varpropto \pi$. Esto junto con el primer lema nos lleva a la conclusión de que si un problema NP-completo puede ser resuelto en tiempo polinomial, entonces todos pueden ser resueltos en tiempo polinomial. Caso contrario, si un problema NP-completo es intratable, entonces todos lo son. Un problema $\pi$ NP-completo entonces funciona con la propiedad del inicio: Si $P \neq NP$, entonces $\pi \in NP-P$, en otras palabras $\pi \in P$ si y solo si $P = NP$.

Un último lema queda como consecuencia general de los dos mencionados anteriormente.

**Lema:** Si $L_1$ y $L_2$ pertecenen a NP, $L_1$ es NP-Completp y $L_1 \varpropto L_2$, entonces $L_2$ es NP-completo.

Lo que nos quiere decir este lema, es que si alguna vez tenemos un problema NP-completo, para demostrar que otro problema $\pi$ es NP-completo, debemos de mostrar que $\pi \in NP$ y transformar este problema NP-completo a $\pi$. En el siguiente capítulo se habla sobre el teorema de Cook y como podemos usar ese para demostrar que los problemas son NP-completos.



