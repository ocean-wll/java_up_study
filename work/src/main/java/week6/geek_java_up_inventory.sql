CREATE TABLE geek_java_up.inventory
(
    id bigint unsigned PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
    gmt_create datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    gmt_modified datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    warehouse_id bigint unsigned NOT NULL COMMENT '仓库ID',
    product_id bigint unsigned NOT NULL COMMENT '商品ID',
    num int unsigned NOT NULL COMMENT '库存数'
);