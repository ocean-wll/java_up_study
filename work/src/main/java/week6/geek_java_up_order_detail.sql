CREATE TABLE geek_java_up.order_detail
(
    id bigint unsigned PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
    gmt_create datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    gmt_modified datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    order_id varchar(64) NOT NULL COMMENT '订单编号',
    product_id bigint unsigned NOT NULL COMMENT '商品id',
    unit_price varchar(64) NOT NULL COMMENT '单价',
    num int unsigned NOT NULL COMMENT '数量'
);