/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.31 : Database - familybang
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`familybang` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `familybang`;

/*Table structure for table `t_sys_resource` */

DROP TABLE IF EXISTS `t_sys_resource`;

CREATE TABLE `t_sys_resource` (
  `ID_RESOURCE` int(11) NOT NULL COMMENT '主键',
  `RESOURCE_NAME` varchar(128) NOT NULL COMMENT '资源名称',
  `RESOURCE_URL` varchar(256) DEFAULT NULL COMMENT '资源请求路径',
  `RESOURCE_TYPE` varchar(16) NOT NULL COMMENT '资源类型 M:菜单 P:页面 O:按钮',
  `RESOURCE_CODE` varchar(64) NOT NULL COMMENT '资源唯一标识',
  `PARENT_RESOURCE_ID` int(11) DEFAULT NULL COMMENT '父资源Id',
  `WEIGHT` int(11) NOT NULL COMMENT '顺序权重',
  `IS_DELETE` varchar(1) DEFAULT NULL COMMENT '是否删除 Y 删除 N不删除',
  `IS_SYSTEM_CONF_MENU` varchar(1) NOT NULL COMMENT '系统配置菜单 Y 是 N 不是',
  PRIMARY KEY (`ID_RESOURCE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_resource` */

/*Table structure for table `t_sys_resource_role_rel` */

DROP TABLE IF EXISTS `t_sys_resource_role_rel`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源与角色的关系表';

/*Data for the table `t_sys_resource_role_rel` */

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `t_sys_role` */

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

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
  `USER_TYPE` int(11) NOT NULL DEFAULT '1' COMMENT '用户类型 1-超级管理员 2-普通用户',
  PRIMARY KEY (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`ID_USER`,`USER_ACCOUNT`,`USER_NAME`,`PASSWORD`,`VALID_FLAG`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`USER_TYPE`) values ('1','admin','管理员','d2a99bd0e8f7d7f169e4feaa08e32215',1,'default',NULL,'2016-09-03 23:11:36',NULL,1);

/*Table structure for table `t_sys_user_role_rel` */

DROP TABLE IF EXISTS `t_sys_user_role_rel`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系表';

/*Data for the table `t_sys_user_role_rel` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
