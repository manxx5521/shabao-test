package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
	/*
	 * 边界布局管理器，有东南西北中五个位置布局
	 * */

public class BorderLayoutDemo {
	private Frame f;
	private Button b1,b2,b3,b4,b5;
	public BorderLayoutDemo(){
		b1=new Button("上北");
		b2=new Button("下南");
		b3=new Button("左西");
		b4=new Button("右东");
		b5=new Button("中间");
	}
	public void show(){
		f=new Frame("BroderLayout 演示");
		f.setSize(400,300);
		f.setLayout(new BorderLayout());//设置布局管理
		f.add(BorderLayout.NORTH,b1);
		f.add(BorderLayout.SOUTH,b2);
		f.add(BorderLayout.WEST,b3);
		f.add(BorderLayout.EAST,b4);
		f.add(BorderLayout.CENTER,b5);
		f.addWindowListener(new WindowAdapter(){//创建适配监听
			public void windowClosing(WindowEvent we){//重写了windowClosing
				f.setVisible(false);  //设置不显示
				f.dispose();  //释放窗口组件资源
				System.exit(0);  //退出
			}
		});
		//f.pack();//重新设置大小，让窗口尽量的小.可选
		f.setVisible(true);  //显示
		
	}
	
	public static void main(String[] args) {
		BorderLayoutDemo bl=new BorderLayoutDemo();
		bl.show();
	}

}
