CREATE DATABASE  IF NOT EXISTS `ghealth` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ghealth`;
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
  `apsStartTime` time DEFAULT NULL,
  PRIMARY KEY (`apsID`),
  KEY `apsPtID_idx` (`apsPtID`),
  KEY `apsDocID_idx` (`apsDocID`),
  CONSTRAINT `apsDocID` FOREIGN KEY (`apsDocID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `apsPtID` FOREIGN KEY (`apsPtID`) REFERENCES `patient` (`ptID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointmentsettings`
--

LOCK TABLES `appointmentsettings` WRITE;
/*!40000 ALTER TABLE `appointmentsettings` DISABLE KEYS */;
INSERT INTO `appointmentsettings` VALUES (1,'4000','2016-05-20','10:30:00','2016-05-15','14:01:43','ARRIVED','5001','hola barosh','10:32:12'),(2,'4001','2016-05-20','11:00:00','2016-05-15','14:17:24','CANCELED','5004','',NULL),(3,'4002','2016-05-21','11:30:00','2016-05-15','15:11:42','ARRIVED','5005','adsf','11:37:00'),(4,'4003','2016-05-21','12:00:00','2016-05-15','15:19:12','ARRIVED','5002','sdav','12:01:00'),(5,'4004','2016-05-22','12:30:00','2016-05-16','14:01:43','ARRIVED','5003','wef','12:30:12'),(6,'4005','2016-05-22','13:00:00','2016-05-16','14:17:24','ARRIVED','5002','dsavsd','13:12:23'),(7,'4006','2016-05-23','13:30:00','2016-05-16','14:01:43','ARRIVED','5003','adsf','13:32:00'),(8,'4011','2016-05-23','14:00:00','2016-05-16','15:19:12','CANCELED','5009','',NULL),(9,'4012','2016-05-21','14:30:00','2016-05-17','14:01:43','ARRIVED','5009','hola barosh','14:36:00'),(10,'4013','2016-05-21','15:00:00','2016-05-17','14:17:24','ARRIVED','5008','hola barosh','15:02:50'),(11,'4014','2016-05-22','15:30:00','2016-05-17','15:11:42','ARRIVED','5011','hola barosh','15:37:00'),(12,'4015','2016-05-22','16:00:00','2016-05-17','15:19:12','CANCELED','5011','',NULL),(13,'4016','2016-05-23','16:30:00','2016-05-17','16:10:12','ARRIVED','5015','hola barosh','16:31:50'),(14,'4018','2016-05-23','17:00:00','2016-05-18','14:01:43','ARRIVED','5004','hola barosh','17:10:00'),(16,'4019','2016-05-24','18:00:00','2016-05-18','15:11:42','ARRIVED','5015','hola barosh','18:00:12'),(17,'4020','2016-05-22','18:30:00','2016-05-18','15:19:12','CANCELED','5008',NULL,NULL),(18,'4007','2016-05-22','10:30:00','2016-05-18','14:01:43','ARRIVED','5001','hola barosh','10:32:07'),(19,'4008','2016-05-23','11:00:00','2016-05-19','14:17:24','CANCELED','5013',NULL,NULL),(20,'4009','2016-05-23','11:30:00','2016-05-19','15:11:42','ARRIVED','5014','hola barosh','11:37:12'),(21,'4010','2016-05-24','12:00:00','2016-05-19','15:19:12','ARRIVED','5001','hola barosh','12:01:05'),(22,'4000','2016-05-24','12:30:00','2016-05-19','16:49:52','CANCELED','5001',NULL,NULL),(23,'4001','2016-05-25','13:00:00','2016-05-20','14:01:43','ARRIVED','5004','hola barosh','13:03:20'),(24,'4002','2016-05-25','13:30:00','2016-05-20','14:17:24','ARRIVED','5005','hola barosh','13:31:03'),(25,'4003','2016-05-23','14:00:00','2016-05-20','15:11:42','ARRIVED','5002','hola barosh','14:06:00'),(26,'4004','2016-05-23','14:30:00','2016-05-20','15:19:12','CANCELED','5003','',NULL),(27,'4005','2016-05-24','15:00:00','2016-05-21','14:01:43','ARRIVED','5002','hola barosh','15:02:07'),(28,'4006','2016-05-24','15:30:00','2016-05-21','14:17:24','ARRIVED','5003','hola barosh','15:38:00'),(29,'4007','2016-05-25','16:00:00','2016-05-21','15:11:42','CANCELED','5001','',NULL),(30,'4008','2016-05-25','16:30:00','2016-05-21','15:19:12','ARRIVED','5013','hola barosh','16:30:01'),(31,'4009','2016-05-26','17:00:00','2016-05-22','14:01:43','ARRIVED','5014','hola barosh','17:09:00'),(32,'4010','2016-05-26','17:30:00','2016-05-22','14:17:24','CANCELED','5001',NULL,NULL),(33,'4011','2016-05-24','18:00:00','2016-05-22','15:11:42','ARRIVED','5009','hola barosh','18:20:00'),(34,'4012','2016-05-24','18:30:00','2016-05-22','15:19:12','ARRIVED','5009','hola barosh','18:32:21'),(35,'4013','2016-05-25','10:00:00','2016-05-23','14:01:43','ARRIVED','5008','hola barosh','10:01:20'),(36,'4015','2016-05-25','10:30:00','2016-05-23','14:17:24','CANCELED','5011',NULL,NULL),(37,'4014','2016-05-26','11:00:00','2016-05-23','15:11:42','ARRIVED','5011','hola barosh','11:05:06'),(38,'4016','2016-05-26','11:30:00','2016-05-23','15:19:12','ARRIVED','5015','hola barosh','11:31:07'),(39,'4017','2016-05-27','12:00:00','2016-05-23','14:01:43','CANCELED','5008','',NULL),(40,'4018','2016-05-27','12:30:00','2016-05-24','14:17:24','ARRIVED','5004','hola barosh','12:32:09'),(41,'4019','2016-06-25','13:00:00','2016-05-24','15:11:42','CANCELED','5015',NULL,NULL),(42,'4020','2016-06-25','13:30:00','2016-05-24','15:19:12','ARRIVED','5008','hola barosh','13:36:10'),(43,'4000','2016-06-15','14:00:00','2016-05-27','14:01:43','ARRIVED','5001','hola barosh','14:03:10'),(44,'4001','2016-06-15','14:30:00','2016-05-27','14:17:24','ARRIVED','5004','hola barosh','14:35:02'),(45,'4002','2016-06-17','15:00:00','2016-05-27','15:11:42','SCHEDUELD','5005',NULL,NULL),(46,'4003','2016-06-18','15:30:00','2016-05-27','15:19:12','SCHEDUELD','5002',NULL,NULL),(47,'4004','2016-06-16','16:00:00','2016-06-01','14:01:43','SCHEDUELD','5003',NULL,NULL),(48,'4005','2016-06-21','16:30:00','2016-06-01','14:17:24','SCHEDUELD','5002',NULL,NULL),(49,'4006','2016-07-03','17:00:00','2016-06-02','15:11:42','SCHEDUELD','5003',NULL,NULL),(50,'4007','2016-07-10','17:30:00','2016-06-02','15:19:12','SCHEDUELD','5001',NULL,NULL),(61,'4017','2016-05-24','17:30:00','2016-05-18','14:17:24','ARRIVED','5008','hola barosh','17:37:20'),(300,'4009','2016-05-23','11:30:00','2016-05-19','15:11:42','ARRIVED','5014','hola barosh','11:37:12'),(310,'4010','2016-05-24','12:00:00','2016-05-19','15:19:12','ARRIVED','5001','hola barosh','12:01:05'),(320,'4000','2016-05-24','12:30:00','2016-05-19','16:49:52','CANCELED','5001',NULL,NULL),(330,'4001','2016-05-25','13:00:00','2016-05-20','14:01:43','ARRIVED','5004','hola barosh','13:03:20'),(340,'4002','2016-05-25','13:30:00','2016-05-20','14:17:24','ARRIVED','5005','hola barosh','13:31:03'),(350,'4003','2016-05-23','14:00:00','2016-05-20','15:11:42','ARRIVED','5002','hola barosh','14:06:00'),(360,'4004','2016-05-23','14:30:00','2016-05-20','15:19:12','CANCELED','5003','',NULL),(370,'4005','2016-05-24','15:00:00','2016-05-21','14:01:43','ARRIVED','5002','hola barosh','15:02:07'),(380,'4006','2016-05-24','15:30:00','2016-05-21','14:17:24','ARRIVED','5003','hola barosh','15:38:00'),(390,'4007','2016-05-25','16:00:00','2016-05-21','15:11:42','CANCELED','5001','',NULL),(400,'4008','2016-05-25','16:30:00','2016-05-21','15:19:12','ARRIVED','5013','hola barosh','16:30:01'),(410,'4009','2016-05-26','17:00:00','2016-05-22','14:01:43','ARRIVED','5014','hola barosh','17:09:00'),(420,'4010','2016-05-26','17:30:00','2016-05-22','14:17:24','CANCELED','5001',NULL,NULL),(430,'4011','2016-05-24','18:00:00','2016-05-22','15:11:42','ARRIVED','5009','hola barosh','18:20:00'),(440,'4012','2016-05-24','18:30:00','2016-05-22','15:19:12','ARRIVED','5009','hola barosh','18:32:21'),(450,'4013','2016-05-25','10:00:00','2016-05-23','14:01:43','ARRIVED','5008','hola barosh','10:01:20'),(460,'4015','2016-05-25','10:30:00','2016-05-23','14:17:24','CANCELED','5011',NULL,NULL),(470,'4014','2016-05-26','11:00:00','2016-05-23','15:11:42','ARRIVED','5011','hola barosh','11:05:06'),(480,'4016','2016-05-26','11:30:00','2016-05-23','15:19:12','ARRIVED','5015','hola barosh','11:31:07'),(490,'4017','2016-05-27','12:00:00','2016-05-23','14:01:43','CANCELED','5008','',NULL),(500,'4018','2016-05-27','12:30:00','2016-05-24','14:17:24','ARRIVED','5004','hola barosh','12:32:09'),(510,'4019','2016-06-25','13:00:00','2016-05-24','15:11:42','CANCELED','5015',NULL,NULL),(520,'4020','2016-06-25','13:30:00','2016-05-24','15:19:12','ARRIVED','5008','hola barosh','13:36:10'),(530,'4000','2016-06-15','14:00:00','2016-05-27','14:01:43','NOSHOW','5001','hola barosh','14:03:10'),(540,'4001','2016-06-15','14:30:00','2016-05-27','14:17:24','ARRIVED','5004','hola barosh','14:35:02'),(550,'4002','2016-06-17','15:00:00','2016-05-27','15:11:42','SCHEDUELD','5005',NULL,NULL),(560,'4003','2016-06-18','15:30:00','2016-05-27','15:19:12','SCHEDUELD','5002',NULL,NULL),(570,'4004','2016-06-16','16:00:00','2016-06-01','14:01:43','SCHEDUELD','5003',NULL,NULL),(580,'4005','2016-06-21','16:30:00','2016-06-01','14:17:24','SCHEDUELD','5002',NULL,NULL),(590,'4006','2016-07-03','17:00:00','2016-06-02','15:11:42','SCHEDUELD','5003',NULL,NULL),(600,'4007','2016-07-10','17:30:00','2016-06-02','15:19:12','SCHEDUELD','5001',NULL,NULL),(601,'4007','2016-06-06','08:30:00','2016-05-27','15:19:12','ARRIVED','5001',NULL,'17:00:00'),(602,'4008','2016-06-06','08:00:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'17:00:00'),(603,'4009','2016-06-06','09:00:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'17:00:00'),(604,'4010','2016-06-06','09:30:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'17:00:00'),(605,'4011','2016-06-06','10:00:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'17:00:00'),(607,'4012','2016-06-05','10:00:00','2016-05-27','15:19:12','ARRIVED','5001',NULL,'17:00:00'),(608,'3393055','2016-06-13','10:00:00','2016-06-12','13:46:37','SCHEDUELD','5008',NULL,NULL);
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
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `calendar_date` date NOT NULL,
  PRIMARY KEY (`calendar_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES ('2016-06-05'),('2016-06-06'),('2016-06-07'),('2016-06-08'),('2016-06-09'),('2016-06-10'),('2016-06-11'),('2016-06-12');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `dID` FOREIGN KEY (`dID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE
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
-- Table structure for table `labsettings`
--

DROP TABLE IF EXISTS `labsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labsettings` (
  `labID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `labPtID` varchar(45) DEFAULT NULL,
  `labCreateDate` date DEFAULT NULL,
  `labCreateTime` time DEFAULT NULL,
  `labStatus` varchar(45) DEFAULT NULL,
  `labDocID` varchar(45) DEFAULT NULL,
  `labworkerID` varchar(45) DEFAULT NULL,
  `labDocReq` varchar(500) DEFAULT NULL,
  `labWorkerSummery` varchar(500) DEFAULT NULL,
  `labPhotoPath` varchar(150) DEFAULT 'NO FILE',
  PRIMARY KEY (`labID`),
  KEY `labPtID_idx` (`labPtID`),
  KEY `labDocID` (`labDocID`),
  CONSTRAINT `labDocID` FOREIGN KEY (`labDocID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `labPtID` FOREIGN KEY (`labPtID`) REFERENCES `patient` (`ptID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labsettings`
--

LOCK TABLES `labsettings` WRITE;
/*!40000 ALTER TABLE `labsettings` DISABLE KEYS */;
INSERT INTO `labsettings` VALUES (1,'4009','2016-06-15','14:01:43','ARRIVED','5004','6000','1 Please kill this patient!','baelkfalsk','src//Server//files//1.jpg'),(2,'4009','2016-07-15','14:01:43','ARRIVED','5003','6000','2Please kill this patient!','Add your lab record here...asdgasdgadsgadsvadsvdsa\n\nsadf\ndsaf\n\ndgsa\na\nsdg\nadsg','src//Server//files//2.jpg'),(3,'4009','2016-06-20','14:01:43','ARRIVED','5005','6000','3 Please kill this patient!','Add your lasdfads\n\nfadsfadsfa asdfa\n\n\nsdfasdfasdfsad','src//Server//files//3.png'),(4,'4009','2016-05-30','14:01:43','ARRIVED','5002','6000','4 Please kill this patient!','Add your lasdfsad\nafsadsf\nasf','src//Server//files//4.jpg'),(5,'4009','2016-09-30','14:01:43','ARRIVED','5002','6000','5 Please kill this patient!','Blasdjgfaldskjfadlksjfadslkfj\n\nsadlfadkslf\n\n\nds\nagdsa','src//Server//files//5.jpg'),(6,'4009','2016-06-08','18:33:10','SCHEDUELD','5000',NULL,'adsfadsf\n\n\n\nadsf\n\nsf\nad\nsfad\n',NULL,'NO FILE'),(7,'4009','2016-06-08','18:36:20','ARRIVED','5000','6000','asdfsadfsdf\n\nasdf\n\nadsf\n\nsdf\n\ndsfa\na\ndsf\n\naf','asdfdsafads \n\n\nasdgk\nsadkg\nsgdka\nkag\nsd','src//Server//files//7.jpg');
/*!40000 ALTER TABLE `labsettings` ENABLE KEYS */;
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
  `ptIsRegistered` varchar(45) DEFAULT NULL,
  `ptLeaveDate` date DEFAULT NULL,
  PRIMARY KEY (`ptID`),
  KEY `PersonalDoctorID_idx` (`ptDoctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('3393055','hava','lotringer','savta@gmail.com','0544876542','Klalit','2','IS_REG',NULL),('4000','gefen','kaplinsky','kgefen@gmail.com','054-4665760','Klalit','1','IS_REG',NULL),('4001','yarden','kaplinsky','kapyarden@gmail.com','054-1235760','Klalit','2','NOT_REG','2016-06-11'),('4002','hila','gazit','ghila@walla.co.il','054-4664444','Klalit','1','IS_REG',NULL),('4003','doron','lev','levdoron@yahoo.com','054-4665333','Klalit','1','IS_REG',NULL),('4004','din','kanuk','dinkan@walla.com','054-4665760','Maccabi','1','IS_REG',NULL),('4005','Ori','erel','temp@gmail.com','054-4611111','Klalit','2','IS_REG',NULL),('4006','Amir','Sht','amamam@yahoo.com','054-3344123','Meuhedet','1','IS_REG',NULL),('4007','dan','shalmon','shalmon23@gmail.com','055-5544336','Maccabi','2','IS_REG',NULL),('4008','mor','yahalom','yahalomor@gmail.com','052-4222456','Meuhedet','1','IS_REG',NULL),('4009','noga','levi','noglev@hotmail.com','050-3332415','Maccabi','1','IS_REG',NULL),('4010','asahel','bar-ilan','godmade@hotmail.com','052-2444423','Meuhedet','2','IS_REG',NULL),('4011','merav','froim','ffmer@walla.com','054-3544232','Maccabi','2','IS_REG',NULL),('4012','nir ','shalmon','shalmnir@gmail.com','055-2442324','Maccabi','2','IS_REG',NULL),('4013','guy','sharon','guy.sharon@outlook.com','+987-255442324','Klalit','2','IS_REG',NULL),('4014','guy','leibovits','leibo69@yahoo.com','052-47654232','Klalit','1','IS_REG',NULL),('4015','alon','shohat','ashohet@walla.com','052-5732897','Maccabi','1','IS_REG',NULL),('4016','koby','toget','togetk@gmail.com','055-5524321','Klalit','1','IS_REG',NULL),('4017','roni','erel','minicatch@walla.co.il','052-2441155','Klalit','1','IS_REG',NULL),('4018','noy','lotringer','lotr.noy@outlook.com','054-4778923','Meuhedet','2','IS_REG',NULL),('4019','dina','belsky','belskydina@walla.co.il','055-2345412','Meuhedet','2','IS_REG',NULL),('4020','guy','levin','tattoosguy@walla.com','055-2314512','Meuhedet','2','IS_REG',NULL);
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
INSERT INTO `user` VALUES ('4000','1234','Amir','Sht','amir@gm.com',1,'CUSTOMER_SERVICE'),('5000','1234','Moshe','Pinto','Pinto@gmail.com',1,'DOCTOR'),('5001','1234','Eran','Tamir','Eran.Tamir@walla.co.il',2,'DOCTOR'),('5002','1234','Yossi','Leitner','Yossi.Leitner@walla.co.il',3,'DOCTOR'),('5003','1234','Hossam','Ra\'d','Hossam.Rara@walla.com',4,'DOCTOR'),('5004','1234','Viktor','Klein','ViktorVikKlein@yahoo.com',5,'DOCTOR'),('5005','1234','Slava','Babayev','SlavSlavaBBB@gmail.com',6,'DOCTOR'),('5006','1234','Vitaliy','Gitman','Vital@iol.com',7,'DOCTOR'),('5007','1234','Alexei','Yad-Shalom','Alexei@yahoo.coom',1,'DOCTOR'),('5008','1234','Vladimir','Ruhman','Vladimirruhruh@yahoo.com',2,'DOCTOR'),('5009','1234','Guy','Reznik','guyrez@walla.com',3,'DOCTOR'),('5010','1234','Guri','Alfi','AlfiGurgur@gmail.com',4,'DOCTOR'),('5011','1234','Ovadia','Cohen','ovad@gmail.com',5,'DOCTOR'),('5012','1234','Ilya','Vered','ilyaver@gmail.com',6,'DOCTOR'),('5013','1234','Shasha','Stoyko','Stoykoshasha@gmail.com',7,'DOCTOR'),('5014','1234','Sveta','Aronov','SvetaRonova@gmail.com',5,'DOCTOR'),('5015','1234','Irena','Glushko','Irenaglushko@gmail.com',2,'DOCTOR'),('6000','1234','Lauren','Smith','SmithMail@gmail.com',6,'LAB_WORKER'),('6001','1234','Isabelle','Olaru','OlaruMail@gmail.com',5,'LAB_WORKER'),('6002','1234','Florina','Tilea','Tilea.Not@gmail.com',4,'LAB_WORKER'),('7001','1234','Yoram','Mendel','Yoram2cool@gmail.com',1,'CLINIC_MANAGER'),('7002','1234','Avner','Fisher','Avner@gmail.com',2,'CLINIC_MANAGER'),('7003','1234','Matan','Boochbinder','Matan888@gmail.com',3,'CLINIC_MANAGER'),('7004','1234','Yoav','Izner','shoomBatzal@gmail.com',4,'CLINIC_MANAGER'),('7005','1234','Dan','Brochman','Sunshine@gmail.com',5,'CLINIC_MANAGER'),('7006','1234','Amos','Dichter','FlowerGirl@gmail.com',6,'CLINIC_MANAGER'),('7007','1234','Gidon','Dorfman','Loveyourself@gmail.com',7,'CLINIC_MANAGER'),('8000','1234','Baruch','Frankfurter','FunAndLove@gmail.com',7,'GENERAL_MANAGER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weeks`
--

DROP TABLE IF EXISTS `weeks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weeks` (
  `idweeks` int(11) NOT NULL,
  PRIMARY KEY (`idweeks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weeks`
--

LOCK TABLES `weeks` WRITE;
/*!40000 ALTER TABLE `weeks` DISABLE KEYS */;
INSERT INTO `weeks` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34),(35),(36),(37),(38),(39),(40),(41),(42),(43),(44),(45),(46),(47),(48),(49),(50),(51),(52);
/*!40000 ALTER TABLE `weeks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ghealth'
--
/*!50003 DROP PROCEDURE IF EXISTS `filldates` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `filldates`(dateStart DATE, dateEnd DATE)
BEGIN
  WHILE dateStart <= dateEnd DO
    INSERT INTO calendar (calendar_date) VALUES (dateStart);
    SET dateStart = date_add(dateStart, INTERVAL 1 DAY);
  END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-13  0:27:56
