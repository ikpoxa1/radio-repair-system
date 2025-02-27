CREATE DATABASE  IF NOT EXISTS `repair_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `repair_db`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: repair_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `department_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (4,'г.Куровское, ул.Советская д.2','ПСЧ-243'),(5,'Орехово-Зуевский г.о, п. Авсюнино, ул. Юбилейная, д. 15','ОП ПСЧ-243'),(6,'г.Дрезна, ул. Парковая д. 5а','ПСЧ-248'),(7,'д.Соболево, пожарное депо','ПСЧ-249'),(8,'О-З г.о., с.п. Ильинское, с. Ил. Погост, ул. Совхозная, 11 б ','ОП ПСЧ-249'),(9,'г.Орехово-Зуево, пр.Беляцкого д.13а','ПСЧ-250(с)'),(10,'д.Демихово, пожарное депо','ОП ПСЧ-250(с)'),(11,'д.Савинская, пожарное депо','ПСЧ-251'),(12,'д.Губино, ул.Железнодорожная д.6','ПСЧ-253'),(13,'г.Электрогорск, ул. Ленина д.10','ПСЧ-254(с)'),(14,'г. П-Посад, ул. Школьная д. 14а','ОП ПСЧ-254(с)'),(15,'Павловский Посад, ул. Школьная, 14','ПСП ПСЧ-254 (спасатели)'),(16,'г.Ликино-Дулево, ул.Комсомольская д.10','ПСЧ-301');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radio_type`
--

DROP TABLE IF EXISTS `radio_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `radio_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radio_type`
--

LOCK TABLES `radio_type` WRITE;
/*!40000 ALTER TABLE `radio_type` DISABLE KEYS */;
INSERT INTO `radio_type` VALUES (1,'Носимая радиостанция','ТАКТ-301'),(3,'Возимая радиостанция','ТАКТ-201'),(4,'Стационарная радиостанция','ТАКТ-102');
/*!40000 ALTER TABLE `radio_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_list`
--

DROP TABLE IF EXISTS `repair_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_list` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `factory_number` varchar(255) NOT NULL,
  `inventory_number` varchar(255) NOT NULL,
  `department_id` bigint NOT NULL,
  `radio_type_id` bigint NOT NULL,
  `spare_part_id` bigint NOT NULL,
  `worker_id` bigint NOT NULL,
  `status_id` bigint NOT NULL,
  `status_timestamp` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKih3jks6m7teahbodskx95ohfk` (`department_id`),
  KEY `FKt42i3tcgpi0um7gpwarhoyyey` (`radio_type_id`),
  KEY `FK69o9kcpngcix293agai1o42u6` (`spare_part_id`),
  KEY `FKp9bvnldj7ije2wvu6g5wbfdhr` (`worker_id`),
  KEY `FKakkjw9xcsum57qfkmcx1raxi3` (`status_id`),
  CONSTRAINT `FK69o9kcpngcix293agai1o42u6` FOREIGN KEY (`spare_part_id`) REFERENCES `spare_part` (`id`),
  CONSTRAINT `FKakkjw9xcsum57qfkmcx1raxi3` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  CONSTRAINT `FKih3jks6m7teahbodskx95ohfk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKp9bvnldj7ije2wvu6g5wbfdhr` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  CONSTRAINT `FKt42i3tcgpi0um7gpwarhoyyey` FOREIGN KEY (`radio_type_id`) REFERENCES `radio_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_list`
--

LOCK TABLES `repair_list` WRITE;
/*!40000 ALTER TABLE `repair_list` DISABLE KEYS */;
INSERT INTO `repair_list` VALUES (7,'82348581','00000074',8,1,4,2,2,'2024-11-06 09:06:55.918032'),(8,'82348111','00000079',11,4,4,3,3,'2024-10-31 09:02:17.800660'),(9,'82348225','00000021',5,1,1,2,3,'2024-10-31 09:02:25.884075'),(10,'82348112','00000070',9,3,3,5,3,'2024-10-31 08:58:44.092723'),(12,'82348874','00000112',5,1,3,2,4,'2024-10-31 08:47:39.318603'),(13,'82348323','00000065',9,4,5,1,6,'2024-10-31 08:57:21.925295'),(14,'82348142','00000099',14,1,1,5,3,'2024-10-31 08:59:52.097855'),(15,'82348009','00000010',10,3,1,1,5,'2024-10-31 09:02:08.920867'),(16,'82348326','00000055',4,1,1,1,4,'2024-10-31 09:03:53.107325'),(17,'82348224','00000001',16,3,5,2,3,'2024-10-31 09:04:53.551697'),(18,'82348333','00000094',4,1,1,1,1,'2024-10-31 09:05:22.740207'),(20,'82348115','00000155',13,4,5,2,6,'2024-11-06 09:03:16.556106'),(23,'82348322','00000053',7,3,3,2,3,'2024-11-06 09:45:47.781849'),(24,'82348010','00000015',4,1,1,1,1,'2024-11-06 09:46:10.280481'),(25,'82348011','00000013',16,3,1,5,4,'2024-11-06 09:46:40.598690'),(26,'82348093','00000017',7,1,4,3,1,'2024-11-06 09:47:34.326033'),(27,'82348073','00000115',5,1,3,5,5,'2024-11-06 09:48:09.950089'),(28,'82348012','00000312',15,1,1,2,2,'2024-11-06 09:49:17.350089'),(29,'82348796','00000212',11,4,5,1,6,'2024-11-06 09:50:28.825674'),(30,'82348371','00000152',10,1,4,1,2,'2024-11-06 09:51:20.912651'),(31,'82348813','00000139',14,1,1,6,6,'2024-11-06 09:51:54.108314'),(32,'82348664','00000301',5,1,3,5,4,'2024-11-06 09:52:48.892649'),(33,'82348130','00000222',9,4,1,1,3,'2024-11-06 09:53:46.153520'),(34,'82348017','00000313',4,1,1,1,1,'2024-11-06 09:54:51.302015');
/*!40000 ALTER TABLE `repair_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spare_part`
--

DROP TABLE IF EXISTS `spare_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spare_part` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `part_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spare_part`
--

LOCK TABLES `spare_part` WRITE;
/*!40000 ALTER TABLE `spare_part` DISABLE KEYS */;
INSERT INTO `spare_part` VALUES (1,'Аккумулятор для радиостанции','Baofeng UV-82'),(3,'Антенна для раций 38 см, 136/520 МГц',' Baofeng UV-5R Nagoya NA-771 F38'),(4,'Внешний динамик','Mini Ham CB'),(5,'Кабель для тангенты ТАКТ-201','Кабель для тангенты');
/*!40000 ALTER TABLE `spare_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rstatcomment` varchar(255) DEFAULT NULL,
  `rstatus` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (1,'Ожидает запчасти для ремонта','Ожидает запчасти'),(2,'Ожидание ремонта радиостанции','Ожидает ремонта'),(3,'В процессе ремонта','В ремонте'),(4,'Тестирование радиостанции после ремонта','Тестирование'),(5,'Радиостанция готов к выдаче','Готов к выдаче'),(6,'Отправка радиостанции в ремонт','Отправка в ремонт'),(7,'Радиостанция ожидает получателя','Ожидает получателя');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (1,'Кириллов','Администратор','kirillov_admin','admin333','ADMIN'),(2,'Плющин','Инженер','plyushin_engineer','engineer123','USER'),(3,'Березовский','Электронщик','berezovsky_tech','tech123','USER'),(5,'Иванов','Тестировщик','ivanov_viewer','viewer123','VIEWER'),(6,'Марочник','Проверяющий','Marochkin','Mars223','VIEWER');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-27  9:39:01
