Integrantes del equipo:
- Martínez Flores Jorge Yael
- Sanchez Morales Rodrigo Alejandro
- Tadeo Guillen Diana G.

1. ¿Por qué el espacio de memoria del Kernel siempre está presente?
Consideremos que aquí, en el espacio de memoria del Kernel es en donde se ejecutan 
las tareas admininistrativas de threads y donde se realiza la inicialización,
tenemos que aquí es en donde posteriormente le asociamos un proceso de usuario.

2. ¿Es posible cambiar el valor del PHYS_BASE?
No, es el valor de la memoria desde donde empieza PHYS_BASE lo guarda como una
referencia principal al inicio de la memoria virtual, si alteramos su valor
cambiarian todas las demás referencias a la memoria.

3. ¿Por qué es el sistema operativo el encargado de colocar los parámetros iniciales de un proceso?