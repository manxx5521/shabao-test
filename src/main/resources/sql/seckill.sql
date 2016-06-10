--创建数据库
CREATE DATABASE shabaotest;
--使用数据库
USE shabaotest;
--创建秒杀库存表
CREATE TABLE  `td_f_seckill` (
`seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT '商品名字',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL  COMMENT '秒杀开始时间',
`end_time` timestamp NOT NULL  COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_tmie(end_time),
key idx_create_time(create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';
-- ENGINE=InnoDB 引擎
--AUTO_INCREMENT=1000 自增主键初始值

----秒杀成功明细表
CREATE TABLE `td_f_seckill_success`(
`seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint(11) NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1无效,0成功,1已付款',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';