-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ghealth
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
INSERT INTO `clinic` VALUES (1,'Asuta','Haifa'),(2,'Killers','Tel-Aviv');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors` (
  `uID` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (5000,'Eyes'),(5005,'Cardio'),(5006,'Family');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
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
  `ptDoctorID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ptID`),
  KEY `PersonalDoctorID_idx` (`ptDoctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('200113','Ori','Arel','temp@gmail.com','12345','Klalit',1),('200567','Mey','Lady','gfhfgh@fgf.com','567567','Macabi',2),('308705235','artur','be','sjdgfkgjsf','sdfsdf','abc',1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_appointments`
--

DROP TABLE IF EXISTS `patient_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_appointments` (
  `ptID` int(11) NOT NULL,
  `apptID` int(11) NOT NULL,
  `uID` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `hour` varchar(45) DEFAULT NULL,
  `createDate` varchar(45) DEFAULT NULL,
  `apptRecord` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ptID`,`apptID`,`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_appointments`
--

LOCK TABLES `patient_appointments` WRITE;
/*!40000 ALTER TABLE `patient_appointments` DISABLE KEYS */;
INSERT INTO `patient_appointments` VALUES (200113,1,5000,'NOSHOW','23/5/16','09:25','22/5/16',NULL),(200113,7,5005,'ARRIVED','25/5/16','11:00','22/5/16','badass'),(200567,2,5000,'SCHEDUELD','30/7/16','10:00','22/5/16',NULL),(200567,4,5006,'CANCELED','24/5/16','09:00','22/5/16',NULL),(200567,5,5005,'ARRIVED','25/5/16','09:25','22/5/16','good'),(308705235,3,5000,'SCHEDUELD','25/9/16','11:00','22/5/16',NULL),(308705235,6,5006,'ARRIVED','25/5/16','09:20','22/5/16','good');
/*!40000 ALTER TABLE `patient_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaldoctor`
--

DROP TABLE IF EXISTS `personaldoctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaldoctor` (
  `PersonalDoctorID` int(11) NOT NULL,
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
INSERT INTO `personaldoctor` VALUES (1,'Muhamad Ali','blabla@gmail.com'),(2,'Yaser Arafat','asdfas@gmail.com');
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
INSERT INTO `privateclniic` VALUES ('Klalit','Klalit@gmail.com'),('Maccabi','Maccabi@gmail.com');
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
INSERT INTO `user` VALUES ('5000','1234','Moshe','Pinto','Pinto@gmail.com',1,'DOCTOR'),('5001','1234','Artur','Be','beartur89@gmail.com',1,'CUSTOMER_SERVICE'),('5002','1234','aaa','bbb','sdfsdf@dfg.com',1,'LAB_WORKER'),('5003','1234','ccc','ddd','sdfsdf@dfg.com',1,'CLINIC_MANAGER'),('5004','1234','eee','fff','sdfsdf@dfg.com',1,'GENERAL_MANAGER'),('5005','1234','suzi','buja','fgthgfh',1,'DOCTOR'),('5006','1234','Shoshi','Rips','fghfgh',1,'DOCTOR');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-28 19:07:29
