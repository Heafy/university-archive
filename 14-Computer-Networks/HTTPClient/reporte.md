# Programacion de un cliente de HTTP v1.1

## Redes de Computadoras

### Martínez Flores Jorge Yael

El programa se ejecuta con Python siguiendo las instrucciones acordadas en la especificación del proyecto.

Se recomienda Python v.3.7.0 64 bits para la correcta ejecución del proyecto.

#### Capturas del proyecto

Ejecución del proyecto en la página *www.fciencias.unam.mx* con el método *GET*

![](https://i.imgur.com/gVj7xsS.png)

Ejecución del proyecto en la página *www.fciencias.unam.mx* con el método *HEAD*

![](https://i.imgur.com/wkuW3eo.png)

1.Investigue para que se usan los métodos HEAD, GET, POST, PUT, DELETE.

* **HEAD:** Pide una respuesta idéntica a la que correspondería a una petición GET, pero en la respuesta no se devuelve el cuerpo. Esto es útil para poder recuperar los metadatos de los encabezados de respuesta, sin tener que transportar todo el contenido.
* **GET:** El método GET solicita una representación del recurso especificado. Las solicitudes que usan GET solo deben recuperar datos y no deben tener ningún otro efecto. (Esto también es cierto para algunos otros métodos HTTP.)
* **POST:** Envía los datos para que sean procesados por el recurso identificado. Los datos se incluirán en el cuerpo de la petición. Esto puede resultar en la creación de un nuevo recurso o de las actualizaciones de los recursos existentes o ambas cosas.
* **PUT:** Sube, carga o realiza un upload de un recurso especificado (archivo o fichero) y es un camino más eficiente ya que POST utiliza un mensaje multiparte y el mensaje es decodificado por el servidor. En contraste, el método PUT permite escribir un archivo en una conexión socket establecida con el servidor. La desventaja del método PUT es que los servidores de alojamiento compartido no lo tienen habilitado.
* **DELETE:** Borra el recurso especificado.

2.Investigue los diferentes códigos de estado que usa HTTP, enliste las categorías.

* **Respuestas informativas:** Códigos en el rango (100-199), sirven para enviar estados y saber información.
* **Respuestas satisfactorias:** En el rango (200-299) estan las respuestas que informan mayormente de solicitudes realizadas correctamente.
* **Redirecciones:** Códigos en el rango (300-399) informan de redirecciones, recursos no encontrados, ademáß de recursos reubicados o solicitudes con mas de una respuesta.
* **Errores del cliente:** Errores que suceden de lado del cliente, ya sea por la sintáxis, cuestión de permisos o que no puede localizar el recurso.
* **Errores de servidor:** Errores que suceden de lado del servidor, errores internos, método no soportado, errores en el gateway o por versiones no soportadas de HTTP.

3.Investigue el uso del campo encoding.

Esta cabecera sirve para mejorar la velocidad de transferencia y la utilización de ancho de banda. Los datos HTTP se comprimen antes de ser enviados desde el servidor. Se comprimen desde nivel inferior, es decir la carga util del mensaje HTTP es la que se comprime o el nivel superior, donde el recurso que se transfiere es comprimido.

4.Investigue el uso del campo connection.

Esta cabecera cuando la conexión se mantiene abierta o no una vez que la petición termina. Solo tiene dos opciones *keep-alive* mantiene viva la conexión mientras que *close* la termina.