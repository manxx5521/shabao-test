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
	exp_day int(2) monment '天',
	exp_hours int(2) comment '小时',
	exp_minutes int(2) comment '分',
	exp_seconds int(2) commnent '秒',
	expression varchar(20) not null '表达式',
	status varchar(1) comment '运行状态',
	sys_type varchar(10) comment '系统类型',
	upate_tag varchar(1) comment '修改标志1可以',
	restart_tag varchar(1) comment '是否可以重试',
	update_user varchar(20) comment '创建人',
	update_time date comment '创建时间'
  	PRIMARY KEY  (work_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**task任务表**/
CREATE TABLE td_w_task (
	task_id	int(8) not null,
	task_name varchar(50) NOT NULL comment 'task名称',
	task_type int(1) not null comment '类型  1存过 2java程序，',
	content varchar(50) not null comment '执行内容',
	remark varchar(50) comment '备注',
	parallel int(1) not null default '0' comment '是否可以并行 1可以'
	used int(1) comment '是否使用',
	update_user varchar(20) comment '创建人',
	update_time date comment '创建时间',
  	PRIMARY KEY  (task_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table td_w_task 
add constraint td_w_task_f1 foreign key (work_id) references td_w_work(work_id);

/**路由表*/
CREATE TABLE td_w_route (
  	work_id int(5) NOT NULL,
	start_task_id	int(8) not null,
	end_task_id int(8) not null,
	remark varchar(50) comment '备注',
	used varchar(1) comment '是否使用',
	update_user varchar(20) comment '创建人',
	update_time date comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**执行日志*/
CREATE TABLE td_w_exelog (
	log_id String(16) not null,
  	work_id int(5) NOT NULL,
  	task_idint(8) comment '可能存在的taskid',
  	type int(1) not null comment '1-workinfo,2-taskinfo,3-debug',
	status	varchar() not null comment '执行状态 2完成，40错误',
	content varchar(50) not null comment '信息内容',
	remark varchar(100) comment '备注',
	update_time date comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table td_w_exelog 
add constraint td_w_exelog_f1 foreign key (work_id) references td_w_work(work_id);

/**执行配置*/
CREATE TABLE td_w_config (
  	config_id varchar(10) NOT NULL,
	config_value varchar(50) not null,
	remark varchar(50);
	used int(1) not null default '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/**
 配置包括  SYS_TYPE  启动的系统类型（可多条）
 */
insert into td_w_config(config_id,config_value,remark,used)
values ('SYS_GROUP','SYS','执行的系统组','1');





