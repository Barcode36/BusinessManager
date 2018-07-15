-- MySQL dump 10.16  Distrib 10.3.7-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: edemko
-- ------------------------------------------------------
-- Server version	10.3.7-MariaDB

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
-- Table structure for table `Companies`
--

DROP TABLE IF EXISTS `Companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Companies` (
  `CompanyID` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(150) NOT NULL,
  PRIMARY KEY (`CompanyID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Companies`
--

LOCK TABLES `Companies` WRITE;
/*!40000 ALTER TABLE `Companies` DISABLE KEYS */;
INSERT INTO `Companies` VALUES (1,'None'),(2,'T-Systems Slovakia s. r. o. Kosice');
/*!40000 ALTER TABLE `Companies` ENABLE KEYS */;
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
  `CountryID` int(11) NOT NULL DEFAULT 196,
  `CompanyID` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`CustomerID`),
  KEY `CountryID` (`CountryID`),
  KEY `CompanyID` (`CompanyID`),
  CONSTRAINT `Customers_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `Countries` (`CountryId`),
  CONSTRAINT `Customers_ibfk_2` FOREIGN KEY (`CompanyID`) REFERENCES `Companies` (`CompanyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5045 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customers`
--

LOCK TABLES `Customers` WRITE;
/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (5001,'Jana','Kusendova','2017-01-02','','','Komenskeho 44','Kosice','','040 01',196,1),(5002,'Tomas','Pelak','2017-01-02','','','','Cana','','044 14',196,1),(5003,'Ondrej','Kuchta','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,2),(5004,'Eva','Dittelova','2017-01-02','','','Komenskeho 44','Kosice','','040 01',196,1),(5005,'Stefan','Varga','2017-01-02','','','Potocna 162/2','Zdana','','044 11',196,2),(5006,'Ruzenka','Dorova','2017-01-02','','','','Nizna Mysla','','044 15',196,1),(5007,'Stanislav','Kopko','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,2),(5008,'Veronika','Suskova','2017-07-16','','','Rieka 2476','Cadca','','022 01',196,1),(5009,'Janka','Mitrova','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,1),(5010,'Maros','Forrai','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,2),(5011,'Vianocne','Trhy','2017-01-02','Kulturny dom','','Jarmocná 118/4','Zdana','','044 11',196,1),(5012,'David','Tkac','2017-01-02','','','Siroka 315','Zdana','','044 11',196,1),(5013,'Tadeas','Jambor','2017-01-02','','','Okruzna 106','Zdana','','044 11',196,1),(5014,'Tomas','Mocarsky','2017-01-09','','','Zriedlova 13','Kosice','','040 01',196,2),(5015,'Miroslava','Medvecka','2017-01-19','','','Zriedlova 13','Kosice','','040 01',196,2),(5016,'Michal','Sykora','2017-01-30','','','Zriedlova 13','Kosice','','040 01',196,2),(5017,'Matus','Trpak','2017-03-13','','','Zriedlova 13','Kosice','','040 01',196,2),(5018,'Martin','Brestovic','2017-03-22','','','Zriedlova 13','Kosice','','040 01',196,2),(5019,'Jan','Popelas','2017-04-06','','','','Cana','','044 14',196,1),(5020,'Ivan','Adamkovic','2017-04-06','','','','','','',196,1),(5021,'David','Ondrus','2017-04-18','','','Toryska 5','Kosice','','040 01',196,1),(5022,'Monika','Fabianova','2017-05-04','','','Komenskeho 44','Kosice','','040 01',196,1),(5023,'Jakub','Hanula','2017-05-04','','','Zriedlova 13','Kosice','','040 01',196,2),(5024,'Tomas','Sromovsky','2017-05-19','','','Zriedlova 13','Kosice','','040 01',196,2),(5025,'Stefan','Girman','2017-05-28','','','Zriedlova 13','Kosice','','040 01',196,2),(5026,'Marcela','Stredanska','2017-07-04','','','P.O. Hviezdoslava 2328','Topolcany','','955 01',196,1),(5027,'VW Zraz','VW Zraz','2017-07-16','','','Kemp Tatranec','Vysoké Tatry','','059 60',196,1),(5028,'Richard','Spalek','2017-07-19','','','Podzahradna 49','Bratislava','','821 06',196,1),(5029,'Marian','Hodza','2017-07-19','','','Prazska 35','Bratislava','','811 04',196,1),(5030,'Robert','Svec','2017-07-19','','','Vinicky 279/11','Male Uherce','','958 03',196,1),(5031,'Marek','Cibula','2017-07-19','','','Cislo domu 865','Stiavnicke Bane','','969 81',196,1),(5032,'Marcel','Boldizar','2017-07-19','','','Zriedlova 13','Kosice','','040 01',196,2),(5033,'Martin','Weber ml.','2017-07-19','','','Sklenarova 28','Bratislava 2','','821 09',196,1),(5034,'Tomas','Michna','2017-07-19','','','Za Vodou 1361/2','Stara Lubovna','','064 01',196,1),(5035,'Marcel','Mihok','2017-07-19','','','Hronska 421/65','Valaska','','976 46',196,1),(5036,'Angel Sanchez','Parreno','2017-07-16','','','C/Los Robles A3','Guardo','','348 80',202,1),(5037,'Lubos','Cura','2017-07-23','','','Babie 56','Hanusovce nad Toplou','','094 31',196,1),(5038,'Michal','Mocol','2017-07-23','','','Kamenna 538','Strecno','','013 24',196,1),(5039,'Matus','Hohol','2017-09-03','','','Svitska Cesta 5','Poprad','','058 01',196,1),(5040,'Lukas','Borbely','2017-09-03','','','','Saca','','040 15',196,1),(5041,'Tomas','Gasiak','2017-09-09','','','Cislo domu 217','Sneznica','','023 32',196,1),(5042,'Jakub','Jakubech','2017-09-09','','','Cislo domu 276','Pruzina','','018 22',196,1),(5043,'Miroslav','Tomasko','2017-10-29','','','Potocna 160','Zdana','','044 11',196,1),(5044,'Viktor','Vysoky','2017-11-27','','','Idanska 31','Kosice','','040 01',196,1);
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
  `ManufacturerID` int(11) NOT NULL,
  `MaterialTypeID` int(11) DEFAULT 1,
  `ColorID` int(11) NOT NULL DEFAULT 2,
  `WeightID` int(11) DEFAULT 3,
  `MaterialPrice` float(5,2) DEFAULT NULL,
  `MaterialShipping` float(4,2) DEFAULT 0.00,
  `PurchaseDate` date DEFAULT NULL,
  `SellerID` int(11) NOT NULL DEFAULT 1,
  `Finished` varchar(10) DEFAULT 'No',
  `Trash` float(2,1) NOT NULL DEFAULT 0.0,
  `DiameterID` int(11) DEFAULT 1,
  `Comment` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`MaterialID`),
  KEY `MaterialTypeID` (`MaterialTypeID`),
  KEY `WeightID` (`WeightID`),
  KEY `DiameterID` (`DiameterID`),
  CONSTRAINT `Materials_ibfk_1` FOREIGN KEY (`MaterialTypeID`) REFERENCES `MaterialTypes` (`MaterialTypeID`),
  CONSTRAINT `Materials_ibfk_2` FOREIGN KEY (`WeightID`) REFERENCES `MaterialWeights` (`WeightID`),
  CONSTRAINT `Materials_ibfk_3` FOREIGN KEY (`DiameterID`) REFERENCES `MaterialDiameters` (`DiameterID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materials`
--

LOCK TABLES `Materials` WRITE;
/*!40000 ALTER TABLE `Materials` DISABLE KEYS */;
INSERT INTO `Materials` VALUES (1,1,1,1,3,0.00,0.00,'2016-09-13',3,'Yes',0.0,1,''),(2,1,1,2,3,15.90,2.75,'2016-10-21',1,'Yes',0.0,1,'Test'),(3,1,1,3,3,15.90,2.75,'2016-10-21',1,'Yes',0.0,1,''),(4,2,1,4,2,15.90,1.20,'2016-11-27',1,'No',0.0,1,''),(5,2,1,5,2,15.90,1.20,'2016-11-27',1,'No',0.0,1,''),(6,1,1,2,3,16.90,1.25,'2016-12-08',1,'Yes',0.0,1,''),(7,1,1,3,3,15.90,1.25,'2016-12-08',1,'Yes',0.0,1,''),(8,1,2,2,3,19.00,0.00,'2017-03-20',1,'No',0.0,1,''),(9,1,1,3,3,19.90,0.00,'2017-03-20',1,'Yes',0.0,1,''),(10,1,1,6,3,19.90,0.00,'2017-03-20',1,'No',0.0,1,''),(11,1,1,2,3,19.90,0.00,'2017-03-20',1,'Yes',0.0,1,''),(12,1,1,7,3,19.90,2.50,'2017-05-02',1,'No',0.0,1,''),(13,1,1,8,3,19.90,2.50,'2017-05-02',1,'No',0.0,1,''),(14,1,1,9,3,20.00,1.20,'2017-06-06',2,'No',0.0,1,''),(15,3,1,1,3,20.00,1.20,'2017-06-06',2,'No',0.0,1,''),(16,1,1,10,3,20.00,1.20,'2017-06-06',2,'No',0.0,1,''),(17,3,1,11,3,20.00,1.20,'2017-07-19',2,'No',0.0,1,''),(18,3,1,12,3,20.00,1.20,'2017-07-19',2,'No',0.0,1,''),(19,1,1,2,3,20.00,1.20,'2017-07-19',2,'No',0.0,1,''),(20,3,1,13,3,20.00,3.60,'2017-07-29',2,'No',0.0,1,''),(21,3,1,14,3,20.00,1.80,'2017-10-12',2,'No',0.0,1,''),(22,1,1,3,3,19.90,1.80,'2017-10-12',2,'No',0.0,1,''),(23,3,1,3,4,34.49,1.80,'2017-10-12',2,'No',0.0,1,''),(24,3,1,2,4,34.49,1.80,'2017-10-12',2,'No',0.0,1,''),(25,2,1,0,2,24.50,1.80,'2018-04-19',1,'Yes',0.0,1,''),(26,2,1,15,2,24.50,1.80,'2018-04-19',1,'No',0.0,1,''),(27,2,1,15,2,24.50,1.80,'2018-04-19',1,'No',0.0,1,'');
/*!40000 ALTER TABLE `Materials` ENABLE KEYS */;
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

--
-- Table structure for table `Printers`
--

DROP TABLE IF EXISTS `Printers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Printers` (
  `PrinterID` int(11) NOT NULL AUTO_INCREMENT,
  `PrinterName` varchar(100) NOT NULL,
  `PrinterPrice` float(5,2) NOT NULL,
  `Comment` varchar(255) DEFAULT NULL,
  `PrinterShipping` float(5,2) NOT NULL,
  `PrinterTypeID` int(11) NOT NULL DEFAULT 1,
  `PurchaseDate` date NOT NULL,
  PRIMARY KEY (`PrinterID`),
  KEY `PrinterTypeID` (`PrinterTypeID`),
  CONSTRAINT `Printers_ibfk_1` FOREIGN KEY (`PrinterTypeID`) REFERENCES `PrinterTypes` (`PrinterTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Printers`
--

LOCK TABLES `Printers` WRITE;
/*!40000 ALTER TABLE `Printers` DISABLE KEYS */;
INSERT INTO `Printers` VALUES (1,'Prusa i3 MK2',699.00,'Original Prusa',8.06,1,'0000-00-00'),(2,'Wanhao Duplicator 7 Plus',408.50,'Original Wanhao D7 Plus',125.98,2,'0000-00-00');
/*!40000 ALTER TABLE `Printers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-15 12:15:53
