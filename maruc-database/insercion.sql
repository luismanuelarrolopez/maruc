-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: maruc
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Dumping data for table `actividad_riesgo`
--

LOCK TABLES `actividad_riesgo` WRITE;
/*!40000 ALTER TABLE `actividad_riesgo` DISABLE KEYS */;
INSERT INTO `actividad_riesgo` VALUES (1,16,11,'2022-06-24 08:53:07','2022-06-24 08:53:07',0,40),(2,16,13,'2022-06-24 11:19:07','2022-06-24 11:19:07',0,40),(3,22,11,'2022-07-14 02:17:48','2022-07-14 02:17:48',0,40),(5,22,12,'2022-07-14 02:17:59','2022-07-14 02:17:59',0,100),(6,22,10,'2022-07-14 02:18:09','2022-07-14 02:18:09',0,20),(7,22,14,'2022-07-14 02:18:15','2022-07-14 02:18:15',0,80),(8,22,13,'2022-07-16 19:40:31','2022-07-16 19:40:31',0,40),(9,23,10,'2022-07-22 00:29:08','2022-07-22 00:29:08',0,20),(10,23,15,'2022-07-22 00:29:26','2022-07-22 00:29:26',0,20),(11,23,13,'2022-07-22 00:29:34','2022-07-22 00:29:34',0,40),(12,24,10,'2022-08-03 04:00:44','2022-08-03 04:00:44',0,60),(13,24,12,'2022-08-03 04:00:49','2022-08-03 04:00:49',0,60),(14,25,10,'2022-08-04 05:39:38','2022-08-04 05:39:38',0,60),(15,27,12,'2022-08-19 04:42:17','2022-08-19 04:42:17',0,40),(16,27,13,'2022-08-19 04:42:53','2022-08-19 04:42:53',0,40),(17,27,11,'2022-08-19 04:43:06','2022-08-19 04:43:06',0,100),(18,26,13,'2022-08-26 10:31:11','2022-08-26 10:31:11',0,100),(19,26,12,'2022-08-26 10:31:19','2022-08-26 10:31:19',0,80),(20,34,10,'2022-08-30 05:19:27','2022-08-30 05:19:27',0,20),(21,35,11,'2022-09-02 01:18:59','2022-09-02 01:18:59',0,40),(22,36,10,'2022-09-02 21:06:32','2022-09-02 21:06:32',0,80),(23,36,15,'2022-09-02 21:07:30','2022-09-02 21:07:30',0,80),(24,36,14,'2022-09-02 21:07:39','2022-09-02 21:07:39',0,40),(25,36,11,'2022-09-02 21:07:49','2022-09-02 21:07:49',0,60),(26,36,12,'2022-09-02 21:07:56','2022-09-02 21:07:56',0,80),(27,36,13,'2022-09-02 21:08:03','2022-09-02 21:08:03',0,80),(28,34,12,'2022-09-06 00:15:57','2022-09-06 00:15:57',0,40),(30,38,11,'2022-09-06 13:51:08','2022-09-06 13:51:08',0,20);
/*!40000 ALTER TABLE `actividad_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` VALUES (10,'Planeación estratégica del proceso y dependencia (Elaboración de planes programas y proyectos, entre ellos plan de acción)',_binary '\0','2022-06-13 11:07:16','2022-08-23 02:11:58',3),(11,'Actividades de seguimiento y adiestramiento de talento humano del proceso y dependencia',_binary '\0','2022-06-13 11:07:16','2022-06-13 11:07:16',1),(12,'Actividades del proceso que tengan relación directa con lo jurídico y administrativo',_binary '\0','2022-06-13 11:07:16','2022-06-13 11:07:16',1),(13,'Planeación, ejecución y seguimiento a las actividades presupuestales, de contabilidad y cartera propias del proceso y dependencia',_binary '\0','2022-06-13 11:07:16','2022-06-13 11:07:16',1),(14,'Ejecución de aplicativos y tecnología interna y externa para el funcionamiento del proceso y dependencias',_binary '\0','2022-06-13 11:07:16','2022-06-13 11:07:16',1),(15,'Una actividad',_binary '\0','2022-07-22 00:23:47','2022-08-23 02:12:09',2);
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `asesorias`
--

LOCK TABLES `asesorias` WRITE;
/*!40000 ALTER TABLE `asesorias` DISABLE KEYS */;
INSERT INTO `asesorias` VALUES (1,'Prueba de la nueva metodología','2022-07-22 06:04:20','2022-07-14 05:00:00','oci',11,'2022-07-22 06:04:20','2022-07-22 06:15:52',1),(2,'Asesoria de la oficina de control interno','2022-07-22 07:34:12','2022-09-03 05:00:00','oci',11,'2022-07-22 07:34:12','2022-07-22 07:36:04',1),(3,'Riesgos','2022-08-19 04:24:19','2022-09-02 05:00:00','oci',11,'2022-08-19 04:24:19','2022-08-31 07:09:19',2),(4,'Alguna asesoria','2022-08-31 04:13:53',NULL,'oci',11,'2022-08-31 04:13:53','2022-08-31 04:13:53',0);
/*!40000 ALTER TABLE `asesorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `causa_controlresidual`
--

LOCK TABLES `causa_controlresidual` WRITE;
/*!40000 ALTER TABLE `causa_controlresidual` DISABLE KEYS */;
INSERT INTO `causa_controlresidual` VALUES (16,13),(17,14);
/*!40000 ALTER TABLE `causa_controlresidual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `causas`
--

LOCK TABLES `causas` WRITE;
/*!40000 ALTER TABLE `causas` DISABLE KEYS */;
INSERT INTO `causas` VALUES (16,'Escazes de recursos financieros y de talento humano destinados al fortalecimiento de la internacionalización','','','',5,4,3,12,23,'2022-07-22 00:26:01','2022-07-22 00:26:01',0,NULL),(17,'Falta de apropiación de la comunidad universitaria para desarrollar actividades tendientes al fortalecimiento de la internacionalización',NULL,NULL,NULL,1,2,3,6,23,'2022-07-22 00:26:37','2022-07-22 00:26:37',0,NULL),(24,'Deficiencias de Información de la comunidad universitaria en conceptos, convenios, comisiones de estudio','','','',1,3,4,8,26,'2022-08-17 11:00:33','2022-08-17 11:00:33',0,NULL),(25,'Los servidores desconocen los procedimientos que ejecutan',NULL,NULL,NULL,5,5,5,15,26,'2022-08-17 11:00:53','2022-08-17 11:00:53',0,NULL),(26,'Falta de Capacitación de la Comunidad Universitaria en conceptos, convenios, comisiones de estudio.\n',NULL,NULL,NULL,3,3,3,9,26,'2022-08-17 11:01:11','2022-08-17 11:01:11',0,NULL);
/*!40000 ALTER TABLE `causas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `consecuencia_riesgo`
--

LOCK TABLES `consecuencia_riesgo` WRITE;
/*!40000 ALTER TABLE `consecuencia_riesgo` DISABLE KEYS */;
INSERT INTO `consecuencia_riesgo` VALUES (1,23,49,'2022-08-09 10:39:25','2022-08-09 10:39:25',NULL);
/*!40000 ALTER TABLE `consecuencia_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `consecuencias`
--

LOCK TABLES `consecuencias` WRITE;
/*!40000 ALTER TABLE `consecuencias` DISABLE KEYS */;
INSERT INTO `consecuencias` VALUES (48,'Vacio legal','texto','2022-07-21 23:46:20','2022-08-23 19:12:26',2,0),(49,'Selcción Consecuencia','seleccion','2022-07-22 00:23:07','2022-07-22 00:23:07',4,0),(55,'Text0','seleccion','2022-08-23 02:32:20','2022-08-23 05:24:50',7,0),(56,'3','texto','2022-09-06 14:36:56','2022-09-06 14:36:56',2,0),(57,'sdasdas','texto','2022-09-06 14:44:43','2022-09-06 14:44:43',2,0);
/*!40000 ALTER TABLE `consecuencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `control_residual`
--

LOCK TABLES `control_residual` WRITE;
/*!40000 ALTER TABLE `control_residual` DISABLE KEYS */;
INSERT INTO `control_residual` VALUES (13,6,'Conformar comité multidisciplinario que trabaje en expedición de la normatividad y el plan de internacionalización de la ORI','Asesor ORII',4,7,'Evidencia 2','2022-07-22 00:43:53','2022-07-22 00:43:53',0),(14,24,'Diseñar estrategias de comunicación que permita la integración de los conceptos de internacionalización en la comunidad universitaria','Asesor ORII / Dirección Centro Gestión de las Comunicaciones',4,11,'I2','2022-07-22 00:44:12','2022-07-22 00:44:12',0);
/*!40000 ALTER TABLE `control_residual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `controles`
--

LOCK TABLES `controles` WRITE;
/*!40000 ALTER TABLE `controles` DISABLE KEYS */;
INSERT INTO `controles` VALUES (12,'Asesor ORI',23,'2022-07-22 00:25:22','2022-07-22 00:30:44',1,NULL,'Política de relaciones de interinstitucionales e internacionales, Política de Movilidad Académica',NULL,1,5,11,6,NULL,0.15,0.00,0.25,0.25,0.00,0.00,6,5,_binary ''),(26,'Asesor ORI',23,'2022-07-22 00:25:22','2022-07-22 00:30:44',1,NULL,'Informes Ejecutivos',NULL,2,1,11,6,NULL,0.15,0.00,0.25,0.25,0.00,0.00,6,5,_binary ''),(106,'Asesor ORI',23,'2022-07-22 00:25:22','2022-07-22 00:30:44',1,NULL,'Formatos y Procedimientos, Instructivo y lista de chequeos',NULL,2,1,11,6,NULL,0.15,0.00,0.25,0.25,0.00,0.00,6,5,_binary ''),(107,'Asesor ORI',26,'2022-07-22 00:25:22','2022-07-22 00:30:44',1,NULL,'Política de prevención del daño antijurídico',NULL,2,1,11,6,NULL,0.15,0.00,0.25,0.25,0.00,0.00,6,5,_binary ''),(108,'Asesor ORI',23,'2022-07-22 00:25:22','2022-07-22 00:30:44',1,NULL,'Procedimientos para elaboración de convenios',NULL,2,1,11,6,NULL,0.15,0.00,0.25,0.25,0.00,0.00,6,5,_binary '');
/*!40000 ALTER TABLE `controles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dependencias`
--

LOCK TABLES `dependencias` WRITE;
/*!40000 ALTER TABLE `dependencias` DISABLE KEYS */;
INSERT INTO `dependencias` VALUES (3,'Secretaría General','1','2022-03-16 09:40:17','2022-03-16 09:40:17','Secretaría General'),(4,'Área de Gestión Documental','2','2022-03-16 09:40:17','2022-03-16 09:40:17','Área de Gestión Documental'),(5,'Centro de Gestión de la Calidad y de la Acreditación Institucional','3','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Gestión de la Calidad y de la Acreditación Institucional'),(6,'Centro de Gestión de las Comunicaciones','4','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Gestión de las Comunicaciones'),(7,'Oficina de Planeación y de Desarrollo Institucional','5','2022-03-16 09:40:17','2022-03-16 09:40:17','Oficina de Planeación y de Desarrollo Institucional'),(10,'Oficina de Control Interno','7','2022-03-16 09:40:17','2022-03-16 09:40:17','Oficina de Control Interno'),(11,'Oficina de Relaciones Interinstitucionales e Internacionales','8','2022-03-16 09:40:17','2022-03-16 09:40:17','Oficina de Relaciones Interinstitucionales e Internacionales'),(12,'Centro de Regionalización','9','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Regionalización'),(13,'División de Admisiones, Registro y Control Académico','10','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Admisiones, Registro y Control Académico'),(14,'División de Gestión de Medios y Recursos Bibliográficos','11','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión de Medios y Recursos Bibliográficos'),(15,'Centro de Posgrados','12','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Posgrados'),(16,'Área de Egresados','13','2022-03-16 09:40:17','2022-03-16 09:40:17','Área de Egresados'),(17,'Centro de Formación en Idiomas','14','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Formación en Idiomas'),(18,'Centro de Educación Continua, Abierta y Virtual – CECAV','15','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Educación Continua, Abierta y Virtual – CECAV'),(19,'División de Gestión del Talento Humano','16','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión del Talento Humano'),(20,'División de Gestión Financiera','17','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión Financiera'),(21,'División de Tecnologías de la Información y las Comunicaciones','18','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Tecnologías de la Información y las Comunicaciones'),(22,'División Administrativa y de Servicios','19','2022-03-16 09:40:17','2022-03-16 09:40:17','División Administrativa y de Servicios'),(23,'Área de Contratación','20','2022-03-16 09:40:17','2022-03-16 09:40:17','Área de Contratación'),(24,'Área de Proyectos Especiales','21','2022-03-16 09:40:17','2022-03-16 09:40:17','Área de Proyectos Especiales'),(25,'División de Gestión de la Investigación','22','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión de la Investigación'),(26,'División de Innovación, Emprendimiento y Articulación con el Entorno','23','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Innovación, Emprendimiento y Articulación con el Entorno'),(27,'Área de Desarrollo Editorial','24','2022-03-16 09:40:17','2022-03-16 09:40:17','Área de Desarrollo Editorial'),(28,'Centro Internacional para la Investigación del Agua y el Oxígeno','25','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro Internacional para la Investigación del Agua y el Oxígeno'),(29,'Centro de Innovación y Apropiación Social de la Caficultura','26','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro de Innovación y Apropiación Social de la Caficultura'),(30,'Centro Internacional de Secuenciación Genómica y Bioingeniería','27','2022-03-16 09:40:17','2022-03-16 09:40:17','Centro Internacional de Secuenciación Genómica y Bioingeniería'),(31,'División de Gestión de la Cultura','28','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión de la Cultura'),(32,'División de Gestión de la Recreación y el Deporte','29','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión de la Recreación y el Deporte'),(33,'División de Gestión de Salud Integral y Desarrollo Humano','30','2022-03-16 09:40:17','2022-03-16 09:40:17','División de Gestión de Salud Integral y Desarrollo Humano'),(34,'FACULTAD DE DERECHO Y CIENCIAS POLÍTICAS Y SOCIALES','31','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE DERECHO Y CIENCIAS POLÍTICAS Y SOCIALES'),(35,'FACULTAD DE CIENCIAS DE LA SALUD','32','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE CIENCIAS DE LA SALUD'),(36,'FACULTAD DE INGENIERÍA CIVIL','33','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE INGENIERÍA CIVIL'),(37,'FACULTAD DE INGENIERÍA ELECTRÓNICA Y TELECOMUNICACIONES','34','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE INGENIERÍA ELECTRÓNICA Y TELECOMUNICACIONES'),(38,'FACULTAD DE CIENCIAS NATURALES, EXACTAS Y DE LA EDUCACIÓN','35','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE CIENCIAS NATURALES, EXACTAS Y DE LA EDUCACIÓN'),(39,'FACULTAD DE ARTES','36','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE ARTES'),(40,'FACULTAD DE CIENCIAS HUMANAS Y SOCIALES','37','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE CIENCIAS HUMANAS Y SOCIALES'),(41,'FACULTAD DE CIENCIAS CONTABLES, ECONÓMICAS Y ADMINISTRATIVAS','38','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE CIENCIAS CONTABLES, ECONÓMICAS Y ADMINISTRATIVAS'),(42,'FACULTAD DE CIENCIAS AGRARIAS','39','2022-03-16 09:40:17','2022-03-16 09:40:17','FACULTAD DE CIENCIAS AGRARIAS'),(43,'UNIDAD DE SALUD','40','2022-03-16 09:40:17','2022-03-16 09:40:17','UNIDAD DE SALUD'),(46,'Oficina Jurídica','6','2022-03-16 09:40:17','2022-03-16 09:40:17','Oficina Jurídica');
/*!40000 ALTER TABLE `dependencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `difusion_control`
--

LOCK TABLES `difusion_control` WRITE;
/*!40000 ALTER TABLE `difusion_control` DISABLE KEYS */;
INSERT INTO `difusion_control` VALUES (1,'Capacitaciones','0.15',0.00,'1','2022-06-11 23:05:49','2022-06-11 23:05:49'),(2,'Publicaciones',NULL,0.00,'2','2022-06-23 21:19:53','2022-06-23 21:19:53'),(3,'Socializaciones',NULL,0.00,'3','2022-06-23 21:19:53','2022-06-23 21:19:53'),(4,'No',NULL,0.15,'5','2022-06-23 21:19:53','2022-06-23 21:19:53'),(5,'N/A',NULL,0.00,'4','2022-06-23 21:19:53','2022-06-23 21:19:53');
/*!40000 ALTER TABLE `difusion_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ejecucion_sistemas_digitales`
--

LOCK TABLES `ejecucion_sistemas_digitales` WRITE;
/*!40000 ALTER TABLE `ejecucion_sistemas_digitales` DISABLE KEYS */;
INSERT INTO `ejecucion_sistemas_digitales` VALUES (5,'No',NULL,0.20,'3','2022-06-24 01:38:10','2022-06-24 01:38:10'),(6,'Si',NULL,0.00,'1','2022-06-24 01:38:10','2022-06-24 01:38:10'),(7,'N/A',NULL,0.00,'2','2022-06-24 01:38:10','2022-06-24 01:38:10');
/*!40000 ALTER TABLE `ejecucion_sistemas_digitales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `emplea_o_ejecuta`
--

LOCK TABLES `emplea_o_ejecuta` WRITE;
/*!40000 ALTER TABLE `emplea_o_ejecuta` DISABLE KEYS */;
INSERT INTO `emplea_o_ejecuta` VALUES (4,'No',NULL,0.35,'2','2022-06-24 01:37:21','2022-06-24 01:37:21'),(5,'Si',NULL,0.00,'1','2022-06-24 01:37:21','2022-06-24 01:37:21');
/*!40000 ALTER TABLE `emplea_o_ejecuta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estatus_info_riesgo`
--

LOCK TABLES `estatus_info_riesgo` WRITE;
/*!40000 ALTER TABLE `estatus_info_riesgo` DISABLE KEYS */;
INSERT INTO `estatus_info_riesgo` VALUES (1,'Información inicial','INFOI',NULL,'2022-06-09 04:57:00','2022-06-09 04:57:00',1),(2,'Causas','CAUSA',NULL,'2022-06-09 04:57:00','2022-06-09 04:57:00',2),(3,'Consecuencias','CONSE',NULL,'2022-06-09 04:58:34','2022-06-09 04:58:34',3),(4,'Probabilidad','PROBA',NULL,'2022-06-09 04:58:34','2022-06-09 04:58:34',4),(5,'Riesgo inherente','RINHE',NULL,'2022-06-09 04:58:34','2022-06-09 04:58:34',5),(6,'Valoración','VALOR',NULL,'2022-06-09 04:58:34','2022-06-09 04:58:34',6),(7,'Riesgo residual','RRESI',NULL,'2022-06-09 04:58:34','2022-06-09 04:58:34',7),(8,'Finalizado','FINAL',NULL,'2022-07-15 02:53:19','2022-07-15 02:53:19',8);
/*!40000 ALTER TABLE `estatus_info_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evaluacion`
--

LOCK TABLES `evaluacion` WRITE;
/*!40000 ALTER TABLE `evaluacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evidencia`
--

LOCK TABLES `evidencia` WRITE;
/*!40000 ALTER TABLE `evidencia` DISABLE KEYS */;
INSERT INTO `evidencia` VALUES (4,20,13,'Resolución Rectoral para la creación del Comité, Acta de reuniones','2022-07-22 05:36:23','2022-08-19 00:26:01',6,15,0,1),(5,0,14,'Reuniones para determinar las estrategias a seguir, Publicaciones, Correos masivos','2022-07-28 10:24:14','2022-07-28 10:24:14',3,0,1,1);
/*!40000 ALTER TABLE `evidencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evidencia_observacion`
--

LOCK TABLES `evidencia_observacion` WRITE;
/*!40000 ALTER TABLE `evidencia_observacion` DISABLE KEYS */;
INSERT INTO `evidencia_observacion` VALUES (43,68,4,'2022-08-18 22:04:00','2022-08-18 22:04:00',0),(44,71,4,'2022-08-19 00:24:10','2022-08-19 00:24:10',0),(47,75,4,'2022-09-03 00:33:33','2022-09-03 00:33:33',0);
/*!40000 ALTER TABLE `evidencia_observacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `monitoreo`
--

LOCK TABLES `monitoreo` WRITE;
/*!40000 ALTER TABLE `monitoreo` DISABLE KEYS */;
/*!40000 ALTER TABLE `monitoreo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notificacion`
--

LOCK TABLES `notificacion` WRITE;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `observacion`
--

LOCK TABLES `observacion` WRITE;
/*!40000 ALTER TABLE `observacion` DISABLE KEYS */;
INSERT INTO `observacion` VALUES (67,'Hay errores de ortografía en la definición de los controles',1,10,9,'2022-08-18 21:33:17','2022-08-19 00:18:50',1),(68,'Solo se ha cargado un soporte hasta la fecha',0,9,9,'2022-08-18 22:03:59','2022-08-30 11:48:07',4),(69,'No de identificaron controles residuales para la causa X',1,10,9,'2022-08-19 00:16:20','2022-08-19 00:19:08',3),(70,'El líder de proceso no ha corregido las observaciones de la OPDI',1,10,10,'2022-08-19 00:17:25','2022-08-19 00:18:42',1),(71,'Falta un soporte de video',1,9,10,'2022-08-19 00:24:09','2022-08-19 00:25:00',1),(72,'identificación incorrecta del riesgo ',1,10,9,'2022-09-02 21:33:04','2022-09-02 21:33:54',1),(73,'Sin evidencia',0,9,9,'2022-09-02 21:49:46','2022-09-02 21:49:46',0),(74,'Evidencia publicada en la web en link xx',1,9,9,'2022-09-02 21:50:43','2022-09-02 21:50:43',0),(75,'falta evidencias ',0,9,9,'2022-09-03 00:33:33','2022-09-03 00:33:33',0);
/*!40000 ALTER TABLE `observacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `opcion_consecuencia_riesgo`
--

LOCK TABLES `opcion_consecuencia_riesgo` WRITE;
/*!40000 ALTER TABLE `opcion_consecuencia_riesgo` DISABLE KEYS */;
/*!40000 ALTER TABLE `opcion_consecuencia_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `opciones_consecuencias`
--

LOCK TABLES `opciones_consecuencias` WRITE;
/*!40000 ALTER TABLE `opciones_consecuencias` DISABLE KEYS */;
INSERT INTO `opciones_consecuencias` VALUES (116,'x1',1,_binary '\0',55,'2022-08-23 04:43:47','2022-08-23 04:43:47',0),(120,'Nueva',3,_binary '\0',55,'2022-08-23 05:24:38','2022-08-23 05:24:38',0),(121,'x3',5,_binary '\0',55,'2022-08-23 05:24:50','2022-08-23 05:24:50',0);
/*!40000 ALTER TABLE `opciones_consecuencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `periodicidad`
--

LOCK TABLES `periodicidad` WRITE;
/*!40000 ALTER TABLE `periodicidad` DISABLE KEYS */;
INSERT INTO `periodicidad` VALUES (1,'Diario','Periodicidad diaria',0.00,'1','2022-06-24 11:11:22','2022-06-24 11:11:22'),(2,'Semanal','Periodicidad semanal',0.00,'2','2022-06-24 11:11:22','2022-06-24 11:11:22'),(3,'Mensual','Periodicidad mensual',0.00,'3','2022-06-24 11:11:22','2022-06-24 11:11:22'),(4,'Bimensual','Periodicidad bimensual',0.00,'4','2022-06-24 11:11:22','2022-06-24 11:11:22'),(5,'Trimestral','Periodicidad trimestral',0.00,'5','2022-06-24 11:11:22','2022-06-24 11:11:22'),(6,'Cuatrimestral','Periodicidad cuatrimestral',0.00,'6','2022-06-24 11:11:22','2022-06-24 11:11:22'),(7,'Anual','Periodicidad anual',0.00,'7','2022-06-24 11:11:22','2022-06-24 11:11:22'),(8,'Eventual','Periodicidad eventual',0.00,'8','2022-06-24 11:11:22','2022-06-24 11:11:22'),(9,'Permanente','Periodicidad permanente',0.00,'9','2022-06-24 11:11:22','2022-06-24 11:11:22'),(10,'No','No',0.10,'10','2022-07-14 07:08:23','2022-07-14 07:08:23'),(11,'Semestral','Periodicidad semestral',0.00,'11','2022-08-03 09:05:13','2022-08-03 09:05:13');
/*!40000 ALTER TABLE `periodicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `riesgo_observacion`
--

LOCK TABLES `riesgo_observacion` WRITE;
/*!40000 ALTER TABLE `riesgo_observacion` DISABLE KEYS */;
INSERT INTO `riesgo_observacion` VALUES (24,23,67,'2022-08-18 21:33:18','2022-08-18 21:33:18',0),(25,23,69,'2022-08-19 00:16:21','2022-08-19 00:16:21',0),(26,23,70,'2022-08-19 00:17:26','2022-08-19 00:17:26',0);
/*!40000 ALTER TABLE `riesgo_observacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `riesgo_residual`
--

LOCK TABLES `riesgo_residual` WRITE;
/*!40000 ALTER TABLE `riesgo_residual` DISABLE KEYS */;
INSERT INTO `riesgo_residual` VALUES (6,'reducir',23,'2022-07-22 00:42:52','2022-07-22 00:42:52',0),(24,'reducir',26,'2022-08-26 10:34:38','2022-08-26 10:34:38',0);
/*!40000 ALTER TABLE `riesgo_residual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `riesgos`
--

LOCK TABLES `riesgos` WRITE;
/*!40000 ALTER TABLE `riesgos` DISABLE KEYS */;
INSERT INTO `riesgos` VALUES (23,'Rezago institucional en la realización de actividades tendientes a la internacionalización\r\n',_binary '','Objetivo del riesgo',2,'2022-07-22 00:25:21','2022-07-22 00:25:21',0,NULL,'','','',NULL,1,1,13,8,'Pérdida de oportunidades para la institución, los estudiantes y de competitividad en el mercado laboral para los egresados'),(26,'Demoras en la elaboración de convenios y conceptos jurídicos.',_binary '','Objetivo del riesgo',5,'2022-08-17 11:00:07','2022-08-26 10:33:43',4,NULL,'','','',NULL,2,1,1,8,'Atraso en la ejecución de los trámites ');
/*!40000 ALTER TABLE `riesgos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador','ADMIN','Representa un administrador de la aplicación','2022-03-15 15:04:59','2022-03-15 15:04:59',NULL),(2,'Funcionario OCI','OCI','Representa un funcionario de la Oficina de Control Interno','2022-03-15 15:05:38','2022-03-15 15:05:38',NULL),(3,'Funcionario OPDI','OPDI','Respresenta un funcionario de la OPDI','2022-03-29 15:27:43','2022-03-29 15:27:43',NULL),(4,'Líder de proceso','LIDER','Representa un líder de proceso','2022-03-29 15:28:33','2022-03-29 15:28:33',NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `soporte_evidencia`
--

LOCK TABLES `soporte_evidencia` WRITE;
/*!40000 ALTER TABLE `soporte_evidencia` DISABLE KEYS */;
INSERT INTO `soporte_evidencia` VALUES (29,'Acta de la reunión',4,'4845cea3-72f7-4ebf-bf78-8a51c2e1e00f_certifiación personal.docx','2022-08-19 00:22:46','2022-08-19 00:22:46',0);
/*!40000 ALTER TABLE `soporte_evidencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_actor`
--

LOCK TABLES `tipo_actor` WRITE;
/*!40000 ALTER TABLE `tipo_actor` DISABLE KEYS */;
INSERT INTO `tipo_actor` VALUES (9,'2022-04-26 16:27:10','2022-04-26 16:27:10','OPDI','1','Observación creada por un funcionario de la OPDI'),(10,'2022-04-26 16:27:10','2022-04-26 16:27:10','OCI','2','Observación creada por un funcionario de la OCI');
/*!40000 ALTER TABLE `tipo_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_afectacion`
--

LOCK TABLES `tipo_afectacion` WRITE;
/*!40000 ALTER TABLE `tipo_afectacion` DISABLE KEYS */;
INSERT INTO `tipo_afectacion` VALUES (1,'Estratégica','Afectación estratégica','1','2022-06-13 10:23:03','2022-06-13 10:23:03'),(2,'Operacional','Afectación operacional','2','2022-06-13 10:25:56','2022-06-13 10:25:56'),(3,'Presupuestal','Afectación presupuestal','3','2022-06-13 10:25:56','2022-06-13 10:25:56'),(4,'Tecnológica','Afectación tecnológica','4','2022-06-13 10:25:56','2022-06-13 10:25:56'),(5,'Cumplimiento','Afectacion de cumplimiento','5','2022-06-13 10:25:56','2022-06-13 10:25:56'),(6,'Imagen','Afectacion de imagen','6','2022-06-13 10:25:56','2022-06-13 10:25:56'),(7,'Corrupción','Afectación de corrupción','7','2022-06-13 10:25:56','2022-06-13 10:25:56'),(8,'Seguridad digital','Afectación de seguridad digital','8','2022-06-13 10:25:56','2022-06-13 10:25:56'),(9,'Ambiental','Afectación ambiental','9','2022-06-13 10:25:56','2022-06-13 10:25:56'),(10,'Misional','Afectación misional','10','2022-06-13 10:25:56','2022-06-13 10:25:56');
/*!40000 ALTER TABLE `tipo_afectacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_de_control`
--

LOCK TABLES `tipo_de_control` WRITE;
/*!40000 ALTER TABLE `tipo_de_control` DISABLE KEYS */;
INSERT INTO `tipo_de_control` VALUES (1,'De Gestión','Son aquellos tendientes a garantizar la ejecución de planes, políticas y objetivos institucionales, entre ellos: Indicadores de Gestión, Auditorías, Informes ejecutivos, la creación de organismos para su desarrollo y seguimiento (Comités), contratos específicos, entre otros. Ejemplo: la vinculación de gestores de calidad que verifican el cumplimiento de las orientaciones de calidad.',0.15,'1','2022-04-22 14:16:06','2022-04-22 14:16:06'),(2,'Estratégicos','Los que plasman la voluntad de la dirección universitaria y de los líderes de los procesos. Ejemplo: Políticas, Planes, programas y proyectos.',0.15,'2','2022-04-22 14:16:07','2022-04-22 14:16:07'),(3,'Operativos','Se enfocan en documentar la ejecución de las actividades, pueden ser nacionales o internos. ejemplo: Procedimientos, manuales, guías, protocolos, instructivos, y sus herramientas de aplicación (Listas de verificación, actas, formatos) y cualquier documento que relacione funciones y responsabilidades.',0.15,'3','2022-04-22 14:16:08','2022-04-22 14:16:08'),(4,'Preventivos','Evitan la materialización del riesgo atacando las causas generadoras del mismo. Ejemplo: La autorización de accesos a los sistemas de información, previene que personas no autorizadas ingresen.',0.30,'4','2022-04-22 14:16:09','2022-04-22 14:16:09'),(5,'Detectivos','Permiten registrar eventos ocurridos, pero no siempre evitan la materialización del riesgo, este control busca verificar, validar, cotejar, comparar o revisar. Ejemplo: Los registros de ingreso y salida a las instalaciones pueden detectar al infractor de cierta norma dentro de la institución.',0.10,'5','2022-08-03 10:13:46','2022-08-03 10:13:46'),(6,'Legales y reglamentarios','Son las normas nacionales e internas que regulan la situación específicamente. Ejemplo: Leyes, Acuerdos y Resoluciones.',0.15,'6','2022-08-03 10:13:47','2022-08-03 10:13:47');
/*!40000 ALTER TABLE `tipo_de_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_observacion`
--

LOCK TABLES `tipo_observacion` WRITE;
/*!40000 ALTER TABLE `tipo_observacion` DISABLE KEYS */;
INSERT INTO `tipo_observacion` VALUES (9,'2022-04-26 16:26:16','2022-04-26 16:26:16','EVIDENCIA','1','Observación sobre una evidencia'),(10,'2022-04-26 16:26:16','2022-04-26 16:26:16','RIESGO','2','Observación sobre el riesgo');
/*!40000 ALTER TABLE `tipo_observacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_proceso`
--

LOCK TABLES `tipo_proceso` WRITE;
/*!40000 ALTER TABLE `tipo_proceso` DISABLE KEYS */;
INSERT INTO `tipo_proceso` VALUES (1,'2022-04-09 13:50:21','2022-04-09 13:50:21','Gestión de la Dirección Universitaria','1','Gestión de la Dirección Universitaria'),(2,'2022-04-09 13:50:21','2022-04-09 13:50:21','Gestión de la Planeación y desarrollo Institucional','2',' Gestión de la Planeación y desarrollo Institucional'),(3,'2022-04-09 13:50:22','2022-04-09 13:50:22','Gestión de la Calidad (Involucra Acreditación y Certificación)','3','Gestión de la Calidad (Involucra Acreditación y Certificación)'),(4,'2022-04-09 13:50:22','2022-04-09 13:50:22','Gestión académica','4','Gestión académica'),(5,'2022-04-09 13:50:22','2022-04-09 13:50:22','Gestión de la investigación, innovación e Interacción social','5','Gestión de la investigación, innovación e Interacción social'),(6,'2022-04-09 13:50:23','2022-04-09 13:50:23','Gestión de cultura y bienestar','6','Gestión de cultura y bienestar'),(7,'2022-04-09 13:50:23','2022-04-09 13:50:23','Gestión administrativa y financiera','7','Gestión administrativa y financiera'),(8,'2022-04-09 13:50:23','2022-04-09 13:50:23','Gestión de Control y Mejoramiento continuo','8','Gestión de Control \r\ny Mejoramiento continuo');
/*!40000 ALTER TABLE `tipo_proceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_riesgo`
--

LOCK TABLES `tipo_riesgo` WRITE;
/*!40000 ALTER TABLE `tipo_riesgo` DISABLE KEYS */;
INSERT INTO `tipo_riesgo` VALUES (1,'Académicos','1','La calidad del servicio misional.','2022-04-05 14:23:03','2022-04-05 14:23:03'),(2,'Estratégicos','2','El cumplimiento de la misión, visión, objetivos y políticas.','2022-04-05 14:23:03','2022-04-05 14:23:03'),(3,'Operativos','3','La operación de los procesos y sus relaciones.','2022-04-05 14:23:03','2022-04-05 14:23:03'),(5,'Cumplimiento','5','Acatamiento de normas, principios, valores y la calidad del servicio, así como procesos contractuales y litigios judiciales.','2022-08-17 10:55:57','2022-08-17 10:55:57'),(6,'Imagen','6','El buen nombre y la confianza en la institución.','2022-08-17 10:57:54','2022-08-17 10:57:54'),(7,'Seguridad digital','7','Integridad, confidencialidad y disponibilidad de la información.','2022-08-17 10:57:54','2022-08-17 10:57:54'),(8,'Financieros','8','La administración de bienes y todas aquellos procesos involucrados con el proceso financiero como presupuesto, tesorería, contabilidad, cartera, central de cuentas, costos, etc.','2022-08-17 10:57:54','2022-08-17 10:57:54'),(9,'Tecnológicos','9','La infraestructura y capacidad tecnológica.','2022-08-17 10:58:35','2022-08-17 10:58:35'),(10,'Corrupción por fraude interno','4','El interés público y los principios de la función pública. Pérdida debido a actos de fraude, actuaciones irregulares, comisión de hechos delictivos abuso de confianza, apropiación indebida, incumplimiento de regulaciones legales o internas de la entidad en las cuales está involucrado por lo menos 1 participante interno de la organización, son realizadas de forma intencional y/o con ánimo de lucro para sí mismo o para terceros.','2022-04-05 14:28:12','2022-04-05 14:28:12'),(11,'Corrupción por fraude externo','11','Pérdida derivada de actos de fraude por personas ajenas a la organización (no participa personal de la entidad).','2022-04-05 14:28:12','2022-04-05 14:28:12'),(12,'Ambientales','10','El medio ambiente, los recursos naturales no renovables.','2022-09-29 06:23:27','2022-09-29 06:23:27');
/*!40000 ALTER TABLE `tipo_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_tratamiento`
--

LOCK TABLES `tipo_tratamiento` WRITE;
/*!40000 ALTER TABLE `tipo_tratamiento` DISABLE KEYS */;
INSERT INTO `tipo_tratamiento` VALUES (1,'Reducir','1','Se adoptan nuevos controles o revalúan los existentes.','2022-04-09 14:14:16','2022-04-09 14:14:16'),(2,'Asumir','2','Sólo aplica al Riesgo Bajo. No se toma ninguna acción; sin embargo puede aplicarse un control como alternativa, analizando su relación costo beneficio.','2022-09-29 06:13:50','2022-09-29 06:13:50'),(3,'Evitar','3','Se abandonan los procedimientos generadores del riesgo, como alternativa excepcional.','2022-09-29 06:13:50','2022-09-29 06:13:50'),(4,'Compartir','4','Transfiere el riesgo a terceros a través de contrato.','2022-09-29 06:13:50','2022-09-29 06:13:50');
/*!40000 ALTER TABLE `tipo_tratamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Alexander','Villaquiran','$2a$10$PdPae3u4DqHlTCryAG538.VYILFLu.JWZDyP8jfFR/ipUVD37lIqC','andyvilla@unicauca.edu.co','2022-03-16 10:25:46','2022-03-16 10:25:46',4,46,0,NULL,_binary ''),(2,'Camila','Martinez','$2a$10$PdPae3u4DqHlTCryAG538.VYILFLu.JWZDyP8jfFR/ipUVD37lIqC','camila@gmail.com','2022-03-16 11:35:48','2022-03-16 11:35:48',3,46,0,NULL,_binary ''),(3,'Daniela','Perez','$2a$10$PdPae3u4DqHlTCryAG538.VYILFLu.JWZDyP8jfFR/ipUVD37lIqC','danperez@mail.com','2022-03-16 14:18:02','2022-03-16 14:18:02',2,7,0,NULL,_binary ''),(4,'Sebastián David','Carabali Carabali','$2a$10$PdPae3u4DqHlTCryAG538.VYILFLu.JWZDyP8jfFR/ipUVD37lIqC','mail@mail.com','2022-03-22 02:35:48','2022-03-22 02:35:48',1,NULL,0,NULL,_binary ''),(5,'Fake','Fake','$2a$10$4ihdvMXwCaQSndDBvh6YOeE4PRCJ0ROrbst1x2pY4tbm./Kx8mFTK','keiran.kalai@fillnoo.com','2022-07-18 13:59:14','2022-07-18 13:59:14',2,7,0,'690ad3bb-469f-4bd1-ac26-31a17ee59e92',_binary '\0'),(6,'Otro','Otro','$2a$10$hARfje2Vdc7271YsElRRf.Zfx0HhSB6wRMd8KRKVInXxUUjLYkPo2','romero.arjan@fillnoo.com','2022-07-18 14:15:15','2022-07-18 14:15:15',1,7,0,'ae584954-da9b-4f94-8456-798d7c3ea2fc',_binary '\0'),(9,'Otro','Fake','$2a$10$8erHncDQzW5L1vE3XJvufOZkCsnQj1zSasQQYkm51CCsEdCMwTqta','hokeyi3331@5k2u.com','2022-07-21 23:56:45','2022-07-21 23:56:45',4,7,0,'e888777d-e5c4-4e50-917d-8e2fdc942bd9',_binary '\0'),(10,'Fake','Asycn','$2a$10$PdPae3u4DqHlTCryAG538.VYILFLu.JWZDyP8jfFR/ipUVD37lIqC','raxanox285@agrolivana.com','2022-07-22 00:12:28','2022-07-22 00:12:28',2,7,0,'86e678ed-7734-4712-8a0c-e21612348678',_binary ''),(11,'Líder','Proceso','$2a$10$FaXzxvh/JsWQ0wJ6MlqzveEl/Yi1WFrJqsUI38FYEpINQHm3YdDu6','jacks.daiki@fillnoo.com','2022-07-22 00:19:10','2022-09-01 15:07:01',4,46,2,'44ba0425-27c5-49b0-9d9e-ca6136eb25b0',_binary ''),(12,'Daniela','Martinez','$2a$10$1XM8aCbwLgp1NElNOgi3M.ooJ7AslnveE2Y7Zd8rsN8n6Tz35n0WK','doryan.loui@fillnoo.com','2022-08-11 01:23:35','2022-08-11 01:23:35',2,7,0,'f438290d-a5c2-48af-8023-b95636274307',_binary '\0'),(13,'juan','Perez','$2a$10$PdPae3u4DqHlTCryAG538.VYILFLu.JWZDyP8jfFR/ipUVD37lIqC','pepito@gmail.com','2022-03-16 10:25:46','2022-08-19 04:12:37',4,46,1,NULL,_binary ''),(15,'Fake','Test','$2a$10$TC0XV5Mxq2ddUoyEjL9X5.QQdy0bfQwVYbufxhp9Njq0B.lkYWD.e','mosibah246@ulforex.com','2022-09-06 07:53:10','2022-09-06 07:53:10',2,7,0,'3151fb7e-6a9e-448a-84e2-4a56232bd853',_binary ''),(16,'Fake ','New','$2a$10$7qvxzNlwmeYDK55I3owX9OAcHIT1gP/MqMrIseIMppc56FLGXvEi6','wefipoj748@oncebar.com','2022-09-07 01:42:17','2022-09-07 01:48:22',4,46,1,'7d316729-ec32-420d-8ba3-9b7b9c0c7cae',_binary '');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `version_riesgo`
--

LOCK TABLES `version_riesgo` WRITE;
/*!40000 ALTER TABLE `version_riesgo` DISABLE KEYS */;
INSERT INTO `version_riesgo` VALUES (1,'Mapa de riesgos 2021','6d2e5f00-35d0-4a55-b2b3-8bb0a9f7ea9e_Mapa_Integral_Riesgos_Institucionales2021.pdf','2022-08-23 06:44:58','2022-08-24 05:25:42',6),(6,'Mapa de riesgos 2022','5bd87a46-5eee-4048-b3dc-1513f189e20e_Mapa_Riesgo_InstitucionalV12022.pdf','2022-08-24 05:49:55','2022-09-01 10:54:21',4);
/*!40000 ALTER TABLE `version_riesgo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-29 22:35:48
