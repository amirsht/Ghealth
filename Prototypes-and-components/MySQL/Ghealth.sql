-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: ghealth
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Temporary view structure for view `amir`
--

DROP TABLE IF EXISTS `amir`;
/*!50001 DROP VIEW IF EXISTS `amir`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `amir` AS SELECT 
 1 AS `apsID`,
 1 AS `apsPtID`,
 1 AS `apsDate`,
 1 AS `apsTime`,
 1 AS `apsCreateDate`,
 1 AS `apsCreateTime`,
 1 AS `apsStatus`,
 1 AS `apsDocID`,
 1 AS `apsSummery`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `appointmentsettings`
--

DROP TABLE IF EXISTS `appointmentsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointmentsettings` (
  `apsID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `apsPtID` varchar(45) DEFAULT NULL,
  `apsDate` date DEFAULT NULL,
  `apsTime` time DEFAULT NULL,
  `apsCreateDate` date DEFAULT NULL,
  `apsCreateTime` time DEFAULT NULL,
  `apsStatus` varchar(45) DEFAULT NULL,
  `apsDocID` varchar(45) DEFAULT NULL,
  `apsSummery` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`apsID`),
  KEY `apsPtID_idx` (`apsPtID`),
  KEY `apsDocID_idx` (`apsDocID`),
  CONSTRAINT `apsDocID` FOREIGN KEY (`apsDocID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `apsPtID` FOREIGN KEY (`apsPtID`) REFERENCES `patient` (`ptID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointmentsettings`
--

LOCK TABLES `appointmentsettings` WRITE;
/*!40000 ALTER TABLE `appointmentsettings` DISABLE KEYS */;
INSERT INTO `appointmentsettings` VALUES (1,'4444','2016-07-15','10:30:00','2016-06-15','14:01:43','ARRIVED','5001',NULL),(2,'4444','2016-08-15','11:00:00','2016-06-15','14:01:43','ARRIVED','5004',NULL),(3,'4444','2016-09-15','11:30:00','2016-06-15','14:01:43','ARRIVED','5005',NULL),(4,'4000','2016-07-15','12:00:00','2016-06-15','14:01:43','ARRIVED','5002',NULL),(5,'4000','2016-08-15','12:30:00','2016-06-15','14:01:43','ARRIVED','5003',NULL),(6,'4003','2016-07-15','13:00:00','2016-06-15','14:01:43','ARRIVED','5002',NULL),(7,'4003','2016-08-15','13:30:00','2016-06-15','14:01:43','ARRIVED','5003',NULL),(8,'4444','2016-08-15','08:00:00','2016-08-15','15:30:00','ARRIVED','5001',NULL),(9,'4444','2016-07-15','08:00:00','2016-07-15','08:00:00','ARRIVED','5002',NULL),(10,'4444','2016-06-15','13:00:00','2016-07-15','08:00:00','ARRIVED','5004',NULL),(11,'4444','2016-09-15','13:00:00','2016-07-15','08:00:00','CANCELED','5011',NULL),(12,'4444','2016-06-24','09:30:00','2016-06-03','00:18:56','CANCELED','5000',NULL),(13,'4444','2016-06-17','14:30:00','2016-06-03','00:27:19','CANCELED','5014',NULL),(14,'4444','2016-06-16','09:30:00','2016-06-04','15:13:31','CANCELED','5010',NULL),(15,'4444','2016-06-06','10:30:00','2016-06-04','15:56:46','CANCELED','5009',NULL),(16,'4444','2016-06-05','10:00:00','2016-06-04','15:57:49','CANCELED','5014',NULL),(17,'4444','2016-06-22','11:00:00','2016-06-06','14:59:25','SCHEDUELD','5000',NULL),(18,'4444','2016-06-30','16:00:00','2016-06-06','15:00:20','SCHEDUELD','5006',NULL);
/*!40000 ALTER TABLE `appointmentsettings` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ghealth`.`appointmentsettings_AFTER_INSERT` AFTER INSERT ON `appointmentsettings` FOR EACH ROW
BEGIN

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinic` (
  `cID` int(11) NOT NULL,
  `cName` varchar(45) DEFAULT NULL,
  `cLocation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES (1,'Asuta','Haifa'),(2,'Gays','Tel-Aviv'),(3,'Dos','Jerusalem'),(4,'Ars','Eilat'),(5,'Assaf Harofe','Acko'),(6,'CCCP','Karmiel'),(7,'Killers','Aza');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `dID` varchar(45) NOT NULL,
  `dSpeciality` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dID`),
  CONSTRAINT `dcid` FOREIGN KEY (`dID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('5000','Orthopedist'),('5001','Orthopedist'),('5002','Gynecologist'),('5003','Orthopedist'),('5004','Orthopedist'),('5005','Orthopedist'),('5006','Gynecologist'),('5007','Eyes'),('5008','Cardio'),('5009','Neurologist'),('5010','Cardio'),('5011','Neurologist'),('5012','Cardio'),('5013','Gynecologist'),('5014','Gynecologist'),('5015','Eyes');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labref`
--

DROP TABLE IF EXISTS `labref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labref` (
  `LabRefID` int(11) NOT NULL,
  `LabFilePath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`LabRefID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labref`
--

LOCK TABLES `labref` WRITE;
/*!40000 ALTER TABLE `labref` DISABLE KEYS */;
/*!40000 ALTER TABLE `labref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `ptID` varchar(45) NOT NULL,
  `ptFirstName` varchar(45) DEFAULT NULL,
  `ptLastName` varchar(45) DEFAULT NULL,
  `ptEmail` varchar(45) DEFAULT NULL,
  `ptPhone` varchar(45) DEFAULT NULL,
  `ptPrivateClinic` varchar(45) DEFAULT NULL,
  `ptDoctorID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ptID`),
  KEY `PersonalDoctorID_idx` (`ptDoctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('4000','Adrienne','Bowers','fa','054-4665760','Klalit','1'),('4001','Irvin','Poole','as','054-1235760','Klalit','1'),('4002','Velma','Foster','adf','054-4664444','Klalit','1'),('4003','Cristina','Rodgers','sfASF','054-4665333','Klalit','1'),('4004','Elvira','Boone','temp@gmail.com','054-4665760','Maccabi','1'),('4005','Ori','Arel','temp@gmail.com','054-4611111','Klalit','1'),('4006','Amir','Sht','amamam@yahoo.com','054-3344123','Meuhedet','1'),('4007','Moshe','Sabag','asfa','055-5544336','Maccabi','1'),('4008','Grace','Mckinney','Test@braude.ac.il','052-4222456','Meuhedet','1'),('4444','Kety','Rina','Kety@gmail.com','050-333241','Maccabi','1');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaldoctor`
--

DROP TABLE IF EXISTS `personaldoctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaldoctor` (
  `PersonalDoctorID` varchar(45) NOT NULL,
  `PersonalDoctorName` varchar(45) DEFAULT NULL,
  `PersonalDoctorEmail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PersonalDoctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaldoctor`
--

LOCK TABLES `personaldoctor` WRITE;
/*!40000 ALTER TABLE `personaldoctor` DISABLE KEYS */;
INSERT INTO `personaldoctor` VALUES ('1','Muhamad Ali','blabla@gmail.com'),('2','Yaser Arafat','asdfas@gmail.com');
/*!40000 ALTER TABLE `personaldoctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privateclniic`
--

DROP TABLE IF EXISTS `privateclniic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privateclniic` (
  `PrivateClinicName` varchar(45) NOT NULL,
  `PrivateClinicEmail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PrivateClinicName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privateclniic`
--

LOCK TABLES `privateclniic` WRITE;
/*!40000 ALTER TABLE `privateclniic` DISABLE KEYS */;
INSERT INTO `privateclniic` VALUES ('Klalit','Klalit@gmail.com'),('Maccabi','Maccabi@gmail.com'),('Meuhedet','Meuhedet@gmail.com');
/*!40000 ALTER TABLE `privateclniic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uID` varchar(45) NOT NULL,
  `uPassword` varchar(45) DEFAULT NULL,
  `uFirstName` varchar(45) DEFAULT NULL,
  `uLastName` varchar(45) DEFAULT NULL,
  `uEmail` varchar(45) DEFAULT NULL,
  `ucID` int(11) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uID`),
  KEY `cID_idx` (`ucID`),
  CONSTRAINT `cID` FOREIGN KEY (`ucID`) REFERENCES `clinic` (`cID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('4000','1234','Amir','Sht','amir@gm.com',1,'CUSTOMER_SERVICE'),('5000','1234','Moshe','Pinto','Pinto@gmail.com',1,'DOCTOR'),('5001','1234','Eran','Tamir','Eran.Tamir@walla.co.il',2,'DOCTOR'),('5002','1234','Yossi','Leitner','Yossi.Leitner@walla.co.il',3,'DOCTOR'),('5003','1234','Hossam','Ra\'d','Hossam.Rara@walla.com',4,'DOCTOR'),('5004','1234','Viktor','Klein','ViktorVikKlein@yahoo.com',5,'DOCTOR'),('5005','1234','Slava','Babayev','SlavSlavaBBB@gmail.com',6,'DOCTOR'),('5006','1234','Vitaliy','Gitman','Vital@iol.com',7,'DOCTOR'),('5007','1234','Alexei','Yad-Shalom','Alexei@yahoo.coom',1,'DOCTOR'),('5008','1234','Vladimir','Ruhman','Vladimirruhruh@yahoo.com',2,'DOCTOR'),('5009','1234','Guy','Reznik','guyrez@walla.com',3,'DOCTOR'),('5010','1234','Guri','Alfi','AlfiGurgur@gmail.com',4,'DOCTOR'),('5011','1234','Ovadia','Cohen','ovad@gmail.com',5,'DOCTOR'),('5012','1234','Ilya','Vered','ilyaver@gmail.com',6,'DOCTOR'),('5013','1234','Shasha','Stoyko','Stoykoshasha@gmail.com',7,'DOCTOR'),('5014','1234','Sveta','Aronov','SvetaRonova@gmail.com',5,'DOCTOR'),('5015','1234','Irena','Glushko','Irenaglushko@gmail.com',2,'DOCTOR');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `amir`
--

/*!50001 DROP VIEW IF EXISTS `amir`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `amir` AS select `a`.`apsID` AS `apsID`,`a`.`apsPtID` AS `apsPtID`,`a`.`apsDate` AS `apsDate`,`a`.`apsTime` AS `apsTime`,`a`.`apsCreateDate` AS `apsCreateDate`,`a`.`apsCreateTime` AS `apsCreateTime`,`a`.`apsStatus` AS `apsStatus`,`a`.`apsDocID` AS `apsDocID`,`a`.`apsSummery` AS `apsSummery` from `appointmentsettings` `a` where ((`a`.`apsPtID` = '4444') and (`a`.`apsStatus` = 'ARRIVED')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-06 15:01:57
