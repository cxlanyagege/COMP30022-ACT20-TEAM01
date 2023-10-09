-- MySQL dump 10.13  Distrib 8.1.0, for Win64 (x86_64)
--
-- Host: localhost    Database: sc
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachment` (
  `attachmentID` int NOT NULL,
  `url` text,
  `requestID` int DEFAULT NULL,
  PRIMARY KEY (`attachmentID`),
  KEY `requestID` (`requestID`),
  CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`requestID`) REFERENCES `request` (`RequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (-7614463,'/upload/temp.txt',-200552446),(0,'C:\\\\Users\\\\cxlan\\\\AppData\\\\Local\\\\Temp\\\\temp.txt',100),(1,'C:\\\\Users\\\\cxlan\\\\AppData\\\\Local\\\\Temp\\\\temp.txt',1000),(822779906,'/upload/(New) EPA6158_2023_Liyan_MA_10627103.pdf',722116609);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachments` (
  `attachmentId` int NOT NULL AUTO_INCREMENT,
  `requestId` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attachmentId`),
  KEY `requestId` (`requestId`),
  CONSTRAINT `attachments_ibfk_1` FOREIGN KEY (`requestId`) REFERENCES `request` (`RequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `RequestID` int NOT NULL AUTO_INCREMENT,
  `StudentID` int DEFAULT NULL,
  `SubjectID` varchar(10) DEFAULT NULL,
  `RequestType` varchar(50) DEFAULT NULL,
  `TaskType` varchar(50) DEFAULT NULL,
  `Emails` text,
  `RequestName` mediumtext,
  `Alert` int DEFAULT NULL,
  `SubmissionDate` datetime DEFAULT NULL,
  `description` text,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`RequestID`),
  KEY `StudentID` (`StudentID`),
  KEY `SubjectID` (`SubjectID`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=1804210179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (-1177903103,NULL,NULL,'Exam','',NULL,'',NULL,'2023-10-09 00:00:00','','WAITING'),(-200552446,108998431,NULL,'Assignment','individual',NULL,'Attachment Test',NULL,'2023-10-08 00:00:00','A new Attachment Test','REJECTED'),(100,108998431,'7677778','Other','Group','exmp1@student.unimelb.edu.au,exmp2@student.unimelb.edu.au','Rejected request',1,'2023-09-01 00:00:00',NULL,'REJECTED'),(1000,108998431,'7677778','Assignment','Group','exmp1@student.unimelb.edu.au,exmp2@student.unimelb.edu.au','Approved request',1,'2023-09-01 00:00:00',NULL,'APPROVED'),(10000,108998431,'7677778','Assignment','Group','exmp1@student.unimelb.edu.au,exmp2@student.unimelb.edu.au','Sample request',1,'2023-09-01 00:00:00',NULL,'WAITING'),(722116609,108998431,NULL,'Assignment','individual',NULL,'Assignment2 Remark',NULL,'2023-10-09 00:00:00','nnnnnnnnnnnnnnnnnnnnnn','WAITING');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `StudentID` int NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (108998431,'He Shen','heshen@student.unimelb.edu.au');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `SubjectID` varchar(10) NOT NULL,
  `SubjectName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SubjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('7677778','AU-COURSE-TEMPLATE');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-09 13:56:07
