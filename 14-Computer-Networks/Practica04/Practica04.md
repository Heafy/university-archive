# Redes de Computadoras
## Práctica 4
### Jorge Yael Martínez Flores

#### Desarrollo de la práctica

1. Se agregan la red virtual VMNet7 con conexión *Host-only* y la IP *192.168.1.0* con la máscara de red *255.255.255.0*.

![](https://i.imgur.com/19QmNm5.png) | 
|:--:| 
| Creación de la red virtual |


2. Se cambian los adaptadores de red con esta nueva red virtual en el nuevo equipo *Ubuntu DNS*, además de para los equipos *Ubuntu Server* y *Ubuntu Client*.

![](https://i.imgur.com/gOcTcIW.png) | 
|:--:| 
| Asignación de red virtual |


3. Se configura la dirección ip estática en el equipo *Ubuntu DNS*. Notese que desde Ubuntu 17.10 se usa netplan en lugar de ifupdown para asignar las direcciones IP de manera estática, así utilizando la nueva herramienta se intenta cambiar la ip. Otra consideración que puede derivar errores es que la interfaz de red usada en la máquina virtual es *ens33* en lugar de *eth0*, esta puede ser derivada del antiguo nombre que le daba Ubuntu a dicha interfaz de red.

![](https://i.imgur.com/2OSQt8J.png) | 
|:--:| 
| Cambio de ip con netplan |

![](https://i.imgur.com/WWkCYC0.png) | 
|:--:| 
| Verificación del cambio de la ip estática |


4. Se regresa a la interfaz de red NAT en el equipo *Ubuntu DNS* para poder instalar la herramienta Bind.
5. Se crean los archivos de configuración como se especifíca en la práctica.
6. Se reincia el servicio DNS con bind9.
7. Se verifica el estado del servicio. Aquí se presenta un error dado que el servicio no aparece como se muestra en la práctica.

![](https://i.imgur.com/X4OmGhn.png) | 
|:--:| 
| Reinicio de bind9 |


8. Se agrega las configuraciones al */etc/resolv.conf* del equipo *Ubuntu Client* para poder resolver las directivas.

![](https://i.imgur.com/Ub7Q5F8.png) | 
|:--:| 
| Cambio de */etc/resolv.conf* |

9.  Se intenta conectar al sitio *www.redes.edu/form.html* desde *Ubuntu CLient* sin resultado.

![](https://i.imgur.com/UDjWf8c.png) | 
|:--:| 
| Conexión sin resultados |

#### Cuestionario

1. ¿Qué función tiene un servidor DNS autoritativo?

    Aquel que tiene una respuesta en su base de datos local para un dominio sobre el que se le pregunte. Éste es el tipo de servidores DNS con el que más común. Se crean los registros DNS que después conformarán el contenido de lo que es un servidor DNS, los cuales podrán después usarse para dar respuestas autoritativas a los clientes que pregunten sobre las direcciones IP asociadas a alguno de los nombres previamente guardados. También son conocidos como servidores DNS raíz.

2. ¿Qué función tiene un servidor DNS primario?

    Este servidor contiene la información de un dominio. Los servidores primarios obtinenen esta información directamente de los archivos locales, es decir, este contiene la información principal para poder resolver los nombres de dominio.

3. ¿Qué función tiene un servidor DNS secundario?

    Los servidores secundarios contienen copias de solo lectura del archivo y obtienen su información de un servidor primario si este está disponible. Una zona puede solo tener un servidor DNS primario pero puede tener cualquier número de servidores DNS secundarios. Tambien actuarán como respaldo ante posibles errores que puedan ocurrir en los servidores primarios.

4. ¿Por cuál puerto se establecen las conexiones al servidor DNS?

    Puerto 53.

5. Investigar el uso de los siguientes dominios, com, edu, gov, org, net, biz, xyz, mx, jp, tv. 

    * **com:** Significa "Company". Se refiere a cualquier sitio web con actividad comercial.
    * **edu:** Significa "Education". Dominios reservados para las instituciones educativas acreditadas.
    * **gov:** Significa "Government". Utilizado por el gobierno federal de los Estados Unidos.
    * **org:** Significa "organization". Se refiere a cualquier sitio web relacionado a instituciones, establecimientos educ! acionales, organizaciones sin fines de lucro etc.
    * **net:** Significa "Internet". Se refiere a cualquier sitio web relacionado al rubro de internet, tecnología, telecomunicaciones, aunque hoy en día se utiliza más ampliamente.
    * **biz:** Significa "Business". Es una terminación nueva que en general se refiere a cualquier sitio web con actividad comercial y cualquier negocio.
    * **xyz:** El origen viene de un dominio que busca conectar a las generaciones X, Y, Z y ofrecer opciones innovadoras de internet para todo el mundo, buscando nombres mas llamativos.
    * **mx:** Significa "México". Dominio de nivel superior para páginas páginas de México.
    * **jp:** Significa "Japón". Dominio de nivel superior para páginas páginas de Japón.
    * **tv:** Significa "Tuvalú". Dominio de nivel superior para páginas páginas de Tuvalú. Desde 1998 este país obtiene beneficios de la venta de este dominio a compañías privadas de televisión. 

6. Investigar sobre los registros DNS, A, AAAA, PTR, CNAME, TXT, MX.

    * **A:** Convierte nombres de hosts en direcciones IPv4.
    * **AAAA:** Convierte nombres de hosts en direcciones IPv6. 
    * **PTR:** Convierte direcciones IPv4 a nombres de hosts.
    * **CNAME:** Contiene el nombre de dominio y es solamente para subdominios. Redirige el subdominio al dominio deseado.
    * **TXT:** Es un tipo de registro de sistema de DNS que contiene información de texto de fuentes externas a un dominio y que se añade a la configuración de este.
    * **MX:** contiene el nombre del servidor de e-mail. Define donde se tienen que entregar los correos electrónicos.

7. ¿En cuál RFC se especifica el protocolo DNS? 

    En *RFC 1034* se encuentran los nombres de dominio, sus definiciones y sus servicios, en el *RFC 1035* está la implementación y especificación.

8. Consigne en el reporte una captura donde se muestre la resolución DNS seguida de la respuesta HTTP/S del servidor web.