-- 1. Socios que no han comprado productos (nombre completo, edad y productos)
SELECT NOMBRE, AP_PATERNO, AP_MATERNO, EDAD 
FROM SOCIO, COMPRAR_SOCIO 
WHERE SOCIO.ID_SOCIO = COMPRAR_SOCIO.ID_SOCIO;

-- 2. Clientes y socios que han comprado productos y que tengan edades entre 18 y 35 años 
-- (nombre completo, edad y productos)
SELECT SOCIO.NOMBRE, SOCIO.AP_PATERNO, SOCIO.AP_MATERNO, SOCIO.EDAD, PRODUCTO.NOMBRE_PRODUCTO
FROM SOCIO, COMPRAR_SOCIO, PRODUCTO
WHERE (SOCIO.ID_SOCIO = COMPRAR_SOCIO.ID_SOCIO)  AND
(COMPRAR_SOCIO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO) AND
(SOCIO.EDAD BETWEEN 18 AND 35)
UNION
SELECT CLIENTE.NOMBRE, CLIENTE.AP_PATERNO, CLIENTE.AP_MATERNO, CLIENTE.EDAD, PRODUCTO.NOMBRE_PRODUCTO
FROM CLIENTE, COMPRAR_CLIENTE, PRODUCTO
WHERE (CLIENTE.ID_CLIENTE = COMPRAR_CLIENTE.ID_CLIENTE)  AND
(COMPRAR_CLIENTE.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO) AND
(CLIENTE.EDAD BETWEEN 18 AND 35);

-- 3. Entrenadores cuyo apellido paterno empiece con Z y de clases de Zumba (nombre completo
-- y nombre de clase).
SELECT NOMBRE, AP_PATERNO, AP_MATERNO, EDAD, NOMBRE_CLASE
FROM ENTRENADOR, IMPARTIR_CLASE, CLASE
WHERE (ENTRENADOR.ID_EMPLEADO = IMPARTIR_CLASE.ID_EMPLEADO) AND
(CLASE.ID_CLASE = IMPARTIR_CLASE.ID_CLASE) AND
AP_PATERNO LIKE 'Z%' AND
NOMBRE_CLASE = 'ZUMBA';

-- 4. Instructores que han dado clase de box y que no empiecen con A en su nombre y cuya edad 
-- no sea 25,26 y 27 años (nombre completo, edad y clase impartida)
SELECT NOMBRE, AP_PATERNO, AP_MATERNO, EDAD, NOMBRE_CLASE
FROM ENTRENADOR, IMPARTIR_CLASE, CLASE
WHERE (ENTRENADOR.ID_EMPLEADO = IMPARTIR_CLASE.ID_EMPLEADO) AND
(CLASE.ID_CLASE = IMPARTIR_CLASE.ID_CLASE) AND
NOMBRE NOT LIKE 'A%' AND
EDAD NOT BETWEEN 25 AND 27;

-- 5. Productos que han comprado y que terminan con ‘n’, cuya fecha de compra año sido en agosto 
-- (nombre de producto y fecha de compra)
SELECT PRODUCTO.NOMBRE_PRODUCTO
FROM COMPRAR_SOCIO, PRODUCTO
WHERE (COMPRAR_SOCIO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO) AND
PRODUCTO.NOMBRE_PRODUCTO LIKE '%n'
UNION
SELECT PRODUCTO.NOMBRE_PRODUCTO
FROM COMPRAR_CLIENTE, PRODUCTO
WHERE (COMPRAR_CLIENTE.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO) AND
PRODUCTO.NOMBRE_PRODUCTO LIKE '%n';

-- 6. Entrenadores que no dieron clase de box, ni zumba pero que la edad sea entre 25 o 60 años 
-- y cuyos Socios tengan fecha de ingreso al Gimnasio en el año actual (nombre completo de 
-- entrenador, edad del entrenador, nombre completo de Socios, fecha de ingreso de socio, 
-- clase impartida)
SELECT ENTRENADOR.NOMBRE, ENTRENADOR.AP_PATERNO, ENTRENADOR.AP_MATERNO, ENTRENADOR.EDAD,
SOCIO.NOMBRE, SOCIO.AP_PATERNO, SOCIO.AP_MATERNO, CLASE.NOMBRE_CLASE
FROM ENTRENADOR, SOCIO, TOMAR_CLASE_SOCIO, IMPARTIR_CLASE, CLASE
WHERE (ENTRENADOR.ID_EMPLEADO = IMPARTIR_CLASE.ID_EMPLEADO) AND
(SOCIO.ID_SOCIO = TOMAR_CLASE_SOCIO.ID_SOCIO) AND 
CLASE.NOMBRE_CLASE NOT IN ('BOX', 'ZUMBA') AND 
ENTRENADOR.EDAD BETWEEN 25 AND 60;

-- 7. Utilizando REGEXP_LIKE y REGEXP_REPLACE cambia las vocales por alguna consonante y 
-- cuyos Clientes terminan su nombre con ‘s’ (nombre completo de clientes, clases tomadas).
SELECT REGEXP_REPLACE(NOMBRE, 'a|e|i|o|u', 'C'), NOMBRE_CLASE
FROM CLIENTE, TOMAR_CLASE_CLIENTE, CLASE
WHERE (TOMAR_CLASE_CLIENTE.ID_CLASE = CLASE.ID_CLASE) AND
REGEXP_LIKE(NOMBRE, '*s$');

-- 8. Muestra los registros de clientes o socios que han comprado algún producto pero cuya cuenta
-- de vocales sea mayor a 3 en su nombre (nombre completo de cliente o socio, prodcuto de compra 
-- y conteo de vocales en su nombre).
SELECT SOCIO.NOMBRE, SOCIO.AP_PATERNO, SOCIO.AP_MATERNO, REGEXP_COUNT(SOCIO.NOMBRE, 'a|e|i|o|u') AS VOCALES, PRODUCTO.NOMBRE_PRODUCTO
FROM SOCIO, COMPRAR_SOCIO, PRODUCTO
WHERE SOCIO.ID_SOCIO = COMPRAR_SOCIO.ID_SOCIO AND
REGEXP_COUNT(SOCIO.NOMBRE, 'a|e|i|o|u') > 3
UNION
SELECT CLIENTE.NOMBRE, CLIENTE.AP_PATERNO, CLIENTE.AP_MATERNO, REGEXP_COUNT(CLIENTE.NOMBRE, 'a|e|i|o|u') AS VOCALES, PRODUCTO.NOMBRE_PRODUCTO
FROM CLIENTE, COMPRAR_CLIENTE, PRODUCTO
WHERE CLIENTE.ID_CLIENTE = COMPRAR_CLIENTE.ID_CLIENTE AND
REGEXP_COUNT(CLIENTE.NOMBRE, 'a|e|i|o|u') > 3;

-- 9. Año de ingreso de todos las personas (clientes y socios) cuyo nombre termine en ‘r’ y el año 
-- de ingreso sea desde el 2001 (nombre completo, año de ingreso y fecha de ingreso).
SELECT NOMBRE, AP_PATERNO, AP_MATERNO, EXTRACT(YEAR FROM FECHA_INGRESO) AS YEAR, FECHA_INGRESO
FROM CLIENTE
WHERE REGEXP_LIKE(NOMBRE, '*r$') AND 
EXTRACT(YEAR FROM FECHA_INGRESO) >= 2001;

-- 10. Añade 3 meses más a la fecha de compra de algún producto para todos aquellos que contengan en 
-- su nombre una p y las clases impartidas sean de yoga y box (nombre completo, fecha de compra, 
-- producto comprado, clase tomada).
SELECT NOMBRE, AP_PATERNO, AP_MATERNO, ADD_MONTHS(FECHA_COMPRA, 3), NOMBRE_PRODUCTO, NOMBRE_CLASE
FROM CLIENTE, COMPRAR_CLIENTE, TOMAR_CLASE_CLIENTE
WHERE CLIENTE.ID_CLIENTE = COMPRAR_CLIENTE.ID_CLIENTE AND
CLIENTE.ID_CLIENTE = TOMAR_CLASE_CLIENTE.ID_CLIENTE AND 
NOMBRE_CLASE IN ('Yoga', 'Box') AND
REGEXP_LIKE(NOMBRE, '*p*');