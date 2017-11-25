/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50616
Source Host           : 114.215.120.117:3306
Source Database       : shabaotest

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2017-09-07 23:22:10
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
-- Records of td_f_seckill
-- ----------------------------
INSERT INTO `td_f_seckill` VALUES ('1000', 'iphone6', '93', '2016-07-08 23:24:41', '2018-01-02 22:32:43', '2016-06-02 22:32:47');
INSERT INTO `td_f_seckill` VALUES ('1001', '红米', '49', '2016-06-12 21:26:40', '2016-10-01 22:16:51', '2016-06-21 22:16:54');

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
-- Records of td_f_seckill_success
-- ----------------------------
INSERT INTO `td_f_seckill_success` VALUES ('1000', '13841111523', '-1', '2016-06-02 22:33:27');
INSERT INTO `td_f_seckill_success` VALUES ('1000', '18612383654', '-1', '2016-06-06 23:14:16');
INSERT INTO `td_f_seckill_success` VALUES ('1000', '132546666662', '-1', '2016-06-04 11:25:27');
INSERT INTO `td_f_seckill_success` VALUES ('1000', '132546666666', '-1', '2016-06-04 11:22:30');
INSERT INTO `td_f_seckill_success` VALUES ('1001', '18686123835', '-1', '2016-06-12 21:26:40');

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
-- Records of td_m_depart
-- ----------------------------
INSERT INTO `td_m_depart` VALUES ('00000', '中国区', '001', '00000', '#', '1', '0', '1', '1');
INSERT INTO `td_m_depart` VALUES ('10001', '内蒙古', '111', '0000010001', '00000', '2', '0', '1', '1');
INSERT INTO `td_m_depart` VALUES ('10002', '测试区', '111', '000001000110002', '10001', '3', '0', '1', '1');

-- ----------------------------
-- Table structure for td_m_menu
-- ----------------------------
DROP TABLE IF EXISTS `td_m_menu`;
CREATE TABLE `td_m_menu` (
  `menu_id` varchar(6) NOT NULL,
  `group_id` int(3) DEFAULT NULL COMMENT '分组，100admin',
  `menu_title` varchar(255) NOT NULL COMMENT '显示名',
  `ismenu` int(1) NOT NULL COMMENT '是否是菜单按钮',
  `ioc` varchar(20) DEFAULT NULL COMMENT '图标样式',
  `menu_des` varchar(255) DEFAULT NULL COMMENT '描述',
  `parent_menu_id` varchar(6) DEFAULT NULL COMMENT '父级标题id',
  `foruse` int(1) DEFAULT NULL COMMENT '是否使用：1、使用，0、不使用',
  `level` int(1) DEFAULT NULL COMMENT '标题等级',
  `permission_id` int(6) DEFAULT NULL COMMENT '对应权限id表示',
  `url` varchar(255) DEFAULT NULL,
  `order_no` int(2) DEFAULT NULL COMMENT '菜单当前等级排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of td_m_menu
-- ----------------------------
INSERT INTO `td_m_menu` VALUES ('YY0000', '999', '后台菜单', '0', '', null, '1', null, '0', null, null, null);
INSERT INTO `td_m_menu` VALUES ('YY0001', '100', '系统管理', '0', 'fa-columns', null, 'YY0000', '1', '1', null, '/admin/accountUser.html', '9000');
INSERT INTO `td_m_menu` VALUES ('YY0004', '100', '微信菜单', '1', 'fa-columns', null, 'YY0003', '1', '2', null, '/admin/wechatMenu.html', '1');
INSERT INTO `td_m_menu` VALUES ('YY0008', '100', '微信素材管理', '0', 'fa-columns', null, 'YY0000', '1', '1', null, null, '400');
INSERT INTO `td_m_menu` VALUES ('YY0009', '100', '文章列表', '1', 'fa-columns', null, 'YY0008', '1', '2', null, '/admin/wechat/article/list', '1');
INSERT INTO `td_m_menu` VALUES ('YY0010', '100', '用户列表', '1', 'fa-columns', '', 'YY0001', '1', '2', null, '/admin/accountUser.html', '9000');
INSERT INTO `td_m_menu` VALUES ('YY0011', '100', '部门管理', '1', 'fa-columns', '', 'YY0001', '1', '2', null, '/admin/depart/view', '100');
INSERT INTO `td_m_menu` VALUES ('YY0012', '100', '用户新增', '1', 'fa-columns', '', 'YY0001', '1', '2', null, '/admin/user/registered', '200');
INSERT INTO `td_m_menu` VALUES ('YY0013', '100', '微信活动', '0', 'fa-columns', '', 'YY0000', '1', '1', null, '', '500');
INSERT INTO `td_m_menu` VALUES ('YY0014', '100', '砍价列表', '1', 'fa-columns', '', 'YY0013', '1', '2', null, '/admin/wechat/bargain/list', '100');
INSERT INTO `td_m_menu` VALUES ('YY0015', '100', '微信帐号管理', '0', 'fa-columns', '', 'YY0000', '1', '1', null, '', '300');
INSERT INTO `td_m_menu` VALUES ('YY0016', '100', '微信用户列表', '1', 'fa-columns', '', 'YY0015', '1', '2', null, '/admin/wechat/user/list', '100');
INSERT INTO `td_m_menu` VALUES ('YY0017', '100', '单据展示1', '1', 'fa-columns', '', 'YY0001', '1', '2', null, '/admin/ui/form/demo0001/list', '500');

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
-- Records of td_m_permission
-- ----------------------------
INSERT INTO `td_m_permission` VALUES ('100001', '查询用户', 'user:select', '1', '查询用户菜单', '1');
INSERT INTO `td_m_permission` VALUES ('100002', '查询微信用户', 'wUser:select', '1', '查询微信用户', '1');

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
-- Records of td_m_role
-- ----------------------------
INSERT INTO `td_m_role` VALUES ('1001', '超级管理员', '超级管理员', '2016-01-17 21:07:24');

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
  `is_used` int(1) NOT NULL DEFAULT '1' COMMENT '是否使用',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_m_user
-- ----------------------------
INSERT INTO `td_m_user` VALUES ('12345678', '00000', 'root', '超级管理员', '6d335dc71084f0f1dbfa093bde69e308', '0fb3e4b92d839be934bf400e194493cb', '1', '2016-01-11 17:14:59', '1');

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
-- Records of td_m_userinfo
-- ----------------------------

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
-- Records of td_m_user_depart
-- ----------------------------

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
-- Records of td_sm_role_permission
-- ----------------------------
INSERT INTO `td_sm_role_permission` VALUES ('10000001', '1001', '100001');

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
-- Records of td_sm_user_role
-- ----------------------------
INSERT INTO `td_sm_user_role` VALUES ('100001', '12345678', '1001');

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
-- Records of td_s_file
-- ----------------------------
INSERT INTO `td_s_file` VALUES ('10002000', '11.png', '/resources/wechat/upload/images/20160821/11.png', '2016-08-21 11:27:11');
INSERT INTO `td_s_file` VALUES ('10002001', '0.jpg', '/resources/wechat/upload/images/20160821/0.jpg', '2016-08-22 21:33:38');

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
-- Records of td_s_poster
-- ----------------------------
INSERT INTO `td_s_poster` VALUES ('1000001', 'vote', '10000001', '568357a9a4d81.png', null, null, '1');
INSERT INTO `td_s_poster` VALUES ('1000002', 'vote', '10000001', '568358a53332e.png', null, null, '2');
INSERT INTO `td_s_poster` VALUES ('1000003', 'vote', '10000002', '568357a9a4d81.png', '', null, '1');
INSERT INTO `td_s_poster` VALUES ('1000004', 'vote', '10000002', '568358a53332e.png', '', null, '2');
INSERT INTO `td_s_poster` VALUES ('1000005', 'bargain', '11221111', '/resources/upload/poster/20160831/thumb_5710843bb70f9.jpg', '小朋友们开心学习', '商品详情', '1');
INSERT INTO `td_s_poster` VALUES ('1000006', 'bargain', '11221111', '/resources/upload/poster/20160831/thumb_572b0ec4a9be1.jpg', '小朋友们开心学习', '商品详情', '2');
INSERT INTO `td_s_poster` VALUES ('1000009', 'bargain', '11221115', '/resources/upload/poster/20160831/20160831145413_891.png', '卡卡', '商品详情', '1');
INSERT INTO `td_s_poster` VALUES ('1000010', 'bargain', '11221116', '/resources/upload/poster/20160901/20160901155241_73.png', '标题测试', '商品详情', '1');
INSERT INTO `td_s_poster` VALUES ('1000011', 'bargain', '11221116', '/resources/upload/poster/20160901/20160901155252_489.jpg', '标题测试', '商品详情', '2');
INSERT INTO `td_s_poster` VALUES ('1000012', 'bargain', '11221117', '/resources/upload/poster/20160903/20160903175010_852.jpg', '砍价活动进行中', '商品详情', '1');
INSERT INTO `td_s_poster` VALUES ('1000013', 'bargain', '11221117', '/resources/upload/poster/20160903/20160903175017_760.jpg', '砍价活动进行中', '商品详情', '2');
INSERT INTO `td_s_poster` VALUES ('1000014', 'bargain', '11221118', '/resources/upload/poster/20160917/20160917001550_599.jpg', '测试', '商品详情', '1');

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
-- Records of td_s_seqstring
-- ----------------------------

-- ----------------------------
-- Table structure for td_s_static
-- ----------------------------
DROP TABLE IF EXISTS `td_s_static`;
CREATE TABLE `td_s_static` (
  `type_id` varchar(50) NOT NULL,
  `data_id` varchar(20) NOT NULL,
  `data_name` varchar(50) NOT NULL,
  `order_no` int(4) DEFAULT NULL COMMENT '排序',
  `is_used` int(1) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`type_id`,`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_s_static
-- ----------------------------
INSERT INTO `td_s_static` VALUES ('ACCOUNT_TYPE', '1', '微信帐号', null, '1');
INSERT INTO `td_s_static` VALUES ('MEDIA_TYPE', 'image', '图片', null, '1');
INSERT INTO `td_s_static` VALUES ('MEDIA_TYPE', 'thumb', '缩略图', null, '1');
INSERT INTO `td_s_static` VALUES ('PUBLIC_BOOLEAN', '0', '否', null, '1');
INSERT INTO `td_s_static` VALUES ('PUBLIC_BOOLEAN', '1', '是', null, '1');
INSERT INTO `td_s_static` VALUES ('QRCODE_TYPE', 'bargain', '砍价', null, '1');
INSERT INTO `td_s_static` VALUES ('TEMPLATE_TYPE', '1', '欢迎模版', null, '1');
INSERT INTO `td_s_static` VALUES ('VOTE_TEMPLATE', 'blue', '蓝色投票模版', null, '1');

-- ----------------------------
-- Table structure for td_s_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `td_s_upgrade`;
CREATE TABLE `td_s_upgrade` (
  `upgrade_id` int(4) NOT NULL,
  `upgrade_name` varchar(100) NOT NULL,
  `upgrade_file_name` varchar(50) NOT NULL,
  `upgrade_date` varchar(10) NOT NULL,
  `server_user` varchar(30) DEFAULT NULL,
  `server_password` varchar(50) DEFAULT NULL,
  `server_host` varchar(30) DEFAULT NULL,
  `server_port` int(6) DEFAULT NULL,
  `server_path` varchar(50) NOT NULL COMMENT '服务器地址容器目录下（/../tomcat）',
  `application_path` varchar(30) NOT NULL COMMENT '应用程序目录',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user` int(8) NOT NULL,
  PRIMARY KEY (`upgrade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_s_upgrade
-- ----------------------------
INSERT INTO `td_s_upgrade` VALUES ('1001', '系统升级', 'shabao-test.war', '2017-01-01', 'root', 'XIAOshabao2016', '114.215.120.117', '22', '/webapp/test/upgrade-test', '/webapp/test/upgrade-test/root', '2017-07-25 22:33:22', '12345678');

-- ----------------------------
-- Table structure for td_uis_demo
-- ----------------------------
DROP TABLE IF EXISTS `td_uis_demo`;
CREATE TABLE `td_uis_demo` (
  `id` int(5) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `depart_id` varchar(5) DEFAULT NULL COMMENT '部门编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_uis_demo
-- ----------------------------
INSERT INTO `td_uis_demo` VALUES ('1', '张三', '00000');
INSERT INTO `td_uis_demo` VALUES ('11', '11', '00000');
INSERT INTO `td_uis_demo` VALUES ('12', '44', '00000');
INSERT INTO `td_uis_demo` VALUES ('13', '1', '00000');
INSERT INTO `td_uis_demo` VALUES ('14', '1', '00000');
INSERT INTO `td_uis_demo` VALUES ('22', '', '00000');
INSERT INTO `td_uis_demo` VALUES ('33', '33', '00000');
INSERT INTO `td_uis_demo` VALUES ('44', '444', '00000');
INSERT INTO `td_uis_demo` VALUES ('55', '55', '00000');
INSERT INTO `td_uis_demo` VALUES ('144', '111', '10001');
INSERT INTO `td_uis_demo` VALUES ('221', '111', '00000');
INSERT INTO `td_uis_demo` VALUES ('333', '3333', '00000');
INSERT INTO `td_uis_demo` VALUES ('345', '22222', '00000');
INSERT INTO `td_uis_demo` VALUES ('555', '55', '00000');
INSERT INTO `td_uis_demo` VALUES ('1444', '111', '10001');
INSERT INTO `td_uis_demo` VALUES ('5555', '555', '00000');
INSERT INTO `td_uis_demo` VALUES ('33111', '333', '10002');

-- ----------------------------
-- Table structure for td_ui_bill
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_bill`;
CREATE TABLE `td_ui_bill` (
  `bill_id` varchar(8) NOT NULL,
  `bill_name` varchar(50) NOT NULL,
  `bill_class` varchar(10) NOT NULL COMMENT '分类',
  `bill_engine` varchar(20) NOT NULL,
  `order_no` int(8) NOT NULL,
  `state` int(1) NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `fk_td_ui_bill_engine` (`bill_engine`),
  CONSTRAINT `fk_td_ui_bill_engine` FOREIGN KEY (`bill_engine`) REFERENCES `td_ui_bill_engine` (`bill_engine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_bill
-- ----------------------------
INSERT INTO `td_ui_bill` VALUES ('demo0001', '示例1', 'TEST', 'simple', '1', '1');

-- ----------------------------
-- Table structure for td_ui_bill_engine
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_bill_engine`;
CREATE TABLE `td_ui_bill_engine` (
  `bill_engine` varchar(20) NOT NULL COMMENT '单据的引擎标识',
  `bill_engine_name` varchar(30) DEFAULT NULL,
  `list_engine` varchar(255) NOT NULL COMMENT '单据的模版',
  `view_engine` varchar(255) NOT NULL,
  `order_no` int(4) NOT NULL,
  `is_used` int(1) NOT NULL,
  PRIMARY KEY (`bill_engine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_bill_engine
-- ----------------------------
INSERT INTO `td_ui_bill_engine` VALUES ('simple', '简单引擎（默认）', 'simpleListService', 'simpleViewService', '1', '1');

-- ----------------------------
-- Table structure for td_ui_button
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_button`;
CREATE TABLE `td_ui_button` (
  `button_id` int(5) NOT NULL AUTO_INCREMENT,
  `button_name` varchar(50) NOT NULL,
  `button_value` varchar(20) NOT NULL,
  `button_type` int(11) NOT NULL,
  `image_id` int(5) NOT NULL,
  `button_frame` varchar(10) NOT NULL DEFAULT '0000000000' COMMENT '适用范围,自右到左分别为：列表，编辑',
  `order_no` int(6) DEFAULT NULL,
  `is_used` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`button_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12324 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_button
-- ----------------------------
INSERT INTO `td_ui_button` VALUES ('12321', '查询', 'BUTTON_QUERY', '1', '22111', '0000000001', '10', '1');
INSERT INTO `td_ui_button` VALUES ('12322', '新增', 'BUTTON_ADD', '1', '22111', '0000000101', '20', '1');
INSERT INTO `td_ui_button` VALUES ('12323', '保存', 'BUTTON_SAVE', '1', '22111', '0000001001', '30', '1');

-- ----------------------------
-- Table structure for td_ui_button_image
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_button_image`;
CREATE TABLE `td_ui_button_image` (
  `image_id` int(5) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(20) NOT NULL,
  `image_desc` varchar(30) DEFAULT NULL,
  `file_class` varchar(30) DEFAULT NULL COMMENT '文件样式,优先于路径模式',
  `file_path` varchar(30) DEFAULT NULL COMMENT '文件路径',
  `is_used` int(11) NOT NULL,
  `order_no` int(4) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_button_image
-- ----------------------------
INSERT INTO `td_ui_button_image` VALUES ('22111', '新增', '新增加号', null, null, '1', '100');

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
-- Records of td_ui_element
-- ----------------------------
INSERT INTO `td_ui_element` VALUES ('SYS_01', 'text', '文本框', '普通文本框', '', '1', '1', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_02', 'number', '数字', '普通数字', null, '1', '2', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_03', 'select', '下拉框', '普通下拉框', null, '1', '3', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_04', 'date', '日期', '普通日期', null, '1', '4', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_05', 'single_ref', '单值引用', '单值引用', null, '1', '5', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_06', 'more_ref', '多栏引用', '多栏引用', null, '1', '6', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_07', 'textarea', '文本域', '文本域', null, '1', '7', null, null);
INSERT INTO `td_ui_element` VALUES ('SYS_08', 'editor', '编辑器', '富文本编辑器', null, '1', '8', null, null);

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
-- Records of td_ui_engine_type
-- ----------------------------
INSERT INTO `td_ui_engine_type` VALUES ('list', 'simpleListService', '简单列表引擎-默认', '适用于大多数情况', '1');

-- ----------------------------
-- Table structure for td_ui_list
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_list`;
CREATE TABLE `td_ui_list` (
  `list_id` varchar(8) NOT NULL,
  `list_name` varchar(30) NOT NULL,
  `bill_id` varchar(8) NOT NULL,
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
-- Records of td_ui_list
-- ----------------------------
INSERT INTO `td_ui_list` VALUES ('dlist001', '示例列表视图', 'demo0001', 'demo_001', 'demo0001', 'demo_001', '0', '1', null, '1');

-- ----------------------------
-- Table structure for td_ui_list_button
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_list_button`;
CREATE TABLE `td_ui_list_button` (
  `list_id` varchar(10) NOT NULL,
  `button_id` int(5) NOT NULL,
  `button_name` varchar(30) NOT NULL,
  `order_no` int(4) DEFAULT NULL,
  `is_used` int(1) NOT NULL,
  PRIMARY KEY (`list_id`,`button_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_list_button
-- ----------------------------
INSERT INTO `td_ui_list_button` VALUES ('dlist001', '12321', '查询', '1', '1');
INSERT INTO `td_ui_list_button` VALUES ('dlist001', '12322', '新增', '2', '1');

-- ----------------------------
-- Table structure for td_ui_report
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_report`;
CREATE TABLE `td_ui_report` (
  `report_id` varchar(10) NOT NULL,
  `table_id` varchar(10) NOT NULL,
  `report_name` varchar(30) NOT NULL,
  `report_engine` varchar(25) NOT NULL COMMENT '引擎类型',
  `is_sum_row` int(1) NOT NULL DEFAULT '0' COMMENT '是否需要合计行',
  `row_num` int(4) NOT NULL DEFAULT '10' COMMENT '每页行数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_report
-- ----------------------------
INSERT INTO `td_ui_report` VALUES ('demo_001', 'demo_001', '实例表', 'simpleReportService', '0', '10');

-- ----------------------------
-- Table structure for td_ui_report_column
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_report_column`;
CREATE TABLE `td_ui_report_column` (
  `report_id` varchar(10) NOT NULL,
  `column_id` varchar(10) NOT NULL,
  `element_id` varchar(10) NOT NULL,
  `title` varchar(30) NOT NULL,
  `title_group1` varchar(30) DEFAULT NULL,
  `title_group2` varchar(30) DEFAULT NULL,
  `title_group3` varchar(30) DEFAULT NULL,
  `title_group4` varchar(30) DEFAULT NULL,
  `is_href` int(1) NOT NULL DEFAULT '0',
  `ext_params` text,
  `is_display` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `is_used` int(1) NOT NULL DEFAULT '1' COMMENT '是否使用',
  `order_no` int(6) NOT NULL DEFAULT '1',
  PRIMARY KEY (`report_id`,`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表列表';

-- ----------------------------
-- Records of td_ui_report_column
-- ----------------------------
INSERT INTO `td_ui_report_column` VALUES ('demo_001', 'demo_00101', 'SYS_01', '编码', null, null, null, null, '0', null, '1', '1', '1');
INSERT INTO `td_ui_report_column` VALUES ('demo_001', 'demo_00102', 'SYS_01', '名称', null, null, null, null, '1', null, '1', '1', '1');
INSERT INTO `td_ui_report_column` VALUES ('demo_001', 'demo_00103', 'SYS_03', '部门', null, null, null, null, '0', null, '1', '1', '1');

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
-- Records of td_ui_static
-- ----------------------------
INSERT INTO `td_ui_static` VALUES ('BEFORE_SCRIPT', 'jquery', '需要提前引入的脚本资源(在头部引入的js)', '1', '1');
INSERT INTO `td_ui_static` VALUES ('COMMON_RESOURCE', 'bootbox', '需要引入的公用资源(包括在所有资源)', '1', '3');
INSERT INTO `td_ui_static` VALUES ('COMMON_RESOURCE', 'bootstrap', '需要引入的公用资源(包括在所有资源)', '1', '2');
INSERT INTO `td_ui_static` VALUES ('COMMON_RESOURCE', 'system', '需要引入的公用资源(包括在所有资源)', '1', '3');
INSERT INTO `td_ui_static` VALUES ('FIELD_ATTR', '1', '数据代码', '1', '1');
INSERT INTO `td_ui_static` VALUES ('FIELD_ATTR', '2', '数据名称', '1', '2');
INSERT INTO `td_ui_static` VALUES ('FIELD_ATTR', '3', '上级代码', '1', '3');

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
-- Records of td_ui_table
-- ----------------------------
INSERT INTO `td_ui_table` VALUES ('demo_001', '示例表', 'td_uis_demo', '1', null);
INSERT INTO `td_ui_table` VALUES ('demo_002', '部门示例', 'td_m_depart', '1', null);

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
  `field_attr` int(2) DEFAULT NULL COMMENT '属性，1-CODE，2-value',
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
-- Records of td_ui_table_column
-- ----------------------------
INSERT INTO `td_ui_table_column` VALUES ('demo_00101', 'demo_001', 'id', '编码', '1', null, '5', '0', '1', '1', '0', null, null);
INSERT INTO `td_ui_table_column` VALUES ('demo_00102', 'demo_001', 'name', '名字', '1', null, '30', null, '0', '0', '0', null, null);
INSERT INTO `td_ui_table_column` VALUES ('demo_00103', 'demo_001', 'depart_id', '部门', '1', null, '5', null, '0', '0', '1', 'demo_002', null);
INSERT INTO `td_ui_table_column` VALUES ('demo_00201', 'demo_002', 'depart_id', '部门编码', '1', '1', '5', null, '0', '0', '0', null, null);
INSERT INTO `td_ui_table_column` VALUES ('demo_00202', 'demo_002', 'depart_name', '部门名', '1', '2', '30', null, '0', '0', '0', null, null);

-- ----------------------------
-- Table structure for td_ui_template
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_template`;
CREATE TABLE `td_ui_template` (
  `template_id` varchar(10) NOT NULL COMMENT '模版id',
  `template_name` varchar(30) NOT NULL COMMENT '模版名字',
  `table_id` varchar(10) DEFAULT NULL,
  `template_engine` varchar(30) NOT NULL,
  `is_visible` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `col_count` varchar(255) DEFAULT NULL COMMENT '分栏数',
  `remark` varchar(40) DEFAULT NULL COMMENT '模版描述',
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模版表';

-- ----------------------------
-- Records of td_ui_template
-- ----------------------------
INSERT INTO `td_ui_template` VALUES ('demo0001', '示例查询1', 'demo_001', 'simpleTemplateService', '1', null, null);
INSERT INTO `td_ui_template` VALUES ('demo0002', '视图1', 'demo_001', 'simpleTemplateService', '1', null, null);

-- ----------------------------
-- Table structure for td_ui_template_element
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_template_element`;
CREATE TABLE `td_ui_template_element` (
  `template_id` varchar(10) NOT NULL COMMENT '模版id',
  `column_id` varchar(10) NOT NULL COMMENT '表单key元素名字 ',
  `element_id` varchar(10) NOT NULL COMMENT '元素id',
  `label` varchar(20) NOT NULL COMMENT '表单显示',
  `ext_params` text COMMENT '可能存在的个性参数',
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
-- Records of td_ui_template_element
-- ----------------------------
INSERT INTO `td_ui_template_element` VALUES ('demo0001', 'demo_00102', 'SYS_01', '名字', null, null, '0', null, null, '1', '0', '1', null, null);
INSERT INTO `td_ui_template_element` VALUES ('demo0001', 'demo_00103', 'SYS_03', '部门', null, null, '0', null, null, '1', '0', '1', null, null);
INSERT INTO `td_ui_template_element` VALUES ('demo0002', 'demo_00101', 'SYS_01', '编码', null, null, '1', null, null, '1', '0', '1', null, '1');
INSERT INTO `td_ui_template_element` VALUES ('demo0002', 'demo_00102', 'SYS_01', '名字', null, null, '0', null, null, '1', '0', '1', null, null);
INSERT INTO `td_ui_template_element` VALUES ('demo0002', 'demo_00103', 'SYS_03', '部门', null, null, '0', null, null, '1', '0', '1', null, null);

-- ----------------------------
-- Table structure for td_ui_view
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_view`;
CREATE TABLE `td_ui_view` (
  `view_id` varchar(8) NOT NULL,
  `view_name` varchar(30) NOT NULL,
  `bill_id` varchar(8) NOT NULL,
  `view_position` int(1) NOT NULL COMMENT '元素位置，1主要元素',
  `view_type` int(1) NOT NULL DEFAULT '1' COMMENT '视图类型，1栅格布局',
  `view_ext_id` varchar(10) NOT NULL COMMENT '视图扩展id',
  `order_no` int(4) NOT NULL,
  PRIMARY KEY (`view_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_view
-- ----------------------------
INSERT INTO `td_ui_view` VALUES ('dview01', '测试1', 'demo0001', '1', '1', 'demo0002', '1');

-- ----------------------------
-- Table structure for td_ui_view_button
-- ----------------------------
DROP TABLE IF EXISTS `td_ui_view_button`;
CREATE TABLE `td_ui_view_button` (
  `view_id` varchar(10) NOT NULL,
  `button_id` int(5) NOT NULL,
  `button_name` varchar(30) NOT NULL,
  `order_no` int(4) DEFAULT NULL,
  `is_used` int(1) NOT NULL,
  PRIMARY KEY (`view_id`,`button_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_ui_view_button
-- ----------------------------
INSERT INTO `td_ui_view_button` VALUES ('dview01', '12322', '新增', '1', '1');

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
-- Records of td_w_config
-- ----------------------------
INSERT INTO `td_w_config` VALUES ('SYS_GROUP', 'SYS', '执行的系统组', '1');

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
-- Records of td_w_exelog
-- ----------------------------

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
-- Records of td_w_route
-- ----------------------------

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
-- Records of td_w_task
-- ----------------------------

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
-- Records of td_w_work
-- ----------------------------

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
-- Records of wx_account
-- ----------------------------
INSERT INTO `wx_account` VALUES ('100001', '00000', 'wx07e34f9575809866', 'd8c5dae813951b0c31599c1a8aebf410', null, '1', '搁搁浅浅测试号', 'gkmeVfEmLVS9uTYB2mC80mkUDgFvpbEqMDZArxXt06kufQxQpkvtZF23yi9N8LY7_Gc4bj4muKSQi75SRGO-JUBg2mVwKEYw9s065ULOsnr9t3wqz4BPQ76jlU45nnwfHIKeABAQRT', '7200', '2017-03-07 20:30:00', 'kgt8ON7yVITDhtdwci0qeRoSMS7p2t4dNn_PhjvbCHzTvlmU6fEnpfjtljhD2N4GIN4AZI1bKq9VUg7i42aJRw', '7200', '2017-03-07 20:30:00', '1');
INSERT INTO `wx_account` VALUES ('100002', '00000', 'wxa93100d5621b6ff3', 'dbfb122a84eb74090f6536cea20386d6', null, '1', '测试2', 'srhO23DMgyO-gXL-637n9B8bdmyJFpVx3G9-hNdn5aAK3J8AQJpYVuKJk5C63jigvUFxR9RBWlanXBmdNultGvshtbLyO1V7qTKkg9Y92J3ocTFyqt8SragSjHLICe-dGHZiAEAPQI', '7200', '2017-03-07 20:30:00', 'kgt8ON7yVITDhtdwci0qeUG5HllshvRWtsnN267WYIkd1gpNYyR4SrQDgrHsinO4_PGG0Tulw4_jvp29PEELsA', '7200', '2017-03-07 20:30:00', '1');

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
-- Records of wx_article
-- ----------------------------
INSERT INTO `wx_article` VALUES ('10001002', '100001', 'QPreUSwRXnLnMqsVubaUJpqiVnUyE4D00Q8QcJtZgL0', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443967&idx=1&sn=75eceb936c18e9f346b01923bd55d481#rd', '2016-08-25 10:50:37', '12345678', '2016-08-25 10:50:37', '12345678');
INSERT INTO `wx_article` VALUES ('10001004', '100001', 'QPreUSwRXnLnMqsVubaUJv9Ba19BC8mvL_hI6Bf1Gro', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443969&idx=1&sn=d0f5ba036d1b6ac17dcbb4a3406120d4#rd', '2016-08-25 11:07:40', '12345678', '2016-08-25 11:07:40', '12345678');
INSERT INTO `wx_article` VALUES ('10001006', '100001', 'QPreUSwRXnLnMqsVubaUJjoq5GjgM_vzIH8vKZPmSBE', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443971&idx=1&sn=06dd987b1d111d53e401298cf99562ba#rd', '2016-08-25 11:09:02', '12345678', '2016-08-25 11:09:02', '12345678');
INSERT INTO `wx_article` VALUES ('10001007', '100001', 'QPreUSwRXnLnMqsVubaUJgVqHgU3mNnTyzPtbjqxLtk', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443973&idx=1&sn=fad0cd4768c77446306fc00fc3f8fe08#rd', '2016-08-25 14:54:33', '12345678', '2016-08-25 14:54:33', '12345678');
INSERT INTO `wx_article` VALUES ('10001008', '100001', 'QPreUSwRXnLnMqsVubaUJr72LS3-wj73rpIvCcqO8p0', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443975&idx=1&sn=edeaea6aa9f391418f024cc1dec873ff#rd', '2016-08-25 15:02:46', '12345678', '2016-08-25 15:02:46', '12345678');
INSERT INTO `wx_article` VALUES ('10001009', '100001', 'QPreUSwRXnLnMqsVubaUJruf7EMki8GzhK8feJpQCa8', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443977&idx=1&sn=0a21b171d42595d18ab27c0d9a649b64#rd', '2016-08-25 15:58:57', '12345678', '2016-08-25 15:58:57', '12345678');

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
-- Records of wx_article_temp
-- ----------------------------
INSERT INTO `wx_article_temp` VALUES ('10001002', '100001', 'QPreUSwRXnLnMqsVubaUJpqiVnUyE4D00Q8QcJtZgL0', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443967&idx=1&sn=75eceb936c18e9f346b01923bd55d481#rd', '2016-08-25 10:50:37', '12345678', '2016-08-25 10:50:37', '12345678');
INSERT INTO `wx_article_temp` VALUES ('10001004', '100001', 'QPreUSwRXnLnMqsVubaUJv9Ba19BC8mvL_hI6Bf1Gro', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443969&idx=1&sn=d0f5ba036d1b6ac17dcbb4a3406120d4#rd', '2016-08-25 11:07:40', '12345678', '2016-08-25 11:07:40', '12345678');
INSERT INTO `wx_article_temp` VALUES ('10001006', '100001', 'QPreUSwRXnLnMqsVubaUJjoq5GjgM_vzIH8vKZPmSBE', '1', '1', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443971&idx=1&sn=06dd987b1d111d53e401298cf99562ba#rd', '2016-08-25 11:09:02', '12345678', '2016-08-25 11:09:02', '12345678');
INSERT INTO `wx_article_temp` VALUES ('10001007', '100001', '0', '1', '1', '0', '2016-08-25 14:54:31', '12345678', '2016-08-25 14:54:31', '12345678');
INSERT INTO `wx_article_temp` VALUES ('10001008', '100001', '0', '1', '1', '0', '2016-08-25 14:59:41', '12345678', '2016-08-25 14:59:41', '12345678');
INSERT INTO `wx_article_temp` VALUES ('10001010', '100001', '0', '1', '1', '0', '2016-08-25 15:58:26', '12345678', '2016-08-25 15:58:26', '12345678');

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
-- Records of wx_bargain
-- ----------------------------
INSERT INTO `wx_bargain` VALUES ('11221111', '100001', 'one', '砍价测试', '砍价活动正在进行中', '<p>规则测试</p>', '95.00', '60.00', '5.00', '10', '32', '0', '32', '2016-07-28 23:16:56', '2017-04-01 23:17:00', '<p>这么好的商品？</p>', '12345678', '2016-07-28 23:17:35');
INSERT INTO `wx_bargain` VALUES ('11221115', '100001', 'one', '活动测试1', '描述', '<p>规则是11</p>', '100.00', '60.00', '10.00', '5', '0', '0', '0', '2016-09-01 00:00:00', '2016-09-30 00:00:00', '<p>这么好的商品？</p>', '12345678', '2016-08-31 14:56:30');
INSERT INTO `wx_bargain` VALUES ('11221116', '100001', 'one', '活动测试2', '1122', '<p>测试1</p>', '1111.00', '111.00', '20.00', '10', '0', '0', '0', '2016-09-02 00:00:00', '2016-09-22 00:00:00', '<p>测试1</p>', '12345678', '2016-09-01 15:53:22');
INSERT INTO `wx_bargain` VALUES ('11221117', '100001', 'one', '测试3', '测试3', '<p>随便看1</p>', '100.00', '70.00', '10.00', '5', '0', '0', '0', '2016-09-03 00:00:00', '2016-09-29 00:00:00', '<p>赠送拉拉</p>', '12345678', '2016-09-03 17:50:56');
INSERT INTO `wx_bargain` VALUES ('11221118', '100002', 'one', '活动2测试', '活动2测试', '<p>啊发生大幅度</p>', '100.00', '80.00', '10.00', '2', '4', '0', '4', '2016-09-17 00:00:00', '2017-01-01 00:00:00', '<p>萨芬的的撒</p>', '12345678', '2016-09-17 00:16:05');

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
-- Records of wx_bargain_join
-- ----------------------------
INSERT INTO `wx_bargain_join` VALUES ('1000030', '11221111', 'oGMKNwjuA6roAPJajXrkMPlA9ZG0', '1', '94.30', '0.70', '1', '1000020020', '2016-09-05 11:01:39', '2016-09-05 11:01:39');
INSERT INTO `wx_bargain_join` VALUES ('1000032', '11221111', 'oGMKNwnS1h3bcyKUu2WQsrFFNeZk', '1', '94.00', '1.00', '2', '1000020021', '2016-09-05 11:05:03', '2016-10-22 00:24:27');
INSERT INTO `wx_bargain_join` VALUES ('1000033', '11221118', 'okGGDwuaAk8LlJy4ETZsJkgOHMiM', '1', '99.00', '1.00', '2', null, '2016-10-06 00:14:31', '2016-10-06 00:17:25');
INSERT INTO `wx_bargain_join` VALUES ('1000034', '11221118', 'okGGDwuO9003pV6T1BcF6sVpik1A', '1', '99.30', '0.70', '2', null, '2016-10-06 00:18:06', '2016-10-06 00:19:50');

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
-- Records of wx_bargain_success
-- ----------------------------
INSERT INTO `wx_bargain_success` VALUES ('1000032', '1000030', 'oGMKNwjuA6roAPJajXrkMPlA9ZG0', '94.30', '0.70', '2016-09-05 11:01:39');
INSERT INTO `wx_bargain_success` VALUES ('1000034', '1000032', 'oGMKNwnS1h3bcyKUu2WQsrFFNeZk', '94.70', '0.30', '2016-09-05 11:05:03');
INSERT INTO `wx_bargain_success` VALUES ('1000035', '1000033', 'okGGDwuaAk8LlJy4ETZsJkgOHMiM', '99.50', '0.50', '2016-10-06 00:14:31');
INSERT INTO `wx_bargain_success` VALUES ('1000036', '1000033', 'okGGDwuO9003pV6T1BcF6sVpik1A', '99.00', '0.50', '2016-10-06 00:17:25');
INSERT INTO `wx_bargain_success` VALUES ('1000037', '1000034', 'okGGDwuO9003pV6T1BcF6sVpik1A', '99.90', '0.10', '2016-10-06 00:18:06');
INSERT INTO `wx_bargain_success` VALUES ('1000038', '1000034', 'okGGDwuaAk8LlJy4ETZsJkgOHMiM', '99.30', '0.60', '2016-10-06 00:19:50');
INSERT INTO `wx_bargain_success` VALUES ('1000039', '1000032', 'oGMKNwjuA6roAPJajXrkMPlA9ZG0', '94.00', '0.70', '2016-10-22 00:24:27');

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
-- Records of wx_media
-- ----------------------------
INSERT INTO `wx_media` VALUES ('QPreUSwRXnLnMqsVubaUJil6FmWPTbjvLJf4RlqQ3LQ', '100001', '1', '10002000', 'http://mmbiz.qpic.cn/mmbiz_png/BibYy0opVNUrHaeGbt3THHxhJx1rV0iaRficwZP69Nt4lM1TpBXrjjbzvoKrMSMPfN8bq5ozhW6REP9FxBFv5jxqg/0?wx_fmt=png', null, '2016-08-24 22:39:12');
INSERT INTO `wx_media` VALUES ('QPreUSwRXnLnMqsVubaUJsnXeOp3G_rt7QE-axP2eBA', '100001', '1', '10002001', 'http://mmbiz.qpic.cn/mmbiz_jpg/BibYy0opVNUoibYWiaYfLnDGwMKge0tFcVT15xyrOdFkCv5J3mMnrXGAlL6JibaUL6pPwCphGib3siciaicVdzoViazfU8A/0?wx_fmt=jpeg', null, '2016-08-24 22:40:37');

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
-- Records of wx_media_news
-- ----------------------------
INSERT INTO `wx_media_news` VALUES ('QPreUSwRXnLnMqsVubaUJgVqHgU3mNnTyzPtbjqxLtk', '100001', '1', '测试3', 'QPreUSwRXnLnMqsVubaUJil6FmWPTbjvLJf4RlqQ3LQ', '用户测试', '33', '1', '<p>444444444444444</p>', 'http://shabao.tunnel.qydev.com/wechat/article/10001007/detail', '2016-08-25 14:54:33');
INSERT INTO `wx_media_news` VALUES ('QPreUSwRXnLnMqsVubaUJjoq5GjgM_vzIH8vKZPmSBE', '100001', '1', '测试3', 'QPreUSwRXnLnMqsVubaUJsnXeOp3G_rt7QE-axP2eBA', '用户测试', '122', '1', '<p>22222222222222</p>', 'http://shabao.tunnel.qydev.com/wechat/article/10001005/detail', '2016-08-25 11:09:02');
INSERT INTO `wx_media_news` VALUES ('QPreUSwRXnLnMqsVubaUJpqiVnUyE4D00Q8QcJtZgL0', '100001', '1', '测试1', 'QPreUSwRXnLnMqsVubaUJil6FmWPTbjvLJf4RlqQ3LQ', '用户测试', '测试', '1', '<p>111111111111</p>', 'http://shabao.tunnel.qydev.com/wechat/article/10001001/detail', '2016-08-25 10:51:34');
INSERT INTO `wx_media_news` VALUES ('QPreUSwRXnLnMqsVubaUJr72LS3-wj73rpIvCcqO8p0', '100001', '1', '测试4', 'QPreUSwRXnLnMqsVubaUJil6FmWPTbjvLJf4RlqQ3LQ', '用户测试', '测试', '1', '<p><img src=\"/shabao-test/resources/upload/images/20160825/1472108354520061381.png\" title=\"1472108354520061381.png\" alt=\"11.png\"/>111111</p><p><br/></p><p><br/></p><p><img src=\"/shabao-test/resources/upload/images/20160825/1472108366923043961.jpg\" title=\"1472108366923043961.jpg\" alt=\"0.jpg\" width=\"127\" height=\"108\" style=\"width: 127px; height: 108px;\"/></p>', 'http://shabao.tunnel.qydev.com/wechat/article/10001008/detail', '2016-08-25 15:02:46');
INSERT INTO `wx_media_news` VALUES ('QPreUSwRXnLnMqsVubaUJruf7EMki8GzhK8feJpQCa8', '100001', '1', '测试6', 'QPreUSwRXnLnMqsVubaUJil6FmWPTbjvLJf4RlqQ3LQ', '用户测试', '测试', '0', '<p>111111111111111</p><p><img src=\"http://mmbiz.qpic.cn/mmbiz_png/BibYy0opVNUrC8iaiazHia0Y2OL8kadPyheicYwYBlPh1RPPSONy2BdAFrjcXRUkTOLrkZ9H2HWFmAKUWHyf5CWAD8A/0\" title=\"1472111897777019356.png\" alt=\"11.png\"/></p>', 'http://shabao.tunnel.qydev.com/wechat/article/10001010/detail', '2016-08-25 15:58:57');
INSERT INTO `wx_media_news` VALUES ('QPreUSwRXnLnMqsVubaUJv9Ba19BC8mvL_hI6Bf1Gro', '100001', '1', '测试2', 'QPreUSwRXnLnMqsVubaUJil6FmWPTbjvLJf4RlqQ3LQ', '用户测试', '3333', '1', '<p>33333</p>', 'http://shabao.tunnel.qydev.com/wechat/article/10001003/detail', '2016-08-25 11:08:21');

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
-- Records of wx_qrcode
-- ----------------------------
INSERT INTO `wx_qrcode` VALUES ('1000020000', '测试', '1', '1', null, '1', '1', '1', null, '2016-08-26 23:34:31');
INSERT INTO `wx_qrcode` VALUES ('1000020007', '砍价兑换自动获取二维码', 'QR_SCENE', '1000017', null, '2592000', 'gQHh7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL296bVJWU2ZsMEFxcG5nTWhlQmZjAAIEd/nCVwMEAI0nAA==', 'http://weixin.qq.com/q/ozmRVSfl0AqpngMheBfc', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHh7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL296bVJWU2ZsMEFxcG5nTWhlQmZjAAIEd%2FnCVwMEAI0nAA%3D%3D', '2016-08-28 22:47:41');
INSERT INTO `wx_qrcode` VALUES ('1000020008', '砍价兑换自动获取二维码', 'QR_SCENE', '1000022', null, '2592000', 'gQGU8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1N6bUVVUi1sc2dyTFN1dFNiUmZjAAIEgj3MVwMEAI0nAA==', 'http://weixin.qq.com/q/SzmEUR-lsgrLSutSbRfc', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGU8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1N6bUVVUi1sc2dyTFN1dFNiUmZjAAIEgj3MVwMEAI0nAA%3D%3D', '2016-09-04 23:28:02');
INSERT INTO `wx_qrcode` VALUES ('1000020017', '砍价兑换自动获取二维码', 'QR_SCENE', '1000024', null, '2592000', 'gQHf8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JUbllqREhsN3dxV1h3Mk9NUmZjAAIE5tTMVwMEAI0nAA==', 'http://weixin.qq.com/q/rTnYjDHl7wqWXw2OMRfc', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHf8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JUbllqREhsN3dxV1h3Mk9NUmZjAAIE5tTMVwMEAI0nAA%3D%3D', '2016-09-05 10:13:58');
INSERT INTO `wx_qrcode` VALUES ('1000020018', '砍价兑换自动获取二维码', 'QR_SCENE', '1000032', null, '2592000', 'gQF58ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2RUbWxnNEhsNkFxUk9OWDdUQmZjAAIEt_HMVwMEAI0nAA==', 'http://weixin.qq.com/q/dTmlg4Hl6AqRONX7TBfc', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQF58ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2RUbWxnNEhsNkFxUk9OWDdUQmZjAAIEt_HMVwMEAI0nAA%3D%3D', '2016-09-05 11:08:39');
INSERT INTO `wx_qrcode` VALUES ('1000020019', '砍价兑换自动获取二维码', 'QR_SCENE', '1000032', null, '2592000', 'gQH47zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0dUa3ZZWGJsYkFvVlFia1h4aGZjAAIEzDQHWAMEAI0nAA==', 'http://weixin.qq.com/q/GTkvYXblbAoVQbkXxhfc', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQH47zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0dUa3ZZWGJsYkFvVlFia1h4aGZjAAIEzDQHWAMEAI0nAA%3D%3D', '2016-10-19 16:54:36');
INSERT INTO `wx_qrcode` VALUES ('1000020020', '砍价兑换自动获取二维码', 'QR_SCENE', '1000030', null, '2592000', 'gQG27zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVUJvek14bzJlZDIxbE1iSHhvMWkAAgRwvkNYAwQAjScA', 'http://weixin.qq.com/q/02UBozMxo2ed21lMbHxo1i', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG27zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVUJvek14bzJlZDIxbE1iSHhvMWkAAgRwvkNYAwQAjScA', '2016-12-04 14:57:52');
INSERT INTO `wx_qrcode` VALUES ('1000020021', '砍价兑换自动获取二维码', 'QR_SCENE', '1000032', null, '2592000', 'gQEx8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUGp1cU0xbzJlZDIxbDNtSE5vMXAAAgRDyUNYAwQAjScA', 'http://weixin.qq.com/q/02PjuqM1o2ed21l3mHNo1p', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEx8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUGp1cU0xbzJlZDIxbDNtSE5vMXAAAgRDyUNYAwQAjScA', '2016-12-04 15:44:03');

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
-- Records of wx_qrcode_rel
-- ----------------------------
INSERT INTO `wx_qrcode_rel` VALUES ('1000020000', '100001', 'bargain', '1');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020007', '100001', 'bargain', '1000017');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020008', '100001', 'bargain', '1000022');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020017', '100001', 'bargain', '1000024');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020018', '100001', 'bargain', '1000032');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020019', '100001', 'bargain', '1000032');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020020', '100001', 'bargain', '1000030');
INSERT INTO `wx_qrcode_rel` VALUES ('1000020021', '100001', 'bargain', '1000032');

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
-- Records of wx_return_message
-- ----------------------------
INSERT INTO `wx_return_message` VALUES ('1000010000', '100100', '100001', '测试', '2', null, null);
INSERT INTO `wx_return_message` VALUES ('1000010001', '100101', '100001', '兑奖', '2', null, null);

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
-- Records of wx_return_template
-- ----------------------------
INSERT INTO `wx_return_template` VALUES ('100100', '1', '测试', '111', null, null);
INSERT INTO `wx_return_template` VALUES ('100101', '1', '兑奖扫一扫', '<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07e34f9575809866&redirect_uri=http%3A%2F%2Fshabao.tunnel.qydev.com%2Fshabao-test%2Fwechat%2Fscanqr%2Fexchange%2Fbargain%2F10000%2Finit&response_type=code&scope=snsapi_base&state=100001#wechat_redirect\">点击这里兑奖</a>', null, null);

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
-- Records of wx_subscriber
-- ----------------------------
INSERT INTO `wx_subscriber` VALUES ('100000004', '1', '100001', 'oGMKNwnS1h3bcyKUu2WQsrFFNeZk', '1', null, '搁搁浅浅丶', 'http://wx.qlogo.cn/mmopen/sTrwpgskuqiaibMblEPFRXlMeSnpwD4qg1EBXqI2DbLQkw8m5SqTIW4gqbFR2YetdJY9nckBiakedY3qibIPkTSHbA/0', '/resources/wechat/upload/portrait/20160905/20160905110503_822.jpg', 'Valence', null, '2016-07-25 21:15:54', null, null, null);
INSERT INTO `wx_subscriber` VALUES ('100000005', '1', '100001', 'oGMKNwjuA6roAPJajXrkMPlA9ZG0', '0', null, '旧事⑨浓', 'http://wx.qlogo.cn/mmopen/OQKjftn2qHhibGwZQic3iazczIFLNP9qgVMZqmq195Y6iblnu8BJEbd1OpWVLU9XBSNsCK2JZhTRVOIaGQEjOCtyibKInUx2t1lUic/0', '/resources/wechat/upload/portrait/20160905/20160905110139_956.jpg', null, null, '2016-09-03 18:55:32', null, null, null);
INSERT INTO `wx_subscriber` VALUES ('100000007', '1', '100002', 'okGGDwuaAk8LlJy4ETZsJkgOHMiM', '1', null, '搁搁浅浅丶', 'http://wx.qlogo.cn/mmopen/3MF7fTylC8Viaz8XwU9nKSeWiaKicx1jNWEFufD26reWPAyoYiaeXicLzboUAp9PJ2kJmGg6cPRLDBjCCgJoCy3OEpw/0', '/resources/wechat/upload/portrait/20161006/20161006001431_101.jpg', 'Valence', null, '2016-10-06 00:13:39', null, null, null);
INSERT INTO `wx_subscriber` VALUES ('100000008', '1', '100002', 'okGGDwuO9003pV6T1BcF6sVpik1A', '0', null, '旧事⑨浓', 'http://wx.qlogo.cn/mmopen/01A4EyQI8ib6Q3lwPD4JOeZibQqHpnznngjaUqlw0baqTicHUF3PaIicOUPEt3w2kMOPg0nJJd6ic6hv6ngbVeIR2f3ZAcl35CU9ib/0', '/resources/wechat/upload/portrait/20161006/20161006001806_958.jpg', null, null, '2016-10-06 00:16:55', null, null, null);

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
-- Records of wx_vote
-- ----------------------------
INSERT INTO `wx_vote` VALUES ('10000001', '100001', 'blue', '0', '蓝色示例投票', '示例投票。蓝色主题', '测试', '2016-02-28 21:51:36', '2017-05-01 21:51:39', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443929&idx=1&sn=52625950d550d0952c81f0f2c27b2f75#rd', '12345678', '2016-02-28 21:52:02');
INSERT INTO `wx_vote` VALUES ('10000002', '100002', 'blue', '0', '蓝色示例投票', '示例投票。蓝色主题', '测试', '2016-02-28 21:51:36', '2017-05-01 21:51:39', 'http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443929&idx=1&sn=52625950d550d0952c81f0f2c27b2f75#rd', '12345678', '2016-02-28 21:52:02');

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
-- Records of wx_vote_count
-- ----------------------------
INSERT INTO `wx_vote_count` VALUES ('10000001', '7', '125', '117');
INSERT INTO `wx_vote_count` VALUES ('10000002', '1', '0', '24');

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
-- Records of wx_vote_image
-- ----------------------------
INSERT INTO `wx_vote_image` VALUES ('10000000', '10000001', '1', '1000000001', 'vote/551905a6ed64d.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000001', '10000001', '1', '1000000001', 'vote/551905a6ed64d.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000002', '10000001', '1', '1000000008', 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000003', '10000001', '1', '1000000009', 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000004', '10000001', '1', '1000000010', 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000005', '10000001', '1', '1000000011', 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000006', '10000001', '1', '1000000012', 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000007', '10000001', '1', '1000000013', 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000008', '10000001', '1', '1000000014', 'gJ7SLg4IxlPJ2j47xWi8ZmW2quM9GxM0XjVTWDxmTx-ismflXIMloAVcS_VfSUqV.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000009', '10000001', '1', '1000000015', '1');
INSERT INTO `wx_vote_image` VALUES ('10000010', '10000001', '1', '1000000016', '1');
INSERT INTO `wx_vote_image` VALUES ('10000011', '10000001', '1', '1000000017', '1');
INSERT INTO `wx_vote_image` VALUES ('10000012', '10000001', '1', '1000000018', '1');
INSERT INTO `wx_vote_image` VALUES ('10000013', '10000001', '1', '1000000019', '1');
INSERT INTO `wx_vote_image` VALUES ('10000014', '10000001', '1', '1000000020', '1');
INSERT INTO `wx_vote_image` VALUES ('10000015', '10000001', '1', '1000000021', '1');
INSERT INTO `wx_vote_image` VALUES ('10000016', '10000001', '1', '1000000022', '1');
INSERT INTO `wx_vote_image` VALUES ('10000017', '10000001', '1', '1000000023', '1');
INSERT INTO `wx_vote_image` VALUES ('10000018', '10000001', '1', '1000000024', '1');
INSERT INTO `wx_vote_image` VALUES ('10000019', '10000001', '1', '1000000025', 'yzDE1DWOiawAWjKzNEOCbsLFk6GQKb850OZwSmtmo4xDs2pf8REJd2JsaZTZLbRN.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000020', '10000001', '1', '1000000026', 'yzDE1DWOiawAWjKzNEOCbsLFk6GQKb850OZwSmtmo4xDs2pf8REJd2JsaZTZLbRN.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000021', '10000001', '1', '1000000027', 'j63cLCE2Ty3qF99iZLyVK4uFkOWXjQYHea04ckdzbrZrxhStlZwR5KEuAKsIx49T.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000022', '10000001', '1', '1000000028', 'j63cLCE2Ty3qF99iZLyVK4uFkOWXjQYHea04ckdzbrZrxhStlZwR5KEuAKsIx49T.jpg');
INSERT INTO `wx_vote_image` VALUES ('10000029', '10000002', '1', '1000000029', 'vote/559a9f4e41523.jpg');

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
-- Records of wx_vote_player
-- ----------------------------
INSERT INTO `wx_vote_player` VALUES ('1000000001', '10000001', '1', '顾襄', '0', '记得投我哦！', '1', 'vote/551905a6ed64d.jpg', '1372', '2016-02-28 21:54:01');
INSERT INTO `wx_vote_player` VALUES ('1000000002', '10000001', '1', '严妙怡', '0', '记得投我哦！', '2', 'vote/5519063dd8afc.jpg', '1276', '2016-02-28 21:58:43');
INSERT INTO `wx_vote_player` VALUES ('1000000003', '10000001', '1', '梅悠茗', '0', '记得投我哦！', '3', 'vote/557932daf1bf6.jpg', '955', '2016-02-28 22:02:07');
INSERT INTO `wx_vote_player` VALUES ('1000000004', '10000001', '1', '曾醉墨', '0', '记得投我哦！', '4', 'vote/5519067c08dda.jpg', '1122', '2016-02-28 22:03:14');
INSERT INTO `wx_vote_player` VALUES ('1000000005', '10000001', '1', '陈静嫕', '1348888597', '记得投我哦！', '5', 'vote/558cdaf8ce7d1.jpg', '856', '2016-02-28 22:04:58');
INSERT INTO `wx_vote_player` VALUES ('1000000006', '10000001', '1', '梅沁雪', '0', '记得投我哦！', '6', 'vote/557b8c778b217.jpg', '676', '2016-02-28 22:05:56');
INSERT INTO `wx_vote_player` VALUES ('1000000007', '10000001', '1', '斯雪佳', '0', '记得投我哦！', '7', 'vote/559a9f4e41523.jpg', '749', '2016-02-28 22:07:39');
INSERT INTO `wx_vote_player` VALUES ('1000000008', '10000001', '0', '测试', '13827442222', '123', null, 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg', '0', '2016-07-22 10:25:53');
INSERT INTO `wx_vote_player` VALUES ('1000000009', '10000001', '0', '测试', '13827442222', '123', null, 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg', '0', '2016-07-22 10:26:04');
INSERT INTO `wx_vote_player` VALUES ('1000000010', '10000001', '0', '测试', '13827442222', '123', null, 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg', '0', '2016-07-22 10:28:29');
INSERT INTO `wx_vote_player` VALUES ('1000000011', '10000001', '0', '测试', '13827442222', '123', null, 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg', '0', '2016-07-22 10:28:36');
INSERT INTO `wx_vote_player` VALUES ('1000000012', '10000001', '0', '测试', '13827442222', '123', null, 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg', '0', '2016-07-22 10:28:47');
INSERT INTO `wx_vote_player` VALUES ('1000000013', '10000001', '0', '测试', '13827442222', '123', null, 'kU2-8t0pj2B3V13VliIJTursMUvoq0oplkBA13rekJiBBWbHwGH-Z6CRgJF6ocad.jpg', '0', '2016-07-22 10:28:54');
INSERT INTO `wx_vote_player` VALUES ('1000000014', '10000001', '0', '测试1', '18686123836', '123', null, 'gJ7SLg4IxlPJ2j47xWi8ZmW2quM9GxM0XjVTWDxmTx-ismflXIMloAVcS_VfSUqV.jpg', '0', '2016-07-22 11:13:07');
INSERT INTO `wx_vote_player` VALUES ('1000000015', '10000001', '0', 'sff', '18686123836', 'ss', null, '1', '0', '2016-07-22 11:15:39');
INSERT INTO `wx_vote_player` VALUES ('1000000016', '10000001', '0', '11', '12332222333', '11', null, '1', '0', '2016-07-22 11:17:45');
INSERT INTO `wx_vote_player` VALUES ('1000000017', '10000001', '0', '11', '12332222333', '11', null, '1', '0', '2016-07-22 11:18:39');
INSERT INTO `wx_vote_player` VALUES ('1000000018', '10000001', '0', '11', '12332222333', '11', null, '1', '0', '2016-07-22 11:18:57');
INSERT INTO `wx_vote_player` VALUES ('1000000019', '10000001', '0', '111', '12324444555', '2222', null, '1', '0', '2016-07-22 11:19:55');
INSERT INTO `wx_vote_player` VALUES ('1000000020', '10000001', '0', '111', '12324444555', '2222', null, '1', '0', '2016-07-22 11:20:02');
INSERT INTO `wx_vote_player` VALUES ('1000000021', '10000001', '0', '111', '12324444555', '2222', null, '1', '0', '2016-07-22 11:20:11');
INSERT INTO `wx_vote_player` VALUES ('1000000022', '10000001', '0', 'ceshi', '12548888697', '111', null, '1', '0', '2016-07-22 11:21:39');
INSERT INTO `wx_vote_player` VALUES ('1000000023', '10000001', '0', 'ceshi', '12548888697', '111', null, '1', '0', '2016-07-22 11:21:49');
INSERT INTO `wx_vote_player` VALUES ('1000000024', '10000001', '0', '1223', '13547777444', '1', null, '1', '0', '2016-07-22 11:22:49');
INSERT INTO `wx_vote_player` VALUES ('1000000025', '10000001', '123', '0', '18686123836', '123', null, 'yzDE1DWOiawAWjKzNEOCbsLFk6GQKb850OZwSmtmo4xDs2pf8REJd2JsaZTZLbRN.jpg', '0', '2016-07-22 15:43:22');
INSERT INTO `wx_vote_player` VALUES ('1000000026', '10000001', '123', '0', '18686123836', '123', null, 'yzDE1DWOiawAWjKzNEOCbsLFk6GQKb850OZwSmtmo4xDs2pf8REJd2JsaZTZLbRN.jpg', '0', '2016-07-22 15:43:41');
INSERT INTO `wx_vote_player` VALUES ('1000000027', '10000001', '234', '1', '15623569696', '123', '8', 'j63cLCE2Ty3qF99iZLyVK4uFkOWXjQYHea04ckdzbrZrxhStlZwR5KEuAKsIx49T.jpg', '0', '2016-07-22 15:45:08');
INSERT INTO `wx_vote_player` VALUES ('1000000028', '10000001', '234', '1', '15623569696', '123', '9', 'j63cLCE2Ty3qF99iZLyVK4uFkOWXjQYHea04ckdzbrZrxhStlZwR5KEuAKsIx49T.jpg', '0', '2016-07-22 15:45:09');
INSERT INTO `wx_vote_player` VALUES ('1000000029', '10000002', '1', '顾襄', '15623569696', '记得投我哦！', '1', 'vote/559a9f4e41523.jpg', '0', '2016-02-28 21:54:01');

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

-- ----------------------------
-- Records of wx_vote_success
-- ----------------------------
INSERT INTO `wx_vote_success` VALUES ('1000001', '10000001', 'oGMKNwnS1h3bcyKUu2WQsrFFNeZk', '2016-07-26 10:09:22');
INSERT INTO `wx_vote_success` VALUES ('1000002', '10000001', 'oGMKNwjuA6roAPJajXrkMPlA9ZG0', '2016-12-04 14:58:14');
