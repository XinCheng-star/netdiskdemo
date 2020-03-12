/*
 Navicat Premium Data Transfer

 Source Server         : chengxin
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : testdatabase

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 12/03/2020 19:07:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_file_table
-- ----------------------------
DROP TABLE IF EXISTS `user_file_table`;
CREATE TABLE `user_file_table`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'account',
  `file_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名或者文件夹名字',
  `father_folder` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父文件夹的名字',
  `file_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型',
  `is_delete` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否删除',
  `file_check_code` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件的md5校验码，文件夹不需要求',
  `create_time` datetime(0) NOT NULL COMMENT '记录创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account`(`account`) USING BTREE COMMENT 'account的索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
