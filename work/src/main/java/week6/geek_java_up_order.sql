CREATE TABLE geek_java_up.`order`
(
    id bigint unsigned PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
    gmt_create datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    gmt_modified datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    order_id varchar(32) NOT NULL COMMENT '订单id',
    user_id bigint unsigned NOT NULL COMMENT '用户id',
    total_money varchar(64) NOT NULL COMMENT '金额',
    status tinyint unsigned NOT NULL COMMENT '订单状态',
    address varchar(256) NOT NULL COMMENT '收货信息'
);