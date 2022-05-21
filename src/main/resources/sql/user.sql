DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL,
  `user_name` varchar(10) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态1有效',
  PRIMARY KEY (`id`)
);

