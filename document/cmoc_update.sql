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

--2017.4.9修改 start
ALTER TABLE t_product_order ADD COLUMN trade_type VARCHAR(16) COMMENT'交易类型 JSAPI 公众号支付 NATIVE 原生扫码支付号支付 APP app支付 MICROPAY 刷卡支付' AFTER channel;
ALTER TABLE t_product_order ADD COLUMN trade_state VARCHAR(32) COMMENT'交易状态 SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付）USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)' AFTER trade_type;
ALTER TABLE t_product_order ADD COLUMN trade_state_desc VARCHAR(256) COMMENT'交易状态描述' AFTER trade_state;

CREATE TABLE `t_product_refund_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `out_refund_no` varchar(32) NOT NULL COMMENT '商户退款订单号',
  `refund_no` varchar(32) DEFAULT NULL COMMENT '第三方退款单号',
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '商户支付单号',
  `trans_no` varchar(32) DEFAULT NULL COMMENT '第三方支付单号',
  `total_fee` decimal(22,2) DEFAULT NULL COMMENT '订单金额',
  `refund_fee` decimal(22,2) DEFAULT NULL COMMENT '退款金额',
  `order_status` varchar(8) DEFAULT NULL COMMENT '000已退款 001退款申请中 002退款失败',
  `return_code` varchar(16) DEFAULT NULL COMMENT '返回状态码 SUCCESS/FAIL',
  `return_msg` varchar(128) DEFAULT NULL COMMENT '返回信息',
  `err_code` varchar(32) DEFAULT NULL COMMENT '错误码',
  `error_reason` varchar(32) DEFAULT NULL COMMENT '错误描述',
  `refund_submit_time` timestamp NULL DEFAULT NULL COMMENT '退款提交时间',
  `refund_callback_time` timestamp NULL DEFAULT NULL COMMENT '退款回调时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '变更时间',
  `create_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_out_refund_no` (`out_refund_no`),
  KEY `idx_out_trade_no` (`out_trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款订单表';
