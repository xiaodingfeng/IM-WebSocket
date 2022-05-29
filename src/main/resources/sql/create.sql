
CREATE TABLE if not exists `user`  (
  `id` bigint NOT NULL,
  `user_name` varchar(10) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态1有效',
  PRIMARY KEY (`id`)
);


CREATE TABLE if not exists `menu`  (
                         `id` bigint NOT NULL,
                         `cid` bigint NULL DEFAULT NULL COMMENT '分类',
                         `name` varchar(30) NULL DEFAULT NULL COMMENT '名称',
                         `display` int NULL DEFAULT NULL COMMENT '显示1不显示0',
                         `url` varchar(255) NULL DEFAULT NULL COMMENT '链接',
                         `ico` varchar(255) NULL DEFAULT NULL COMMENT '图标',
                         `sort` int NULL DEFAULT NULL COMMENT '顺序',
                         `status` int NULL DEFAULT NULL COMMENT '状态1有效0无效',
                         PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `category`  (
                             `id` bigint NOT NULL,
                             `name` varchar(255) NULL DEFAULT NULL,
                             `status` int NULL DEFAULT NULL COMMENT '1有效2无效',
                             PRIMARY KEY (`id`)
);

INSERT INTO `CATEGORY`(
    ID,
    NAME,
    STATUS
) SELECT 1, '默认分类', 1 FROM DUAL WHERE NOT EXISTS(SELECT 1 FROM `CATEGORY` WHERE ID = 1);