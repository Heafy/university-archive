# Redes de Computadoras
## Práctica 3
### Jorge Yael Martínez Flores

#### Desarrollo de la práctica

Se usaron dos equipos virtuales para el desarrollo de la práctica:
    * Ubuntu 01 64-bit para el equipo servidor
    * Ubuntu 02 64-bit para el equipo cliente

1. Se instaló y se configuró el equipo servidor con MySQl Server.

![](https://i.imgur.com/yclssv4.png) | 
|:--:| 
| Instalación y configuración de MYSQL Server |

2. Se modifica la IP como se indica en la práctica.

Notese que, aunque en la práctica se indica que el archivo a modificar es */etc/mysql/my.cnf* este no contiene ningún parámetro que tenga que ver con la IP, el archivo correcto se encuentra en */etc/mysql/mysql.conf.d/mysqld.cnf*

![](https://i.imgur.com/ka5x3EJ.png) | 
|:--:| 
| Cambio de IP en la configuración de MySQL |


3. Se crea el usuario de prueba *test*

![](https://i.imgur.com/sMtk7PF.png) | 
|:--:| 
| Creación del usuario test |

4. En el equipo cliente se verifica que tenga comunicación con el equipo servidor.

![](https://i.imgur.com/Kkfd2cL.pngg) | 
|:--:| 
| Comunicación existente entre equipo cliente y servidor |

5. Posteriormente se crea la conexión SQL desde el equipo cliente.

![](https://i.imgur.com/cm4h9Mv.png) | 
|:--:| 
| Conexión SQL desde el equipo cliente |

6. Se crean las tablas desde el equipo cliente lo cual nos ayuda a verificar que la conexión es funcional.

![](https://i.imgur.com/cmwR7tU.png) | 
|:--:| 
| Creación de tablas desde el equipo cliente |

7. En el equipo servidor se modifica el archivo *index.py* con la configuración de MySQLdb

![](https://i.imgur.com/MHQXoAO.png) | 
|:--:| 
| Modificación del *index.py* |

8. Finalmente la página no muestra el resultado actualizado, a pesar de que la configuración funcionaba correctamente.

![](https://i.imgur.com/43XeMP8.png) | 
|:--:| 
| Página final sin información de MySQL |

#### Cuestionario

1. ¿Qué tienen en común el marco de trabajo MVC y el modelo TCP/IP? Justifique su respuesta relacionándola con el concepto de Protocolos por capas.

Aplicación funciona como la Vista, Transporte e Red funcionan como el Modelo y la capa de Enlace funciona como el Controlador. 

2. ¿Cuál es la diferencia entre una Base de Datos y un manejador de Base de Datos?

    Una base de datos es el conjunto de datos almacenados relacionados que se encuentran agrupados. 

    El manejador es el software que sirve de interfaz entre la base de datos, el usuario y las aplicaciones que utilizan la base de datos. SU objetivo es manejar los conjuntos de datos que se almacenan en la base de datos.

3. ¿Por qué es recomendable tener los servicios web y de base de datos en servidores diferentes?

    Para mantener la integridad de los datos segura, ante un ataque de servidores o problemas que se pueden llegar a presentar, solo se perdería el servicio web o la base de datos y no los dos al msimo tiempo.

4. ¿Cuál es el puerto por defecto donde se brinda el servicio de MySQL y en que archivo se encuentra dicha configuración?

    Se conecta a través del puerto **3306** Y su archivo de configuración según el sistema operativo:

    * **Windows:** C:\ProgramData\MySQL\MySQL Server 5.7\my.ini
    * **UNIX:** /etc/mysql/my.cnf

5. Cambie la línea 47 del archivo */etc/mysql/my.cnf* de *0.0.0.0* a *direccion_B* y posteriormente anote las diferencias al ejecutar el comando **ss**. Adjunte captura de pantalla que refleje el cambio.

![](https://i.imgur.com/OgmdbZ8.png) | 
|:--:| 
| Captura antes y después del cambio |