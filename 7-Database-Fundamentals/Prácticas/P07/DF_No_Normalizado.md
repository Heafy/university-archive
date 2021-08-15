### Tablas del Gimnasio No Normalizadas

+ **Socio: **No está en ninguna forma normal, dado que ni siquiera tiene atributos atómicos, tanto  *Nombre* como *Persona_Contacto* son atributos con mas de un valor.
+ **Tiene: **Al ser una tabla con solamente dos atributos (y ambos son llaves foráneas) no tienen ningun atributo no llave, y ambos determinantes son llaves, entonces está en la Forma Normal Boyce-Codd.
+ **Membresía: **Solo tiene un atributo que es su llave primaria, entonces como no tiene dependencias funcionales de ningun tipo y es atómica está en Forma Normal-Boyce-Codd.
+ **Ser_Tipo: **El mismo caso, no tiene dependencias funcionales y es atómica, por lo que está en tercera forma normal.
+ **Tipo_Membresia: **Se repiten los casos anteriores, dado que la llave foránea es el único atributo, es atómica y no tiene dependencias funcionales, está en primera forma normal.
+ **Básica: ** No está en ninguna forma normal, dado de que tanto *Áreas_Entrenamiento* como *Regaderas* son atributos compuestos 
+ **Plus: **Mismo caso que la Membresía Básica
+ **Premium: ** Mismo caso que la Membresía Básica.
+ **Cliente: ** El cliente también solo tiene un atributo, por lo que es atómico, y no existen dependencias transitivas y de ningun tipo, por lo que está en tercera forma normal.
+ **Entrenador: ** El atributo *Direccion* de la tabla entrenador se puede descomponer dado que no es atómico, entonces no está en ninguna forma normal.
+ **Impartir_Clase: ** Todos los atributos de la tabla son atómicos, y la llave primaria es una llave compuesta por los 3 atributos, por lo que tampoco hay atributos no llave que dependan de una llave, por lo tanto tampoco hay dependencias transitivas hacia estos, pero no hay determinados para las dependencias así que está en tercera forma normal.
+ **Tomar_Clase_Socio: **Un caso similar al de *Impartir_Clase*, dado que todos sus atributos forman una llave compuesta, asi que no hay atributos no llave que dependan de estas, y está en tercera forma normal.
+ **Tomar_Clase_Cliente: ** Las llaves compuestas también están presentes aquí, todos los atributos forman una llave compuesta, y por lo mismo que hemos estado diciendo está en tercera forma normal.
+ **Clases: ** Si se cambian *Hora_Inicio* y *Hora_Fin* por un Timestamp sería un valor mas adecuado para que fueran atómicos y por otro lado *Dias_Impartidos* se puede descomponer en una tabla al haber varios y diferentes dias que se puede impartir la clase, así que la tabla no está en ninguna forma normal.
+ **Producto: **Los atributos de toda esta tabla son atómicos, *Descripción*, *Existencias* y *Marca* son los atributos no llave, mientas que *Descripción* y *Existencias* dependen de la llave compuesta (*Nombre_Producto* y *Presentación*) podemos ver que *Marca* dependería solo de *Nombre_Producto*, podemos ver que también es tercera forma normal dado que los tres atributos que no son llave no se determinan entre ellas. 
+ **Compra_Producto: **Ambos atributos de esta tabla forman una llave primaria compuesta y son atómicas, no existen atributos no llave que dependan de una llave y como estos no existen no hay dependencias transitivas pero por esto mismo no hay determinados, por lo que está en tercera forma normal.
+ **Obtener_Puntos: **Obtener puntos solo está formado por dos llaves compuestas así que por lo que he hemos visto está en tercera forma normal.
+ **Puntos: **Debido a que en este momento estaba mal diseñada esta parte el sistema de puntos solo tiene una llave primaria, asi que también está en tercera forma normal.
+ **Zumba: **Solo asigna una constante como su llave primaria así que también está en su tercera forma normal.
+ **Twerk: **Mismo caso de la tabla *Zumba*.
+ **Yoga: **Mismo caso de la tabla *Zumba*.
+ **Boxeo: **Mismo caso de la tabla *Zumba*.
+ **Danza_Arabe: **Mismo caso de la tabla *Zumba*.​