-- MySQL dump 10.16  Distrib 10.2.14-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: edemko
-- ------------------------------------------------------
-- Server version	10.2.14-MariaDB

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
-- Table structure for table `Costs`
--

DROP TABLE IF EXISTS `Costs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Costs` (
  `CostID` int(11) NOT NULL AUTO_INCREMENT,
  `CostName` varchar(100) NOT NULL,
  `CostQuantity` int(11) DEFAULT 1,
  `CostShipping` float(5,2) NOT NULL,
  `PurchaseDate` date DEFAULT NULL,
  `Comment` varchar(256) NOT NULL,
  `UnitPrice` float(6,2) NOT NULL,
  PRIMARY KEY (`CostID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Costs`
--

LOCK TABLES `Costs` WRITE;
/*!40000 ALTER TABLE `Costs` DISABLE KEYS */;
INSERT INTO `Costs` VALUES (1,'E3D Nozzle 0.25mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00),(2,'E3D Nozzle 0.15mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00),(3,'E3D Nozzle 0.6mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00);
/*!40000 ALTER TABLE `Costs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customers`
--

DROP TABLE IF EXISTS `Customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customers` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `DateCreated` date NOT NULL,
  `Comment` varchar(250) NOT NULL,
  `Phone` varchar(30) DEFAULT NULL,
  `Address` varchar(50) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Mail` varchar(50) DEFAULT NULL,
  `ZipCode` varchar(20) NOT NULL,
  `State` varchar(50) DEFAULT 'Slovakia',
  `Company` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=5045 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customers`
--

LOCK TABLES `Customers` WRITE;
/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (5001,'Jana','Kusendova','2017-01-02','','','Komenskeho 44','Kosice','','040 01','Slovensko',''),(5002,'Tomas','Pelak','2017-01-02','','','','Cana','','044 14','Slovensko',''),(5003,'Ondrej','Kuchta','2017-01-02','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5004,'Eva','Dittelova','2017-01-02','','','Komenskeho 44','Kosice','','040 01','Slovensko',''),(5005,'Stefan','Varga','2017-01-02','','','Potocna 162/2','Zdana','','044 11','Slovensko',''),(5006,'Ruzenka','Dorova','2017-01-02','','','','Nizna Mysla','','044 15','Slovensko',''),(5007,'Stanislav','Kopko','2017-01-02','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5008,'Veronika','Suskova','2017-07-16','','','Rieka 2476','Cadca','','022 01','Slovensko',''),(5009,'Janka','Mitrova','2017-01-02','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5010,'Maros','Forrai','2017-01-02','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5011,'Vianocne','Trhy','2017-01-02','Kulturny dom','','Jarmocná 118/4','Zdana','','044 11','Slovensko',''),(5012,'David','Tkac','2017-01-02','','','Siroka 315','Zdana','','044 11','Slovensko',''),(5013,'Tadeas','Jambor','2017-01-02','','','Okruzna 106','Zdana','','044 11','Slovensko',''),(5014,'Tomas','Mocarsky','2017-01-09','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5015,'Miroslava','Medvecka','2017-01-19','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5016,'Michal','Sykora','2017-01-30','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5017,'Matus','Trpak','2017-03-13','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5018,'Martin','Brestovic','2017-03-22','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5019,'Jan','Popelas','2017-04-06','','','','Cana','','044 14','Slovensko',''),(5020,'Ivan','Adamkovic','2017-04-06','','','','','','','Slovensko',''),(5021,'David','Ondrus','2017-04-18','','','Toryska 5','Kosice','','040 01','Slovensko',''),(5022,'Monika','Fabianova','2017-05-04','','','Komenskeho 44','Kosice','','040 01','Slovensko',''),(5023,'Jakub','Hanula','2017-05-04','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5024,'Tomas','Sromovsky','2017-05-19','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5025,'Stefan','Girman','2017-05-28','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5026,'Marcela','Stredanska','2017-07-04','','','P.O. Hviezdoslava 2328','Topolcany','','955 01','Slovensko',''),(5027,'VW Zraz','VW Zraz','2017-07-16','','','Kemp Tatranec','Vysoké Tatry','','059 60','Slovensko',''),(5028,'Richard','Spalek','2017-07-19','','','Podzahradna 49','Bratislava','','821 06','Slovensko',''),(5029,'Marian','Hodza','2017-07-19','','','Prazska 35','Bratislava','','811 04','Slovensko',''),(5030,'Robert','Svec','2017-07-19','','','Vinicky 279/11','Male Uherce','','958 03','Slovensko',''),(5031,'Marek','Cibula','2017-07-19','','','Cislo domu 865','Stiavnicke Bane','','969 81','Slovensko',''),(5032,'Marcel','Boldizar','2017-07-19','','','Zriedlova 13','Kosice','','040 01','Slovensko',''),(5033,'Martin','Weber ml.','2017-07-19','','','Sklenarova 28','Bratislava 2','','821 09','Slovensko',''),(5034,'Tomas','Michna','2017-07-19','','','Za Vodou 1361/2','Stara Lubovna','','064 01','Slovensko',''),(5035,'Marcel','Mihok','2017-07-19','','','Hronska 421/65','Valaska','','976 46','Slovensko',''),(5036,'Angel Sanchez','Parreno','2017-07-16','','','C/Los Robles A3','Guardo','','348 80','Spain',''),(5037,'Lubos','Cura','2017-07-23','','','Babie 56','Hanusovce nad Toplou','','094 31','Slovensko',''),(5038,'Michal','Mocol','2017-07-23','','','Kamenna 538','Strecno','','013 24','Slovensko',''),(5039,'Matus','Hohol','2017-09-03','','','Svitska Cesta 5','Poprad','','058 01','Slovensko',''),(5040,'Lukas','Borbely','2017-09-03','','','','Saca','','040 15','Slovensko',''),(5041,'Tomas','Gasiak','2017-09-09','','','Cislo domu 217','Sneznica','','023 32','Slovensko',''),(5042,'Jakub','Jakubech','2017-09-09','','','Cislo domu 276','Pruzina','','018 22','Slovensko',''),(5043,'Miroslav','Tomasko','2017-10-29','','','Potocna 160','Zdana','','044 11','Slovensko',''),(5044,'Viktor','Vysoky','2017-11-27','','','Idanska 31','Kosice','','040 01','Slovensko','');
/*!40000 ALTER TABLE `Customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Materials`
--

DROP TABLE IF EXISTS `Materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Materials` (
  `MaterialID` int(11) NOT NULL AUTO_INCREMENT,
  `Manufacturer` varchar(50) DEFAULT 'Mladec',
  `Type` varchar(15) DEFAULT 'PLA',
  `Color` varchar(30) DEFAULT NULL,
  `MaterialWeight` int(11) DEFAULT 1000,
  `MaterialPrice` float(5,2) DEFAULT NULL,
  `MaterialShipping` float(4,2) DEFAULT 0.00,
  `PurchaseDate` date DEFAULT NULL,
  `Seller` varchar(100) DEFAULT NULL,
  `Finished` varchar(10) DEFAULT 'No',
  `Trash` float(2,1) NOT NULL DEFAULT 0.0,
  `MaterialDiameter` float(3,2) DEFAULT 1.75,
  PRIMARY KEY (`MaterialID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materials`
--

LOCK TABLES `Materials` WRITE;
/*!40000 ALTER TABLE `Materials` DISABLE KEYS */;
INSERT INTO `Materials` VALUES (1,'Prusa','PLA','Lime Green',1000,0.00,0.00,'2016-09-13','Prusa Research','Yes',0.0,1.75),(2,'Mladec','PLA','Black',1000,15.90,2.75,'2016-10-21','3DeS s.r.o.','Yes',0.0,1.75),(3,'Mladec','PLA','White',1000,15.90,2.75,'2016-10-21','3DeS s.r.o.','Yes',0.0,1.75),(4,'Fillamentum','PLA','Luminious Red',750,15.90,1.20,'2016-11-27','3DeS s.r.o.','No',0.0,1.75),(5,'Fillamentum','PLA','Luminious Orange',750,15.90,1.20,'2016-11-27','3DeS s.r.o.','No',0.0,1.75),(6,'Mladec','PLA','Black',1000,16.90,1.25,'2016-12-08','3DeS s.r.o.','Yes',0.0,1.75),(7,'Mladec','PLA','White',1000,15.90,1.25,'2016-12-08','3DeS s.r.o.','Yes',0.0,1.75),(8,'Mladec','ABS','Black',1000,19.00,0.00,'2017-03-20','3DeS s.r.o.','No',0.0,1.75),(9,'Mladec','PLA','White',1000,19.90,0.00,'2017-03-20','3DeS s.r.o.','Yes',0.0,1.75),(10,'Mladec','PLA','Red',1000,19.90,0.00,'2017-03-20','3DeS s.r.o.','No',0.0,1.75),(11,'Mladec','PLA','Black',1000,19.90,0.00,'2017-03-20','3DeS s.r.o.','Yes',0.0,1.75),(12,'Mladec','PLA','Blue',1000,19.90,2.50,'2017-05-02','3DeS s.r.o.','No',0.0,1.75),(13,'Mladec','PLA','Orange',1000,19.90,2.50,'2017-05-02','3DeS s.r.o.','No',0.0,1.75),(14,'Mladec','PLA','Gold',1000,20.00,1.20,'2017-06-06','Kreatiff s.r.o.','No',0.0,1.75),(15,'Devil Design','PLA','Lime Green',1000,20.00,1.20,'2017-06-06','Kreatiff s.r.o.','No',0.0,1.75),(16,'Mladec','PLA','Silver',1000,20.00,1.20,'2017-06-06','Kreatiff s.r.o.','No',0.0,1.75),(17,'Devil Design','PLA','Navy Blue',1000,20.00,1.20,'2017-07-19','Kreatiff s.r.o.','No',0.0,1.75),(18,'Devil Design','PLA','Green',1000,20.00,1.20,'2017-07-19','Kreatiff s.r.o.','No',0.0,1.75),(19,'Mladec','PLA','Black',1000,20.00,1.20,'2017-07-19','Kreatiff s.r.o.','No',0.0,1.75),(20,'Devil Design','PLA','Raspberry Red',1000,20.00,3.60,'2017-07-29','Kreatiff s.r.o.','No',0.0,1.75),(21,'Devil Design','PLA','Hot Red',1000,20.00,1.80,'2017-10-12','Kreatiff s.r.o.','No',0.0,1.75),(22,'Mladec','PLA','White',1000,19.90,1.80,'2017-10-12','Kreatiff s.r.o.','No',0.0,1.75),(23,'Devil Design','PLA','White',1000,34.49,1.80,'2017-10-12','Kreatiff s.r.o.','No',0.0,1.75),(24,'Devil Design','PLA','Black',1000,34.49,1.80,'2017-10-12','Kreatiff s.r.o.','No',0.0,1.75);
/*!40000 ALTER TABLE `Materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Objects`
--

DROP TABLE IF EXISTS `Objects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Objects` (
  `ObjectID` int(11) NOT NULL AUTO_INCREMENT,
  `ObjectName` varchar(100) DEFAULT NULL,
  `ObjectWeight` float(5,2) DEFAULT NULL,
  `SupportWeight` float(5,2) DEFAULT 0.00,
  `ProjectID` int(11) NOT NULL DEFAULT 0,
  `BuildTime` int(11) NOT NULL,
  PRIMARY KEY (`ObjectID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `Objects_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `Projects` (`ProjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objects`
--

LOCK TABLES `Objects` WRITE;
/*!40000 ALTER TABLE `Objects` DISABLE KEYS */;
INSERT INTO `Objects` VALUES (1,'3D Printing',0.00,0.00,1,0),(2,'Eiffel Tower',38.00,0.00,1,417),(3,'Spiral Vase 2',85.00,0.00,2,441);
/*!40000 ALTER TABLE `Objects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderItems`
--

DROP TABLE IF EXISTS `OrderItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderItems` (
  `OrderItemID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NOT NULL,
  `ObjectID` int(11) NOT NULL,
  `ItemMaterialID` int(11) NOT NULL,
  `ItemWeight` float(5,2) NOT NULL,
  `ItemSupportWeight` float(5,2) DEFAULT 0.00,
  `ItemBuildTime` int(11) NOT NULL,
  `ItemPrice` float(5,2) NOT NULL,
  `ItemQuantity` int(11) NOT NULL,
  PRIMARY KEY (`OrderItemID`),
  KEY `OrderID` (`OrderID`),
  KEY `ObjectID` (`ObjectID`),
  KEY `ItemMaterialID` (`ItemMaterialID`),
  CONSTRAINT `OrderItems_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`),
  CONSTRAINT `OrderItems_ibfk_2` FOREIGN KEY (`ObjectID`) REFERENCES `Objects` (`ObjectID`),
  CONSTRAINT `OrderItems_ibfk_3` FOREIGN KEY (`ItemMaterialID`) REFERENCES `Materials` (`MaterialID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderItems`
--

LOCK TABLES `OrderItems` WRITE;
/*!40000 ALTER TABLE `OrderItems` DISABLE KEYS */;
INSERT INTO `OrderItems` VALUES (1,100001,2,2,38.00,0.00,417,5.00,0),(2,100001,3,3,85.00,0.00,441,5.00,0);
/*!40000 ALTER TABLE `OrderItems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderStatus`
--

DROP TABLE IF EXISTS `OrderStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderStatus` (
  `OrderStatus` varchar(50) NOT NULL,
  PRIMARY KEY (`OrderStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderStatus`
--

LOCK TABLES `OrderStatus` WRITE;
/*!40000 ALTER TABLE `OrderStatus` DISABLE KEYS */;
INSERT INTO `OrderStatus` VALUES ('Canceled'),('Not Sold'),('Sold');
/*!40000 ALTER TABLE `OrderStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) NOT NULL,
  `OrderPrice` float(5,2) NOT NULL,
  `OrderQuantity` int(11) NOT NULL,
  `DateCreated` date DEFAULT NULL,
  `OrderStatus` varchar(50) NOT NULL,
  `DueDate` date DEFAULT NULL,
  `Comment` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `OrderStatus` (`OrderStatus`),
  CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`),
  CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`OrderStatus`) REFERENCES `OrderStatus` (`OrderStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (100001,5001,10.00,2,'2016-12-02','Sold','2016-12-02','');
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Projects`
--

DROP TABLE IF EXISTS `Projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projects` (
  `ProjectID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ProjectID`),
  UNIQUE KEY `ProjectName` (`ProjectName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projects`
--

LOCK TABLES `Projects` WRITE;
/*!40000 ALTER TABLE `Projects` DISABLE KEYS */;
INSERT INTO `Projects` VALUES (5,'1-slot Card Case 1.0'),(6,'2-slot Card Case 3.0'),(4,'Card Roller 2.0'),(17,'Chinese Riddle'),(13,'Cigarette Case'),(16,'Cleveland Indians'),(1,'General'),(15,'Japanese Customs'),(11,'Key Hanger'),(12,'Keychains'),(14,'La Bella Art'),(8,'Odroid C2 Cases'),(7,'Raspberry Cases'),(3,'Snowflakes'),(2,'Spiral Vases'),(9,'Tesla Supercharger'),(10,'Torsen Differential');
/*!40000 ALTER TABLE `Projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-27 19:58:29
