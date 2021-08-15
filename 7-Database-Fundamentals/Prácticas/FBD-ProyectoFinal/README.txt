Nombre: Jorge Yael Martinez Flores
Cuenta:312128726 
Correo: jmtz@ciencias.unam.mx

Nombre: Rodrigo Alejandro Sanchez Morales
Cuenta: 312089580
Correo: rodrigosanchez@ciencias.unam.mx

Se sugiere leer primero este texto ante cualquier duda que pueda surgir en cuanto al proyecto.

El proyecto está dividio en varias carpetas, en base a las necesidas de Tacoste y como fue creciendo en base al diseño, 
mostrando los modelos Entidad Relacion y su transformacion al modelo Relacional, además de su normalización previa al 
diseño de la base en SQL.

La carpeta de scripts contiene todos los cripts necesarios para la creacion de la base de datos, con sus restricciones y 
sus inserciones para borrarla, para su uso correcto deben ejecutarse en este orden:

1. creacion_tablas.sql
2. integridad_restricciones.sql
3. inserciones.sql

Aunado a esto se agrega un script borrado_tablas.sql por si se ha terminado de usar la base y quiere eliminarse todo sin
tener problemas con las llaves foráneas.

En la carpeta Tacoste se encuentra la app que creamos para revisar las consultas principales, es importante recordar que 
Google no nos permite enviar correos con .jar asi que estos serán eliminados del correo, por lo que serán necesarios agregar
el ojdbc6.jar al proyecto para que Java y SQL puedan funcionar en conjunto sin problemas, ademas situar el login de la url
En el archivo VistaForm.java con el login, la URL y el orcl del usuario que lo quiera probar para que no haya problemas.

Si esto llegara a ser un inconveniente en el cuerpo del correo hay un enlace a Dropbox con el proyecto con los .jar, pero seguirá
siendo necesaria una configuracion por su parte a su sistema de Oracle, dado que este solo funciona por el momento en nuestro
sistema manejador de base de datos de Oracle.

Cualquier otro inconveniente o duda que se presente contacte a los administradores, ya sea Rodrigo o Jorge.


