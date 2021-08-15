<h1 align="center">Redes de Computadoras</h1>
<h2 align="center">Practica 7</h2>
<h2 align="center">Martínez Flores Jorge Yael</h2>

# Desarrollo de la práctica

## 1. Configuración de la infraestructura

**1.1** Se agrega la infraestructura a la Práctica 06. Sin contar las configuraciones de los routers que se agregarán mas tarde.

![](https://i.imgur.com/3DjUnnw.png)

## 2. Configuración de los parámetros de red

**2.1** Se configuran las direcciones IP, la máscara de red, el gateway y los DNS de los equipos creados, esto se hizo de igual manera que en la práctica 06.

## 3. Configuración de los sitios Web

**3.1** Se configuran los servidores web de la Facultad de Ciencias, del Instituo de Física y de la DGTIC de igual manera que en la práctica 06. Se coloca el encabezado al igual que en la práctica 06 con el nombre de cada servidor.

![](https://i.imgur.com/smWwLpc.png)

![](https://i.imgur.com/07y7ptw.png)

![](https://i.imgur.com/JKOgzVB.png)

## 4. Configuración de los registros DNS

**4.1** Se configuran los 3 servidores DNS con los registos que están en las tablas 5, 6, 7 y 8, con los comandos aprendidos en las Práctica 06.

## 5. Configuración de los router

**5.1** Se cambian los nombres de cada router de acuerdo con la tabla 9.

**5.2** Se configuran las interfaces de los 3 routers como en la práctica 06.
 
**5.3** Se configuran las interfaces de SW-Core para que se puedan asignar una dirección IP a cada interfaz.

![](https://i.imgur.com/5GIx6zB.png)

## 6. Configuración de rutas estáticas

**6.1** Se configuran las rutas estáticas para el Router Labs.

![](https://i.imgur.com/9fePlLV.png)

**6.2** Se configuran las rutas estáticas para los Router de Ciencias, el del Instituo de Física y de la DGTIC.

![](https://i.imgur.com/E5rtzhx.png)

![](https://i.imgur.com/oHsKzy3.png)

![](https://i.imgur.com/iUGO2QJ.png)

**6.3** Se configuran las rutas estáticas para el SW-Core, se configua una ruta estática por cada red conectada a la interfaz.

![](https://i.imgur.com/KdInVEa.png)

![](https://i.imgur.com/rTD9opB.png)

## 7. Comprobar la configuración

**7.1** Se intenta probar la configuración, aunque sin el resultado esperado.

# Cuestionario

1. ¿Cuáles son las funciones de un router en una red de computadoras?

    Proporciona conectividad a nivel de red (nivel red modelo OSI o TCP/IP). Su función principal consiste en enviar los paquetes de datos de una red a otra, es decir, conectar subredes entre si, una subred es un conjunto de equipos con su IP que se pueden comunicar sin la intervención de un switch, y que por tanto tienen prefijos de red distintos.

2. ¿Qué son los protocolos de ruteo?

    Los protocolos de ruteo permiten que los dispositivos de capa 3 aprendan y compartan de forma dinámica la información de ruteo. De esta forma, los equipos que habiliten dicho protocolo, intercambiaran toda la información de prefijos, parámetros y atributos de rutas entre sí. Cuando un dispositivo agrega, cambia o remueve alguna información en particular, el cambio se propaga y el resto de los equipos se actualiza de forma dinámica.

3. ¿Qué es una ruta estática en un router?

    Sirven para establecer rutas específicas que han de seguir los paquetes para pasar de un puerto de origen hasta un puerto de destino.

4. Indique para que se usan los registros A, NS, CNAME y SOA en un servidor DNS.

    |  Tipo |        Significado        |                                                                               Significado                                                                              |
    |:-----:|:-------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
    | A     | Dirección IPV4 de un host | Tipo de registro del DNS que asocia un número IP a un dominio                                                                                                          |
    | NS    | Servidor de nombres       | Determinan los servidores que comunicarán la información del DNS de un dominio                                                                                         |
    | CNAME | Nombre canónico           | Enlaza un nombre de alias con otro nombre de dominio canónico o auténtico                                                                                              |
    | SOA   | Inicio de autoridad       | Contiene identificadores del servidor de nombres con autoridad sobre la denominación y su operador que regulan el funcionamiento general del DNS para la denominación. |
    