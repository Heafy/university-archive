# Redes de Computadoras
## Práctica 2
### Jorge Yael Martínez Flores

#### Reporte

1. Se instaló el servidor apache junto con los módulos y bibliotecas para poder usar Python. Se verificó la conexión.

    ![](https://i.imgur.com/8aenYJ8.png) | 
    |:--:| 
    | Instalación del servidor HTTP Apache.l |

2. Se crearon los archivos **form.html** e **index.py** como lo indica en la práctica 
3. Se cambian la configuración del servidor de Apache al agregar los Handlers para **.cgi** y **.py**
4. Se navega hacia el formulario que creamos y se llenan con datos aleatorios.

    ![](https://i.imgur.com/WUmstSe.png) | 
    |:--:| 
    | Formulario en el navegador web |

5. Se muestran los datos capturados del formulario con Wireshark.

    ![](https://i.imgur.com/hmRCz9d.png) | 
    |:--:| 
    | Captura de la información del formulario con Wireshark |

6. Se habilita el módulo SSL en Apache
7. Se crean las llaves firmadas.
8. Se sustituyen las llaves en la configuración SSL.

    ![](https://i.imgur.com/DVHFIzF.png) | 
    |:--:| 
    | Configuración SSL |

9.  Se reinicia el servidor (desde aquí es donde todo falla).

Al modificar las configuraciones SSL no se aprecia ningun cambio ni visualmente ni en la captura de tráfico con Wireshark, así que debo de haber realizado un paso incorrecto.

#### Cuestionario

1. ¿De qué manera influyó la implementación de SSL/TLS a los códigos **form.html** e **index.py**? ¿Fué necesario realizar los cambios en el código? Justifique su respuesta relacionandola con el concepto de Encaosulado de protocolos y Pila de protocolos de internet.


2. ¿Qué son y para qué sirven los certificados digitales?

    Es un archivo firmado por una autoridad de confianza y garantiza a los clientes o visitantes web que las transacciones y operaciones realizadas en su sitio web son seguras. Es un medio para que las organizaciones puedan aumentar su tráfico en la red y crezcan sus operaciones mostrándoles a los usuarios que son seguras.

3. ¿Cuáles sitios conoce que utilicen HTTPS y cuál podría ser una posible razón de su implementación?

    Facebook, Dropbox, UNAM, la mayoría de los portales que buscan proteger la información de sus clientes ante posibles ataques o robos de información que se puedan presentar. Notese que [fciencias.unam.mx](http://www.fciencias.unam.mx/) no usa HTTPS.

4. En el código *form.html* cambie el método get por post y realice una nueva captura de Wireshark ¿Cuál es la diferencia que se aprecia en la captura entre el método get y el método post usando HTTP? ¿CUál es la diferencia que se nota en el navegador web cuando se usa cada uno de los métodos?

    La diferencia en el navegador web es que usando el método **GET** todos los parámetros aparecen en la URL del navegador, es decir estos son visibles para el usuario, contrario a POST, que los "oculta" en el código y no son visibles al usuario.

5. Investigue a que hace refencia la dirección *IP 127.0.0.1* y lo relacionado a ella.

    Hace referencia a *localhost*, es la dirección IP a la que apunta tu equipo desde tu equipo. Este mecanismo se usa constantemente para acceder a sus propios servicios de red, acceder a servicios web instalados dentro del equipo.