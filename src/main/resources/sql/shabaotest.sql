/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 50616
Source Host           : 114.215.120.117:3306
Source Database       : shabaotest

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2017-05-04 15:31:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for td_f_seckill
-- ----------------------------
DROP TABLE IF EXISTS `td_f_seckill`;
CREATE TABLE `td_f_seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名字',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_tmie` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Table structure for td_f_seckill_success
-- ----------------------------
DROP TABLE IF EXISTS `td_f_seckill_success`;
CREATE TABLE `td_f_seckill_success` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品id',
  `user_phone` bigint(11) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态表示：-1无效,0成功,1已付款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Table structure for td_m_depart
-- ----------------------------
DROP TABLE IF EXISTS `td_m_depart`;
CREATE TABLE `td_m_depart` (
  `depart_id` varchar(5) NOT NULL,
  `depart_name` varchar(50) NOT NULL,
  `depart_type` varchar(3) NOT NULL,
  `depart_frame` varchar(50) NOT NULL,
  `parent_depart_id` varchar(5) NOT NULL,
  `depart_level` int(2) NOT NULL,
  `childNum` int(3) DEFAULT NULL,
  `used` int(1) NOT NULL,
  `order_no` int(3) DEFAULT NULL,
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_m_menu
-- ----------------------------
DROP TABLE IF EXISTS `td_m_menu`;
CREATE TABLE `td_m_menu` (
  `menu_id` varchar(6) NOT NULL,
  `group_id` int(3) DEFAULT NULL COMMENT '分组，100admin',
  `menu_title` varchar(255) DEFAULT NULL COMMENT '显示名',
  `ismenu` int(1) DEFAULT NULL COMMENT '是否是菜单按钮',
  `ioc` varchar(20) DEFAULT NULL COMMENT '图标样式',
  `menu_des` varchar(255) DEFAULT NULL COMMENT '描述',
  `parent_menu_id` varchar(6) DEFAULT NULL COMMENT '父级标题id',
  `foruse` int(1) DEFAULT NULL COMMENT '是否使用：1、使用，0、不使用',
  `level` int(1) DEFAULT NULL COMMENT '标题等级',
  `permission_id` int(6) DEFAULT NULL COMMENT '对应权限id表示',
  `url` varchar(255) DEFAULT NULL,
  `order_no` int(2) DEFAULT NULL COMMENT '菜单当前等级排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_m_permission
-- ----------------------------
DROP TABLE IF EXISTS `td_m_permission`;
CREATE TABLE `td_m_permission` (
  `PERMISSION_ID` int(6) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `NAME` varchar(40) DEFAULT NULL COMMENT '权限名字',
  `PERMISSION_CODE` varchar(20) DEFAULT NULL COMMENT '权限编码',
  `PID` int(11) DEFAULT NULL COMMENT '父级id',
  `DES` varchar(100) DEFAULT NULL,
  `TYPE` int(1) DEFAULT NULL COMMENT '0上级表示，1菜单权限，2功能权限',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_m_role
-- ----------------------------
DROP TABLE IF EXISTS `td_m_role`;
CREATE TABLE `td_m_role` (
  `ROLE_ID` int(4) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(40) DEFAULT NULL COMMENT '角色名字',
  `DES` varchar(100) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  KEY `ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_m_user
-- ----------------------------
DROP TABLE IF EXISTS `td_m_user`;
CREATE TABLE `td_m_user` (
  `user_id` int(8) NOT NULL,
  `depart_id` varchar(5) NOT NULL,
  `login_name` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  `password_salt` varchar(32) NOT NULL,
  `login_state` int(1) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_m_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `td_m_userinfo`;
CREATE TABLE `td_m_userinfo` (
  `user_id` int(8) NOT NULL,
  `sex` int(1) DEFAULT NULL COMMENT '1男-2女',
  `age` int(3) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `qq` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_m_user_depart
-- ----------------------------
DROP TABLE IF EXISTS `td_m_user_depart`;
CREATE TABLE `td_m_user_depart` (
  `user_id` int(8) NOT NULL,
  `depart_id` varchar(5) NOT NULL,
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '1-使用中，0-失效,2-部门调换',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门与人员关系，防止调换部门';

-- ----------------------------
-- Table structure for td_sm_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `td_sm_role_permission`;
CREATE TABLE `td_sm_role_permission` (
  `NUM` int(8) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(4) NOT NULL,
  `PERMISSION_ID` int(6) NOT NULL,
  PRIMARY KEY (`NUM`),
  KEY `PK1_ROLE_RIGHT` (`ROLE_ID`),
  KEY `PK2_ROLE_RIGHT` (`PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_sm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `td_sm_user_role`;
CREATE TABLE `td_sm_user_role` (
  `num` int(8) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(8) NOT NULL,
  `ROLE_ID` int(4) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_s_file
-- ----------------------------
DROP TABLE IF EXISTS `td_s_file`;
CREATE TABLE `td_s_file` (
  `file_id` int(8) NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `url` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_s_poster
-- ----------------------------
DROP TABLE IF EXISTS `td_s_poster`;
CREATE TABLE `td_s_poster` (
  `poster_id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT '对应海报类型 比如投票',
  `type_id` varchar(10) NOT NULL,
  `image` varchar(100) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `button` varchar(20) DEFAULT NULL,
  `orderNo` int(3) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`poster_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000015 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_s_seqstring
-- ----------------------------
DROP TABLE IF EXISTS `td_s_seqstring`;
CREATE TABLE `td_s_seqstring` (
  `id` int(6) NOT NULL,
  `elements` text,
  `length` int(3) NOT NULL,
  `index` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_s_static
-- ----------------------------
DROP TABLE IF EXISTS `td_s_static`;
CREATE TABLE `td_s_static` (
  `type_id` varchar(50) NOT NULL,
  `data_id` varchar(20) NOT NULL,
  `data_name` varchar(50) NOT NULL,
  `used` int(1) NOT NULL COMMENT '是否有效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_uis_demo
-- ----------------------------
DROP TABLE IF EXISTS `td_uis_demo`;
CREATE TABLE `td_uis_demo` (
  `id` int(5) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `depart_id` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_bill
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_bill`;
CREATE TABLE `td_ui_bill` (
  `bill_id` varchar(8) NOT NULL,
  `bill_name` varchar(50) NOT NULL,
  `bill_class` varchar(10) NOT NULL COMMENT '分类',
  `order_no` int(8) NOT NULL,
  `state` int(1) NOT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_button
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_button`;
CREATE TABLE `td_ui_button` (
  `button_id` varchar(10) NOT NULL,
  `button_name` varchar(50) NOT NULL,
  `button_value` varchar(20) NOT NULL,
  `button_type` int(11) NOT NULL,
  `image_id` varchar(5) NOT NULL,
  `order_no` int(6) DEFAULT NULL,
  `is_used` int(11) NOT NULL,
  PRIMARY KEY (`button_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_button_image
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_button_image`;
CREATE TABLE `td_ui_button_image` (
  `image_id` varchar(5) NOT NULL,
  `image_name` varchar(20) NOT NULL,
  `image_desc` varchar(30) DEFAULT NULL,
  `file_name` varchar(30) NOT NULL COMMENT '文件名',
  `is_used` int(11) NOT NULL,
  `order_no` int(4) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_element
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_element`;
CREATE TABLE `td_ui_element` (
  `element_id` varchar(10) NOT NULL COMMENT '元素id',
  `element_type` varchar(10) NOT NULL COMMENT '类型 tree，select等',
  `element_name` varchar(30) NOT NULL COMMENT '元素名字',
  `element_desc` varchar(100) DEFAULT NULL COMMENT '描述',
  `params` text COMMENT '参数',
  `version` int(2) DEFAULT '1' COMMENT '版本',
  `order_no` int(4) DEFAULT NULL,
  `view_template` text COMMENT '显示模版',
  `read_template` text COMMENT '只读模版',
  PRIMARY KEY (`element_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_engine_type
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_engine_type`;
CREATE TABLE `td_ui_engine_type` (
  `group_id` varchar(10) NOT NULL,
  `type_id` varchar(20) NOT NULL,
  `type_name` varchar(50) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `order_no` int(2) NOT NULL,
  PRIMARY KEY (`type_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_list
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_list`;
CREATE TABLE `td_ui_list` (
  `list_id` varchar(8) NOT NULL,
  `list_name` varchar(30) NOT NULL,
  `bill_id` varchar(8) NOT NULL,
  `list_engine` varchar(20) NOT NULL,
  `table_id` varchar(10) NOT NULL,
  `template_id` varchar(10) NOT NULL,
  `report_id` varchar(10) NOT NULL,
  `is_query` int(1) NOT NULL DEFAULT '0' COMMENT '是否直接查询数据',
  `is_visible` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `data_where` varchar(255) DEFAULT NULL COMMENT '取数条件',
  `order_no` int(4) NOT NULL,
  PRIMARY KEY (`list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_list_button
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_list_button`;
CREATE TABLE `td_ui_list_button` (
  `list_id` varchar(10) NOT NULL,
  `button_id` varchar(10) NOT NULL,
  `order_no` int(4) DEFAULT NULL,
  `is_used` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_report
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_report`;
CREATE TABLE `td_ui_report` (
  `report_id` varchar(10) CHARACTER SET latin1 NOT NULL,
  `report_name` varchar(30) NOT NULL,
  `is_sum_row` int(1) DEFAULT NULL,
  `row_num` int(4) DEFAULT NULL,
  `table_id` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_report_column
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_report_column`;
CREATE TABLE `td_ui_report_column` (
  `report_id` varchar(10) NOT NULL,
  `column_id` varchar(10) NOT NULL,
  `column_name` varchar(255) NOT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for td_ui_static
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_static`;
CREATE TABLE `td_ui_static` (
  `type_id` varchar(50) NOT NULL,
  `data_id` varchar(20) NOT NULL,
  `data_name` varchar(50) NOT NULL,
  `is_used` int(1) NOT NULL COMMENT '是否有效',
  `order_no` int(4) NOT NULL,
  PRIMARY KEY (`type_id`,`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_table
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_table`;
CREATE TABLE `td_ui_table` (
  `table_id` varchar(10) NOT NULL COMMENT '数据表id',
  `display_name` varchar(30) NOT NULL,
  `table_name` varchar(20) NOT NULL COMMENT '数据表名',
  `table_type` varchar(1) NOT NULL COMMENT '数据表类型',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_table_column
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_table_column`;
CREATE TABLE `td_ui_table_column` (
  `column_id` varchar(10) NOT NULL,
  `table_id` varchar(10) NOT NULL COMMENT '数据表id',
  `field_code` varchar(30) NOT NULL COMMENT '数据字段',
  `field_name` varchar(20) NOT NULL COMMENT '数据表名',
  `field_type` int(2) NOT NULL COMMENT '数据类型',
  `field_attr` int(2) DEFAULT NULL COMMENT '属性，1-key，2-value',
  `field_length` int(3) NOT NULL COMMENT '数据长度',
  `field_decimal` int(2) DEFAULT NULL COMMENT '小数为长度',
  `is_key` int(1) NOT NULL DEFAULT '0' COMMENT '是否主键',
  `is_null` int(1) NOT NULL DEFAULT '0' COMMENT '是否非空',
  `is_ref` int(1) NOT NULL DEFAULT '0' COMMENT '是否有关联表',
  `ref_table` varchar(20) DEFAULT NULL COMMENT '关联表明',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_ui_template
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_template`;
CREATE TABLE `td_ui_template` (
  `template_id` varchar(10) NOT NULL COMMENT '模版id',
  `template_name` varchar(30) NOT NULL COMMENT '模版名字',
  `table_id` varchar(10) DEFAULT NULL,
  `is_visible` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `col_count` varchar(255) DEFAULT NULL COMMENT '分栏数',
  `remark` varchar(40) DEFAULT NULL COMMENT '模版描述',
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模版表';

-- ----------------------------
-- Table structure for td_ui_template_element
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_template_element`;
CREATE TABLE `td_ui_template_element` (
  `template_id` varchar(10) NOT NULL COMMENT '模版id',
  `column_id` varchar(10) NOT NULL COMMENT '表单key元素名字 ',
  `element_id` varchar(10) NOT NULL COMMENT '元素id',
  `label` varchar(20) NOT NULL COMMENT '表单显示',
  `element_params` text COMMENT '可能存在的个性参数',
  `default_value` varchar(30) DEFAULT NULL COMMENT '默认值',
  `required` int(1) NOT NULL DEFAULT '0' COMMENT '是否必填1是，0否',
  `max_length` int(3) DEFAULT NULL,
  `min_length` int(3) DEFAULT NULL,
  `is_used` int(1) NOT NULL DEFAULT '1' COMMENT '是否使用',
  `is_read_only` int(1) NOT NULL DEFAULT '0' COMMENT '是否只读',
  `is_display` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `remark` varchar(30) DEFAULT NULL COMMENT '描述',
  `order_no` int(6) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`template_id`,`column_id`),
  KEY `pk_element_id` (`element_id`),
  CONSTRAINT `pk_element_id` FOREIGN KEY (`element_id`) REFERENCES `td_ui_element` (`element_id`),
  CONSTRAINT `pk_template_id` FOREIGN KEY (`template_id`) REFERENCES `td_ui_template` (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模版元素关联表';

-- ----------------------------
-- Table structure for td_ui_view
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_view`;
CREATE TABLE `td_ui_view` (
  `view_id` varchar(8) NOT NULL,
  `bill_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`view_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_w_config
-- ----------------------------
DROP TABLE IF EXISTS `td_w_config`;
CREATE TABLE `td_w_config` (
  `config_id` varchar(10) NOT NULL,
  `config_value` varchar(50) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `used` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';

-- ----------------------------
-- Table structure for td_w_exelog
-- ----------------------------
DROP TABLE IF EXISTS `td_w_exelog`;
CREATE TABLE `td_w_exelog` (
  `log_id` varchar(16) NOT NULL,
  `work_id` int(5) NOT NULL,
  `task_id` int(8) DEFAULT NULL COMMENT '可能存在的taskid',
  `type` int(1) NOT NULL COMMENT '1-workinfo,2-taskinfo,3-debug',
  `state` int(2) NOT NULL COMMENT '执行状态 2完成，40错误',
  `content` varchar(50) NOT NULL COMMENT '信息内容',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `update_time` date DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Table structure for td_w_route
-- ----------------------------
DROP TABLE IF EXISTS `td_w_route`;
CREATE TABLE `td_w_route` (
  `work_id` int(5) NOT NULL,
  `start_task_id` int(8) NOT NULL,
  `end_task_id` int(8) NOT NULL,
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `used` varchar(1) DEFAULT NULL COMMENT '是否使用',
  `update_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_time` date DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路由表';

-- ----------------------------
-- Table structure for td_w_task
-- ----------------------------
DROP TABLE IF EXISTS `td_w_task`;
CREATE TABLE `td_w_task` (
  `task_id` int(8) NOT NULL,
  `task_name` varchar(50) NOT NULL COMMENT 'task名称',
  `task_type` int(1) NOT NULL COMMENT '类型  1存过 2java程序，',
  `content` varchar(50) NOT NULL COMMENT '执行内容',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `parallel` int(1) NOT NULL DEFAULT '0' COMMENT '是否可以并行 1可以',
  `used` int(1) DEFAULT NULL COMMENT '是否使用',
  `update_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Table structure for td_w_work
-- ----------------------------
DROP TABLE IF EXISTS `td_w_work`;
CREATE TABLE `td_w_work` (
  `work_id` int(5) NOT NULL,
  `work_name` varchar(50) NOT NULL COMMENT '任务名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date DEFAULT NULL COMMENT '结束时间',
  `work_type` varchar(1) NOT NULL COMMENT '类型1简单，2日历',
  `exe_num` int(3) DEFAULT '0' COMMENT '运行次数0无限次',
  `exp_month` int(2) DEFAULT NULL COMMENT '月份',
  `exp_day` int(2) DEFAULT NULL COMMENT '天',
  `exp_hours` int(2) DEFAULT NULL COMMENT '小时',
  `exp_minutes` int(2) DEFAULT NULL COMMENT '分',
  `exp_seconds` int(2) DEFAULT NULL COMMENT '秒',
  `expression` varchar(20) NOT NULL COMMENT '表达式',
  `state` varchar(1) DEFAULT NULL COMMENT '运行状态',
  `sys_type` varchar(10) DEFAULT NULL COMMENT '系统类型',
  `upate_tag` varchar(1) DEFAULT NULL COMMENT '修改标志1可以',
  `restart_tag` varchar(1) DEFAULT NULL COMMENT '是否可以重试',
  `update_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`work_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='work工作组';

-- ----------------------------
-- Table structure for wx_account
-- ----------------------------
DROP TABLE IF EXISTS `wx_account`;
CREATE TABLE `wx_account` (
  `account_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '系统内微信帐号id',
  `depart_id` varchar(5) DEFAULT NULL,
  `appid` varchar(200) NOT NULL COMMENT '微信端的唯一id',
  `appsecret` varchar(255) NOT NULL COMMENT '微信一开始给的唯一密钥',
  `encodingAESKey` varchar(20) DEFAULT NULL,
  `id` int(2) DEFAULT NULL COMMENT '表示不同应用',
  `app_name` varchar(20) NOT NULL,
  `access_token` varchar(600) NOT NULL COMMENT '全局票据',
  `expires_in` varchar(255) NOT NULL COMMENT '有效时间',
  `update_time` datetime NOT NULL COMMENT '票据更新时间',
  `jsaccess_token` varchar(600) NOT NULL,
  `jsexpires_in` varchar(255) NOT NULL,
  `jsupdate_time` datetime NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0未接入，1正常，3失效',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_article
-- ----------------------------
DROP TABLE IF EXISTS `wx_article`;
CREATE TABLE `wx_article` (
  `article_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `account_id` int(6) NOT NULL,
  `media_id` varchar(255) NOT NULL,
  `type` int(1) NOT NULL COMMENT '1,图文消息',
  `status` int(1) NOT NULL COMMENT '0未同步到微信，1已同步',
  `url` varchar(255) NOT NULL COMMENT '微信的url',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `updata_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_article_temp
-- ----------------------------
DROP TABLE IF EXISTS `wx_article_temp`;
CREATE TABLE `wx_article_temp` (
  `article_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `account_id` int(6) NOT NULL,
  `media_id` varchar(255) NOT NULL,
  `type` int(1) NOT NULL COMMENT '1,图文消息',
  `status` int(1) NOT NULL COMMENT '0未同步到微信，1已同步',
  `url` varchar(255) NOT NULL COMMENT '微信的url',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `updata_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001011 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_bargain
-- ----------------------------
DROP TABLE IF EXISTS `wx_bargain`;
CREATE TABLE `wx_bargain` (
  `bargain_id` int(8) NOT NULL AUTO_INCREMENT,
  `account_id` int(6) NOT NULL,
  `template` varchar(10) NOT NULL,
  `bargain_name` varchar(50) NOT NULL,
  `des` varchar(100) DEFAULT NULL,
  `rules` varchar(2000) DEFAULT NULL,
  `total_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '总价',
  `min_price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '可以砍掉的最大价钱',
  `one_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '一次性砍掉的最高价钱',
  `max_bargain_num` int(5) NOT NULL COMMENT '砍价的最大次数',
  `bargain_num` int(5) NOT NULL DEFAULT '0' COMMENT '当前砍价次数',
  `sale_num` int(4) NOT NULL DEFAULT '0' COMMENT '销量',
  `user_num` int(9) NOT NULL DEFAULT '0' COMMENT '参与人数',
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `goods` text,
  `create_staff` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bargain_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11221119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_bargain_join
-- ----------------------------
DROP TABLE IF EXISTS `wx_bargain_join`;
CREATE TABLE `wx_bargain_join` (
  `join_id` int(8) NOT NULL AUTO_INCREMENT,
  `bargain_id` int(8) NOT NULL,
  `openid` varchar(50) NOT NULL,
  `status` int(1) NOT NULL COMMENT '1-砍价中，2-兑奖',
  `price` decimal(8,2) NOT NULL COMMENT '当前价钱',
  `bargain_price` decimal(8,2) NOT NULL COMMENT '砍掉价格',
  `bargain_num` int(3) NOT NULL,
  `qrcode_id` int(10) DEFAULT NULL COMMENT '兑奖时对应的二维吗id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`join_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000035 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_bargain_success
-- ----------------------------
DROP TABLE IF EXISTS `wx_bargain_success`;
CREATE TABLE `wx_bargain_success` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `join_id` int(8) NOT NULL,
  `openid` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL COMMENT '当前价钱',
  `bargain_price` decimal(8,2) NOT NULL COMMENT '砍掉价格',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000040 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_media
-- ----------------------------
DROP TABLE IF EXISTS `wx_media`;
CREATE TABLE `wx_media` (
  `media_id` varchar(100) NOT NULL,
  `account_id` int(6) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT '1图片，对应MEDIA_TYPE',
  `file_id` int(8) NOT NULL COMMENT '对应td_s_file',
  `url` varchar(255) NOT NULL COMMENT '微信中对应的url',
  `created_at` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_media_news
-- ----------------------------
DROP TABLE IF EXISTS `wx_media_news`;
CREATE TABLE `wx_media_news` (
  `media_id` varchar(255) NOT NULL,
  `account_id` int(6) NOT NULL,
  `type` int(1) NOT NULL COMMENT '1图文素材',
  `title` varchar(255) NOT NULL,
  `thumb_media_id` varchar(255) DEFAULT NULL COMMENT '封面',
  `author` varchar(100) DEFAULT NULL,
  `digest` varchar(255) DEFAULT NULL COMMENT '摘要',
  `show_cover_pic` varchar(1) DEFAULT NULL COMMENT '是否显示为封面1显示 0不显示',
  `content` text NOT NULL COMMENT '阅读原文后的url',
  `content_source_url` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_qrcode
-- ----------------------------
DROP TABLE IF EXISTS `wx_qrcode`;
CREATE TABLE `wx_qrcode` (
  `qrcode_id` int(10) NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  `action_name` varchar(20) NOT NULL,
  `scene_id` varchar(30) DEFAULT NULL,
  `scene_str` varchar(64) DEFAULT NULL,
  `expire_seconds` int(10) DEFAULT NULL,
  `ticket` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL COMMENT '二维码解析后的地址，可以自行用地址生成url',
  `qr_url` varchar(255) DEFAULT NULL COMMENT '二维码地址,可用来展现二维码',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`qrcode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_qrcode_rel
-- ----------------------------
DROP TABLE IF EXISTS `wx_qrcode_rel`;
CREATE TABLE `wx_qrcode_rel` (
  `qrcode_id` int(10) NOT NULL AUTO_INCREMENT,
  `account_id` int(6) NOT NULL,
  `type` varchar(255) NOT NULL COMMENT 'bargain看见对应QRCODE_TYPE',
  `params` text,
  PRIMARY KEY (`qrcode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000020022 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_return_message
-- ----------------------------
DROP TABLE IF EXISTS `wx_return_message`;
CREATE TABLE `wx_return_message` (
  `message_id` int(10) NOT NULL AUTO_INCREMENT,
  `template_id` int(6) NOT NULL COMMENT '1文本',
  `account_id` int(6) NOT NULL COMMENT '1为全部',
  `content` varchar(255) NOT NULL COMMENT '相应内容',
  `order_no` int(1) NOT NULL COMMENT '优先级1最高',
  `update_user` int(8) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000010002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_return_template
-- ----------------------------
DROP TABLE IF EXISTS `wx_return_template`;
CREATE TABLE `wx_return_template` (
  `template_id` int(6) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL COMMENT '1文本',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `params` text NOT NULL,
  `update_user` int(8) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `wx_subscriber`;
CREATE TABLE `wx_subscriber` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户id',
  `type` int(1) NOT NULL COMMENT '1订阅，0取消',
  `account_id` int(6) NOT NULL COMMENT '微信帐号id',
  `openid` varchar(50) NOT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '微信的头像url',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `sub_time` datetime DEFAULT NULL COMMENT '订阅时间',
  `update_time` datetime DEFAULT NULL,
  `access_token` varchar(500) DEFAULT NULL COMMENT '网页授权token',
  `expires_in` bigint(10) DEFAULT NULL COMMENT '网页授权有效时间',
  `refresh_token` varchar(500) DEFAULT NULL COMMENT '用来刷新token',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_vote
-- ----------------------------
DROP TABLE IF EXISTS `wx_vote`;
CREATE TABLE `wx_vote` (
  `vote_id` int(8) NOT NULL AUTO_INCREMENT,
  `account_id` int(6) NOT NULL,
  `template` varchar(20) NOT NULL COMMENT '使用模版,对应VOTE_TEMPLATE',
  `audit` int(1) NOT NULL DEFAULT '1' COMMENT '是否审核',
  `vote_name` varchar(100) NOT NULL COMMENT '活动的名字',
  `des` varchar(255) DEFAULT NULL COMMENT '活动描述',
  `rules` text COMMENT '规则描述',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `login_url` varchar(200) NOT NULL COMMENT '未关注跳转url',
  `create_staff` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_vote_count
-- ----------------------------
DROP TABLE IF EXISTS `wx_vote_count`;
CREATE TABLE `wx_vote_count` (
  `vote_id` int(8) NOT NULL,
  `user_num` int(5) NOT NULL DEFAULT '0',
  `vote_num` int(10) NOT NULL DEFAULT '0' COMMENT '票数',
  `visit_num` int(11) NOT NULL DEFAULT '0' COMMENT '访问次数',
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_vote_image
-- ----------------------------
DROP TABLE IF EXISTS `wx_vote_image`;
CREATE TABLE `wx_vote_image` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) NOT NULL,
  `type` varchar(2) NOT NULL,
  `player_id` int(11) NOT NULL,
  `image` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000030 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_vote_player
-- ----------------------------
DROP TABLE IF EXISTS `wx_vote_player`;
CREATE TABLE `wx_vote_player` (
  `player_id` int(10) NOT NULL AUTO_INCREMENT,
  `vote_id` int(8) NOT NULL,
  `status` int(1) NOT NULL COMMENT '状态1通过，0未审核',
  `player_name` varchar(40) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `des` varchar(100) NOT NULL,
  `player_num` int(5) DEFAULT NULL COMMENT '选手号码',
  `image` varchar(100) NOT NULL COMMENT '图片',
  `vote_num` int(11) NOT NULL COMMENT '票数',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000030 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_vote_success
-- ----------------------------
DROP TABLE IF EXISTS `wx_vote_success`;
CREATE TABLE `wx_vote_success` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `vote_id` int(8) NOT NULL,
  `openid` varchar(50) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000003 DEFAULT CHARSET=utf8;
