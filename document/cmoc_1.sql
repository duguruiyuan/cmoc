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
  `IS_DELETE` varchar(1) DEFAULT NULL COMMENT '是否删除 Y 删除 N不删除',
  `IS_QUICK_MENU` varchar(1) DEFAULT NULL COMMENT '是否删除 Y 删除 N不删除',
  `IS_SYSTEM_CONF_MENU` varchar(1) NOT NULL COMMENT '系统配置菜单 Y 是 N 不是',
  PRIMARY KEY (`ID_RESOURCE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource`
--

LOCK TABLES `t_sys_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_resource` DISABLE KEYS */;
INSERT INTO `t_sys_resource` VALUES (-1,'商户管理平台菜单',NULL,'M','ROOT',NULL,1,'zs','admin','2015-09-07 03:19:07','2015-09-25 17:14:05','N','N',''),(10,'商家信息管理',NULL,'M','MERCHANTS_INFOMATION_MANAGE',-1,10,'admin','admin','2015-09-21 08:20:53','2015-09-21 16:20:53','N','N',''),(20,'子帐户与权限管理',NULL,'M','SUB_ACCOUNT_AND_AUTHORITY_MANAGE',-1,20,'admin','admin','2015-09-17 03:08:23','2015-09-17 11:08:23','N','Y',''),(30,'商品管理',NULL,'M','GOODS_MANAGE',-1,30,'admin','admin','2015-09-21 08:26:32','2015-09-21 16:26:32','N','N',''),(40,'订单管理',NULL,'M','ORDER_MANAGE',-1,40,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','N','N',''),(50,'物流管理',NULL,'M','LOGISTICS_MANAGE',-1,50,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','Y','N',''),(60,'评论管理',NULL,'M','COMMENT_MANAGE',-1,60,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','Y','N',''),(70,'商家促销管理',NULL,'M','BUSINESS_SALES_MANAGE',-1,70,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','Y','N',''),(80,'营销管理',NULL,'M','MARKETING_MANAGE',-1,80,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','Y','N',''),(90,'财房管理',NULL,'M','MONEYROOM_MANAGE',-1,90,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','N','N',''),(100,'统计报表管理',NULL,'M','STAT_REPORT_MANAGE',-1,100,'admin','admin','2015-09-25 09:14:01','2015-09-25 17:14:05','Y','N',''),(150,'公司基本信息','/merchant/company/show.do','M','COMPANY_BASIC_INFOMATION',10,10,'admin','admin','2015-09-21 08:26:42','2015-09-21 16:26:42','N','N','');
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='系统资源与角色的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource_role_rel`
--

LOCK TABLES `t_sys_resource_role_rel` WRITE;
/*!40000 ALTER TABLE `t_sys_resource_role_rel` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role`
--

LOCK TABLES `t_sys_role` WRITE;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user` (
  `ID_USER` varchar(32) NOT NULL COMMENT '用户id',
  `USER_ACCOUNT` varchar(45) NOT NULL COMMENT '用户登陆账号',
  `USER_NAME` varchar(45) NOT NULL COMMENT '姓名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `VALID_FLAG` int(11) NOT NULL DEFAULT '1' COMMENT '有效性 1：有效 0：失效',
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE t_sys_user ADD COLUMN user_type INT DEFAULT 2 COMMENT '账户类型 1管理员 2一般账户';

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` VALUES ('1','admin','管理员','c4ca4238a0b923820dcc509a6f75849b',1,'default',NULL,'2016-11-02 07:03:18',NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user_role_rel`
--

LOCK TABLES `t_sys_user_role_rel` WRITE;
/*!40000 ALTER TABLE `t_sys_user_role_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_user_role_rel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-02 16:51:41
