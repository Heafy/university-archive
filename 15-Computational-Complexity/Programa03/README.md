# Programa 03

## MAX-SAT Con algoritmos genéticos

Cruces implementados:
* Partially-mapped Crossover (PMX)
* Order Crossover (OX)

Mutaciones implementadas:
* Exchange mutation
* Displacement mutation

Requisitos:
* Maven 3.6.0+
* JDK 1.8+

### Compilación

```bash
mvn clean
mvn compile
```

### Ejecución

```bash
mvn exec:java
```

**Reproducción**: Reproduce los mejores elementos de la generación con los peores para crear diferentes elementos en la población.

**Selección**: Selecciona y ordena los elementos con base en su fitness, para saber que tan buenos son.

**Remplazo:** Tras cada generación, remplaza los peores hijos con los mejores de la siguiente generación.

**Mutación:** Después de cada cruce decide si aplicar la mutación con probabilidad 0.2.

Primero muestra el ejemplar al cual aplicar el
algoritmo genético y después muestra cada generación con 3 segundos de espera entre generación, para que se puedan apreciar los datos. Para evitar que tantos datos sean mostrados, solo se muestra la mejor asignación encontrada por el algoritmo genético, pero muestra el fitness de todas las generaciones.

Al final muestra la asignación de verdad para el mejor ejemplar generado por las generaciones, además de su fitness.

Disclaimer: Las variables y cláusulas, tanto en su generación como en su cantidad son generados aleatoriamente, ya que el ejemplar también es generado aleatoriamente, no encontré una manera de hacer un ejemplar conveniente para la cada solución.