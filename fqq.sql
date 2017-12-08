/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : fqq

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-12-08 19:48:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friendship
-- ----------------------------
DROP TABLE IF EXISTS `friendship`;
CREATE TABLE `friendship` (
  `friendshipId` int(11) NOT NULL AUTO_INCREMENT,
  `fUserId` int(11) DEFAULT NULL,
  `sUserId` int(11) DEFAULT NULL,
  `setUpTime` datetime DEFAULT NULL,
  PRIMARY KEY (`friendshipId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendship
-- ----------------------------
INSERT INTO `friendship` VALUES ('2', '499587417', '499587414', '2017-11-20 22:32:09');
INSERT INTO `friendship` VALUES ('3', '499587417', '499587413', '2017-11-24 20:50:15');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(255) DEFAULT NULL,
  `sendUserId` int(11) DEFAULT NULL,
  `recvUserId` int(11) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '你好', '499587417', '499587414', '2017-12-08 13:59:08');
INSERT INTO `message` VALUES ('2', 'hi', '499587414', '499587417', '2017-12-08 13:59:24');
INSERT INTO `message` VALUES ('3', '你好，我是小四', '499587414', '499587417', '2017-12-08 14:04:41');
INSERT INTO `message` VALUES ('4', '你好，我是小七', '499587417', '499587414', '2017-12-08 14:04:57');
INSERT INTO `message` VALUES ('5', '你好，我是小七', '499587417', '499587414', '2017-12-08 14:05:32');
INSERT INTO `message` VALUES ('6', '123', '499587414', '499587417', '2017-12-08 14:06:00');
INSERT INTO `message` VALUES ('7', '哈哈哈', '499587417', '499587414', '2017-12-08 14:06:08');
INSERT INTO `message` VALUES ('8', '用Microsoft Edge一直掉线', '499587414', '499587417', '2017-12-08 14:08:15');
INSERT INTO `message` VALUES ('9', '垃圾，用Chrome吧', '499587417', '499587414', '2017-12-08 14:08:27');
INSERT INTO `message` VALUES ('10', '你几号', '499587417', '499587414', '2017-12-08 14:16:27');
INSERT INTO `message` VALUES ('11', '4号', '499587414', '499587417', '2017-12-08 14:16:34');
INSERT INTO `message` VALUES ('12', '我七号', '499587417', '499587414', '2017-12-08 14:16:40');
INSERT INTO `message` VALUES ('13', '收到', '499587414', '499587417', '2017-12-08 14:16:45');
INSERT INTO `message` VALUES ('14', '123', '499587414', '499587417', '2017-12-08 14:59:39');
INSERT INTO `message` VALUES ('15', 'ceshi', '499587417', '499587414', '2017-12-08 19:29:13');
INSERT INTO `message` VALUES ('16', '123', '499587414', '499587417', '2017-12-08 19:29:18');
INSERT INTO `message` VALUES ('17', '你好啊', '499587417', '499587414', '2017-12-08 19:29:25');
INSERT INTO `message` VALUES ('18', '你好', '499587414', '499587417', '2017-12-08 19:29:29');
INSERT INTO `message` VALUES ('19', '哈哈哈', '499587417', '499587414', '2017-12-08 19:29:35');
INSERT INTO `message` VALUES ('20', '真彩', '499587417', '499587414', '2017-12-08 19:29:42');
INSERT INTO `message` VALUES ('21', '呜呜呜', '499587414', '499587417', '2017-12-08 19:29:46');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `registerTime` datetime DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `isOnline` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('499587410', '123456', '2017-11-20 20:44:52', '2017-11-20 20:44:57', '0');
INSERT INTO `user` VALUES ('499587412', '123456', '2017-11-14 20:27:22', null, '0');
INSERT INTO `user` VALUES ('499587413', '123456', '2017-11-14 19:49:38', null, '0');
INSERT INTO `user` VALUES ('499587414', '123456', '2017-11-10 21:51:35', '2017-12-08 19:29:02', '1');
INSERT INTO `user` VALUES ('499587417', '123456', '2017-11-07 22:20:16', '2017-12-08 19:28:32', '1');
