<h1 align="center">Redes de Computadoras</h1>
<h2 align="center">Practica 5</h2>
<h2 align="center">Martínez Flores Jorge Yael</h2>

# Desarrollo de la práctica

1. Se crea la nueva máquina virtual *Ubuntu Proxy* como una copia de *Ubuntu CLient* de la prácticas anteriores.
2. Se conecta a la red virtual *VMnet7* creada en la práctica anterior.

![](https://i.imgur.com/E999Tmt.png) | 
|:--:| 
| Modificación de la red de *Ubuntu Proxy* |

3. Se modifican los registros A y PTR de *Ubuntu DNS* de la práctica anterior.

![](https://i.imgur.com/pYQgKpQ.png) | 
|:--:| 
| Modificación de los archivos del servidor DNS |

4. Se agrega la dirección IP del servidor proxy para poder realizar consultas al servidor DNS.
5. Se limpia la caché del servidor, resultando en un error.

![](https://i.imgur.com/3czieJo.png) | 
|:--:| 
| Error al tratar de limpiar la cache del servidor |

6. Se cambia la IP de *Ubuntu Proxy* usando el mismo método con netplan de la práctica anterior.
7. Se agrega el servidor proxy a la configuración de red.

![](https://i.imgur.com/X5GzGwV.png) | 
|:--:| 
| Proxy agregado |

8. Se agregan y se habilitan los módulos de Apache necesarios.
9.  Se modifica la ruta de sitios habilitados de Apache.

![](https://i.imgur.com/r3Eb5EC.png) | 
|:--:| 
| Activación de módulos de Apache2 |

10. Se agrega la configuración del servidor DNS al cliente para que pueda utilizarlo.

![](https://i.imgur.com/44SNqHq.png) | 
|:--:| 
| Cambio en *resolv.conf* |

11. Se hace la resolución con *nslookup*, aunque muestra errores que pueden haber derivado de la práctica anterior.

![](https://i.imgur.com/Wl8cm2d.png) | 
|:--:| 
| Error al no poder resolver la dirección con el proxy |

# Cuestionario

1. ¿Qué función tiene la directiva en Apache ProxyPass?


    Permite mapear servidores remotos en el servidor local. El servidor local no actúa como un proxy, sino que se comporta como un espejo del servidor remoto. El servidor local pasa a ser el proxy inverso.

2. ¿Qué función tiene la directiva en Apache ProxyPassReverse?

    Permite a Apache httpd la URL en las cabeceras de Ubicación, Ubicación de Contenidos y URI de las respuestas de redirección HTTP. Importante cuando Apache se utiliza como un proxy inverso para evitar pasar por alto el proxy debido a los redireccionamientos HTTP en los servidores detrás del proxy inverso.

    Sólo se reescriben estas cabeceras de respuesta HTTP. Apache no reescribe otras cabeceras de respuesta, esto significa que si el contenido del proxy contiene referencias a la URL, éstas pasarán por alto el proxy.

3. ¿Qué función tiene un servidor Proxy?

    Es un equipo dedicado o un sistema de software que se ejecuta en un equipo de cómputo que actúa como intermediario entre un dispositivo de punto final, como una computadora, y otro servidor del cual un usuario o cliente solicita un servicio. El servidor proxy puede existir en la misma máquina que un servidor de firewall o puede estar en un servidor independiente, que reenvía las solicitudes a través del firewall.

4. ¿Qué características tiene un Forward Proxy vs Reverse Proxy?

    **Forward Proxy:**
    * Se encuentra entre el cliente y otros servidores. 
    * Puede reducir el tráfico de salida mediante el uso de datos de caché.
    * Puede restringir el acceso de los usuarios a sitios no deseados.
  
    **Reverse Proxy:**
    * Se interpone entre un servidor y muchos clientes, como por ejemplo las peticiones de entrada de una página web corporativa.
    * De este modo, los usuarios de Internet pueden conectarse a redes privadas.
    * El usuario puede conectarse con el servidor original y reenviar la solicitud original. 
    * Se puede recibir una solicitud de conexión de usuario.
    * Puede realizar multiplexación TCP mediante la cual se terminan las conexiones entrantes y se agrupan para establecer nuevas conexiones.