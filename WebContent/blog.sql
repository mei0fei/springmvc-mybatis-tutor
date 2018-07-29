/*
 Navicat MySQL Data Transfer

 Source Server         : no-password
 Source Server Version : 50626
 Source Host           : localhost
 Source Database       : test

 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 07/29/2018 11:32:22 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `dob` date NOT NULL,
  `state` varchar(20) NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `fk_blog_author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `blog`
-- ----------------------------
BEGIN;
INSERT INTO `blog` VALUES ('1', 'a blog', 'blog content', '2017-10-24', 'active', '1'), ('2', 'a blog 2', 'a blog 2 cotent', '2018-04-03', 'active', '1'), ('4', 'a blog 4', 'blog content 4', '2017-10-24', 'active', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
