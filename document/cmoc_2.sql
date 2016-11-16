/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.31 : Database - cmoc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cmoc` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cmoc`;

/*Table structure for table `t_activity_family` */

DROP TABLE IF EXISTS `t_activity_family`;

CREATE TABLE `t_activity_family` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
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
  `activity_id` decimal(10,0) NOT NULL COMMENT '活动编号',
  `activity_name` varchar(128) NOT NULL COMMENT '活动名称',
  `marine_id` int(22) DEFAULT NULL COMMENT '战队编号',
  `marine_name` varchar(64) DEFAULT NULL COMMENT '战队名称',
  `hm_name` varchar(128) DEFAULT NULL COMMENT '透明人姓名',
  `hm_mobile` varchar(11) DEFAULT NULL COMMENT '透明人手机号码',
  `teacher_name` varchar(128) DEFAULT NULL COMMENT '老师姓名',
  `teacher_mobile` varchar(11) DEFAULT NULL COMMENT '老师手机号码',
  `create` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否有效 N有效 Y无效',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `idx_father_name` (`father_name`),
  FULLTEXT KEY `idx_father_mobile` (`father_mobile`),
  FULLTEXT KEY `idx_mother_name` (`mother_name`),
  FULLTEXT KEY `idx_mother_mobile` (`mother_mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动报名家庭信息';

/*Data for the table `t_activity_family` */

/*Table structure for table `t_activity_hm_sign` */

DROP TABLE IF EXISTS `t_activity_hm_sign`;

CREATE TABLE `t_activity_hm_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL COMMENT '活动id',
  `activity_name` varchar(128) NOT NULL COMMENT '活动名称',
  `hm_name` varchar(128) NOT NULL COMMENT '透明人姓名',
  `hm_mobile` varchar(11) NOT NULL COMMENT '透明人手机号码',
  `sign_date` varchar(8) DEFAULT NULL COMMENT '报名日期',
  `is_effect` int(2) DEFAULT '0' COMMENT '是否生效 0否 1是',
  `effect_date` varchar(8) DEFAULT NULL COMMENT '生效日期',
  `score` decimal(8,2) DEFAULT NULL COMMENT '评分',
  `judge` varchar(512) DEFAULT NULL COMMENT '评价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='透明人活动报名表';

/*Data for the table `t_activity_hm_sign` */

/*Table structure for table `t_activity_info` */

DROP TABLE IF EXISTS `t_activity_info`;

CREATE TABLE `t_activity_info` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(128) NOT NULL COMMENT '活动名称',
  `start_date` varchar(8) DEFAULT NULL COMMENT '活动开始日期',
  `end_date` varchar(8) DEFAULT NULL COMMENT '活动结束日期',
  `activity_addr` varchar(258) NOT NULL COMMENT '活动地址',
  `activity_num` varchar(128) DEFAULT NULL COMMENT '活动期数',
  `activity_desc` varchar(512) DEFAULT NULL COMMENT '活动描述',
  `activity_type` varchar(8) DEFAULT NULL COMMENT '活动类型',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `updater_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动信息';

/*Data for the table `t_activity_info` */

/*Table structure for table `t_activity_marines` */

DROP TABLE IF EXISTS `t_activity_marines`;

CREATE TABLE `t_activity_marines` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `marine_name` varchar(64) NOT NULL COMMENT '战队名称',
  `marine_img` varchar(128) DEFAULT NULL COMMENT '战队图片',
  `lines` varchar(64) DEFAULT NULL COMMENT '线路',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `activity_name` varchar(64) DEFAULT NULL COMMENT '活动名称',
  `votes` int(11) DEFAULT NULL COMMENT '支持票数',
  `reads` int(11) DEFAULT NULL COMMENT '阅读量',
  `score` decimal(8,2) DEFAULT NULL COMMENT '得分',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动战队信息';

/*Data for the table `t_activity_marines` */

/*Table structure for table `t_activity_marines_support` */

DROP TABLE IF EXISTS `t_activity_marines_support`;

CREATE TABLE `t_activity_marines_support` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(128) DEFAULT NULL,
  `nickName` varchar(128) DEFAULT NULL COMMENT '昵称',
  `img_url` varchar(128) DEFAULT NULL COMMENT '图片url',
  `margin_id` int(11) DEFAULT NULL COMMENT '战队id',
  `margin_name` varchar(64) DEFAULT NULL COMMENT '战队名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='战队支持记录';

/*Data for the table `t_activity_marines_support` */

/*Table structure for table `t_activity_resource` */

DROP TABLE IF EXISTS `t_activity_resource`;

CREATE TABLE `t_activity_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL COMMENT '资源路径',
  `marine_id` int(11) NOT NULL COMMENT '战队id',
  `marine_name` varchar(128) DEFAULT NULL COMMENT '战队名称',
  `hm_mobile` varchar(11) NOT NULL COMMENT '透明人手机号码',
  `hm_name` varchar(128) DEFAULT NULL COMMENT '透明人姓名',
  `resource_type` varchar(2) DEFAULT NULL COMMENT '资源类型 1图片 2视频 3语音',
  `is_delete` varchar(2) DEFAULT NULL COMMENT '是否有效 N有效 Y失效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动资源表';

/*Data for the table `t_activity_resource` */

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
  `discountPrice` decimal(22,2) DEFAULT NULL COMMENT '折扣价',
  `points` int(11) DEFAULT NULL COMMENT '积分兑换点',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_goods_detail` */

/*Table structure for table `t_goods_info` */

DROP TABLE IF EXISTS `t_goods_info`;

CREATE TABLE `t_goods_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_no` varchar(32) NOT NULL COMMENT '商品编号',
  `goods_desc` blob COMMENT '商品描述',
  `creater` varchar(128) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(128) DEFAULT NULL COMMENT '变更人',
  `updater_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '变更时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_goods_info` */

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
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '变更时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='透明人信息';

/*Data for the table `t_hollow_man_info` */

/*Table structure for table `t_parent_info` */

DROP TABLE IF EXISTS `t_parent_info`;

CREATE TABLE `t_parent_info` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `relation` varchar(4) DEFAULT NULL COMMENT '关系',
  `mobile` varchar(1) DEFAULT NULL COMMENT '手机号码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
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

insert  into `t_sys_resource`(`ID_RESOURCE`,`RESOURCE_NAME`,`RESOURCE_URL`,`RESOURCE_TYPE`,`RESOURCE_CODE`,`PARENT_RESOURCE_ID`,`WEIGHT`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`IS_DELETE`,`IS_QUICK_MENU`,`IS_SYSTEM_CONF_MENU`) values (10,'子帐户与权限管理',NULL,'M','authority_manage',0,10,'admin',NULL,'2015-09-21 16:20:53',NULL,'N','N',''),(20,'站内管理',NULL,'M','content_manage',0,20,'admin',NULL,'2015-09-17 11:08:23',NULL,'N','N',''),(30,'活动管理',NULL,'M','activity_manage',0,30,'admin',NULL,'2015-09-21 16:26:32',NULL,'N','N',''),(40,'课程管理',NULL,'M','course_manage',0,40,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(50,'家长管理',NULL,'M','family_manage',0,50,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(60,'透明人管理',NULL,'M','hollow_man_manage',0,60,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(70,'评论管理',NULL,'M','comment_manage',0,70,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(100,'财房管理',NULL,'M','money_manage',0,100,'admin',NULL,'2015-09-25 17:14:01',NULL,'N','N',''),(1010,'角色管理','/power/role','M','role_manage',10,10,'admin',NULL,'2016-11-13 11:39:07',NULL,'N',NULL,''),(1015,'用户管理','/power/user','M','user_manage',10,20,'admin',NULL,'2016-11-13 11:43:16',NULL,'N',NULL,''),(1020,'菜单管理','/power/menu','M','menu_manage',10,30,'admin',NULL,'2016-11-13 19:35:02',NULL,'N',NULL,''),(2010,'图组管理','/content/img/group','M','content_img_group',20,10,'admin',NULL,'2016-11-13 21:00:51',NULL,'N',NULL,''),(3010,'活动管理','/activity/manage','M','activity_manage',30,10,'admin',NULL,'2016-11-13 12:04:08',NULL,'N',NULL,''),(5010,'家长绑定查询','/parent/bind/query','M','parent_bind_query',50,10,'admin',NULL,'2016-11-13 19:37:24',NULL,'N',NULL,''),(5020,'家长购买记录','/parent/buy/record','M','parent_buy_record',50,20,'admin',NULL,'2016-11-13 19:38:18',NULL,'N',NULL,''),(6010,'透明人查询','/hm/query','M','hollow_man_query',60,10,'admin',NULL,'2016-11-13 19:39:47',NULL,'N',NULL,''),(6020,'透明人上传资源管理','/hm/resource/query','M','hollow_man_resource_query',60,20,'admin',NULL,'2016-11-13 19:40:39',NULL,'N',NULL,'');

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
  `user_type` int(11) DEFAULT '2' COMMENT '账户类型 1管理员 2一般账户',
  PRIMARY KEY (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`ID_USER`,`USER_ACCOUNT`,`USER_NAME`,`PASSWORD`,`VALID_FLAG`,`CREATOR`,`LAST_UPDATOR`,`CREATE_TIME`,`LAST_UPDATE_TIME`,`user_type`) values ('1','admin','管理员','c4ca4238a0b923820dcc509a6f75849b',1,'default',NULL,'2016-11-02 15:03:18',NULL,1);

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
