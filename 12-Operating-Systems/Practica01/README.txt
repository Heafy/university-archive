Integrantes del equipo:
- Estrada Gomez Cesar Derian
- Martínez Flores Jorge Yael
- Sanchez Morales Rodrigo Alejandro
- Tadeo Guillen Diana G.

1. ¿Por qué en Pintos los procesos de kernel se llaman threads en lugar de procesos?
Representa los procesos del usuario

2. ¿Para qué sirve el stack de un proceso?
En Pintos, cada thread o proceso tiene un bloque de 4KB donde almacena el PCB y el stack,
primero almacena el bloque de control de proceso al inicio del bloque y el resto lo ocupa
para almacenar el stack del thread.

3. En Pintos cada thread utiliza un bloque de 4KB en el cual se almacena su PCB y el stack del thread, ¿Donde se almacenan las variables globales?
La primera tarea del código está dentro del archivo start.s, obtienen el tamaño de la 
memoria de la máquina solicitandola al BIOS. La función almacena el tamaño de la memoria
en páginas, en la variable init_ram_pages.

4. Si suponemos que solamente existe un thread en ejecución y dicho thread se bloquea (utilizando la función thread_block) ¿De qué forma se podría despertar si es el único proceso en ejecución?
El hilo no se volverá a despertar hasta que 
