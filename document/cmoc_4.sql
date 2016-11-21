/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.6.34 : Database - cmoc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cmoc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cmoc`;

/*Table structure for table `t_activity_family` */

DROP TABLE IF EXISTS `t_activity_family`;

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
  FULLTEXT KEY `idx_father_name` (`father_name`),
  FULLTEXT KEY `idx_father_mobile` (`father_mobile`),
  FULLTEXT KEY `idx_mother_name` (`mother_name`),
  FULLTEXT KEY `idx_mother_mobile` (`mother_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='活动报名家庭信息';

/*Data for the table `t_activity_family` */

insert  into `t_activity_family`(`id`,`father_name`,`father_mobile`,`father_age`,`mother_name`,`mother_mobile`,`mother_age`,`other_name`,`other_mobile`,`other_age`,`child_name`,`child_mobile`,`child_age`,`child_img`,`child_title`,`child_comment`,`activity_id`,`marine_id`,`creater_user_id`,`creater`,`create_time`,`updater_user_id`,`updater`,`update_time`,`is_delete`) values (1,'张虎爸一','13500000000',NULL,'张虎妈一','13600000000',NULL,NULL,NULL,NULL,'张虎一',NULL,NULL,NULL,'小队长',NULL,2,4,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(2,'张虎爸二','13500000001',NULL,'张虎妈二','13600000001',NULL,NULL,NULL,NULL,'张虎二',NULL,NULL,NULL,'体育尖兵',NULL,2,4,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(3,'张虎爸三','13500000002',NULL,'张虎妈三','13600000002',NULL,NULL,NULL,NULL,'张虎三',NULL,NULL,NULL,'后勤部长',NULL,2,4,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(4,'张虎爸四','13500000003',NULL,'张虎妈四','13600000003',NULL,NULL,NULL,NULL,'张虎四',NULL,NULL,NULL,'头脑特工',NULL,2,4,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(5,'张虎爸五','13500000004',NULL,'张虎妈五','13600000004',NULL,NULL,NULL,NULL,'张虎五',NULL,NULL,NULL,'谈判专家',NULL,2,4,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(6,'刘爸一','13510000000',NULL,'刘妈一','13610000000',NULL,NULL,NULL,NULL,'刘一',NULL,NULL,NULL,'小队长',NULL,2,5,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(7,'刘爸二','13510000001',NULL,'刘妈二','13610000001',NULL,NULL,NULL,NULL,'刘二',NULL,NULL,NULL,'体育尖兵',NULL,2,5,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(8,'刘爸三','13510000002',NULL,'刘妈三','13610000002',NULL,NULL,NULL,NULL,'刘三',NULL,NULL,NULL,'后勤部长',NULL,2,5,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(9,'刘爸四','13510000003',NULL,'刘妈四','13610000003',NULL,NULL,NULL,NULL,'刘四',NULL,NULL,NULL,'头脑特工',NULL,2,5,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(10,'刘爸五','13510000004',NULL,'刘妈五','13610000004',NULL,NULL,NULL,NULL,'刘五',NULL,NULL,NULL,'谈判专家',NULL,2,5,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(11,'马爸一','13520000000',NULL,'马爸一','13620000000',NULL,NULL,NULL,NULL,'马一',NULL,NULL,NULL,'小队长',NULL,2,6,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(12,'马爸二','13520000001',NULL,'马爸二','13620000001',NULL,NULL,NULL,NULL,'马二',NULL,NULL,NULL,'体育尖兵',NULL,2,6,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(13,'马爸三','13520000002',NULL,'马爸三','13620000002',NULL,NULL,NULL,NULL,'马三',NULL,NULL,NULL,'后勤部长',NULL,2,6,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(14,'马爸四','13520000003',NULL,'马爸四','13620000003',NULL,NULL,NULL,NULL,'马四',NULL,NULL,NULL,'头脑特工',NULL,2,6,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(15,'马爸五','13520000004',NULL,'马爸五','13620000004',NULL,NULL,NULL,NULL,'马五',NULL,NULL,NULL,'谈判专家',NULL,2,6,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N');

/*Table structure for table `t_activity_hm_sign` */

DROP TABLE IF EXISTS `t_activity_hm_sign`;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='透明人活动报名表';

/*Data for the table `t_activity_hm_sign` */

insert  into `t_activity_hm_sign`(`id`,`activity_id`,`line_id`,`marine_id`,`hm_id`,`sign_date`,`is_effect`,`effect_date`,`score`,`judge`,`creater_user_id`,`creater`,`create_time`,`updater_user_id`,`updater`,`update_time`,`is_delete`) values (1,2,NULL,4,1,'20161121',1,'20161121',NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(2,2,NULL,4,2,'20161121',1,'20161121',NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(3,2,NULL,5,3,'20161121',1,'20161121',NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(4,2,NULL,5,4,'20161121',1,'20161121',NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(5,2,NULL,6,5,'20161121',1,'20161121',NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(6,2,NULL,6,6,'20161121',1,'20161121',NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N');

/*Table structure for table `t_activity_info` */

DROP TABLE IF EXISTS `t_activity_info`;

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

/*Data for the table `t_activity_info` */

insert  into `t_activity_info`(`id`,`activity_name`,`start_date`,`end_date`,`activity_addr`,`line_name`,`line_id`,`activity_peoples`,`activity_img_url`,`city`,`city_id`,`activity_num`,`activity_desc`,`activity_type`,`year`,`month`,`creater_user_id`,`creater`,`create_time`,`updater_user_id`,`updater`,`updater_time`,`is_delete`) values (2,'上海一日游','2016-11-23 01:45:00','2016-11-30 01:45:00','的孙菲菲',NULL,NULL,25,NULL,'上海',NULL,'上海一日游1期','发斯蒂芬','1',2016,11,NULL,NULL,'2016-11-20 01:34:03',1,'管理员','2016-11-21 15:34:34','N'),(3,'武汉一日游','2016-11-21 01:54:00','2016-11-22 01:54:00','发',NULL,NULL,40,NULL,'武汉',NULL,'武汉一日游1期','的飞洒','1',2016,11,1,'管理员','2016-11-20 01:54:46',1,'管理员','2016-11-20 10:51:56','N');

/*Table structure for table `t_activity_marines` */

DROP TABLE IF EXISTS `t_activity_marines`;

CREATE TABLE `t_activity_marines` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `marine_name` varchar(128) NOT NULL COMMENT '战队名称',
  `marine_img` varchar(128) DEFAULT NULL COMMENT '战队图片',
  `marine_slogan` varchar(256) DEFAULT NULL COMMENT '战队口号',
  `marine_prize` varchar(128) DEFAULT NULL COMMENT '战队奖项',
  `line_name` varchar(128) DEFAULT NULL COMMENT '线路名称',
  `line_id` int(11) DEFAULT NULL COMMENT '线路id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `votes` int(11) DEFAULT NULL COMMENT '支持票数',
  `reads` int(11) DEFAULT NULL COMMENT '阅读量',
  `score` int(11) DEFAULT NULL COMMENT '得分',
  `creater_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='活动战队信息';

/*Data for the table `t_activity_marines` */

insert  into `t_activity_marines`(`id`,`marine_name`,`marine_img`,`marine_slogan`,`marine_prize`,`line_name`,`line_id`,`activity_id`,`votes`,`reads`,`score`,`creater_user_id`,`creater`,`create_time`,`updater_user_id`,`updater`,`update_time`,`is_delete`) values (4,'小虎队',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(5,'小牛队',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N'),(6,'小马队',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,1,'管理员','2016-11-21 15:32:39',NULL,NULL,NULL,'N');

/*Table structure for table `t_activity_marines_support` */

DROP TABLE IF EXISTS `t_activity_marines_support`;

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

/*Data for the table `t_activity_marines_support` */

/*Table structure for table `t_activity_resource` */

DROP TABLE IF EXISTS `t_activity_resource`;

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

/*Data for the table `t_activity_resource` */

/*Table structure for table `t_activity_teacher` */

DROP TABLE IF EXISTS `t_activity_teacher`;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='活动参与老师';

/*Data for the table `t_activity_teacher` */

insert  into `t_activity_teacher`(`id`,`name`,`mobile`,`activity_id`,`marine_id`,`is_delete`,`creater_user_id`,`create_time`,`creater`,`updater_user_id`,`update_time`,`updater`) values (3,'李老师','13710000000',2,4,'N',1,'2016-11-21 15:32:39','管理员',NULL,'0000-00-00 00:00:00',NULL),(4,'王老师','13710000001',2,4,'N',1,'2016-11-21 15:32:39','管理员',NULL,'0000-00-00 00:00:00',NULL);

/*Table structure for table `t_buy_record` */

DROP TABLE IF EXISTS `t_buy_record`;

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

/*Data for the table `t_buy_record` */

/*Table structure for table `t_goods_detail` */

DROP TABLE IF EXISTS `t_goods_detail`;

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

/*Data for the table `t_goods_detail` */

/*Table structure for table `t_hollow_man_info` */

DROP TABLE IF EXISTS `t_hollow_man_info`;

CREATE TABLE `t_hollow_man_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hm_name` varchar(128) NOT NULL COMMENT '透明人姓名',
  `hm_mobile` varchar(11) NOT NULL COMMENT '透明人手机号码',
  `hm_id_type` varchar(8) DEFAULT NULL COMMENT '透明人证件类型',
  `hm_id_card` varchar(64) DEFAULT NULL COMMENT '透明人证件号码',
  `hm_addr` varchar(128) DEFAULT NULL COMMENT '透明人住址',
  `hm_schoole` varchar(128) DEFAULT NULL COMMENT '透明人学校名称',
  `hm_kim_name` varchar(128) DEFAULT NULL COMMENT '透明人亲属姓名',
  `hm_kim_mobile` varchar(11) DEFAULT NULL COMMENT '透明人手机号码',
  `is_active` int(2) DEFAULT '0' COMMENT '是否激活 0未激活 1已激活',
  `active` varchar(8) DEFAULT NULL COMMENT '激活日期',
  `openid` varchar(64) DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `level` varchar(4) DEFAULT NULL COMMENT '级别',
  `score` decimal(8,2) DEFAULT NULL COMMENT '评分',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '变更人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '变更时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y失效',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `idx_hm_name` (`hm_name`),
  FULLTEXT KEY `idx_hm_mobile` (`hm_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='透明人信息';

/*Data for the table `t_hollow_man_info` */

insert  into `t_hollow_man_info`(`id`,`hm_name`,`hm_mobile`,`hm_id_type`,`hm_id_card`,`hm_addr`,`hm_schoole`,`hm_kim_name`,`hm_kim_mobile`,`is_active`,`active`,`openid`,`level`,`score`,`creater`,`create_time`,`updater`,`update_time`,`is_delete`) values (1,'青溪','13800000000',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员','2016-11-21 15:32:39',NULL,NULL,'N'),(2,'乞年','13800000001',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员','2016-11-21 15:32:39',NULL,NULL,'N'),(3,'潇潇','13810000000',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员','2016-11-21 15:32:39',NULL,NULL,'N'),(4,'落雨','13810000001',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员','2016-11-21 15:32:39',NULL,NULL,'N'),(5,'茹存','13820000000',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员','2016-11-21 15:32:39',NULL,NULL,'N'),(6,'西语','13820000001',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员','2016-11-21 15:32:39',NULL,NULL,'N');

/*Table structure for table `t_parent_info` */

DROP TABLE IF EXISTS `t_parent_info`;

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
  FULLTEXT KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='家长信息';

/*Data for the table `t_parent_info` */

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
  `CREATOR` varchar(64) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATOR` varchar(64) DEFAULT NULL COMMENT '最新更新人',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETE` varchar(1) DEFAULT 'N' COMMENT '是否删除 Y 删除 N不删除',
  `IS_QUICK_MENU` varchar(1) DEFAULT NULL COMMENT '是否删除 Y 删除 N不删除',
  `IS_SYSTEM_CONF_MENU` varchar(1) NOT NULL COMMENT '系统配置菜单 Y 是 N 不是',
  PRIMARY KEY (`ID_RESOURCE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_resource` */

insert  into `t_sys_resource`(`ID_RESOURCE`,`RESOURCE_NAME`,`RESOURCE_URL`,`RESOURCE_TYPE`,`RESOURCE_CODE`,`PARENT_RESOURCE_ID`,`WEIGHT`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`IS_DELETE`,`IS_QUICK_MENU`,`IS_SYSTEM_CONF_MENU`) values (10,'子帐户与权限管理',NULL,'M','authority_manage',0,10,'admin',NULL,'2015-09-21 16:20:53',NULL,'N','N',''),(20,'站内管理',NULL,'M','content_manage',0,20,'admin',NULL,'2015-09-17 11:08:23',NULL,'N','N',''),(30,'活动管理',NULL,'M','activity_manage',0,30,'admin',NULL,'2015-09-21 16:26:32',NULL,'N','N',''),(40,'课程管理',NULL,'M','course_manage',0,40,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(50,'家长管理',NULL,'M','family_manage',0,50,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(60,'透明人管理',NULL,'M','hollow_man_manage',0,60,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(70,'评论管理',NULL,'M','comment_manage',0,70,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(100,'财房管理',NULL,'M','money_manage',0,100,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(1010,'角色管理','/power/role','M','role_manage',10,10,'admin',NULL,'2016-11-13 11:39:07',NULL,'N',NULL,''),(1015,'用户管理','/power/user','M','user_manage',10,20,'admin',NULL,'2016-11-13 11:43:16',NULL,'N',NULL,''),(1020,'菜单管理','/power/menu','M','menu_manage',10,30,'admin',NULL,'2016-11-13 19:35:02',NULL,'N',NULL,''),(2010,'图组管理','/content/img/group','M','content_img_group',20,10,'admin',NULL,'2016-11-13 21:00:51',NULL,'N',NULL,''),(3010,'活动管理','/activity/manage','M','activity_manage',30,10,'admin',NULL,'2016-11-13 12:04:08',NULL,'N',NULL,''),(3020,'名单管理','/activity/namelist','M','activity_namelist',30,20,'admin',NULL,'2016-11-20 02:22:34',NULL,'N',NULL,''),(3030,'战队管理','/activity/marines','M','activity_marines',30,30,'admin',NULL,'2016-11-20 02:23:40',NULL,'N','N',''),(3040,'透明人资源管理','/activity/hm/resource','M','hollow_man_activity_resource',30,40,'admin',NULL,'2016-11-13 19:40:39',NULL,'N',NULL,''),(5010,'家长绑定查询','/parent/bind/query','M','parent_bind_query',50,10,'admin',NULL,'2016-11-13 19:37:24',NULL,'N',NULL,''),(5020,'家长购买记录','/parent/buy/record','M','parent_buy_record',50,20,'admin',NULL,'2016-11-13 19:38:18',NULL,'N',NULL,''),(6010,'透明人查询','/hm/query','M','hollow_man_query',60,10,'admin',NULL,'2016-11-13 19:39:47',NULL,'N',NULL,'');

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='系统资源与角色的关系表';

/*Data for the table `t_sys_resource_role_rel` */

insert  into `t_sys_resource_role_rel`(`ID_RESOURCE_ROLE_REL`,`ID_RESOURCE`,`ID_ROLE`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`IS_DELETE`) values (35,10,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(36,1010,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(37,1015,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(38,1020,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(39,20,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(40,2010,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(41,30,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(42,3010,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(43,50,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(44,5010,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(45,5020,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(46,60,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(47,6010,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(48,6020,1,NULL,NULL,'2016-11-16 22:15:43',NULL,'N'),(49,30,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(50,3010,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(51,60,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(52,6010,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(53,6020,2,NULL,NULL,'2016-11-16 22:16:48',NULL,'N'),(58,30,3,NULL,NULL,'2016-11-19 16:58:38',NULL,'N'),(59,3010,3,NULL,NULL,'2016-11-19 16:58:38',NULL,'N');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`ID_ROLE`,`ROLE_NAME`,`ROLE_DESC`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`IS_DELETE`) values (1,'管理员','活动管理员','???','???','2016-11-16 21:51:09','2016-11-16 22:15:43','N'),(2,'活动管理员','活动管理员','???',NULL,'2016-11-16 22:16:49',NULL,'N'),(3,'活动管理员','活动管理员','admin','admin','2016-11-19 16:06:20','2016-11-19 16:58:38','N');

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

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

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`ID_USER`,`USER_ACCOUNT`,`USER_NAME`,`PASSWORD`,`VALID_FLAG`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`user_type`) values (1,'admin','管理员','c4ca4238a0b923820dcc509a6f75849b',1,'default',NULL,'2016-11-02 15:03:18',NULL,1),(7,'abc','abc','',1,'default','admin','2016-11-17 00:37:43','2016-11-17 01:01:12',2),(8,'ab','ab','',0,'admin','admin','2016-11-17 00:42:38','2016-11-17 01:21:21',2),(9,'abcd','abcd','e2fc714c4727ee9395f324cd2e7f331f',1,'admin',NULL,'2016-11-17 01:11:04',NULL,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系表';

/*Data for the table `t_sys_user_role_rel` */

insert  into `t_sys_user_role_rel`(`ID_USER_ROLE_REL`,`ID_USER`,`ID_ROLE`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`IS_DELETE`) values (7,7,2,NULL,NULL,'2016-11-17 01:01:12',NULL,'N'),(8,9,2,NULL,NULL,'2016-11-17 01:11:04',NULL,'N'),(9,8,2,NULL,NULL,'2016-11-17 01:21:21',NULL,'N');

/*Table structure for table `t_validate_code` */

DROP TABLE IF EXISTS `t_validate_code`;

CREATE TABLE `t_validate_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `code` varchar(16) NOT NULL COMMENT '验证码',
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码表';

/*Data for the table `t_validate_code` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
