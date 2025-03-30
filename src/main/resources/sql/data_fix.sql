-- 修复数据库表结构和数据以确保乘客信息正确显示

-- 确保存在normal_user表
DROP TABLE IF EXISTS `normal_user`;
CREATE TABLE `normal_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 确保存在admin表
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 确保存在passenger表
DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_num` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_num`(`id_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 确保存在flight表
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` date NOT NULL,
  `num` int NOT NULL COMMENT '票价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 确保存在orders表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `passenger_id` int NULL DEFAULT NULL,
  `flight_id` int NULL DEFAULT NULL,
  UNIQUE INDEX `passenger_id`(`passenger_id`, `flight_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 插入测试数据
-- 添加普通用户
INSERT INTO `normal_user` VALUES (1, 'user1', '123456', '13800138001');
INSERT INTO `normal_user` VALUES (2, 'user2', '123456', '13900139002');
INSERT INTO `normal_user` VALUES (3, 'user3', '123456', '13700137003');

-- 添加管理员
INSERT INTO `admin` VALUES (1, 'admin', '123456', '13600136001');

-- 添加航班
INSERT INTO `flight` VALUES (1, '北京', '上海', '2023-05-20', 120);
INSERT INTO `flight` VALUES (2, '广州', '深圳', '2023-05-21', 80);
INSERT INTO `flight` VALUES (3, '成都', '重庆', '2023-05-22', 100);
INSERT INTO `flight` VALUES (4, '杭州', '南京', '2023-05-23', 90);
INSERT INTO `flight` VALUES (5, '武汉', '长沙', '2023-05-24', 110);

-- 为用户添加乘客
-- user1的乘客
INSERT INTO `passenger` VALUES (1, '张三', '110101199001011234', 1);
INSERT INTO `passenger` VALUES (2, '李四', '110101199001021235', 1);
INSERT INTO `passenger` VALUES (3, '王五', '110101199001031236', 1);

-- user2的乘客
INSERT INTO `passenger` VALUES (4, '赵六', '110101199001041237', 2);
INSERT INTO `passenger` VALUES (5, '钱七', '110101199001051238', 2);

-- user3的乘客
INSERT INTO `passenger` VALUES (6, '孙八', '110101199001061239', 3);
INSERT INTO `passenger` VALUES (7, '周九', '110101199001071230', 3);

-- 添加订单关系
INSERT INTO `orders` VALUES (1, 1);
INSERT INTO `orders` VALUES (1, 2);
INSERT INTO `orders` VALUES (2, 3);
INSERT INTO `orders` VALUES (3, 4);
INSERT INTO `orders` VALUES (4, 5);
INSERT INTO `orders` VALUES (5, 1);
INSERT INTO `orders` VALUES (6, 2);
INSERT INTO `orders` VALUES (7, 3); 