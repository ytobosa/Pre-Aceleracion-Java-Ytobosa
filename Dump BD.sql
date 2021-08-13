-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: alkemy
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `idPeliculaSerie` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Drama','C/imagenes',3),(2,'Caricaturas','C:/imagenes/genero',2),(3,'SuperHeroes','C/imagenes',20);
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculaserie`
--

DROP TABLE IF EXISTS `peliculaserie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculaserie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imagen` varchar(45) DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `calificacion` int DEFAULT NULL,
  `genero_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculaserie`
--

LOCK TABLES `peliculaserie` WRITE;
/*!40000 ALTER TABLE `peliculaserie` DISABLE KEYS */;
INSERT INTO `peliculaserie` VALUES (1,'C:MisImagenes','Rapido y Furioso 3','2021-06-01',50,5,1),(2,'/C:MisImagenes','Frozen','2020-01-01',70,4,NULL),(3,'/C:MisImagenes/yala','Marvel','2020-01-01',30,4,3),(7,'/C:MisImagenes/yala','Marvel','2020-01-01',30,4,3);
/*!40000 ALTER TABLE `peliculaserie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaje`
--

DROP TABLE IF EXISTS `personaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personaje` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imagen` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `historia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaje`
--

LOCK TABLES `personaje` WRITE;
/*!40000 ALTER TABLE `personaje` DISABLE KEYS */;
INSERT INTO `personaje` VALUES (1,'C:/imagenes','Toreto',50,90,'Rapido y Furioso 3'),(2,'C:/imagenes','Elsa',20,50,'Frozen'),(4,'C:/imagenes2','Cruella',25,30,'Cruella'),(5,'C:/imagenes2/yala','Iron Man',40,30,'Marvel'),(9,'C:/imagenes2','Camila',7,30,'Camila'),(13,'C:/imagenes2','Capitan America',30,30,'Marvel'),(14,'C:imagenes','Elvis',40,70,'Elvis'),(15,'C:/imagenes2','Elvis',7,30,'Elvis'),(16,'C:/imagenes2','Elvis',7,30,'Elvis'),(20,'C:/imagenes2','Yalaury',7,30,'Yalaury'),(22,'C:/imagenes2','Ricardo',7,30,'Antonio'),(23,'C:/imagenes2','Antonio',7,30,'Ricardo'),(25,'C:/imagenes2/yala','Nubia',7,30,'nubia'),(26,'C:/imagenes2/yala','Prueba List Pelicula',7,30,'nubia');
/*!40000 ALTER TABLE `personaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personajes_peliculas`
--

DROP TABLE IF EXISTS `personajes_peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personajes_peliculas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idpersonaje` int NOT NULL,
  `idpelicula` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_personajes_peliculas_personaje_idx` (`idpersonaje`),
  KEY `FK_personajes_peliculas_peliculaserie_idx` (`idpelicula`),
  CONSTRAINT `FK_personajes_peliculas_peliculaserie` FOREIGN KEY (`idpelicula`) REFERENCES `peliculaserie` (`id`),
  CONSTRAINT `FK_personajes_peliculas_personaje` FOREIGN KEY (`idpersonaje`) REFERENCES `personaje` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personajes_peliculas`
--

LOCK TABLES `personajes_peliculas` WRITE;
/*!40000 ALTER TABLE `personajes_peliculas` DISABLE KEYS */;
INSERT INTO `personajes_peliculas` VALUES (3,1,1),(7,13,2),(10,16,1),(21,25,2),(26,2,3);
/*!40000 ALTER TABLE `personajes_peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `f_name` varchar(25) DEFAULT NULL,
  `l_name` varchar(25) DEFAULT NULL,
  `dni` int DEFAULT NULL,
  `rol` varchar(10) DEFAULT NULL,
  `create_at` date NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (50,'Ricardo','Areiza',9674559,'User','2021-03-15',_binary '','$2a$10$Sx3KHjauh9sQsZy7YN3WtuLxUzAYGiLtLoVPnSbuBiAv197FvjZby','raaz','ricardo.areiza.01@gmail.com'),(51,'Alkemy','App',1234567,'Admin','2021-03-28',_binary '',NULL,NULL,NULL),(52,'Alkemy2','App2',7654321,'Admin','2021-03-28',_binary '',NULL,NULL,NULL),(53,'Alkemy3','App3',98765,'User','2021-03-28',_binary '\0',NULL,NULL,NULL),(54,'Diego','Areiza',56564567,'Students','2021-03-28',_binary '','$2a$10$SZy6Jnx5k8bxcBpytITeXO4h3DB2jtlFX0.G/UU/fsrNBbBk0GYui','56564567','diego.areiza.01@gmail.com'),(56,'elvis','Areiza',67163818,'User','2021-03-28',_binary '','$2a$10$8tEwIEj27Ipj1r.112VjL.gHJ89K3K.xs4aFRi6Iguz65yV9yQk7i','67163818','elvis.areiza.01@gmail.com'),(57,'Antonio','Alfonso',12675317,'User','2021-03-28',_binary '','$2a$10$svZCSBt.w/yhnPOKJd//QOLdNXuSK1uVrKhEwLOAnKWbdoVW3I2zS','12675317','antonio.alfonso.01@gmail.com'),(58,'Maria','Martinez',457567,'Students','2021-03-28',_binary '','$2a$10$w1xDAf5YFA4B9Xer7KedOORQrH4Rb5krUfM.IsiAPZutOpITnGfru','457567','derek.martinez.01@gmail.com'),(59,'Alkemy','App',45678436,'Admin','2021-03-28',_binary '','$2a$10$RSnE.asowkzweV92bD6oceXDqUsIrlQ5HQt5CQO/wuYiX.OkQjplm','admin','alkemy@gmail.com'),(64,'Ricardo','Areiza',96745599,'Admin','2021-08-13',_binary '','12345','ytobosa','ytobosa@gmail.com'),(69,'Yalaury','Tobosa',95745599,'Admin','2021-08-13',_binary '','$2a$10$GthPnwI/T.OJyTEQo7Y06OAJqb7vu5cip75HMdXXmgtNAKbvfgTuW','ytobosa1','yalauryt@gmail.com'),(70,'Yalaury','Tobosa',95745999,'User','2021-08-13',_binary '','$2a$10$0Ge8XgvvGM.J89PTWpbaruRr7HE0jwBDC1J0DXVr7kfYbiuhJKr1O','ytobosa2','yala1502@gmail.com');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  UNIQUE KEY `UKqjaspm7473pnu9y4jxhrds8r2` (`usuario_id`,`role_id`),
  KEY `FKihom0uklpkfpffipxpoyf7b74` (`role_id`),
  CONSTRAINT `FKihom0uklpkfpffipxpoyf7b74` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (50,1),(54,1),(56,1),(57,1),(58,1),(64,1),(69,1),(70,1),(59,2);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-13 10:09:38
