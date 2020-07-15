/*
SQLyog Ultimate v9.02 
MySQL - 8.0.18 : Database - utn-2020
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`utn-2020` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `utn-2020`;

/*Table structure for table `alumno` */

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `alu_dni` bigint(10) unsigned NOT NULL,
  `alu_nombre` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `alu_apellido` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `alu_fec_nac` date DEFAULT NULL,
  `alu_domicilio` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `alu_telefono` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `alu_insc_cod` bigint(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`alu_dni`),
  KEY `FK_alumno` (`alu_insc_cod`),
  CONSTRAINT `FK_alumno` FOREIGN KEY (`alu_insc_cod`) REFERENCES `inscripcion` (`insc_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `alumno` */

insert  into `alumno`(`alu_dni`,`alu_nombre`,`alu_apellido`,`alu_fec_nac`,`alu_domicilio`,`alu_telefono`,`alu_insc_cod`) values (245262,'Pedro','Picapiedra','2020-06-06','453','34534',2),(413499,'Juana','deArco','2020-06-11','Paris 45','1557779846',14),(2497879,'Fernanda','Caprese','1999-04-02','Sarmiento 14','459783',1),(12345678,'Darío','Basualdo','2020-06-18','Laprida 45','46798973',7);

/*Table structure for table `carrera` */

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `car_cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `car_nombre` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `car_duracion` tinyint(2) unsigned NOT NULL,
  PRIMARY KEY (`car_cod`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `carrera` */

insert  into `carrera`(`car_cod`,`car_nombre`,`car_duracion`) values (37,'Comunismo',33),(38,'44',5),(39,'77',44);

/*Table structure for table `cursado` */

DROP TABLE IF EXISTS `cursado`;

CREATE TABLE `cursado` (
  `cur_alu_dni` bigint(10) unsigned NOT NULL,
  `cur_mat_cod` int(10) unsigned NOT NULL,
  `cur_nota` int(2) unsigned NOT NULL,
  PRIMARY KEY (`cur_alu_dni`,`cur_mat_cod`),
  KEY `FK_cursado` (`cur_mat_cod`),
  CONSTRAINT `FK_cursado` FOREIGN KEY (`cur_mat_cod`) REFERENCES `materia` (`mat_cod`),
  CONSTRAINT `FK_cursado1` FOREIGN KEY (`cur_alu_dni`) REFERENCES `alumno` (`alu_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `cursado` */

insert  into `cursado`(`cur_alu_dni`,`cur_mat_cod`,`cur_nota`) values (245262,1,9),(2497879,1,6),(12345678,465,10),(12345678,466,9);

/*Table structure for table `inscripcion` */

DROP TABLE IF EXISTS `inscripcion`;

CREATE TABLE `inscripcion` (
  `insc_cod` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `insc_nombre` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `insc_fecha` date NOT NULL,
  `insc_car_cod` int(20) unsigned NOT NULL,
  PRIMARY KEY (`insc_cod`),
  KEY `FK_inscripcion` (`insc_car_cod`),
  CONSTRAINT `FK_inscripcion` FOREIGN KEY (`insc_car_cod`) REFERENCES `carrera` (`car_cod`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `inscripcion` */

insert  into `inscripcion`(`insc_cod`,`insc_nombre`,`insc_fecha`,`insc_car_cod`) values (1,'Primer Año','2020-06-01',39),(14,'Quinto año','2020-06-11',37),(15,'Cuarto año','2020-06-11',38);

/*Table structure for table `materia` */

DROP TABLE IF EXISTS `materia`;

CREATE TABLE `materia` (
  `mat_cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mat_nombre` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mat_prof_dni` bigint(10) NOT NULL,
  PRIMARY KEY (`mat_cod`),
  KEY `FK_materia` (`mat_prof_dni`),
  CONSTRAINT `FK_materia` FOREIGN KEY (`mat_prof_dni`) REFERENCES `profesor` (`prof_dni`)
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `materia` */

insert  into `materia`(`mat_cod`,`mat_nombre`,`mat_prof_dni`) values (0,'Física',11111),(1,'Matemática',29987454),(458,'Dibujo Téc',4646646),(459,'Programación',11111),(465,'Contabilidad',2467989);

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `prof_dni` bigint(10) NOT NULL,
  `prof_nombre` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `prof_apellido` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `prof_fec_nac` date NOT NULL,
  `prof_domicilio` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prof_telefono` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`prof_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `profesor` */

insert  into `profesor`(`prof_dni`,`prof_nombre`,`prof_apellido`,`prof_fec_nac`,`prof_domicilio`,`prof_telefono`) values (11111,'Salomón','Ramirez','2020-06-05','Sarmiento 153','411398'),(2467989,'Soberano','Dominguez','2020-06-03','Luzuriaga','4546787'),(4577989,'Santiago','Cabeza','2020-06-11','','4223798'),(4646646,'Saladino','Perez','2020-06-03','La bondad 33','45787'),(29987454,'Ramiro','Agujis','2020-06-18','Formosa 89','45678979');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
