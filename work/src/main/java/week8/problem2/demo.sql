create schema ocean_db_demo_0;
create schema ocean_db_demo_1;

CREATE TABLE IF NOT EXISTS ocean_db_demo_0.ocean_demo_order_0
(
    id bigint unsigned PRIMARY KEY COMMENT '主键' AUTO_INCREMENT,
    user_id bigint unsigned NOT NULL COMMENT '用户id',
    status varchar(255) COMMENT '订单状态'
    );
CREATE TABLE IF NOT EXISTS ocean_db_demo_0.ocean_demo_order_1
(
    id bigint unsigned PRIMARY KEY COMMENT '主键' AUTO_INCREMENT,
    user_id bigint unsigned NOT NULL COMMENT '用户id',
    status varchar(255) COMMENT '订单状态'
    );
CREATE TABLE IF NOT EXISTS ocean_db_demo_1.ocean_demo_order_0
(
    id bigint unsigned PRIMARY KEY COMMENT '主键' AUTO_INCREMENT,
    user_id bigint unsigned NOT NULL COMMENT '用户id',
    status varchar(255) COMMENT '订单状态'
    );
CREATE TABLE IF NOT EXISTS ocean_db_demo_1.ocean_demo_order_1
(
    id bigint unsigned PRIMARY KEY COMMENT '主键' AUTO_INCREMENT,
    user_id bigint unsigned NOT NULL COMMENT '用户id',
    status varchar(255) COMMENT '订单状态'
    );