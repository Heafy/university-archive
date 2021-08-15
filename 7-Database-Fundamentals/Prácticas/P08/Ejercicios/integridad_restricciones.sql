/* RESTRICCIONES DEL GIMNASIO HERCULES*/

-- RESTRICCIONES TABLA PERSONA
ALTER TABLE PERSONA ADD CONSTRAINT PK_PERSONA PRIMARY KEY (ID_PERSONA);
ALTER TABLE PERSONA ADD CONSTRAINT CK_PERSONA_NOMBRE CHECK (NOMBRE IS NOT NULL);
ALTER TABLE PERSONA ADD CONSTRAINT CK_PERSONA_SEXO CHECK(SEXO IN ('MASCULINO', 'FEMENINO'));

-- RESTRICCIONES TABLA SOCIO
ALTER TABLE SOCIO ADD CONSTRAINT PK_SOCIO PRIMARY KEY (ID_SOCIO);
ALTER TABLE SOCIO ADD CONSTRAINT CK_SOCIO_NOMBRE CHECK (NOMBRE IS NOT NULL);
ALTER TABLE SOCIO ADD CONSTRAINT CK_SOCIO_SEXO CHECK(SEXO IN ('MASCULINO', 'FEMENINO'));
ALTER TABLE SOCIO ADD CONSTRAINT FK_SOCIO_ID FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(ID_PERSONA) ON DELETE CASCADE;

-- RESTRICCIONES TABLA PERSONA_CONTACTO
ALTER TABLE PERSONA_CONTACTO ADD CONSTRAINT PK_PERSONA_CONTACTO PRIMARY KEY (NOMBRE);

-- RESTRICCIONES TABLA CLIENTE
ALTER TABLE CLIENTE ADD CONSTRAINT PK_CLIENTE PRIMARY KEY (ID_CLIENTE);
ALTER TABLE CLIENTE ADD CONSTRAINT CK_CLIENTE_NOMBRE CHECK (NOMBRE IS NOT NULL);
ALTER TABLE CLIENTE ADD CONSTRAINT CK_CLIENTE_SEXO CHECK(SEXO IN ('MASCULINO', 'FEMENINO'));
ALTER TABLE CLIENTE ADD CONSTRAINT FK_CLIENTE_ID FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(ID_PERSONA) ON DELETE CASCADE;

-- RESTRICCIONES TABLA DIRECCION
ALTER TABLE DIRECCION ADD CONSTRAINT PK_DIRECCION PRIMARY KEY (ID_DIRECCION);
ALTER TABLE DIRECCION ADD CONSTRAINT CK_CP CHECK(CP >= 99999);

-- RESTRICCIONES TABLA ENTRENADOR
ALTER TABLE ENTRENADOR ADD CONSTRAINT PK_ENTRENADOR PRIMARY KEY (ID_EMPLEADO);
ALTER TABLE ENTRENADOR ADD CONSTRAINT CK_ENTRENADOR_NOMBRE CHECK (NOMBRE IS NOT NULL);
ALTER TABLE ENTRENADOR ADD CONSTRAINT CK_ENTRENADOR_SEXO CHECK(SEXO IN ('MASCULINO', 'FEMENINO'));
ALTER TABLE ENTRENADOR ADD CONSTRAINT FK_ENTRENADOR_ID FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(ID_PERSONA) ON DELETE CASCADE;
ALTER TABLE ENTRENADOR ADD CONSTRAINT FK_ENTRENADOR_DIRECICON FOREIGN KEY (ID_DIRECCION) REFERENCES DIRECCION(ID_DIRECCION);

-- RESTRICCIONES TABLA MEMBRESIA
ALTER TABLE MEMBRESIA ADD CONSTRAINT PK_MEMBRESIA PRIMARY KEY (ID_MEMBRESIA);
ALTER TABLE MEMBRESIA ADD CONSTRAINT FK_MEMBRESIA_SOCIO FOREIGN KEY (ID_SOCIO) REFERENCES SOCIO(ID_SOCIO) ON DELETE CASCADE;

-- RESTRICCIONES TABLA MEMBRESIA_BASICA
ALTER TABLE MEMBRESIA_BASICA ADD CONSTRAINT PK_MEMBRESIA_BASICA PRIMARY KEY (ID_MEMBRESIA);
ALTER TABLE MEMBRESIA_BASICA ADD CONSTRAINT FK_MEMBRESIA_BASICA FOREIGN KEY (ID_MEMBRESIA) REFERENCES MEMBRESIA(ID_MEMBRESIA) ON DELETE CASCADE;

-- RESTRICCIONES TABLA MEMBRESIA_PLUS
ALTER TABLE MEMBRESIA_PLUS ADD CONSTRAINT PK_MEMBRESIA_PLUS PRIMARY KEY (ID_MEMBRESIA);
ALTER TABLE MEMBRESIA_PLUS ADD CONSTRAINT FK_MEMBRESIA_PLUS FOREIGN KEY (ID_MEMBRESIA) REFERENCES MEMBRESIA(ID_MEMBRESIA) ON DELETE CASCADE;

-- RESTRICCIONES TABLA MEMBRESIA_PREMIUM
ALTER TABLE MEMBRESIA_PREMIUM ADD CONSTRAINT PK_MEMBRESIA_PREMIUM PRIMARY KEY (ID_MEMBRESIA);
ALTER TABLE MEMBRESIA_PREMIUM ADD CONSTRAINT FK_MEMBRESIA_PREMIUM FOREIGN KEY (ID_MEMBRESIA) REFERENCES MEMBRESIA(ID_MEMBRESIA) ON DELETE CASCADE;

-- RESTRICCIONES TABLA CLASE
ALTER TABLE CLASE ADD CONSTRAINT PK_CLASE PRIMARY KEY (NOMBRE_CLASE);

-- RESTRICCIONES TABLA PRODUCTO
ALTER TABLE PRODUCTO ADD CONSTRAINT PK_PRODUCTO PRIMARY KEY (NOMBRE_PRODUCTO); 

-- RESTRICCIONES TABLA TENER
ALTER TABLE TENER ADD CONSTRAINT PK_TENER PRIMARY KEY (ID_MEMBRESIA, ID_SOCIO);
ALTER TABLE TENER ADD CONSTRAINT FK_TENER_MREMBRESIA FOREIGN KEY (ID_MEMBRESIA) REFERENCES MEMBRESIA(ID_MEMBRESIA) ON DELETE CASCADE;
ALTER TABLE TENER ADD CONSTRAINT FK_TENER_SOCIO FOREIGN KEY (ID_SOCIO) REFERENCES SOCIO(ID_SOCIO) ON DELETE CASCADE;

-- RESTRICCIONES TABLA TOMAR_CLASE_SOCIO
ALTER TABLE TOMAR_CLASE_SOCIO ADD CONSTRAINT PK_TOMARCLASESOCIO PRIMARY KEY (NOMBRE_CLASE, ENTRENADOR, ID_SOCIO);
ALTER TABLE TOMAR_CLASE_SOCIO ADD CONSTRAINT FK_TOMARCLASESOCIO_NOMCLASE FOREIGN KEY (NOMBRE_CLASE) REFERENCES CLASE(NOMBRE_CLASE) ON DELETE CASCADE;
ALTER TABLE TOMAR_CLASE_SOCIO ADD CONSTRAINT FK_TOMARCLASESOCIO_IDSOCIO FOREIGN KEY (ID_SOCIO) REFERENCES SOCIO(ID_SOCIO) ON DELETE CASCADE;

-- RESTRICCIONES TABLA TOMAR_CLASE_CLIENTE
ALTER TABLE TOMAR_CLASE_CLIENTE ADD CONSTRAINT PK_TOMARCLASECLIENTE PRIMARY KEY (NOMBRE_CLASE, ENTRENADOR, ID_CLIENTE);
ALTER TABLE TOMAR_CLASE_CLIENTE ADD CONSTRAINT FK_TOMARCLASECLIENTE_NOMCLASE FOREIGN KEY (NOMBRE_CLASE) REFERENCES CLASE(NOMBRE_CLASE) ON DELETE CASCADE;
ALTER TABLE TOMAR_CLASE_CLIENTE ADD CONSTRAINT FK_TOMARCLASECLIENTE_IDCLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE) ON DELETE CASCADE;

-- RESTRICCIONES TABLA COMPRAR_SOCIO
ALTER TABLE COMPRAR_SOCIO ADD CONSTRAINT PK_COMPRARSOCIO PRIMARY KEY (ID_SOCIO, NOMBRE_PRODUCTO, PRESENTACION);
ALTER TABLE COMPRAR_SOCIO ADD CONSTRAINT FK_COMPRARSOCIO_SOCIO FOREIGN KEY (ID_SOCIO) REFERENCES SOCIO(ID_SOCIO) ON DELETE CASCADE;
ALTER TABLE COMPRAR_SOCIO ADD CONSTRAINT FK_COMPRARSOCIO_NOMPRODUCTO FOREIGN KEY (NOMBRE_PRODUCTO) REFERENCES PRODUCTO(NOMBRE_PRODUCTO) ON DELETE CASCADE;

-- RESTRICCIONES TABLA COMPRAR_CLIENTE
ALTER TABLE COMPRAR_CLIENTE ADD CONSTRAINT PK_COMPRARCLIENTE PRIMARY KEY (ID_CLIENTE, NOMBRE_PRODUCTO, PRESENTACION);
ALTER TABLE COMPRAR_CLIENTE ADD CONSTRAINT FK_COMPRARCLIENTE_SOCIO FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE) ON DELETE CASCADE;
ALTER TABLE COMPRAR_CLIENTE ADD CONSTRAINT FK_COMPRARCLIENTE_NOMPRODUCTO FOREIGN KEY (NOMBRE_PRODUCTO) REFERENCES PRODUCTO(NOMBRE_PRODUCTO) ON DELETE CASCADE;

-- RESTRICCIONES TABLA IMPARTIR_CLASE
ALTER TABLE IMPARTIR_CLASE ADD CONSTRAINT PK_IMPARTIR_CLASE PRIMARY KEY (NOMBRE_CLASE, ENTRENADOR, ID_EMPLEADO);
ALTER TABLE IMPARTIR_CLASE ADD CONSTRAINT FK_IMPARTIR_CLASE_NOMBRECLASE FOREIGN KEY (NOMBRE_CLASE) REFERENCES CLASE(NOMBRE_CLASE) ON DELETE CASCADE;
ALTER TABLE IMPARTIR_CLASE ADD CONSTRAINT FK_IMPARTIR_CLASE_IDEMPLEADO FOREIGN KEY (ID_EMPLEADO) REFERENCES ENTRENADOR(ID_EMPLEADO) ON DELETE CASCADE;
