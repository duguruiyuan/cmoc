insert into `t_sys_resource` (`ID_RESOURCE`, `RESOURCE_NAME`, `RESOURCE_URL`, `RESOURCE_TYPE`, `RESOURCE_CODE`, `PARENT_RESOURCE_ID`, `WEIGHT`, `CREATOR`, `LAST_UPDATOR`, `LAST_UPDATE_TIME`, `IS_DELETE`, `IS_QUICK_MENU`, `IS_SYSTEM_CONF_MENU`) 
values('2030','关键词管理','/content/keyword','M','content_keyword','20','30','admin',NULL,NULL,'N',NULL,NULL);

--3月4号上线修改 start
ALTER TABLE t_course_info ADD COLUMN sign_way INT(4) DEFAULT 0 COMMENT '报名方式 0单人 1组队';
ALTER TABLE `t_product_order` ADD COLUMN activity_id INT(22) COMMENT'活动id' AFTER product_id;
ALTER TABLE `t_parent_info` MODIFY COLUMN head_img VARCHAR(256) COMMENT'头像';
ALTER TABLE t_child_sign_info ADD COLUMN activity_id INT(11) COMMENT '活动编号' AFTER product_id;
ALTER TABLE `t_child_sign_info` ADD COLUMN child_weight INT(4) COMMENT'小孩体重' AFTER disease_desc;
ALTER TABLE `t_child_sign_info` ADD COLUMN child_height INT(4) COMMENT'小孩身高' AFTER disease_desc;
ALTER TABLE t_product_order ADD COLUMN poster_img VARCHAR(128) COMMENT'邀请海报';
INSERT INTO `t_sys_resource`(ID_RESOURCE, RESOURCE_NAME, RESOURCE_URL, RESOURCE_TYPE, RESOURCE_CODE, PARENT_RESOURCE_ID,WEIGHT,creator)
VALUES(4030,'课程报名订单','/course/sign/order','M','course_sign_order',40,30,'admin');
ALTER TABLE t_parent_info ADD COLUMN nick_name VARCHAR(128) COMMENT'微信昵称' AFTER wechat_num;
ALTER TABLE t_product_order ADD COLUMN sign_phone VARCHAR(11) COMMENT'报名电话' AFTER cust_id;
ALTER TABLE t_product_order ADD COLUMN sign_name VARCHAR(128) COMMENT'报名人姓名' AFTER cust_id;
ALTER TABLE t_product_order ADD COLUMN openid VARCHAR(64) COMMENT'用户的标识，对当前公众号唯一' AFTER cust_id;
ALTER TABLE `t_activity_info` ADD COLUMN is_full VARCHAR(2) NULL DEFAULT 'N' COMMENT'是否满员 Y是 N否';
ALTER TABLE `t_child_sign_info` ADD COLUMN is_delete VARCHAR(2) DEFAULT'N' COMMENT'是否有效 N有效 Y失效';
--3月4号上线修改 end
