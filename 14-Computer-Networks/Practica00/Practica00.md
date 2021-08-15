# Redes de Computadoras

## Práctica 0

### Jorge Yael Martínez Flores

1. Describe la estructura en un árbol de sistema de archivos de los sistemas operativos GNU/Linux.

    El directorio raíz */* es el nivel mas alto del sistema operativo, debajo de este se encuentra los siguientes directorios:

    * **/bin:** Directorio donde se almacenan los comandos de la terminal.
    * **/boot:** Contiene los archivos necesarios para iniciar el sistema operativo, además del kernel.
    * **/dev:** Archivos que ayudan a comunicarse con los dispositivos de hardware del equipo.
    * **/etc:** Archivos de configuración global del equipo, afecta a todos los usuarios.
    * **/home:** Directorio principal donde se almacenan los archivos de los usuarios.
    * **/lib:** Espacio para las bibliotecas y módulos del kernel.
    * **/media:** Punto de montaje para dispositivos externos extraíbles, como HDD externos, USB's o DVD's.
    * **/mnt:** Punto de montaje para dispositivos montados "temporalmente", como los sistemas de archivos compartidos en red.
    * **/opt:** Almacenamiento adicional de software, independiente del administrador de paquetes.
    * **/proc:** Fichero virtual para comunicarse entre el kernel y los procesos.
    * **/root:** Directorio principal del superusuario.
    * **/run:** Directorio temporal que se inicia con el proceso de inicio del sistema operativo. Se elimina despúes del arranque.
    * **/sbin:** Contiene comandos administrativos exclusivos del superusuario.
    * **/srv:** Directorio para los datos y servicios de HTTP y FTP.
    * **/sys:** Ficheros virtuales para obtener información sobre el kernel del sistema.
    * **/tmp:** Aquí se almacenan archivos temporales usados por aplicaciones.
    * **/usr:** Utilidades y aplicaciones del usuario.
    * **/var:** Almacena los registros, bases de datos y archivos temporales relacionados, además de los archivos de registro del sistema.

2. Describa que es **entrada estándar, salida estándar y error estándar**. Proporcione un ejemplo para cada una.

    * **Entrada estándar:** Los datos que recibe la entrada de la terminal o de una aplicación para ejecutarse.
    * **Salida estándar:** Método que utiliza la terminal para mostrar información en forma de texto.
    * **Error estándar:** Informa sobre los problemas que suceden durante la ejecución de un comando.

3. Define que es una redirección.

    Mueve la información de un lado a otro, con esta podemos redirigir la salida estandar como un parámetro de la entrada estándar o a un archivo de texto.

4. ¿Cuáles son los caracteres para redirigir la salida entre procesos y archivos? Explique a que se refiere cada una.

    Redirecciona la salida a un archivo de texto, en dado caso sobreescribe:
    ```bash
    echo "Hello world" > text.txt
    ```

    Redirecciona la salida a un archivo de texto, agrega no sobreescribe.
    ```bash
    echo "Hello world" >> text.txt
    ```

    Redirecciona la salida y error estándar que pueda ocurrir durante la ejecución.
    ```bash
    echo "Hello world" > text.txt 2 >&1
    ```

5. Listar todos los archivos del directorio *bin*

    ```bash
    ls /bin
    ```

6. Listar con formato largo todos los archivos del directorio *tmp*

    ```bash
    ls -la /tmp
    ```

7. Listar todos los archivos, incluidos los ocultos, del directorio raíz.

    ```bash
    ls -a /
    ```

8. Cambiarse al directorio *tmp* y crear directorio *PRUEBA*

    ```bash
    cd /tmp
    mkdir PRUEBA
    ```

9. Comando para verificar el directorio de trabajo actual

    ```bash
    # Print working directory
    pwd
    ```

10. Comando para mostrar la fecha y hora actual

    ```bash
    date
    ```

    Adicionalmente si se encuentra instalado timedatectl ofrece un listado mas detallado de la fecha y hora:
    ```bash
    timedatectl
    ```

11. Copiar los archivos del directorio *rc.d* que se encuentra en */etc* al directorio */tmp/PRUEBA/*

    ```bash
    cp -r /etc/rc.d /tmp/PRUEBA
    ```

12. Mostrar por pantalla los archivos ordinarios del directorio *HOME* y sus subdirecciones

    ```bash
    ls -r $HOME
    ```

13. ¿Cuál es la diferencia de enlace duro y enlace simbólico?

    * **Enlace duro:** Es un identificador numérico que apunta a un fichero o archivo almacenado en disco.
    * **Enlace simbólico:** Es como un acceso directo de Windows, apunta al nombre de un archivo o fichero y después este apunta a un contenido almacenado en disco:
  
14. Dar dos ejemplos de creación de enlaces simbólicos y enlaces duros.

    Enlace duro:
    ```bash
    ln text0.txt hard_link0
    ln text1.txt hard_link1
    ```

    Enlace simbólico:
    ```bash
    ln -s text0.txt $HOME/symbolic_link0
    ln -s text1.txt $HOME/symbolic_link1
    ```

15. ¿Qué comando se utiliza para realizar una instalación por paquetes en sistemas operativos GNU/Linux Debian y GNU/Linux CentOS?

    Debian:
    ```bash
    sudo apt-get update
    sudo apt-get install <package_name>
    # O un paquete APT
    sudo apt upgrade
    sudo apt install <package_name>
    ```

    CentOS:
    ```bash
    sudo yum -y update
    sudo yum install <package_name>
    ```

16. Escriba el comando que se utiliza para instalar los paquetes de Apache, MySQL y PHP, tanto para sistemas operativos GNU/Linux Debian y GNU/Linux CentOS.

    Debian:
    ```bash
    #Apache
    sudo apt-get update
    sudo apt-get install apache2
    #MySQL
    sudo apt update
    sudo apt install mysql-server
    # PHP
    sudo apt update
    sudo apt install php7.2
    sudo apt install php7.2-cli php7.2-common php7.2-curl php7.2-mbstring php7.2-mysql php7.2-xml
    ```
    CentOS:
    ```bash
    #Apache
    sudo yum -y update
    sudo yum install httpd
    #MySQL
    sudo yum -y update
    sudo yum install mysql-server
    # PHP
    sudo yum -y update
    sudo yum install php php-mysql
    ```

17. ¿Que comando muestra los procesos del sistema con la sintaxis BSD?

    ```bash
    ps -aux
    ```

18. ¿Qué comando muestra todas las conexiones en modo escucha para el protocolo TCP, sin resolver nombres de dominio?

    ``` bash
    netstat -al
    ```

19. ¿Qué comando muestra todas las conexiones en modo escucha para el protocolo UDP, sin resolver nombres de dominio?

    ```bash
    netstat -ul
    ```

20. Escriba el comando o conjunto de comandos para localizar la cadena *localhost* en el directorio */etc/* que incluya el número de línea donde se encuentra y el archivo.'

    ```bash
    grep -rn localhost/etc
    ```

21. ¿Qué comando o conjunto de comandos podrían ser utilizados para localizar los nombres y solo sean directorios que comienzan con la letra *d* bajo la estructura */etc/*?

    ```bash
    find /etc -type d -name d*
    ```

22. ¿Qué comando o conjunto de comandos podrían ser utilizados para localizar los nombres que solo sean archivos y tengan extensión *.conf*?

    ```bash
    locate *.conf
    ```
