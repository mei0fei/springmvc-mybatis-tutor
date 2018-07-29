/*
 Navicat MySQL Data Transfer

 Source Server         : no-password
 Source Server Version : 50626
 Source Host           : localhost
 Source Database       : test

 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 07/29/2018 11:32:41 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `author`
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `author`
-- ----------------------------
BEGIN;
INSERT INTO `author` VALUES ('1', 'joe', '123456'), ('3', 'smith', '123456');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
