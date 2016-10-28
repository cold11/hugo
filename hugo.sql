/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : hugo

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-10-06 14:46:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  `is_disable` int(11) DEFAULT NULL,
  `modify_id` bigint(20) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  `role_key` varchar(30) NOT NULL,
  `role_name` varchar(30) NOT NULL,
  `role_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '2016-10-04 15:08:52', '0', '0', null, '2016-10-04 15:09:09', null, null, 'ROLE_EDITOR', '编辑', '1');
INSERT INTO `sys_role` VALUES ('2', null, '2016-10-04 15:11:33', '0', '0', null, '2016-10-04 15:11:39', null, null, 'ROLE_AUTHOR', '作者/译者', '2');
INSERT INTO `sys_role` VALUES ('3', null, '2016-10-04 15:12:09', '0', '0', null, '2016-10-04 15:12:14', null, null, 'ROLE_ANGET', '版权代理', '3');
INSERT INTO `sys_role` VALUES ('4', null, '2016-10-04 15:13:33', '0', '0', null, '2016-10-04 15:13:39', null, null, 'ROLE_TRANS', '翻译团队/翻译员', '4');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `disable_date` datetime DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `is_activate` int(11) DEFAULT NULL,
  `is_check` int(11) DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  `is_disable` int(11) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_ip` varchar(36) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `personal_intro` text,
  `phone` varchar(30) NOT NULL,
  `postscript` longtext,
  `release_agency` varchar(255) DEFAULT NULL,
  `release_company` varchar(255) DEFAULT NULL,
  `release_role` int(11) DEFAULT NULL,
  `sign_up_code` varchar(255) DEFAULT NULL,
  `translation_type` varchar(255) DEFAULT NULL,
  `translator_count` int(11) DEFAULT NULL,
  `translator_type` int(11) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `modify_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_6x015sw6amliwwegrhax3sdg3` (`modify_id`),
  KEY `FK_70i78v8fshnlpqsalaqhgljts` (`create_id`),
  CONSTRAINT `FK_6x015sw6amliwwegrhax3sdg3` FOREIGN KEY (`modify_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_70i78v8fshnlpqsalaqhgljts` FOREIGN KEY (`create_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', '2016-10-06 14:44:06', null, 'cofd@163.com', 'D:\\软件开发\\ideaProject\\hugo\\target\\hugo\\upload\\13678909878\\2016\\10\\06\\1475736246193.jpg', '1', '1', '0', '0', 'yuzhonf ', '2016-10-06 14:44:06', '127.0.0.1', '2016-10-06 14:44:06', 'qwer', '96E79218965EB72C92A549DD5A330112', null, '13678909878', null, null, null, null, null, 'asd', '8', '2', 'test1', null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `FK_fethvr269t6stivlddbo5pxry` (`user_id`),
  KEY `FK_fxu3td9m5o7qov1kbdvmn0g0x` (`role_id`),
  CONSTRAINT `FK_fethvr269t6stivlddbo5pxry` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_fxu3td9m5o7qov1kbdvmn0g0x` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('5', '4', '5');
