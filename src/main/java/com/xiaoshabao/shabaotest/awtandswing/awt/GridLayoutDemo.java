package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
	/*
	 * 网格布局管理
	 * */
public class GridLayoutDemo {
	private Frame f;
	private Button b1,b2,b3,b4,b5,b6;
	public GridLayoutDemo(){
		b1=new Button("【0】【0】");
		b2=new Button("【0】【1】");
		b3=new Button("【0】【2】");
		b4=new Button("【1】【0】");
		b5=new Button("【1】【1】");
		b6=new Button("【1】【2】");
	}
	public void show(){
		f=new Frame("GridLayout 演示");
		f.setSize(400,300);
		f.setLayout(new GridLayout(2,3));//设置布局管理   2行3列
		f.add(b1);//这个是顺序添加的，像html一样
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.addWindowListener(new WindowAdapter(){//创建适配监听
			@Override
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
		GridLayoutDemo gl=new GridLayoutDemo();
		gl.show();
	}

}
