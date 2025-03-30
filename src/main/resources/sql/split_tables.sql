-- 将user表拆分为normal_user和admin表

-- 创建普通用户表
DROP TABLE IF EXISTS `normal_user`;
CREATE TABLE `normal_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 创建管理员表
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- 迁移数据：将原表中的普通用户数据迁移到normal_user表
INSERT INTO normal_user (username, password, telephone)
SELECT username, password, telephone
FROM user
WHERE identity = 0;

-- 迁移数据：将原表中的管理员数据迁移到admin表
INSERT INTO admin (username, password, telephone)
SELECT username, password, telephone
FROM user
WHERE identity = 1;

-- 修改passenger表中的用户外键关联
-- 注意：需要确认所有的用户ID在新表中一致，否则这一步可能失败
-- 此处仅显示修改模板，实际操作可能需要先查看数据并手动处理

-- 使用以下命令查看用户ID映射关系
-- SELECT u.id AS old_id, nu.id AS new_id, u.username 
-- FROM user u 
-- JOIN normal_user nu ON u.username = nu.username
-- WHERE u.identity = 0;

-- 更新passenger表的外键引用，指向新表
-- UPDATE passenger p
-- JOIN user u ON p.user_id = u.id
-- JOIN normal_user nu ON u.username = nu.username
-- SET p.user_id = nu.id
-- WHERE u.identity = 0;

-- 以下是测试数据，请根据实际情况调整

-- 为普通用户表添加测试数据
INSERT INTO `normal_user` VALUES (1, 'user1', '123456', '15974156');
INSERT INTO `normal_user` VALUES (2, 'user2', '123456', '114514');

-- 为管理员表添加测试数据
INSERT INTO `admin` VALUES (1, 'admin', '123456', '123456789'); 