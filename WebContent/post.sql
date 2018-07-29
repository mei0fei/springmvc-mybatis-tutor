/*
 Navicat MySQL Data Transfer

 Source Server         : no-password
 Source Server Version : 50626
 Source Host           : localhost
 Source Database       : test

 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 07/29/2018 11:32:33 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) NOT NULL,
  `dob` date NOT NULL,
  `blog_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `fk_post_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `post`
-- ----------------------------
BEGIN;
INSERT INTO `post` VALUES ('4', 'post 1', '2017-10-24', '1'), ('5', 'post 2', '2017-10-24', '1'), ('6', 'post 3', '2017-10-24', '1'), ('7', 'post 4', '2018-04-03', '2'), ('8', 'post 5', '2018-04-05', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
