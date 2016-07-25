/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : huxin

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-25 17:35:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `friend_id` bigint(20) DEFAULT NULL,
  `set_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES ('1680225194548377595', '6458979467927438983', '6224667885346271744', '2016-07-25 16:39:21', '1', 'b8161');
INSERT INTO `t_friend` VALUES ('1998322895404092335', '3228894024801825522', '6224667885346271744', '2016-07-25 16:37:24', '1', 'a8160');
INSERT INTO `t_friend` VALUES ('2687599352087753640', '6224667885346271744', '3228894024801825522', '2016-07-25 16:37:45', '1', 'a8160');
INSERT INTO `t_friend` VALUES ('5039738939606940531', '2929592806447298066', '6224667885346271744', '2016-07-25 16:39:41', '1', '18363088162');
INSERT INTO `t_friend` VALUES ('5237742879971523758', '6224667885346271744', '6458979467927438983', '2016-07-25 17:07:59', '1', 'b8161');
INSERT INTO `t_friend` VALUES ('5635285676433032470', '6224667885346271744', '2929592806447298066', '2016-07-25 17:08:01', '1', '18363088162');
INSERT INTO `t_friend` VALUES ('5790041313269428786', '5927310680834096074', '6224667885346271744', '2016-07-25 17:11:57', '2', '18363088164');
INSERT INTO `t_friend` VALUES ('6238069491534876587', '6224667885346271744', '210191708330543836', '2016-07-25 17:04:31', '2', '啦啦啦');
INSERT INTO `t_friend` VALUES ('6461017196358319686', '6224667885346271744', '6224667885346271000', '2016-07-25 13:52:45', '2', '啦啦啦');

-- ----------------------------
-- Table structure for t_friend_book
-- ----------------------------
DROP TABLE IF EXISTS `t_friend_book`;
CREATE TABLE `t_friend_book` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_friend_book
-- ----------------------------
