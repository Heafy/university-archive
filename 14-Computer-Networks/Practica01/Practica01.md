# Redes de Computadoras
## Práctica 1
### Jorge Yael Martínez Flores

#### Reporte

Una vez configurado el equipo virtual y con Wireshark instalado se realizaron los siguientes pasos:

1. Se revisó la información de las direcciones físicas y lógicas del equipo físico y virtual con el comando **ip addr**.

**Máquina virtual Ubuntu**

* Dirección física: **00:0C:28:88:19:E5**
* Dirección lógica: **192.168.86.128**

#### Equipo físico Windows

* Dirección física: **F8:34:41:D6:F3:80**
* Dirección lógica: **192.168.100.22**

2. Se abre Wireshark con permisos de superusuario y se empieza a monitorear en tráfico en la interfaz **ens33**.
3.  Se limpia la tabla de consultas ARP con el comando **arp -d ***
4.  Comprobamos la conectividad desde el equipo virtual hacia el equipo físico.

![Virtual Machine connection](https://i.imgur.com/ti2tSCH.png) | 
|:--:| 
| Conexión desde el equipo virtual |

5. Revisamos ahora la conexión desde el equipo físico al virtual

![Host connection](https://i.imgur.com/uGRq1En.pngg) | 
|:--:| 
| Conexión desde el equipo físico |

1. Se comprueban los paquetes ARP desde Wireshark para comprobar que el intercambio de paquetes fue exitoso.

![ARP protocol filter](https://i.imgur.com/5UHYKzW.png) | 
|:--:| 
| Consultas ARP al hacer la conexión |

#### Cuestionario

1. ¿Cuál es la diferencia a nivel de bits entre una dirección física y una lógica?

    En la IP son 4 octetos de 8 bits y en la MAC son 6 octetos de 8 bits.

2. ¿Por qué existen dos consultas ARP?

    Porque una es la consulta de petición en la que pregunta quien es el que tiene la dirección IP por la que está preguntando. Mientras que la segunda consulta es la respuesta, en la que le responde que la dirección IP está en la dirección MAC correspondiente.

3. Describa que significa la salida del comando **arp -a** en el sistema operativo Windows.
   
   Muestra las entradas ARP actuales de cada interfaz de red en el equipo.

4. Investigar y describir de manera breve en que consiste un ataque de ARP spoofing.

    
    ARP es un tipo de ataque en el que se envias mensajes falsos de ARP en el cual el atacante vincula su dirección MAC con la dirección IP de un equipo en la red, si consigue hacerlo empieza a recibir cualquier dato que se puede acceder desde la IP, ya con esto podrá leer los datos o modificarlos antes de reenviarlos o lanzar un ataque de denegación de servicio.

5. Investigar el fabricante del adaptador de red físico del equipo personal.

    La MAC de mi equipo es **F8-34-41-D6-F3-80**, con la descripción *Intel(R) Dual Band Wireless-AC 8265*, una búsqueda de los primeros 3 bits hexadecimales en [HWadress](https://hwaddress.com/) nos dice que es de la marca Intel Corporate, esto quiere decir que Intel proporcionó la antena Wi-Fi de mi equipo.