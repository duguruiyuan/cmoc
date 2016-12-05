-- MySQL dump 10.13  Distrib 5.5.53, for linux2.6 (x86_64)
--
-- Host: localhost    Database: cmoc
-- ------------------------------------------------------
-- Server version	5.5.53-log

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
-- Table structure for table `t_activity_family`
--

DROP TABLE IF EXISTS `t_activity_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_family` (
  `id` int(22) unsigned NOT NULL AUTO_INCREMENT,
  `father_name` varchar(128) DEFAULT NULL COMMENT '爸爸姓名',
  `father_mobile` varchar(11) DEFAULT NULL COMMENT '爸爸手机号码',
  `father_age` int(11) DEFAULT NULL COMMENT '爸爸年龄',
  `mother_name` varchar(128) DEFAULT NULL COMMENT '妈妈姓名',
  `mother_mobile` varchar(11) DEFAULT NULL COMMENT '妈妈手机号码',
  `mother_age` int(11) DEFAULT NULL COMMENT '妈妈年龄',
  `other_name` varchar(128) DEFAULT NULL COMMENT '其他联系人姓名',
  `other_mobile` varchar(11) DEFAULT NULL COMMENT '其他联系人手机号码',
  `other_age` int(11) DEFAULT NULL COMMENT '其他联系人年龄',
  `child_name` varchar(128) DEFAULT NULL COMMENT '小孩姓名',
  `child_mobile` varchar(11) DEFAULT NULL COMMENT '小孩手机号码',
  `child_age` int(11) DEFAULT NULL COMMENT '小孩年龄',
  `child_img` varchar(128) DEFAULT NULL COMMENT '小孩头像',
  `child_title` varchar(128) DEFAULT NULL COMMENT '小孩头衔',
  `child_comment` varchar(1000) DEFAULT NULL COMMENT '小孩评语',
  `activity_id` int(22) NOT NULL COMMENT '活动编号',
  `marine_id` int(22) DEFAULT NULL COMMENT '战队编号',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y无效',
  PRIMARY KEY (`id`),
  KEY `idx_father_name` (`father_name`),
  KEY `idx_father_mobile` (`mother_mobile`,`father_mobile`),
  KEY `idx_mother_name` (`mother_name`),
  KEY `idx_mother_mobile` (`mother_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='活动报名家庭信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_family`
--

LOCK TABLES `t_activity_family` WRITE;
/*!40000 ALTER TABLE `t_activity_family` DISABLE KEYS */;
INSERT INTO `t_activity_family` VALUES (41,'张爸一','13681984141',NULL,'张妈一','13681984031',NULL,NULL,NULL,NULL,'张一',NULL,NULL,NULL,'小队长',NULL,3,15,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(42,'张爸二','13681984142',NULL,'张妈二','13681984032',NULL,NULL,NULL,NULL,'张二',NULL,NULL,NULL,'后勤部长',NULL,3,15,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(43,'张爸三','13681984143',NULL,'张妈三','13681984033',NULL,NULL,NULL,NULL,'张三',NULL,NULL,NULL,'体育委员',NULL,3,15,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(44,'张爸四','13681984144',NULL,'张妈四','13681984034',NULL,NULL,NULL,NULL,'张四',NULL,NULL,NULL,'活动标兵',NULL,3,15,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(45,'张爸五','13681984145',NULL,'张妈五','13681984035',NULL,NULL,NULL,NULL,'张五',NULL,NULL,NULL,'活动部长',NULL,3,15,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(46,'刘爸一','13681984041',NULL,'张妈一','13681984631',NULL,NULL,NULL,NULL,'刘一',NULL,NULL,NULL,'小队长',NULL,3,16,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(47,'刘爸二','13681984042',NULL,'张妈二','13681984632',NULL,NULL,NULL,NULL,'刘二',NULL,NULL,NULL,'后勤部长',NULL,3,16,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(48,'刘爸三','13681984043',NULL,'张妈三','13681984633',NULL,NULL,NULL,NULL,'刘三',NULL,NULL,NULL,'体育委员',NULL,3,16,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(49,'刘爸四','13681984044',NULL,'张妈四','13681984634',NULL,NULL,NULL,NULL,'刘四',NULL,NULL,NULL,'活动标兵',NULL,3,16,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(50,'刘爸五','13681984045',NULL,'张妈五','13681984635',NULL,NULL,NULL,NULL,'刘五',NULL,NULL,NULL,'活动部长',NULL,3,16,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(51,'马爸一','13681984331',NULL,'马妈一','13681984731',NULL,NULL,NULL,NULL,'马一',NULL,NULL,NULL,'小队长',NULL,3,17,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(52,'马爸二','13681984332',NULL,'马妈二','13681984732',NULL,NULL,NULL,NULL,'马二',NULL,NULL,NULL,'后勤部长',NULL,3,17,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(53,'马爸三','13681984333',NULL,'马妈三','13681984733',NULL,NULL,NULL,NULL,'马三',NULL,NULL,NULL,'体育委员',NULL,3,17,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(54,'马爸四','13681984334',NULL,'马妈四','13681984734',NULL,NULL,NULL,NULL,'马四',NULL,NULL,NULL,'活动标兵',NULL,3,17,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(55,'马爸五','13681984335',NULL,'马妈五','13681984735',NULL,NULL,NULL,NULL,'马五',NULL,NULL,NULL,'活动部长',NULL,3,17,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(56,'黄爸一','13681985331',NULL,'黄妈一','13681984831',NULL,NULL,NULL,NULL,'黄一',NULL,NULL,NULL,'小队长',NULL,3,18,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(57,'黄爸二','13681985332',NULL,'黄妈二','13681984832',NULL,NULL,NULL,NULL,'黄二',NULL,NULL,NULL,'后勤部长',NULL,3,18,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(58,'黄爸三','13681985333',NULL,'黄妈三','13681984833',NULL,NULL,NULL,NULL,'黄三',NULL,NULL,NULL,'体育委员',NULL,3,18,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(59,'黄爸四','13681985334',NULL,'黄妈四','13681984834',NULL,NULL,NULL,NULL,'黄四',NULL,NULL,NULL,'活动标兵',NULL,3,18,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(60,'黄爸五','13681985335',NULL,'黄妈五','13681984835',NULL,NULL,NULL,NULL,'黄五',NULL,NULL,NULL,'活动部长',NULL,3,18,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(61,'青爸一','13681987331',NULL,'青妈一','13681984931',NULL,NULL,NULL,NULL,'青一',NULL,NULL,NULL,'小队长',NULL,3,19,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(62,'青爸二','13681987332',NULL,'青妈二','13681984932',NULL,NULL,NULL,NULL,'青二',NULL,NULL,NULL,'后勤部长',NULL,3,19,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(63,'青爸三','13681987333',NULL,'青妈三','13681984933',NULL,NULL,NULL,NULL,'青三',NULL,NULL,NULL,'体育委员',NULL,3,19,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(64,'青爸四','13681987334',NULL,'青妈四','13681984934',NULL,NULL,NULL,NULL,'青四',NULL,NULL,NULL,'活动标兵',NULL,3,19,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(65,'青爸五','13681987335',NULL,'青妈五','13681984935',NULL,NULL,NULL,NULL,'青五',NULL,NULL,NULL,'活动部长',NULL,3,19,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N');
/*!40000 ALTER TABLE `t_activity_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity_hm_sign`
--

DROP TABLE IF EXISTS `t_activity_hm_sign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_hm_sign` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `activity_id` int(22) NOT NULL COMMENT '活动id',
  `line_id` int(22) DEFAULT NULL COMMENT '线路id',
  `marine_id` int(22) DEFAULT NULL COMMENT '战队id',
  `hm_id` int(22) NOT NULL COMMENT '透明人id',
  `sign_date` varchar(8) DEFAULT NULL COMMENT '报名日期',
  `is_effect` int(2) DEFAULT '0' COMMENT '是否生效 0否 1是',
  `effect_date` varchar(8) DEFAULT NULL COMMENT '生效日期',
  `score` decimal(8,2) DEFAULT NULL COMMENT '评分',
  `judge` varchar(512) DEFAULT NULL COMMENT '评价',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='透明人活动报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_hm_sign`
--

LOCK TABLES `t_activity_hm_sign` WRITE;
/*!40000 ALTER TABLE `t_activity_hm_sign` DISABLE KEYS */;
INSERT INTO `t_activity_hm_sign` VALUES (17,3,NULL,15,31,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,'admin','2016-12-05 16:48:54','N'),(18,3,NULL,15,22,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(19,3,NULL,16,23,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(20,3,NULL,16,24,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(21,3,NULL,17,25,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(22,3,NULL,17,26,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(23,3,NULL,18,27,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(24,3,NULL,18,28,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(25,3,NULL,19,29,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(26,3,NULL,19,30,'20161205',1,'20161205',NULL,NULL,1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N');
/*!40000 ALTER TABLE `t_activity_hm_sign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity_info`
--

DROP TABLE IF EXISTS `t_activity_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_info` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(128) NOT NULL COMMENT '活动名称',
  `start_date` datetime NOT NULL COMMENT '活动开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '活动结束日期',
  `activity_addr` varchar(256) DEFAULT NULL COMMENT '活动地址',
  `line_name` varchar(256) DEFAULT NULL COMMENT '活动线路',
  `line_id` int(11) DEFAULT NULL COMMENT '活动线路id',
  `activity_peoples` int(11) DEFAULT NULL COMMENT '活动满员人数',
  `activity_img_url` varchar(256) DEFAULT NULL COMMENT '活动图片url',
  `city` varchar(128) DEFAULT NULL COMMENT '城市',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `activity_num` varchar(128) DEFAULT NULL COMMENT '活动期数',
  `activity_desc` varchar(512) DEFAULT NULL COMMENT '活动描述',
  `activity_type` varchar(8) NOT NULL COMMENT '活动类型',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `updater_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='活动信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_info`
--

LOCK TABLES `t_activity_info` WRITE;
/*!40000 ALTER TABLE `t_activity_info` DISABLE KEYS */;
INSERT INTO `t_activity_info` VALUES (3,'上海一日游','2016-12-05 22:02:00','2016-12-09 22:02:00','',NULL,NULL,30,'/imgextra/ACTIVITY/2016/129da015-2785-4761-b152-3f488868c063.jpg','上海',NULL,'上海一日游第1期','','2',2016,12,1,'管理员','2016-12-05 14:02:31',NULL,NULL,NULL,'N');
/*!40000 ALTER TABLE `t_activity_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity_marines`
--

DROP TABLE IF EXISTS `t_activity_marines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_marines` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `marine_name` varchar(128) NOT NULL COMMENT '战队名称',
  `marine_img` varchar(128) DEFAULT NULL COMMENT '战队图片',
  `marine_slogan` varchar(256) DEFAULT NULL COMMENT '战队口号',
  `marine_prize` varchar(128) DEFAULT NULL COMMENT '战队奖项',
  `line_name` varchar(128) DEFAULT NULL COMMENT '线路名称',
  `line_id` int(11) DEFAULT NULL COMMENT '线路id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `status` int(2) DEFAULT '0' COMMENT '战队状态 0:准备中 1:已绑定',
  `comment` varchar(512) DEFAULT NULL COMMENT '总评',
  `votes` int(11) DEFAULT '0' COMMENT '支持票数',
  `readnum` int(11) DEFAULT '0' COMMENT '阅读量',
  `score` int(11) DEFAULT '0' COMMENT '得分',
  `qrcode_url` varchar(256) DEFAULT NULL COMMENT '微信二维码',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='活动战队信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_marines`
--

LOCK TABLES `t_activity_marines` WRITE;
/*!40000 ALTER TABLE `t_activity_marines` DISABLE KEYS */;
INSERT INTO `t_activity_marines` VALUES (15,'小虎队',NULL,NULL,NULL,NULL,NULL,3,0,NULL,0,0,0,'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEo8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyTzl2TjhHSEdlazIxam5rSmhvMTAAAgTXh0VYAwQAjScA',1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(16,'小牛队',NULL,NULL,NULL,NULL,NULL,3,0,NULL,0,0,0,'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGz8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAybW5pQThHSEdlazIxaEJwSjFvMTkAAgRljEVYAwQAjScA',1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(17,'小马队',NULL,NULL,NULL,NULL,NULL,3,0,NULL,0,0,0,'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQE28DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyc1ZvZDl1SEdlazIxaENwSmhvMTcAAgRmjEVYAwQAjScA',1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(18,'小黄人队',NULL,NULL,NULL,NULL,NULL,3,0,NULL,0,0,0,'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFd8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycWgxXzliSEdlazIxaENwSmhvMVYAAgRmjEVYAwQAjScA',1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N'),(19,'小蜻蜓队',NULL,NULL,NULL,NULL,NULL,3,0,NULL,0,0,0,'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOW9DZTlaSEdlazIxaENwSjFvMXcAAgRmjEVYAwQAjScA',1,'管理员','2016-12-05 14:03:20',NULL,NULL,NULL,'N');
/*!40000 ALTER TABLE `t_activity_marines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity_marines_support`
--

DROP TABLE IF EXISTS `t_activity_marines_support`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_marines_support` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `openid` varchar(128) DEFAULT NULL COMMENT '公众号开放id',
  `nickName` varchar(128) DEFAULT NULL COMMENT '昵称',
  `img_url` varchar(128) DEFAULT NULL COMMENT '图片url',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `margin_id` int(22) DEFAULT NULL COMMENT '战队id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='战队支持记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_marines_support`
--

LOCK TABLES `t_activity_marines_support` WRITE;
/*!40000 ALTER TABLE `t_activity_marines_support` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_activity_marines_support` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity_resource`
--

DROP TABLE IF EXISTS `t_activity_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_resource` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL COMMENT '资源路径',
  `activity_id` int(22) DEFAULT NULL COMMENT '活动id',
  `marine_id` int(22) NOT NULL COMMENT '战队id',
  `hm_id` int(22) NOT NULL COMMENT '透明人id',
  `resource_type` varchar(2) DEFAULT NULL COMMENT '资源类型 1图片 2视频 3语音 4文字 5定位',
  `resource` varchar(1000) DEFAULT NULL COMMENT '资源',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_resource`
--

LOCK TABLES `t_activity_resource` WRITE;
/*!40000 ALTER TABLE `t_activity_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_activity_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity_teacher`
--

DROP TABLE IF EXISTS `t_activity_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity_teacher` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '老师姓名',
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动编号',
  `marine_id` int(11) DEFAULT NULL COMMENT '活动编号',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N正常 Y无效',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='活动参与老师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity_teacher`
--

LOCK TABLES `t_activity_teacher` WRITE;
/*!40000 ALTER TABLE `t_activity_teacher` DISABLE KEYS */;
INSERT INTO `t_activity_teacher` VALUES (9,'张师一','13681981231',3,15,'N',1,'2016-12-05 14:03:20','管理员',NULL,'0000-00-00 00:00:00',NULL),(10,'李师二','13681981232',3,15,'N',1,'2016-12-05 14:03:20','管理员',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `t_activity_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_buy_record`
--

DROP TABLE IF EXISTS `t_buy_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_buy_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  `product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `product_type` varchar(2) DEFAULT NULL COMMENT '商品类型 1活动 2课程 3小商品',
  `mobile` varchar(11) DEFAULT NULL COMMENT '购买人手机号码',
  `amount` decimal(22,2) DEFAULT NULL COMMENT '支付金额',
  `exchangePoints` int(11) DEFAULT NULL COMMENT '积分兑换点',
  `give_points` int(11) DEFAULT NULL COMMENT '赠送积点',
  `buy_type` varchar(2) DEFAULT '1' COMMENT '购买类型 1现金 2积分',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购买记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_buy_record`
--

LOCK TABLES `t_buy_record` WRITE;
/*!40000 ALTER TABLE `t_buy_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_buy_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods_detail`
--

DROP TABLE IF EXISTS `t_goods_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(128) NOT NULL COMMENT '商品名称',
  `stock` int(11) NOT NULL COMMENT '库存',
  `goods_no` varchar(32) NOT NULL COMMENT '商品编号',
  `color` varchar(64) DEFAULT NULL COMMENT '商品颜色',
  `main_img` varchar(128) NOT NULL COMMENT '商品主图',
  `price` decimal(22,2) NOT NULL COMMENT '原价',
  `discount_price` decimal(22,2) DEFAULT NULL COMMENT '折扣价',
  `points` int(11) DEFAULT NULL COMMENT '积分兑换点',
  `product_desc_url` varchar(258) DEFAULT NULL COMMENT '商品描述url',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `updater_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT NULL COMMENT '是否有效 N正常 Y失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods_detail`
--

LOCK TABLES `t_goods_detail` WRITE;
/*!40000 ALTER TABLE `t_goods_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_goods_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_hollow_man_info`
--

DROP TABLE IF EXISTS `t_hollow_man_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_hollow_man_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hm_name` varchar(128) NOT NULL COMMENT '透明人姓名',
  `hm_mobile` varchar(11) NOT NULL COMMENT '透明人手机号码',
  `id_type` varchar(8) DEFAULT NULL COMMENT '透明人证件类型',
  `id_card` varchar(64) DEFAULT NULL COMMENT '透明人证件号码',
  `addr` varchar(128) DEFAULT NULL COMMENT '透明人住址',
  `place` varchar(128) DEFAULT NULL COMMENT '籍贯',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别 F男 M女',
  `grade` varchar(32) DEFAULT NULL COMMENT '籍贯',
  `schoole` varchar(128) DEFAULT NULL COMMENT '透明人学校名称',
  `id_photo` varchar(128) DEFAULT NULL COMMENT '证件照',
  `kim_name` varchar(128) DEFAULT NULL COMMENT '透明人亲属姓名',
  `kim_mobile` varchar(11) DEFAULT NULL COMMENT '透明人亲属手机号码',
  `is_active` int(2) DEFAULT '0' COMMENT '是否激活 0未激活 1已激活',
  `activeDate` varchar(8) DEFAULT NULL COMMENT '激活日期',
  `openid` varchar(64) DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `level` varchar(4) DEFAULT NULL COMMENT '级别',
  `score` decimal(8,2) DEFAULT NULL COMMENT '评分',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '变更人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '变更时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  PRIMARY KEY (`id`),
  KEY `idx_hm_name` (`hm_name`),
  KEY `idx_hm_mobile` (`hm_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='透明人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_hollow_man_info`
--

LOCK TABLES `t_hollow_man_info` WRITE;
/*!40000 ALTER TABLE `t_hollow_man_info` DISABLE KEYS */;
INSERT INTO `t_hollow_man_info` VALUES (21,'张透一','13681984634',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(22,'张透二','13681984635',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(23,'刘透一','13681984632',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(24,'刘透二','13681984633',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(25,'马透一','13681984739',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(26,'马透二','13681984738',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(27,'黄透一','13681984836',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(28,'黄透二','13681984837',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(29,'青透一','13681984936',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(30,'青透二','13681984937',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'20161205',NULL,NULL,NULL,'管理员','2016-12-05 14:03:20',NULL,NULL,'N'),(31,'胡启萌','13681984045','01','429004199006274911',NULL,'','F','大四','','/imgextra/IDPHOTO/oqyqUwq_YY84qjFWUtn6Ti4XIROE.jpg',NULL,NULL,1,NULL,'oqyqUwq_YY84qjFWUtn6Ti4XIROE',NULL,NULL,'admin','2016-12-05 14:42:57',NULL,NULL,'N');
/*!40000 ALTER TABLE `t_hollow_man_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_parent_info`
--

DROP TABLE IF EXISTS `t_parent_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_parent_info` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `relation` varchar(4) DEFAULT NULL COMMENT '关系',
  `name` varchar(128) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `openid` varchar(128) DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `sex` int(2) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='家长信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_parent_info`
--

LOCK TABLES `t_parent_info` WRITE;
/*!40000 ALTER TABLE `t_parent_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_parent_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_resource`
--

DROP TABLE IF EXISTS `t_sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_resource` (
  `ID_RESOURCE` int(11) NOT NULL COMMENT '主键',
  `RESOURCE_NAME` varchar(128) NOT NULL COMMENT '资源名称',
  `RESOURCE_URL` varchar(256) DEFAULT NULL COMMENT '资源请求路径',
  `RESOURCE_TYPE` varchar(16) NOT NULL COMMENT '资源类型 M:菜单 P:页面 O:按钮',
  `RESOURCE_CODE` varchar(64) NOT NULL COMMENT '资源唯一标识',
  `PARENT_RESOURCE_ID` int(11) DEFAULT NULL COMMENT '父资源Id',
  `WEIGHT` int(11) NOT NULL COMMENT '顺序权重',
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最新更新人',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETE` varchar(1) DEFAULT 'N' COMMENT '是否删除 Y 删除 N不删除',
  `IS_QUICK_MENU` varchar(1) DEFAULT NULL COMMENT '是否删除 Y 删除 N不删除',
  `IS_SYSTEM_CONF_MENU` varchar(1) DEFAULT NULL COMMENT '系统配置菜单 Y 是 N 不是',
  PRIMARY KEY (`ID_RESOURCE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource`
--

LOCK TABLES `t_sys_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_resource` DISABLE KEYS */;
INSERT INTO `t_sys_resource` VALUES (10,'子帐户与权限管理',NULL,'M','authority_manage',0,10,'admin',NULL,'2015-09-21 08:20:53',NULL,'N','N',''),(20,'站内管理',NULL,'M','content_manage',0,20,'admin',NULL,'2015-09-17 03:08:23',NULL,'N','N',''),(30,'活动管理',NULL,'M','activity_manage',0,30,'admin',NULL,'2015-09-21 08:26:32',NULL,'N','N',''),(40,'课程管理',NULL,'M','course_manage',0,40,'admin',NULL,'2015-09-25 09:14:01',NULL,'N','N',''),(50,'家长管理',NULL,'M','family_manage',0,50,'admin',NULL,'2015-09-25 09:14:01',NULL,'N','N',''),(60,'透明人管理',NULL,'M','hollow_man_manage',0,60,'admin',NULL,'2015-09-25 09:14:01',NULL,'N','N',''),(70,'评论管理',NULL,'M','comment_manage',0,70,'admin',NULL,'2015-09-25 09:14:01',NULL,'N','N',''),(100,'财房管理',NULL,'M','money_manage',0,100,'admin',NULL,'2015-09-25 09:14:01',NULL,'N','N',''),(1010,'角色管理','/power/role','M','role_manage',10,10,'admin',NULL,'2016-11-13 03:39:07',NULL,'N',NULL,''),(1015,'用户管理','/power/user','M','user_manage',10,20,'admin',NULL,'2016-11-13 03:43:16',NULL,'N',NULL,''),(1020,'菜单管理','/power/menu','M','menu_manage',10,30,'admin',NULL,'2016-11-13 11:35:02',NULL,'N',NULL,''),(2010,'图组管理','/content/img/group','M','content_img_group',20,10,'admin',NULL,'2016-11-13 13:00:51',NULL,'N',NULL,''),(2020,'站内文章编辑','/content/article/edit','M','content_article_edit',20,20,'admin',NULL,'2016-11-25 01:27:14',NULL,'N',NULL,NULL),(2030,'关键词管理','/content/keyword','M','content_keyword',20,30,'admin',NULL,'2016-12-03 07:03:28',NULL,'N',NULL,NULL),(3010,'活动管理','/activity/manage','M','activity_manage',30,10,'admin',NULL,'2016-11-13 04:04:08',NULL,'N',NULL,''),(3020,'名单管理','/activity/namelist','M','activity_namelist',30,30,'admin',NULL,'2016-11-19 18:22:34',NULL,'N',NULL,''),(3030,'战队管理','/activity/marines','M','activity_marines',30,20,'admin',NULL,'2016-11-19 18:23:40',NULL,'N','N',''),(3035,'活动透明人查询','/activity/hm','M','activity_hm',30,35,'admin',NULL,'2016-11-24 13:01:46',NULL,'N',NULL,''),(3040,'透明人资源管理','/activity/hm/resource','M','hollow_man_activity_resource',30,40,'admin',NULL,'2016-11-13 11:40:39',NULL,'N',NULL,''),(3050,'活动老师查询','/activity/teacher','M','activity_teacher',30,50,NULL,NULL,'2016-11-24 13:55:15',NULL,'N',NULL,''),(5010,'家长绑定查询','/parent/bind/query','M','parent_bind_query',50,10,'admin',NULL,'2016-11-13 11:37:24',NULL,'N',NULL,''),(5020,'家长购买记录','/parent/buy/record','M','parent_buy_record',50,20,'admin',NULL,'2016-11-13 11:38:18',NULL,'N',NULL,''),(6010,'透明人查询','/hm/query','M','hollow_man_query',60,10,'admin',NULL,'2016-11-13 11:39:47',NULL,'N',NULL,'');
/*!40000 ALTER TABLE `t_sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_resource_role_rel`
--

DROP TABLE IF EXISTS `t_sys_resource_role_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_resource_role_rel` (
  `ID_RESOURCE_ROLE_REL` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ID_RESOURCE` int(11) NOT NULL COMMENT '系统资源ID',
  `ID_ROLE` int(11) NOT NULL COMMENT '角色ID',
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  `IS_DELETE` varchar(1) DEFAULT 'N' COMMENT '是否删除 Y 删除 N不删除',
  PRIMARY KEY (`ID_RESOURCE_ROLE_REL`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='系统资源与角色的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource_role_rel`
--

LOCK TABLES `t_sys_resource_role_rel` WRITE;
/*!40000 ALTER TABLE `t_sys_resource_role_rel` DISABLE KEYS */;
INSERT INTO `t_sys_resource_role_rel` VALUES (49,30,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(50,3010,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(51,60,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(52,6010,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(53,6020,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(58,30,3,NULL,NULL,'2016-11-19 16:58:38',NULL,'N'),(59,3010,3,NULL,NULL,'2016-11-19 16:58:38',NULL,'N'),(125,10,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(126,1010,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(127,1015,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(128,1020,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(129,20,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(130,2010,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(131,30,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(132,3010,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(133,50,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(134,5010,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(135,5020,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(136,60,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N'),(137,6010,1,NULL,NULL,'2016-11-25 15:09:46',NULL,'N');
/*!40000 ALTER TABLE `t_sys_resource_role_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role`
--

DROP TABLE IF EXISTS `t_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role` (
  `ID_ROLE` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_NAME` varchar(128) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  `IS_DELETE` varchar(1) DEFAULT 'N' COMMENT '是否删除 Y 删除 N不删除',
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role`
--

LOCK TABLES `t_sys_role` WRITE;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
INSERT INTO `t_sys_role` VALUES (1,'管理员','活动管理员','???','admin','2016-11-16 21:51:09','2016-11-25 15:09:47','N'),(2,'活动管理员','活动管理员','???',NULL,'2016-11-16 22:16:49',NULL,'N'),(3,'活动管理员','活动管理员','admin','admin','2016-11-19 16:06:20','2016-11-19 16:58:38','N');
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `USER_ACCOUNT` varchar(45) NOT NULL COMMENT '用户登陆账号',
  `USER_NAME` varchar(45) NOT NULL COMMENT '姓名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `VALID_FLAG` int(11) NOT NULL DEFAULT '1' COMMENT '有效性 1：有效 0：失效',
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  `user_type` int(11) DEFAULT '2' COMMENT '账户类型 1管理员 2一般账户',
  PRIMARY KEY (`ID_USER`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` VALUES (1,'admin','管理员','c4ca4238a0b923820dcc509a6f75849b',1,'default',NULL,'2016-11-02 07:03:18',NULL,1),(7,'abc','abc','',1,'default','admin','2016-11-16 16:37:43','2016-11-25 14:45:46',2),(8,'ab','ab','',0,'admin','admin','2016-11-16 16:42:38','2016-11-17 01:21:21',2),(9,'abcd','abcd','e2fc714c4727ee9395f324cd2e7f331f',1,'admin',NULL,'2016-11-16 17:11:04',NULL,2);
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user_role_rel`
--

DROP TABLE IF EXISTS `t_sys_user_role_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user_role_rel` (
  `ID_USER_ROLE_REL` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ID_USER` int(11) NOT NULL COMMENT '用户ID',
  `ID_ROLE` int(11) NOT NULL COMMENT '角色ID',
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  `IS_DELETE` varchar(1) DEFAULT 'N' COMMENT '是否删除 Y 删除 N不删除',
  PRIMARY KEY (`ID_USER_ROLE_REL`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user_role_rel`
--

LOCK TABLES `t_sys_user_role_rel` WRITE;
/*!40000 ALTER TABLE `t_sys_user_role_rel` DISABLE KEYS */;
INSERT INTO `t_sys_user_role_rel` VALUES (8,9,2,NULL,NULL,'2016-11-17 01:11:04',NULL,'N'),(9,8,2,NULL,NULL,'2016-11-17 01:21:21',NULL,'N'),(10,7,2,NULL,NULL,'2016-11-25 14:45:46',NULL,'N'),(11,7,3,NULL,NULL,'2016-11-25 14:45:46',NULL,'N');
/*!40000 ALTER TABLE `t_sys_user_role_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_validate_code`
--

DROP TABLE IF EXISTS `t_validate_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_validate_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `code` varchar(16) NOT NULL COMMENT '验证码',
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_validate_code`
--

LOCK TABLES `t_validate_code` WRITE;
/*!40000 ALTER TABLE `t_validate_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_validate_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wechat_keyword`
--

DROP TABLE IF EXISTS `t_wechat_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wechat_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(64) DEFAULT NULL COMMENT '关键词',
  `msgType` varchar(32) DEFAULT NULL COMMENT '消息类型 文本:text 图片:image 视频:video 语音:music 图文:news',
  `isDelete` varchar(2) DEFAULT 'N' COMMENT '是否有效',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_keyword` (`keyword`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微信关键词管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wechat_keyword`
--

LOCK TABLES `t_wechat_keyword` WRITE;
/*!40000 ALTER TABLE `t_wechat_keyword` DISABLE KEYS */;
INSERT INTO `t_wechat_keyword` VALUES (1,'透明人','news','N',NULL,'2016-12-03 10:13:03',NULL,'0000-00-00 00:00:00');
/*!40000 ALTER TABLE `t_wechat_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wechat_receive_message`
--

DROP TABLE IF EXISTS `t_wechat_receive_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wechat_receive_message` (
  `msgId` char(64) NOT NULL COMMENT '消息id',
  `hmSignId` int(22) DEFAULT NULL COMMENT '透明人注册id',
  `toUserName` varchar(128) DEFAULT NULL COMMENT '开发者微信号',
  `fromUserName` varchar(128) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `msgType` varchar(32) NOT NULL COMMENT '消息类型 文本:text 图片:image 视频:video 语音:music 图文:news',
  `mediaId` varchar(128) DEFAULT NULL COMMENT '通过素材管理接口上传多媒体文件',
  `content` varchar(512) DEFAULT NULL COMMENT '文本内容',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `format` varchar(32) DEFAULT NULL COMMENT '语音格式',
  `recognition` varchar(128) DEFAULT NULL COMMENT '语音识别结果',
  `picUrl` varchar(128) DEFAULT NULL COMMENT '图片Url',
  `url` varchar(128) DEFAULT NULL COMMENT '链接',
  `musicUrl` varchar(128) DEFAULT NULL COMMENT '音乐链接',
  `hqMusicUrl` varchar(128) DEFAULT NULL COMMENT '高质量音乐链接，WIFI环境优先使用该链接播放音乐',
  `thumbMediaId` varchar(128) DEFAULT NULL COMMENT '缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id',
  `locationX` float DEFAULT NULL COMMENT '地理位置维度',
  `locationY` float DEFAULT NULL COMMENT '地理位置经度',
  `scale` int(10) DEFAULT NULL COMMENT '地图缩放大小',
  `label` varchar(128) DEFAULT NULL COMMENT '地理位置信息',
  `isDelete` varchar(2) DEFAULT 'N',
  `createTime` int(22) NOT NULL COMMENT '创建时间',
  `sysCreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`msgId`),
  KEY `idx_from_openid` (`fromUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接收微信端消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wechat_receive_message`
--

LOCK TABLES `t_wechat_receive_message` WRITE;
/*!40000 ALTER TABLE `t_wechat_receive_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_wechat_receive_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wechat_send_message`
--

DROP TABLE IF EXISTS `t_wechat_send_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wechat_send_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyId` int(11) DEFAULT NULL COMMENT '关键词id',
  `keyword` varchar(64) DEFAULT NULL COMMENT '关键词',
  `msgType` varchar(32) NOT NULL COMMENT '消息类型 文本:text 图片:image 视频:video 语音:music 图文:news',
  `mediaId` varchar(128) DEFAULT NULL COMMENT '通过素材管理接口上传多媒体文件',
  `content` varchar(512) DEFAULT NULL COMMENT '文本内容',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `picUrl` varchar(128) DEFAULT NULL COMMENT '图片Url',
  `url` varchar(128) DEFAULT NULL COMMENT '链接',
  `musicUrl` varchar(128) DEFAULT NULL COMMENT '音乐链接',
  `hqMusicUrl` varchar(128) DEFAULT NULL COMMENT '高质量音乐链接，WIFI环境优先使用该链接播放音乐',
  `thumbMediaId` varchar(128) DEFAULT NULL COMMENT '缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id',
  `isDelete` varchar(2) DEFAULT 'N',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='微信发送消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wechat_send_message`
--

LOCK TABLES `t_wechat_send_message` WRITE;
/*!40000 ALTER TABLE `t_wechat_send_message` DISABLE KEYS */;
INSERT INTO `t_wechat_send_message` VALUES (1,1,'透明人','news',NULL,NULL,'透明人管理中心',NULL,'http://www.xue110.top:80/images/m-taoxuequ.jpg','http://m.xue110.top/hm',NULL,NULL,NULL,'N',NULL,'2016-12-03 16:01:11',NULL,'0000-00-00 00:00:00'),(2,1,'透明人','news',NULL,NULL,'透明人微信直播教程',NULL,'http://m.xue110.top:80/images/slider1.jpg','http://mp.weixin.qq.com/s/KN6d3mzGB9e35FLD0ctaZg',NULL,NULL,NULL,'N',NULL,'2016-12-03 16:02:41',NULL,'0000-00-00 00:00:00');
/*!40000 ALTER TABLE `t_wechat_send_message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-06  0:53:14
