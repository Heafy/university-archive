![](header.png)

##Reporte Especificando la Estructura de Las Tablas.

A lo largo del reporte se repiten varios parámetros, ya sea porque son llaves foráneas o atributos que se repiten a lo largo de las entidades y sus relaciones, para evitar repeticiones estos atributos se irán omitiendo, si es necesario revisarlos estarán consultados en una entidad mas arriba de donde se está leyendo, al final todos los datos usados para los atributos están siendo explicados.

Es importante mencionar que se modificaron las tablas en base a los comentarios recibidos de la práctica pasada y los cambios que consideramos pertienentes.

Empezando de arriba hacia abajo leyendo el archivo gym_Hercules.sql:

####PERSONA:

* **ID_PERSONA NUMBER:** Manejamos el ID_PERSONA como un número en vez de un entero para manejarlo mas fácilmente, mantiene todos los registros de las personas relacionadas al Gimnasio Hercules.
* **NOMBRE VARCHAR(50)**: VARCHAR para cadenas, 50 caracteres dado que almacenará el nombre completo.
* **EMAIL VARCHAR(25):** Mismo caso, utilizamos una longitud reducida.
* **EDAD DATE:** DATE es la mejor manera de almacenar una fecha.
* **SEXO VARCHAR(9):** Se añade una restricción para que solo haya valores de 'Masculino' y 'Femenino', 9 debido a que es la maxima longitud entre estas dos palabras.
* **TELEFONO NUMBER(10):** La longitud habitual que tiene un número (al menos los locales de México).

#### SOCIO

* **ID_SOCIO NUMBER:** Llave primaria para poder identificar a los socios.
* **FEC_NAC DATE:** Debido a la especificación dada por el gimnasio no nos importa la fecha de nacimiento de todas las personas, solo de los socios.
* **PERSONA_CONTACTO VARCHAR(50):** VARCHAR para que pueda introducir cualquier información de la persona, como su nombre y su número.

#### CLIENTE

+ **ID_CLIENTE NUMBER:** Llave primaria para poder identificar a los clientes.

#### ENTRENADOR

* **ID_EMPLEADO NUMBER:** Llave primaria para poder identificar a los clientes. 
* **DIRECCION VARCHAR(50):** VARCHAR para almacenar toda la dirección del entrenador, incluidos numeros y calles.

#### MEMBRESIA

* **ID_MEMBRESIA NUMBER:** Llave primaria con forma de número para almacenar el identificador de las membresias.
* **COSTO DECIMAL(5,2):** Número decimal para almacenar el costo, con hasta 5 digitos y 2 para los centavos, respectivamente.

#### TIPO_MEMBRESIA

* **AREAS_ENTRENAMIENTO VARCHAR(80): ** VARCHAR para listar las áreas de entrenamiento, cualquier tipo de membresia tiene acceso a las áreas de entrenamiento.
* **REGADERAS VARCHAR(25): ** VARCHAR para listar las regaderas, ya que estas pueden estar numeradas o acomodadas en base a las zonas, cualquier tipo de membresia tiene acceso a las regaderas.

#### MEMBRESIA_BASICA

No se agrega ningun atributo, contiene los mismos que el tipo de membresia

####MEMBRESIA_PLUS

+ **CASILLERO VARCHAR(25):** VARCHAR para almacenar el casillero de la membresia, ya que este puede ser una combinación alfanumérica.
+ **ENTRENADOR VARCHAR(25): ** VARCHAR para e nombre del entrenador.

#### MEMBRESIA_PREMIUM

* **SAUNA VARCHAR(25):** VARCHAR para listar los saunas a los que tiene acceso, ya sean números o nombres.
* **ESPECIALISTA_NUTRICION VARCHAR(25):** VARCHAR para el nombre del especialista en nutrición al que tiene derecho un socio premium.
* **PUNTOS NUMBER:** Almacenamos los puntos del socio como un número.

#### SISTEMA_PUNTOS

No agrega ningun atributo nuevo, ya que ID_MEMBRESIA y PUNTOS ya fueron explicados.

#### CLASE

+ **NOMBRE_CLASE VARCHAR(25): ** Llave primaria con el nombre de la clase, pueden ser cadenas o números.
+ **ENTRENADOR VARCHAR(40):** VARCHAR para el nombre completo del entrenador.
+ **COSTO DECIMAL(5,2): ** Misma explicación que el costo de la membresia.
+ **DIAS_IMPARTIDOS VARCHAR(50):** VARCHAR para almacenar todos los dias que se puede impartir una clase.
+ **HORA_INICIO TIMESTAMP:** TIMESTAMP Almacena los datos para las horas, asi que viene perfecto para almacenar la hora de inicio
+ **HORA_FIN TIMESTAMP: ** El mismo caso que el anterior, pero para almacenar el final de la clase.

#### PRODUCTO

* **NOMBRE_PRODUCTO VARCHAR(25):** VARCHAR para representar el nombre del producto, es la llave primaria.
* **PRESENTACION VARCHAR(25): **VARCHAR para representar las diferentes presentaciones que puede tener un producto.
* **DESCRIPCION VARCHAR(50):** Usamos un VARCHAR como para almacenar una pequeña descripción del producto como cadena.
* **EXISTENCIAS  NUMBER:** El número restante de los productos como número entero.
* **MARCA VARCHAR(25): **La marca del producto en cuestión como cadena.

#### TENER

No agrega atributos nuevos, ID_MEMBRESIA e ID_SOCIO ya fueron explicados.

#### TOMAR_CLASE_SOCIO

No agrega atributos nuevos, NOMBRE_CLASE, ENTRENADOR, ID_SOCIO ya fueron explicados.

#### TOMAR_CLASE_CLIENTE

No agrega atributos nuevos, NOMBRE_CLASE, ENTRENADOR, ID_CLIENTE ya fueron explicados.

#### COMPRAR_SOCIO

No agrega atributos nuevos, ID_SOCIO, NOMBRE_PRODUCTO y PRESENTACION ya fueron explicados.

#### COMPRAR_CLIENTE

No agrega atributos nuevos, ID_CLIENTE, NOMBRE_PRODUCTO y PRESENTACION ya fueron explicados.

#### IMPARTIR_CLASE

No agrega atributos nuevos, NOMBRE_CLASE, ENTRENADOR e ID_EMPLEADO ya fueron explicados.

#### OBTENER_PUNTOS

Dado que no encontré una manera de definir booleanos y dar un valor específico para cada atributo, opté por una manera donde se le asigne el numero en base a la actividad y 0 en las demás para cada caso.

+ **ES_ZUMBA NUMBER(1): ** Punto asignado como número para el Zumba (con valor de 1).
+ **ES_TWERK NUMBER(1): **Punto asignado como número para el Twerk (con valor de 2).
+ **ES_YOGA NUMBER(1): **Punto asignado como número para el Yoga (con valor de 3).
+ **ES_BOXEO NUMBER(1): **Punto asignado como número para el Boxeo (con valor de 4).
+ **ES_DANZA_ARABE NUMBER(1): **Punto asignado como número para la Danza Árabe (con valor de 5).





