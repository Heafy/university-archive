# Proyecto 02

## Agenda persistente

Se deberá programar una agenda en Java que sea persistente, es
debe almacenar en disco la información y no perderse al terminar la ejecución.
Esta agenda deberá almacenar objetos de tipo Persona.
decir,

Una persona tiene:
* Nombre completo (nombre(s), apellido(s))
* Teléfono de casa
* Teléfono celular
* Correo electrónico

Tu agenda deberá permitir las siguientes operaciones:
* Agregar contacto: Al agregar un contacto deberás solicitar su nombre, teléfono de casa,
celular
y correo electrónico, deberás validar que toda esta información sea
consistente; es decir, no aceptar número dentro del nombre ni caracteres alfabéticos
en el teléfono y celular; que el correo tenga la estructura de un correo electrónico.

*Buscar contacto: Al buscar un contacto deberás proporcionar su nombre y entonces
mostrarás la información de contacto de todas las personas, tales que
hayan coincidido con el criterio de búsqueda.
Ejemplo:

Búsqueda:
San

Regresaría:
Santiago Mendez Olivares
58962356
5589784859
santi@yahoo.com.mx

Alejandra Sandoval López
78787878
5512894555
ale@gmail.com

*Eliminar contacto: Podrás borrar a un contacto pidiendo el nombre completo de este.

*Editar contacto: Podrás editar la información de un contacto tan como nombre, teléfonos o correo.

*Ver todos los contactos: Al seleccionar esta opción podrás personas guardadas en la agenda.

*Borrar toda la agenda: Esta opción borrará todo lo que haya en la agenda, pidiendo antes un
mensaje de confirmación.
