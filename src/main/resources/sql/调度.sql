/**work**/
CREATE TABLE td_w_work (
  	work_id int(5) NOT NULL,
	work_name varchar(50) NOT NULL comment '任务名称',
	remark varchar(50) comment '备注',
	start_time date NOT NULL comment '开始时间',
	end_time date comment '结束时间',
	work_type varchar(1) not null comment '类型1简单，2日历',
	exe_num int(3) default 0 comment '运行次数0无限次',
	exp_month int(2) comment'月份',
	exp_day int(2) comment '天',
	exp_hours int(2) comment '小时',
	exp_minutes int(2) comment '分',
	exp_seconds int(2) comment '秒',
	expression varchar(20) not null comment '表达式',
	state varchar(1) comment '运行状态',
	sys_type varchar(10) comment '系统类型',
	upate_tag varchar(1) comment '修改标志1可以',
	restart_tag varchar(1) comment '是否可以重试',
	update_user varchar(20) comment '创建人',
	update_time date comment '创建时间',
  	PRIMARY KEY  (work_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='work工作组';

/**task任务表**/
CREATE TABLE td_w_task (
	task_id INT (8) NOT NULL,
	task_name VARCHAR (50) NOT NULL COMMENT 'task名称',
	task_type INT (1) NOT NULL COMMENT '类型  1存过 2java程序，',
	content VARCHAR (50) NOT NULL COMMENT '执行内容',
	remark VARCHAR (50) COMMENT '备注',
	parallel INT (1) NOT NULL DEFAULT '0' COMMENT '是否可以并行 1可以',
	used INT (1) COMMENT '是否使用',
	update_user VARCHAR (20) COMMENT '创建人',
	update_time date COMMENT '创建时间',
	PRIMARY KEY (task_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT='任务表';

/**路由表*/
CREATE TABLE td_w_route (
	work_id INT (5) NOT NULL,
	start_task_id INT (8) NOT NULL,
	end_task_id INT (8) NOT NULL,
	remark VARCHAR (50) COMMENT '备注',
	used VARCHAR (1) COMMENT '是否使用',
	update_user VARCHAR (20) COMMENT '创建人',
	update_time date COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT='路由表';

/**执行日志*/
CREATE TABLE td_w_exelog (
	log_id varchar(16) NOT NULL,
	work_id INT (5) NOT NULL,
	task_id int (8) COMMENT '可能存在的taskid',
	type INT (1) NOT NULL COMMENT '1-workinfo,2-taskinfo,3-debug',
	state int(2) NOT NULL COMMENT '执行状态 2完成，40错误',
	content VARCHAR (50) NOT NULL COMMENT '信息内容',
	remark VARCHAR (100) COMMENT '备注',
	update_time date COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT='日志表';

/**执行配置*/
CREATE TABLE td_w_config (
  	config_id varchar(10) NOT NULL,
	config_value varchar(50) not null,
	remark varchar(50),
	used int(1) not null default '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';
/**
 配置包括  SYS_TYPE  启动的系统类型（可多条）
 */
insert into td_w_config(config_id,config_value,remark,used)
values ('SYS_GROUP','SYS','执行的系统组','1');
