-- MySQL dump 10.17  Distrib 10.3.12-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: edemko
-- ------------------------------------------------------
-- Server version	10.3.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CommonCustomerProperties`
--

DROP TABLE IF EXISTS `CommonCustomerProperties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CommonCustomerProperties` (
  `PropertyID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyTypeID` int(11) NOT NULL,
  `PropertyName` varchar(255) NOT NULL,
  PRIMARY KEY (`PropertyID`),
  KEY `PropertyTypeID` (`PropertyTypeID`),
  CONSTRAINT `CommonCustomerProperties_ibfk_1` FOREIGN KEY (`PropertyTypeID`) REFERENCES `CustomerPropertyTypes` (`PropertyTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CommonCustomerProperties`
--

LOCK TABLES `CommonCustomerProperties` WRITE;
/*!40000 ALTER TABLE `CommonCustomerProperties` DISABLE KEYS */;
INSERT INTO `CommonCustomerProperties` VALUES (1,2,'Afghanistan'),(2,2,'Albania'),(3,2,'Algeria'),(4,2,'American Samoa'),(5,2,'Andorra'),(6,2,'Angola'),(7,2,'Anguilla'),(8,2,'Antarctica'),(9,2,'Antigua and Barbuda'),(10,2,'Argentina'),(11,2,'Armenia'),(12,2,'Aruba'),(13,2,'Australia'),(14,2,'Austria'),(15,2,'Azerbaijan'),(16,2,'Bahamas'),(17,2,'Bahrain'),(18,2,'Bangladesh'),(19,2,'Barbados'),(20,2,'Belarus'),(21,2,'Belgium'),(22,2,'Belize'),(23,2,'Benin'),(24,2,'Bermuda'),(25,2,'Bhutan'),(26,2,'Bolivia'),(27,2,'Bosnia and Herzegovina'),(28,2,'Botswana'),(29,2,'Bouvet Island'),(30,2,'Brazil'),(31,2,'British Indian Ocean Territory'),(32,2,'Brunei Darussalam'),(33,2,'Bulgaria'),(34,2,'Burkina Faso'),(35,2,'Burundi'),(36,2,'Cambodia'),(37,2,'Cameroon'),(38,2,'Canada'),(39,2,'Cape Verde'),(40,2,'Cayman Islands'),(41,2,'Central African Republic'),(42,2,'Chad'),(43,2,'Chile'),(44,2,'China'),(45,2,'Christmas Island'),(46,2,'Cocos (Keeling), Islands'),(47,2,'Colombia'),(48,2,'Comoros'),(49,2,'Congo'),(50,2,'Cook Islands'),(51,2,'Costa Rica'),(52,2,'Croatia (Hrvatska),'),(53,2,'Cuba'),(54,2,'Cyprus'),(55,2,'Czech Republic'),(56,2,'Denmark'),(57,2,'Djibouti'),(58,2,'Dominica'),(59,2,'Dominican Republic'),(60,2,'East Timor'),(61,2,'Ecuador'),(62,2,'Egypt'),(63,2,'El Salvador'),(64,2,'Equatorial Guinea'),(65,2,'Eritrea'),(66,2,'Estonia'),(67,2,'Ethiopia'),(68,2,'Falkland Islands (Malvinas),'),(69,2,'Faroe Islands'),(70,2,'Fiji'),(71,2,'Finland'),(72,2,'France'),(73,2,'France, Metropolitan'),(74,2,'French Guiana'),(75,2,'French Polynesia'),(76,2,'French Southern Territories'),(77,2,'Gabon'),(78,2,'Gambia'),(79,2,'Georgia'),(80,2,'Germany'),(81,2,'Ghana'),(82,2,'Gibraltar'),(83,2,'Guernsey'),(84,2,'Greece'),(85,2,'Greenland'),(86,2,'Grenada'),(87,2,'Guadeloupe'),(88,2,'Guam'),(89,2,'Guatemala'),(90,2,'Guinea'),(91,2,'Guinea-Bissau'),(92,2,'Guyana'),(93,2,'Haiti'),(94,2,'Heard and Mc Donald Islands'),(95,2,'Honduras'),(96,2,'Hong Kong'),(97,2,'Hungary'),(98,2,'Iceland'),(99,2,'India'),(100,2,'Isle of Man'),(101,2,'Indonesia'),(102,2,'Iran (Islamic Republic of),'),(103,2,'Iraq'),(104,2,'Ireland'),(105,2,'Israel'),(106,2,'Italy'),(107,2,'Ivory Coast'),(108,2,'Jersey'),(109,2,'Jamaica'),(110,2,'Japan'),(111,2,'Jordan'),(112,2,'Kazakhstan'),(113,2,'Kenya'),(114,2,'Kiribati'),(115,2,'Korea, Democratic People\'s Republic of'),(116,2,'Korea, Republic of'),(117,2,'Kosovo'),(118,2,'Kuwait'),(119,2,'Kyrgyzstan'),(120,2,'Lao People\'s Democratic Republic'),(121,2,'Latvia'),(122,2,'Lebanon'),(123,2,'Lesotho'),(124,2,'Liberia'),(125,2,'Libyan Arab Jamahiriya'),(126,2,'Liechtenstein'),(127,2,'Lithuania'),(128,2,'Luxembourg'),(129,2,'Macau'),(130,2,'Macedonia'),(131,2,'Madagascar'),(132,2,'Malawi'),(133,2,'Malaysia'),(134,2,'Maldives'),(135,2,'Mali'),(136,2,'Malta'),(137,2,'Marshall Islands'),(138,2,'Martinique'),(139,2,'Mauritania'),(140,2,'Mauritius'),(141,2,'Mayotte'),(142,2,'Mexico'),(143,2,'Micronesia, Federated States of'),(144,2,'Moldova, Republic of'),(145,2,'Monaco'),(146,2,'Mongolia'),(147,2,'Montenegro'),(148,2,'Montserrat'),(149,2,'Morocco'),(150,2,'Mozambique'),(151,2,'Myanmar'),(152,2,'Namibia'),(153,2,'Nauru'),(154,2,'Nepal'),(155,2,'Netherlands'),(156,2,'Netherlands Antilles'),(157,2,'New Caledonia'),(158,2,'New Zealand'),(159,2,'Nicaragua'),(160,2,'Niger'),(161,2,'Nigeria'),(162,2,'Niue'),(163,2,'Norfolk Island'),(164,2,'Northern Mariana Islands'),(165,2,'Norway'),(166,2,'Oman'),(167,2,'Pakistan'),(168,2,'Palau'),(169,2,'Palestine'),(170,2,'Panama'),(171,2,'Papua New Guinea'),(172,2,'Paraguay'),(173,2,'Peru'),(174,2,'Philippines'),(175,2,'Pitcairn'),(176,2,'Poland'),(177,2,'Portugal'),(178,2,'Puerto Rico'),(179,2,'Qatar'),(180,2,'Reunion'),(181,2,'Romania'),(182,2,'Russian Federation'),(183,2,'Rwanda'),(184,2,'Saint Kitts and Nevis'),(185,2,'Saint Lucia'),(186,2,'Saint Vincent and the Grenadines'),(187,2,'Samoa'),(188,2,'San Marino'),(189,2,'Sao Tome and Principe'),(190,2,'Saudi Arabia'),(191,2,'Senegal'),(192,2,'Serbia'),(193,2,'Seychelles'),(194,2,'Sierra Leone'),(195,2,'Singapore'),(196,2,'Slovakia'),(197,2,'Slovenia'),(198,2,'Solomon Islands'),(199,2,'Somalia'),(200,2,'South Africa'),(201,2,'South Georgia South Sandwich Islands'),(202,2,'Spain'),(203,2,'Sri Lanka'),(204,2,'St. Helena'),(205,2,'St. Pierre and Miquelon'),(206,2,'Sudan'),(207,2,'Suriname'),(208,2,'Svalbard and Jan Mayen Islands'),(209,2,'Swaziland'),(210,2,'Sweden'),(211,2,'Switzerland'),(212,2,'Syrian Arab Republic'),(213,2,'Taiwan'),(214,2,'Tajikistan'),(215,2,'Tanzania, United Republic of'),(216,2,'Thailand'),(217,2,'Togo'),(218,2,'Tokelau'),(219,2,'Tonga'),(220,2,'Trinidad and Tobago'),(221,2,'Tunisia'),(222,2,'Turkey'),(223,2,'Turkmenistan'),(224,2,'Turks and Caicos Islands'),(225,2,'Tuvalu'),(226,2,'Uganda'),(227,2,'Ukraine'),(228,2,'United Arab Emirates'),(229,2,'United Kingdom'),(230,2,'United States'),(231,2,'United States minor outlying islands'),(232,2,'Uruguay'),(233,2,'Uzbekistan'),(234,2,'Vanuatu'),(235,2,'Vatican City State'),(236,2,'Venezuela'),(237,2,'Vietnam'),(238,2,'Virgin Islands (British),'),(239,2,'Virgin Islands (U.S.),'),(240,2,'Wallis and Futuna Islands'),(241,2,'Western Sahara'),(242,2,'Yemen'),(243,2,'Zaire'),(244,2,'Zambia'),(245,2,'Zimbabwe'),(246,1,'None'),(247,1,'T-Systems Slovakia s. r. o.');
/*!40000 ALTER TABLE `CommonCustomerProperties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CommonMaterialProperties`
--

DROP TABLE IF EXISTS `CommonMaterialProperties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CommonMaterialProperties` (
  `PropertyID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyTypeID` int(11) NOT NULL,
  `PropertyName` varchar(255) NOT NULL,
  PRIMARY KEY (`PropertyID`),
  KEY `PropertyTypeID` (`PropertyTypeID`),
  CONSTRAINT `CommonMaterialProperties_ibfk_1` FOREIGN KEY (`PropertyTypeID`) REFERENCES `MaterialPropertyTypes` (`PropertyTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CommonMaterialProperties`
--

LOCK TABLES `CommonMaterialProperties` WRITE;
/*!40000 ALTER TABLE `CommonMaterialProperties` DISABLE KEYS */;
INSERT INTO `CommonMaterialProperties` VALUES (1,2,'Lime Green'),(2,2,'Black'),(3,2,'White'),(4,2,'Luminious Red'),(5,2,'Luminious Orange'),(6,2,'Red'),(7,2,'Blue'),(8,2,'Orange'),(9,2,'Gold'),(10,2,'Silver'),(11,2,'Navy Blue'),(12,2,'Green'),(13,2,'Raspberry Red'),(14,2,'Hot Red'),(15,2,'Everybody\'s magenta'),(16,2,'Melon Yellow'),(17,2,'Transparent Blue'),(18,2,'Brown'),(19,2,'Silk Blue'),(20,2,'Sky Blue'),(21,1,'PLA'),(22,1,'ABS'),(23,1,'PETG'),(24,1,'TPU'),(25,1,'Nylon'),(26,1,'PC'),(27,1,'PC/ABS'),(28,1,'HIPS'),(29,1,'PVA'),(30,1,'ASA'),(31,1,'POM'),(32,1,'Wood'),(33,1,'Metal'),(34,1,'PMMA'),(35,3,'Mladec'),(36,3,'Fillamentum'),(37,3,'Devil Design'),(38,3,'Jinbo Hao'),(39,3,'YASIN Filamnets'),(40,4,'3DeS s.r.o.'),(41,4,'Kreatiff s.r.o.'),(42,4,'Prusa Research'),(43,4,'EMG Trade s.r.o.'),(44,4,'WilTec s.r.o.'),(45,5,'1.75'),(46,5,'3.00'),(47,6,'500'),(48,6,'750'),(49,6,'1000'),(50,6,'2000'),(51,7,'FDM'),(52,7,'SLA');
/*!40000 ALTER TABLE `CommonMaterialProperties` ENABLE KEYS */;
UNLOCK TABLES;

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
  `CostShipping` float(7,2) DEFAULT NULL,
  `PurchaseDate` date DEFAULT NULL,
  `Comment` varchar(256) NOT NULL,
  `CostPrice` float(7,2) DEFAULT NULL,
  `PrinterID` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`CostID`),
  KEY `PrinterID` (`PrinterID`),
  CONSTRAINT `Costs_ibfk_1` FOREIGN KEY (`PrinterID`) REFERENCES `Printers` (`PrinterID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Costs`
--

LOCK TABLES `Costs` WRITE;
/*!40000 ALTER TABLE `Costs` DISABLE KEYS */;
INSERT INTO `Costs` VALUES (1,'E3D Nozzle 0.25mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00,1),(2,'E3D Nozzle 0.15mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00,1),(3,'E3D Nozzle 0.6mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00,1),(4,'Igus DryLin LM8UU Bearings',12,0.00,'2017-07-31','www.ebay.com ',20.09,1),(5,'Stepper Motor SX17-1003LQCEF',1,3.92,'2017-08-08','www.vsepro3dtisk.cz ',11.00,1),(6,'Electricity',1,0.00,'2017-05-18','Preplatok za elektrinu udajne sposobeny tlaciarnou ',60.00,1),(7,'Technicky Lieh',2,0.00,'2016-10-01','Technicky lieh na cistenie podlozky ',5.00,1),(8,'0.25 Nozzle',1,0.00,'2016-10-12','www.aliexpress.com ',0.28,1),(9,'Nozzle 0.35mm',5,0.00,'2016-12-03','www.aliexpress.com ',0.60,1),(10,'Nozzle 0.25',5,0.00,'2017-06-05','www.aliexpress.com ',0.76,1),(11,'Nozzle Cleaning Needles',1,0.00,'2017-11-20','www.aliexpress.com ',1.47,1),(12,'Precision Scale 0.1g',1,0.00,'2017-11-12','www.aliexpress.com ',2.99,1),(13,'Digital Calipper',1,0.00,'2017-11-30','www.aliexpress.com ',12.12,1),(14,'Epoxy Glue',1,0.00,'2018-09-11','Farby Laky Cana ',6.60,1),(15,'Gumene Rukavice',20,0.00,'2018-09-11','Farby Laky Cana ',1.20,2),(16,'Thermal Grizzly - Kryonaut 1g',1,1.18,'2018-10-10','https://www.alza.sk/thermal-grizzly-kryonaut-1g-d4798714.htm?layoutAutoChange=1 ',5.50,1),(17,'LS055R1SX03 5.5 inch 2560x1440 2K LCD screen',1,0.00,'2018-09-26','aliexpress ',27.98,2),(18,'9 Blades + Scalpel Knife',1,0.00,'2018-09-27','aliexpress ',1.41,1),(19,'Silver Keychains keyrings',100,0.26,'2018-10-03','aliexpress ',3.86,1),(20,'E3D V6 Gold heatsink',1,0.55,'2018-10-10','aliexpress ',4.31,1),(21,'E3D Heatbreak',1,0.00,'2018-10-10','aliexpress ',0.48,1),(22,'Trianglelab A2 Hardened Steel Nozzle 0.4mm',1,2.38,'2018-10-12','aliexpress ',9.16,1),(23,'Polyolefin Heatshrinking Tube',164,0.00,'2018-10-14','Aliexpress ',1.03,1),(24,'Original E3D Titan Extruder',1,5.70,'2018-10-12','e3d-online.com ',87.00,1),(25,'GT2 Belt Upgrade 100mm',20,5.70,'2018-10-12','e3d-online.com ',27.50,1),(26,'ANTCLABS BL Touch',1,4.55,'2018-10-12','ebay.com ',34.64,1),(27,'MKS MOSFET module for',1,0.47,'2018-10-10','ebay.com ',8.35,1),(28,'Raspberry Pi 3 B+',1,0.00,'2018-10-19','datacomp, Moldavska Kosice ',39.10,1),(29,'Qoltec AC adapter 15W | 5V | 3A | microUSB',1,0.00,'2018-10-19','datacomp, Moldavska Kosice ',7.99,1),(30,'5.5\' LS055R1SX04 2K 1440*2560 LCD screen',1,0.00,'2017-11-19','aliexpress ',31.24,2),(31,'Heatblock Thermal Isolation',1,1.55,'2018-11-08','tlacv3d.sk ',1.79,1),(32,'Neodym Magnet  4x10mm',4,3.95,'2018-12-10','Magnets for levitating sipral ',0.62,1),(33,'Rubber Feet for aluminum profiles',4,0.00,'2018-11-27','aliexpress ',2.96,1),(34,'Star Wars Cookie Cutters',1,0.00,'2018-12-17',' ',10.33,1),(35,'Heatbed Cables MK2S soldered',1,0.00,'2018-11-16','prusa shop ',5.74,1),(36,'Noctua NF-A4x10 FLX 40mm',1,0.00,NULL,'datacomp',12.46,1),(37,'210x250x3mm Borosilicate Glass',1,0.00,NULL,'aliexpress',14.68,1),(38,'Wanhao Resin 1L',2,36.44,NULL,'Black + white resin',122.01,2),(39,'Mini differential IR height sensor',1,5.50,NULL,'www.filastruder.com',27.68,1),(40,'Technicky Lieh',1,0.00,NULL,'',2.20,1),(41,'Scissors',1,0.00,NULL,'',1.10,1),(42,'Business Cards',50,4.20,NULL,'www.printman.sk',12.18,1),(43,'Cooling Fan Housing',4,0.00,NULL,'aliexpress',2.69,1);
/*!40000 ALTER TABLE `Costs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerPropertyTypes`
--

DROP TABLE IF EXISTS `CustomerPropertyTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomerPropertyTypes` (
  `PropertyTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyTypeName` varchar(100) NOT NULL,
  PRIMARY KEY (`PropertyTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerPropertyTypes`
--

LOCK TABLES `CustomerPropertyTypes` WRITE;
/*!40000 ALTER TABLE `CustomerPropertyTypes` DISABLE KEYS */;
INSERT INTO `CustomerPropertyTypes` VALUES (1,'Company'),(2,'Country');
/*!40000 ALTER TABLE `CustomerPropertyTypes` ENABLE KEYS */;
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
  CONSTRAINT `Customers_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `CommonCustomerProperties` (`PropertyID`),
  CONSTRAINT `Customers_ibfk_2` FOREIGN KEY (`CompanyID`) REFERENCES `CommonCustomerProperties` (`PropertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5062 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customers`
--

LOCK TABLES `Customers` WRITE;
/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (5000,'Anonymous','Customer','2018-11-05','','','','','','',196,246),(5001,'Jana','Kusendova','2017-01-02','','','Komenskeho 44','Kosice','','040 01',196,246),(5002,'Tomas','Pelak','2017-01-02','','','','Cana','','044 14',196,246),(5003,'Ondrej','Kuchta','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,247),(5004,'Eva','Dittelova','2017-01-02','','','Komenskeho 44','Kosice','','040 01',196,246),(5005,'Stefan','Varga','2017-01-02','','','Potocna 162/2','Zdana','','044 11',196,247),(5006,'Ruzenka','Dorova','2017-01-02','','','','Nizna Mysla','','044 15',196,246),(5007,'Stanislav','Kopko','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,247),(5008,'Veronika','Suskova','2017-07-16','','','Rieka 2476','Cadca','','022 01',196,246),(5009,'Janka','Mitrova','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,246),(5010,'Maros','Forrai','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,247),(5011,'Vianocne','Trhy','2017-01-02','Kulturny dom','','Jarmocná 118/4','Zdana','','044 11',196,246),(5012,'David','Tkac','2017-01-02','','','Siroka 315','Zdana','','044 11',196,246),(5013,'Tadeas','Jambor','2017-01-02','','','Okruzna 106','Zdana','','044 11',196,246),(5014,'Tomas','Mocarsky','2017-01-09','','','Zriedlova 13','Kosice','','040 01',196,247),(5015,'Miroslava','Medvecka','2017-01-19','','','Zriedlova 13','Kosice','','040 01',196,247),(5016,'Michal','Sykora','2017-01-30','','','Zriedlova 13','Kosice','','040 01',196,247),(5017,'Matus','Trpak','2017-03-13','','','Zriedlova 13','Kosice','','040 01',196,247),(5018,'Martin','Brestovic','2017-03-22','','','Zriedlova 13','Kosice','','040 01',196,247),(5019,'Jan','Popelas','2017-04-06','','','','Cana','','044 14',196,246),(5020,'Ivan','Adamkovic','2017-04-06','','','','','','',196,246),(5021,'David','Ondrus','2017-04-18','','','Toryska 5','Kosice','','040 01',196,246),(5022,'Monika','Fabianova','2017-05-04','','','Komenskeho 44','Kosice','','040 01',196,246),(5023,'Jakub','Hanula','2017-05-04','','','Zriedlova 13','Kosice','','040 01',196,247),(5024,'Tomas','Sromovsky','2017-05-19','','','Zriedlova 13','Kosice','','040 01',196,247),(5025,'Stefan','Girman','2017-05-28','','','Zriedlova 13','Kosice','','040 01',196,247),(5026,'Marcela','Stredanska','2017-07-04','','','P.O. Hviezdoslava 2328','Topolcany','','955 01',196,246),(5027,'VW Zraz','VW Zraz','2017-07-16','','','Kemp Tatranec','Vysoké Tatry','','059 60',196,246),(5028,'Richard','Spalek','2017-07-19','','','Podzahradna 49','Bratislava','','821 06',196,246),(5029,'Marian','Hodza','2017-07-19','','','Prazska 35','Bratislava','','811 04',196,246),(5030,'Robert','Svec','2017-07-19','','','Vinicky 279/11','Male Uherce','','958 03',196,246),(5031,'Marek','Cibula','2017-07-19','','','Cislo domu 865','Stiavnicke Bane','','969 81',196,246),(5032,'Marcel','Boldizar','2017-07-19','','','Zriedlova 13','Kosice','','040 01',196,247),(5033,'Martin','Weber ml.','2017-07-19','','','Sklenarova 28','Bratislava 2','','821 09',196,246),(5034,'Tomas','Michna','2017-07-19','','','Za Vodou 1361/2','Stara Lubovna','','064 01',196,246),(5035,'Marcel','Mihok','2017-07-19','','','Hronska 421/65','Valaska','','976 46',196,246),(5036,'Angel Sanchez','Parreno','2017-07-16','','','C/Los Robles A3','Guardo','','348 80',202,246),(5037,'Lubos','Cura','2017-07-23','','','Babie 56','Hanusovce nad Toplou','','094 31',196,246),(5038,'Michal','Mocol','2017-07-23','','','Kamenna 538','Strecno','','013 24',196,246),(5039,'Matus','Hohol','2017-09-03','','','Svitska Cesta 5','Poprad','','058 01',196,246),(5040,'Lukas','Borbely','2017-09-03','','','','Saca','','040 15',196,246),(5041,'Tomas','Gasiak','2017-09-09','','','Cislo domu 217','Sneznica','','023 32',196,246),(5042,'Jakub','Jakubech','2017-09-09','','','Cislo domu 276','Pruzina','','018 22',196,246),(5043,'Miroslav','Tomasko','2017-10-29','','','Potocna 160','Zdana','','044 11',196,246),(5044,'Viktor','Vysoky','2017-11-27','','','Idanska 31','Kosice','','040 01',196,246),(5045,'Nela','Vargova','2017-01-02',' ','+421 907 421 938','Zriedlova 13','Kosice','nela.vargova@t-systems.com','040 01',196,247),(5046,'Marek','Svoboda','2017-10-31',' ','+421 948 969 782',' ','Kosice',' ','040 01',196,246),(5047,'Arnold','Szabo','2017-11-12','','','Kvetna ulica 242/7','Maly Hores','','076 52',196,246),(5048,'Lukas','Dunaj','2017-01-02','','+421 911 470 858','','','','',196,247),(5049,'Jozef','Krupka','2017-01-02','','','','','','',196,247),(5050,'Martin','Gbur','2017-01-02','','','','','','',196,247),(5051,'Michal','Borza','2017-01-02','','','','','','',196,247),(5052,'Dan','Urban','2017-01-02','','','','','','',196,246),(5053,'Michal','Fortuna','2018-01-18','','','','','','',196,246),(5054,'Patrik','Popelic','2018-01-18','','+421 911 908 925','Sturova 44','Kosice','','040 01',196,246),(5055,'Martin','Murco','2018-01-17','','','Janka Krala 2591/1','Cadca','','022 01',196,246),(5056,'Telekom IT','GSO','2018-05-04','','','','','','',230,247),(5057,'Viktor','Slota','2018-06-27','','+421 904 656 289','Zahradna','Cana','','044 14',230,246),(5058,'Alex','Janitor','2018-05-30','','+421 915 136 055','','','alex.janitor@gmail.com','',230,246),(5059,'Derjan','Zsolt','2018-11-26','','0948789313','','','','',196,247),(5060,'Erik','Gorej','2019-01-28','','+421902653540','Ticha 4','Cana','erikgorej@gmail.com','044 14',196,246),(5061,'Maria','Gorejova','2019-04-08','','0948004888','Ticha 4','Cana','','04414',196,246);
/*!40000 ALTER TABLE `Customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialPropertyTypes`
--

DROP TABLE IF EXISTS `MaterialPropertyTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialPropertyTypes` (
  `PropertyTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyTypeName` varchar(100) NOT NULL,
  PRIMARY KEY (`PropertyTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialPropertyTypes`
--

LOCK TABLES `MaterialPropertyTypes` WRITE;
/*!40000 ALTER TABLE `MaterialPropertyTypes` DISABLE KEYS */;
INSERT INTO `MaterialPropertyTypes` VALUES (1,'Type'),(2,'Color'),(3,'Manufacturer'),(4,'Seller'),(5,'Diameter'),(6,'Weight'),(7,'Printer Type');
/*!40000 ALTER TABLE `MaterialPropertyTypes` ENABLE KEYS */;
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
  `MaterialPrice` float(7,2) DEFAULT NULL,
  `MaterialShipping` float(4,2) DEFAULT NULL,
  `PurchaseDate` date DEFAULT NULL,
  `SellerID` int(11) NOT NULL DEFAULT 1,
  `Finished` varchar(10) DEFAULT 'No',
  `Trash` float(2,1) DEFAULT NULL,
  `DiameterID` int(11) DEFAULT 1,
  `Comment` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`MaterialID`),
  KEY `ColorID` (`ColorID`),
  KEY `ManufacturerID` (`ManufacturerID`),
  KEY `SellerID` (`SellerID`),
  KEY `WeightID` (`WeightID`),
  KEY `DiameterID` (`DiameterID`),
  KEY `MaterialTypeID` (`MaterialTypeID`),
  CONSTRAINT `Materials_ibfk_1` FOREIGN KEY (`ColorID`) REFERENCES `CommonMaterialProperties` (`PropertyID`),
  CONSTRAINT `Materials_ibfk_2` FOREIGN KEY (`ManufacturerID`) REFERENCES `CommonMaterialProperties` (`PropertyID`),
  CONSTRAINT `Materials_ibfk_3` FOREIGN KEY (`SellerID`) REFERENCES `CommonMaterialProperties` (`PropertyID`),
  CONSTRAINT `Materials_ibfk_4` FOREIGN KEY (`WeightID`) REFERENCES `CommonMaterialProperties` (`PropertyID`),
  CONSTRAINT `Materials_ibfk_5` FOREIGN KEY (`DiameterID`) REFERENCES `CommonMaterialProperties` (`PropertyID`),
  CONSTRAINT `Materials_ibfk_6` FOREIGN KEY (`MaterialTypeID`) REFERENCES `CommonMaterialProperties` (`PropertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materials`
--

LOCK TABLES `Materials` WRITE;
/*!40000 ALTER TABLE `Materials` DISABLE KEYS */;
INSERT INTO `Materials` VALUES (1,35,21,1,49,0.00,0.00,'2016-09-13',42,'No',0.0,45,''),(2,35,21,2,49,15.90,2.75,'2016-10-21',40,'No',0.0,45,'Test'),(3,35,21,3,49,15.90,2.75,'2016-10-21',40,'No',0.0,45,''),(4,36,21,4,48,15.90,1.20,'2016-11-27',40,'Yes',0.0,45,''),(5,36,21,5,48,15.90,1.20,'2016-11-27',40,'Yes',0.0,45,''),(6,35,21,2,49,16.90,1.25,'2016-12-08',40,'No',0.0,45,''),(7,35,21,3,49,15.90,1.25,'2016-12-08',40,'No',0.0,45,''),(8,35,22,2,49,19.00,0.00,'2017-03-20',40,'No',0.0,45,''),(9,35,21,3,49,19.90,0.00,'2017-03-20',40,'No',0.0,45,''),(10,35,21,6,49,19.90,0.00,'2017-03-20',40,'Yes',0.0,45,''),(11,35,21,2,49,19.90,0.00,'2017-03-20',40,'No',0.0,45,''),(12,35,21,7,49,19.90,2.50,'2017-05-02',40,'Yes',0.0,45,''),(13,35,21,8,49,19.90,2.50,'2017-05-02',40,'Yes',0.0,45,''),(14,35,21,9,49,20.00,1.20,'2017-06-06',41,'No',0.0,45,''),(15,37,21,1,49,20.00,1.20,'2017-06-06',41,'Yes',0.0,45,''),(16,35,21,10,49,20.00,1.20,'2017-06-06',41,'No',0.0,45,''),(17,37,21,11,49,20.00,1.20,'2017-07-19',41,'Yes',0.0,45,''),(18,37,21,12,49,20.00,1.20,'2017-07-19',41,'Yes',0.0,45,''),(19,35,21,2,49,20.00,1.20,'2017-07-19',41,'No',0.0,45,''),(20,37,21,13,49,20.00,3.60,'2017-07-29',41,'Yes',0.0,45,''),(21,37,21,14,49,20.00,1.80,'2017-10-12',41,'No',0.0,45,''),(22,35,21,3,49,19.90,1.80,'2017-10-12',41,'Yes',0.0,45,''),(23,37,21,3,50,34.49,1.80,'2017-10-12',41,'No',0.0,45,''),(24,37,21,2,50,34.49,1.80,'2017-10-12',41,'Yes',0.0,45,''),(26,36,21,15,48,24.50,1.80,'2018-04-19',40,'Yes',0.0,45,''),(27,36,21,15,48,24.50,1.80,'2018-04-19',40,'Yes',0.0,45,''),(28,36,21,15,48,24.50,1.80,'2018-04-19',40,'No',0.0,45,' '),(29,36,21,16,48,0.00,0.00,'2018-08-13',40,'Yes',0.0,45,'Darcek za 2 tyzdnove meskanie Melon Yellow'),(30,38,21,3,49,6.98,0.00,'2018-10-27',43,'Yes',0.0,45,'Alibaba'),(31,39,23,17,49,11.09,0.00,'2018-10-27',43,'No',0.0,45,'Alibaba'),(32,38,21,10,49,6.98,0.00,'2018-10-30',43,'Yes',0.0,45,'alibaba'),(33,38,22,18,49,6.53,0.00,'2018-11-06',43,'Yes',0.0,45,'alibaba'),(34,38,21,19,49,7.98,0.00,'2018-11-06',43,'Yes',0.0,45,'alibaba'),(35,38,22,20,49,6.53,0.00,'2019-01-06',43,'Yes',0.0,45,'alibaba april 2018'),(36,39,23,17,49,11.09,0.00,'2019-01-30',43,'Yes',0.0,45,'alibaba'),(37,37,23,2,49,35.72,3.49,'2019-02-07',44,'Yes',0.0,45,'www.naga.sk'),(38,35,21,3,49,19.90,0.00,'2019-04-05',44,'No',0.0,45,'FREE COMPANY s.r.o.');
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
  `ObjectWeight` float(7,2) DEFAULT NULL,
  `SupportWeight` float(7,2) DEFAULT NULL,
  `ProjectID` int(11) NOT NULL DEFAULT 0,
  `BuildTime` int(11) NOT NULL,
  `StlLink` varchar(5000) DEFAULT '',
  `Comment` varchar(100) DEFAULT '',
  PRIMARY KEY (`ObjectID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `Objects_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `Projects` (`ProjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objects`
--

LOCK TABLES `Objects` WRITE;
/*!40000 ALTER TABLE `Objects` DISABLE KEYS */;
INSERT INTO `Objects` VALUES (1,'3D Printing',0.00,0.00,1,0,'',''),(2,'Eiffel Tower 0.4 nozzle',38.00,0.00,1,417,'https://www.thingiverse.com/thing:912478',''),(3,'Spiral Vase 2',85.00,0.00,2,441,'https://www.thingiverse.com/thing:481259',''),(4,'Balljoint Octopus',13.60,1.50,1,140,'https://www.thingiverse.com/thing:584405',''),(5,'Card Case 3.0 Cover',3.00,0.00,1,18,'',''),(6,'Card Case 3.0 Mid',17.80,3.00,1,51,'',''),(7,'Batman Blade',4.50,0.00,1,30,'',''),(8,'Card cover 1-slot',11.00,2.69,1,53,'',''),(9,'Logo 50x80 mm',5.00,0.00,1,15,'',''),(12,'Key Hanger',25.00,0.00,1,75,'',''),(13,'Key Hanger Outlines + Logo',5.00,0.00,1,10,'',''),(14,'Small Snowflake',0.70,0.00,1,20,'',''),(15,'Big Snowflake',3.00,0.00,1,20,'',''),(16,'Odroid C2 case Bottom',12.41,0.00,1,125,'',''),(17,'Odroid C2 case Top',17.06,0.00,1,172,'',''),(18,'Raspberry Pi 3 Case with Raspberry Logo Bottom',16.24,0.00,1,117,'',''),(19,'Raspberry Pi 3 Case with Raspberry Logo Top',22.16,0.00,1,78,'',''),(20,'Raspberry Pi Zero Simple Case Bottom',9.00,0.00,1,52,'',''),(21,'Raspberry Pi Zero Simple Case Top',5.00,0.00,1,20,'',''),(22,'Spiral Vase 1',92.00,0.00,1,457,'https://www.thingiverse.com/thing:481259',''),(23,'Spiral Vase 3',75.00,0.00,1,430,'https://www.thingiverse.com/thing:481259',''),(24,'T-Systems Figure',2.14,0.00,1,36,'',''),(25,'VW in Circle Keychain - Backplate',3.70,0.00,1,5,'https://www.thingiverse.com/thing:668293',''),(26,'VW in Circle Keychain - Logo',1.00,0.00,1,5,'https://www.thingiverse.com/thing:668293',''),(27,'Low Poly Subaru - Body',31.40,0.00,1,140,'https://www.myminifactory.com/object/3d-print-low-poly-subaru-impreza-22b-sti-66881',''),(28,'Low Poly Subaru - Spoiler',0.40,0.00,1,31,'https://www.myminifactory.com/object/3d-print-low-poly-subaru-impreza-22b-sti-66881',''),(29,'Low Poly Subaru - Wheels',6.20,0.40,1,24,'https://www.myminifactory.com/object/3d-print-low-poly-subaru-impreza-22b-sti-66881',''),(30,'Long Strap - Smooth',36.60,0.00,1,125,'','Snowboard binding'),(31,'Paleta na farby',66.50,0.00,1,255,'',''),(32,'Kryt na paletu na farby',28.60,0.00,1,43,'','');
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
  `ItemWeight` float(7,2) DEFAULT NULL,
  `ItemSupportWeight` float(7,2) DEFAULT NULL,
  `ItemBuildTime` int(11) NOT NULL,
  `ItemPrice` float(7,2) DEFAULT NULL,
  `ItemQuantity` int(11) NOT NULL,
  `PrinterID` int(11) NOT NULL DEFAULT 1,
  `ItemCosts` float(7,2) DEFAULT NULL,
  PRIMARY KEY (`OrderItemID`),
  KEY `OrderID` (`OrderID`),
  KEY `ObjectID` (`ObjectID`),
  KEY `ItemMaterialID` (`ItemMaterialID`),
  KEY `PrinterID` (`PrinterID`),
  CONSTRAINT `OrderItems_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`),
  CONSTRAINT `OrderItems_ibfk_2` FOREIGN KEY (`ObjectID`) REFERENCES `Objects` (`ObjectID`),
  CONSTRAINT `OrderItems_ibfk_3` FOREIGN KEY (`ItemMaterialID`) REFERENCES `Materials` (`MaterialID`),
  CONSTRAINT `OrderItems_ibfk_4` FOREIGN KEY (`PrinterID`) REFERENCES `Printers` (`PrinterID`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderItems`
--

LOCK TABLES `OrderItems` WRITE;
/*!40000 ALTER TABLE `OrderItems` DISABLE KEYS */;
INSERT INTO `OrderItems` VALUES (9,100001,2,2,38.00,0.00,417,5.00,1,1,0.60),(10,100001,3,3,85.00,0.00,441,5.00,1,1,1.35),(11,100002,1,1,86.06,61.91,204,3.00,1,1,0.00),(12,100003,1,2,8.50,1.00,73,2.00,1,1,0.15),(13,100004,3,3,85.00,0.00,441,5.00,1,1,1.35),(14,100005,1,2,200.00,0.00,240,6.00,1,1,3.18),(15,100006,3,3,85.00,0.00,441,10.00,1,1,1.35),(16,100007,3,3,85.00,0.00,441,5.00,1,1,1.35),(17,100007,22,3,92.00,0.00,914,10.00,2,1,1.46),(18,100008,3,3,85.00,0.00,441,5.00,1,1,1.35),(19,100009,3,3,85.00,0.00,441,5.00,1,1,1.35),(20,100010,8,4,11.00,2.69,53,1.70,1,1,0.29),(21,100010,9,7,5.00,0.00,15,0.30,1,1,0.08),(22,100011,14,7,0.70,0.00,520,4.90,26,1,0.01),(23,100011,2,7,38.00,0.00,417,5.00,1,1,0.60),(24,100011,1,1,14.50,1.50,95,0.40,1,1,0.00),(25,100011,1,1,111.00,15.00,434,3.00,1,1,0.00),(26,100011,7,2,4.50,0.00,180,3.00,6,1,0.07),(27,100011,15,7,3.00,0.00,700,17.50,35,1,0.05),(28,100011,1,2,116.00,0.00,605,4.00,1,1,1.84),(29,100012,6,4,17.80,3.00,51,2.50,1,1,0.44),(30,100012,5,4,3.00,0.00,36,1.00,2,1,0.06),(31,100012,9,7,5.00,0.00,30,0.50,2,1,0.08),(32,100013,1,4,1.00,0.00,13,0.30,1,1,0.02),(33,100014,16,5,12.41,0.00,125,2.00,1,1,0.26),(34,100014,17,5,17.06,0.00,172,2.00,1,1,0.36),(35,100015,19,7,22.16,0.00,78,2.50,1,1,0.35),(36,100015,18,7,16.24,0.00,117,2.00,1,1,0.26),(37,100016,18,5,16.24,0.00,117,2.50,1,1,0.34),(38,100016,19,5,22.16,0.00,78,2.00,1,1,0.47),(39,100016,20,5,9.00,0.00,104,4.00,2,1,0.19),(40,100016,21,5,5.00,0.00,40,2.00,2,1,0.11),(41,100017,1,4,11.00,1.80,150,3.10,1,1,0.27),(42,100017,1,7,71.41,29.64,863,13.00,1,1,1.61),(43,100017,1,6,18.57,0.00,126,3.90,1,1,0.31),(44,100018,3,3,85.00,0.00,441,5.00,1,1,1.35),(45,100019,8,6,11.00,2.69,53,1.70,1,1,0.23),(46,100019,9,7,5.00,0.00,15,0.30,1,1,0.08),(47,100020,5,7,3.00,0.00,36,1.00,2,1,0.05),(48,100020,6,7,17.80,3.00,51,2.50,1,1,0.33),(49,100020,9,6,5.00,0.00,30,0.50,2,1,0.08),(50,100021,1,6,9.50,0.00,330,2.00,2,1,0.16),(51,100022,1,6,30.00,5.00,105,5.00,1,1,0.59),(52,100023,1,9,10.80,0.00,40,5.00,1,1,0.21),(53,100024,1,6,143.63,30.74,695,17.50,1,1,2.95),(54,100024,1,9,139.63,24.60,1206,17.50,1,1,3.27),(55,100025,1,6,1.10,0.00,31,3.00,1,1,0.02),(56,100026,24,6,2.14,0.00,72,1.00,2,1,0.04),(57,100026,24,9,2.14,0.00,72,1.00,2,1,0.04),(58,100027,3,13,85.00,0.00,882,10.00,2,1,1.69),(59,100028,1,12,67.77,6.00,447,10.00,1,1,1.47),(60,100029,23,13,75.00,0.00,860,12.00,2,1,1.49),(61,100030,5,11,3.00,0.00,36,1.00,2,1,0.06),(62,100030,6,11,17.80,3.00,51,2.50,1,1,0.41),(63,100030,9,10,5.00,0.00,30,1.50,2,1,0.10),(64,100031,12,11,25.00,0.00,75,8.30,1,1,0.50),(65,100031,13,10,5.00,0.00,10,1.70,1,1,0.10),(66,100032,5,16,3.00,0.00,36,0.65,2,1,0.06),(67,100032,5,10,3.00,0.00,18,0.55,1,1,0.06),(68,100032,6,10,17.80,3.00,51,2.90,1,1,0.41),(69,100032,9,11,5.00,0.00,30,0.75,2,1,0.10),(70,100032,9,10,5.00,0.00,30,0.75,2,1,0.10),(71,100032,6,16,17.80,3.00,51,2.75,1,1,0.42),(72,100032,9,16,5.00,0.00,15,3.35,1,1,0.10),(73,100032,1,16,39.50,4.00,220,5.75,1,1,0.87),(74,100033,25,12,3.70,0.00,10,4.29,2,1,0.07),(75,100033,26,9,1.00,0.00,10,1.21,2,1,0.02),(76,100033,4,15,13.60,1.50,140,5.00,1,1,0.30),(77,100033,1,9,15.87,1.41,65,3.00,1,1,0.34),(78,100033,2,9,38.00,0.00,417,5.00,1,1,0.76),(79,100033,13,9,5.00,0.00,20,2.20,2,1,0.10),(80,100033,13,10,5.00,0.00,10,0.85,1,1,0.10),(81,100033,1,10,4.80,1.00,21,1.10,1,1,0.12),(82,100033,12,11,25.00,0.00,450,28.65,6,1,0.50),(83,100033,4,11,13.60,1.50,140,5.00,1,1,0.30),(84,100033,1,11,4.75,0.00,17,1.85,1,1,0.09),(85,100033,13,12,5.00,0.00,20,1.95,2,1,0.10),(86,100033,1,12,11.20,0.00,72,3.45,1,1,0.22),(87,100033,22,13,92.00,0.00,457,5.00,1,1,1.83),(88,100033,2,14,38.00,0.00,417,10.00,1,1,0.76),(89,100033,4,14,13.60,1.50,140,5.00,1,1,0.30),(90,100033,13,15,5.00,0.00,10,0.85,1,1,0.10),(91,100033,2,16,38.00,0.00,417,9.00,1,1,0.76),(92,100033,1,16,20.60,1.50,170,10.30,1,1,0.44),(93,100034,12,11,25.00,0.00,75,5.00,1,1,0.50),(94,100034,13,16,5.00,0.00,10,1.00,1,1,0.10),(95,100035,12,11,25.00,0.00,75,5.00,1,1,0.50),(96,100035,13,16,5.00,0.00,10,1.00,1,1,0.10),(97,100036,12,11,25.00,0.00,75,5.00,1,1,0.50),(98,100036,13,16,5.00,0.00,10,1.00,1,1,0.10),(99,100037,12,11,25.00,0.00,75,5.80,1,1,0.50),(100,100037,13,15,5.00,0.00,10,1.20,1,1,0.10),(101,100038,12,9,50.00,0.00,150,12.80,2,1,1.00),(102,100038,12,10,25.00,0.00,75,6.40,1,1,0.50),(103,100038,13,15,5.00,0.00,10,1.30,1,1,0.10),(104,100038,13,13,5.00,0.00,10,1.30,1,1,0.10),(105,100038,13,9,5.00,0.00,10,1.30,1,1,0.10),(106,100039,12,12,25.00,0.00,75,5.00,1,1,0.50),(107,100039,13,14,5.00,0.00,10,1.00,1,1,0.10),(108,100040,12,13,25.00,0.00,75,5.00,1,1,0.50),(109,100040,13,9,10.00,0.00,20,2.00,2,1,0.20),(110,100040,12,19,25.00,0.00,75,5.00,1,1,0.50),(111,100040,13,19,5.00,0.00,10,1.00,1,1,0.10),(112,100040,12,9,25.00,0.00,75,5.00,1,1,0.50),(113,100041,12,12,25.00,0.00,75,5.00,1,1,0.50),(114,100041,13,11,5.00,0.00,10,1.00,1,1,0.10),(115,100041,12,19,25.00,0.00,75,5.00,1,1,0.50),(116,100041,13,16,5.00,0.00,10,1.00,1,1,0.10),(117,100042,12,17,25.00,0.00,75,5.00,1,1,0.50),(118,100042,13,9,5.00,0.00,10,1.00,1,1,0.10),(119,100043,12,19,25.00,0.00,75,5.00,1,1,0.50),(120,100043,13,10,5.00,0.00,10,1.00,1,1,0.10),(121,100044,12,19,25.00,0.00,75,5.00,1,1,0.50),(122,100044,13,10,5.00,0.00,10,1.00,1,1,0.10),(123,100045,12,19,25.00,0.00,75,5.00,1,1,0.50),(124,100045,13,19,5.00,0.00,10,1.00,1,1,0.10),(125,100045,12,18,25.00,0.00,75,5.00,1,1,0.50),(126,100045,13,16,5.00,0.00,10,1.00,1,1,0.10),(127,100046,12,19,25.00,0.00,75,5.00,1,1,0.50),(128,100046,13,10,5.00,0.00,10,1.00,1,1,0.10),(129,100047,1,4,17.00,0.00,160,5.80,1,1,0.36),(130,100047,1,5,17.00,0.00,560,5.80,1,1,0.36),(131,100047,1,9,51.50,0.00,420,19.40,1,1,1.02),(132,100047,1,10,10.00,0.00,90,3.80,1,1,0.20),(133,100047,1,11,4.80,0.00,40,1.20,1,1,0.10),(134,100047,1,13,6.00,0.00,100,2.50,1,1,0.12),(135,100047,1,15,7.00,0.00,70,2.00,1,1,0.14),(136,100047,1,19,21.00,0.00,250,17.50,1,1,0.42),(137,100047,1,20,7.00,0.00,70,2.00,1,1,0.14),(138,100048,12,17,50.00,0.00,150,10.00,2,1,1.00),(139,100048,12,16,25.00,0.00,75,5.00,1,1,0.50),(140,100048,12,19,25.00,0.00,75,5.00,1,1,0.50),(141,100048,13,10,15.00,0.00,30,3.00,3,1,0.30),(142,100048,13,9,10.00,0.00,20,2.00,2,1,0.20),(143,100048,12,11,25.00,0.00,75,5.00,1,1,0.50),(144,100048,26,9,5.00,0.00,25,1.10,5,1,0.10),(145,100048,25,12,18.50,0.00,25,3.90,5,1,0.37),(146,100048,1,19,2.40,0.00,4,0.00,1,1,0.05),(147,100048,1,5,0.60,0.00,4,0.35,1,1,0.01),(148,100048,1,10,0.60,0.00,4,0.35,1,1,0.01),(149,100049,1,16,36.00,0.00,196,17.20,1,1,0.72),(150,100050,1,22,12.90,0.00,129,6.10,1,1,0.26),(151,100050,1,21,5.53,0.00,44,2.70,1,1,0.11),(152,100050,1,17,8.55,0.00,47,4.20,1,1,0.17),(153,100051,12,11,25.00,0.00,75,4.60,1,1,0.50),(154,100051,13,10,5.00,0.00,10,1.00,1,1,0.10),(155,100052,5,19,3.00,0.00,18,1.40,1,1,0.06),(156,100052,6,19,17.80,3.00,51,3.00,1,1,0.42),(157,100052,9,19,10.00,0.00,30,0.60,2,1,0.20),(158,100053,12,17,25.00,0.00,75,5.00,1,1,0.50),(159,100053,13,22,10.00,0.00,20,1.00,2,1,0.20),(160,100053,12,13,25.00,0.00,75,5.00,1,1,0.50),(161,100054,1,22,190.00,34.00,567,20.00,1,1,4.46),(162,100054,1,16,108.15,3.00,164,10.00,1,1,2.22),(163,100054,1,16,187.26,7.00,447,10.00,1,1,3.89),(164,100055,9,20,80.00,0.00,120,7.50,24,1,1.60),(165,100055,6,22,213.60,36.00,612,37.50,12,1,4.97),(166,100055,5,22,144.00,0.00,864,5.00,48,1,2.87),(167,100056,9,21,10.00,0.00,30,0.75,2,1,0.20),(168,100056,5,19,6.00,1.00,20,0.50,2,1,0.14),(169,100056,6,19,17.80,3.00,51,3.75,1,1,0.42),(170,100057,3,5,85.00,0.00,441,10.00,1,1,1.80),(171,100058,6,19,71.20,12.00,204,4.00,1,1,1.66),(172,100058,5,19,6.00,0.00,36,1.00,2,1,0.12),(173,100059,3,12,85.00,0.00,441,10.00,1,1,1.69),(174,100060,1,16,2.20,0.00,15,1.00,1,1,0.04),(175,100060,15,22,45.00,0.00,300,6.00,15,1,0.90),(176,100061,1,16,4.00,0.00,20,3.60,1,1,0.08),(177,100061,1,22,3.00,0.00,20,5.40,1,1,0.06),(178,100062,1,21,5.00,0.00,50,1.70,1,1,0.10),(179,100062,1,22,10.00,0.00,60,3.30,1,1,0.20),(180,100063,1,24,8.10,0.00,32,4.00,1,1,0.14),(181,100063,1,23,8.10,0.00,32,4.00,1,1,0.14),(182,100064,1,16,2.00,0.00,7,0.75,1,1,0.04),(183,100064,1,17,69.80,0.00,186,17.85,1,1,1.40),(184,100064,1,22,13.25,0.00,36,3.20,1,1,0.26),(185,100064,1,10,13.25,0.00,36,3.20,1,1,0.26),(186,100065,1,24,25.70,0.00,150,20.00,1,1,0.44),(187,100066,1,24,8.00,0.00,43,5.00,1,1,0.14),(188,100066,1,22,8.00,0.00,43,5.00,1,1,0.16),(189,100067,1,26,750.00,0.00,1032,75.54,1,1,24.50),(190,100067,1,27,750.00,0.00,1032,75.54,1,1,24.50),(191,100067,1,28,485.60,0.00,672,48.92,1,1,15.86),(192,100068,1,24,36.40,0.00,525,15.00,1,1,0.63),(193,100068,1,24,10.20,0.00,31,1.20,1,1,0.18),(194,100068,1,23,89.60,0.00,215,8.80,1,1,1.55),(195,100069,1,29,82.00,0.00,192,11.60,1,1,0.00),(196,100069,1,24,24.00,0.00,100,3.40,1,1,0.41),(197,100070,1,8,10.80,0.00,58,6.00,1,1,0.21),(198,100071,1,16,20.00,0.00,100,10.00,1,1,0.40),(199,100072,1,23,99.80,0.00,450,15.00,1,1,1.72),(200,100073,1,5,7.70,0.50,44,4.00,1,1,0.17),(201,100074,1,12,27.70,0.00,104,7.76,1,1,0.62),(202,100074,1,24,111.30,0.00,415,31.20,1,1,2.02),(203,100074,1,10,28.40,0.00,106,7.96,1,1,0.57),(204,100074,1,30,51.00,0.00,190,14.29,1,1,0.36),(205,100074,1,28,3.00,0.00,11,0.84,1,1,0.11),(206,100074,1,32,24.10,0.00,90,6.75,1,1,0.17),(207,100074,1,13,2.80,0.00,11,0.78,1,1,0.06),(208,100074,1,33,6.00,0.00,15,2.12,1,1,0.04),(209,100074,1,18,2.00,0.00,8,0.58,1,1,0.04),(210,100074,1,17,56.10,0.00,209,15.72,1,1,1.19),(211,100075,1,24,20.00,0.00,192,16.00,1,1,0.36),(212,100075,1,5,10.00,0.00,98,8.00,1,1,0.23),(213,100076,1,15,35.50,0.00,129,18.00,1,1,0.75),(214,100077,1,15,308.00,45.00,360,15.00,1,1,7.48),(215,100078,1,15,152.00,0.00,424,20.00,1,1,3.22),(216,100079,1,18,74.81,0.00,233,5.00,1,1,1.59),(217,100080,1,18,75.30,0.00,219,6.00,1,1,1.60);
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
  `OrderPrice` float(7,2) DEFAULT NULL,
  `OrderQuantity` int(11) NOT NULL,
  `DateCreated` date DEFAULT NULL,
  `OrderStatus` varchar(50) NOT NULL,
  `DueDate` date DEFAULT NULL,
  `Comment` varchar(256) DEFAULT NULL,
  `OrderCosts` float(7,2) DEFAULT NULL,
  `OrderWeight` float(7,2) DEFAULT NULL,
  `OrderSupportWeight` float(7,2) DEFAULT NULL,
  `OrderBuildTime` int(11) NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `OrderStatus` (`OrderStatus`),
  CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`),
  CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`OrderStatus`) REFERENCES `OrderStatus` (`OrderStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=100081 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (100001,5001,10.00,2,'2016-12-02','Sold','2016-12-02','Eiffel Tower + Spiral Vase 2',2.23,123.00,0.00,858),(100002,5002,3.00,1,'2016-10-10','Sold','2016-10-10','Kippah',0.00,86.00,61.00,204),(100003,5003,2.00,1,'2016-11-05','Sold','2016-11-05','Card Roller',0.16,8.00,1.00,73),(100004,5004,5.00,1,'2016-12-21','Sold','2016-12-21','Spiral Vase 2',1.59,85.00,0.00,441),(100005,5005,6.00,1,'2016-12-11','Sold','2016-12-11','Latka do regalu',3.73,200.00,0.00,240),(100006,5005,10.00,1,'2016-12-11','Sold','2016-12-11','Spiral Vase 2',1.59,85.00,0.00,441),(100007,5006,15.00,3,'2016-12-21','Sold','2016-12-21','Spiral Vase 2 + 2x Spiral Vase 1',3.30,1777.00,0.00,1355),(100008,5007,5.00,1,'2016-11-24','Sold','2016-11-24','Spiral Vase 2',1.59,85.00,0.00,441),(100009,5009,5.00,1,'2016-12-06','Sold','2016-12-06','Spiral Vase 2',1.59,85.00,0.00,441),(100010,5010,2.00,2,'2017-01-09','Sold','2017-01-09','1-slot Card Cover, Logo audi',0.34,16.00,2.00,68),(100011,5011,37.80,71,'2016-12-18','Sold','2016-12-18','',2.96,287.00,16.00,2951),(100012,5012,4.00,5,'2016-12-29','Sold','2016-12-29','Card Case 2.0 Tesla Logo',0.56,25.00,3.00,117),(100013,5013,0.30,1,'2016-12-30','Sold','2016-12-30','Guitar Pick',0.02,1.00,0.00,13),(100014,5005,4.00,2,'2017-01-09','Sold','2017-01-09','Odroid C2 Case',0.67,29.00,0.00,297),(100015,5007,4.50,2,'2017-01-09','Sold','2017-01-09','Raspberry Pi 3 Case With Raspberry Logo',0.66,38.00,0.00,195),(100016,5014,10.50,6,'2017-01-11','Sold','2017-01-11','2x Raspberry Pi Zero',1.19,52.00,0.00,339),(100017,5012,20.00,3,'2017-01-19','Sold','2017-01-19','Tesla Super Charger Mobile Charging Stand',1.81,100.00,0.00,1139),(100018,5015,5.00,1,'2017-01-19','Sold','2017-01-19','Spiral Vase 2',1.59,85.00,0.00,441),(100019,5016,2.00,2,'2017-01-25','Sold','2017-01-25','1-Slot Card Case Logo Apple',0.29,16.00,2.00,68),(100020,5017,4.00,5,'2017-03-13','Sold','2017-03-13','Card Case Ferrari + F1',0.45,25.00,3.00,117),(100021,5018,2.00,2,'2017-03-22','Sold','2017-03-22','Cover for Panasonic Speakers',0.17,9.00,0.00,330),(100022,5019,5.00,1,'2017-04-06','Sold','2017-04-06','HP-Stand',0.54,30.00,5.00,105),(100023,5020,5.00,1,'2017-04-11','Sold','2017-04-11','Naraznik Skoda 120',0.21,10.00,0.00,40),(100024,5021,35.00,2,'2017-04-18','Sold','2017-04-18','Torsen Differential',5.39,283.00,55.00,1901),(100025,5018,3.00,1,'2017-04-20','Sold','2017-04-20','Poistka do vysavaca',0.02,1.00,0.00,31),(100026,5017,2.00,4,'2017-04-24','Sold','2017-04-24','T-Systems Figurky',0.08,4.00,0.00,144),(100027,5022,10.00,2,'2017-05-04','Sold','2017-05-04','2x  Spiral Vase 2',1.90,85.00,0.00,882),(100028,5024,10.00,1,'2017-05-26','Sold','2017-05-26','ASUS Doplnky',1.52,67.00,6.00,447),(100029,5010,12.00,2,'2017-05-28','Sold','2017-05-28','2x Spiral Vase 3',1.68,75.00,0.00,860),(100030,5023,5.00,5,'2017-05-31','Sold','2017-05-31','Card Case',0.51,25.00,3.00,117),(100031,5025,10.00,2,'2017-05-28','Sold','2017-05-28','Key Hangers',0.60,30.00,0.00,85),(100032,5026,17.45,11,'2017-07-04','Sold','2017-07-04','2x Card case, Marlboro + Kaufland, Marlboro Cigarette Case',2.00,96.00,10.00,451),(100033,5027,103.70,28,'2017-07-16','Sold','2017-07-16','VW Zraz 2017',7.49,353.00,8.00,3003),(100034,5008,6.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger',0.60,30.00,0.00,85),(100035,5008,6.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger',0.60,30.00,0.00,85),(100036,5031,6.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger',0.60,30.00,0.00,85),(100037,5032,7.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger',0.60,30.00,0.00,85),(100038,5036,23.10,6,'2017-07-19','Sold','2017-07-19','3x Key Hanger',1.81,90.00,0.00,255),(100039,5034,6.00,2,'2017-07-23','Sold','2017-07-23','Key hanger',0.67,30.00,0.00,85),(100040,5028,18.00,6,'2017-07-23','Sold','2017-07-23','3x Key Hanger',1.89,90.00,0.00,255),(100041,5029,12.00,4,'2017-07-23','Sold','2017-07-23','2x Key Hanger',1.30,60.00,0.00,170),(100042,5037,6.00,2,'2017-07-23','Sold','2017-07-23','Key Hanger',0.63,30.00,0.00,85),(100043,5030,6.00,2,'2017-07-23','Sold','2017-07-23','Key Hanger',0.63,30.00,0.00,85),(100044,5033,6.00,2,'2017-07-23','Sold','2017-07-23','Key Hanger',0.63,30.00,0.00,85),(100045,5038,12.00,4,'2017-07-30','Sold','2017-07-30','2x Key Hanger',1.27,60.00,0.00,170),(100046,5035,6.00,2,'2017-07-30','Sold','2017-07-30','Key Hanger',0.63,30.00,0.00,85),(100047,5039,60.00,9,'2018-09-23','Sold','2018-09-23','Japanese Customs + Bella Art Keychains',2.99,141.00,0.00,1760),(100048,5040,35.70,23,'2017-09-09','Sold','2017-09-09','Key Hangers and other VW stuff',3.71,177.00,0.00,487),(100049,5041,17.20,1,'2017-09-09','Sold','2017-09-09','Krytky na kolesa',0.76,36.00,0.00,196),(100050,5043,13.00,3,'2017-10-29','Sold','2017-10-29','Cleavland Indians',0.58,26.00,0.00,220),(100051,5047,5.60,2,'2017-11-13','Sold','2017-11-13','Key Hanger',0.60,30.00,0.00,85),(100052,5049,5.00,4,'2017-11-16','Sold','2017-11-16','Razer 2-slot Card case ',0.65,30.00,3.00,99),(100053,5044,11.00,4,'2017-11-27','Sold','2017-11-27','2x Key Hanger Vans',1.31,60.00,0.00,170),(100054,5048,40.00,3,'2017-12-14','Sold','2017-12-14','Star Lord Mask and Gun',10.39,485.00,44.00,1178),(100055,5050,50.00,84,'2017-12-14','Sold','2017-12-14','12x Card Case',9.65,437.00,36.00,1596),(100056,5051,5.00,5,'2017-12-14','Sold','2017-12-14','CardCover Horder',0.72,33.00,4.00,101),(100057,5045,10.00,1,'2017-12-14','Sold','2017-12-14','Spiral Vase 2',1.94,85.00,0.00,441),(100058,5052,5.00,3,'2017-12-14','Sold','2017-12-14','2 slot card case',1.64,77.00,12.00,240),(100059,5045,10.00,1,'2017-12-14','Sold','2017-12-14','Spiral Vase',1.90,85.00,0.00,441),(100060,5011,7.00,16,'2017-12-14','Sold','2017-12-14','Triquetra and snowflakes',1.02,47.00,0.00,315),(100061,5029,9.00,2,'2017-12-14','Sold','2017-12-14','Cotrans keycain',0.15,7.00,0.00,40),(100062,5053,5.00,2,'2017-01-18','Sold','2017-01-18','AHK Sokolany',0.33,15.00,0.00,110),(100063,5054,8.00,2,'2018-01-28','Sold','2018-01-28','Subaru outback IV',0.29,16.00,0.00,86),(100064,5046,25.00,4,'2018-01-28','Sold','2018-01-28','Chinese Riddle',2.07,98.00,0.00,265),(100065,5055,20.00,1,'2017-01-17','Sold','2017-01-17','Chopper Style Sablny',0.47,25.00,0.00,150),(100066,5054,10.00,2,'2017-02-06','Sold','2017-02-06','Subaru outback IV',0.32,16.00,0.00,86),(100067,5056,200.00,3,'2018-05-04','Sold','2018-05-04','Telekom IT GSO Napis',69.63,1985.00,0.00,2736),(100068,5057,25.00,3,'2018-06-29','Sold','2018-06-29','Eiffel Tower 0.35mm + Versace picture',2.47,136.00,0.00,771),(100069,5043,15.00,2,'2018-08-02','Sold','2018-08-02','CAT Plate',0.44,106.00,0.00,292),(100070,5019,6.00,1,'2018-03-24','Sold','2018-03-24','Mobile phone stand',0.21,10.00,0.00,58),(100071,5041,10.00,1,'2018-04-04','Sold','2018-04-04','Dlasie 2 krytky na kolesa',0.42,20.00,0.00,100),(100072,5058,15.00,1,'2018-05-30','Sold','2018-05-30','Sedadlo do auta',1.81,99.00,0.00,450),(100073,5002,4.00,1,'2018-10-17','Sold','2018-10-17','Xiaomi Scooter backlight covers',0.19,7.70,0.50,44),(100074,5000,88.00,10,'2018-11-09','Sold','2018-11-09','73 Car keychains - Detail Valet, Erik Mizak, Suzuki M1800R etc...',5.17,312.40,0.00,1159),(100075,5000,24.00,2,'2018-11-20','Sold','2018-11-20','Schrotos privesky',0.59,30.00,0.00,290),(100076,5059,18.00,1,'2018-11-26','Sold','2018-11-26','3x rubiks cube keychain',0.75,35.50,0.00,129),(100077,5060,15.00,1,'2018-12-05','Sold','2018-12-05','egorej - redukcia na odsavacku',7.48,308.00,45.00,360),(100078,5000,20.00,1,'2018-12-19','Sold','2018-12-19','Nasty cookie cutters',3.22,152.00,0.00,424),(100079,5060,5.00,1,'2019-01-28','Sold','2019-01-28','Switch case + calble holders',1.59,74.81,0.00,233),(100080,5002,6.00,1,'2019-01-31','Sold','2019-01-31','SD card stand',1.60,75.30,0.00,219);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PrinterTypes`
--

DROP TABLE IF EXISTS `PrinterTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PrinterTypes` (
  `PrinterTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `PrinterType` varchar(50) NOT NULL,
  PRIMARY KEY (`PrinterTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PrinterTypes`
--

LOCK TABLES `PrinterTypes` WRITE;
/*!40000 ALTER TABLE `PrinterTypes` DISABLE KEYS */;
INSERT INTO `PrinterTypes` VALUES (1,'FDM'),(2,'SLA');
/*!40000 ALTER TABLE `PrinterTypes` ENABLE KEYS */;
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
  `PrinterPrice` float(7,2) DEFAULT NULL,
  `Comment` varchar(255) DEFAULT NULL,
  `PrinterShipping` float(7,2) DEFAULT NULL,
  `PrinterTypeID` int(11) NOT NULL DEFAULT 1,
  `PurchaseDate` date NOT NULL,
  `Duty` float(7,2) NOT NULL,
  `Tax` float(7,2) NOT NULL,
  PRIMARY KEY (`PrinterID`),
  CONSTRAINT `Printers_ibfk_1` FOREIGN KEY (`PrinterID`) REFERENCES `CommonMaterialProperties` (`PropertyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Printers`
--

LOCK TABLES `Printers` WRITE;
/*!40000 ALTER TABLE `Printers` DISABLE KEYS */;
INSERT INTO `Printers` VALUES (1,'Prusa i3 MK2',699.00,'Original Prusa',8.06,51,'2016-09-12',0.00,0.00),(2,'Wanhao Duplicator 7 Plus',408.50,'Original Wanhao D7 Plus',125.98,52,'2018-07-04',33.09,108.71);
/*!40000 ALTER TABLE `Printers` ENABLE KEYS */;
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

-- Dump completed on 2019-04-09  2:41:27
