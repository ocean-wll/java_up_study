CREATE TABLE geek_java_up.user
(
    id bigint unsigned PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
    gmt_create datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    gmt_modified datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    user_id bigint unsigned NOT NULL COMMENT '用户ID',
    user_name varchar(32) NOT NULL COMMENT '用户名',
    password varchar(16) NOT NULL COMMENT '密码',
    phone varchar(11) NOT NULL COMMENT '手机号'
);
CREATE UNIQUE INDEX user_user_id_uindex ON geek_java_up.user (user_id);