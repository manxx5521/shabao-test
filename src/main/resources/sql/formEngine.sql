/**
 表单引擎1.0
 */
/*模版表*/
create table td_ui_template(
template_id varchar(10) not null comment '模版id',
template_name varchar(30) not null comment '模版名字',
engine_type varchar(10) comment '引擎类型',
remark varchar(40) comment '模版描述',
primary key (template_id),
)engine=INNODB default CHARSET=utf8 comment='模版表';
/*单据表*/
create table td_ui_bill(
bill_id varchar(10) not null comment '单据id',
bill_name varchar(30) not null comment '单据名字',
bill_desc varchar(40) comment '单据描述',
list_template_id varchar(10) comment '列表模版',
view_template_id varchar(10) comment '展现模版',
primary key (bill_id),
CONSTRAINT pk_td_ui_bill_pk1 FOREIGN KEY (list_template_id) REFERENCES td_ui_template(template_id),
CONSTRAINT pk_td_ui_bill_pk2 FOREIGN KEY (view_template_id) REFERENCES td_ui_template(template_id)
)engine=INNODB default CHARSET=utf8 comment='单据表';

/*元素表*/
CREATE TABLE td_ui_element (
  	element_id varchar(10) NOT NULL comment '元素id',
	element_type varchar(10) NOT NULL comment '类型 tree，select等',
	element_name varchar(30) NOT NULL comment '元素名字',
	element_desc varchar(100) comment '描述',
  	params text comment '参数',
  	session_tag int(1) default 0 comment '是否使用session参数，1是0不' not null,
  	version int(2) default 1 comment '版本',
	view_template text comment '显示模版',
	read_template text comment '只读模版',
  	PRIMARY KEY  (element_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/**模版元素关联表*/
create table td_ui_template_element(
template_id varchar(10) not null comment '模版id',
element_id varchar(10) not null comment '元素id',
form_type varchar(10) not null comment '表单类型 condition tab list等',
form_key varchar(20) not null comment '表单key元素名字 ',
label varchar(20) not null comment '表单显示',
form_params text comment '可能存在的个性参数',
form_desc varchar(30) comment '描述',
order_no int(6) comment '排序',
required int(1) default 0 comment '是否必填1是，0否',
view_type varchar(10) default 'view' comment '显示模版',
read_type varchar(10) default 'read' comment '只读模版',
primary key (template_id,element_id),
CONSTRAINT pk_template_id FOREIGN KEY (template_id) REFERENCES td_ui_template(template_id),
CONSTRAINT pk_element_id FOREIGN KEY (element_id) REFERENCES td_ui_element(element_id),
constraint ch_required check (required in (1,0))
)engine=INNODB default CHARSET=utf8 comment='模版元素关联表';