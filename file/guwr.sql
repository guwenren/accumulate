/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.23-72.1-log : Database - guwr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`guwr` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `guwr`;

/*Table structure for table `tbl_account_balance` */

DROP TABLE IF EXISTS `tbl_account_balance`;

CREATE TABLE `tbl_account_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(10) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL,
  `balance` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_account_balance_record` */

DROP TABLE IF EXISTS `tbl_account_balance_record`;

CREATE TABLE `tbl_account_balance_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` int(10) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL COMMENT '会员ID',
  `type` int(11) NOT NULL COMMENT '收入、支出类型： 1、线上充值  2、线下充值， 3、还款',
  `amount` decimal(15,2) DEFAULT '0.00' COMMENT '金额',
  `balance` decimal(15,2) DEFAULT '0.00' COMMENT '账户余额',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员资金账户变化表';

/*Table structure for table `tbl_authority_permission` */

DROP TABLE IF EXISTS `tbl_authority_permission`;

CREATE TABLE `tbl_authority_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_authority_role` */

DROP TABLE IF EXISTS `tbl_authority_role`;

CREATE TABLE `tbl_authority_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_authority_role_permission` */

DROP TABLE IF EXISTS `tbl_authority_role_permission`;

CREATE TABLE `tbl_authority_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_authority_user` */

DROP TABLE IF EXISTS `tbl_authority_user`;

CREATE TABLE `tbl_authority_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '盐',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_authority_user_role` */

DROP TABLE IF EXISTS `tbl_authority_user_role`;

CREATE TABLE `tbl_authority_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_notify_message` */

DROP TABLE IF EXISTS `tbl_notify_message`;

CREATE TABLE `tbl_notify_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL,
  `title` varchar(256) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='通知任务表';

/*Table structure for table `tbl_notify_record` */

DROP TABLE IF EXISTS `tbl_notify_record`;

CREATE TABLE `tbl_notify_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `uuid` varchar(33) NOT NULL COMMENT 'uuid',
  `notify_times` int(11) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `limit_notify_times` int(11) NOT NULL COMMENT '消息最大次数',
  `url` varchar(50) NOT NULL COMMENT '通知URL',
  `merchant_no` varchar(50) NOT NULL COMMENT '商户编号',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `notify_type` int(11) NOT NULL COMMENT '通知类型 NotifyTypeEnum',
  `status` int(11) NOT NULL COMMENT '通知状态(100:成功:101:未成功,默认101)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_notify_record_log` */

DROP TABLE IF EXISTS `tbl_notify_record_log`;

CREATE TABLE `tbl_notify_record_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `version` int(10) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `notify_id` int(11) NOT NULL COMMENT '通知记录ID',
  `request` varchar(50) NOT NULL DEFAULT '' COMMENT '请求信息',
  `response` varchar(50) NOT NULL DEFAULT '' COMMENT '返回信息',
  `merchant_no` varchar(50) NOT NULL COMMENT '商户编号',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `http_status` int(11) NOT NULL COMMENT 'HTTP状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_notify_transaction_message` */

DROP TABLE IF EXISTS `tbl_notify_transaction_message`;

CREATE TABLE `tbl_notify_transaction_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `uuid` varchar(33) NOT NULL COMMENT 'uuid',
  `message_body` varchar(500) NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(50) NOT NULL DEFAULT '' COMMENT '消费队列',
  `message_send_times` int(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `areadly_dead` int(11) NOT NULL DEFAULT '0' COMMENT '是否死亡',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_user_userinfo` */

DROP TABLE IF EXISTS `tbl_user_userinfo`;

CREATE TABLE `tbl_user_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(10) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `realname` varchar(20) NOT NULL COMMENT '姓名',
  `mobile` varchar(30) NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiyi_mobile` (`mobile`),
  UNIQUE KEY `weiyi_realname` (`realname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Table structure for table `tbl_user_wmps_earnings` */

DROP TABLE IF EXISTS `tbl_user_wmps_earnings`;

CREATE TABLE `tbl_user_wmps_earnings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `pid` int(11) DEFAULT NULL COMMENT '理财产品ID',
  `invest_amount` decimal(10,2) DEFAULT NULL COMMENT '购买金额',
  `proearn` decimal(10,2) DEFAULT NULL COMMENT '预期收益',
  `realearn` decimal(10,2) DEFAULT NULL COMMENT '实际收益',
  `lid` int(11) DEFAULT NULL COMMENT 'VIP利率ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户投资收益表';

/*Table structure for table `tbl_user_wmps_invest` */

DROP TABLE IF EXISTS `tbl_user_wmps_invest`;

CREATE TABLE `tbl_user_wmps_invest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `total_invest` decimal(20,2) NOT NULL COMMENT '投资总金额',
  `user_type` int(11) NOT NULL DEFAULT '0' COMMENT '用户类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品投资总额';

/*Table structure for table `tbl_user_wmps_invest_log` */

DROP TABLE IF EXISTS `tbl_user_wmps_invest_log`;

CREATE TABLE `tbl_user_wmps_invest_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `pid` int(11) NOT NULL COMMENT '产品投资总额ID',
  `invest_money` decimal(20,2) NOT NULL COMMENT '变更金额',
  `invest_type` int(11) NOT NULL DEFAULT '1' COMMENT '变更方向: 1:增加，2:减少 （默认1）',
  `befor_total_invest` decimal(20,2) NOT NULL DEFAULT '1.00' COMMENT '用户变更前在投资金额',
  `after_total_invest` decimal(20,2) NOT NULL DEFAULT '1.00' COMMENT '用户变更后在投资金额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品投资总额日志';

/*Table structure for table `tbl_user_wmps_level` */

DROP TABLE IF EXISTS `tbl_user_wmps_level`;

CREATE TABLE `tbl_user_wmps_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(10) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '等级',
  `min_invest` decimal(20,0) NOT NULL COMMENT '最小值',
  `max_invest` decimal(20,0) NOT NULL COMMENT '最大值',
  `interestrate` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '利率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='产品投资总额级别';

/*Table structure for table `tbl_wmps_product` */

DROP TABLE IF EXISTS `tbl_wmps_product`;

CREATE TABLE `tbl_wmps_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(10) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `amount` decimal(10,2) NOT NULL COMMENT '募集金额',
  `invest_amount` decimal(10,2) NOT NULL COMMENT '投资金额',
  `effect_amount` decimal(10,2) NOT NULL COMMENT '有效金额',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态:0尚未发布 1.已发布，认购中  2认购完成，审核中  3计息中 4到期完成',
  `phases` int(11) NOT NULL DEFAULT '0' COMMENT '期限',
  `interestrate` decimal(5,4) NOT NULL COMMENT '发行年利率',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计息开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计息结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='产品';

/*Table structure for table `tbl_wmps_product_record` */

DROP TABLE IF EXISTS `tbl_wmps_product_record`;

CREATE TABLE `tbl_wmps_product_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `pid` int(11) DEFAULT NULL COMMENT '理财产品ID',
  `invest_amount` decimal(10,2) DEFAULT NULL COMMENT '购买金额',
  `effect_amount` decimal(10,2) DEFAULT NULL COMMENT '有效金额',
  `proearn` decimal(10,2) DEFAULT NULL COMMENT '预期收益',
  `vip_interestrate` decimal(5,4) DEFAULT NULL COMMENT 'VIP利率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
