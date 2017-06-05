-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: www.yinzhiwu.com    Database: yiwu
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler','orderBrockerageCronTrigger','DEFAULT','0 0 4 1/1 * ?','Asia/Shanghai');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` VALUES ('scheduler','orderBrockerageJobDetail','DEFAULT',NULL,'com.yinzhiwu.springmvc3.timer.OrderBrockerageJob','1','0','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0');
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` VALUES ('scheduler','orderBrockerageCronTrigger','DEFAULT','orderBrockerageJobDetail','DEFAULT',NULL,1496692800000,1496606400000,0,'WAITING','CRON',1494591731000,0,NULL,2,'');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vappointment`
--

DROP TABLE IF EXISTS `vappointment`;
/*!50001 DROP VIEW IF EXISTS `vappointment`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vappointment` AS SELECT 
 1 AS `id`,
 1 AS `courseHourId`,
 1 AS `customerId`,
 1 AS `status`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machinecode`,
 1 AS `Sf_Last_Sync_TimeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vcheck_ins`
--

DROP TABLE IF EXISTS `vcheck_ins`;
/*!50001 DROP VIEW IF EXISTS `vcheck_ins`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vcheck_ins` AS SELECT 
 1 AS `id`,
 1 AS `memberCard`,
 1 AS `lessonId`,
 1 AS `contract`,
 1 AS `teacherId`,
 1 AS `comsumeSd`,
 1 AS `flag`,
 1 AS `isRetroactive`,
 1 AS `storemanCallroll`,
 1 AS `uncallrollReason`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`,
 1 AS `sf_last_change_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vclassroom`
--

DROP TABLE IF EXISTS `vclassroom`;
/*!50001 DROP VIEW IF EXISTS `vclassroom`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vclassroom` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `store_id`,
 1 AS `storeName`,
 1 AS `maxStudentCount`,
 1 AS `minStudentCount`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`,
 1 AS `sf_last_change_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vcourse`
--

DROP TABLE IF EXISTS `vcourse`;
/*!50001 DROP VIEW IF EXISTS `vcourse`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vcourse` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `dance_id`,
 1 AS `danceDesc`,
 1 AS `store_id`,
 1 AS `storeName`,
 1 AS `teacher_id`,
 1 AS `teacherName`,
 1 AS `interval_id`,
 1 AS `intervalDesc`,
 1 AS `startTime`,
 1 AS `endTime`,
 1 AS `startDate`,
 1 AS `endDate`,
 1 AS `sumCourseHours`,
 1 AS `classRoom_id`,
 1 AS `classRoomName`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `MachineCode`,
 1 AS `sf_last_sync_timeStamp`,
 1 AS `weeklyCourseHours`,
 1 AS `delete_flag`,
 1 AS `weeks`,
 1 AS `courseType`,
 1 AS `monInterval_id`,
 1 AS `monRoom_id`,
 1 AS `tueInterval_id`,
 1 AS `tueRoom_id`,
 1 AS `wedInterval_id`,
 1 AS `wedRoom_id`,
 1 AS `thuInterval_id`,
 1 AS `thuRoom_id`,
 1 AS `friInterval_id`,
 1 AS `friRoom_id`,
 1 AS `satInterval_id`,
 1 AS `satRoom_id`,
 1 AS `sunInterval_id`,
 1 AS `sunRoom_id`,
 1 AS `status`,
 1 AS `StudentCount`,
 1 AS `sf_last_change_timeStamp`,
 1 AS `include_holeday_flag`,
 1 AS `DanceGrade`,
 1 AS `ShenheRen`,
 1 AS `connotation`,
 1 AS `help`,
 1 AS `briefIntroduction`,
 1 AS `picture`,
 1 AS `videoURL`,
 1 AS `audio`,
 1 AS `audioURL`,
 1 AS `danceIntroduction`,
 1 AS `subCourseType`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vcustomer`
--

DROP TABLE IF EXISTS `vcustomer`;
/*!50001 DROP VIEW IF EXISTS `vcustomer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vcustomer` AS SELECT 
 1 AS `Id`,
 1 AS `salesmanId`,
 1 AS `audit_child`,
 1 AS `isMember`,
 1 AS `memberCard`,
 1 AS `name`,
 1 AS `sex`,
 1 AS `mobilePhone`,
 1 AS `residentId`,
 1 AS `birthday`,
 1 AS `age`,
 1 AS `QQ`,
 1 AS `wechat`,
 1 AS `address`,
 1 AS `company`,
 1 AS `profession`,
 1 AS `interesting`,
 1 AS `photo`,
 1 AS `customer_level`,
 1 AS `source_of_customer`,
 1 AS `date_of_next_track`,
 1 AS `times_of_untrack`,
 1 AS `times_of_remainder_course`,
 1 AS `password`,
 1 AS `credit`,
 1 AS `earnest`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `MachineCode`,
 1 AS `sf_Last_Sync_TimeStamp`,
 1 AS `sf_last_change_timestamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vdance`
--

DROP TABLE IF EXISTS `vdance`;
/*!50001 DROP VIEW IF EXISTS `vdance`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vdance` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `danceClass`,
 1 AS `remuneration`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`,
 1 AS `sf_last_change_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vdance_grade`
--

DROP TABLE IF EXISTS `vdance_grade`;
/*!50001 DROP VIEW IF EXISTS `vdance_grade`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vdance_grade` AS SELECT 
 1 AS `id`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vdepartment`
--

DROP TABLE IF EXISTS `vdepartment`;
/*!50001 DROP VIEW IF EXISTS `vdepartment`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vdepartment` AS SELECT 
 1 AS `Id`,
 1 AS `Name`,
 1 AS `superiorId`,
 1 AS `path`,
 1 AS `manager1`,
 1 AS `manager2`,
 1 AS `description`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `removed`,
 1 AS `flag`,
 1 AS `wparam`,
 1 AS `lparam`,
 1 AS `machineCode`,
 1 AS `sf_Last_Sync_TimeStamp`,
 1 AS `sf_last_change_timeStamp`,
 1 AS `operationDistrict`,
 1 AS `city`,
 1 AS `officialAccount`,
 1 AS `logo`,
 1 AS `province`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `velectric_contract`
--

DROP TABLE IF EXISTS `velectric_contract`;
/*!50001 DROP VIEW IF EXISTS `velectric_contract`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `velectric_contract` AS SELECT 
 1 AS `contractNo`,
 1 AS `customerName`,
 1 AS `gender`,
 1 AS `birthday`,
 1 AS `IdentityCardNo`,
 1 AS `mobiePhoneNo`,
 1 AS `qqNo`,
 1 AS `wechatNo`,
 1 AS `customerId`,
 1 AS `contactAddress`,
 1 AS `homeAddress`,
 1 AS `recommendMemberCardNo`,
 1 AS `memberCardNo`,
 1 AS `effectiveStart`,
 1 AS `effectiveEnd`,
 1 AS `timesOfLesson`,
 1 AS `price`,
 1 AS `amount`,
 1 AS `promotionPrice`,
 1 AS `rangeOfApplication`,
 1 AS `supplementalInstruction`,
 1 AS `uppcaseAmount`,
 1 AS `lowercaseAmount`,
 1 AS `payedDate`,
 1 AS `payedMethod`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `depositAmount`,
 1 AS `depositDate`,
 1 AS `finalPayment`,
 1 AS `finalDate`,
 1 AS `contractType`,
 1 AS `isConfirmed`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `velectric_contract_type`
--

DROP TABLE IF EXISTS `velectric_contract_type`;
/*!50001 DROP VIEW IF EXISTS `velectric_contract_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `velectric_contract_type` AS SELECT 
 1 AS `id`,
 1 AS `description`,
 1 AS `content`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vemployee`
--

DROP TABLE IF EXISTS `vemployee`;
/*!50001 DROP VIEW IF EXISTS `vemployee`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vemployee` AS SELECT 
 1 AS `ID`,
 1 AS `User`,
 1 AS `SeegleUserID`,
 1 AS `name`,
 1 AS `PassWord`,
 1 AS `Sex`,
 1 AS `Birthday`,
 1 AS `Tel`,
 1 AS `CellPhone`,
 1 AS `Fax`,
 1 AS `Email`,
 1 AS `Disabled`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `CreateTime`,
 1 AS `sf_last_change_time`,
 1 AS `Removed`,
 1 AS `wparam`,
 1 AS `lparam`,
 1 AS `machineCode`,
 1 AS `SeegleUserName`,
 1 AS `SeegleSerialNum`,
 1 AS `UserType`,
 1 AS `AccessCode`,
 1 AS `BindMac`,
 1 AS `sf_Last_Sync_TimeStamp`,
 1 AS `sf_last_change_timeStamp`,
 1 AS `fingerPrintNo`,
 1 AS `departmentId`,
 1 AS `defaultDuty`,
 1 AS `OutUser`,
 1 AS `ClusterServerIP`,
 1 AS `ClusterServerPort`,
 1 AS `ClusterToken`,
 1 AS `Last_Online_TimeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vemployee_post`
--

DROP TABLE IF EXISTS `vemployee_post`;
/*!50001 DROP VIEW IF EXISTS `vemployee_post`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vemployee_post` AS SELECT 
 1 AS `id`,
 1 AS `employeeId`,
 1 AS `postID`,
 1 AS `lastChangerId`,
 1 AS `lastChangeTime`,
 1 AS `removed`,
 1 AS `machineCode`,
 1 AS `lastSyncTimestamp`,
 1 AS `lastChangeTimestamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vinterval`
--

DROP TABLE IF EXISTS `vinterval`;
/*!50001 DROP VIEW IF EXISTS `vinterval`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vinterval` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `start`,
 1 AS `end`,
 1 AS `hours`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_time_stamp`,
 1 AS `sf_last_change_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vlesson`
--

DROP TABLE IF EXISTS `vlesson`;
/*!50001 DROP VIEW IF EXISTS `vlesson`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vlesson` AS SELECT 
 1 AS `id`,
 1 AS `courseId`,
 1 AS `lessonDate`,
 1 AS `startTime`,
 1 AS `endTime`,
 1 AS `courseDesc`,
 1 AS `storeId`,
 1 AS `storeName`,
 1 AS `lessonTime`,
 1 AS `yindaoTeacherId`,
 1 AS `yindaoTeacherName`,
 1 AS `classRoomId`,
 1 AS `classRoomName`,
 1 AS `shidaoTeacherId`,
 1 AS `shidaoTeacherName`,
 1 AS `lessonStatus`,
 1 AS `courseType`,
 1 AS `subCourseType`,
 1 AS `flag_delete`,
 1 AS `start_date_time`,
 1 AS `lessonTime_minutes`,
 1 AS `appointed_huijiheyue`,
 1 AS `yindaoRenshu`,
 1 AS `tiyanRenshu`,
 1 AS `dianmingRenshu`,
 1 AS `shidaoRenshu`,
 1 AS `qiandaoRenshu`,
 1 AS `yuyueRenshu`,
 1 AS `neihan`,
 1 AS `help`,
 1 AS `jianjie`,
 1 AS `picture`,
 1 AS `video`,
 1 AS `audio`,
 1 AS `audio_link`,
 1 AS `dance_introduction`,
 1 AS `QRcode`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`,
 1 AS `sf_last_change_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vorder`
--

DROP TABLE IF EXISTS `vorder`;
/*!50001 DROP VIEW IF EXISTS `vorder`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vorder` AS SELECT 
 1 AS `id`,
 1 AS `memberCardNo`,
 1 AS `product_id`,
 1 AS `markedPrice`,
 1 AS `preferential`,
 1 AS `count`,
 1 AS `discount`,
 1 AS `payedAmount`,
 1 AS `customer_id`,
 1 AS `payed_date`,
 1 AS `course_id`,
 1 AS `checked_status`,
 1 AS `store_id`,
 1 AS `vip_attr`,
 1 AS `contractNo`,
 1 AS `validity`,
 1 AS `validity_times`,
 1 AS `startdate`,
 1 AS `endDate`,
 1 AS `remain_times`,
 1 AS `product_type`,
 1 AS `product_subType`,
 1 AS `recommender`,
 1 AS `valid_storeids`,
 1 AS `pic`,
 1 AS `ask_for_leave_flag`,
 1 AS `comments`,
 1 AS `relative_SD`,
 1 AS `check_privilege`,
 1 AS `current_status`,
 1 AS `remain_hours`,
 1 AS `audit_flag`,
 1 AS `e_contract_text`,
 1 AS `e_contract_address`,
 1 AS `e_contract_picture_flag`,
 1 AS `e_contract_status`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `MachineCode`,
 1 AS `sf_last_sync_TimeStamp`,
 1 AS `sf_last_change_timestamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vorder_payed_method`
--

DROP TABLE IF EXISTS `vorder_payed_method`;
/*!50001 DROP VIEW IF EXISTS `vorder_payed_method`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vorder_payed_method` AS SELECT 
 1 AS `id`,
 1 AS `orderId`,
 1 AS `payedMethodId`,
 1 AS `amount`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`,
 1 AS `sf_last_change_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vpayed_method`
--

DROP TABLE IF EXISTS `vpayed_method`;
/*!50001 DROP VIEW IF EXISTS `vpayed_method`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vpayed_method` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `createUserId`,
 1 AS `lastChangeUserId`,
 1 AS `createTime`,
 1 AS `lastChangeTime`,
 1 AS `machineCode`,
 1 AS `lastSyncTimestamp`,
 1 AS `lastChangeTimestamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vpost`
--

DROP TABLE IF EXISTS `vpost`;
/*!50001 DROP VIEW IF EXISTS `vpost`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vpost` AS SELECT 
 1 AS `ID`,
 1 AS `Type`,
 1 AS `Name`,
 1 AS `Description`,
 1 AS `Creator`,
 1 AS `LastChanger`,
 1 AS `CreateTime`,
 1 AS `LastChangeTime`,
 1 AS `Removed`,
 1 AS `flag`,
 1 AS `wparam`,
 1 AS `lparam`,
 1 AS `MachineCode`,
 1 AS `Sf_Last_Sync_TimeStamp`,
 1 AS `SF_Last_Change_Timestamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vproduct`
--

DROP TABLE IF EXISTS `vproduct`;
/*!50001 DROP VIEW IF EXISTS `vproduct`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vproduct` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `card_type`,
 1 AS `customer_type`,
 1 AS `marked_price`,
 1 AS `useful_life`,
 1 AS `useful_times`,
 1 AS `obsolete_flag`,
 1 AS `DY_RCP`,
 1 AS `max_leave_times`,
 1 AS `sf_create_user`,
 1 AS `sf_last_change_user`,
 1 AS `sf_create_time`,
 1 AS `sf_last_change_time`,
 1 AS `sf_last_change_timeStamp`,
 1 AS `machineCode`,
 1 AS `sf_last_sync_timeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vstore_callroll`
--

DROP TABLE IF EXISTS `vstore_callroll`;
/*!50001 DROP VIEW IF EXISTS `vstore_callroll`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vstore_callroll` AS SELECT 
 1 AS `id`,
 1 AS `lessonId`,
 1 AS `memberCard`,
 1 AS `flagCallroll`,
 1 AS `unCallrollReason`,
 1 AS `createUserId`,
 1 AS `lastChangeUserId`,
 1 AS `createTime`,
 1 AS `lastChangeTime`,
 1 AS `machineCode`,
 1 AS `lastSyncTimeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vteacher_callroll`
--

DROP TABLE IF EXISTS `vteacher_callroll`;
/*!50001 DROP VIEW IF EXISTS `vteacher_callroll`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vteacher_callroll` AS SELECT 
 1 AS `id`,
 1 AS `memberCard`,
 1 AS `courseType`,
 1 AS `lessonId`,
 1 AS `callroll`,
 1 AS `studentName`,
 1 AS `slotCardId`,
 1 AS `memberId`,
 1 AS `unCallRollReason`,
 1 AS `isFilledUp`,
 1 AS `createUserid`,
 1 AS `lastChangeUserId`,
 1 AS `createTime`,
 1 AS `lastChangeTime`,
 1 AS `lastChangeTimeStamp`,
 1 AS `machineCode`,
 1 AS `lastSyncTimeStamp`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `yiwu_account`
--

DROP TABLE IF EXISTS `yiwu_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(32) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_Account_employeeId` (`employee_id`),
  UNIQUE KEY `uk_Account_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_account`
--

LOCK TABLES `yiwu_account` WRITE;
/*!40000 ALTER TABLE `yiwu_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_capital_account`
--

DROP TABLE IF EXISTS `yiwu_capital_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_capital_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `account` varchar(50) NOT NULL,
  `capitalAccountType_id` int(11) DEFAULT NULL,
  `distributer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_CapitalAccount_accont` (`account`),
  KEY `fk_capitalAccount_capitalAccountType_id` (`capitalAccountType_id`),
  KEY `fk_CapitalAccount_distributer_id` (`distributer_id`),
  CONSTRAINT `fk_capitalAccount_capitalAccountType_id` FOREIGN KEY (`capitalAccountType_id`) REFERENCES `yiwu_type` (`id`),
  CONSTRAINT `fk_CapitalAccount_distributer_id` FOREIGN KEY (`distributer_id`) REFERENCES `yiwu_distributer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_capital_account`
--

LOCK TABLES `yiwu_capital_account` WRITE;
/*!40000 ALTER TABLE `yiwu_capital_account` DISABLE KEYS */;
INSERT INTO `yiwu_capital_account` VALUES (2000000,'2017-06-04 13:10:18',1,'2017-06-04 13:10:18',1,'longfree',17000014,3000002),(2000001,'2017-06-04 13:10:32',1,'2017-06-04 13:10:32',1,'hhbbbbn',17000014,3000002);
/*!40000 ALTER TABLE `yiwu_capital_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_distributer`
--

DROP TABLE IF EXISTS `yiwu_distributer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_distributer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  `accumulativeBrokerage` float NOT NULL,
  `accumulativeFunds` float NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `brokerage` float NOT NULL,
  `exp` float NOT NULL,
  `funds` float NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `headIconUrl` varchar(255) DEFAULT NULL,
  `memberId` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `nickName` varchar(32) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `phoneNo` varchar(32) NOT NULL,
  `registedTime` datetime DEFAULT NULL,
  `shareCode` varchar(10) DEFAULT NULL,
  `wechatNo` varchar(32) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `defaultCapitalAccount_id` int(11) DEFAULT NULL,
  `expGrade_id` int(11) DEFAULT NULL,
  `followedByStore_id` int(11) DEFAULT NULL,
  `super_distributer_id` int(11) DEFAULT NULL,
  `headIconName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_distributer_memberId` (`memberId`),
  UNIQUE KEY `uk_distributer_account` (`account`),
  UNIQUE KEY `uk_distributer_wechatNo` (`wechatNo`),
  UNIQUE KEY `uk_distributer_phoneNo` (`phoneNo`),
  UNIQUE KEY `uk_distributer_shareCode` (`shareCode`),
  UNIQUE KEY `fuk_distributer_customer_id` (`customer_id`),
  UNIQUE KEY `fuk_distributer_defaultCapitalAccount_id` (`defaultCapitalAccount_id`),
  UNIQUE KEY `uk_distributer_headIconName` (`headIconName`),
  KEY `fk_distributer_expGrade_id` (`expGrade_id`),
  KEY `fk_distributer_superDistributer_id` (`super_distributer_id`),
  CONSTRAINT `fk_distributer_defaultCapitalAccount_id` FOREIGN KEY (`defaultCapitalAccount_id`) REFERENCES `yiwu_capital_account` (`id`),
  CONSTRAINT `fk_distributer_expGrade_id` FOREIGN KEY (`expGrade_id`) REFERENCES `yiwu_exp_grade` (`id`),
  CONSTRAINT `fk_distributer_superDistributer_id` FOREIGN KEY (`super_distributer_id`) REFERENCES `yiwu_distributer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3000005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_distributer`
--

LOCK TABLES `yiwu_distributer` WRITE;
/*!40000 ALTER TABLE `yiwu_distributer` DISABLE KEYS */;
INSERT INTO `yiwu_distributer` VALUES (3000000,'2017-06-03 17:41:35',1,'2017-06-05 13:16:49',1,'18332633885',0,0,'1999-01-01 00:00:00',0,0,0,0,'http://oq3hegvvd.bkt.clouddn.com/android/exit3.jpg','E53000000','朱棣','明成祖','-10ki6j7vj978un6puqk71mt9l8','18332633885','2017-06-05 13:16:49','e3it8o3f','oIgTGwx1Qisvq4rsZ49zcwyz',45719,NULL,4000014,63,NULL,NULL),(3000001,'2017-06-04 01:23:52',1,'2017-06-04 01:23:52',1,'18815279924',0,0,NULL,0,0,0,0,NULL,'E53000001','18815279924','18815279924','-mcnjjnlpkosl0r7rqp63mrkft','18815279924','2017-06-04 01:23:52','e3ita','oIgTGw-zs2k_cgezdMUJ4C4S2S04',31554,NULL,4000014,63,NULL,NULL),(3000002,'2017-06-04 13:08:07',1,'2017-06-04 13:10:34',1,'18058782160',0,0,NULL,0,0,0,0,NULL,'E53000002','18058782160','18058782160','-36hoog68hn07oca8biicrtdem6','18058782160','2017-06-04 13:08:07','e3its','oIgTGwy8pL7MDj4H_jNVGO4uJGIE',34892,2000001,4000014,63,NULL,'E53000002.jpg'),(3000003,'2017-06-05 11:29:07',1,'2017-06-05 11:29:07',1,'18332633889',0,0,'1999-01-01 00:00:00',0,0,0,0,NULL,'E53000003','朱元璋','明太祖','-10ki6j7vj978un6puqk71mt9l8','18332633889','2017-06-05 11:29:07','e3it2','oIgTGwx1Qisvq4rsB49zcwyz',45720,NULL,4000014,63,NULL,NULL),(3000004,'2017-06-05 12:07:11',1,'2017-06-05 12:07:11',1,'18332644889',0,0,'1999-01-01 00:00:00',0,0,0,0,NULL,'E53000004','王麻子','王麻子','-10ki6j7vj978un6puqk71mt9l8','18332644889','2017-06-05 12:07:11','e3itd','oIgTGwx1Qisvq4ty949zcwyz',45721,NULL,4000014,63,NULL,NULL);
/*!40000 ALTER TABLE `yiwu_distributer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_exp_grade`
--

DROP TABLE IF EXISTS `yiwu_exp_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_exp_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `increaseDiscont` float NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `upgradeExp` float NOT NULL,
  `next_grade_id` int(11) DEFAULT NULL,
  `gradeNo` int(11) NOT NULL,
  `highesGrade` bit(1) DEFAULT NULL,
  `lowestGrade` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_expgrade_gradeNo` (`gradeNo`),
  UNIQUE KEY `uk_expgrade_name` (`name`),
  KEY `fk_expGrade_nextGrade_id` (`next_grade_id`),
  CONSTRAINT `fk_expGrade_nextGrade_id` FOREIGN KEY (`next_grade_id`) REFERENCES `yiwu_exp_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4000015 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_exp_grade`
--

LOCK TABLES `yiwu_exp_grade` WRITE;
/*!40000 ALTER TABLE `yiwu_exp_grade` DISABLE KEYS */;
INSERT INTO `yiwu_exp_grade` VALUES (4000000,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.15,'十五级',150000,NULL,15,'',NULL),(4000001,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.14,'十四级',140000,4000000,14,NULL,NULL),(4000002,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.13,'十三级',130000,4000001,13,NULL,NULL),(4000003,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.12,'十二级',120000,4000002,12,NULL,NULL),(4000004,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.11,'十一级',110000,4000003,11,NULL,NULL),(4000005,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.1,'十级',100000,4000004,10,NULL,NULL),(4000006,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.09,'九级',90000,4000005,9,NULL,NULL),(4000007,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.08,'八级',80000,4000006,8,NULL,NULL),(4000008,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.07,'七级',70000,4000007,7,NULL,NULL),(4000009,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.06,'六级',60000,4000008,6,NULL,NULL),(4000010,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.05,'五级',50000,4000009,5,NULL,NULL),(4000011,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.04,'四级',40000,4000010,4,NULL,NULL),(4000012,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.03,'三级',30000,4000011,3,NULL,NULL),(4000013,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.02,'二级',20000,4000012,2,NULL,NULL),(4000014,'2017-05-02 21:15:03',1,'2017-05-02 21:15:03',1,0.01,'一级',10000,4000013,1,NULL,'');
/*!40000 ALTER TABLE `yiwu_exp_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_exp_record`
--

DROP TABLE IF EXISTS `yiwu_exp_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_exp_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `contributedDate` datetime DEFAULT NULL,
  `contributedValue` float NOT NULL,
  `income` float NOT NULL,
  `incomeFactor` float NOT NULL,
  `currentExp` float NOT NULL,
  `beneficiaty_id` int(11) DEFAULT NULL,
  `contributor_id` int(11) DEFAULT NULL,
  `recordType_id` int(11) DEFAULT NULL,
  `shareTweet_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ExpRecord_shareTweet_id` (`shareTweet_id`),
  KEY `FK9eadbwck8n5ud16mdh7sqpiu3` (`contributor_id`),
  KEY `FKam3vberdrwmypqamv55jmjda3` (`beneficiaty_id`),
  CONSTRAINT `FK9eadbwck8n5ud16mdh7sqpiu3` FOREIGN KEY (`contributor_id`) REFERENCES `yiwu_distributer` (`id`),
  CONSTRAINT `FKam3vberdrwmypqamv55jmjda3` FOREIGN KEY (`beneficiaty_id`) REFERENCES `yiwu_distributer` (`id`),
  CONSTRAINT `fk_ExpRecord_shareTweet_id` FOREIGN KEY (`shareTweet_id`) REFERENCES `yiwu_share_tweet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_exp_record`
--

LOCK TABLES `yiwu_exp_record` WRITE;
/*!40000 ALTER TABLE `yiwu_exp_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_exp_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_message`
--

DROP TABLE IF EXISTS `yiwu_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `messageType_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Message_messageType_id` (`messageType_id`),
  KEY `fk_Message_receiver_id` (`receiver_id`),
  CONSTRAINT `fk_Message_messageType_id` FOREIGN KEY (`messageType_id`) REFERENCES `yiwu_type` (`id`),
  CONSTRAINT `fk_Message_receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `yiwu_distributer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_message`
--

LOCK TABLES `yiwu_message` WRITE;
/*!40000 ALTER TABLE `yiwu_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_money_record`
--

DROP TABLE IF EXISTS `yiwu_money_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_money_record` (
  `type` varchar(32) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `contributedDate` datetime DEFAULT NULL,
  `contributedValue` float NOT NULL,
  `income` float NOT NULL,
  `incomeFactor` float NOT NULL,
  `currentBrokerage` float NOT NULL,
  `currentFunds` float NOT NULL,
  `beneficiaty_id` int(11) DEFAULT NULL,
  `contributor_id` int(11) DEFAULT NULL,
  `recordType_id` int(11) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `capitalAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_withDrawRecord_capitalAccount_id` (`capitalAccount_id`),
  KEY `FKcjqp1rjytic3uy2ekdpw6gfuu` (`contributor_id`),
  KEY `FK2xxairg21bxp2qnm3mvc2w536` (`beneficiaty_id`),
  CONSTRAINT `FK2xxairg21bxp2qnm3mvc2w536` FOREIGN KEY (`beneficiaty_id`) REFERENCES `yiwu_distributer` (`id`),
  CONSTRAINT `FKcjqp1rjytic3uy2ekdpw6gfuu` FOREIGN KEY (`contributor_id`) REFERENCES `yiwu_distributer` (`id`),
  CONSTRAINT `fk_withDrawRecord_capitalAccount_id` FOREIGN KEY (`capitalAccount_id`) REFERENCES `yiwu_capital_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_money_record`
--

LOCK TABLES `yiwu_money_record` WRITE;
/*!40000 ALTER TABLE `yiwu_money_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_money_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_plan_revenue`
--

DROP TABLE IF EXISTS `yiwu_plan_revenue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_plan_revenue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storeId` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `productTypeId` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_PlanRevenue_storeId_year_month_productTypeId` (`storeId`,`year`,`month`,`productTypeId`),
  KEY `fk_PlanRevenue_productType_id` (`productTypeId`),
  CONSTRAINT `fk_PlanRevenue_productType_id` FOREIGN KEY (`productTypeId`) REFERENCES `yiwu_product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_plan_revenue`
--

LOCK TABLES `yiwu_plan_revenue` WRITE;
/*!40000 ALTER TABLE `yiwu_plan_revenue` DISABLE KEYS */;
INSERT INTO `yiwu_plan_revenue` VALUES (1,61,2016,3,2,60000),(2,61,2016,3,3,80000),(27,62,2016,3,2,30000),(28,62,2016,3,3,120000),(29,63,2016,3,2,50000),(30,63,2016,3,3,160000),(31,65,2016,3,2,40000),(32,65,2016,3,3,190000),(40,62,2017,3,2,30000),(41,65,2017,3,2,40000),(42,64,2017,3,2,50000),(43,66,2017,3,2,30000),(44,111,2017,3,2,0),(45,63,2017,3,2,50000),(46,68,2017,3,2,40000),(47,69,2017,3,2,50000),(48,83,2017,3,2,20000),(49,84,2017,3,2,10000),(50,85,2017,3,2,20000),(51,71,2017,3,2,40000),(52,72,2017,3,2,40000),(53,99,2017,3,2,30000),(54,70,2017,3,2,30000),(55,87,2017,3,2,30000),(56,108,2017,3,2,40000),(57,107,2017,3,2,30000),(58,61,2017,3,2,60000),(71,62,2017,3,3,120000),(72,65,2017,3,3,190000),(73,64,2017,3,3,90000),(74,66,2017,3,3,160000),(75,111,2017,3,3,70000),(76,63,2017,3,3,180000),(77,68,2017,3,3,160000),(78,69,2017,3,3,60000),(79,83,2017,3,3,120000),(80,84,2017,3,3,60000),(81,85,2017,3,3,100000),(82,71,2017,3,3,120000),(83,72,2017,3,3,130000),(84,99,2017,3,3,40000),(85,70,2017,3,3,100000),(86,87,2017,3,3,40000),(87,108,2017,3,3,100000),(88,107,2017,3,3,110000),(89,61,2017,3,3,80000),(102,62,2017,4,1,30000),(103,85,2017,4,1,10000),(104,71,2017,4,1,0),(105,68,2017,4,1,30000),(106,111,2017,4,1,40000),(107,63,2017,4,1,30000),(108,66,2017,4,1,20000),(109,99,2017,4,1,0),(110,61,2017,4,1,10000),(111,67,2017,4,1,30000),(112,107,2017,4,1,0),(113,108,2017,4,1,0),(114,72,2017,4,1,0),(115,83,2017,4,1,30000),(116,87,2017,4,1,0),(117,64,2017,4,1,20000),(118,70,2017,4,1,0),(119,69,2017,4,1,10000),(120,84,2017,4,1,10000),(121,65,2017,4,1,30000),(133,62,2017,4,2,20000),(134,85,2017,4,2,20000),(135,71,2017,4,2,40000),(136,68,2017,4,2,30000),(137,111,2017,4,2,0),(138,63,2017,4,2,50000),(139,66,2017,4,2,20000),(140,99,2017,4,2,30000),(141,61,2017,4,2,50000),(142,67,2017,4,2,50000),(143,107,2017,4,2,30000),(144,108,2017,4,2,60000),(145,72,2017,4,2,40000),(146,83,2017,4,2,30000),(147,87,2017,4,2,30000),(148,64,2017,4,2,50000),(149,70,2017,4,2,30000),(150,69,2017,4,2,60000),(151,84,2017,4,2,10000),(152,65,2017,4,2,50000),(164,62,2017,4,3,60000),(165,85,2017,4,3,70000),(166,71,2017,4,3,110000),(167,68,2017,4,3,90000),(168,111,2017,4,3,60000),(169,63,2017,4,3,130000),(170,66,2017,4,3,90000),(171,99,2017,4,3,40000),(172,61,2017,4,3,70000),(173,67,2017,4,3,100000),(174,107,2017,4,3,50000),(175,108,2017,4,3,20000),(176,72,2017,4,3,80000),(177,83,2017,4,3,70000),(178,87,2017,4,3,40000),(179,64,2017,4,3,70000),(180,70,2017,4,3,80000),(181,69,2017,4,3,20000),(182,84,2017,4,3,60000),(183,65,2017,4,3,110000),(195,67,2017,3,2,40000),(196,67,2017,3,3,190000),(197,83,2017,1,4,70000),(198,84,2017,1,4,70000),(199,65,2017,4,4,0),(200,65,2017,1,4,100000),(201,66,2017,1,4,90000),(202,111,2017,1,4,50000),(205,64,2017,1,4,80000),(206,62,2017,1,4,140000),(207,61,2017,1,4,100000),(208,63,2017,1,4,100000),(209,67,2017,1,4,140000),(210,68,2017,1,4,70000),(211,69,2017,1,4,60000),(212,85,2017,1,4,80000),(213,71,2017,1,4,100000),(214,99,2017,1,4,90000),(215,72,2017,1,4,110000),(216,70,2017,1,4,80000),(217,87,2017,1,4,90000),(218,108,2017,1,4,50000),(219,107,2017,1,4,30000),(220,83,2018,12,4,0),(221,83,2016,2,4,50000),(222,83,2016,3,4,130000),(223,83,2016,4,4,90000),(224,83,2016,5,4,120000),(225,83,2016,6,4,150000),(226,83,2016,7,4,150000),(227,83,2016,8,4,150000),(228,83,2016,9,4,160000),(229,83,2016,10,4,80000),(230,83,2016,11,4,100000),(231,83,2016,12,4,150000),(232,84,2016,2,4,40000),(233,84,2016,3,4,100000),(236,84,2016,5,4,80000),(237,84,2016,4,4,60000),(238,84,2016,6,4,110000),(239,84,2016,7,4,110000),(240,84,2016,8,4,90000),(241,84,2016,9,4,110000),(242,84,2016,10,4,60000),(243,84,2016,11,4,70000),(244,84,2016,12,4,100000),(245,65,2016,2,4,80000),(246,65,2016,3,4,220000),(247,65,2016,4,4,100000),(248,65,2016,5,4,220000),(249,65,2016,6,4,230000),(250,65,2016,7,4,150000),(251,65,2016,8,4,170000),(252,65,2016,9,4,230000),(253,65,2016,10,4,120000),(254,65,2016,11,4,160000),(255,65,2016,12,4,220000),(256,66,2016,2,4,60000),(257,66,2016,3,4,200000),(258,66,2016,4,4,60000),(259,66,2016,5,4,130000),(260,66,2016,6,4,180000),(261,66,2016,7,4,190000),(262,66,2016,8,4,180000),(263,66,2016,9,4,200000),(264,66,2016,10,4,100000),(265,66,2016,11,4,120000),(266,66,2016,12,4,190000),(267,111,2016,11,4,100000),(268,111,2016,12,4,80000),(269,64,2016,2,4,70000),(270,64,2016,3,4,180000),(271,64,2016,4,4,110000),(272,64,2016,5,4,120000),(273,64,2016,6,4,180000),(274,64,2016,7,4,180000),(275,64,2016,8,4,160000),(276,64,2016,9,4,170000),(277,64,2016,10,4,110000),(278,64,2016,11,4,120000),(279,64,2016,12,4,120000),(280,62,2016,2,4,50000),(281,62,2016,3,4,180000),(282,62,2016,4,4,60000),(283,62,2016,5,4,80000),(284,62,2016,6,4,150000),(285,62,2016,7,4,120000),(286,62,2016,8,4,100000),(287,62,2016,9,4,200000),(288,62,2016,10,4,60000),(289,62,2016,11,4,60000),(290,62,2016,12,4,200000),(291,61,2016,2,4,90000),(292,61,2016,3,4,200000),(293,61,2016,4,4,140000),(294,61,2016,5,4,140000),(295,61,2016,6,4,160000),(296,61,2016,7,4,170000),(297,61,2016,8,4,150000),(298,61,2016,9,4,160000),(299,61,2016,10,4,100000),(300,61,2016,11,4,140000),(301,61,2016,12,4,150000),(302,63,2016,2,4,60000),(303,63,2016,3,4,180000),(304,63,2016,4,4,110000),(305,63,2016,5,4,140000),(306,63,2016,6,4,200000),(307,63,2016,7,4,160000),(308,63,2016,8,4,150000),(309,63,2016,9,4,200000),(310,63,2016,10,4,120000),(311,63,2016,11,4,160000),(312,63,2016,12,4,170000),(313,67,2016,2,4,90000),(314,67,2016,3,4,220000),(315,67,2016,4,4,150000),(316,67,2016,5,4,160000),(317,67,2016,6,4,200000),(318,67,2016,7,4,160000),(319,67,2016,8,4,150000),(320,67,2016,9,4,230000),(321,67,2016,10,4,130000),(322,67,2016,11,4,160000),(323,67,2016,12,4,210000),(324,68,2016,2,4,60000),(325,68,2016,3,4,160000),(326,68,2016,4,4,110000),(327,68,2016,5,4,110000),(328,68,2016,6,4,160000),(329,68,2016,7,4,160000),(330,68,2016,8,4,150000),(331,68,2016,9,4,160000),(332,68,2016,10,4,90000),(333,68,2016,11,4,110000),(334,68,2016,12,4,160000),(335,69,2016,2,4,70000),(336,69,2016,3,4,130000),(337,69,2016,4,4,70000),(338,69,2016,5,4,80000),(339,69,2016,6,4,110000),(340,69,2016,7,4,130000),(341,69,2016,8,4,100000),(342,69,2016,9,4,100000),(343,69,2016,10,4,60000),(344,69,2016,11,4,70000),(345,69,2016,12,4,120000),(346,85,2016,2,4,80000),(347,85,2016,3,4,150000),(348,85,2016,4,4,100000),(349,85,2016,5,4,110000),(350,85,2016,6,4,160000),(351,85,2016,7,4,160000),(352,85,2016,8,4,140000),(353,85,2016,9,4,150000),(354,85,2016,10,4,110000),(355,85,2016,11,4,120000),(356,85,2016,12,4,140000),(357,71,2016,2,4,80000),(358,71,2016,3,4,200000),(359,71,2016,4,4,110000),(360,71,2016,5,4,110000),(361,71,2016,6,4,200000),(362,71,2016,7,4,170000),(363,71,2016,8,4,130000),(364,71,2016,9,4,170000),(365,71,2016,10,4,100000),(366,71,2016,11,4,130000),(367,71,2016,12,4,150000),(368,99,2016,7,4,100000),(369,99,2016,8,4,90000),(370,99,2016,9,4,130000),(371,99,2016,10,4,80000),(372,99,2016,11,4,70000),(373,99,2016,12,4,140000),(374,72,2016,2,4,60000),(375,72,2016,3,4,180000),(376,72,2016,4,4,110000),(377,72,2016,5,4,70000),(378,72,2016,6,4,180000),(379,72,2016,7,4,140000),(380,72,2016,8,4,70000),(381,72,2016,9,4,150000),(382,72,2016,10,4,130000),(383,72,2016,11,4,140000),(384,72,2016,12,4,160000),(385,70,2016,2,4,40000),(386,70,2016,3,4,110000),(387,70,2016,4,4,70000),(388,70,2016,5,4,90000),(389,70,2016,6,4,120000),(390,70,2016,7,4,140000),(391,70,2016,8,4,130000),(392,70,2016,9,4,150000),(393,70,2016,10,4,70000),(394,70,2016,11,4,80000),(395,70,2016,12,4,120000),(396,87,2016,5,4,80000),(397,87,2016,6,4,60000),(398,87,2016,7,4,80000),(399,87,2016,8,4,90000),(400,87,2016,9,4,140000),(401,87,2016,10,4,90000),(402,87,2016,11,4,70000),(403,87,2016,12,4,100000),(404,108,2016,10,4,150000),(405,108,2016,11,4,100000),(406,108,2016,12,4,100000),(407,107,2016,11,4,30000),(408,107,2016,12,4,70000),(409,61,2017,5,1,10000),(410,61,2017,5,2,60000),(411,61,2017,5,3,60000),(412,62,2017,5,2,30000),(413,62,2017,5,3,70000),(414,62,2017,5,1,15000),(415,63,2017,5,2,60000),(416,63,2017,5,3,120000),(417,63,2017,5,1,15000),(418,65,2017,5,2,50000),(419,65,2017,5,3,190000),(420,65,2017,5,1,15000),(421,64,2017,5,2,50000),(422,64,2017,5,3,100000),(423,64,2017,5,1,15000),(424,66,2017,5,2,30000),(425,66,2017,5,3,180000),(426,66,2017,5,1,15000),(427,111,2017,5,3,60000),(428,111,2017,5,1,15000),(429,67,2017,5,2,60000),(431,67,2017,5,3,130000),(432,67,2017,5,1,15000),(433,68,2017,5,2,40000),(434,68,2017,5,3,90000),(435,68,2017,5,1,15000),(436,69,2017,5,2,70000),(437,69,2017,5,3,20000),(439,69,2017,5,1,15000),(440,83,2017,5,2,30000),(441,83,2017,5,3,90000),(442,83,2017,5,1,15000),(443,84,2017,5,2,15000),(444,84,2017,5,3,55000),(445,84,2017,5,1,10000),(446,85,2017,5,2,20000),(447,85,2017,5,3,70000),(448,85,2017,5,1,10000),(449,71,2017,5,2,40000),(450,71,2017,5,3,90000),(451,72,2017,5,2,30000),(452,72,2017,5,3,70000),(453,99,2017,5,2,25000),(454,99,2017,5,3,45000),(455,70,2017,5,2,20000),(456,70,2017,5,3,90000),(457,87,2017,5,2,20000),(459,87,2017,5,3,50000),(460,108,2017,5,2,80000),(461,108,2017,5,3,40000),(462,107,2017,5,2,40000),(463,107,2017,5,3,80000);
/*!40000 ALTER TABLE `yiwu_plan_revenue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_product_type`
--

DROP TABLE IF EXISTS `yiwu_product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ProductType_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_product_type`
--

LOCK TABLES `yiwu_product_type` WRITE;
/*!40000 ALTER TABLE `yiwu_product_type` DISABLE KEYS */;
INSERT INTO `yiwu_product_type` VALUES (4,'其他'),(1,'小主持人'),(3,'少儿舞蹈'),(2,'成人舞蹈');
/*!40000 ALTER TABLE `yiwu_product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_product_type_relation`
--

DROP TABLE IF EXISTS `yiwu_product_type_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_product_type_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL,
  `product_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`product_type_id`),
  UNIQUE KEY `uk_ProductTypeRelation_productId` (`productId`),
  KEY `fk_ProductTypeRelation_ProductType_id` (`product_type_id`),
  CONSTRAINT `fk_ProductTypeRelation_ProductType_id` FOREIGN KEY (`product_type_id`) REFERENCES `yiwu_product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_product_type_relation`
--

LOCK TABLES `yiwu_product_type_relation` WRITE;
/*!40000 ALTER TABLE `yiwu_product_type_relation` DISABLE KEYS */;
INSERT INTO `yiwu_product_type_relation` VALUES (1,2,2),(2,3,2),(3,4,2),(4,5,2),(5,6,2),(6,7,2),(7,8,2),(8,9,2),(9,10,2),(10,11,2),(11,12,2),(12,13,3),(13,14,3),(14,15,3),(15,16,2),(16,17,2),(17,18,2),(18,19,2),(19,20,2),(20,21,2),(21,22,2),(22,23,2),(23,24,2),(24,25,2),(25,26,3),(26,27,3),(27,28,3),(28,29,3),(29,30,3),(30,31,3),(31,32,3),(32,33,3),(33,34,3),(34,35,3),(35,36,3),(36,37,3),(37,38,3),(38,39,3),(39,40,3),(40,41,3),(41,42,3),(42,43,3),(43,44,3),(44,45,3),(45,46,3),(46,47,3),(47,48,3),(48,49,3),(49,50,3),(50,51,3),(51,52,3),(52,53,3),(53,54,3),(54,55,3),(55,56,3),(56,57,3),(57,58,3),(58,59,3),(59,60,2),(60,61,2),(61,62,2),(62,63,2),(63,64,2),(64,65,2),(65,66,2),(66,67,2),(67,68,2),(68,69,2),(69,70,2),(70,71,2),(71,72,2),(72,73,2),(73,74,2),(74,75,3),(75,76,3),(76,77,3),(77,78,3),(78,79,3),(79,80,3),(80,81,3),(81,82,3),(82,83,2),(83,84,2),(84,85,2),(85,86,2),(86,87,2),(87,88,2),(88,89,3),(89,90,3),(90,91,3),(91,92,3),(92,93,3),(93,94,3),(94,95,3),(95,96,3),(96,97,3),(97,98,2),(98,99,2),(99,100,2),(100,101,2),(101,102,2),(102,103,2),(103,104,2),(104,105,2),(105,106,3),(106,107,3),(107,108,3),(108,109,3),(109,110,3),(110,111,3),(111,112,3),(112,113,3),(113,114,3),(114,115,3),(115,116,3),(116,117,2),(117,118,3),(118,119,2),(119,120,2),(120,121,3),(121,122,3),(122,123,3),(123,124,2),(124,125,2),(125,126,2),(126,127,3),(127,128,2),(128,129,3),(129,130,2),(130,131,2),(131,132,2),(132,133,2),(133,134,3),(134,135,3),(135,136,2),(136,137,2),(137,138,2),(138,139,2),(139,140,2),(140,141,2),(141,142,2),(142,143,3),(143,144,3),(144,145,3),(145,146,3),(146,147,3),(147,148,3),(148,149,3),(149,150,3),(150,151,3),(151,152,3),(152,153,3),(153,154,3),(154,155,3),(155,156,3),(156,157,3),(157,158,3),(158,159,3),(159,160,3),(160,161,3),(161,162,3),(162,163,3),(163,164,3),(164,165,3),(165,166,3),(166,167,3),(167,168,3),(168,169,3),(169,170,3),(170,171,3),(171,172,3),(172,173,3),(173,174,3),(174,175,3),(175,176,2),(176,177,3),(177,178,3),(178,179,3),(179,180,3),(180,181,3),(181,182,3),(182,183,3),(183,184,3),(184,185,3),(185,186,3),(186,187,3),(187,188,3),(188,189,3),(189,190,3),(190,191,3),(191,192,3),(192,193,3),(193,194,3),(194,195,3),(195,196,3),(196,197,3),(197,198,2),(198,199,2),(199,200,3),(200,201,3),(201,202,2),(202,203,2),(203,204,2),(204,205,3),(205,206,3),(206,207,3),(207,208,3),(208,209,3),(209,210,3),(210,211,3),(211,212,3),(212,213,3),(213,214,3),(214,215,3),(215,216,3),(216,217,3),(217,218,3),(218,219,3),(219,220,3),(220,221,3),(221,222,3),(222,223,2),(223,224,3),(224,225,3),(225,226,3),(226,227,3),(227,392,3),(228,803,3),(229,804,3),(230,866,3),(231,867,3),(232,876,3),(233,877,3),(234,45106,2),(235,45307,3),(236,45308,2),(237,45309,3),(238,45310,2),(239,45311,2),(240,45312,3),(241,45313,2),(242,45314,2),(243,45315,2),(244,45316,3),(245,45317,2),(246,45318,2),(247,45319,2),(248,45320,2),(249,45321,3),(250,45322,3),(251,45323,3),(252,45324,3),(253,45325,3),(254,45326,1),(255,45327,1),(256,45328,3),(257,45329,1),(258,45330,2),(259,45331,3),(260,45332,3),(261,45333,2),(262,45334,2),(263,45335,2),(264,45336,3),(265,45337,3),(266,45338,3);
/*!40000 ALTER TABLE `yiwu_product_type_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_share_tweet`
--

DROP TABLE IF EXISTS `yiwu_share_tweet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_share_tweet` (
  `share_style` varchar(32) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `shareDate` datetime DEFAULT NULL,
  `sharer_id` int(11) DEFAULT NULL,
  `tweet_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ShareTweet_sharer_id` (`sharer_id`),
  KEY `fk_ShareTweet_tweet_id` (`tweet_id`),
  CONSTRAINT `fk_ShareTweet_sharer_id` FOREIGN KEY (`sharer_id`) REFERENCES `yiwu_distributer` (`id`),
  CONSTRAINT `fk_ShareTweet_tweet_id` FOREIGN KEY (`tweet_id`) REFERENCES `yiwu_tweet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_share_tweet`
--

LOCK TABLES `yiwu_share_tweet` WRITE;
/*!40000 ALTER TABLE `yiwu_share_tweet` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_share_tweet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_store_info`
--

DROP TABLE IF EXISTS `yiwu_store_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_store_info` (
  `storeId` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `telePhone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`storeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_store_info`
--

LOCK TABLES `yiwu_store_info` WRITE;
/*!40000 ALTER TABLE `yiwu_store_info` DISABLE KEYS */;
INSERT INTO `yiwu_store_info` VALUES (59,'南苑街道东湖南路284号','0571-89189602'),(61,'杭州庆春路182-188号金融大厦西座808室','0571-87036991'),(62,'杭州香积寺东路60号万华国际酒店5楼（沃尔玛超市旁）','0571-85838839'),(63,'杭州市凤起东路新塘路口杭州天虹购物中心B座3F','0571-89801286'),(64,'杭州拱墅区湖墅南路103号（文晖路419号）百大花园3楼光影影城7号电影放映厅斜对面','0571-88377065'),(65,'杭州文三西路374号香樟商贸楼207-209室','0571-88802510'),(66,'杭州拱墅区温州路38号3楼（温州路和上塘路交叉口）','0571-87150653'),(67,'杭州滨江区滨盛路4199号江滨花园会所3F','0571-87713938'),(68,'滨江区滨和路和江晖路交叉口中赢国际元瑞酒店8楼（世纪联华旁）','0571-87113676'),(69,'萧山区金城路628号心意广场5幢215商铺（恒隆广场斜对面）','0571-82862126'),(70,'宁波鄞州区联盛商业广场C区三楼','0574-28905828'),(71,'绍兴越城区胜利东路与东池路交叉江南世家3-24','0575-85204031'),(72,'绍兴柯桥万达广场万达百货四楼','0575-85682007'),(83,'古墩路1888号永旺梦乐城T312室','0571—89380312'),(84,'申花路386号西城年华商铺-45','0571—87688691'),(85,'浙江省杭州市江干区下沙路跟海达南路交叉口下沙银泰7楼','0571-87796869'),(86,'杭州市余杭区星桥街道藕花洲大街西段603 605 607号（瑞金华丽嘉苑北门左侧）','0571-86132782?'),(87,'宁波市海曙区日新街亿彩购物中心3楼','0574-83868792'),(92,'上饶市横峰县岑阳镇音之舞舞蹈（华泰宾馆对面信用社楼上）','15070321883'),(95,'湖州市南浔镇适园西路世茂名流南大门向南100米','0572-3030766'),(96,'',''),(99,'绍兴市解放南路766号漫天虹少儿艺术园（金时代广场对面）','0575-88131662'),(107,'新区塔园路地铁站易生活购物公园1-209','0512-62763885'),(108,'园区星海广场地铁站北区B214','0512-62767885'),(111,'杭州市拱墅区台州路2号运河上街三楼星偶像','0571-86778575');
/*!40000 ALTER TABLE `yiwu_store_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_tweet`
--

DROP TABLE IF EXISTS `yiwu_tweet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_tweet` (
  `type` varchar(32) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `author` varchar(32) DEFAULT NULL,
  `coverIconUrl` varchar(255) DEFAULT NULL,
  `digest` varchar(50) DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `tweetContent_id` int(11) DEFAULT NULL,
  `tweetType_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Tweet_tweetContent_id` (`tweetContent_id`),
  KEY `fk_tweet_tweetType_id` (`tweetType_id`),
  CONSTRAINT `fk_Tweet_tweetContent_id` FOREIGN KEY (`tweetContent_id`) REFERENCES `yiwu_tweet_content` (`id`),
  CONSTRAINT `fk_tweet_tweetType_id` FOREIGN KEY (`tweetType_id`) REFERENCES `yiwu_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_tweet`
--

LOCK TABLES `yiwu_tweet` WRITE;
/*!40000 ALTER TABLE `yiwu_tweet` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_tweet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_tweet_content`
--

DROP TABLE IF EXISTS `yiwu_tweet_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_tweet_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `content` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_tweet_content`
--

LOCK TABLES `yiwu_tweet_content` WRITE;
/*!40000 ALTER TABLE `yiwu_tweet_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `yiwu_tweet_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiwu_type`
--

DROP TABLE IF EXISTS `yiwu_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiwu_type` (
  `type_class` varchar(32) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastModifiedUserId` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `comments` varchar(50) DEFAULT NULL,
  `factor` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17000025 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiwu_type`
--

LOCK TABLES `yiwu_type` WRITE;
/*!40000 ALTER TABLE `yiwu_type` DISABLE KEYS */;
INSERT INTO `yiwu_type` VALUES ('ExpRecordType',17000001,'2017-05-02 15:01:35',1,'2017-05-02 15:01:35',1,'注册','一级客户注册获取经验值',50),('ExpRecordType',17000002,'2017-05-02 15:03:51',1,'2017-05-02 15:03:51',1,'注册','二级客户注册获取经验值',10),('FundsRecordType',17000004,'2017-05-02 15:33:48',1,'2017-05-02 15:33:48',1,'注册','一级客户注册获取50基金',50),('BrokerageRecordType',17000005,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'提成','一级客首次购买音之舞产品提成',0.05),('BrokerageRecordType',17000006,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'提成','二级客户首次购买音之舞产品提成',0.02),('BrokerageRecordType',17000007,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'提成','一级客户非首次购买音之舞产品提成',0.02),('BrokerageRecordType',17000008,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'提成','二级客户非首次购买音之舞产品提成',0.01),('BrokerageRecordType',17000009,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'提现','将钱包里的前转到支付宝或微信账户',-1),('FundsRecordType',17000010,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'生息','存在钱包中的拥挤产生的利息',0.1),('ExpRecordType',17000011,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'分享推文','本人分享推文一次获得的经验值',50),('ExpRecordType',17000012,'2017-05-05 20:29:19',1,'2017-05-05 20:29:19',1,'分享推文','一级客户分享推文一次获取的经验值',10),('capitalAccountType',17000013,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'微信',NULL,NULL),('capitalAccountType',17000014,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'支付宝',NULL,NULL),('TweetType',17000015,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'产品',NULL,NULL),('TweetType',17000016,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'市场活动',NULL,NULL),('TweetType',17000017,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'优惠促销',NULL,NULL),('TweetType',17000018,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'比赛表演',NULL,NULL),('TweetType',17000019,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'新闻',NULL,NULL),('TweetType',17000020,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'少儿软文',NULL,NULL),('TweetType',17000021,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'成人软文',NULL,NULL),('TweetType',17000022,'2017-05-05 20:55:25',1,'2017-05-05 20:55:25',1,'其他',NULL,NULL),('BrokerageRecordType',17000023,'2017-05-06 15:42:13',1,'2017-05-06 15:42:13',1,'支付','使用佣金支付定金',-1),('FundsRecordType',17000024,'2017-05-06 15:42:13',1,'2017-05-06 15:42:13',1,'支付',' 使用基金支付定金',-1);
/*!40000 ALTER TABLE `yiwu_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'yiwu'
--

--
-- Dumping routines for database 'yiwu'
--

--
-- Final view structure for view `vappointment`
--

/*!50001 DROP VIEW IF EXISTS `vappointment`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vappointment` AS select `chenkuserdb1`.`skt63`.`SKF846` AS `id`,`chenkuserdb1`.`skt63`.`SKF847` AS `courseHourId`,`chenkuserdb1`.`skt63`.`SKF848` AS `customerId`,`chenkuserdb1`.`skt63`.`SKF854` AS `status`,`chenkuserdb1`.`skt63`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt63`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt63`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt63`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt63`.`MachineCode` AS `machinecode`,`chenkuserdb1`.`skt63`.`Sf_Last_Sync_TimeStamp` AS `Sf_Last_Sync_TimeStamp` from `chenkuserdb1`.`skt63` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vcheck_ins`
--

/*!50001 DROP VIEW IF EXISTS `vcheck_ins`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vcheck_ins` AS select `chenkuserdb1`.`skt23`.`SKF325` AS `id`,`chenkuserdb1`.`skt23`.`SKF326` AS `memberCard`,`chenkuserdb1`.`skt23`.`SKF327` AS `lessonId`,`chenkuserdb1`.`skt23`.`SKF328` AS `contract`,`chenkuserdb1`.`skt23`.`SKF334` AS `teacherId`,`chenkuserdb1`.`skt23`.`SKF704` AS `comsumeSd`,`chenkuserdb1`.`skt23`.`SKF705` AS `flag`,`chenkuserdb1`.`skt23`.`SKF965` AS `isRetroactive`,`chenkuserdb1`.`skt23`.`SKF983` AS `storemanCallroll`,`chenkuserdb1`.`skt23`.`SKF984` AS `uncallrollReason`,`chenkuserdb1`.`skt23`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt23`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt23`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt23`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt23`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt23`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt23`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt23` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vclassroom`
--

/*!50001 DROP VIEW IF EXISTS `vclassroom`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vclassroom` AS select `chenkuserdb1`.`skt5`.`SKF41` AS `id`,`chenkuserdb1`.`skt5`.`SKF42` AS `name`,`chenkuserdb1`.`skt5`.`SKF119` AS `store_id`,`chenkuserdb1`.`skt5`.`SKF43` AS `storeName`,`chenkuserdb1`.`skt5`.`SKF44` AS `maxStudentCount`,`chenkuserdb1`.`skt5`.`SKF45` AS `minStudentCount`,`chenkuserdb1`.`skt5`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt5`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt5`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt5`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt5`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt5`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt5`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt5` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vcourse`
--

/*!50001 DROP VIEW IF EXISTS `vcourse`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vcourse` AS select `chenkuserdb1`.`skt6`.`SKF50` AS `id`,`chenkuserdb1`.`skt6`.`SKF51` AS `name`,`chenkuserdb1`.`skt6`.`SKF52` AS `dance_id`,`chenkuserdb1`.`skt6`.`SKF53` AS `danceDesc`,`chenkuserdb1`.`skt6`.`SKF54` AS `store_id`,`chenkuserdb1`.`skt6`.`SKF55` AS `storeName`,`chenkuserdb1`.`skt6`.`SKF56` AS `teacher_id`,`chenkuserdb1`.`skt6`.`SKF57` AS `teacherName`,`chenkuserdb1`.`skt6`.`SKF58` AS `interval_id`,`chenkuserdb1`.`skt6`.`SKF59` AS `intervalDesc`,`chenkuserdb1`.`skt6`.`SKF60` AS `startTime`,`chenkuserdb1`.`skt6`.`SKF61` AS `endTime`,`chenkuserdb1`.`skt6`.`SKF62` AS `startDate`,`chenkuserdb1`.`skt6`.`SKF63` AS `endDate`,`chenkuserdb1`.`skt6`.`SKF64` AS `sumCourseHours`,`chenkuserdb1`.`skt6`.`SKF65` AS `classRoom_id`,`chenkuserdb1`.`skt6`.`SKF66` AS `classRoomName`,`chenkuserdb1`.`skt6`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt6`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt6`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt6`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt6`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`skt6`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt6`.`SKF88` AS `weeklyCourseHours`,`chenkuserdb1`.`skt6`.`SKF89` AS `delete_flag`,`chenkuserdb1`.`skt6`.`SKF93` AS `weeks`,`chenkuserdb1`.`skt6`.`SKF118` AS `courseType`,`chenkuserdb1`.`skt6`.`SKF165` AS `monInterval_id`,`chenkuserdb1`.`skt6`.`SKF166` AS `monRoom_id`,`chenkuserdb1`.`skt6`.`SKF167` AS `tueInterval_id`,`chenkuserdb1`.`skt6`.`SKF168` AS `tueRoom_id`,`chenkuserdb1`.`skt6`.`SKF169` AS `wedInterval_id`,`chenkuserdb1`.`skt6`.`SKF170` AS `wedRoom_id`,`chenkuserdb1`.`skt6`.`SKF171` AS `thuInterval_id`,`chenkuserdb1`.`skt6`.`SKF172` AS `thuRoom_id`,`chenkuserdb1`.`skt6`.`SKF173` AS `friInterval_id`,`chenkuserdb1`.`skt6`.`SKF174` AS `friRoom_id`,`chenkuserdb1`.`skt6`.`SKF175` AS `satInterval_id`,`chenkuserdb1`.`skt6`.`SKF176` AS `satRoom_id`,`chenkuserdb1`.`skt6`.`SKF177` AS `sunInterval_id`,`chenkuserdb1`.`skt6`.`SKF178` AS `sunRoom_id`,`chenkuserdb1`.`skt6`.`SKF197` AS `status`,`chenkuserdb1`.`skt6`.`SKF224` AS `StudentCount`,`chenkuserdb1`.`skt6`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`skt6`.`SKF367` AS `include_holeday_flag`,`chenkuserdb1`.`skt6`.`SKF388` AS `DanceGrade`,`chenkuserdb1`.`skt6`.`SKF868` AS `ShenheRen`,`chenkuserdb1`.`skt6`.`SKF919` AS `connotation`,`chenkuserdb1`.`skt6`.`SKF920` AS `help`,`chenkuserdb1`.`skt6`.`SKF921` AS `briefIntroduction`,`chenkuserdb1`.`skt6`.`SKF922` AS `picture`,`chenkuserdb1`.`skt6`.`SKF923` AS `videoURL`,`chenkuserdb1`.`skt6`.`SKF924` AS `audio`,`chenkuserdb1`.`skt6`.`SKF925` AS `audioURL`,`chenkuserdb1`.`skt6`.`SKF949` AS `danceIntroduction`,`chenkuserdb1`.`skt6`.`SKF1034` AS `subCourseType` from `chenkuserdb1`.`skt6` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vcustomer`
--

/*!50001 DROP VIEW IF EXISTS `vcustomer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vcustomer` AS select `chenkuserdb1`.`skt1`.`SKF223` AS `Id`,`chenkuserdb1`.`skt1`.`SKF225` AS `salesmanId`,`chenkuserdb1`.`skt1`.`SKF226` AS `audit_child`,`chenkuserdb1`.`skt1`.`SKF227` AS `isMember`,`chenkuserdb1`.`skt1`.`SKF1` AS `memberCard`,`chenkuserdb1`.`skt1`.`SKF2` AS `name`,`chenkuserdb1`.`skt1`.`SKF335` AS `sex`,`chenkuserdb1`.`skt1`.`SKF3` AS `mobilePhone`,`chenkuserdb1`.`skt1`.`SKF4` AS `residentId`,`chenkuserdb1`.`skt1`.`SKF5` AS `birthday`,`chenkuserdb1`.`skt1`.`SKF6` AS `age`,`chenkuserdb1`.`skt1`.`SKF7` AS `QQ`,`chenkuserdb1`.`skt1`.`SKF8` AS `wechat`,`chenkuserdb1`.`skt1`.`SKF9` AS `address`,`chenkuserdb1`.`skt1`.`SKF302` AS `company`,`chenkuserdb1`.`skt1`.`SKF303` AS `profession`,`chenkuserdb1`.`skt1`.`SKF304` AS `interesting`,`chenkuserdb1`.`skt1`.`SKF344` AS `photo`,`chenkuserdb1`.`skt1`.`SKF364` AS `customer_level`,`chenkuserdb1`.`skt1`.`SKF365` AS `source_of_customer`,`chenkuserdb1`.`skt1`.`SKF379` AS `date_of_next_track`,`chenkuserdb1`.`skt1`.`SKF726` AS `times_of_untrack`,`chenkuserdb1`.`skt1`.`SKF853` AS `times_of_remainder_course`,`chenkuserdb1`.`skt1`.`SKF864` AS `password`,`chenkuserdb1`.`skt1`.`SKF930` AS `credit`,`chenkuserdb1`.`skt1`.`SKF245` AS `earnest`,`chenkuserdb1`.`skt1`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt1`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt1`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt1`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt1`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`skt1`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`skt1`.`SF_Last_Change_Timestamp` AS `sf_last_change_timestamp` from `chenkuserdb1`.`skt1` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vdance`
--

/*!50001 DROP VIEW IF EXISTS `vdance`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vdance` AS select `chenkuserdb1`.`skt4`.`SKF33` AS `id`,`chenkuserdb1`.`skt4`.`SKF34` AS `name`,`chenkuserdb1`.`skt4`.`SKF35` AS `danceClass`,`chenkuserdb1`.`skt4`.`SKF36` AS `remuneration`,`chenkuserdb1`.`skt4`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt4`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt4`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt4`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt4`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt4`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt4`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt4` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vdance_grade`
--

/*!50001 DROP VIEW IF EXISTS `vdance_grade`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vdance_grade` AS select `chenkuserdb1`.`skt28`.`SKF381` AS `id`,`chenkuserdb1`.`skt28`.`SKF382` AS `name` from `chenkuserdb1`.`skt28` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vdepartment`
--

/*!50001 DROP VIEW IF EXISTS `vdepartment`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vdepartment` AS select `chenkuserdb1`.`tbldept`.`ID` AS `Id`,`chenkuserdb1`.`tbldept`.`Name` AS `Name`,`chenkuserdb1`.`tbldept`.`SuperiorID` AS `superiorId`,`chenkuserdb1`.`tbldept`.`Path` AS `path`,`chenkuserdb1`.`tbldept`.`Manager1` AS `manager1`,`chenkuserdb1`.`tbldept`.`Manager2` AS `manager2`,`chenkuserdb1`.`tbldept`.`Description` AS `description`,`chenkuserdb1`.`tbldept`.`Creator` AS `sf_create_user`,`chenkuserdb1`.`tbldept`.`LastChanger` AS `sf_last_change_user`,`chenkuserdb1`.`tbldept`.`CreateTime` AS `sf_create_time`,`chenkuserdb1`.`tbldept`.`LastChangeTime` AS `sf_last_change_time`,`chenkuserdb1`.`tbldept`.`Removed` AS `removed`,`chenkuserdb1`.`tbldept`.`flag` AS `flag`,`chenkuserdb1`.`tbldept`.`wparam` AS `wparam`,`chenkuserdb1`.`tbldept`.`lparam` AS `lparam`,`chenkuserdb1`.`tbldept`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`tbldept`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`tbldept`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`tbldept`.`SKF725` AS `operationDistrict`,`chenkuserdb1`.`tbldept`.`SKF862` AS `city`,`chenkuserdb1`.`tbldept`.`SKF863` AS `officialAccount`,`chenkuserdb1`.`tbldept`.`SKF865` AS `logo`,`chenkuserdb1`.`tbldept`.`SKF901` AS `province` from `chenkuserdb1`.`tbldept` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `velectric_contract`
--

/*!50001 DROP VIEW IF EXISTS `velectric_contract`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `velectric_contract` AS select `chenkuserdb1`.`skt79`.`SKF1041` AS `contractNo`,`chenkuserdb1`.`skt79`.`SKF1042` AS `customerName`,`chenkuserdb1`.`skt79`.`SKF1043` AS `gender`,`chenkuserdb1`.`skt79`.`SKF1044` AS `birthday`,`chenkuserdb1`.`skt79`.`SKF1045` AS `IdentityCardNo`,`chenkuserdb1`.`skt79`.`SKF1046` AS `mobiePhoneNo`,`chenkuserdb1`.`skt79`.`SKF1047` AS `qqNo`,`chenkuserdb1`.`skt79`.`SKF1048` AS `wechatNo`,`chenkuserdb1`.`skt79`.`SKF1049` AS `customerId`,`chenkuserdb1`.`skt79`.`SKF1050` AS `contactAddress`,`chenkuserdb1`.`skt79`.`SKF1051` AS `homeAddress`,`chenkuserdb1`.`skt79`.`SKF1053` AS `recommendMemberCardNo`,`chenkuserdb1`.`skt79`.`SKF1054` AS `memberCardNo`,`chenkuserdb1`.`skt79`.`SKF1055` AS `effectiveStart`,`chenkuserdb1`.`skt79`.`SKF1056` AS `effectiveEnd`,`chenkuserdb1`.`skt79`.`SKF1057` AS `timesOfLesson`,`chenkuserdb1`.`skt79`.`SKF1058` AS `price`,`chenkuserdb1`.`skt79`.`SKF1059` AS `amount`,`chenkuserdb1`.`skt79`.`SKF1060` AS `promotionPrice`,`chenkuserdb1`.`skt79`.`SKF1061` AS `rangeOfApplication`,`chenkuserdb1`.`skt79`.`SKF1062` AS `supplementalInstruction`,`chenkuserdb1`.`skt79`.`SKF1063` AS `uppcaseAmount`,`chenkuserdb1`.`skt79`.`SKF1064` AS `lowercaseAmount`,`chenkuserdb1`.`skt79`.`SKF1065` AS `payedDate`,`chenkuserdb1`.`skt79`.`SKF1066` AS `payedMethod`,`chenkuserdb1`.`skt79`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt79`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt79`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt79`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt79`.`SKF1071` AS `depositAmount`,`chenkuserdb1`.`skt79`.`SKF1072` AS `depositDate`,`chenkuserdb1`.`skt79`.`SKF1073` AS `finalPayment`,`chenkuserdb1`.`skt79`.`SKF1074` AS `finalDate`,`chenkuserdb1`.`skt79`.`SKF1075` AS `contractType`,`chenkuserdb1`.`skt79`.`SKF1076` AS `isConfirmed` from `chenkuserdb1`.`skt79` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `velectric_contract_type`
--

/*!50001 DROP VIEW IF EXISTS `velectric_contract_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `velectric_contract_type` AS select `chenkuserdb1`.`skt81`.`SKF1085` AS `id`,`chenkuserdb1`.`skt81`.`SKF1086` AS `description`,`chenkuserdb1`.`skt81`.`SKF1087` AS `content`,`chenkuserdb1`.`skt81`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt81`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt81`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt81`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt81`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt81`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp` from `chenkuserdb1`.`skt81` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vemployee`
--

/*!50001 DROP VIEW IF EXISTS `vemployee`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vemployee` AS select `chenkuserdb1`.`tblemployee`.`ID` AS `ID`,`chenkuserdb1`.`tblemployee`.`User` AS `User`,`chenkuserdb1`.`tblemployee`.`SeegleUserID` AS `SeegleUserID`,`chenkuserdb1`.`tblemployee`.`name` AS `name`,`chenkuserdb1`.`tblemployee`.`PassWord` AS `PassWord`,`chenkuserdb1`.`tblemployee`.`Sex` AS `Sex`,`chenkuserdb1`.`tblemployee`.`Birthday` AS `Birthday`,`chenkuserdb1`.`tblemployee`.`Tel` AS `Tel`,`chenkuserdb1`.`tblemployee`.`CellPhone` AS `CellPhone`,`chenkuserdb1`.`tblemployee`.`Fax` AS `Fax`,`chenkuserdb1`.`tblemployee`.`Email` AS `Email`,`chenkuserdb1`.`tblemployee`.`Disabled` AS `Disabled`,`chenkuserdb1`.`tblemployee`.`Creator` AS `sf_create_user`,`chenkuserdb1`.`tblemployee`.`LastChanger` AS `sf_last_change_user`,`chenkuserdb1`.`tblemployee`.`CreateTime` AS `CreateTime`,`chenkuserdb1`.`tblemployee`.`LastChangeTime` AS `sf_last_change_time`,`chenkuserdb1`.`tblemployee`.`Removed` AS `Removed`,`chenkuserdb1`.`tblemployee`.`wparam` AS `wparam`,`chenkuserdb1`.`tblemployee`.`lparam` AS `lparam`,`chenkuserdb1`.`tblemployee`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`tblemployee`.`SeegleUserName` AS `SeegleUserName`,`chenkuserdb1`.`tblemployee`.`SeegleSerialNum` AS `SeegleSerialNum`,`chenkuserdb1`.`tblemployee`.`UserType` AS `UserType`,`chenkuserdb1`.`tblemployee`.`AccessCode` AS `AccessCode`,`chenkuserdb1`.`tblemployee`.`BindMac` AS `BindMac`,`chenkuserdb1`.`tblemployee`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`tblemployee`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`tblemployee`.`SKF798` AS `fingerPrintNo`,`chenkuserdb1`.`tblemployee`.`SKF844` AS `departmentId`,`chenkuserdb1`.`tblemployee`.`SKF845` AS `defaultDuty`,`chenkuserdb1`.`tblemployee`.`OutUser` AS `OutUser`,`chenkuserdb1`.`tblemployee`.`ClusterServerIP` AS `ClusterServerIP`,`chenkuserdb1`.`tblemployee`.`ClusterServerPort` AS `ClusterServerPort`,`chenkuserdb1`.`tblemployee`.`ClusterToken` AS `ClusterToken`,`chenkuserdb1`.`tblemployee`.`Last_Online_TimeStamp` AS `Last_Online_TimeStamp` from `chenkuserdb1`.`tblemployee` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vemployee_post`
--

/*!50001 DROP VIEW IF EXISTS `vemployee_post`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vemployee_post` AS select `chenkuserdb1`.`tblemployee_post`.`ID` AS `id`,`chenkuserdb1`.`tblemployee_post`.`EmployeeID` AS `employeeId`,`chenkuserdb1`.`tblemployee_post`.`PostID` AS `postID`,`chenkuserdb1`.`tblemployee_post`.`LastChanger` AS `lastChangerId`,`chenkuserdb1`.`tblemployee_post`.`LastChangeTime` AS `lastChangeTime`,`chenkuserdb1`.`tblemployee_post`.`Removed` AS `removed`,`chenkuserdb1`.`tblemployee_post`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`tblemployee_post`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimestamp`,`chenkuserdb1`.`tblemployee_post`.`SF_Last_Change_Timestamp` AS `lastChangeTimestamp` from `chenkuserdb1`.`tblemployee_post` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vinterval`
--

/*!50001 DROP VIEW IF EXISTS `vinterval`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vinterval` AS select `chenkuserdb1`.`skt3`.`SKF24` AS `id`,`chenkuserdb1`.`skt3`.`SKF25` AS `name`,`chenkuserdb1`.`skt3`.`SKF26` AS `start`,`chenkuserdb1`.`skt3`.`SKF27` AS `end`,`chenkuserdb1`.`skt3`.`SKF28` AS `hours`,`chenkuserdb1`.`skt3`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt3`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt3`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt3`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt3`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt3`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_time_stamp`,`chenkuserdb1`.`skt3`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt3` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vlesson`
--

/*!50001 DROP VIEW IF EXISTS `vlesson`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vlesson` AS select `chenkuserdb1`.`skt7`.`SKF139` AS `id`,`chenkuserdb1`.`skt7`.`SKF120` AS `courseId`,`chenkuserdb1`.`skt7`.`SKF72` AS `lessonDate`,`chenkuserdb1`.`skt7`.`SKF73` AS `startTime`,`chenkuserdb1`.`skt7`.`SKF74` AS `endTime`,`chenkuserdb1`.`skt7`.`SKF75` AS `courseDesc`,`chenkuserdb1`.`skt7`.`SKF76` AS `storeId`,`chenkuserdb1`.`skt7`.`SKF77` AS `storeName`,`chenkuserdb1`.`skt7`.`SKF78` AS `lessonTime`,`chenkuserdb1`.`skt7`.`SKF79` AS `yindaoTeacherId`,`chenkuserdb1`.`skt7`.`SKF80` AS `yindaoTeacherName`,`chenkuserdb1`.`skt7`.`SKF81` AS `classRoomId`,`chenkuserdb1`.`skt7`.`SKF82` AS `classRoomName`,`chenkuserdb1`.`skt7`.`SKF221` AS `shidaoTeacherId`,`chenkuserdb1`.`skt7`.`SKF222` AS `shidaoTeacherName`,`chenkuserdb1`.`skt7`.`SKF200` AS `lessonStatus`,`chenkuserdb1`.`skt7`.`SKF336` AS `courseType`,`chenkuserdb1`.`skt7`.`SKF1035` AS `subCourseType`,`chenkuserdb1`.`skt7`.`SKF83` AS `flag_delete`,`chenkuserdb1`.`skt7`.`SKF198` AS `start_date_time`,`chenkuserdb1`.`skt7`.`SKF199` AS `lessonTime_minutes`,`chenkuserdb1`.`skt7`.`SKF368` AS `appointed_huijiheyue`,`chenkuserdb1`.`skt7`.`SKF821` AS `yindaoRenshu`,`chenkuserdb1`.`skt7`.`SKF822` AS `tiyanRenshu`,`chenkuserdb1`.`skt7`.`SKF823` AS `dianmingRenshu`,`chenkuserdb1`.`skt7`.`SKF824` AS `shidaoRenshu`,`chenkuserdb1`.`skt7`.`SKF826` AS `qiandaoRenshu`,`chenkuserdb1`.`skt7`.`SKF827` AS `yuyueRenshu`,`chenkuserdb1`.`skt7`.`SKF855` AS `neihan`,`chenkuserdb1`.`skt7`.`SKF856` AS `help`,`chenkuserdb1`.`skt7`.`SKF857` AS `jianjie`,`chenkuserdb1`.`skt7`.`SKF858` AS `picture`,`chenkuserdb1`.`skt7`.`SKF859` AS `video`,`chenkuserdb1`.`skt7`.`SKF860` AS `audio`,`chenkuserdb1`.`skt7`.`SKF861` AS `audio_link`,`chenkuserdb1`.`skt7`.`SKF950` AS `dance_introduction`,`chenkuserdb1`.`skt7`.`SKF1001` AS `QRcode`,`chenkuserdb1`.`skt7`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt7`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt7`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt7`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt7`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt7`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt7`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt7` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vorder`
--

/*!50001 DROP VIEW IF EXISTS `vorder`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vorder` AS select `chenkuserdb1`.`skt18`.`SKF254` AS `id`,`chenkuserdb1`.`skt18`.`SKF255` AS `memberCardNo`,`chenkuserdb1`.`skt18`.`SKF256` AS `product_id`,`chenkuserdb1`.`skt18`.`SKF257` AS `markedPrice`,`chenkuserdb1`.`skt18`.`SKF258` AS `preferential`,`chenkuserdb1`.`skt18`.`SKF369` AS `count`,`chenkuserdb1`.`skt18`.`SKF370` AS `discount`,`chenkuserdb1`.`skt18`.`SKF259` AS `payedAmount`,`chenkuserdb1`.`skt18`.`SKF380` AS `customer_id`,`chenkuserdb1`.`skt18`.`SKF706` AS `payed_date`,`chenkuserdb1`.`skt18`.`SKF276` AS `course_id`,`chenkuserdb1`.`skt18`.`SKF277` AS `checked_status`,`chenkuserdb1`.`skt18`.`SKF278` AS `store_id`,`chenkuserdb1`.`skt18`.`SKF293` AS `vip_attr`,`chenkuserdb1`.`skt18`.`SKF294` AS `contractNo`,`chenkuserdb1`.`skt18`.`SKF295` AS `validity`,`chenkuserdb1`.`skt18`.`SKF296` AS `validity_times`,`chenkuserdb1`.`skt18`.`SKF297` AS `startdate`,`chenkuserdb1`.`skt18`.`SKF298` AS `endDate`,`chenkuserdb1`.`skt18`.`SKF299` AS `remain_times`,`chenkuserdb1`.`skt18`.`SKF305` AS `product_type`,`chenkuserdb1`.`skt18`.`SKF1036` AS `product_subType`,`chenkuserdb1`.`skt18`.`SKF306` AS `recommender`,`chenkuserdb1`.`skt18`.`SKF307` AS `valid_storeids`,`chenkuserdb1`.`skt18`.`SKF308` AS `pic`,`chenkuserdb1`.`skt18`.`SKF321` AS `ask_for_leave_flag`,`chenkuserdb1`.`skt18`.`SKF354` AS `comments`,`chenkuserdb1`.`skt18`.`SKF366` AS `relative_SD`,`chenkuserdb1`.`skt18`.`SKF300` AS `check_privilege`,`chenkuserdb1`.`skt18`.`SKF301` AS `current_status`,`chenkuserdb1`.`skt18`.`SKF867` AS `remain_hours`,`chenkuserdb1`.`skt18`.`SKF963` AS `audit_flag`,`chenkuserdb1`.`skt18`.`SKF1037` AS `e_contract_text`,`chenkuserdb1`.`skt18`.`SKF1038` AS `e_contract_address`,`chenkuserdb1`.`skt18`.`SKF1039` AS `e_contract_picture_flag`,`chenkuserdb1`.`skt18`.`SKF1040` AS `e_contract_status`,`chenkuserdb1`.`skt18`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt18`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt18`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt18`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt18`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`skt18`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_TimeStamp`,`chenkuserdb1`.`skt18`.`SF_Last_Change_Timestamp` AS `sf_last_change_timestamp` from `chenkuserdb1`.`skt18` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vorder_payed_method`
--

/*!50001 DROP VIEW IF EXISTS `vorder_payed_method`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vorder_payed_method` AS select `chenkuserdb1`.`skt16`.`SKF237` AS `id`,`chenkuserdb1`.`skt16`.`SKF238` AS `orderId`,`chenkuserdb1`.`skt16`.`SKF239` AS `payedMethodId`,`chenkuserdb1`.`skt16`.`SKF240` AS `amount`,`chenkuserdb1`.`skt16`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt16`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt16`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt16`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt16`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt16`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt16`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt16` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vpayed_method`
--

/*!50001 DROP VIEW IF EXISTS `vpayed_method`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vpayed_method` AS select `chenkuserdb1`.`skt17`.`SKF246` AS `id`,`chenkuserdb1`.`skt17`.`SKF247` AS `name`,`chenkuserdb1`.`skt17`.`SF_CREATE_USER` AS `createUserId`,`chenkuserdb1`.`skt17`.`SF_LAST_CHANGE_USER` AS `lastChangeUserId`,`chenkuserdb1`.`skt17`.`SF_CREATE_TIME` AS `createTime`,`chenkuserdb1`.`skt17`.`SF_LAST_CHANGE_TIME` AS `lastChangeTime`,`chenkuserdb1`.`skt17`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt17`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimestamp`,`chenkuserdb1`.`skt17`.`SF_Last_Change_Timestamp` AS `lastChangeTimestamp` from `chenkuserdb1`.`skt17` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vpost`
--

/*!50001 DROP VIEW IF EXISTS `vpost`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vpost` AS select `chenkuserdb1`.`tblpost`.`ID` AS `ID`,`chenkuserdb1`.`tblpost`.`Type` AS `Type`,`chenkuserdb1`.`tblpost`.`Name` AS `Name`,`chenkuserdb1`.`tblpost`.`Description` AS `Description`,`chenkuserdb1`.`tblpost`.`Creator` AS `Creator`,`chenkuserdb1`.`tblpost`.`LastChanger` AS `LastChanger`,`chenkuserdb1`.`tblpost`.`CreateTime` AS `CreateTime`,`chenkuserdb1`.`tblpost`.`LastChangeTime` AS `LastChangeTime`,`chenkuserdb1`.`tblpost`.`Removed` AS `Removed`,`chenkuserdb1`.`tblpost`.`flag` AS `flag`,`chenkuserdb1`.`tblpost`.`wparam` AS `wparam`,`chenkuserdb1`.`tblpost`.`lparam` AS `lparam`,`chenkuserdb1`.`tblpost`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`tblpost`.`Sf_Last_Sync_TimeStamp` AS `Sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`tblpost`.`SF_Last_Change_Timestamp` AS `SF_Last_Change_Timestamp` from `chenkuserdb1`.`tblpost` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vproduct`
--

/*!50001 DROP VIEW IF EXISTS `vproduct`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vproduct` AS select `chenkuserdb1`.`skt8`.`SKF94` AS `id`,`chenkuserdb1`.`skt8`.`SKF95` AS `name`,`chenkuserdb1`.`skt8`.`SKF100` AS `card_type`,`chenkuserdb1`.`skt8`.`SKF101` AS `customer_type`,`chenkuserdb1`.`skt8`.`SKF102` AS `marked_price`,`chenkuserdb1`.`skt8`.`SKF103` AS `useful_life`,`chenkuserdb1`.`skt8`.`SKF104` AS `useful_times`,`chenkuserdb1`.`skt8`.`SKF252` AS `obsolete_flag`,`chenkuserdb1`.`skt8`.`SKF253` AS `DY_RCP`,`chenkuserdb1`.`skt8`.`SKF322` AS `max_leave_times`,`chenkuserdb1`.`skt8`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt8`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt8`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt8`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt8`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`skt8`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt8`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp` from `chenkuserdb1`.`skt8` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vstore_callroll`
--

/*!50001 DROP VIEW IF EXISTS `vstore_callroll`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vstore_callroll` AS select `chenkuserdb1`.`skt75`.`SKF992` AS `id`,`chenkuserdb1`.`skt75`.`SKF993` AS `lessonId`,`chenkuserdb1`.`skt75`.`SKF994` AS `memberCard`,`chenkuserdb1`.`skt75`.`SKF995` AS `flagCallroll`,`chenkuserdb1`.`skt75`.`SKF996` AS `unCallrollReason`,`chenkuserdb1`.`skt75`.`SF_CREATE_USER` AS `createUserId`,`chenkuserdb1`.`skt75`.`SF_LAST_CHANGE_USER` AS `lastChangeUserId`,`chenkuserdb1`.`skt75`.`SF_CREATE_TIME` AS `createTime`,`chenkuserdb1`.`skt75`.`SF_LAST_CHANGE_TIME` AS `lastChangeTime`,`chenkuserdb1`.`skt75`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt75`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimeStamp` from `chenkuserdb1`.`skt75` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vteacher_callroll`
--

/*!50001 DROP VIEW IF EXISTS `vteacher_callroll`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ping3`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vteacher_callroll` AS select `chenkuserdb1`.`skt56`.`SKF760` AS `id`,`chenkuserdb1`.`skt56`.`SKF757` AS `memberCard`,`chenkuserdb1`.`skt56`.`SKF758` AS `courseType`,`chenkuserdb1`.`skt56`.`SKF759` AS `lessonId`,`chenkuserdb1`.`skt56`.`SKF766` AS `callroll`,`chenkuserdb1`.`skt56`.`SKF797` AS `studentName`,`chenkuserdb1`.`skt56`.`SKF828` AS `slotCardId`,`chenkuserdb1`.`skt56`.`SKF938` AS `memberId`,`chenkuserdb1`.`skt56`.`SKF964` AS `unCallRollReason`,`chenkuserdb1`.`skt56`.`SKF966` AS `isFilledUp`,`chenkuserdb1`.`skt56`.`SF_CREATE_USER` AS `createUserid`,`chenkuserdb1`.`skt56`.`SF_LAST_CHANGE_USER` AS `lastChangeUserId`,`chenkuserdb1`.`skt56`.`SF_CREATE_TIME` AS `createTime`,`chenkuserdb1`.`skt56`.`SF_LAST_CHANGE_TIME` AS `lastChangeTime`,`chenkuserdb1`.`skt56`.`SF_LAST_CHANGE_TIMESTAMP` AS `lastChangeTimeStamp`,`chenkuserdb1`.`skt56`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt56`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimeStamp` from `chenkuserdb1`.`skt56` */;
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

-- Dump completed on 2017-06-05 17:18:02
