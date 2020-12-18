/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : advertisement

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 18/12/2020 17:19:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qr_code
-- ----------------------------
DROP TABLE IF EXISTS `qr_code`;
CREATE TABLE `qr_code`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(6) UNSIGNED NOT NULL DEFAULT 0,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qr_code
-- ----------------------------
INSERT INTO `qr_code` VALUES ('1aa0c9f63cf0499f98bccca9831d16e0', 0, '78', 'http://78', 'D:\\page\\78.jpg');
INSERT INTO `qr_code` VALUES ('1aa3d7c5f0684bd482fb70057a69c8e7', 0, 'kjljk', 'http://jkjk', 'opt\\page\\kjljk.jpg');
INSERT INTO `qr_code` VALUES ('23845b8380c245d98fad2ff7a13b7c80', 0, '34', 'http://34', 'D:\\page\\34.jpg');
INSERT INTO `qr_code` VALUES ('2cf350d6b2b447e6bf00219d639edc78', 0, '45', 'http://45', 'D:\\page\\45.jpg');
INSERT INTO `qr_code` VALUES ('513360ad62f14f71a9788e4f65aaaf5f', 0, '45', 'http://45', 'D:\\page\\45.jpg');
INSERT INTO `qr_code` VALUES ('6601421eaccc4fe6b1c7d57a000f1d7a', 0, '23', 'http://23', 'D:\\page\\23.jpg');
INSERT INTO `qr_code` VALUES ('6cca1d1700934853b790255fb602da73', 0, 'qweqwe', 'http://eqeqwe', 'opt\\page\\qweqwe.jpg');
INSERT INTO `qr_code` VALUES ('83c08031bab548e18851a6eb247b792d', 0, 'baidu', 'http://www.baidu.com', 'D:\\page\\baidu.jpg');
INSERT INTO `qr_code` VALUES ('bb1ef131bcd646dfbcb24a912739e51a', 0, '百度', 'www.baidu.com', 'D:\\a\\b\\c.jpg');
INSERT INTO `qr_code` VALUES ('be5daff25f3740ebb0e0b54a977c8951', 0, '34', 'http://34', 'D:\\page\\34.jpg');
INSERT INTO `qr_code` VALUES ('e5845032b6f3417b81bbde8fa8ee1c05', 0, '12', 'http://12', 'D:\\page\\12.jpg');
INSERT INTO `qr_code` VALUES ('fdc37179006346e1a7f6f553f7daadf6', 0, '123123', 'http://请问23', 'opt\\page\\123123.jpg');
INSERT INTO `qr_code` VALUES ('ffa022f5a4584d89b4d6f02629d7ca71', 0, '89', 'http://89', 'D:\\page\\89.jpg');

SET FOREIGN_KEY_CHECKS = 1;
