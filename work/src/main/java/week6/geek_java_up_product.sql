CREATE TABLE geek_java_up.product
(
    id bigint unsigned PRIMARY KEY NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    gmt_create datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    gmt_modified datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    product_id bigint unsigned NOT NULL COMMENT '商品ID',
    product_name bigint unsigned NOT NULL COMMENT '商品名',
    type tinyint unsigned NOT NULL COMMENT '商品分类',
    weight decimal(6,2) NOT NULL COMMENT '商品重量，单位：kg',
    money varchar(64) NOT NULL COMMENT '商品单价'
);