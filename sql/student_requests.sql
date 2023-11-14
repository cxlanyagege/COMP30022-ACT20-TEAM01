CREATE DATABASE  IF NOT EXISTS `student_requests` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `student_requests`;
-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: student_requests
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `a member of`
--

DROP TABLE IF EXISTS `a member of`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `a member of` (
  `student_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`student_id`,`group_id`),
  KEY `fk_a member of_Group1_idx` (`group_id`),
  CONSTRAINT `fk_a member of_Group1` FOREIGN KEY (`group_id`) REFERENCES `Group` (`group_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_a member of_Student1` FOREIGN KEY (`student_id`) REFERENCES `Student` (`student_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a member of`
--

LOCK TABLES `a member of` WRITE;
/*!40000 ALTER TABLE `a member of` DISABLE KEYS */;
/*!40000 ALTER TABLE `a member of` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AAP`
--

DROP TABLE IF EXISTS `AAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AAP` (
  `id` int NOT NULL,
  `description` mediumtext NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AAP`
--

LOCK TABLES `AAP` WRITE;
/*!40000 ALTER TABLE `AAP` DISABLE KEYS */;
/*!40000 ALTER TABLE `AAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AAP_attachment`
--

DROP TABLE IF EXISTS `AAP_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AAP_attachment` (
  `aap_id` int NOT NULL AUTO_INCREMENT,
  `attachment_url` varchar(100) NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`aap_id`),
  KEY `fk_AAP_attachment_Student1_idx` (`student_id`),
  CONSTRAINT `fk_AAP_attachment_Student1` FOREIGN KEY (`student_id`) REFERENCES `Student` (`student_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AAP_attachment`
--

LOCK TABLES `AAP_attachment` WRITE;
/*!40000 ALTER TABLE `AAP_attachment` DISABLE KEYS */;
INSERT INTO `AAP_attachment` VALUES (1,'test1.png',1266288);
/*!40000 ALTER TABLE `AAP_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Attachment`
--

DROP TABLE IF EXISTS `Attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Attachment` (
  `attachment_id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `request_id` int NOT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `fk_Attachment_Request1_idx` (`request_id`),
  CONSTRAINT `fk_Attachment_Request1` FOREIGN KEY (`request_id`) REFERENCES `Request` (`request_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2144555010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attachment`
--

LOCK TABLES `Attachment` WRITE;
/*!40000 ALTER TABLE `Attachment` DISABLE KEYS */;
INSERT INTO `Attachment` VALUES (-2142023679,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1783844866),(-2074914815,'/upload/WechatIMG888.jpg',1918062593),(-2020388862,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',2064863234),(-1903017982,'/upload/WechatIMG888.jpg',1553088514),(-1291845631,'/upload/test_file1.png',-1321205759),(-1198276606,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',-1349271551),(-1031688190,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',-1245597694),(-762097662,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',-829206526),(-280997887,'/upload/WechatIMG888.jpg',-624930814),(756158466,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',668078081),(760356865,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',261234689),(1182814210,'/upload/WechatIMG888.jpg',1065373697),(1217536001,'/upload/WechatIMG888.jpg',1095901186),(1347674114,'/upload/WechatIMG888.jpg',1121181698),(1385406466,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1104388098),(1477586946,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',898772994),(1687293954,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1494355969),(1694527490,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1472229377),(2144555009,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1918062593);
/*!40000 ALTER TABLE `Attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group`
--

DROP TABLE IF EXISTS `Group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Group` (
  `group_id` int NOT NULL,
  `subject_id` varchar(10) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group`
--

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;
/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `is_responsible_for`
--

DROP TABLE IF EXISTS `is_responsible_for`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `is_responsible_for` (
  `staff_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `staff_role` varchar(20) NOT NULL,
  `assignment_request` tinyint NOT NULL,
  `quiz_request` tinyint NOT NULL,
  `exam_request` tinyint NOT NULL,
  `personal_request` tinyint NOT NULL,
  `others_request` tinyint NOT NULL,
  PRIMARY KEY (`staff_id`,`subject_id`),
  KEY `fk_is responsible for_Subject1_idx` (`subject_id`),
  CONSTRAINT `fk_is responsible for_Staff_member1` FOREIGN KEY (`staff_id`) REFERENCES `Staff_member` (`staff_id`),
  CONSTRAINT `fk_is responsible for_Subject1` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`subject_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `is_responsible_for`
--

LOCK TABLES `is_responsible_for` WRITE;
/*!40000 ALTER TABLE `is_responsible_for` DISABLE KEYS */;
INSERT INTO `is_responsible_for` VALUES (123455,111,'tutor',0,1,1,1,1),(123455,222,'tutor',1,1,1,1,1),(123456,111,'coordinator',0,0,1,1,1);
/*!40000 ALTER TABLE `is_responsible_for` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Request`
--

DROP TABLE IF EXISTS `Request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `description` mediumtext NOT NULL,
  `status` varchar(10) NOT NULL,
  `submission_date` date NOT NULL,
  `student_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `request_type` varchar(20) NOT NULL,
  `request_name` varchar(45) DEFAULT NULL,
  `task_type` varchar(20) DEFAULT NULL,
  `work_type` varchar(20) DEFAULT NULL,
  `flagged` tinyint NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `fk_Request_Subject1_idx` (`subject_id`),
  KEY `fk_Request_Student1_idx` (`student_id`),
  CONSTRAINT `fk_Request_Student1` FOREIGN KEY (`student_id`) REFERENCES `Student` (`student_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_Request_Subject1` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`subject_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2097152003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Request`
--

LOCK TABLES `Request` WRITE;
/*!40000 ALTER TABLE `Request` DISABLE KEYS */;
INSERT INTO `Request` VALUES (-1421869055,'he','WAITING','2023-09-19',1266288,111,'Others','hehe',NULL,NULL,0),(-1349378047,'oh','WAITING','2023-10-06',1266288,111,'Assignment','','individual','Extension',0),(-1349271551,'hehehhhe','WAITING','2023-09-18',1266288,111,'Other','hello',NULL,NULL,0),(-1321205759,'shit','WAITING','2023-11-13',1266288,111,'Personal','I don\'t like it project',NULL,NULL,0),(-1245597694,'testing','WAITING','2023-10-06',1266288,111,'Assignment','Extension','individual','Remark',0),(-829206526,'extension','WAITING','2023-10-06',1266288,111,'Assignment','extension','individual',NULL,0),(-775942143,'This is 1 request','WAITING','2023-08-02',1266288,222,'Assignment','About assignment',NULL,NULL,0),(-624930814,'nihau','APPROVED','2023-09-22',1266288,111,'Exam','nihau','individual',NULL,0),(-88076287,'lonely dance','WAITING','2023-11-13',1266288,111,'Quiz','lonely dance','individual','Remark',0),(2,'ohnonono','APPROVED','2023-09-19',1266288,222,'Other','hehe',NULL,NULL,0),(213913602,'I was doing good','WAITING','2023-10-19',1266288,111,'Exam','Remark the exam','Group','Remark',0),(261234689,'I want to extend my ass2','WAITING','2023-10-06',1266288,111,'Assignment','Assignment 2 extension','individual','Extension',0),(331476993,'This is a new test request','WAITING','2023-10-09',1266288,333,'Quiz','About task','individual','Remark',0),(668078081,'my destiny','WAITING','2023-09-22',1266288,111,'Personal','u are',NULL,NULL,0),(898772994,'hhhhhh','WAITING','2023-09-24',1266288,111,'Others','hhhhh',NULL,NULL,0),(1065373697,'olala','WAITING','2023-09-22',1266288,111,'Exam','ola','Group',NULL,0),(1095901186,'ok?','WAITING','2023-09-24',1266288,111,'Quiz','we won\'t do the test','Group',NULL,0),(1104388098,'I can fly','WAITING','2023-09-21',1266288,111,'Others','I believe',NULL,NULL,0),(1121181698,'ok?','WAITING','2023-10-05',1266288,111,'Exam','About exam ','individual',NULL,0),(1472229377,'test','WAITING','2023-10-06',1266288,111,'Quiz','haohaohoa','individual','Remark',0),(1494355969,'I jump','APPROVED','2023-09-22',1266288,111,'Assignment','you jump','individual',NULL,0),(1553088514,'ok','APPROVED','2023-10-04',1266288,111,'Quiz','','individual',NULL,0),(1783844866,'remark','REJECTED','2023-10-06',1266288,111,'Personal','remark',NULL,NULL,0),(1918062593,'hahhahhahahahaha','WAITING','2023-10-05',1266288,111,'Others','hahahahhaha',NULL,NULL,0),(2064863234,'mymym','WAITING','2023-10-06',1266288,111,'Others','mymymy',NULL,NULL,0);
/*!40000 ALTER TABLE `Request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff_member`
--

DROP TABLE IF EXISTS `Staff_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff_member` (
  `staff_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `new_request` tinyint NOT NULL,
  `flagged_request` tinyint NOT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff_member`
--

LOCK TABLES `Staff_member` WRITE;
/*!40000 ALTER TABLE `Staff_member` DISABLE KEYS */;
INSERT INTO `Staff_member` VALUES (123455,'Tom','zewang4@student.unimelb.edu.au',1,0),(123456,'Tim Yu','heshen@student.unimelb.edu.au',1,1);
/*!40000 ALTER TABLE `Staff_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Student` (
  `student_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `create_request` tinyint NOT NULL,
  `process_request` tinyint NOT NULL,
  `delete_request` tinyint NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (1266288,'Dennis Wang','zewang4@student.unimelb.edu.au',1,1,1);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subject`
--

DROP TABLE IF EXISTS `Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Subject` (
  `subject_id` int NOT NULL,
  `subject_name` varchar(45) NOT NULL,
  `assignment_request` tinyint NOT NULL,
  `quiz_request` tinyint NOT NULL,
  `exam_request` tinyint NOT NULL,
  `personal_request` tinyint NOT NULL,
  `others_request` tinyint NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject`
--

LOCK TABLES `Subject` WRITE;
/*!40000 ALTER TABLE `Subject` DISABLE KEYS */;
INSERT INTO `Subject` VALUES (111,'some subject',0,1,1,1,1),(222,'test subject 1',1,1,1,1,1),(333,'test subject',1,1,1,1,1);
/*!40000 ALTER TABLE `Subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-14 13:30:29
