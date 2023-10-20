-- MySQL dump 10.13  Distrib 8.1.0, for Win64 (x86_64)
--
-- Host: localhost    Database: full
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
  CONSTRAINT `fk_a member of_Group1` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_a member of_Student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE
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
-- Table structure for table `aap`
--

DROP TABLE IF EXISTS `aap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aap` (
  `id` int NOT NULL,
  `description` mediumtext NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime NOT NULL,
  `staff_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_AAP_Staff_member1_idx` (`staff_id`),
  CONSTRAINT `fk_AAP_Staff_member1` FOREIGN KEY (`staff_id`) REFERENCES `staff_member` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aap`
--

LOCK TABLES `aap` WRITE;
/*!40000 ALTER TABLE `aap` DISABLE KEYS */;
/*!40000 ALTER TABLE `aap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aap_attachment`
--

DROP TABLE IF EXISTS `aap_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aap_attachment` (
  `aap_attachment_id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `aap_id` int NOT NULL,
  PRIMARY KEY (`aap_attachment_id`,`aap_id`),
  KEY `fk_AAP_attachment_AAP1_idx` (`aap_id`),
  CONSTRAINT `fk_AAP_attachment_AAP1` FOREIGN KEY (`aap_id`) REFERENCES `aap` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aap_attachment`
--

LOCK TABLES `aap_attachment` WRITE;
/*!40000 ALTER TABLE `aap_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `aap_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachment` (
  `attachment_id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `request_id` int NOT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `fk_Attachment_Request1_idx` (`request_id`),
  CONSTRAINT `fk_Attachment_Request1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2144555010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (-2142023679,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1783844866),(-2074914815,'/upload/WechatIMG888.jpg',1918062593),(-2020388862,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',2064863234),(-1903017982,'/upload/WechatIMG888.jpg',1553088514),(-1198276606,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',-1349271551),(-1031688190,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',-1245597694),(-762097662,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',-829206526),(-418656255,'/upload/temp.pdf',-485765118),(-280997887,'/upload/WechatIMG888.jpg',-624930814),(756158466,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',668078081),(760356865,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',261234689),(1182814210,'/upload/WechatIMG888.jpg',1065373697),(1217536001,'/upload/WechatIMG888.jpg',1095901186),(1347674114,'/upload/WechatIMG888.jpg',1121181698),(1385406466,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1104388098),(1477586946,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',898772994),(1687293954,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1494355969),(1694527490,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1472229377),(2144555009,'/upload/Screen Shot 2023-09-14 at 10.32.24 pm.png',1918062593);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `group_id` int NOT NULL,
  `subject_id` varchar(10) NOT NULL,
  PRIMARY KEY (`group_id`),
  KEY `fk_Group_Subject1_idx` (`subject_id`),
  CONSTRAINT `fk_Group_Subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `is responsible for`
--

DROP TABLE IF EXISTS `is responsible for`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `is responsible for` (
  `staff_id` int NOT NULL,
  `subject_id` varchar(10) NOT NULL,
  PRIMARY KEY (`staff_id`,`subject_id`),
  KEY `fk_is responsible for_Subject1_idx` (`subject_id`),
  CONSTRAINT `fk_is responsible for_Staff_member1` FOREIGN KEY (`staff_id`) REFERENCES `staff_member` (`staff_id`),
  CONSTRAINT `fk_is responsible for_Subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `is responsible for`
--

LOCK TABLES `is responsible for` WRITE;
/*!40000 ALTER TABLE `is responsible for` DISABLE KEYS */;
/*!40000 ALTER TABLE `is responsible for` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `description` mediumtext NOT NULL,
  `status` varchar(10) NOT NULL,
  `submission_date` date NOT NULL,
  `student_id` int NOT NULL,
  `subject_id` varchar(10) NOT NULL,
  `request_type` varchar(20) NOT NULL,
  `request_name` varchar(45) DEFAULT NULL,
  `task_type` varchar(20) DEFAULT NULL,
  `work_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `fk_Request_Subject1_idx` (`subject_id`),
  KEY `fk_Request_Student1_idx` (`student_id`),
  CONSTRAINT `fk_Request_Student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_Request_Subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2097152003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (-1421869055,'he','WAITING','2023-09-19',1266288,'COMP10012','Others','hehe',NULL,NULL),(-1349378047,'oh','WAITING','2023-10-06',1266288,'COMP10012','Assignment','','individual','Extension'),(-1349271551,'hehehhhe','WAITING','2023-09-18',1266288,'COMP10012','Other','hello',NULL,NULL),(-1245597694,'testing','WAITING','2023-10-06',1266288,'COMP10012','Assignment','Extension','individual','Remark'),(-909389823,'Placeholder','REJECTED','2023-10-13',108998431,'7677778','Test','Test Remark','individual','Remark'),(-829206526,'extension','WAITING','2023-10-06',1266288,'COMP10012','Assignment','extension','individual',NULL),(-775942143,'This is 1 request','WAITING','2023-08-02',1266288,'COMP20024','Assignment','About assignment',NULL,NULL),(-624930814,'nihau','WAITING','2023-09-22',1266288,'COMP10012','Exam','nihau','individual',NULL),(-485765118,'This is other things','APPROVED','2023-10-13',108998431,'7677778','Others','Other Things',NULL,NULL),(-443822079,'Placeholder','WAITING','2023-10-13',108998431,'7677778','Assignment','Assignment Extension','individual','Extension'),(2,'ohnonono','APPROVED','2023-09-19',1266288,'COMP20024','Other','hehe',NULL,NULL),(261234689,'I want to extend my ass2','WAITING','2023-10-06',1266288,'COMP10012','Assignment','Assignment 2 extension','individual','Extension'),(668078081,'my destiny','WAITING','2023-09-22',1266288,'COMP10012','Personal','u are',NULL,NULL),(898772994,'hhhhhh','WAITING','2023-09-24',1266288,'COMP10012','Others','hhhhh',NULL,NULL),(1065373697,'olala','WAITING','2023-09-22',1266288,'COMP10012','Exam','ola','Group',NULL),(1095901186,'ok?','WAITING','2023-09-24',1266288,'COMP10012','Test','we won\'t do the test','Group',NULL),(1104388098,'I can fly','WAITING','2023-09-21',1266288,'COMP10012','Others','I believe',NULL,NULL),(1121181698,'ok?','WAITING','2023-10-05',1266288,'COMP10012','Exam','About exam ','individual',NULL),(1472229377,'test','WAITING','2023-10-06',1266288,'COMP10012','Test','haohaohoa','individual','Remark'),(1494355969,'I jump','WAITING','2023-09-22',1266288,'COMP10012','Assignment','you jump','individual',NULL),(1553088514,'ok','WAITING','2023-10-04',1266288,'COMP10012','Test','','individual',NULL),(1783844866,'remark','WAITING','2023-10-06',1266288,'COMP10012','Personal','remark',NULL,NULL),(1918062593,'hahhahhahahahaha','WAITING','2023-10-05',1266288,'COMP10012','Others','hahahahhaha',NULL,NULL),(2064863234,'mymym','WAITING','2023-10-06',1266288,'COMP10012','Others','mymymy',NULL,NULL);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_member`
--

DROP TABLE IF EXISTS `staff_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_member` (
  `staff_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_member`
--

LOCK TABLES `staff_member` WRITE;
/*!40000 ALTER TABLE `staff_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `create_request` tinyint(1) DEFAULT NULL,
  `delete_request` tinyint(1) DEFAULT NULL,
  `process_request` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1266288,'Denni Wang','test@student.edu.au',1,1,1),(108998431,'He Shen','heshen@student.unimelb.edu.au',1,1,1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` varchar(10) NOT NULL,
  `subject_name` varchar(45) NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('7677778','AU-COURSE-TEMPLATE'),('COMP10012','some subject'),('COMP20024','test subject 1'),('COMP30023','test subject');
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

-- Dump completed on 2023-10-20 20:37:25
