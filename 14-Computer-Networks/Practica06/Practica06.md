<h1 align="center">Redes de Computadoras</h1>
<h2 align="center">Practica 6</h2>
<h2 align="center">Martínez Flores Jorge Yael</h2>

# Desarrollo de la práctica



## 1. Creación de la infraestructura

**1.1** Se crea la primera versión de la infraestructura, como el router "Router Labs" no tiene las interfaces de red necesarias todavía no se realiza la conexión entre el router y los switchs de los laboratorios.

![](https://i.imgur.com/ouw2OWG.png)
| Infraestructura incompleta |

**1.2** Nos saltamos el paso de crear las direcciones IP ya que estas se van asignando conforme al desarrollo de la práctica.

## 2. Configuración de una red inalámbrica

**2.1** Se configura el Wireless Router RIU como se indica en la configuración de la tabla 1 de la Práctica 06, cambiando el internet y Network Setup, ademáß de modificar el SSID por Default para que ahora sea RIU.

**2.2** Se agrega una interfaz de red inalámbrica a la Laptop para poder conectarla a la RIU.

![](https://i.imgur.com/I1iUMbJ.png)
| Interfaz inalámbrica para la Laptop0 |

**2.3** Se conectan la Laptop y los dos Smartphones a la nueva red inalámrbica RIU para poder dar el siguiente resultado.

![](https://i.imgur.com/j4qm1V1.png)
| Infraestructura paa la red inalámbrica RIU |

## 3. Configuración de servidor web

**3.1** Se configura el servidor web con los parámetros de la tabla 3.

**3.2** Se agrega mi nombre a la página principal del servidor web.

![](https://i.imgur.com/IGORVRR.png)
| Código HTML de la página principal |

## 4. Configuración de un servidor DNS

**4.1** Se configura el servidor DNS con los parámetros dados en la tabla 4.

**4.2** Se crean los registros DNS como se encuentran en la tabla 5.

**4.3** Se prueba la configuración con el servidor, en la Laptop 0 conectada a la RIU, se visita la página *www.fciencias.unam.mx* y con un cmd se ejecuta el comando *nslookup www.fciencias.unam.mx*.

![](https://i.imgur.com/i3le2E2.png)
| Página principal vista desde el equipo Laptop0 |

![](https://i.imgur.com/vagZ8cF.png)
| Resolución de DNS desde el equipo Laptop0 |

## 5. Configuración del laboratorio A y B

**5.1** Se configura el servidor DHCP  del Laboratorio A con los parámetros de red la tabla 6.

**5.2** Se configuran los parámetros del servidor DHCP con su configuración del servidor con los parámetros de la tabla 7.

**5.3** Se configura Printer Lab A con los valores de la tabla 8.

**5.4** Se configuran los dos equipos de escritorio del Laboratorio A para que el DHCP les asigne los parámetros de conexión

**5.5** De la misma forma configuramos el servidor DHCP, el Printer y los dos equipos de escritorio del laboratorio B, con los valores de las tablas 9, 10 y 11 respectivamente.

## 6. Configuración NAT/PAT para el router del laboratorio A y B

**6.1** Se agregan las dos interfaces de red FastEthernet al router Router Labs

![](https://i.imgur.com/4QwLoFW.png)
| Interfaces de red para el router Router Labs |

**6.2** Se realizan las conexiones restantes con el Switch Ciencias y el Router Labs, además este se conecta a los Switch de los Laboratorios A y B.

![](https://i.imgur.com/wmhbEzQ.png)
| Nuevas conexiones para la infraestructura |

**6.3** Se hacen las configuraciones de red según los parámetros de la tabla 12 para el router Router Labs.

**6.4** Se abre la consola de comandos CLI para terminar la configuración y hacer que las conexiones que se realizaron en el paso 6.2 funcionen correctamente, tanto para el Laboratorio A como el Laboratorio B.

![](https://i.imgur.com/eaR12Sq.png)
| Configuracion en CLI para las nuevas interfaces de red |

![](https://i.imgur.com/UIzRyq2.png)
| Conexiones realizadas entre el router y los switches |

**6.5** Se prueban la configuración abriendo el navegador en el primer equipo de escritorio del Laboratorio A entrando a la página *www.fciencias.unam.mx*, se realiza el mismo paso para el laboratorio B.

![](https://i.imgur.com/D8n1eR5.png)
| Prueba en el equipo del Laboratorio A |

![](https://i.imgur.com/gBi4T9Y.png)
| Prueba en el equipo del Laboratorio B |

![](https://i.imgur.com/oswE1yW.png)
| Infraestructura terminada |

# Cuestionario

1. ¿Qué direcciones IP le asignó el router inalámbrico a cada host, a la laptop y a los dos smartphones, conectados a la red inalámbrica RIU?

    * **Laptop:** 10.10.0.2
    * **Smartphone0:** 10.10.0.3
    * **Smartphone1:** 10.10.0.4

2. ¿Qué direcciones IP les asignó el servidor DHCP a cada host de la red del laboratorio A y B?

    * **A - PC1:** 169.254.11.153
    * **A - PC2:** 169.254.74.90
    * **B - PC1:** 169.254.198.132
    * **B - PC2:** 169.254.55.215

3. Investigue el concepto de DHCP.

    Significa protocolo de configuración dinámica de host (Dynamic Host Configuration Protocol), es un protocolo de red mediante el cuál un servidor DHCP asigna dinámicamente una dirección IP y demás parámetros de configuración a cada dispositivo de la red para que puedan comunicarse con otros dispositivos por su IP.

4. Investigue los conceptos NAT y PAT.
    * **NAT:** (Network Address Translation) Mecanismo diseñado para conservar direcciones IP. Permite que se conecten a Internet las redes de IP privada que emplean direcciones IP no registradas. NAT opera en routers y convierte las direcciones privadas de la red interna en direcciones públicas, antes de que se reenvíen los paquetes a otra red.
    * **PAT:** (Port Address Translation) Característica del mecanismo NAT,  traduce conexiones TCP y UDP hechas por un host y un puerto en una red externa a otra dirección y puerto de la red interna. Permite que una sola dirección IP sea utilizada por varias máquinas de la intranet. Con PAT, una IP externa puede responder hasta a 64000 direcciones internas.

5. ¿Qué es la máscara de red o Netmask?

    Es una combinación de bits que sirve para delimitar el ámbito de una red de ordenadores.​ Su función es indicar a los dispositivos qué parte de la dirección IP es el número de la red, incluyendo la subred, y qué parte es la correspondiente al host. Mediante la máscara de red, un dispositivo podrá saber si debe enviar un paquete dentro o fuera de la subred en la que está conectado. Por ejemplo, si el router tiene la dirección IP 192.168.1.1 y máscara de red 255.255.255.0, entiende que todo lo que se envía a una dirección IP con formato 192.168.1.X, se envía hacia la red local, mientras que direcciones con distinto formato de direcciones IP serán buscadas hacia afuera (internet, otra red local mayor, entre otros.)

6. ¿Qué es la puerta de Enlace predeterminada o Default Gateway?

    Es el dispositivo que sirve como enlace entre dos redes, es decir, es aquel dispositivo que conecta y dirige el tráfico de datos entre dos o más redes. Generalmente ese dispositivo es el router o módem-DSL, que conecta la red local (LAN) hogareña u ofimática con Internet (WAN).

7. ¿Qué es el SSID en una red inalámbrica?

    El SSID (Service Set Identifier) es un identificador incluido en la red inalámbrica y que viaja con cada paquete de información de la misma, de forma que puede ser identificado como parte de ella.