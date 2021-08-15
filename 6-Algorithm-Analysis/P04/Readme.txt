Nombre: Jorge Yael Martínez Flores
Cuenta: 312128726

  Utilizando terminal
************************/

Desde el directorio 'src':

-)  Compilar con:
      javac sort/*.java

-)  Correr con:
      java sort.Main <archivo de resource> <velocidad> <algoritmo>

    <archivo de resource> = Nombre de archivo de imagen a procesar, debe encontrarse en la carpeta 'resource'
    <velocidad> = Numero de iteraciones que ocurrirán antes de hacer un update a la interfaz grafica
    <algoritmo> = Algoritmo de ordenamiento a utilizar, puede ser 'bubble', 'selection', 'insertion', 'merge', 'quick'

    Por ejemplo:
      java sort.Main unam 50 bubble
      java sort.Main unam 50 selection
      java sort.Main unam 50 insertion
      java sort.Main unam 50 merge
      java sort.Main unam 50 quick

      Es la velocidad optima para que se muestre el algoritmo a una velocidad aceptable con una imagen que yo agregué.
