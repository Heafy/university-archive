##Tablas del Gimnasio Normalizadas

Primero modelaremos las entidades y después las relaciones. Para el diseño y la normalización no solo se tomó en cuenta el diagrama adjunto, si no además las correciones realizadas a lo largo de las prácticas y los comentarios hechos respecto a su diseño

####Persona

Esta nueva tabla se agrega para la práctica 5, pero nos servirá para evitar la redundancia de datos agregada pasada a a primera formal normal se ve como:

****

| <u>id_Persona</u> | Nombre | Ap_Paterno | Ap_Materno | Email | Fec_Nac | Sexo | Teléfono |
| ----------------- | ------ | ---------- | ---------- | ----- | ------- | ---- | -------- |
|                   |        |            |            |       |         |      |          |

De esta manera su llave primaria id_Persona va a tener las siguientes dependencias funcionales:

- id_Persona $\rightarrow$ Nombre
- id_Persona $\rightarrow$ Ap_Paterno
- id_Persona $\rightarrow$Ap_Materno
- id_Persona $\rightarrow$ Email
- id_Persona $\rightarrow$ Fec_Nacimiento
- id_Persona $\rightarrow$ Sexo
- id_Persona $\rightarrow$ Teléfono


Una vez que todas las dependencias funcionales tienen como determinante a la llave primaria vemos que está en forma normal Boyce-Codd, esto nos va a servir para poder identificar a cualquier persona relacionada con el gimnasio.

------



####Socio

Es necesario cambiar esta tabla en más de una forma, pero para evitar que sean muchas tablas las dibujadas solo mostraremos el resultado final.

Primero hacemos que todos los atributos de la tabla sean atómicos, agregando el id_Socio (su llave primaria) podemos hacer que todos los demás atributos dependan de este, así podemos eliminar dependencias transitivas, como en el caso de que Nombre sea el determinante de Ap_Paterno y Ap_Materno, en este caso no podría haber 2 personas con el mismo nombre y apellidos, si los hacemos dependientes, cuando dependen de la llave primaria esto ya no nos importa.

Además de que cambiamos el Sistema de puntos y lo agregamos directamente a esta tabla dado que un socio es el único que puede acumular puntos.

Una vez hecho esto la tabla nos quedará de la siguiente manera:

| <u>id_Socio</u> | Nombre | Ap_Paterno | Ap_Materno | Email | Fec_Nacimiento |
| :-------------- | ------ | ---------- | ---------- | ----- | -------------- |
|                 |        |            |            |       |                |

| Sexo | Teléfono | Puntos | Persona_Contacto |
| ---- | -------- | ------ | ---------------- |
|      |          |        |                  |

Como Persona_Contacto puede almacenar tanto el nombre, como el número o correo por el que se le desea contactar lo agregamos como otra tabla, el atributo Person_Contacto se volverá un nombre y pasará a ser llave foránea.

Para pasarlo a segunda forma normal podemos ver que existen las siguientes dependencias funcionales:

- id_Socio $\rightarrow$ Nombre
- id_Socio $\rightarrow$ Ap_Paterno (Si dependiera del nombre no podrían existir dos Socios con el mismo nombre).
- id_Socio $\rightarrow$ Ap_Materno
- id_Socio $\rightarrow$ Email
- id_Socio $\rightarrow$ Fec_Nacimiento
- id_Socio $\rightarrow$ Sexo
- id_Socio $\rightarrow$ Teléfono
- id_Socio $\rightarrow$ Puntos
- id_Socio $\rightarrow$ Persona_Contacto

Así id_Persona pasa a ser la llave primaria y es el determinante de todos los demás atributos.

------



####Persona_Contacto

| <u>Nombre</u> | Ap_Paterno | Ap_Materno | Contacto |
| ------------- | ---------- | ---------- | -------- |
|               |            |            |          |

Se agrega una nueva tabla y queda en Forma Normal de Boyce-Codd.

------



####Tiene

Esa tabla no recibe modificaciones debido a que ya está en forma normal de Boyce-Codd. Los atributos id_Membresia e id_Socio son parte de una llave compuesta y no tienen dependencias funcionales.

**Tiene**

| <u>id_Membresia</u> | <u>id_Socio</u> |
| ------------------- | --------------- |
|                     |                 |

------



####Cliente

A su vez conforme mejoramos el programa notamos que también es necesario mejorar la tabla Cliente, agregando datos bastante parecidos a los de un Socio, aunque esto puede ser a gusto del cliente, ya que solo se almacenan 'por si es necesario', ya que se pueden prescindir de ellos.

| <u>id_Cliente</u> | Nombre | Ap_Paterno | Ap_Materno | Edad | Email | Sexo | Teléfono |
| ----------------- | ------ | ---------- | ---------- | ---- | ----- | ---- | -------- |
|                   |        |            |            |      |       |      |          |

Para este caso no nos interesa saber la Persona de Contacto ni sus Puntos, dado que no es Socio ni está debidamente registrados, en versiones anteriores solo se llevaba una id para saber el numero total de clientes, por lo cual reitero que estos datos pueden ser prescindibles. En dado caso de que los ocupemos nos resultarán las siguientes dependencias funcionales:

- id_Cliente $\rightarrow$ Nombre

- id_Cliente $\rightarrow$ Ap_Paterno

- id_Cliente $\rightarrow$ Ap_Materno

- id_Cliente $\rightarrow$ Edad

- id_Cliente $\rightarrow$ Email

- id_Cliente $\rightarrow$ Sexo

- id_Cliente $\rightarrow$ Teléfono

#### Entrenador

Una vez que se fué mejorando la tabla se creo una super clase para modelar a las personas relacionadas con el gimnasio, por eso existen varias tablas con atributos similares, salvo que nos interesa también saber la direccion del entrenador, pero tomando como base el ejemplo dado para esta práctica, convertiremos su direccion en atributos atómicos al momento en el que agregamos mas atributos a la tabla.

| <u>id_Empleado</u> | Nombre | Ap_P | Ap_M | Email | Edad | Sexo | Telefono | id_Direccion |
| ------------------ | ------ | ---- | ---- | ----- | ---- | ---- | -------- | ------------ |
|                    |        |      |      |       |      |      |          |              |

Manejamos las dirección como un id que es una llave foránea y nos quedan las siguientes dependencias funcionales:

- id_Empleado $\rightarrow$ Nombre
- id_Empleado $\rightarrow$ Ap_P
- id_Empleado $\rightarrow$ Ap_M
- id_Empleado $\rightarrow$ Email
- id_Empleado $\rightarrow$ Edad
- id_Empleado $\rightarrow$ Sexo
- id_Empleado $\rightarrow$ Telefono
- id_Empleado $\rightarrow$ id_Direccion

------



#### Membresía

Membresía originalmente solo contenía el valor de su id que se pasaba como llava foránea de la especialización de los tipos de membresia y su costo, pero para darle mas sentido a esta entidad se le asigna tambien el id del socio como una llave foránea, que a su vez se utiliza para crear una llave compuesta.

| <u>id_Membresia</u> | <u>id_Socio</u> | Costo |
| ------------------- | --------------- | ----- |
|                     |                 |       |

Nos queda la dependencia funcional id_Membresia $\rightarrow$ Costo

------

####Membresía Básica

Contiene la información respecto a los beneficios de la membresía básica y su identificador único.

| <u>id_Membresia</u> | Areas_Entrenamiento | Regaderas |
| ------------------- | ------------------- | --------- |
|                     |                     |           |

Nos quedan las dos dependencias funcionales:

- id_Membresia $\rightarrow$ Areas_Entrenamiento
- id_Membresia $\rightarrow$ Regaderas

Sus beneficios de todos los tipos de membresias depeden del id porque este a su vez nos define el tipo de membresia que puede ser.

------

####Membresía Plus

| <u>id_Membresia</u> | Areas_Entrenamiento | Regaderas | Casillero | Entrenador |
| ------------------- | ------------------- | --------- | --------- | ---------- |
|                     |                     |           |           |            |

Con las depdendencias funcionales:

- id_Membresia $\rightarrow​$ Areas_Entrenamiento
- id_Membresia $\rightarrow$ Regaderas
- id_Membresia $\rightarrow$ Casillero
- id_Membresia $\rightarrow$ Entrenador

------

#### Membresía Premium

| <u>id_Membresia</u> | Areas_Entrenamiento | Regaderas | Casillero | Entrenador | Sauna | Especialista | Puntos |
| ------------------- | ------------------- | --------- | --------- | ---------- | ----- | ------------ | ------ |
|                     |                     |           |           |            |       |              |        |

Esta vez nos importa almacenar tambien si es valido el sistema de puntos o no, pero estos se guardan en la tabla Socio que interactua con las clases y sus puntos, nos resultan las siguientes dependendias funcionales:

- id_Membresia $\rightarrow$ Areas_Entrenamiento

- id_Membresia $\rightarrow$ Regaderas

- id_Membresia $\rightarrow$ Casillero

- id_Membresia $\rightarrow$ Entrenador

- id_Membresia $\rightarrow$ Sauna

- id_Membresia $\rightarrow$ Especialista

- id_Membresia $\rightarrow$ Puntos

------

####Clase

Para las clases solo es necesario agregar otra tabla para representar los días que son impartidos las clases, y estos se relacionan mediante una llave foránea.



| <u>Nombre_Clase</u> | Instructor | Costo | id_Dias | Hora_Inicio | Hora_Fin | Puntos |
| ------------------- | ---------- | ----- | ------- | ----------- | -------- | ------ |
|                     |            |       |         |             |          |        |

Todas las dependencias quedan en base a su llave primaria:

- Nombre_Clase $\rightarrow$ Instructor
- Nombre_Clase $\rightarrow$ Costo
- Nombre_Clase $\rightarrow$ id_Dias
- Nombre_Clase $\rightarrow$ Hora_Inicio
- Nombre_Clase $\rightarrow$ Hora_Fin
- Nombre_Clase $\rightarrow$ Puntos


------

#### Dias_Clase

| <u>id_Dias</u> | Lunes | Martes | Miercoles | Jueves | Viernes | Sabado | Domingo |
| -------------- | ----- | ------ | --------- | ------ | ------- | ------ | ------- |
|                |       |        |           |        |         |        |         |

------

#### Area

Agregamos una nueva entidad para modelar las áreas disponibles del gimnasio

| <u>id_Area</u> | Nombre_Area | Visitas | Es_Permitida |
| -------------- | ----------- | ------- | ------------ |
|                |             |         |              |



------

####Producto

Como todos los atributos ya eran atómicos solo nos encargamos de eliminar sus dependencias y dirigirlas a su llave primaria compuesta:

| <u>Nombre_Producto</u> | <u>Presentación</u> | Descripción | Existencias | Marca |
| ---------------------- | ------------------- | ----------- | ----------- | ----- |
|                        |                     |             |             |       |

------

#### Tener

La relación Tener nos muestra como un Socio puede tener una membresía, pero por el diseño de la base de datos podemos prescindir de una tabla, pero la relación ejemplificandola en el diagrama seguiría siendo útil.

------

#### Tomar_Clase_Area

Se agrega una nueva relación para las clases y las áreas donde son impartidas, pero las relaciones no llevan atributos

------

#### Tomar_Clase_Socio

| <u>Nombre_Clase</u> | <u>Entrenador</u> | <u>id_Socio</u> |
| ------------------- | ----------------- | --------------- |
|                     |                   |                 |

No tiene dependencias funcionales

------

#### Tomar_Clase_Cliente

| <u>Nombre_Clase</u> | Entrenador | <u>id_Cliente</u> |
| ------------------- | ---------- | ----------------- |
|                     |            |                   |

No tiene dependencias funcionales

------

#### Comprar_Socio

| <u>Nombre_Producto</u> | <u>Presentación</u> | <u>id_Socio</u> |
| ---------------------- | ------------------- | --------------- |
|                        |                     |                 |

No tiene dependencias funcionales

------

#### Comprar_Cliente

| <u>Nombre_Producto</u> | <u>Presentación</u> | <u>id_Cliente</u> |
| ---------------------- | ------------------- | ----------------- |
|                        |                     |                   |

No tiene dependencias funcionales.

------

#### Impartir_Clase

| <u>Nombre_Clase</u> | <u>Entrenador</u> | <u>id_Entrenador</u> |
| ------------------- | ----------------- | -------------------- |
|                     |                   |                      |

No tiene dependencias funcionales.