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

/*Table structure for table `sum_user` */

DROP TABLE IF EXISTS `sum_user`;

CREATE TABLE `sum_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `cdate` int(11) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sum_user` */

insert  into `sum_user`(`id`,`uid`,`cdate`) values (1,1,1488506119),(2,1,1488506111),(3,1,1488506112),(4,1,1488506113),(5,1,1488506114),(6,1,1488506115),(7,1,1488506116),(8,1,1488506117),(9,1,1488506118),(16,2,1488506116),(17,2,1488506124),(18,2,1488506137),(19,2,1488506143),(20,2,1488506156),(21,2,1488506163),(22,2,1488506171),(23,2,1488506182),(24,2,1488506196),(31,3,1488506115),(32,3,1488506124),(33,3,1488506134),(34,3,1488506146),(35,3,1488506109),(36,3,1488506110),(37,3,1488506116),(38,3,1488506126),(39,3,1488506128);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_account_balance` */

insert  into `tbl_account_balance`(`id`,`version`,`create_time`,`update_time`,`uuid`,`uid`,`balance`) values (1,0,'2017-01-07 17:19:56','2017-01-07 17:19:56','4a935364256d4f368d97cdd38a55c407',2,'0.00'),(2,0,'2017-01-07 17:20:45','2017-01-07 17:20:45','a2145071dea1416cb405f1a9afc2d710',3,'0.00'),(3,0,'2017-01-07 17:25:43','2017-01-07 17:25:43','dcda9969b39045a3907e447a04acfa06',4,'0.00'),(4,0,'2017-01-07 17:25:44','2017-01-07 17:25:44','cf655121a7d14a75baded4032ddbc38b',5,'0.00'),(5,0,'2017-01-07 17:26:53','2017-01-07 17:26:53','7e185f78b4c042d194de5808bc0dc087',1,'0.00');

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

/*Data for the table `tbl_account_balance_record` */

/*Table structure for table `tbl_authority_permission` */

DROP TABLE IF EXISTS `tbl_authority_permission`;

CREATE TABLE `tbl_authority_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_authority_permission` */

insert  into `tbl_authority_permission`(`id`,`url`,`name`) values (4,'/permission/index.shtml','权限列表'),(6,'/permission/addPermission.shtml','权限添加'),(7,'/permission/deletePermissionById.shtml','权限删除'),(8,'/member/list.shtml','用户列表'),(9,'/member/online.shtml','在线用户'),(10,'/member/changeSessionStatus.shtml','用户Session踢出'),(11,'/member/forbidUserById.shtml','用户激活&禁止'),(12,'/member/deleteUserById.shtml','用户删除'),(13,'/permission/addPermission2Role.shtml','权限分配'),(14,'/role/clearRoleByUserIds.shtml','用户角色分配清空'),(15,'/role/addRole2User.shtml','角色分配保存'),(16,'/role/deleteRoleById.shtml','角色列表删除'),(17,'/role/addRole.shtml','角色列表添加'),(18,'/role/index.shtml','角色列表'),(19,'/permission/allocation.shtml','权限分配'),(20,'/role/allocation.shtml','角色分配');

/*Table structure for table `tbl_authority_role` */

DROP TABLE IF EXISTS `tbl_authority_role`;

CREATE TABLE `tbl_authority_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_authority_role` */

insert  into `tbl_authority_role`(`id`,`name`,`type`) values (1,'系统管理员','888888'),(3,'权限角色','100003'),(4,'用户中心','100002');

/*Table structure for table `tbl_authority_role_permission` */

DROP TABLE IF EXISTS `tbl_authority_role_permission`;

CREATE TABLE `tbl_authority_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_authority_role_permission` */

insert  into `tbl_authority_role_permission`(`id`,`rid`,`pid`) values (33,4,8),(34,4,9),(35,4,10),(36,4,11),(37,4,12),(38,3,4),(39,3,6),(40,3,7),(41,3,13),(42,3,14),(43,3,15),(44,3,16),(45,3,17),(46,3,18),(47,3,19),(48,3,20),(49,1,4),(50,1,6),(51,1,7),(52,1,8),(53,1,9),(54,1,10),(55,1,11),(56,1,12),(57,1,13),(58,1,14),(59,1,15),(60,1,16),(61,1,17),(62,1,18),(63,1,19),(64,1,20);

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

/*Data for the table `tbl_authority_user` */

insert  into `tbl_authority_user`(`id`,`username`,`password`,`salt`) values (1,'admin','admin','1473317226469'),(11,'soso','8446666@qq.com',''),(12,'8446666','8446666',''),(15,'zhangsan','79a38875a5ec72bc5709907a202ce960','a7050029d928749ebc8bc647d2bccda6'),(16,'liurong','2b8823528f621fd3f4543945cbb492cd','feea0799727c2ff770b0163839222e9a'),(17,'wangwu','b9deb3cf6767eaead77c21614d1d9ab4','567228811e2a65215ea4df63979fa1b9'),(18,'zhaoliu','775f3823b08cdf00fb4fa6ca274b5dd7','5518749ed90ceb030a151e06c1d7ea45'),(19,'zhaoliu1','9fe0f72d6bc0519aef187d506c311f40','804298de00d3a4e72fc8c4f022e56a1f');

/*Table structure for table `tbl_authority_user_role` */

DROP TABLE IF EXISTS `tbl_authority_user_role`;

CREATE TABLE `tbl_authority_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_authority_user_role` */

insert  into `tbl_authority_user_role`(`id`,`uid`,`rid`) values (5,12,4),(6,11,3),(7,11,4),(8,1,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='通知任务表';

/*Data for the table `tbl_notify_message` */

insert  into `tbl_notify_message`(`id`,`version`,`create_time`,`update_time`,`uuid`,`uid`,`title`,`content`) values (1,0,'2017-01-07 17:02:56','2017-01-07 17:02:56','7826f21319794b1781eb686d59c37e7e',2,'2_注册成功Title','2_注册成功Content'),(2,0,'2017-01-07 17:02:56','2017-01-07 17:02:56','7e185f78b4c042d194de5808bc0dc087',1,'1_注册成功Title','1_注册成功Content'),(3,0,'2017-01-07 17:20:00','2017-01-07 17:20:00','4a935364256d4f368d97cdd38a55c407',2,'2_注册成功Title','2_注册成功Content'),(4,0,'2017-01-07 17:20:52','2017-01-07 17:20:52','a2145071dea1416cb405f1a9afc2d710',3,'3_注册成功Title','3_注册成功Content'),(5,0,'2017-01-07 17:25:47','2017-01-07 17:25:47','dcda9969b39045a3907e447a04acfa06',4,'4_注册成功Title','4_注册成功Content'),(6,0,'2017-01-07 17:25:48','2017-01-07 17:25:48','cf655121a7d14a75baded4032ddbc38b',5,'5_注册成功Title','5_注册成功Content');

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

/*Data for the table `tbl_notify_record` */

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

/*Data for the table `tbl_notify_record_log` */

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

/*Data for the table `tbl_notify_transaction_message` */

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `cdate` int(11) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`uid`,`cdate`) values (1,1,1488506119),(2,1,1488506111),(3,1,1488506112),(4,1,1488506113),(5,1,1488506114),(6,1,1488506115),(7,1,1488506116),(8,1,1488506117),(9,1,1488506118);

/*Table structure for table `tbl_user_2` */

DROP TABLE IF EXISTS `tbl_user_2`;

CREATE TABLE `tbl_user_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `cdate` int(11) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tbl_user_2` */

insert  into `tbl_user_2`(`id`,`uid`,`cdate`) values (1,2,1488506116),(2,2,1488506124),(3,2,1488506137),(4,2,1488506143),(5,2,1488506156),(6,2,1488506163),(7,2,1488506171),(8,2,1488506182),(9,2,1488506196);

/*Table structure for table `tbl_user_3` */

DROP TABLE IF EXISTS `tbl_user_3`;

CREATE TABLE `tbl_user_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `cdate` int(11) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tbl_user_3` */

insert  into `tbl_user_3`(`id`,`uid`,`cdate`) values (1,3,1488506115),(2,3,1488506124),(3,3,1488506134),(4,3,1488506146),(5,3,1488506109),(6,3,1488506110),(7,3,1488506116),(8,3,1488506126),(9,3,1488506128);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `tbl_user_userinfo` */

insert  into `tbl_user_userinfo`(`id`,`version`,`create_time`,`update_time`,`uuid`,`password`,`realname`,`mobile`) values (1,0,'2017-01-07 16:58:25','2017-01-07 16:58:25','7e185f78b4c042d194de5808bc0dc087','R3IGER','李四毛','13448449026'),(2,0,'2017-01-07 17:19:56','2017-01-07 17:19:56','4a935364256d4f368d97cdd38a55c407','Sfubnu','李四毛','13840894055'),(3,0,'2017-01-07 17:20:39','2017-01-07 17:20:39','a2145071dea1416cb405f1a9afc2d710','C5RsQP','李四毛','13121887275'),(4,0,'2017-01-07 17:23:06','2017-01-07 17:23:06','dcda9969b39045a3907e447a04acfa06','OgQB7O','李四毛','13541565920'),(5,0,'2017-01-07 17:24:35','2017-01-07 17:24:35','cf655121a7d14a75baded4032ddbc38b','Av7d3i','李四毛','13562017378');

/*Table structure for table `tbl_user_wmps_day_inter` */

DROP TABLE IF EXISTS `tbl_user_wmps_day_inter`;

CREATE TABLE `tbl_user_wmps_day_inter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `inter` decimal(10,2) NOT NULL COMMENT '利息',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT ' 状态：0未发 1已发',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日付息表';

/*Data for the table `tbl_user_wmps_day_inter` */

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
  `interestrate` decimal(5,4) DEFAULT NULL COMMENT 'VIP利率',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiyi_key` (`uid`,`pid`,`interestrate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户投资收益表';

/*Data for the table `tbl_user_wmps_earnings` */

/*Table structure for table `tbl_user_wmps_funds_info` */

DROP TABLE IF EXISTS `tbl_user_wmps_funds_info`;

CREATE TABLE `tbl_user_wmps_funds_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `uuid` varchar(33) NOT NULL DEFAULT '' COMMENT 'uuid',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `inter` decimal(10,2) NOT NULL COMMENT '利息',
  `bid` int(11) DEFAULT NULL COMMENT '购买记录ID',
  `pid` int(11) DEFAULT NULL COMMENT '产品ID',
  `sid` int(11) DEFAULT NULL COMMENT '发息总表ID',
  `interestrate` decimal(5,4) DEFAULT NULL COMMENT '利率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日发息资金流水';

/*Data for the table `tbl_user_wmps_funds_info` */

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

/*Data for the table `tbl_user_wmps_invest` */

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

/*Data for the table `tbl_user_wmps_invest_log` */

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品投资总额级别';

/*Data for the table `tbl_user_wmps_level` */

insert  into `tbl_user_wmps_level`(`id`,`version`,`create_time`,`update_time`,`uuid`,`level`,`min_invest`,`max_invest`,`interestrate`) values (1,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','8f83c0ead2524af9a670500291956f0c',0,'0','100000','0.0000'),(2,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','063f4f291df94e7594f987a914e06d3d',1,'100000','200000','0.0010'),(3,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','fc22473146074c3c8e5d09aeac0e02d5',2,'200000','300000','0.0020'),(4,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','480b8cc509d342f98f4e2b541c67e1e5',3,'300000','400000','0.0030'),(5,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','8040e2da2d10473db27f24d51693d6db',4,'400000','500000','0.0040'),(6,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','a82ccf4993c64005b47efe4481d237bd',5,'500000','600000','0.0050'),(7,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','194d5328795749ee8fdd7f1f9bf0c9a7',6,'600000','700000','0.0060'),(8,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','332b8c7449ff447cbff74c47636816d9',7,'700000','800000','0.0070'),(9,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','74a50d82abda4343b083127394877202',8,'800000','900000','0.0080'),(10,0,'2016-12-28 11:35:56','2016-12-28 11:35:56','842d56d247094469b5281f1301aa0222',9,'900000','1000000','0.0090');

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
  `publisheddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '发布时间',
  `finisheddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成时间',
  `interdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计息时间',
  `enddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品';

/*Data for the table `tbl_wmps_product` */

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
  `interestrate` decimal(5,4) DEFAULT NULL COMMENT 'VIP利率',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_wmps_product_record` */

/* Procedure structure for procedure `myproc` */

/*!50003 DROP PROCEDURE IF EXISTS  `myproc` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`developer`@`%` PROCEDURE `myproc`()
BEGIN
DECLARE num INT;
SET num=1;
WHILE num < 10 DO
INSERT INTO tbl_user(uid, cdate) VALUES(1, UNIX_TIMESTAMP(NOW()) + FLOOR(1 + (RAND() * 10)));
INSERT INTO tbl_user_2(uid, cdate) VALUES(2,UNIX_TIMESTAMP(NOW()) + FLOOR(1 + (RAND() * 10)));
INSERT INTO tbl_user_3(uid, cdate) VALUES(3, UNIX_TIMESTAMP(NOW()) + FLOOR(1 + (RAND() * 10)));
SET num=num+1;
END WHILE;
 END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
