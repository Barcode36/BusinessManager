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
  `CostPrice` float(5,2) NOT NULL DEFAULT 0.00,
  `PrinterID` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`CostID`),
  KEY `PrinterID` (`PrinterID`),
  CONSTRAINT `Costs_ibfk_1` FOREIGN KEY (`PrinterID`) REFERENCES `Printers` (`PrinterID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Costs`
--

LOCK TABLES `Costs` WRITE;
/*!40000 ALTER TABLE `Costs` DISABLE KEYS */;
INSERT INTO `Costs` VALUES (1,'E3D Nozzle 0.25mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00,1),(2,'E3D Nozzle 0.15mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00,1),(3,'E3D Nozzle 0.6mm',1,1.30,'2017-07-13','www.vsepro3dtisk.cz',0.00,1),(4,'Igus DryLin LM8UU Bearings',12,0.00,'2017-07-31','www.ebay.com ',20.09,1),(5,'Stepper Motor SX17-1003LQCEF',1,3.92,'2017-08-08','www.vsepro3dtisk.cz ',11.00,1),(6,'Electricity',1,0.00,'2017-05-18','Preplatok za elektrinu udajne sposobeny tlaciarnou ',60.00,1),(7,'Technicky Lieh',2,0.00,'2016-10-01','Technicky lieh na cistenie podlozky ',5.00,1),(8,'0.25 Nozzle',1,0.00,'2016-10-12','www.aliexpress.com ',0.28,1),(9,'Nozzle 0.35mm',5,0.00,'2016-12-03','www.aliexpress.com ',0.60,1),(10,'Nozzle 0.25',5,0.00,'2017-06-05','www.aliexpress.com ',0.76,1),(11,'Nozzle Cleaning Needles',1,0.00,'2017-11-20','www.aliexpress.com ',1.47,1),(12,'Precision Scale 0.1g',1,0.00,'2017-11-12','www.aliexpress.com ',2.99,1),(13,'Digital Calipper',1,0.00,'2017-11-30','www.aliexpress.com ',12.12,1),(14,'Epoxy Glue',1,0.00,'2018-09-11','Farby Laky Cana ',6.60,1),(15,'Gumene Rukavice',20,0.00,'2018-09-11','Farby Laky Cana ',1.20,2);
/*!40000 ALTER TABLE `Costs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Countries`
--

DROP TABLE IF EXISTS `Countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Countries` (
  `CountryId` int(11) NOT NULL AUTO_INCREMENT,
  `CountryCode` varchar(2) NOT NULL DEFAULT '',
  `CountryName` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`CountryId`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Countries`
--

LOCK TABLES `Countries` WRITE;
/*!40000 ALTER TABLE `Countries` DISABLE KEYS */;
INSERT INTO `Countries` VALUES (1,'AF','Afghanistan'),(2,'AL','Albania'),(3,'DZ','Algeria'),(4,'DS','American Samoa'),(5,'AD','Andorra'),(6,'AO','Angola'),(7,'AI','Anguilla'),(8,'AQ','Antarctica'),(9,'AG','Antigua and Barbuda'),(10,'AR','Argentina'),(11,'AM','Armenia'),(12,'AW','Aruba'),(13,'AU','Australia'),(14,'AT','Austria'),(15,'AZ','Azerbaijan'),(16,'BS','Bahamas'),(17,'BH','Bahrain'),(18,'BD','Bangladesh'),(19,'BB','Barbados'),(20,'BY','Belarus'),(21,'BE','Belgium'),(22,'BZ','Belize'),(23,'BJ','Benin'),(24,'BM','Bermuda'),(25,'BT','Bhutan'),(26,'BO','Bolivia'),(27,'BA','Bosnia and Herzegovina'),(28,'BW','Botswana'),(29,'BV','Bouvet Island'),(30,'BR','Brazil'),(31,'IO','British Indian Ocean Territory'),(32,'BN','Brunei Darussalam'),(33,'BG','Bulgaria'),(34,'BF','Burkina Faso'),(35,'BI','Burundi'),(36,'KH','Cambodia'),(37,'CM','Cameroon'),(38,'CA','Canada'),(39,'CV','Cape Verde'),(40,'KY','Cayman Islands'),(41,'CF','Central African Republic'),(42,'TD','Chad'),(43,'CL','Chile'),(44,'CN','China'),(45,'CX','Christmas Island'),(46,'CC','Cocos (Keeling), Islands'),(47,'CO','Colombia'),(48,'KM','Comoros'),(49,'CG','Congo'),(50,'CK','Cook Islands'),(51,'CR','Costa Rica'),(52,'HR','Croatia (Hrvatska),'),(53,'CU','Cuba'),(54,'CY','Cyprus'),(55,'CZ','Czech Republic'),(56,'DK','Denmark'),(57,'DJ','Djibouti'),(58,'DM','Dominica'),(59,'DO','Dominican Republic'),(60,'TP','East Timor'),(61,'EC','Ecuador'),(62,'EG','Egypt'),(63,'SV','El Salvador'),(64,'GQ','Equatorial Guinea'),(65,'ER','Eritrea'),(66,'EE','Estonia'),(67,'ET','Ethiopia'),(68,'FK','Falkland Islands (Malvinas),'),(69,'FO','Faroe Islands'),(70,'FJ','Fiji'),(71,'FI','Finland'),(72,'FR','France'),(73,'FX','France, Metropolitan'),(74,'GF','French Guiana'),(75,'PF','French Polynesia'),(76,'TF','French Southern Territories'),(77,'GA','Gabon'),(78,'GM','Gambia'),(79,'GE','Georgia'),(80,'DE','Germany'),(81,'GH','Ghana'),(82,'GI','Gibraltar'),(83,'GK','Guernsey'),(84,'GR','Greece'),(85,'GL','Greenland'),(86,'GD','Grenada'),(87,'GP','Guadeloupe'),(88,'GU','Guam'),(89,'GT','Guatemala'),(90,'GN','Guinea'),(91,'GW','Guinea-Bissau'),(92,'GY','Guyana'),(93,'HT','Haiti'),(94,'HM','Heard and Mc Donald Islands'),(95,'HN','Honduras'),(96,'HK','Hong Kong'),(97,'HU','Hungary'),(98,'IS','Iceland'),(99,'IN','India'),(100,'IM','Isle of Man'),(101,'ID','Indonesia'),(102,'IR','Iran (Islamic Republic of),'),(103,'IQ','Iraq'),(104,'IE','Ireland'),(105,'IL','Israel'),(106,'IT','Italy'),(107,'CI','Ivory Coast'),(108,'JE','Jersey'),(109,'JM','Jamaica'),(110,'JP','Japan'),(111,'JO','Jordan'),(112,'KZ','Kazakhstan'),(113,'KE','Kenya'),(114,'KI','Kiribati'),(115,'KP','Korea, Democratic People\'s Republic of'),(116,'KR','Korea, Republic of'),(117,'XK','Kosovo'),(118,'KW','Kuwait'),(119,'KG','Kyrgyzstan'),(120,'LA','Lao People\'s Democratic Republic'),(121,'LV','Latvia'),(122,'LB','Lebanon'),(123,'LS','Lesotho'),(124,'LR','Liberia'),(125,'LY','Libyan Arab Jamahiriya'),(126,'LI','Liechtenstein'),(127,'LT','Lithuania'),(128,'LU','Luxembourg'),(129,'MO','Macau'),(130,'MK','Macedonia'),(131,'MG','Madagascar'),(132,'MW','Malawi'),(133,'MY','Malaysia'),(134,'MV','Maldives'),(135,'ML','Mali'),(136,'MT','Malta'),(137,'MH','Marshall Islands'),(138,'MQ','Martinique'),(139,'MR','Mauritania'),(140,'MU','Mauritius'),(141,'TY','Mayotte'),(142,'MX','Mexico'),(143,'FM','Micronesia, Federated States of'),(144,'MD','Moldova, Republic of'),(145,'MC','Monaco'),(146,'MN','Mongolia'),(147,'ME','Montenegro'),(148,'MS','Montserrat'),(149,'MA','Morocco'),(150,'MZ','Mozambique'),(151,'MM','Myanmar'),(152,'NA','Namibia'),(153,'NR','Nauru'),(154,'NP','Nepal'),(155,'NL','Netherlands'),(156,'AN','Netherlands Antilles'),(157,'NC','New Caledonia'),(158,'NZ','New Zealand'),(159,'NI','Nicaragua'),(160,'NE','Niger'),(161,'NG','Nigeria'),(162,'NU','Niue'),(163,'NF','Norfolk Island'),(164,'MP','Northern Mariana Islands'),(165,'NO','Norway'),(166,'OM','Oman'),(167,'PK','Pakistan'),(168,'PW','Palau'),(169,'PS','Palestine'),(170,'PA','Panama'),(171,'PG','Papua New Guinea'),(172,'PY','Paraguay'),(173,'PE','Peru'),(174,'PH','Philippines'),(175,'PN','Pitcairn'),(176,'PL','Poland'),(177,'PT','Portugal'),(178,'PR','Puerto Rico'),(179,'QA','Qatar'),(180,'RE','Reunion'),(181,'RO','Romania'),(182,'RU','Russian Federation'),(183,'RW','Rwanda'),(184,'KN','Saint Kitts and Nevis'),(185,'LC','Saint Lucia'),(186,'VC','Saint Vincent and the Grenadines'),(187,'WS','Samoa'),(188,'SM','San Marino'),(189,'ST','Sao Tome and Principe'),(190,'SA','Saudi Arabia'),(191,'SN','Senegal'),(192,'RS','Serbia'),(193,'SC','Seychelles'),(194,'SL','Sierra Leone'),(195,'SG','Singapore'),(196,'SK','Slovakia'),(197,'SI','Slovenia'),(198,'SB','Solomon Islands'),(199,'SO','Somalia'),(200,'ZA','South Africa'),(201,'GS','South Georgia South Sandwich Islands'),(202,'ES','Spain'),(203,'LK','Sri Lanka'),(204,'SH','St. Helena'),(205,'PM','St. Pierre and Miquelon'),(206,'SD','Sudan'),(207,'SR','Suriname'),(208,'SJ','Svalbard and Jan Mayen Islands'),(209,'SZ','Swaziland'),(210,'SE','Sweden'),(211,'CH','Switzerland'),(212,'SY','Syrian Arab Republic'),(213,'TW','Taiwan'),(214,'TJ','Tajikistan'),(215,'TZ','Tanzania, United Republic of'),(216,'TH','Thailand'),(217,'TG','Togo'),(218,'TK','Tokelau'),(219,'TO','Tonga'),(220,'TT','Trinidad and Tobago'),(221,'TN','Tunisia'),(222,'TR','Turkey'),(223,'TM','Turkmenistan'),(224,'TC','Turks and Caicos Islands'),(225,'TV','Tuvalu'),(226,'UG','Uganda'),(227,'UA','Ukraine'),(228,'AE','United Arab Emirates'),(229,'GB','United Kingdom'),(230,'US','United States'),(231,'UM','United States minor outlying islands'),(232,'UY','Uruguay'),(233,'UZ','Uzbekistan'),(234,'VU','Vanuatu'),(235,'VA','Vatican City State'),(236,'VE','Venezuela'),(237,'VN','Vietnam'),(238,'VG','Virgin Islands (British),'),(239,'VI','Virgin Islands (U.S.),'),(240,'WF','Wallis and Futuna Islands'),(241,'EH','Western Sahara'),(242,'YE','Yemen'),(243,'ZR','Zaire'),(244,'ZM','Zambia'),(245,'ZW','Zimbabwe');
/*!40000 ALTER TABLE `Countries` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5059 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customers`
--

LOCK TABLES `Customers` WRITE;
/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (5001,'Jana','Kusendova','2017-01-02','','','Komenskeho 44','Kosice','','040 01',196,1),(5002,'Tomas','Pelak','2017-01-02','','','','Cana','','044 14',196,1),(5003,'Ondrej','Kuchta','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,2),(5004,'Eva','Dittelova','2017-01-02','','','Komenskeho 44','Kosice','','040 01',196,1),(5005,'Stefan','Varga','2017-01-02','','','Potocna 162/2','Zdana','','044 11',196,2),(5006,'Ruzenka','Dorova','2017-01-02','','','','Nizna Mysla','','044 15',196,1),(5007,'Stanislav','Kopko','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,2),(5008,'Veronika','Suskova','2017-07-16','','','Rieka 2476','Cadca','','022 01',196,1),(5009,'Janka','Mitrova','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,1),(5010,'Maros','Forrai','2017-01-02','','','Zriedlova 13','Kosice','','040 01',196,2),(5011,'Vianocne','Trhy','2017-01-02','Kulturny dom','','Jarmocná 118/4','Zdana','','044 11',196,1),(5012,'David','Tkac','2017-01-02','','','Siroka 315','Zdana','','044 11',196,1),(5013,'Tadeas','Jambor','2017-01-02','','','Okruzna 106','Zdana','','044 11',196,1),(5014,'Tomas','Mocarsky','2017-01-09','','','Zriedlova 13','Kosice','','040 01',196,2),(5015,'Miroslava','Medvecka','2017-01-19','','','Zriedlova 13','Kosice','','040 01',196,2),(5016,'Michal','Sykora','2017-01-30','','','Zriedlova 13','Kosice','','040 01',196,2),(5017,'Matus','Trpak','2017-03-13','','','Zriedlova 13','Kosice','','040 01',196,2),(5018,'Martin','Brestovic','2017-03-22','','','Zriedlova 13','Kosice','','040 01',196,2),(5019,'Jan','Popelas','2017-04-06','','','','Cana','','044 14',196,1),(5020,'Ivan','Adamkovic','2017-04-06','','','','','','',196,1),(5021,'David','Ondrus','2017-04-18','','','Toryska 5','Kosice','','040 01',196,1),(5022,'Monika','Fabianova','2017-05-04','','','Komenskeho 44','Kosice','','040 01',196,1),(5023,'Jakub','Hanula','2017-05-04','','','Zriedlova 13','Kosice','','040 01',196,2),(5024,'Tomas','Sromovsky','2017-05-19','','','Zriedlova 13','Kosice','','040 01',196,2),(5025,'Stefan','Girman','2017-05-28','','','Zriedlova 13','Kosice','','040 01',196,2),(5026,'Marcela','Stredanska','2017-07-04','','','P.O. Hviezdoslava 2328','Topolcany','','955 01',196,1),(5027,'VW Zraz','VW Zraz','2017-07-16','','','Kemp Tatranec','Vysoké Tatry','','059 60',196,1),(5028,'Richard','Spalek','2017-07-19','','','Podzahradna 49','Bratislava','','821 06',196,1),(5029,'Marian','Hodza','2017-07-19','','','Prazska 35','Bratislava','','811 04',196,1),(5030,'Robert','Svec','2017-07-19','','','Vinicky 279/11','Male Uherce','','958 03',196,1),(5031,'Marek','Cibula','2017-07-19','','','Cislo domu 865','Stiavnicke Bane','','969 81',196,1),(5032,'Marcel','Boldizar','2017-07-19','','','Zriedlova 13','Kosice','','040 01',196,2),(5033,'Martin','Weber ml.','2017-07-19','','','Sklenarova 28','Bratislava 2','','821 09',196,1),(5034,'Tomas','Michna','2017-07-19','','','Za Vodou 1361/2','Stara Lubovna','','064 01',196,1),(5035,'Marcel','Mihok','2017-07-19','','','Hronska 421/65','Valaska','','976 46',196,1),(5036,'Angel Sanchez','Parreno','2017-07-16','','','C/Los Robles A3','Guardo','','348 80',202,1),(5037,'Lubos','Cura','2017-07-23','','','Babie 56','Hanusovce nad Toplou','','094 31',196,1),(5038,'Michal','Mocol','2017-07-23','','','Kamenna 538','Strecno','','013 24',196,1),(5039,'Matus','Hohol','2017-09-03','','','Svitska Cesta 5','Poprad','','058 01',196,1),(5040,'Lukas','Borbely','2017-09-03','','','','Saca','','040 15',196,1),(5041,'Tomas','Gasiak','2017-09-09','','','Cislo domu 217','Sneznica','','023 32',196,1),(5042,'Jakub','Jakubech','2017-09-09','','','Cislo domu 276','Pruzina','','018 22',196,1),(5043,'Miroslav','Tomasko','2017-10-29','','','Potocna 160','Zdana','','044 11',196,1),(5044,'Viktor','Vysoky','2017-11-27','','','Idanska 31','Kosice','','040 01',196,1),(5045,'Nela','Vargova','2017-01-02',' ','+421 907 421 938','Zriedlova 13','Kosice','nela.vargova@t-systems.com','040 01',196,2),(5046,'Marek','Svoboda','2017-10-31',' ','+421 948 969 782',' ','Kosice',' ','040 01',196,1),(5047,'Arnold','Szabo','2017-11-12','','','Kvetna ulica 242/7','Maly Hores','','076 52',196,1),(5048,'Lukas','Dunaj','2017-01-02','','+421 911 470 858','','','','',196,2),(5049,'Jozef','Krupka','2017-01-02','','','','','','',196,2),(5050,'Martin','Gbur','2017-01-02','','','','','','',196,2),(5051,'Michal','Borza','2017-01-02','','','','','','',196,2),(5052,'Dan','Urban','2017-01-02','','','','','','',196,1),(5053,'Michal','Fortuna','2018-01-18','','','','','','',196,1),(5054,'Patrik','Popelic','2018-01-18','','+421 911 908 925','Sturova 44','Kosice','','040 01',196,1),(5055,'Martin','Murco','2018-01-17','','','Janka Krala 2591/1','Cadca','','022 01',196,1),(5056,'Telekom IT','GSO','2018-05-04','','','','','','',230,2),(5057,'Viktor','Slota','2018-06-27','','+421 904 656 289','Zahradna','Cana','','044 14',230,1),(5058,'Alex','Janitor','2018-05-30','','+421 915 136 055','','','alex.janitor@gmail.com','',230,1);
/*!40000 ALTER TABLE `Customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialColors`
--

DROP TABLE IF EXISTS `MaterialColors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialColors` (
  `ColorID` int(11) NOT NULL AUTO_INCREMENT,
  `ColorName` varchar(50) NOT NULL,
  PRIMARY KEY (`ColorID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialColors`
--

LOCK TABLES `MaterialColors` WRITE;
/*!40000 ALTER TABLE `MaterialColors` DISABLE KEYS */;
INSERT INTO `MaterialColors` VALUES (1,'Lime Green'),(2,'Black'),(3,'White'),(4,'Luminious Red'),(5,'Luminious Orange'),(6,'Red'),(7,'Blue'),(8,'Orange'),(9,'Gold'),(10,'Silver'),(11,'Navy Blue'),(12,'Green'),(13,'Raspberry Red'),(14,'Hot Red'),(15,'Everybody\'s magenta'),(16,'Melon Yellow');
/*!40000 ALTER TABLE `MaterialColors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialDiameters`
--

DROP TABLE IF EXISTS `MaterialDiameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialDiameters` (
  `DiameterID` int(11) NOT NULL AUTO_INCREMENT,
  `DiameterValue` float(3,2) NOT NULL,
  PRIMARY KEY (`DiameterID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialDiameters`
--

LOCK TABLES `MaterialDiameters` WRITE;
/*!40000 ALTER TABLE `MaterialDiameters` DISABLE KEYS */;
INSERT INTO `MaterialDiameters` VALUES (1,1.75),(2,3.00);
/*!40000 ALTER TABLE `MaterialDiameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialManufacturers`
--

DROP TABLE IF EXISTS `MaterialManufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialManufacturers` (
  `ManufacturerID` int(11) NOT NULL AUTO_INCREMENT,
  `ManufacturerName` varchar(30) NOT NULL DEFAULT 'Mladec',
  PRIMARY KEY (`ManufacturerID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialManufacturers`
--

LOCK TABLES `MaterialManufacturers` WRITE;
/*!40000 ALTER TABLE `MaterialManufacturers` DISABLE KEYS */;
INSERT INTO `MaterialManufacturers` VALUES (1,'Mladec'),(2,'Fillamentum'),(3,'Devil Design');
/*!40000 ALTER TABLE `MaterialManufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialSellers`
--

DROP TABLE IF EXISTS `MaterialSellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialSellers` (
  `SellerID` int(11) NOT NULL AUTO_INCREMENT,
  `SellerName` varchar(50) NOT NULL DEFAULT '3DeS s.r.o.',
  PRIMARY KEY (`SellerID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialSellers`
--

LOCK TABLES `MaterialSellers` WRITE;
/*!40000 ALTER TABLE `MaterialSellers` DISABLE KEYS */;
INSERT INTO `MaterialSellers` VALUES (1,'3DeS s.r.o.'),(2,'Kreatiff s.r.o.'),(3,'Prusa Research');
/*!40000 ALTER TABLE `MaterialSellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialTypes`
--

DROP TABLE IF EXISTS `MaterialTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialTypes` (
  `MaterialTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `MaterialType` varchar(10) DEFAULT 'PLA',
  PRIMARY KEY (`MaterialTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialTypes`
--

LOCK TABLES `MaterialTypes` WRITE;
/*!40000 ALTER TABLE `MaterialTypes` DISABLE KEYS */;
INSERT INTO `MaterialTypes` VALUES (1,'PLA'),(2,'ABS');
/*!40000 ALTER TABLE `MaterialTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaterialWeights`
--

DROP TABLE IF EXISTS `MaterialWeights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaterialWeights` (
  `WeightID` int(11) NOT NULL AUTO_INCREMENT,
  `WeightValue` int(11) NOT NULL,
  PRIMARY KEY (`WeightID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaterialWeights`
--

LOCK TABLES `MaterialWeights` WRITE;
/*!40000 ALTER TABLE `MaterialWeights` DISABLE KEYS */;
INSERT INTO `MaterialWeights` VALUES (1,500),(2,750),(3,1000),(4,2000);
/*!40000 ALTER TABLE `MaterialWeights` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materials`
--

LOCK TABLES `Materials` WRITE;
/*!40000 ALTER TABLE `Materials` DISABLE KEYS */;
INSERT INTO `Materials` VALUES (1,1,1,1,3,0.00,0.00,'2016-09-13',3,'Yes',0.0,1,''),(2,1,1,2,3,15.90,2.75,'2016-10-21',1,'Yes',0.0,1,'Test'),(3,1,1,3,3,15.90,2.75,'2016-10-21',1,'Yes',0.0,1,''),(4,2,1,4,2,15.90,1.20,'2016-11-27',1,'No',0.0,1,''),(5,2,1,5,2,15.90,1.20,'2016-11-27',1,'No',0.0,1,''),(6,1,1,2,3,16.90,1.25,'2016-12-08',1,'Yes',0.0,1,''),(7,1,1,3,3,15.90,1.25,'2016-12-08',1,'Yes',0.0,1,''),(8,1,2,2,3,19.00,0.00,'2017-03-20',1,'No',0.0,1,''),(9,1,1,3,3,19.90,0.00,'2017-03-20',1,'Yes',0.0,1,''),(10,1,1,6,3,19.90,0.00,'2017-03-20',1,'No',0.0,1,''),(11,1,1,2,3,19.90,0.00,'2017-03-20',1,'Yes',0.0,1,''),(12,1,1,7,3,19.90,2.50,'2017-05-02',1,'No',0.0,1,''),(13,1,1,8,3,19.90,2.50,'2017-05-02',1,'No',0.0,1,''),(14,1,1,9,3,20.00,1.20,'2017-06-06',2,'No',0.0,1,''),(15,3,1,1,3,20.00,1.20,'2017-06-06',2,'No',0.0,1,''),(16,1,1,10,3,20.00,1.20,'2017-06-06',2,'No',0.0,1,''),(17,3,1,11,3,20.00,1.20,'2017-07-19',2,'No',0.0,1,''),(18,3,1,12,3,20.00,1.20,'2017-07-19',2,'No',0.0,1,''),(19,1,1,2,3,20.00,1.20,'2017-07-19',2,'No',0.0,1,''),(20,3,1,13,3,20.00,3.60,'2017-07-29',2,'No',0.0,1,''),(21,3,1,14,3,20.00,1.80,'2017-10-12',2,'No',0.0,1,''),(22,1,1,3,3,19.90,1.80,'2017-10-12',2,'No',0.0,1,''),(23,3,1,3,4,34.49,1.80,'2017-10-12',2,'No',0.0,1,''),(24,3,1,2,4,34.49,1.80,'2017-10-12',2,'No',0.0,1,''),(25,2,1,0,2,24.50,1.80,'2018-04-19',1,'Yes',0.0,1,''),(26,2,1,15,2,24.50,1.80,'2018-04-19',1,'No',0.0,1,''),(27,2,1,15,2,24.50,1.80,'2018-04-19',1,'No',0.0,1,''),(28,2,1,15,2,24.50,1.80,'2018-04-19',1,'No',0.0,1,' '),(29,2,1,16,2,0.00,0.00,'2018-08-13',1,'No',0.0,1,'Darcek za 2 tyzdnove meskanie Melon Yellow');
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
  `StlLink` varchar(5000) DEFAULT '',
  `Comment` varchar(100) DEFAULT '',
  PRIMARY KEY (`ObjectID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `Objects_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `Projects` (`ProjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objects`
--

LOCK TABLES `Objects` WRITE;
/*!40000 ALTER TABLE `Objects` DISABLE KEYS */;
INSERT INTO `Objects` VALUES (1,'3D Printing',0.00,0.00,1,0,'',''),(2,'Eiffel Tower 0.4 nozzle',38.00,0.00,1,417,'https://www.thingiverse.com/thing:912478',''),(3,'Spiral Vase 2',85.00,0.00,2,441,'https://www.thingiverse.com/thing:481259',''),(4,'Balljoint Octopus',13.60,1.50,1,140,'https://www.thingiverse.com/thing:584405',''),(5,'Card Case 3.0 Cover',3.00,0.00,1,18,'',''),(6,'Card Case 3.0 Mid',17.80,3.00,1,51,'',''),(7,'Batman Blade',4.50,0.00,1,30,'',''),(8,'Card cover 1-slot',11.00,2.69,1,53,'',''),(9,'Logo 50x80 mm',5.00,0.00,1,15,'',''),(10,'GTI Keychain - Text',0.60,0.00,1,4,'',''),(11,'GTI Keychain - Plate',1.20,0.00,1,2,'',''),(12,'Key Hanger',25.00,0.00,1,75,'',''),(13,'Key Hanger Outlines + Logo',5.00,0.00,1,10,'',''),(14,'Small Snowflake',0.70,0.00,1,20,'',''),(15,'Big Snowflake',3.00,0.00,1,20,'',''),(16,'Odroid C2 case Bottom',12.41,0.00,1,125,'',''),(17,'Odroid C2 case Top',17.06,0.00,1,172,'',''),(18,'Raspberry Pi 3 Case with Raspberry Logo Bottom',16.24,0.00,1,117,'',''),(19,'Raspberry Pi 3 Case with Raspberry Logo Top',22.16,0.00,1,78,'',''),(20,'Raspberry Pi Zero Simple Case Bottom',9.00,0.00,1,52,'',''),(21,'Raspberry Pi Zero Simple Case Top',5.00,0.00,1,20,'',''),(22,'Spiral Vase 1',92.00,0.00,1,457,'https://www.thingiverse.com/thing:481259',''),(23,'Spiral Vase 3',75.00,0.00,1,430,'https://www.thingiverse.com/thing:481259',''),(24,'T-Systems Figure',2.14,0.00,1,36,'',''),(25,'VW in Circle Keychain - Backplate',3.70,0.00,1,5,'https://www.thingiverse.com/thing:668293',''),(26,'VW in Circle Keychain - Logo',1.00,0.00,1,5,'https://www.thingiverse.com/thing:668293','');
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
  `PrinterID` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`OrderItemID`),
  KEY `OrderID` (`OrderID`),
  KEY `ObjectID` (`ObjectID`),
  KEY `ItemMaterialID` (`ItemMaterialID`),
  KEY `PrinterID` (`PrinterID`),
  CONSTRAINT `OrderItems_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`),
  CONSTRAINT `OrderItems_ibfk_2` FOREIGN KEY (`ObjectID`) REFERENCES `Objects` (`ObjectID`),
  CONSTRAINT `OrderItems_ibfk_3` FOREIGN KEY (`ItemMaterialID`) REFERENCES `Materials` (`MaterialID`),
  CONSTRAINT `OrderItems_ibfk_4` FOREIGN KEY (`PrinterID`) REFERENCES `Printers` (`PrinterID`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderItems`
--

LOCK TABLES `OrderItems` WRITE;
/*!40000 ALTER TABLE `OrderItems` DISABLE KEYS */;
INSERT INTO `OrderItems` VALUES (9,100001,2,2,38.00,0.00,417,5.00,1,1),(10,100001,3,3,85.00,0.00,441,5.00,1,1),(11,100002,1,1,86.06,61.91,204,3.00,1,1),(12,100003,1,2,8.50,1.00,73,2.00,1,1),(13,100004,3,3,85.00,0.00,441,5.00,1,1),(14,100005,1,2,200.00,0.00,240,6.00,1,1),(15,100006,3,3,85.00,0.00,441,10.00,1,1),(16,100007,3,3,85.00,0.00,441,5.00,1,1),(17,100007,22,3,92.00,0.00,914,10.00,2,1),(18,100008,3,3,85.00,0.00,441,5.00,1,1),(19,100009,3,3,85.00,0.00,441,5.00,1,1),(20,100010,8,4,11.00,2.69,53,1.70,1,1),(21,100010,9,7,5.00,0.00,15,0.30,1,1),(22,100011,14,7,0.70,0.00,520,4.90,26,1),(23,100011,2,7,38.00,0.00,417,5.00,1,1),(24,100011,1,1,14.50,1.50,95,0.40,1,1),(25,100011,1,1,111.00,15.00,434,3.00,1,1),(26,100011,7,2,4.50,0.00,180,3.00,6,1),(27,100011,15,7,3.00,0.00,700,17.50,35,1),(28,100011,1,2,116.00,0.00,605,4.00,1,1),(29,100012,6,4,17.80,3.00,51,2.50,1,1),(30,100012,5,4,3.00,0.00,36,1.00,2,1),(31,100012,9,7,5.00,0.00,30,0.50,2,1),(32,100013,1,4,1.00,0.00,13,0.30,1,1),(33,100014,16,5,12.41,0.00,125,2.00,1,1),(34,100014,17,5,17.06,0.00,172,2.00,1,1),(35,100015,19,7,22.16,0.00,78,2.50,1,1),(36,100015,18,7,16.24,0.00,117,2.00,1,1),(37,100016,18,5,16.24,0.00,117,2.50,1,1),(38,100016,19,5,22.16,0.00,78,2.00,1,1),(39,100016,20,5,9.00,0.00,104,4.00,2,1),(40,100016,21,5,5.00,0.00,40,2.00,2,1),(41,100017,1,4,11.00,1.80,150,3.10,1,1),(42,100017,1,7,71.41,29.64,863,13.00,1,1),(43,100017,1,6,18.57,0.00,126,3.90,1,1),(44,100018,3,3,85.00,0.00,441,5.00,1,1),(45,100019,8,6,11.00,2.69,53,1.70,1,1),(46,100019,9,7,5.00,0.00,15,0.30,1,1),(47,100020,5,7,3.00,0.00,36,1.00,2,1),(48,100020,6,7,17.80,3.00,51,2.50,1,1),(49,100020,9,6,5.00,0.00,30,0.50,2,1),(50,100021,1,6,9.50,0.00,330,2.00,2,1),(51,100022,1,6,30.00,5.00,105,5.00,1,1),(52,100023,1,9,10.80,0.00,40,5.00,1,1),(53,100024,1,6,143.63,30.74,695,17.50,1,1),(54,100024,1,9,139.63,24.60,1206,17.50,1,1),(55,100025,1,6,1.10,0.00,31,3.00,1,1),(56,100026,24,6,2.14,0.00,72,1.00,2,1),(57,100026,24,9,2.14,0.00,72,1.00,2,1),(58,100027,3,13,85.00,0.00,882,10.00,2,1),(59,100028,1,12,67.77,6.00,447,10.00,1,1),(60,100029,23,13,75.00,0.00,860,12.00,2,1),(61,100030,5,11,3.00,0.00,36,1.00,2,1),(62,100030,6,11,17.80,3.00,51,2.50,1,1),(63,100030,9,10,5.00,0.00,30,1.50,2,1),(64,100031,12,11,25.00,0.00,75,8.30,1,1),(65,100031,13,10,5.00,0.00,10,1.70,1,1),(66,100032,5,16,3.00,0.00,36,0.65,2,1),(67,100032,5,10,3.00,0.00,18,0.55,1,1),(68,100032,6,10,17.80,3.00,51,2.90,1,1),(69,100032,9,11,5.00,0.00,30,0.75,2,1),(70,100032,9,10,5.00,0.00,30,0.75,2,1),(71,100032,6,16,17.80,3.00,51,2.75,1,1),(72,100032,9,16,5.00,0.00,15,3.35,1,1),(73,100032,1,16,39.50,4.00,220,5.75,1,1),(74,100033,25,12,3.70,0.00,10,4.29,2,1),(75,100033,26,9,1.00,0.00,10,1.21,2,1),(76,100033,4,15,13.60,1.50,140,5.00,1,1),(77,100033,1,9,15.87,1.41,65,3.00,1,1),(78,100033,2,9,38.00,0.00,417,5.00,1,1),(79,100033,13,9,5.00,0.00,20,2.20,2,1),(80,100033,13,10,5.00,0.00,10,0.85,1,1),(81,100033,1,10,4.80,1.00,21,1.10,1,1),(82,100033,12,11,25.00,0.00,450,28.65,6,1),(83,100033,4,11,13.60,1.50,140,5.00,1,1),(84,100033,1,11,4.75,0.00,17,1.85,1,1),(85,100033,13,12,5.00,0.00,20,1.95,2,1),(86,100033,1,12,11.20,0.00,72,3.45,1,1),(87,100033,22,13,92.00,0.00,457,5.00,1,1),(88,100033,2,14,38.00,0.00,417,10.00,1,1),(89,100033,4,14,13.60,1.50,140,5.00,1,1),(90,100033,13,15,5.00,0.00,10,0.85,1,1),(91,100033,2,16,38.00,0.00,417,9.00,1,1),(92,100033,1,16,20.60,1.50,170,10.30,1,1),(93,100034,12,11,25.00,0.00,75,5.00,1,1),(94,100034,13,16,5.00,0.00,10,1.00,1,1),(95,100035,12,11,25.00,0.00,75,5.00,1,1),(96,100035,13,16,5.00,0.00,10,1.00,1,1),(97,100036,12,11,25.00,0.00,75,5.00,1,1),(98,100036,13,16,5.00,0.00,10,1.00,1,1),(99,100037,12,11,25.00,0.00,75,5.80,1,1),(100,100037,13,15,5.00,0.00,10,1.20,1,1),(101,100038,12,9,50.00,0.00,150,12.80,2,1),(102,100038,12,10,25.00,0.00,75,6.40,1,1),(103,100038,13,15,5.00,0.00,10,1.30,1,1),(104,100038,13,13,5.00,0.00,10,1.30,1,1),(105,100038,13,9,5.00,0.00,10,1.30,1,1),(106,100039,12,12,25.00,0.00,75,5.00,1,1),(107,100039,13,14,5.00,0.00,10,1.00,1,1),(108,100040,12,13,25.00,0.00,75,5.00,1,1),(109,100040,13,9,10.00,0.00,20,2.00,2,1),(110,100040,12,19,25.00,0.00,75,5.00,1,1),(111,100040,13,19,5.00,0.00,10,1.00,1,1),(112,100040,12,9,25.00,0.00,75,5.00,1,1),(113,100041,12,12,25.00,0.00,75,5.00,1,1),(114,100041,13,11,5.00,0.00,10,1.00,1,1),(115,100041,12,19,25.00,0.00,75,5.00,1,1),(116,100041,13,16,5.00,0.00,10,1.00,1,1),(117,100042,12,17,25.00,0.00,75,5.00,1,1),(118,100042,13,9,5.00,0.00,10,1.00,1,1),(119,100043,12,19,25.00,0.00,75,5.00,1,1),(120,100043,13,10,5.00,0.00,10,1.00,1,1),(121,100044,12,19,25.00,0.00,75,5.00,1,1),(122,100044,13,10,5.00,0.00,10,1.00,1,1),(123,100045,12,19,25.00,0.00,75,5.00,1,1),(124,100045,13,19,5.00,0.00,10,1.00,1,1),(125,100045,12,18,25.00,0.00,75,5.00,1,1),(126,100045,13,16,5.00,0.00,10,1.00,1,1),(127,100046,12,19,25.00,0.00,75,5.00,1,1),(128,100046,13,10,5.00,0.00,10,1.00,1,1),(129,100047,1,4,17.00,0.00,160,5.80,1,1),(130,100047,1,5,17.00,0.00,560,5.80,1,1),(131,100047,1,9,51.50,0.00,420,19.40,1,1),(132,100047,1,10,10.00,0.00,90,3.80,1,1),(133,100047,1,11,4.80,0.00,40,1.20,1,1),(134,100047,1,13,6.00,0.00,100,2.50,1,1),(135,100047,1,15,7.00,0.00,70,2.00,1,1),(136,100047,1,19,21.00,0.00,250,17.50,1,1),(137,100047,1,20,7.00,0.00,70,2.00,1,1),(138,100048,12,17,50.00,0.00,150,10.00,2,1),(139,100048,12,16,25.00,0.00,75,5.00,1,1),(140,100048,12,19,25.00,0.00,75,5.00,1,1),(141,100048,13,10,15.00,0.00,30,3.00,3,1),(142,100048,13,9,10.00,0.00,20,2.00,2,1),(143,100048,12,11,25.00,0.00,75,5.00,1,1),(144,100048,26,9,5.00,0.00,25,1.10,5,1),(145,100048,25,12,18.50,0.00,25,3.90,5,1),(146,100048,1,19,2.40,0.00,4,0.00,1,1),(147,100048,1,5,0.60,0.00,4,0.35,1,1),(148,100048,1,10,0.60,0.00,4,0.35,1,1),(149,100049,1,16,36.00,0.00,196,17.20,1,1),(150,100050,1,22,12.90,0.00,129,6.10,1,1),(151,100050,1,21,5.53,0.00,44,2.70,1,1),(152,100050,1,17,8.55,0.00,47,4.20,1,1),(153,100051,12,11,25.00,0.00,75,4.60,1,1),(154,100051,13,10,5.00,0.00,10,1.00,1,1),(155,100052,5,19,3.00,0.00,18,1.40,1,1),(156,100052,6,19,17.80,3.00,51,3.00,1,1),(157,100052,9,19,10.00,0.00,30,0.60,2,1),(158,100053,12,17,25.00,0.00,75,5.00,1,1),(159,100053,13,22,10.00,0.00,20,1.00,2,1),(160,100053,12,13,25.00,0.00,75,5.00,1,1),(161,100054,1,22,190.00,34.00,567,20.00,1,1),(162,100054,1,16,108.15,3.00,164,10.00,1,1),(163,100054,1,16,187.26,7.00,447,10.00,1,1),(164,100055,9,20,80.00,0.00,120,7.50,24,1),(165,100055,6,22,213.60,36.00,612,37.50,12,1),(166,100055,5,22,144.00,0.00,864,5.00,48,1),(167,100056,9,21,10.00,0.00,30,0.75,2,1),(168,100056,5,19,6.00,1.00,20,0.50,2,1),(169,100056,6,19,17.80,3.00,51,3.75,1,1),(170,100057,3,5,85.00,0.00,441,10.00,1,1),(171,100058,6,19,71.20,12.00,204,4.00,1,1),(172,100058,5,19,6.00,0.00,36,1.00,2,1),(173,100059,3,12,85.00,0.00,441,10.00,1,1),(174,100060,1,16,2.20,0.00,15,1.00,1,1),(175,100060,15,22,45.00,0.00,300,6.00,15,1),(176,100061,1,16,4.00,0.00,20,3.60,1,1),(177,100061,1,22,3.00,0.00,20,5.40,1,1),(178,100062,1,21,5.00,0.00,50,1.70,1,1),(179,100062,1,22,10.00,0.00,60,3.30,1,1),(180,100063,1,24,8.10,0.00,32,4.00,1,1),(181,100063,1,23,8.10,0.00,32,4.00,1,1),(182,100064,1,16,2.00,0.00,7,0.75,1,1),(183,100064,1,17,69.80,0.00,186,17.85,1,1),(184,100064,1,22,13.25,0.00,36,3.20,1,1),(185,100064,1,10,13.25,0.00,36,3.20,1,1),(186,100065,1,24,25.70,0.00,150,20.00,1,1),(187,100066,1,24,8.00,0.00,43,5.00,1,1),(188,100066,1,22,8.00,0.00,43,5.00,1,1),(189,100067,1,26,750.00,0.00,1032,75.54,1,1),(190,100067,1,27,750.00,0.00,1032,75.54,1,1),(191,100067,1,28,485.60,0.00,672,48.92,1,1),(192,100068,1,24,36.40,0.00,525,15.00,1,1),(193,100068,1,24,10.20,0.00,31,1.20,1,1),(194,100068,1,23,89.60,0.00,215,8.80,1,1),(195,100069,1,29,82.00,0.00,192,11.60,1,1),(196,100069,1,24,24.00,0.00,100,3.40,1,1),(197,100070,1,8,10.80,0.00,58,6.00,1,1),(198,100071,1,16,20.00,0.00,100,10.00,1,1),(199,100072,1,23,99.80,0.00,450,15.00,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=100073 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (100001,5001,10.00,2,'2016-12-02','Sold','2016-12-02','Eiffel Tower + Spiral Vase 2'),(100002,5002,3.00,1,'2016-10-10','Sold','2016-10-10','Kippah'),(100003,5003,2.00,1,'2016-11-05','Sold','2016-11-05','Card Roller'),(100004,5004,5.00,1,'2016-12-21','Sold','2016-12-21','Spiral Vase 2'),(100005,5005,6.00,1,'2016-12-11','Sold','2016-12-11','Latka do regalu'),(100006,5005,10.00,1,'2016-12-11','Sold','2016-12-11','Spiral Vase 2'),(100007,5006,15.00,3,'2016-12-21','Sold','2016-12-21','Spiral Vase 2 + 2x Spiral Vase 1'),(100008,5007,5.00,1,'2016-11-24','Sold','2016-11-24','Spiral Vase 2'),(100009,5009,5.00,1,'2016-12-06','Sold','2016-12-06','Spiral Vase 2'),(100010,5010,2.00,2,'2017-01-09','Sold','2017-01-09','1-slot Card Cover, Logo audi'),(100011,5011,37.80,71,'2016-12-18','Sold','2016-12-18',''),(100012,5012,4.00,5,'2016-12-29','Sold','2016-12-29','Card Case 2.0 Tesla Logo'),(100013,5013,0.30,1,'2016-12-30','Sold','2016-12-30','Guitar Pick'),(100014,5005,4.00,2,'2017-01-09','Sold','2017-01-09','Odroid C2 Case'),(100015,5007,4.50,2,'2017-01-09','Sold','2017-01-09','Raspberry Pi 3 Case With Raspberry Logo'),(100016,5014,10.50,6,'2017-01-11','Sold','2017-01-11','2x Raspberry Pi Zero'),(100017,5012,20.00,3,'2017-01-19','Sold','2017-01-19','Tesla Super Charger Mobile Charging Stand'),(100018,5015,5.00,1,'2017-01-19','Sold','2017-01-19','Spiral Vase 2'),(100019,5016,2.00,2,'2017-01-25','Sold','2017-01-25','1-Slot Card Case Logo Apple'),(100020,5017,4.00,5,'2017-03-13','Sold','2017-03-13','Card Case Ferrari + F1'),(100021,5018,2.00,2,'2017-03-22','Sold','2017-03-22','Cover for Panasonic Speakers'),(100022,5019,5.00,1,'2017-04-06','Sold','2017-04-06','HP-Stand'),(100023,5020,5.00,1,'2017-04-11','Sold','2017-04-11','Naraznik Skoda 120'),(100024,5021,35.00,2,'2017-04-18','Sold','2017-04-18','Torsen Differential'),(100025,5018,3.00,1,'2017-04-20','Sold','2017-04-20','Poistka do vysavaca'),(100026,5017,2.00,4,'2017-04-24','Sold','2017-04-24','T-Systems Figurky'),(100027,5022,10.00,2,'2017-05-04','Sold','2017-05-04','2x  Spiral Vase 2'),(100028,5024,10.00,1,'2017-05-26','Sold','2017-05-26','ASUS Doplnky'),(100029,5010,12.00,2,'2017-05-28','Sold','2017-05-28','2x Spiral Vase 3'),(100030,5023,5.00,5,'2017-05-31','Sold','2017-05-31','Card Case'),(100031,5025,10.00,2,'2017-05-28','Sold','2017-05-28','Key Hangers'),(100032,5026,17.45,11,'2017-07-04','Sold','2017-07-04','2x Card case, Marlboro + Kaufland, Marlboro Cigarette Case'),(100033,5027,103.70,28,'2017-07-16','Sold','2017-07-16','VW Zraz 2017'),(100034,5008,6.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger'),(100035,5008,6.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger'),(100036,5031,6.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger'),(100037,5032,7.00,2,'2017-07-19','Sold','2017-07-19','Key Hanger'),(100038,5036,23.10,6,'2017-07-19','Sold','2017-07-19','3x Key Hanger'),(100039,5034,6.00,2,'2017-07-23','Sold','2017-07-23','Key hanger'),(100040,5028,18.00,6,'2017-07-23','Sold','2017-07-23','3x Key Hanger'),(100041,5029,12.00,4,'2017-07-23','Sold','2017-07-23','2x Key Hanger'),(100042,5037,6.00,2,'2017-07-23','Sold','2017-07-23','Key Hanger'),(100043,5030,6.00,2,'2017-07-23','Sold','2017-07-23','Key Hanger'),(100044,5033,6.00,2,'2017-07-23','Sold','2017-07-23','Key Hanger'),(100045,5038,12.00,4,'2017-07-30','Sold','2017-07-30','2x Key Hanger'),(100046,5035,6.00,2,'2017-07-30','Sold','2017-07-30','Key Hanger'),(100047,5039,60.00,9,'2018-09-23','Sold','2018-09-23','Japanese Customs + Bella Art Keychains'),(100048,5040,35.70,23,'2017-09-09','Sold','2017-09-09','Key Hangers and other VW stuff'),(100049,5041,17.20,1,'2017-09-09','Sold','2017-09-09','Krytky na kolesa'),(100050,5043,13.00,3,'2017-10-29','Sold','2017-10-29','Cleavland Indians'),(100051,5047,5.60,2,'2017-11-13','Sold','2017-11-13','Key Hanger'),(100052,5049,5.00,4,'2017-11-16','Sold','2017-11-16','Razer 2-slot Card case '),(100053,5044,11.00,4,'2017-11-27','Sold','2017-11-27','2x Key Hanger Vans'),(100054,5048,40.00,3,'2017-12-14','Sold','2017-12-14','Star Lord Mask and Gun'),(100055,5050,50.00,84,'2017-12-14','Sold','2017-12-14','12x Card Case'),(100056,5051,5.00,5,'2017-12-14','Sold','2017-12-14','CardCover Horder'),(100057,5045,10.00,1,'2017-12-14','Sold','2017-12-14','Spiral Vase 2'),(100058,5052,5.00,3,'2017-12-14','Sold','2017-12-14','2 slot card case'),(100059,5045,10.00,1,'2017-12-14','Sold','2017-12-14','Spiral Vase'),(100060,5011,7.00,16,'2017-12-14','Sold','2017-12-14','Triquetra and snowflakes'),(100061,5029,9.00,2,'2017-12-14','Sold','2017-12-14','Cotrans keycain'),(100062,5053,5.00,2,'2017-01-18','Sold','2017-01-18','AHK Sokolany'),(100063,5054,8.00,2,'2018-01-28','Sold','2018-01-28','Subaru outback IV'),(100064,5046,25.00,4,'2018-01-28','Sold','2018-01-28','Chinese Riddle'),(100065,5055,20.00,1,'2017-01-17','Sold','2017-01-17','Chopper Style Sablny'),(100066,5054,10.00,2,'2017-02-06','Sold','2017-02-06','Subaru outback IV'),(100067,5056,200.00,3,'2018-05-04','Sold','2018-05-04','Telekom IT GSO Napis'),(100068,5057,25.00,3,'2018-06-29','Sold','2018-06-29','Eiffel Tower 0.35mm + Versace picture'),(100069,5043,15.00,2,'2018-08-02','Sold','2018-08-02','CAT Plate'),(100070,5019,6.00,1,'2018-03-24','Sold','2018-03-24','Mobile phone stand'),(100071,5041,10.00,1,'2018-04-04','Sold','2018-04-04','Dlasie 2 krytky na kolesa'),(100072,5058,15.00,1,'2018-05-30','Sold','2018-05-30','Sedadlo do auta');
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

-- Dump completed on 2018-10-01 16:15:31
