/*
 Navicat Premium Data Transfer

 Source Server         : share_linux
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 112.74.165.55:3306
 Source Schema         : shareshop

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/07/2019 10:22:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_check
-- ----------------------------
DROP TABLE IF EXISTS `account_check`;
CREATE TABLE `account_check`  (
  `account_check_id` int(11) NOT NULL AUTO_INCREMENT,
  `third_party_no` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `transaction_code` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `money` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `type` tinyint(4) NOT NULL,
  `correlation_id` int(11) NOT NULL,
  `notes` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` tinyint(4) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`account_check_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `city_id` int(11) NOT NULL,
  `city` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `state_id` int(11) NOT NULL,
  PRIMARY KEY (`city_id`) USING BTREE,
  INDEX `state_id`(`state_id`) USING BTREE,
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'Montgomery', 1);

-- ----------------------------
-- Table structure for coupon_info
-- ----------------------------
DROP TABLE IF EXISTS `coupon_info`;
CREATE TABLE `coupon_info`  (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `coupon_money` decimal(10, 2) NULL DEFAULT NULL,
  `full_money` decimal(10, 2) NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `remaining_quantity` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT 0,
  `status` tinyint(4) NULL DEFAULT NULL,
  `category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`coupon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon_info
-- ----------------------------
INSERT INTO `coupon_info` VALUES (1, 'coupon', 0, 20.00, 100.00, '', '2019-03-14 17:19:48', '2019-05-05 17:19:53', 1000, 784, '2019-03-06 17:20:10', 1, 1, 'hat', 'Used to describe');
INSERT INTO `coupon_info` VALUES (2, 'coupon', 1, 20.00, 100.00, NULL, '2019-03-10 17:25:20', '2019-03-17 17:25:27', 500, 93, '2019-03-13 17:28:55', 0, 1, 'all', 'Used to describe');
INSERT INTO `coupon_info` VALUES (3, 'coupon', 0, 2.00, 30.00, '', '2019-03-12 09:46:28', '2019-05-11 20:39:53', 1000, 1000, '2019-03-17 17:59:38', 3, -1, 'shoe', 'Used to describe');
INSERT INTO `coupon_info` VALUES (4, 'coupon', 0, 50.00, 300.00, '', '2019-03-14 17:19:48', '2019-03-19 17:19:53', 1000, 999, '2019-03-21 14:53:54', 1, 1, 'hat', 'Used to describe');

-- ----------------------------
-- Table structure for coupon_logs
-- ----------------------------
DROP TABLE IF EXISTS `coupon_logs`;
CREATE TABLE `coupon_logs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `coupon_id` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `order_number`(`order_id`) USING BTREE,
  INDEX `coupon_id`(`coupon_id`) USING BTREE,
  CONSTRAINT `coupon_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_logs_ibfk_4` FOREIGN KEY (`coupon_id`) REFERENCES `coupon_info` (`coupon_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_logs_ibfk_5` FOREIGN KEY (`order_id`) REFERENCES `order_master` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon_logs
-- ----------------------------
INSERT INTO `coupon_logs` VALUES (1, 00000000001, 1, 1, '2019-03-13 17:31:30', 1);

-- ----------------------------
-- Table structure for coupon_receive
-- ----------------------------
DROP TABLE IF EXISTS `coupon_receive`;
CREATE TABLE `coupon_receive`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `coupon_id` int(11) NULL DEFAULT NULL,
  `receive_time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `coupon_id`(`coupon_id`) USING BTREE,
  CONSTRAINT `coupon_receive_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_receive_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon_info` (`coupon_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon_receive
-- ----------------------------
INSERT INTO `coupon_receive` VALUES (1, 00000000001, 1, '2019-03-11 17:30:20', 1);
INSERT INTO `coupon_receive` VALUES (2, 00000000079, 4, '2019-05-27 14:40:09', 1);
INSERT INTO `coupon_receive` VALUES (3, 00000000078, 1, '2019-06-03 10:36:05', 1);
INSERT INTO `coupon_receive` VALUES (4, 00000000078, 1, '2019-06-03 10:36:09', 1);
INSERT INTO `coupon_receive` VALUES (5, 00000000078, 1, '2019-06-03 10:36:10', 1);
INSERT INTO `coupon_receive` VALUES (6, 00000000078, 2, '2019-06-03 10:36:17', 1);
INSERT INTO `coupon_receive` VALUES (7, 00000000078, 1, '2019-06-03 11:17:27', 1);
INSERT INTO `coupon_receive` VALUES (8, 00000000078, 1, '2019-06-03 11:26:16', 1);
INSERT INTO `coupon_receive` VALUES (9, 00000000078, 1, '2019-06-03 11:27:50', 1);
INSERT INTO `coupon_receive` VALUES (10, 00000000078, 1, '2019-06-03 11:28:09', 1);
INSERT INTO `coupon_receive` VALUES (11, 00000000078, 2, '2019-06-03 11:28:11', 1);
INSERT INTO `coupon_receive` VALUES (12, 00000000078, 2, '2019-06-03 11:28:12', 1);
INSERT INTO `coupon_receive` VALUES (13, 00000000078, 1, '2019-06-03 11:29:24', 1);
INSERT INTO `coupon_receive` VALUES (14, 00000000078, 1, '2019-06-03 11:40:36', 1);
INSERT INTO `coupon_receive` VALUES (15, 00000000078, 1, '2019-06-03 11:41:03', 1);
INSERT INTO `coupon_receive` VALUES (16, 00000000078, 1, '2019-06-03 15:50:08', 1);
INSERT INTO `coupon_receive` VALUES (17, 00000000078, 2, '2019-06-03 16:10:50', 1);
INSERT INTO `coupon_receive` VALUES (18, 00000000078, 2, '2019-06-03 16:11:27', 1);
INSERT INTO `coupon_receive` VALUES (19, 00000000078, 4, '2019-06-03 16:11:41', 1);
INSERT INTO `coupon_receive` VALUES (20, 00000000078, 2, '2019-06-03 17:02:06', 1);
INSERT INTO `coupon_receive` VALUES (21, 00000000079, 4, '2019-06-03 18:22:59', 1);
INSERT INTO `coupon_receive` VALUES (22, 00000000079, 4, '2019-06-03 18:29:46', 1);
INSERT INTO `coupon_receive` VALUES (23, 00000000079, 4, '2019-06-03 18:32:35', 1);
INSERT INTO `coupon_receive` VALUES (24, 00000000079, 4, '2019-06-03 18:37:17', 1);
INSERT INTO `coupon_receive` VALUES (25, 00000000079, 4, '2019-06-03 18:39:24', 1);
INSERT INTO `coupon_receive` VALUES (26, 00000000085, 1, '2019-06-17 19:33:07', 1);
INSERT INTO `coupon_receive` VALUES (27, 00000000085, 2, '2019-06-17 19:33:11', 1);
INSERT INTO `coupon_receive` VALUES (28, 00000000085, 1, '2019-06-17 19:33:28', 1);
INSERT INTO `coupon_receive` VALUES (29, 00000000085, 1, '2019-06-17 19:33:56', 1);
INSERT INTO `coupon_receive` VALUES (30, 00000000085, 2, '2019-06-17 20:55:14', 1);
INSERT INTO `coupon_receive` VALUES (31, 00000000079, 2, '2019-06-17 22:32:37', 1);
INSERT INTO `coupon_receive` VALUES (32, 00000000079, 1, '2019-06-17 22:32:57', 1);
INSERT INTO `coupon_receive` VALUES (33, 00000000085, 2, '2019-07-01 12:20:17', 1);
INSERT INTO `coupon_receive` VALUES (38, 00000000084, 2, '2019-07-01 15:19:47', 1);
INSERT INTO `coupon_receive` VALUES (46, 00000000085, 1, '2019-07-10 16:08:32', 1);
INSERT INTO `coupon_receive` VALUES (47, 00000000085, 1, '2019-07-10 16:08:35', 1);
INSERT INTO `coupon_receive` VALUES (48, 00000000085, 1, '2019-07-10 16:08:35', 1);
INSERT INTO `coupon_receive` VALUES (49, 00000000085, 1, '2019-07-10 16:09:11', 1);
INSERT INTO `coupon_receive` VALUES (50, 00000000085, 1, '2019-07-10 16:09:11', 1);
INSERT INTO `coupon_receive` VALUES (51, 00000000085, 1, '2019-07-10 16:09:12', 1);
INSERT INTO `coupon_receive` VALUES (52, 00000000088, 1, '2019-07-10 18:47:02', 1);
INSERT INTO `coupon_receive` VALUES (53, 00000000088, 1, '2019-07-11 10:02:07', 1);
INSERT INTO `coupon_receive` VALUES (54, 00000000088, 2, '2019-07-11 10:02:08', 1);
INSERT INTO `coupon_receive` VALUES (55, 00000000088, 2, '2019-07-11 10:02:11', 1);
INSERT INTO `coupon_receive` VALUES (56, 00000000088, 2, '2019-07-11 11:21:26', 1);
INSERT INTO `coupon_receive` VALUES (57, 00000000088, 1, '2019-07-13 22:36:51', 1);

-- ----------------------------
-- Table structure for manager_category
-- ----------------------------
DROP TABLE IF EXISTS `manager_category`;
CREATE TABLE `manager_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_category
-- ----------------------------
INSERT INTO `manager_category` VALUES (1, 11, 'admin', 'Manage commodity information');
INSERT INTO `manager_category` VALUES (4, 11, 'accaa', NULL);
INSERT INTO `manager_category` VALUES (7, 20, 'accaa', NULL);
INSERT INTO `manager_category` VALUES (8, 20, 'accaa', NULL);

-- ----------------------------
-- Table structure for manager_info
-- ----------------------------
DROP TABLE IF EXISTS `manager_info`;
CREATE TABLE `manager_info`  (
  `manager_info_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identity_card_type` tinyint(4) NULL DEFAULT 1,
  `identity_card_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `register_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`manager_info_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `manager_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_info
-- ----------------------------
INSERT INTO `manager_info` VALUES (00000000001, 5, 'Mike', 1, '3478374', '180765423877878', '12@gmain.com', 'male', '1993-07-16 20:30:19', '2019-02-19 20:30:36');
INSERT INTO `manager_info` VALUES (00000000002, 6, 'Ma', 1, '3478374', '18076542387', '12@gmain.com', 'male', '1993-07-16 20:30:19', '2019-03-11 10:35:45');
INSERT INTO `manager_info` VALUES (00000000003, 66, 'Ma', 0, '347837452224', '18076542387', '12@gmain.com', 'male', '1993-07-16 20:30:19', '2019-04-23 19:12:03');
INSERT INTO `manager_info` VALUES (00000000004, 67, 'Ma2', 1, '347837452224', '18076542387', '12@gmain.com', 'male', '1993-07-16 20:30:19', '2019-04-23 19:14:13');
INSERT INTO `manager_info` VALUES (00000000005, 82, 'chenManage', 1, '347837452224', '18076542387', '12@gmain.com', 'male', '1993-07-16 20:30:19', '2019-05-13 15:16:39');

-- ----------------------------
-- Table structure for order_cart
-- ----------------------------
DROP TABLE IF EXISTS `order_cart`;
CREATE TABLE `order_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  `product_specs_id` int(11) NULL DEFAULT NULL,
  `product_quantity` int(11) NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `product_specs_id`(`product_specs_id`) USING BTREE,
  CONSTRAINT `order_cart_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_cart_ibfk_4` FOREIGN KEY (`product_specs_id`) REFERENCES `product_specs` (`product_specs_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_cart
-- ----------------------------
INSERT INTO `order_cart` VALUES (1, 1, 79, 1, 3, '2019-03-08 21:31:22');
INSERT INTO `order_cart` VALUES (2, 1, 1, 2, 2, '2019-03-09 21:31:36');
INSERT INTO `order_cart` VALUES (4, 1, 84, 1, 8, '2019-06-19 20:40:30');
INSERT INTO `order_cart` VALUES (5, 1, 85, 1, 67, '2019-06-20 11:44:25');
INSERT INTO `order_cart` VALUES (6, 3, 85, 1, 7, '2019-06-20 20:34:04');
INSERT INTO `order_cart` VALUES (7, 2, 85, 1, 17, '2019-06-24 09:18:44');
INSERT INTO `order_cart` VALUES (8, 9, 85, 1, 1, '2019-06-24 14:29:05');
INSERT INTO `order_cart` VALUES (9, 6, 84, 1, 1, '2019-06-24 14:30:59');
INSERT INTO `order_cart` VALUES (10, 20, 84, 1, 1, '2019-06-24 14:45:01');
INSERT INTO `order_cart` VALUES (11, 2, 84, 1, 6, '2019-06-24 21:51:58');
INSERT INTO `order_cart` VALUES (12, 3, 84, 1, 6, '2019-06-27 22:01:03');
INSERT INTO `order_cart` VALUES (13, 8, 85, 1, 1, '2019-06-29 11:35:22');
INSERT INTO `order_cart` VALUES (14, 11, 84, 1, 1, '2019-06-30 19:27:33');
INSERT INTO `order_cart` VALUES (15, 20, 87, 1, 1, '2019-07-03 17:48:36');
INSERT INTO `order_cart` VALUES (19, 1, 88, 1, 6, '2019-07-11 10:02:18');
INSERT INTO `order_cart` VALUES (20, 2, 88, 1, 5, '2019-07-11 10:02:45');
INSERT INTO `order_cart` VALUES (21, 4, 85, 1, 1, '2019-07-11 18:13:38');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `product_specs_id` int(11) NULL DEFAULT NULL,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_quantity` int(11) NULL DEFAULT NULL,
  `product_price` decimal(10, 2) NULL DEFAULT NULL,
  `actual_paid_money` decimal(10, 2) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`order_detail_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `product_specs_id`(`product_specs_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_master` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_specs_id`) REFERENCES `product_specs` (`product_specs_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_detail_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1, 1, 1, 1, 'bucket hat', 2, 7.00, 14.00, 5);
INSERT INTO `order_detail` VALUES (2, 1, 1, 2, 'bucket hat', 4, 8.00, 32.00, 5);
INSERT INTO `order_detail` VALUES (3, 2, 1, 1, 'bucket hat', 2, 7.00, 14.00, 5);
INSERT INTO `order_detail` VALUES (4, 3, 1, 1, 'bucket hat', 1, 7.00, 7.00, 5);
INSERT INTO `order_detail` VALUES (5, 4, 2, 1, 'fashion hat', 2, 10.00, 20.00, 5);
INSERT INTO `order_detail` VALUES (6, 5, 3, 1, 'peaked hat', 1, 28.00, 28.00, 4);

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` bigint(20) NOT NULL,
  `user_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `consignee_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consignee_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postal_code` int(11) NULL DEFAULT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `second_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_mode` tinyint(4) NULL DEFAULT NULL,
  `order_moeny` decimal(10, 2) NULL DEFAULT NULL,
  `discount_money` decimal(10, 2) NULL DEFAULT 0.00,
  `shipping_money` decimal(10, 2) NULL DEFAULT NULL,
  `payment_money` decimal(10, 2) NULL DEFAULT NULL,
  `tax_money` decimal(10, 2) NULL DEFAULT NULL,
  `express_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `pay_time` datetime(0) NULL DEFAULT NULL,
  `shipping_time` datetime(0) NULL DEFAULT NULL,
  `receive_time` datetime(0) NULL DEFAULT NULL,
  `order_status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `order_number`(`order_number`) USING BTREE,
  CONSTRAINT `order_master_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES (1, 201904131615342865, 00000000084, 'luyy1', '13087342876', 32322, 'Alabama', 'Montgomery', 'The first street', '', 1, 46.00, 0.00, 1.00, 48.96, 1.96, '563782112', '2019-04-02 17:38:30', '2019-04-02 17:38:54', '2019-04-03 10:47:18', '2019-04-05 20:47:18', 5);
INSERT INTO `order_master` VALUES (2, 201904131615632833, 00000000084, 'luyy1', '13087342876', 32322, 'Alabama', 'Montgomery', 'The first street', NULL, 1, 14.00, 0.00, 1.00, 15.95, 0.95, '563873553', '2019-04-03 00:00:10', '2019-04-03 00:00:39', '2019-04-03 13:00:10', '2019-04-07 13:00:10', 5);
INSERT INTO `order_master` VALUES (3, 201904141619075112, 00000000084, 'luyy1', '13087342876', 32322, 'Alabama', 'Montgomery', 'The first street', NULL, 1, 7.00, 0.00, 1.00, 8.54, 0.54, '568974631', '2019-04-10 00:00:18', '2019-04-10 00:00:47', '2019-04-10 13:00:18', '2019-04-15 13:00:18', 5);
INSERT INTO `order_master` VALUES (4, 201904141653175313, 00000000085, 'luyy2', '13178651298', 43432, 'Alabama', 'Montgomery', 'The first street', NULL, 1, 10.00, 0.00, 2.00, 12.82, 0.82, '563673467', '2019-04-20 00:00:32', '2019-04-20 00:00:45', '2019-04-20 10:00:32', '2019-04-23 11:09:32', 5);
INSERT INTO `order_master` VALUES (5, 201905091615592819, 00000000085, 'luyy2', '13178655641', 43432, 'Alabama', 'Montgomery', 'The first street', NULL, 1, 28.00, 0.00, 2.00, 31.52, 1.52, '563434224', '2019-05-09 16:15:59', '2019-05-09 16:16:14', '2019-05-10 12:15:59', NULL, 4);

-- ----------------------------
-- Table structure for product_attribute_key
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute_key`;
CREATE TABLE `product_attribute_key`  (
  `key_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NULL DEFAULT NULL,
  `attr_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_sort` tinyint(4) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`key_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attribute_key
-- ----------------------------
INSERT INTO `product_attribute_key` VALUES (1, 5, 'color', 1, 1);
INSERT INTO `product_attribute_key` VALUES (2, 5, 'size', 2, 1);
INSERT INTO `product_attribute_key` VALUES (3, 5, 'weight', 3, 1);
INSERT INTO `product_attribute_key` VALUES (4, 6, 'color', 1, 1);
INSERT INTO `product_attribute_key` VALUES (5, 9, 'size', 2, 1);
INSERT INTO `product_attribute_key` VALUES (6, 9, 'color', 1, 1);

-- ----------------------------
-- Table structure for product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute_value`;
CREATE TABLE `product_attribute_value`  (
  `value_id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_key_id` int(11) NOT NULL,
  `attr_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value_sort` tinyint(4) NULL DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`value_id`) USING BTREE,
  INDEX `attr_key_id`(`attr_key_id`) USING BTREE,
  CONSTRAINT `product_attribute_value_ibfk_1` FOREIGN KEY (`attr_key_id`) REFERENCES `product_attribute_key` (`key_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attribute_value
-- ----------------------------
INSERT INTO `product_attribute_value` VALUES (1, 1, 'red', 1, 1);
INSERT INTO `product_attribute_value` VALUES (2, 1, 'black', 2, 1);
INSERT INTO `product_attribute_value` VALUES (3, 1, 'green', 3, 1);
INSERT INTO `product_attribute_value` VALUES (4, 1, 'yello', 4, 1);
INSERT INTO `product_attribute_value` VALUES (5, 2, 'M', 1, 1);
INSERT INTO `product_attribute_value` VALUES (6, 2, 'L', 2, 1);
INSERT INTO `product_attribute_value` VALUES (7, 2, 'XL', 3, 1);
INSERT INTO `product_attribute_value` VALUES (8, 3, '30g', 1, 1);
INSERT INTO `product_attribute_value` VALUES (9, 3, '38g', 2, 1);
INSERT INTO `product_attribute_value` VALUES (10, 4, 'red', 1, 1);
INSERT INTO `product_attribute_value` VALUES (11, 4, 'white', 2, 1);
INSERT INTO `product_attribute_value` VALUES (12, 5, '170cm', 1, 1);
INSERT INTO `product_attribute_value` VALUES (13, 5, '180cm', 2, 1);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT 0,
  `category_level` tinyint(4) UNSIGNED NULL DEFAULT NULL,
  `category_status` tinyint(4) NULL DEFAULT 1,
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (0, 'all', NULL, 0, 0, 1, NULL);
INSERT INTO `product_category` VALUES (1, 'hat', '6501', 0, 1, 1, 'http://112.74.165.55/classify/01.jpg');
INSERT INTO `product_category` VALUES (2, 'scarf', '6502', 0, 1, 1, 'http://112.74.165.55/classify/02.jpg');
INSERT INTO `product_category` VALUES (3, 'shoe', '6503', 0, 1, 1, 'http://112.74.165.55/classify/03.jpg');
INSERT INTO `product_category` VALUES (4, 'clothes', '6504', 0, 1, 1, 'http://112.74.165.55/classify/04.jpg');
INSERT INTO `product_category` VALUES (5, 'bag', '6505', 0, 1, 1, 'http://112.74.165.55/classify/05.jpg');
INSERT INTO `product_category` VALUES (6, 'computer', '6506', 0, 1, 1, 'http://112.74.165.55/classify/06.jpg');
INSERT INTO `product_category` VALUES (7, 'camera ', '6507', 0, 1, 1, 'http://112.74.165.55/classify/07.jpg');
INSERT INTO `product_category` VALUES (8, 'phone', '6508', 0, 1, 1, 'http://112.74.165.55/classify/08.jpg');
INSERT INTO `product_category` VALUES (9, 'television', '6509', 0, 1, 1, 'http://112.74.165.55/classify/09.jpg');
INSERT INTO `product_category` VALUES (10, 'watch', '6510', 0, 1, 1, 'http://112.74.165.55/classify/10.jpg');
INSERT INTO `product_category` VALUES (11, 'bowler hat', '650101', 1, 2, 1, 'http://112.74.165.55/hat/111.jpg');
INSERT INTO `product_category` VALUES (12, 'fedora hat', '650102', 1, 2, 1, 'http://112.74.165.55/hat/112.jpg');
INSERT INTO `product_category` VALUES (13, 'baseball hat ', '650103', 1, 2, 1, 'http://112.74.165.55/hat/113.jpg');
INSERT INTO `product_category` VALUES (14, 'silk hat ', '650104', 1, 2, 1, 'http://112.74.165.55/hat/114.jpg');
INSERT INTO `product_category` VALUES (15, 'cashmere scarf', '650201', 2, 2, 1, 'http://112.74.165.55/scarf/211.jpg');
INSERT INTO `product_category` VALUES (16, 'silk scarf ', '650202', 2, 2, 1, 'http://112.74.165.55/scarf/212.jpg');

-- ----------------------------
-- Table structure for product_comment
-- ----------------------------
DROP TABLE IF EXISTS `product_comment`;
CREATE TABLE `product_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `order_detail_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `second_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` tinyint(4) NULL DEFAULT NULL,
  `comment_time` datetime(0) NULL DEFAULT NULL,
  `audit_status` tinyint(4) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `order_id`(`order_detail_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `product_comment_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_comment_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_comment_ibfk_4` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`order_detail_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_comment
-- ----------------------------
INSERT INTO `product_comment` VALUES (1, 1, 1, 1, 'hat', 'good good', 'I like it ,perfect', 5, '2019-03-02 17:33:52', 1);
INSERT INTO `product_comment` VALUES (2, 1, 1, 1, 'hat22', 'dd', 'fdfdff', 5, '2019-03-02 17:34:45', 1);
INSERT INTO `product_comment` VALUES (3, 1, 1, 1, 'hfsj', 'ordinary', '', 4, '2019-03-02 18:07:17', 1);
INSERT INTO `product_comment` VALUES (4, 1, 1, 1, 'hat', 'good good hahaha', 'very small', 4, '2019-03-02 19:02:05', 1);
INSERT INTO `product_comment` VALUES (5, 1, 1, 1, 'hat', 'good good hahaha', 'test', 5, '2019-03-02 19:02:42', 1);
INSERT INTO `product_comment` VALUES (6, 1, 1, 1, 'hat', 'good good', 'very small', 1, '2019-03-07 17:21:55', 1);
INSERT INTO `product_comment` VALUES (7, 2, 1, 1, 'hat', 'good wonderful', 'test', 3, '2019-03-07 17:54:31', 1);
INSERT INTO `product_comment` VALUES (8, 2, 1, 1, 'hat', 'good good', NULL, 4, '2019-03-07 21:31:26', 1);

-- ----------------------------
-- Table structure for product_discount
-- ----------------------------
DROP TABLE IF EXISTS `product_discount`;
CREATE TABLE `product_discount`  (
  `discount_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `discount_rate` decimal(4, 2) NULL DEFAULT NULL,
  `seller_discount_rate` decimal(4, 2) NULL DEFAULT NULL,
  `yield_rate` decimal(4, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`discount_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `product_discount_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_discount
-- ----------------------------
INSERT INTO `product_discount` VALUES (0, 0, 1.00, 1.00, 1.00);
INSERT INTO `product_discount` VALUES (1, 1, 10.00, 12.00, 5.00);
INSERT INTO `product_discount` VALUES (2, 3, 7.00, 8.00, 6.00);
INSERT INTO `product_discount` VALUES (3, 2, 8.00, 10.00, 5.00);
INSERT INTO `product_discount` VALUES (4, 4, 7.00, 11.00, 6.00);

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `brand_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `spu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `one_category_id` int(11) NULL DEFAULT NULL,
  `two_category_id` int(11) NULL DEFAULT NULL,
  `three_category_id` int(11) NULL DEFAULT NULL,
  `is_recommend` tinyint(4) NULL DEFAULT 0,
  `main_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attribute_list` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publish_status` tinyint(4) NULL DEFAULT 0,
  `audit_status` tinyint(4) NULL DEFAULT 0,
  `use_coupon` tinyint(4) NULL DEFAULT NULL,
  `discount_rate` decimal(4, 2) NULL DEFAULT 0.00,
  `production_date` datetime(4) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `html` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_time` datetime(0) NULL DEFAULT NULL,
  `modified_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `one_category_id`(`one_category_id`) USING BTREE,
  INDEX `two_category_id`(`two_category_id`) USING BTREE,
  INDEX `three_category_id`(`three_category_id`) USING BTREE,
  CONSTRAINT `product_info_ibfk_1` FOREIGN KEY (`one_category_id`) REFERENCES `product_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_info_ibfk_2` FOREIGN KEY (`two_category_id`) REFERENCES `product_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_info_ibfk_3` FOREIGN KEY (`three_category_id`) REFERENCES `product_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES (1, 'bucket hat', 'cacuss', '6677221', 1, 11, NULL, 1, 'http://112.74.165.55/hat/111.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]},{\"attributeKey\":\"size\",\"attributeValue\":[\"M\",\"L\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 2000, '<h4 class=\"hd\">商品详情</h4>', '2019-02-27 19:11:27', '2019-03-07 18:11:49');
INSERT INTO `product_info` VALUES (2, 'fashion hat', 'cacuss', '6677222', 1, 11, NULL, 0, 'http://112.74.165.55/hat/112.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"green\"]},{\"attributeKey\":\"size\",\"attributeValue\":[\"M\",\"L\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-02-27 19:11:29', '2019-03-01 17:25:26');
INSERT INTO `product_info` VALUES (3, 'peaked hat', 'cacuss', '6677223', 1, 11, NULL, 1, 'http://112.74.165.55/hat/113.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"white\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-02-27 19:11:29', '2019-03-01 21:55:55');
INSERT INTO `product_info` VALUES (4, 'sunhat', 'cacuss', '6677224', 1, 11, NULL, 1, 'http://112.74.165.55/hat/114.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"white\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-02-27 19:11:29', '2019-03-01 10:48:22');
INSERT INTO `product_info` VALUES (5, 'bucket hat', 'Stetson', '6677225', 1, 12, NULL, 0, 'http://112.74.165.55/hat/115.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"white\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-03-07 18:07:59', '2019-03-07 18:09:15');
INSERT INTO `product_info` VALUES (6, 'fashion hat', 'Stetson', '6677226', 1, 12, NULL, 1, 'http://112.74.165.55/hat/114.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"white\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:25', '2019-06-11 09:56:14');
INSERT INTO `product_info` VALUES (7, 'peaked hat', 'Stetson', '6677227', 1, 12, NULL, 0, 'http://112.74.165.55/hat/115.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]},{\"attributeKey\":\"size\",\"attributeValue\":[\"M\",\"L\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:28', '2019-06-11 09:56:17');
INSERT INTO `product_info` VALUES (8, 'sunhat', 'Stetson', '6677228', 1, 12, NULL, 0, 'http://112.74.165.55/hat/116.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 10.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:31', '2019-06-11 09:56:21');
INSERT INTO `product_info` VALUES (9, 'leisure hat', 'Stetson', '6677229', 1, 12, NULL, 0, 'http://112.74.165.55/hat/117.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:34', '2019-06-11 09:56:35');
INSERT INTO `product_info` VALUES (10, 'sunhat', 'Fedora', '6677230', 1, 13, NULL, 0, 'http://112.74.165.55/hat/118.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:36', '2019-06-11 09:56:38');
INSERT INTO `product_info` VALUES (11, 'fashion hat', 'Fedora', '6677231', 1, 13, NULL, 0, 'http://112.74.165.55/hat/119.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:40', '2019-06-11 09:56:41');
INSERT INTO `product_info` VALUES (12, 'leisure hat', 'Fedora', '6677232', 1, 13, NULL, 0, 'http://112.74.165.55/hat/120.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:43', '2019-06-11 09:56:44');
INSERT INTO `product_info` VALUES (13, 'peaked hat', 'Fedora', '6677233', 1, 13, NULL, 0, 'http://112.74.165.55/hat/121.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:45', '2019-06-11 09:57:09');
INSERT INTO `product_info` VALUES (14, 'cowboy hat', 'Fedora', '6677234', 1, 13, NULL, 0, 'http://112.74.165.55/hat/111.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 20.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:48', '2019-06-11 09:57:13');
INSERT INTO `product_info` VALUES (15, 'bucket hat', 'Borsalino', '6677235', 1, 14, NULL, 0, 'http://112.74.165.55/hat/112.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:50', '2019-06-11 09:57:17');
INSERT INTO `product_info` VALUES (16, 'fashion hat', 'Borsalino', '6677236', 1, 14, NULL, 0, 'http://112.74.165.55/hat/113.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:54', '2019-06-11 09:57:22');
INSERT INTO `product_info` VALUES (17, 'peaked hat', 'Borsalino', '6677237', 1, 14, NULL, 0, 'http://112.74.165.55/hat/114.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:55:57', '2019-06-11 09:57:25');
INSERT INTO `product_info` VALUES (18, 'sunhat', 'Borsalino', '6677238', 1, 14, NULL, 0, 'http://112.74.165.55/hat/115.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:56:00', '2019-06-11 09:57:29');
INSERT INTO `product_info` VALUES (19, 'leisure hat', 'Borsalino', '6677239', 1, 14, NULL, 0, 'http://112.74.165.55/hat/116.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:56:03', '2019-06-11 09:57:32');
INSERT INTO `product_info` VALUES (20, 'Phenix Cashmere Half', 'HERMES', '6677240', 2, 15, NULL, 1, 'http://112.74.165.55/scarf/211.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 20.00, NULL, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:56:06', '2019-06-11 09:57:35');
INSERT INTO `product_info` VALUES (21, 'pashmina', 'HERMES', '6677241', 2, 15, NULL, 0, 'http://112.74.165.55/scarf/212.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-11 09:56:09', '2019-06-11 09:57:39');
INSERT INTO `product_info` VALUES (22, 'Comforter', 'HERMES', '6677242', 2, 15, NULL, 0, 'http://112.74.165.55/scarf/213.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-14 16:23:22', NULL);
INSERT INTO `product_info` VALUES (23, 'Comforter', 'CHANEL', '6677243', 2, 16, NULL, 0, 'http://112.74.165.55/scarf/214.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-14 17:17:59', NULL);
INSERT INTO `product_info` VALUES (24, 'Phenix Cashmere Half', 'CHANEL', '6677244', 2, 16, NULL, 0, 'http://112.74.165.55/scarf/215.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 10.00, NULL, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-14 16:23:25', NULL);
INSERT INTO `product_info` VALUES (25, 'pashmina', 'CHANEL', '6677245', 2, 16, NULL, 0, 'http://112.74.165.55/scarf/216.jpg', '[{\"attributeKey\":\"color\",\"attributeValue\":[\"red\",\"blue\"]}]', 1, 1, NULL, 0.00, NULL, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', 3000, '<html>\r\n<h1>shareshop</h1>\r\n</html>', '2019-06-14 16:23:27', NULL);

-- ----------------------------
-- Table structure for product_pic_info
-- ----------------------------
DROP TABLE IF EXISTS `product_pic_info`;
CREATE TABLE `product_pic_info`  (
  `product_pic_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `pic_desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_master` tinyint(4) NULL DEFAULT 0,
  `pic_status` tinyint(4) NULL DEFAULT 1,
  `pic_order` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`product_pic_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `product_pic_info_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_pic_info
-- ----------------------------
INSERT INTO `product_pic_info` VALUES (1, 1, NULL, 'http://112.74.165.55/hat/111.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (2, 1, NULL, 'http://112.74.165.55/hat/112.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (3, 1, NULL, 'http://112.74.165.55/hat/113.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (4, 1, NULL, 'http://112.74.165.55/hat/114.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (5, 2, NULL, 'http://112.74.165.55/hat/115.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (6, 2, NULL, 'http://112.74.165.55/hat/116.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (7, 2, NULL, 'http://112.74.165.55/hat/117.jpg', 0, 1, NULL);
INSERT INTO `product_pic_info` VALUES (8, 2, NULL, 'http://112.74.165.55/hat/118.jpg', 0, 1, NULL);

-- ----------------------------
-- Table structure for product_specs
-- ----------------------------
DROP TABLE IF EXISTS `product_specs`;
CREATE TABLE `product_specs`  (
  `product_specs_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `specs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specs_sort` tinyint(4) NULL DEFAULT NULL,
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `average_cost` decimal(10, 2) NULL DEFAULT NULL,
  `original_price` decimal(10, 2) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_stock` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`product_specs_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `product_specs_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_specs
-- ----------------------------
INSERT INTO `product_specs` VALUES (1, 1, 'cacuss bucket hat', '667722101', '{\"color\":\"red\",\"size\":\"M\"}', NULL, 'http://112.74.165.55/hat/111.jpg', 4.00, NULL, 7.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-03-02 15:04:21', NULL);
INSERT INTO `product_specs` VALUES (2, 1, 'cacuss bucket hat', '667722102', '{\"color\":\"blue\",\"size\":\"L\"}', NULL, 'http://112.74.165.55/hat/112.jpg', 4.50, NULL, 8.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-04-12 15:04:21', '2019-04-02 15:03:12');
INSERT INTO `product_specs` VALUES (3, 1, 'cacuss bucket hat', '667722103', '{\"color\":\"red\",\"size\":\"M\"}', NULL, 'http://112.74.165.55/hat/113.jpg', 4.00, NULL, 7.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-04-09 15:04:21', NULL);
INSERT INTO `product_specs` VALUES (4, 1, 'cacuss bucket hat', '667722104', '{\"color\":\"red\",\"size\":\"L\"}', NULL, 'http://112.74.165.55/hat/114.jpg', 4.50, NULL, 8.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-04-07 15:04:21', NULL);
INSERT INTO `product_specs` VALUES (5, 2, 'cacuss fashion hat', '667722201', '{\"color\":\"green\",\"size\":\"M\"}', NULL, 'http://112.74.165.55/hat/115.jpg', 6.00, NULL, 10.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-04-14 18:54:43', NULL);
INSERT INTO `product_specs` VALUES (6, 2, 'cacuss fashion hat', '667722202', '{\"color\":\"green\",\"size\":\"L\"}', NULL, 'http://112.74.165.55/hat/116.jpg', 6.00, NULL, 10.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-04-16 18:08:23', NULL);
INSERT INTO `product_specs` VALUES (7, 3, 'cacuss peaked hat', '667722401', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/117.jpg', 15.50, NULL, 28.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-04-16 19:44:44', NULL);
INSERT INTO `product_specs` VALUES (8, 3, 'cacuss peaked hat', '667722402', '{\"color\":\"white\"}', NULL, 'http://112.74.165.55/hat/118.jpg', 15.50, NULL, 28.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-02 15:19:33', NULL);
INSERT INTO `product_specs` VALUES (9, 4, 'cacuss sunhat', '667722401', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/119.jpg', 7.00, NULL, 12.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-02 15:19:39', NULL);
INSERT INTO `product_specs` VALUES (10, 4, 'cacuss sunhat', '667722402', '{\"color\":\"white\"}', NULL, 'http://112.74.165.55/hat/120.jpg', 7.00, NULL, 12.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-01 15:19:43', NULL);
INSERT INTO `product_specs` VALUES (11, 5, 'Stetson bucket hat', '667722501', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/111.jpg', 10.00, NULL, 15.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-02 15:19:46', NULL);
INSERT INTO `product_specs` VALUES (12, 5, 'Stetson bucket hat', '667722502', '{\"color\":\"white\"}', NULL, 'http://112.74.165.55/hat/112.jpg', 11.00, NULL, 16.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-11 10:14:04', NULL);
INSERT INTO `product_specs` VALUES (13, 6, 'Stetson fashion hat', '667722601', '{\"color\":\"white\"}', NULL, 'http://112.74.165.55/hat/113.jpg', 12.00, NULL, 18.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-11 10:14:07', NULL);
INSERT INTO `product_specs` VALUES (14, 6, 'Stetson fashion hat', '667722602', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/114.jpg', 12.00, NULL, 18.00, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, '2019-06-11 10:14:10', NULL);
INSERT INTO `product_specs` VALUES (15, 7, 'Stetson peaked hat', '667722701', '{\"color\":\"red\",\"size\":\"M\"}', NULL, 'http://112.74.165.55/hat/115.jpg', 20.00, NULL, 30.50, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (16, 7, 'Stetson peaked hat', '667722702', '{\"color\":\"blue\",\"size\":\"L\"}', NULL, 'http://112.74.165.55/hat/115.jpg', 20.00, NULL, 30.50, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (17, 7, 'Stetson peaked hat', '667722703', '{\"color\":\"red\",\"size\":\"M\"}', NULL, 'http://112.74.165.55/hat/116.jpg', 20.00, NULL, 32.50, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (18, 7, 'Stetson peaked hat', '667722704', '{\"color\":\"red\",\"size\":\"L\"}', NULL, 'http://112.74.165.55/hat/116.jpg', 20.00, NULL, 32.50, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (19, 8, 'Stetson sunhat', '667722801', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/117.jpg', 22.00, NULL, 33.50, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (20, 8, 'Stetson sunhat', '667722802', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/118.jpg', 22.00, NULL, 33.50, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (21, 9, 'Stetson leisure hat', '667722901', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/119.jpg', 14.00, NULL, 20.30, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (22, 9, 'Stetson leisure hat', '667722902', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/120.jpg', 14.00, NULL, 20.30, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (23, 10, 'Fedora sunhat', '667723001', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/121.jpg', 14.00, NULL, 21.20, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (24, 10, 'Fedora sunhat', '667723002', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/111.jpg', 14.00, NULL, 21.20, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (25, 11, 'Fedora fashion hat', '667723101', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/112.jpg', 32.00, NULL, 50.80, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (26, 11, 'Fedora fashion hat', '667723102', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/113.jpg', 32.00, NULL, 50.80, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (27, 12, 'Fedora leisure hat', '667723201', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/114.jpg', 35.00, NULL, 63.20, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (28, 12, 'Fedora leisure hat', '667723202', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/115.jpg', 35.00, NULL, 63.20, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (29, 13, 'Fedora peaked hat', '667723301', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/116.jpg', 25.00, NULL, 37.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (30, 13, 'Fedora peaked hat', '667723302', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/116.jpg', 25.00, NULL, 37.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (31, 14, 'Fedora cowboy hat', '667723401', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/117.jpg', 27.00, NULL, 39.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (32, 14, 'Fedora cowboy hat', '667723402', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/117.jpg', 27.00, NULL, 39.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (33, 15, ' Borsalino bucket hat', '667723501', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/118.jpg', 26.00, NULL, 38.80, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (34, 15, 'Borsalino bucket hat', '667723502', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/119.jpg', 26.00, NULL, 38.80, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (35, 16, 'Borsalino fashion hat', '667723601', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/120.jpg', 22.00, NULL, 34.30, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (36, 16, 'Borsalino fashion hat', '667723602', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/111.jpg', 22.00, NULL, 34.30, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (37, 17, 'Borsalino peaked hat', '667723701', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/112.jpg', 24.00, NULL, 36.40, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (38, 17, 'Borsalino peaked hat', '667723702', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/113.jpg', 24.00, NULL, 36.40, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (39, 18, 'Borsalino sunhat', '667723801', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/114.jpg', 35.00, NULL, 52.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (40, 18, 'Borsalino sunhat', '667723802', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/115.jpg', 35.00, NULL, 52.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (41, 19, 'Borsalino leisure hat', '667723901', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/hat/116.jpg', 36.00, NULL, 55.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (42, 19, 'Borsalino leisure hat', '667723902', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/hat/117.jpg', 36.00, NULL, 55.90, 'Hats can also be used to dress up, first of all, according to the face to choose the right hat', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (43, 20, 'HERMES Phenix Cashmere Half', '667724001', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/scarf/211.jpg', 37.00, NULL, 56.80, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (44, 20, 'HERMES Phenix Cashmere Half', '667724002', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/scarf/212.jpg', 37.00, NULL, 56.80, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (45, 21, ' HERMES pashmina', '667724101', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/scarf/213.jpg', 38.00, NULL, 58.80, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (46, 21, 'HERMES pashmina', '667724102', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/scarf/214.jpg', 38.00, NULL, 58.80, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (47, 22, 'HERMES Comforter', '667724201', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/scarf/215.jpg', 39.00, NULL, 61.20, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (48, 22, 'HERMES Comforter', '667724202', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/scarf/216.jpg', 39.00, NULL, 61.20, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (49, 23, 'CHANEL Comforter', '667724301', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/scarf/217.jpg', 58.00, NULL, 87.30, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (50, 23, 'CHANEL Comforter', '667724302', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/scarf/218.jpg', 58.00, NULL, 87.30, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (51, 24, 'CHANEL Phenix Cashmere Half', '667724401', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/scarf/219.jpg', 32.00, NULL, 49.90, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (52, 24, 'CHANEL Phenix Cashmere Half', '667724402', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/scarf/220.jpg', 32.00, NULL, 49.90, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (53, 25, 'CHANEL pashmina', '667724501', '{\"color\":\"blue\"}', NULL, 'http://112.74.165.55/scarf/211.jpg', 40.00, NULL, 59.90, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);
INSERT INTO `product_specs` VALUES (54, 25, 'CHANEL pashmina', '667724502', '{\"color\":\"red\"}', NULL, 'http://112.74.165.55/scarf/212.jpg', 40.00, NULL, 59.90, 'A fabric worn around the neck for warmth, protection of the collar, or for decoration', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for return_record
-- ----------------------------
DROP TABLE IF EXISTS `return_record`;
CREATE TABLE `return_record`  (
  `return_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `order_detail_id` int(11) NOT NULL,
  `product_specs_id` int(11) NULL DEFAULT NULL,
  `return_type` tinyint(4) NULL DEFAULT NULL,
  `return_money` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`return_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `order_detail_id`(`order_detail_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `return_record_ibfk_3`(`product_specs_id`) USING BTREE,
  CONSTRAINT `return_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `return_record_ibfk_3` FOREIGN KEY (`product_specs_id`) REFERENCES `product_specs` (`product_specs_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `return_record_ibfk_4` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`order_detail_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `return_record_ibfk_5` FOREIGN KEY (`order_id`) REFERENCES `order_master` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of return_record
-- ----------------------------
INSERT INTO `return_record` VALUES (1, 1, 5, 6, 1, 1, 31.52, '2019-05-12 12:15:59', 1, 'k');

-- ----------------------------
-- Table structure for seller_register_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_register_info`;
CREATE TABLE `seller_register_info`  (
  `register_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL,
  `payment` decimal(10, 2) NOT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`register_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `seller_register_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `state_id` int(11) NOT NULL,
  `state` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `abbreviation` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`state_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (1, 'Alabama', 'AL');
INSERT INTO `state` VALUES (2, 'Alaska', 'AK\r\n\r\n');
INSERT INTO `state` VALUES (3, 'Arizona', 'AZ');
INSERT INTO `state` VALUES (4, 'Arkansas', 'AR');
INSERT INTO `state` VALUES (5, 'California', 'CA');
INSERT INTO `state` VALUES (6, 'Colorado', 'CO');
INSERT INTO `state` VALUES (7, 'Connecticut', 'CT');
INSERT INTO `state` VALUES (8, 'Delaware', 'DE');
INSERT INTO `state` VALUES (9, 'Florida', 'FL');
INSERT INTO `state` VALUES (10, 'Georgia', 'GA');
INSERT INTO `state` VALUES (11, 'Hawaii', 'HI');
INSERT INTO `state` VALUES (12, 'Idaho', 'ID');
INSERT INTO `state` VALUES (13, 'Illinois', 'IL');
INSERT INTO `state` VALUES (14, 'Indiana', 'IN');
INSERT INTO `state` VALUES (15, 'Iowa', 'IA');
INSERT INTO `state` VALUES (16, 'Kansas', 'KS');
INSERT INTO `state` VALUES (17, 'Kentucky', 'KY');
INSERT INTO `state` VALUES (18, 'Louisiana', 'LA');
INSERT INTO `state` VALUES (19, 'Maine', 'ME');
INSERT INTO `state` VALUES (20, 'Maryland', 'MD');
INSERT INTO `state` VALUES (21, 'Massachusetts', 'MA');
INSERT INTO `state` VALUES (22, 'Michigan', 'MI');
INSERT INTO `state` VALUES (23, 'Minnesota', 'MN');
INSERT INTO `state` VALUES (24, 'Mississippi', 'MS');
INSERT INTO `state` VALUES (25, 'Missouri', 'MO');
INSERT INTO `state` VALUES (26, 'Montana', 'MT');
INSERT INTO `state` VALUES (27, 'Nebraska', 'NE');
INSERT INTO `state` VALUES (28, 'Nevada', 'NV');
INSERT INTO `state` VALUES (29, 'New hampshire', 'NH');
INSERT INTO `state` VALUES (30, 'New jersey', 'NJ');
INSERT INTO `state` VALUES (31, 'New mexico', 'NM');
INSERT INTO `state` VALUES (32, 'New York', 'NY');
INSERT INTO `state` VALUES (33, 'North Carolina', 'NC');
INSERT INTO `state` VALUES (34, 'North Dakota', 'ND');
INSERT INTO `state` VALUES (35, 'Ohio', 'OH');
INSERT INTO `state` VALUES (36, 'Oklahoma', 'OK');
INSERT INTO `state` VALUES (37, 'Oregon', 'OR');
INSERT INTO `state` VALUES (38, 'Pennsylvania', 'PA');
INSERT INTO `state` VALUES (39, 'Rhode island', 'RI');
INSERT INTO `state` VALUES (40, 'South carolina', 'SC');
INSERT INTO `state` VALUES (41, 'South dakota', 'SD');
INSERT INTO `state` VALUES (42, 'Tennessee', 'TN');
INSERT INTO `state` VALUES (43, 'Texas', 'TX');
INSERT INTO `state` VALUES (44, 'Utah', 'UT');
INSERT INTO `state` VALUES (45, 'Vermont', 'VT');
INSERT INTO `state` VALUES (46, 'Virginia', 'VA');
INSERT INTO `state` VALUES (47, 'Washington', 'WA');
INSERT INTO `state` VALUES (48, 'West Virginia', 'WV');
INSERT INTO `state` VALUES (49, 'Wisconsin', 'WI');
INSERT INTO `state` VALUES (50, 'Wyoming', 'WY');
INSERT INTO `state` VALUES (51, 'Washington,D.C.', 'Washington,D.C.');

-- ----------------------------
-- Table structure for state_tax
-- ----------------------------
DROP TABLE IF EXISTS `state_tax`;
CREATE TABLE `state_tax`  (
  `state_tax_id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tax_rate` decimal(4, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`state_tax_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state_tax
-- ----------------------------
INSERT INTO `state_tax` VALUES (1, 'Alabama', 2.60);
INSERT INTO `state_tax` VALUES (2, 'Alaska', 3.70);

-- ----------------------------
-- Table structure for user_addr
-- ----------------------------
DROP TABLE IF EXISTS `user_addr`;
CREATE TABLE `user_addr`  (
  `user_addr_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL,
  `consignee_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postal_code` int(11) NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `first_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `second_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_default` tinyint(4) NULL DEFAULT 0,
  `modified_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_addr_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_addr_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_addr
-- ----------------------------
INSERT INTO `user_addr` VALUES (1, 1, 'Tom', 345673, '78643829864', 'New York', 'New York', 'aaaa', NULL, 1, '2019-03-08 21:15:46');
INSERT INTO `user_addr` VALUES (2, 2, 'Jack', 473843, '12324574631', 'California', 'San Francisco ', 'wwww', NULL, 0, '2019-03-14 21:18:48');
INSERT INTO `user_addr` VALUES (3, 3, 'Bob', 233323, '13986564312', 'New York', 'San', 'ccccc', '', 1, '2019-03-25 00:00:00');
INSERT INTO `user_addr` VALUES (4, 79, 'ChenC', 434332, '13109853622', 'Alabama', 'Montgomery', '4343', '', 1, '2019-05-07 09:11:09');
INSERT INTO `user_addr` VALUES (5, 84, 'LuYY', 323222, '13087342876', 'Alabama', 'Montgomery', 'The first street', '', 0, NULL);
INSERT INTO `user_addr` VALUES (12, 84, 'Mary123', 112234, '12345678901', 'Alabama', 'Montgomery', '杭州市西湖区 黄龙万科中心', NULL, 1, NULL);
INSERT INTO `user_addr` VALUES (13, 84, 'jsdkl', 123456, '12341234123', 'Alabama', 'Montgomery', '杭州市西湖区 黄龙万科中心', NULL, 0, NULL);
INSERT INTO `user_addr` VALUES (14, 85, 'Carn', 111121, '12345612345', 'Alabama', '', 'adaman', NULL, 0, NULL);
INSERT INTO `user_addr` VALUES (15, 84, 'Kuri', 0, '18712344321', 'Alabama', '', 'center street', NULL, 0, NULL);
INSERT INTO `user_addr` VALUES (16, 84, '123', 123321, '09876543211', '天津市', '天津市', '静待花开时', NULL, 0, NULL);
INSERT INTO `user_addr` VALUES (17, 85, 'luyy', 121212, '12312312311', 'Alabama', '', 'center minst', NULL, 0, NULL);
INSERT INTO `user_addr` VALUES (18, 88, 'rtyrt', 123123, '13971020062', '天津市', '天津市', '杭州市西湖区 黄龙万科中心', NULL, 1, NULL);

-- ----------------------------
-- Table structure for user_bank_card
-- ----------------------------
DROP TABLE IF EXISTS `user_bank_card`;
CREATE TABLE `user_bank_card`  (
  `card_id` int(11) NOT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `card_number` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `type` tinyint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`card_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_bank_card_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_collect
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect`  (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `collect_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`collect_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_collect_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_collect_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collect
-- ----------------------------
INSERT INTO `user_collect` VALUES (1, 1, 1, '2019-03-08 18:04:55');
INSERT INTO `user_collect` VALUES (2, 1, 3, '2019-02-26 18:05:12');
INSERT INTO `user_collect` VALUES (3, 3, 1, '2019-03-07 18:05:26');
INSERT INTO `user_collect` VALUES (6, 78, 1, '2019-06-03 11:06:12');
INSERT INTO `user_collect` VALUES (7, 78, 2, '2019-06-03 13:57:22');
INSERT INTO `user_collect` VALUES (11, 85, 22, '2019-06-17 19:35:14');
INSERT INTO `user_collect` VALUES (12, 85, 7, '2019-06-17 20:15:59');
INSERT INTO `user_collect` VALUES (13, 85, 24, '2019-06-17 20:26:31');
INSERT INTO `user_collect` VALUES (16, 79, 2, '2019-06-17 22:30:35');
INSERT INTO `user_collect` VALUES (17, 79, 14, '2019-06-17 22:30:43');
INSERT INTO `user_collect` VALUES (18, 79, 3, '2019-06-17 22:30:51');
INSERT INTO `user_collect` VALUES (19, 79, 8, '2019-06-17 22:34:45');
INSERT INTO `user_collect` VALUES (25, 79, 1, '2019-06-18 21:33:22');
INSERT INTO `user_collect` VALUES (29, 85, 21, '2019-06-19 20:17:50');
INSERT INTO `user_collect` VALUES (30, 84, 6, '2019-06-24 14:31:03');
INSERT INTO `user_collect` VALUES (31, 84, 21, '2019-06-24 14:48:08');
INSERT INTO `user_collect` VALUES (33, 84, 20, '2019-06-28 20:58:41');
INSERT INTO `user_collect` VALUES (35, 88, 1, '2019-07-09 15:31:19');
INSERT INTO `user_collect` VALUES (36, 88, 2, '2019-07-09 15:31:23');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity_card_type` tinyint(4) NULL DEFAULT 1,
  `identity_card_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `register_time` datetime(0) NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `superior_id` int(11) NULL DEFAULT 0,
  `user_money` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`user_info_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `identity_card_no`(`identity_card_no`) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_danish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'Tom', 1, '4682911234546', '13078332783', 'http://112.74.165.55/userIcon/1.jpg', '323@gmai.com', 'male', '2023-07-31 00:00:00', '2019-03-01 20:17:27', 00000000001, 0, 0.00);
INSERT INTO `user_info` VALUES (2, 'Jack', 1, '4682935643783', '13078393765', 'http://112.74.165.55/userIcon/2.jpg', '122@gmai.com', 'female', '1995-10-12 20:16:59', '2019-03-03 20:29:37', 00000000002, 3, 0.00);
INSERT INTO `user_info` VALUES (3, 'Bob', 1, '4682974387422', '13867236723', 'http://112.74.165.55/userIcon/3.jpg', '4839@gmain.com', 'female', '1995-10-12 20:16:59', '2019-03-03 20:30:52', 00000000003, 0, 1000.00);
INSERT INTO `user_info` VALUES (4, 'Mike', 1, '4349111145523', '18735459332', 'http://112.74.165.55/userIcon/4.jpg', '2231@qq.com', 'male', '2019-02-23 00:00:00', '2019-03-03 00:00:00', 00000000004, 0, 0.00);
INSERT INTO `user_info` VALUES (5, 'ChenC', 1, '4682911111112', '13645454899', 'http://112.74.165.55/userIcon/1.jpg', '2121@qq.com', 'male', '2001-01-01 08:00:00', '2019-05-28 12:45:00', 00000000079, 3, 0.00);
INSERT INTO `user_info` VALUES (21, 'LuYY', 1, '3435565654337', '13087436743', 'http://112.74.165.55/userIcon/1.jpg', '12323@qq.com', 'female', '1998-08-14 20:16:59', '2019-06-12 21:19:46', 00000000084, 3, 0.00);
INSERT INTO `user_info` VALUES (22, 'Lu', 1, '3435565654334', '13087342876', 'http://112.74.165.55/userIcon/2.jpg', '1238783@qq.com', 'female', '1999-03-12 20:16:59', '2019-06-13 21:19:46', 00000000085, 3, 0.00);
INSERT INTO `user_info` VALUES (24, NULL, NULL, NULL, '13098783623', 'http://112.74.165.55/userIcon/1.jpg', '1217@qq.com', NULL, NULL, '2019-06-18 11:00:52', 00000000087, NULL, 0.00);
INSERT INTO `user_info` VALUES (25, NULL, NULL, NULL, '123456789', 'http://112.74.165.55/userIcon/3.jpg', '574954@162.com', NULL, NULL, '2019-06-18 11:50:09', 00000000088, NULL, 0.00);

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES (1, 'admin', 'jq0ZrHhJKfhNp6cfMN4gSQ==', 1, 1);
INSERT INTO `user_login` VALUES (2, 'hello2', 'hello2', 1, 2);
INSERT INTO `user_login` VALUES (3, 'hello', 'hello', 1, 3);
INSERT INTO `user_login` VALUES (4, 'hello1', 'hello1', 0, 1);
INSERT INTO `user_login` VALUES (5, 'hello12', '12asa21', 1, 11);
INSERT INTO `user_login` VALUES (6, 'cc', '3xDvhQncF21zPVlUnn2/rw==', 1, 20);
INSERT INTO `user_login` VALUES (66, 'hello82', '12asa21', 1, 20);
INSERT INTO `user_login` VALUES (67, 'hell1o82', 'jq0ZrHhJKfhNp6cfMN4gSQ==', 1, 1);
INSERT INTO `user_login` VALUES (78, 'chenhongyan222', '2o5EGL7DQ06EsaKnU97CXg==', 1, 1);
INSERT INTO `user_login` VALUES (79, 'chencheng', '3xDvhQncF21zPVlUnn2/rw==', 1, 2);
INSERT INTO `user_login` VALUES (82, 'chencheng1', '3xDvhQncF21zPVlUnn2/rw==', 1, 20);
INSERT INTO `user_login` VALUES (84, 'luyy1', '3xDvhQncF21zPVlUnn2/rw==', 1, 1);
INSERT INTO `user_login` VALUES (85, 'luyy2', '3xDvhQncF21zPVlUnn2/rw==', 1, 1);
INSERT INTO `user_login` VALUES (87, 'wangql', '3xDvhQncF21zPVlUnn2/rw==', 1, 1);
INSERT INTO `user_login` VALUES (88, 'luyy3', '3xDvhQncF21zPVlUnn2/rw==', 1, 1);

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` datetime(0) NOT NULL,
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_type` tinyint(4) NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_login_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 755 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login_log
-- ----------------------------
INSERT INTO `user_login_log` VALUES (1, '2019-05-18 10:16:13', '111.111.111.111', 1, 79);
INSERT INTO `user_login_log` VALUES (2, '2019-05-18 11:02:32', '111.111.111.111', 1, 79);
INSERT INTO `user_login_log` VALUES (3, '2019-05-18 11:12:12', '111.111.111.111', 1, 79);
INSERT INTO `user_login_log` VALUES (4, '2019-05-18 11:55:04', '111.111.111.111', 1, 79);
INSERT INTO `user_login_log` VALUES (5, '2019-05-18 17:33:56', '111.111.111.111', 1, 79);
INSERT INTO `user_login_log` VALUES (18, '2019-05-24 15:42:22', '111.111.111.111', 1, 6);
INSERT INTO `user_login_log` VALUES (19, '2019-05-24 15:43:51', '111.111.111.111', 1, 6);
INSERT INTO `user_login_log` VALUES (20, '2019-05-24 15:44:04', '111.111.111.111', 1, 6);
INSERT INTO `user_login_log` VALUES (21, '2019-05-24 16:20:42', '0:0:0:0:0:0:0:1', 1, 6);
INSERT INTO `user_login_log` VALUES (22, '2019-05-24 16:23:56', '10.120.203.64', 1, 6);
INSERT INTO `user_login_log` VALUES (23, '2019-05-24 16:26:20', '10.120.203.64', 1, 6);
INSERT INTO `user_login_log` VALUES (24, '2019-05-24 16:27:54', '111.111.111.111', 1, 79);
INSERT INTO `user_login_log` VALUES (25, '2019-05-24 16:32:03', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (26, '2019-05-24 21:00:36', '111.111.111.111', 1, 6);
INSERT INTO `user_login_log` VALUES (27, '2019-05-26 21:14:16', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (28, '2019-05-26 21:31:40', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (29, '2019-05-26 21:34:42', '10.120.189.2', 1, 1);
INSERT INTO `user_login_log` VALUES (30, '2019-05-26 21:36:24', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (31, '2019-05-26 21:43:53', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (32, '2019-05-26 21:45:05', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (33, '2019-05-26 21:46:35', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (34, '2019-05-26 21:49:05', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (35, '2019-05-26 21:49:42', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (36, '2019-05-26 21:56:18', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (37, '2019-05-26 22:11:49', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (38, '2019-05-26 22:12:54', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (39, '2019-05-27 09:08:24', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (40, '2019-05-27 09:13:47', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (41, '2019-05-27 10:06:10', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (42, '2019-05-27 10:07:44', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (43, '2019-05-27 10:07:50', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (44, '2019-05-27 10:13:43', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (45, '2019-05-27 10:21:12', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (46, '2019-05-27 10:21:22', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (47, '2019-05-27 10:21:23', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (48, '2019-05-27 10:22:00', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (49, '2019-05-27 10:24:19', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (50, '2019-05-27 10:25:56', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (51, '2019-05-27 11:41:19', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (52, '2019-05-27 11:41:35', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (53, '2019-05-27 12:00:02', '111.111.111.111', 1, 78);
INSERT INTO `user_login_log` VALUES (54, '2019-05-27 12:00:06', '111.111.111.111', 1, 78);
INSERT INTO `user_login_log` VALUES (55, '2019-05-27 12:00:11', '111.111.111.111', 1, 78);
INSERT INTO `user_login_log` VALUES (56, '2019-05-27 12:01:47', '111.111.111.111', 1, 78);
INSERT INTO `user_login_log` VALUES (57, '2019-05-27 12:02:12', '111.111.111.111', 1, 78);
INSERT INTO `user_login_log` VALUES (58, '2019-05-27 12:45:26', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (59, '2019-05-27 12:45:30', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (60, '2019-05-27 12:45:48', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (61, '2019-05-27 12:51:25', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (62, '2019-05-27 12:51:28', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (63, '2019-05-27 12:51:40', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (64, '2019-05-27 12:51:57', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (65, '2019-05-27 12:52:18', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (66, '2019-05-27 12:52:38', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (67, '2019-05-27 12:53:28', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (68, '2019-05-27 12:54:12', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (69, '2019-05-27 12:55:17', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (70, '2019-05-27 12:56:03', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (71, '2019-05-27 12:56:07', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (72, '2019-05-27 12:59:14', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (73, '2019-05-27 12:59:24', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (74, '2019-05-27 13:20:27', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (75, '2019-05-27 13:22:02', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (76, '2019-05-27 13:23:35', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (77, '2019-05-27 13:23:37', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (78, '2019-05-27 13:35:08', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (79, '2019-05-27 13:35:08', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (80, '2019-05-27 13:35:13', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (81, '2019-05-27 13:35:13', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (82, '2019-05-27 13:35:21', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (83, '2019-05-27 13:35:31', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (84, '2019-05-27 13:35:42', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (85, '2019-05-27 13:35:43', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (86, '2019-05-27 13:35:48', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (87, '2019-05-27 13:35:50', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (88, '2019-05-27 13:35:55', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (89, '2019-05-27 13:36:06', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (90, '2019-05-27 13:36:10', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (91, '2019-05-27 13:36:13', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (92, '2019-05-27 13:36:20', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (93, '2019-05-27 14:35:35', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (94, '2019-05-27 14:35:37', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (95, '2019-05-27 14:35:39', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (96, '2019-05-27 14:35:44', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (97, '2019-05-27 14:36:47', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (98, '2019-05-27 14:38:27', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (99, '2019-05-27 14:38:58', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (100, '2019-05-27 14:39:00', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (101, '2019-05-27 14:39:02', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (102, '2019-05-27 14:39:04', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (103, '2019-05-27 14:48:56', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (104, '2019-05-27 14:50:15', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (105, '2019-05-27 14:50:23', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (106, '2019-05-27 14:54:34', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (107, '2019-05-27 15:00:41', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (108, '2019-05-27 15:01:23', '10.120.189.2', 1, 78);
INSERT INTO `user_login_log` VALUES (109, '2019-05-27 15:03:32', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (110, '2019-05-27 15:03:58', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (111, '2019-05-27 15:06:11', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (112, '2019-05-27 15:09:46', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (113, '2019-05-27 15:12:46', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (114, '2019-05-27 15:14:47', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (115, '2019-05-27 15:31:01', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (116, '2019-05-28 20:27:12', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (117, '2019-05-28 20:27:15', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (118, '2019-05-28 20:27:19', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (119, '2019-05-28 20:27:19', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (120, '2019-05-28 20:27:19', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (121, '2019-05-28 20:27:20', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (122, '2019-05-28 20:27:35', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (123, '2019-05-28 20:27:35', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (124, '2019-05-30 16:03:45', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (125, '2019-05-30 16:07:01', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (126, '2019-05-30 17:19:57', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (127, '2019-05-30 20:21:54', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (128, '2019-06-01 16:13:48', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (129, '2019-06-01 16:13:55', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (130, '2019-06-01 17:01:11', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (131, '2019-06-01 17:04:56', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (132, '2019-06-01 17:14:20', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (133, '2019-06-01 17:30:37', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (134, '2019-06-01 18:12:45', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (135, '2019-06-01 18:18:54', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (136, '2019-06-01 18:18:57', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (137, '2019-06-01 18:23:30', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (138, '2019-06-02 11:47:54', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (139, '2019-06-02 15:35:43', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (140, '2019-06-02 23:59:22', '211.161.155.47', 1, 78);
INSERT INTO `user_login_log` VALUES (141, '2019-06-03 00:04:17', '211.161.155.47', 1, 78);
INSERT INTO `user_login_log` VALUES (142, '2019-06-03 00:05:27', '211.161.155.47', 1, 78);
INSERT INTO `user_login_log` VALUES (143, '2019-06-03 00:05:51', '211.161.155.47', 1, 78);
INSERT INTO `user_login_log` VALUES (144, '2019-06-03 00:06:02', '211.161.155.47', 1, 78);
INSERT INTO `user_login_log` VALUES (145, '2019-06-03 15:08:41', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (146, '2019-06-03 15:29:26', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (147, '2019-06-03 15:37:27', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (148, '2019-06-03 15:42:45', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (149, '2019-06-03 15:48:08', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (150, '2019-06-03 15:49:23', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (151, '2019-06-03 16:34:34', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (152, '2019-06-03 16:37:53', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (153, '2019-06-03 17:09:41', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (154, '2019-06-03 17:09:49', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (155, '2019-06-03 17:10:02', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (156, '2019-06-03 18:14:44', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (157, '2019-06-03 21:11:23', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (158, '2019-06-03 21:11:30', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (159, '2019-06-03 21:11:32', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (160, '2019-06-05 15:56:33', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (161, '2019-06-07 21:05:12', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (162, '2019-06-07 21:07:49', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (163, '2019-06-10 16:00:30', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (164, '2019-06-10 16:01:30', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (165, '2019-06-10 16:01:50', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (166, '2019-06-10 16:05:04', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (167, '2019-06-10 16:05:55', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (168, '2019-06-10 16:07:18', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (169, '2019-06-10 16:09:44', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (170, '2019-06-10 16:10:34', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (171, '2019-06-10 16:11:19', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (172, '2019-06-10 16:13:30', '113.57.237.16', 1, 78);
INSERT INTO `user_login_log` VALUES (173, '2019-06-10 17:06:00', '10.120.189.2', 1, 84);
INSERT INTO `user_login_log` VALUES (174, '2019-06-10 17:06:12', '10.120.189.2', 1, 84);
INSERT INTO `user_login_log` VALUES (175, '2019-06-10 17:06:27', '10.120.189.2', 1, 84);
INSERT INTO `user_login_log` VALUES (176, '2019-06-10 17:06:44', '10.120.189.2', 1, 84);
INSERT INTO `user_login_log` VALUES (177, '2019-06-10 17:06:47', '10.120.189.2', 1, 84);
INSERT INTO `user_login_log` VALUES (178, '2019-06-10 17:13:13', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (179, '2019-06-10 17:13:21', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (180, '2019-06-10 17:13:37', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (181, '2019-06-10 17:14:14', '10.120.78.76', 1, 79);
INSERT INTO `user_login_log` VALUES (182, '2019-06-10 17:15:23', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (183, '2019-06-10 17:15:56', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (184, '2019-06-10 17:16:00', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (185, '2019-06-10 17:16:52', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (186, '2019-06-10 17:17:29', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (187, '2019-06-10 17:17:59', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (188, '2019-06-14 08:24:23', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (189, '2019-06-14 11:13:26', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (190, '2019-06-14 11:13:33', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (191, '2019-06-17 14:28:46', '113.57.237.9', 1, 84);
INSERT INTO `user_login_log` VALUES (192, '2019-06-17 14:28:50', '113.57.237.9', 1, 84);
INSERT INTO `user_login_log` VALUES (193, '2019-06-17 14:29:08', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (194, '2019-06-17 16:16:33', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (195, '2019-06-17 16:17:00', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (196, '2019-06-17 16:20:41', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (197, '2019-06-17 16:20:43', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (198, '2019-06-17 16:20:44', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (199, '2019-06-17 16:21:40', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (200, '2019-06-17 19:59:31', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (201, '2019-06-17 19:59:35', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (202, '2019-06-17 19:59:44', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (203, '2019-06-17 20:37:21', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (204, '2019-06-17 20:37:24', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (205, '2019-06-17 20:37:28', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (206, '2019-06-17 20:56:50', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (207, '2019-06-17 20:57:40', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (208, '2019-06-17 21:01:38', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (209, '2019-06-17 21:03:06', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (210, '2019-06-17 21:05:06', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (211, '2019-06-17 21:08:35', '0:0:0:0:0:0:0:1', 1, 85);
INSERT INTO `user_login_log` VALUES (212, '2019-06-17 21:08:46', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (213, '2019-06-17 21:09:08', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (214, '2019-06-17 21:09:30', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (215, '2019-06-17 21:09:31', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (216, '2019-06-17 21:19:10', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (217, '2019-06-17 21:19:36', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (218, '2019-06-17 21:44:42', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (219, '2019-06-17 21:45:02', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (220, '2019-06-17 21:46:30', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (221, '2019-06-17 21:46:38', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (222, '2019-06-17 21:48:58', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (223, '2019-06-17 21:51:13', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (224, '2019-06-17 21:51:14', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (225, '2019-06-17 21:53:18', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (226, '2019-06-17 21:54:10', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (227, '2019-06-17 22:00:23', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (228, '2019-06-17 22:04:40', '61.143.127.226', 1, 78);
INSERT INTO `user_login_log` VALUES (229, '2019-06-17 22:05:42', '61.143.127.226', 1, 78);
INSERT INTO `user_login_log` VALUES (230, '2019-06-17 22:10:33', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (231, '2019-06-17 22:10:49', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (232, '2019-06-17 22:10:53', '61.143.127.226', 1, 78);
INSERT INTO `user_login_log` VALUES (233, '2019-06-17 22:11:05', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (234, '2019-06-17 22:12:37', '113.57.237.18', 1, 85);
INSERT INTO `user_login_log` VALUES (235, '2019-06-17 22:12:58', '113.57.237.18', 1, 85);
INSERT INTO `user_login_log` VALUES (236, '2019-06-17 22:13:27', '113.57.237.18', 1, 85);
INSERT INTO `user_login_log` VALUES (237, '2019-06-17 22:13:29', '113.57.237.18', 1, 85);
INSERT INTO `user_login_log` VALUES (238, '2019-06-17 22:13:29', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (239, '2019-06-17 22:14:55', '61.143.127.226', 1, 78);
INSERT INTO `user_login_log` VALUES (240, '2019-06-17 22:17:57', '61.143.127.226', 1, 78);
INSERT INTO `user_login_log` VALUES (241, '2019-06-17 22:19:31', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (242, '2019-06-17 22:20:04', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (243, '2019-06-17 22:21:01', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (244, '2019-06-17 22:21:49', '113.57.237.9', 1, 84);
INSERT INTO `user_login_log` VALUES (245, '2019-06-17 22:21:51', '113.57.237.18', 1, 79);
INSERT INTO `user_login_log` VALUES (246, '2019-06-17 22:21:55', '113.57.237.18', 1, 79);
INSERT INTO `user_login_log` VALUES (247, '2019-06-17 22:21:57', '113.57.237.18', 1, 79);
INSERT INTO `user_login_log` VALUES (248, '2019-06-17 22:21:58', '113.57.237.18', 1, 79);
INSERT INTO `user_login_log` VALUES (249, '2019-06-17 22:22:12', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (250, '2019-06-17 22:23:43', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (251, '2019-06-17 22:30:54', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (252, '2019-06-17 22:31:09', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (253, '2019-06-17 22:32:32', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (254, '2019-06-17 22:32:54', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (255, '2019-06-17 22:34:13', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (256, '2019-06-17 22:34:14', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (257, '2019-06-17 22:34:14', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (258, '2019-06-17 22:34:15', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (259, '2019-06-17 22:34:15', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (260, '2019-06-17 22:34:16', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (261, '2019-06-17 22:34:17', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (262, '2019-06-17 22:34:23', '113.57.237.9', 1, 79);
INSERT INTO `user_login_log` VALUES (263, '2019-06-17 22:34:36', '113.57.237.18', 1, 85);
INSERT INTO `user_login_log` VALUES (264, '2019-06-17 22:35:22', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (265, '2019-06-17 22:35:55', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (266, '2019-06-17 22:38:18', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (267, '2019-06-17 22:38:52', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (268, '2019-06-17 22:47:54', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (269, '2019-06-17 22:48:10', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (270, '2019-06-17 22:52:28', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (271, '2019-06-17 22:52:37', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (272, '2019-06-17 22:53:27', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (273, '2019-06-17 23:06:02', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (274, '2019-06-17 23:07:34', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (275, '2019-06-17 23:12:25', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (276, '2019-06-18 09:49:35', '113.57.237.18', 1, 85);
INSERT INTO `user_login_log` VALUES (277, '2019-06-18 10:10:04', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (278, '2019-06-18 10:10:12', '113.57.237.9', 1, 85);
INSERT INTO `user_login_log` VALUES (279, '2019-06-18 10:47:09', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (280, '2019-06-18 11:38:50', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (281, '2019-06-18 11:38:58', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (282, '2019-06-18 11:39:12', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (283, '2019-06-18 11:46:37', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (284, '2019-06-18 11:46:47', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (285, '2019-06-18 11:47:04', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (286, '2019-06-18 11:55:35', '10.120.78.76', 1, 88);
INSERT INTO `user_login_log` VALUES (287, '2019-06-18 11:55:38', '10.120.78.76', 1, 88);
INSERT INTO `user_login_log` VALUES (288, '2019-06-18 11:55:39', '10.120.78.76', 1, 88);
INSERT INTO `user_login_log` VALUES (289, '2019-06-18 11:58:50', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (290, '2019-06-18 11:59:10', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (291, '2019-06-18 12:00:54', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (292, '2019-06-18 12:01:07', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (293, '2019-06-18 12:54:39', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (294, '2019-06-18 12:54:48', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (295, '2019-06-18 13:00:17', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (296, '2019-06-18 13:00:24', '61.143.127.226', 1, 85);
INSERT INTO `user_login_log` VALUES (297, '2019-06-18 14:54:00', '113.57.237.18', 1, 79);
INSERT INTO `user_login_log` VALUES (298, '2019-06-18 16:29:34', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (299, '2019-06-18 16:30:07', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (300, '2019-06-18 16:30:10', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (301, '2019-06-18 16:30:15', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (302, '2019-06-18 17:07:04', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (303, '2019-06-18 17:07:21', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (304, '2019-06-18 17:09:43', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (305, '2019-06-18 17:09:45', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (306, '2019-06-18 18:03:09', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (307, '2019-06-18 19:58:31', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (308, '2019-06-18 20:03:27', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (309, '2019-06-18 20:14:33', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (310, '2019-06-18 20:58:15', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (311, '2019-06-18 21:24:28', '10.120.203.1', 1, 79);
INSERT INTO `user_login_log` VALUES (312, '2019-06-18 21:50:49', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (313, '2019-06-18 21:51:20', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (314, '2019-06-19 12:12:23', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (315, '2019-06-19 19:29:18', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (316, '2019-06-19 20:29:24', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (317, '2019-06-19 20:29:32', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (318, '2019-06-20 10:57:45', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (319, '2019-06-20 11:20:26', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (320, '2019-06-20 11:25:25', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (321, '2019-06-20 11:28:55', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (322, '2019-06-20 11:30:30', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (323, '2019-06-20 11:30:51', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (324, '2019-06-20 11:30:55', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (325, '2019-06-20 11:31:08', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (326, '2019-06-21 11:31:51', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (327, '2019-06-24 09:18:04', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (328, '2019-06-24 10:03:23', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (329, '2019-06-24 10:58:16', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (330, '2019-06-24 10:58:27', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (331, '2019-06-24 11:06:08', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (332, '2019-06-24 11:06:12', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (333, '2019-06-24 11:06:20', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (334, '2019-06-24 11:06:22', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (335, '2019-06-24 11:32:42', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (336, '2019-06-24 11:32:46', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (337, '2019-06-24 11:32:54', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (338, '2019-06-24 14:30:20', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (339, '2019-06-24 14:59:50', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (340, '2019-06-24 15:04:19', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (341, '2019-06-24 15:04:23', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (342, '2019-06-24 16:17:48', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (343, '2019-06-24 18:03:56', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (344, '2019-06-24 18:12:00', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (345, '2019-06-24 20:50:46', '10.120.78.76', 1, 85);
INSERT INTO `user_login_log` VALUES (346, '2019-06-24 21:10:51', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (347, '2019-06-24 21:10:58', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (348, '2019-06-24 21:11:00', '10.120.78.76', 1, 84);
INSERT INTO `user_login_log` VALUES (349, '2019-06-26 21:39:06', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (350, '2019-06-26 21:39:29', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (351, '2019-06-27 11:41:26', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (352, '2019-06-27 11:42:03', '113.57.237.27', 1, 84);
INSERT INTO `user_login_log` VALUES (353, '2019-06-27 18:23:12', '0:0:0:0:0:0:0:1', 1, 6);
INSERT INTO `user_login_log` VALUES (354, '2019-06-27 21:41:16', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (355, '2019-06-28 21:04:16', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (356, '2019-06-28 21:07:12', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (357, '2019-06-30 19:26:25', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (358, '2019-06-30 19:35:06', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (359, '2019-06-30 19:39:53', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (360, '2019-06-30 19:49:55', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (361, '2019-06-30 20:44:44', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (362, '2019-06-30 21:24:08', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (363, '2019-07-01 10:20:21', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (364, '2019-07-01 10:49:49', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (365, '2019-07-01 10:50:51', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (366, '2019-07-01 10:55:32', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (367, '2019-07-01 10:58:35', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (368, '2019-07-01 11:02:09', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (369, '2019-07-01 11:14:49', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (370, '2019-07-01 12:16:56', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (371, '2019-07-01 12:16:58', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (372, '2019-07-01 12:17:23', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (373, '2019-07-01 12:17:24', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (374, '2019-07-01 12:17:30', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (375, '2019-07-01 12:17:33', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (376, '2019-07-01 14:47:43', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (377, '2019-07-01 14:47:46', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (378, '2019-07-01 14:47:47', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (379, '2019-07-01 14:56:54', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (380, '2019-07-01 14:57:01', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (381, '2019-07-01 14:57:03', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (382, '2019-07-01 14:59:28', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (383, '2019-07-01 14:59:32', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (384, '2019-07-01 14:59:54', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (385, '2019-07-01 15:01:59', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (386, '2019-07-01 15:02:02', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (387, '2019-07-01 15:02:02', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (388, '2019-07-01 15:03:41', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (389, '2019-07-01 15:03:44', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (390, '2019-07-01 15:03:46', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (391, '2019-07-01 15:04:44', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (392, '2019-07-01 15:04:45', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (393, '2019-07-01 15:04:47', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (394, '2019-07-01 15:09:51', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (395, '2019-07-01 15:09:53', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (396, '2019-07-01 15:09:54', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (397, '2019-07-01 15:11:23', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (398, '2019-07-01 15:11:25', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (399, '2019-07-01 15:11:27', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (400, '2019-07-01 15:17:20', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (401, '2019-07-01 15:17:21', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (402, '2019-07-01 15:17:22', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (403, '2019-07-01 15:17:23', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (404, '2019-07-01 15:19:43', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (405, '2019-07-01 15:23:40', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (406, '2019-07-01 15:23:42', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (407, '2019-07-01 15:23:42', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (408, '2019-07-01 15:27:10', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (409, '2019-07-01 15:27:14', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (410, '2019-07-01 15:27:15', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (411, '2019-07-01 15:28:54', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (412, '2019-07-01 15:28:55', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (413, '2019-07-01 15:28:56', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (414, '2019-07-01 15:30:24', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (415, '2019-07-01 15:30:25', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (416, '2019-07-01 15:30:26', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (417, '2019-07-01 15:53:47', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (418, '2019-07-01 15:53:49', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (419, '2019-07-01 15:55:20', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (420, '2019-07-01 15:55:21', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (421, '2019-07-01 15:55:22', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (422, '2019-07-01 15:55:58', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (423, '2019-07-01 15:55:59', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (424, '2019-07-01 15:55:59', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (425, '2019-07-01 15:58:11', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (426, '2019-07-01 15:58:13', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (427, '2019-07-01 15:58:14', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (428, '2019-07-02 17:57:36', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (429, '2019-07-02 17:57:40', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (430, '2019-07-02 17:57:47', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (431, '2019-07-02 17:58:32', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (432, '2019-07-02 17:58:34', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (433, '2019-07-02 17:58:35', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (434, '2019-07-03 14:46:12', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (435, '2019-07-03 14:46:16', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (436, '2019-07-03 14:46:18', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (437, '2019-07-03 14:46:45', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (438, '2019-07-03 14:58:20', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (439, '2019-07-03 14:58:21', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (440, '2019-07-03 14:58:23', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (441, '2019-07-03 14:59:45', '113.57.237.18', 1, 87);
INSERT INTO `user_login_log` VALUES (442, '2019-07-03 16:11:44', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (443, '2019-07-03 16:13:46', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (444, '2019-07-03 16:22:26', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (445, '2019-07-03 16:22:31', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (446, '2019-07-03 16:22:33', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (447, '2019-07-03 16:22:48', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (448, '2019-07-03 16:22:49', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (449, '2019-07-03 17:05:30', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (450, '2019-07-03 17:05:33', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (451, '2019-07-03 17:11:35', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (452, '2019-07-03 17:11:39', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (453, '2019-07-03 17:11:51', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (454, '2019-07-03 17:11:52', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (455, '2019-07-03 17:12:20', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (456, '2019-07-03 17:13:22', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (457, '2019-07-03 17:15:44', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (458, '2019-07-03 17:15:47', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (459, '2019-07-03 17:15:48', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (460, '2019-07-03 17:16:47', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (461, '2019-07-03 17:16:50', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (462, '2019-07-03 17:16:51', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (463, '2019-07-03 17:17:28', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (464, '2019-07-03 17:17:46', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (465, '2019-07-03 17:17:48', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (466, '2019-07-03 17:17:49', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (467, '2019-07-03 17:17:50', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (468, '2019-07-03 17:17:51', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (469, '2019-07-03 17:17:52', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (470, '2019-07-03 17:17:53', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (471, '2019-07-03 17:17:54', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (472, '2019-07-03 17:17:54', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (473, '2019-07-03 17:17:54', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (474, '2019-07-03 17:17:59', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (475, '2019-07-03 17:18:00', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (476, '2019-07-03 17:18:01', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (477, '2019-07-03 17:18:01', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (478, '2019-07-03 17:18:02', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (479, '2019-07-03 17:18:02', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (480, '2019-07-03 17:18:02', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (481, '2019-07-03 17:18:03', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (482, '2019-07-03 17:18:03', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (483, '2019-07-03 17:18:04', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (484, '2019-07-03 17:18:05', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (485, '2019-07-03 17:18:05', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (486, '2019-07-03 17:18:05', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (487, '2019-07-03 17:18:06', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (488, '2019-07-03 17:18:06', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (489, '2019-07-03 17:18:07', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (490, '2019-07-03 17:18:07', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (491, '2019-07-03 17:18:08', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (492, '2019-07-03 17:18:16', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (493, '2019-07-03 17:18:17', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (494, '2019-07-03 17:18:18', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (495, '2019-07-03 17:18:20', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (496, '2019-07-03 17:22:05', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (497, '2019-07-03 17:22:07', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (498, '2019-07-03 17:22:23', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (499, '2019-07-03 17:22:25', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (500, '2019-07-03 17:22:26', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (501, '2019-07-03 17:22:27', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (502, '2019-07-03 17:22:28', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (503, '2019-07-03 17:23:33', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (504, '2019-07-03 17:23:34', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (505, '2019-07-03 17:23:34', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (506, '2019-07-03 17:23:35', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (507, '2019-07-03 17:24:21', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (508, '2019-07-03 17:24:21', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (509, '2019-07-03 17:24:22', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (510, '2019-07-03 17:25:27', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (511, '2019-07-03 17:25:44', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (512, '2019-07-03 17:27:30', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (513, '2019-07-03 17:27:31', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (514, '2019-07-03 17:27:32', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (515, '2019-07-03 17:47:47', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (516, '2019-07-03 17:48:08', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (517, '2019-07-03 17:48:10', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (518, '2019-07-03 17:48:11', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (519, '2019-07-03 17:48:47', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (520, '2019-07-03 17:50:25', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (521, '2019-07-03 17:54:02', '113.57.237.10', 1, 87);
INSERT INTO `user_login_log` VALUES (522, '2019-07-03 17:54:02', '113.57.237.10', 1, 87);
INSERT INTO `user_login_log` VALUES (523, '2019-07-03 17:54:03', '113.57.237.10', 1, 87);
INSERT INTO `user_login_log` VALUES (524, '2019-07-03 17:56:05', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (525, '2019-07-03 18:10:21', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (526, '2019-07-03 18:10:24', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (527, '2019-07-03 18:10:25', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (528, '2019-07-03 18:11:54', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (529, '2019-07-03 18:11:55', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (530, '2019-07-03 18:12:20', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (531, '2019-07-03 19:39:19', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (532, '2019-07-03 19:39:22', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (533, '2019-07-03 19:41:19', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (534, '2019-07-03 19:51:35', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (535, '2019-07-03 19:51:37', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (536, '2019-07-03 19:51:38', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (537, '2019-07-03 20:11:36', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (538, '2019-07-03 20:13:58', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (539, '2019-07-03 20:14:01', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (540, '2019-07-03 21:02:39', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (541, '2019-07-03 21:03:36', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (542, '2019-07-03 21:35:09', '10.120.203.1', 1, 87);
INSERT INTO `user_login_log` VALUES (543, '2019-07-03 22:03:15', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (544, '2019-07-03 22:03:17', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (545, '2019-07-04 15:41:34', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (546, '2019-07-04 15:41:39', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (547, '2019-07-04 15:41:40', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (548, '2019-07-04 15:41:45', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (549, '2019-07-04 15:41:46', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (550, '2019-07-04 15:44:37', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (551, '2019-07-04 15:44:41', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (552, '2019-07-04 15:46:43', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (553, '2019-07-04 15:46:44', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (554, '2019-07-04 15:46:48', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (555, '2019-07-05 09:43:13', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (556, '2019-07-05 09:43:17', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (557, '2019-07-05 11:18:02', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (558, '2019-07-05 11:23:49', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (559, '2019-07-05 11:23:52', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (560, '2019-07-05 11:23:53', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (561, '2019-07-05 11:23:55', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (562, '2019-07-05 11:24:58', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (563, '2019-07-05 11:25:02', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (564, '2019-07-05 11:25:04', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (565, '2019-07-05 11:28:41', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (566, '2019-07-05 11:28:42', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (567, '2019-07-05 11:28:43', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (568, '2019-07-05 11:33:55', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (569, '2019-07-05 11:33:56', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (570, '2019-07-05 11:33:57', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (571, '2019-07-05 11:36:08', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (572, '2019-07-05 11:36:10', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (573, '2019-07-05 11:36:11', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (574, '2019-07-05 11:36:14', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (575, '2019-07-05 11:36:18', '113.57.237.10', 1, 84);
INSERT INTO `user_login_log` VALUES (576, '2019-07-05 11:43:08', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (577, '2019-07-05 11:43:09', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (578, '2019-07-05 11:43:10', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (579, '2019-07-05 11:43:34', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (580, '2019-07-05 11:43:35', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (581, '2019-07-05 11:43:36', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (582, '2019-07-05 11:53:11', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (583, '2019-07-05 11:53:12', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (584, '2019-07-05 11:53:13', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (585, '2019-07-05 11:59:03', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (586, '2019-07-05 11:59:04', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (587, '2019-07-05 11:59:06', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (588, '2019-07-05 17:53:21', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (589, '2019-07-05 17:53:26', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (590, '2019-07-05 17:53:30', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (591, '2019-07-05 17:53:31', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (592, '2019-07-05 17:55:24', '113.57.237.27', 1, 84);
INSERT INTO `user_login_log` VALUES (593, '2019-07-05 17:55:25', '113.57.237.27', 1, 84);
INSERT INTO `user_login_log` VALUES (594, '2019-07-05 17:55:26', '113.57.237.27', 1, 84);
INSERT INTO `user_login_log` VALUES (595, '2019-07-08 15:39:30', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (596, '2019-07-08 15:39:31', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (597, '2019-07-08 15:55:24', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (598, '2019-07-08 16:00:12', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (599, '2019-07-08 16:07:15', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (600, '2019-07-08 17:24:38', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (601, '2019-07-08 17:25:34', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (602, '2019-07-08 17:26:21', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (603, '2019-07-08 17:26:27', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (604, '2019-07-08 17:26:28', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (605, '2019-07-08 17:37:43', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (606, '2019-07-08 17:51:03', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (607, '2019-07-08 17:58:11', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (608, '2019-07-08 18:02:25', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (609, '2019-07-08 18:04:04', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (610, '2019-07-08 18:07:02', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (611, '2019-07-08 18:08:28', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (612, '2019-07-08 18:12:05', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (613, '2019-07-09 11:18:43', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (614, '2019-07-09 11:18:45', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (615, '2019-07-09 13:24:19', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (616, '2019-07-09 13:24:36', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (617, '2019-07-09 13:24:55', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (618, '2019-07-09 14:29:56', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (619, '2019-07-09 14:33:28', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (620, '2019-07-09 14:47:20', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (621, '2019-07-09 14:47:23', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (622, '2019-07-09 14:53:27', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (623, '2019-07-09 14:53:36', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (624, '2019-07-09 15:02:51', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (625, '2019-07-09 15:02:51', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (626, '2019-07-09 15:08:42', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (627, '2019-07-09 15:09:48', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (628, '2019-07-09 15:09:49', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (629, '2019-07-09 15:09:50', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (630, '2019-07-09 15:09:50', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (631, '2019-07-09 15:11:27', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (632, '2019-07-09 15:11:27', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (633, '2019-07-09 15:13:19', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (634, '2019-07-09 15:13:40', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (635, '2019-07-09 15:13:43', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (636, '2019-07-09 15:13:55', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (637, '2019-07-09 16:30:39', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (638, '2019-07-09 17:03:44', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (639, '2019-07-09 17:33:19', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (640, '2019-07-09 17:34:45', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (641, '2019-07-09 17:39:24', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (642, '2019-07-09 17:46:00', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (643, '2019-07-09 17:51:59', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (644, '2019-07-09 18:12:01', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (645, '2019-07-09 18:14:12', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (646, '2019-07-09 18:14:56', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (647, '2019-07-09 18:20:29', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (648, '2019-07-10 10:50:22', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (649, '2019-07-10 10:51:03', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (650, '2019-07-10 10:51:04', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (651, '2019-07-10 10:51:05', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (652, '2019-07-10 10:51:06', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (653, '2019-07-10 11:05:22', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (654, '2019-07-10 11:06:03', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (655, '2019-07-10 11:06:35', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (656, '2019-07-10 11:07:20', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (657, '2019-07-10 11:15:41', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (658, '2019-07-10 11:20:30', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (659, '2019-07-10 11:30:31', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (660, '2019-07-10 11:45:44', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (661, '2019-07-10 11:48:44', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (662, '2019-07-10 14:54:36', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (663, '2019-07-10 14:55:15', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (664, '2019-07-10 14:55:16', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (665, '2019-07-10 15:00:47', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (666, '2019-07-10 15:02:17', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (667, '2019-07-10 15:12:24', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (668, '2019-07-10 15:13:21', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (669, '2019-07-10 15:18:34', '0:0:0:0:0:0:0:1', 1, 79);
INSERT INTO `user_login_log` VALUES (670, '2019-07-10 15:32:34', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (671, '2019-07-10 15:38:38', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (672, '2019-07-10 15:43:38', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (673, '2019-07-10 15:50:36', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (674, '2019-07-10 16:00:42', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (675, '2019-07-10 16:04:10', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (676, '2019-07-10 16:04:12', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (677, '2019-07-10 16:04:13', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (678, '2019-07-10 16:04:15', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (679, '2019-07-10 16:07:55', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (680, '2019-07-10 16:08:11', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (681, '2019-07-10 16:08:15', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (682, '2019-07-10 16:08:19', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (683, '2019-07-10 16:10:25', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (684, '2019-07-10 16:10:45', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (685, '2019-07-10 16:11:16', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (686, '2019-07-10 17:25:26', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (687, '2019-07-10 17:50:37', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (688, '2019-07-10 17:50:53', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (689, '2019-07-10 17:51:03', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (690, '2019-07-10 17:51:23', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (691, '2019-07-10 17:51:25', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (692, '2019-07-10 17:51:25', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (693, '2019-07-10 17:51:28', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (694, '2019-07-10 17:51:40', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (695, '2019-07-10 17:51:56', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (696, '2019-07-10 17:51:58', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (697, '2019-07-10 17:58:59', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (698, '2019-07-10 17:59:55', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (699, '2019-07-10 17:59:57', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (700, '2019-07-10 18:01:06', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (701, '2019-07-10 18:01:08', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (702, '2019-07-10 18:05:16', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (703, '2019-07-10 18:09:46', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (704, '2019-07-10 18:13:57', '10.120.54.102', 1, 88);
INSERT INTO `user_login_log` VALUES (705, '2019-07-10 18:20:28', '10.120.54.102', 1, 85);
INSERT INTO `user_login_log` VALUES (706, '2019-07-10 18:20:30', '10.120.54.102', 1, 85);
INSERT INTO `user_login_log` VALUES (707, '2019-07-10 18:20:31', '10.120.54.102', 1, 85);
INSERT INTO `user_login_log` VALUES (708, '2019-07-10 18:22:24', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (709, '2019-07-10 18:22:25', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (710, '2019-07-10 18:22:42', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (711, '2019-07-10 18:22:43', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (712, '2019-07-10 18:26:47', '10.120.54.102', 1, 85);
INSERT INTO `user_login_log` VALUES (713, '2019-07-10 18:29:44', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (714, '2019-07-10 18:30:52', '113.57.237.27', 1, 85);
INSERT INTO `user_login_log` VALUES (715, '2019-07-10 18:30:53', '113.57.237.27', 1, 85);
INSERT INTO `user_login_log` VALUES (716, '2019-07-10 18:30:53', '113.57.237.27', 1, 85);
INSERT INTO `user_login_log` VALUES (717, '2019-07-10 18:34:17', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (718, '2019-07-10 18:34:38', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (719, '2019-07-10 18:34:42', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (720, '2019-07-10 18:34:43', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (721, '2019-07-10 18:34:50', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (722, '2019-07-10 18:34:51', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (723, '2019-07-10 18:34:52', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (724, '2019-07-10 18:46:58', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (725, '2019-07-10 18:46:59', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (726, '2019-07-10 18:47:00', '113.57.237.10', 1, 88);
INSERT INTO `user_login_log` VALUES (727, '2019-07-10 18:54:24', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (728, '2019-07-10 18:54:25', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (729, '2019-07-10 18:54:26', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (730, '2019-07-11 09:50:17', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (731, '2019-07-11 10:43:31', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (732, '2019-07-11 10:43:34', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (733, '2019-07-11 10:43:35', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (734, '2019-07-11 10:43:38', '113.57.237.10', 1, 85);
INSERT INTO `user_login_log` VALUES (735, '2019-07-11 17:14:53', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (736, '2019-07-11 17:14:56', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (737, '2019-07-11 17:14:57', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (738, '2019-07-11 17:15:00', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (739, '2019-07-11 17:16:28', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (740, '2019-07-11 17:16:29', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (741, '2019-07-11 17:16:30', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (742, '2019-07-11 17:39:30', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (743, '2019-07-11 17:44:21', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (744, '2019-07-11 18:07:19', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (745, '2019-07-11 18:07:26', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (746, '2019-07-11 18:17:02', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (747, '2019-07-12 13:28:03', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (748, '2019-07-12 13:28:04', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (749, '2019-07-12 13:28:06', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (750, '2019-07-12 13:28:07', '113.57.237.27', 1, 88);
INSERT INTO `user_login_log` VALUES (751, '2019-07-12 13:28:50', '113.57.237.27', 1, 85);
INSERT INTO `user_login_log` VALUES (752, '2019-07-12 13:28:52', '113.57.237.27', 1, 85);
INSERT INTO `user_login_log` VALUES (753, '2019-07-13 22:34:34', '219.140.151.194', 1, 88);
INSERT INTO `user_login_log` VALUES (754, '2019-07-13 22:34:36', '219.140.151.194', 1, 88);

-- ----------------------------
-- Table structure for withdraw_record
-- ----------------------------
DROP TABLE IF EXISTS `withdraw_record`;
CREATE TABLE `withdraw_record`  (
  `withdraw_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `withdraw_money` decimal(10, 2) NULL DEFAULT NULL,
  `tax_money` decimal(10, 2) NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 2,
  PRIMARY KEY (`withdraw_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `withdraw_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of withdraw_record
-- ----------------------------
INSERT INTO `withdraw_record` VALUES (1, 00000000002, 1.00, NULL, 'test', '2019-03-05 20:38:41', 1);
INSERT INTO `withdraw_record` VALUES (2, 00000000003, 1.00, NULL, 'test', '2019-04-03 20:41:11', 1);
INSERT INTO `withdraw_record` VALUES (3, 00000000003, 5.00, NULL, 'test', '2019-04-05 16:32:12', 1);

-- ----------------------------
-- Table structure for yield_detail
-- ----------------------------
DROP TABLE IF EXISTS `yield_detail`;
CREATE TABLE `yield_detail`  (
  `yield_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `order_detail_id` int(11) NULL DEFAULT NULL,
  `actual_paid_money` decimal(10, 2) NOT NULL,
  `received_money` decimal(10, 2) NULL DEFAULT NULL,
  `yield_rate` decimal(4, 2) NULL DEFAULT NULL,
  `purchaser_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `finish_time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`yield_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `purchaser_id`(`purchaser_id`) USING BTREE,
  INDEX `yield_detail_ibfk_5`(`order_detail_id`) USING BTREE,
  CONSTRAINT `yield_detail_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order_master` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `yield_detail_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `yield_detail_ibfk_4` FOREIGN KEY (`purchaser_id`) REFERENCES `user_login` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `yield_detail_ibfk_5` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`order_detail_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yield_detail
-- ----------------------------
INSERT INTO `yield_detail` VALUES (1, 00000000003, 1, NULL, 46.00, 0.92, 2.00, 00000000084, '2019-04-05 20:47:18', NULL, 1);
INSERT INTO `yield_detail` VALUES (2, 00000000003, 2, NULL, 14.00, 0.28, 2.00, 00000000084, '2019-04-07 13:00:10', NULL, 1);
INSERT INTO `yield_detail` VALUES (3, 00000000003, 3, NULL, 7.00, 0.14, 2.00, 00000000084, '2019-04-15 13:00:18', NULL, 1);
INSERT INTO `yield_detail` VALUES (4, 00000000003, 4, NULL, 10.00, 0.20, 2.00, 00000000085, '2019-04-23 11:09:32', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
