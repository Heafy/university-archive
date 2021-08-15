-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: cafeteria
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `afiliados`
--

DROP TABLE IF EXISTS `afiliados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `afiliados` (
  `nombre` varchar(25) DEFAULT NULL,
  `paterno` varchar(25) DEFAULT NULL,
  `materno` varchar(25) DEFAULT NULL,
  `ID` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afiliados`
--

LOCK TABLES `afiliados` WRITE;
/*!40000 ALTER TABLE `afiliados` DISABLE KEYS */;
INSERT INTO `afiliados` VALUES ('Jorge','Martinez','Flores','MAFJ2662016','MAFJ2662016'),('Rodrigo','Sanchez','Morales','SAMR2662016','SAMR2662016');
/*!40000 ALTER TABLE `afiliados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bebidasCalientes`
--

DROP TABLE IF EXISTS `bebidasCalientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bebidasCalientes` (
  `nombre` varchar(25) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebidasCalientes`
--

LOCK TABLES `bebidasCalientes` WRITE;
/*!40000 ALTER TABLE `bebidasCalientes` DISABLE KEYS */;
INSERT INTO `bebidasCalientes` VALUES ('Americano',15,50,'bebidasCalientes/americanCoffee.jpeg'),('Capuccino',25,50,'bebidasCalientes/capuccino.jpg'),('Chai Latte',30,50,'bebidasCalientes/chaiLatte.jpg'),('Chocolate Caliente',25,50,'bebidasCalientes/chocolate.jpg'),('Espresso',15,50,'bebidasCalientes/espresso.jpg'),('Te',15,50,'bebidasCalientes/tea.jpg');
/*!40000 ALTER TABLE `bebidasCalientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bebidasFrias`
--

DROP TABLE IF EXISTS `bebidasFrias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bebidasFrias` (
  `nombre` varchar(25) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebidasFrias`
--

LOCK TABLES `bebidasFrias` WRITE;
/*!40000 ALTER TABLE `bebidasFrias` DISABLE KEYS */;
INSERT INTO `bebidasFrias` VALUES ('Capuccino',35,50,'bebidasFrias/capuccino.jpg'),('Frappe',40,50,'bebidasFrias/frappe.jpg'),('Smoothie',40,50,'bebidasFrias/smoothies.jpg'),('Soda Italiana',35,50,'bebidasFrias/sodaItaliana.jpg');
/*!40000 ALTER TABLE `bebidasFrias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crepas`
--

DROP TABLE IF EXISTS `crepas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crepas` (
  `nombre` varchar(25) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crepas`
--

LOCK TABLES `crepas` WRITE;
/*!40000 ALTER TABLE `crepas` DISABLE KEYS */;
INSERT INTO `crepas` VALUES ('Crepa de Jamon',40,50,'crepas/crepaJamon.jpg'),('Crepa de Pollo',45,50,'crepas/crepaPollo.jpg'),('Crepas Dulces',40,50,'crepas/crepaDulce.jpg');
/*!40000 ALTER TABLE `crepas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `nombre` varchar(25) DEFAULT NULL,
  `paterno` varchar(25) DEFAULT NULL,
  `materno` varchar(25) DEFAULT NULL,
  `ID` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('Admin','Admin','Admin','admin','adminPass'),('Luis','Rincon','Morales','RIML2662016','RIML2662016'),('Alejandro','Bravo','Mojica','BRMA2662016','BRMA2662016');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasteles`
--

DROP TABLE IF EXISTS `pasteles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasteles` (
  `nombre` varchar(25) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasteles`
--

LOCK TABLES `pasteles` WRITE;
/*!40000 ALTER TABLE `pasteles` DISABLE KEYS */;
INSERT INTO `pasteles` VALUES ('Chocoflan',25,50,'pasteles/chocoflan.jpg'),('Muffin',20,50,'pasteles/muffin.jpg'),('Pay de Limon',25,50,'pasteles/payLimon.jpg');
/*!40000 ALTER TABLE `pasteles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refrescos`
--

DROP TABLE IF EXISTS `refrescos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refrescos` (
  `nombre` varchar(25) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refrescos`
--

LOCK TABLES `refrescos` WRITE;
/*!40000 ALTER TABLE `refrescos` DISABLE KEYS */;
INSERT INTO `refrescos` VALUES ('Agua Natural',15,50,'refrescos/agua.jpg'),('Nestea',15,50,'refrescos/nestea.jpg'),('Refresco en Lata',15,50,'refrescos/refrescoLata.jpg'),('Refresco 600 ml',15,50,'refrescos/refrescos600.jpg');
/*!40000 ALTER TABLE `refrescos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sandwiches`
--

DROP TABLE IF EXISTS `sandwiches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sandwiches` (
  `nombre` varchar(25) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sandwiches`
--

LOCK TABLES `sandwiches` WRITE;
/*!40000 ALTER TABLE `sandwiches` DISABLE KEYS */;
INSERT INTO `sandwiches` VALUES ('Club Sandwich',30,50,'sandwich/clubSandwich.jpg'),('Ejecutivo',20,50,'sandwich/ejecutivo.png'),('Escolar',15,50,'sandwich/escolar.jpg');
/*!40000 ALTER TABLE `sandwiches` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-26  2:15:56
