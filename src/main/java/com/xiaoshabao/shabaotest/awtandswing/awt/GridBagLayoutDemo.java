package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridBagLayoutDemo extends Frame{
	private static final long serialVersionUID = -6998785035348051387L;
	Label l1,l2,l3,l4;  //标签
	TextField tf1,tf2,tf3;  //文本域
	Button bt1,bt2;  //按钮
	CheckboxGroup cbg;  //多选框组
	Checkbox cb1,cb2,cb3,cb4;  //复选狂
	GridBagLayout gb; //网包管理器
	GridBagConstraints gbc;  //网包管理容器
	
	public GridBagLayoutDemo(String title){  //构造
		super(title); //调用父类
		l1=new Label("用户名");  //初始化
		l2=new Label("密码");  //初始化
		l3=new Label("重复密码");  //初始化
		l4=new Label("获取路径");  //初始化
		tf1=new TextField(20);   //初始化 大小
		tf2=new TextField(20);   //初始化 大小
		tf3=new TextField(20);   //初始化 大小
		
		gb=new GridBagLayout();  //初始化网包布局管理器
		setLayout(gb);  //设置布局管理器
		
		//复选框的设计
		gbc=new GridBagConstraints();  //初始化容器
		Panel p=new Panel();  //初始化面板
		cbg=new CheckboxGroup();  //初始化多选框组
		cb1=new Checkbox("搜索",cbg,false);  //显示名字，属于哪个复选框组，是否勾选
		cb2=new Checkbox("广告",cbg,false);
		cb3=new Checkbox("朋友",cbg,false);
		cb4=new Checkbox("其他",cbg,false);
		p.add(cb1);
		p.add(cb2);
		p.add(cb3);
		p.add(cb4);  //向面板中添加这个复选框组
		
		bt1=new Button("提交");
		bt2=new Button("重置");
		Panel p1=new Panel(); //创建一个面板保存 上边两个按钮
		p1.add(bt1);
		p1.add(bt2);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		gbc.fill=GridBagConstraints.HORIZONTAL;  //设置gbc的fill域   单元大于组件是如何填充
											/*
											 * NONE 组件不变
											 * HORIZOTAL  水平填充
											 * VERTICAL  水平垂直
											 * BOTH   填充全部
											 * */
		
		/*自己调整后也正常了
		addComponent(l1,0,0,1,1);
		addComponent(tf1,0,2,1,4);   //测试了下这里的textfield大约占gridy为4，小于4会不正常
		addComponent(l2,4,0,1,1);
		addComponent(tf2,4,2,1,4);
		addComponent(l3,8,0,1,1);
		addComponent(tf3,8,2,1,4);
		addComponent(l4,12,0,1,1);
		addComponent(p,12,2,1,1);
		addComponent(p1,13,2,1,5);	 */
		
		//就是和上边的textfield所占的空间不同可以不同的布局
		addComponent(l1,0,0,1,1);
		addComponent(tf1,0,2,1,1);  
		addComponent(l2,2,0,1,1);
		addComponent(tf2,2,2,1,1);
		addComponent(l3,4,0,1,1);
		addComponent(tf3,4,2,1,1);
		addComponent(l4,6,0,1,1);
		addComponent(p,6,2,1,4);
		addComponent(p1,10,2,1,2);
	}
	
	//添加组件的方法
	public void addComponent(Component c,int row,int col,int nrow,int ncol){
		gbc.gridx=col;  //设置组件显示区域的 开始 边单元格           距离左边的格数
		gbc.gridy=row;   //设置组件显示区域的 顶端 边单元格         距离顶边的格数
		gbc.gridheight=ncol;  //设置组件显示区域的 一列的 单元格数         显示区域分网格 的列数   这个显示区又为一个网格
		gbc.gridwidth=nrow;   //设置组件显示区域的 一行 的单元 格数            显示区域分网格 的行数         -----
		 					//这个也占用gridx的行数
		
		gb.setConstraints(c, gbc);  //设置布局的约束条件  网包管理容器
		add(c);   //把组件c添加到容器
	}
	public static void main(String[] args){
		//初始化界面
		GridBagLayoutDemo mygb=new GridBagLayoutDemo("网包布局");
		mygb.setSize(300,200);
		mygb.setVisible(true);
	}
}
