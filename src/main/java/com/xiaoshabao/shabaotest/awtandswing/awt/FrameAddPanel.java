package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.*;

public class FrameAddPanel extends Frame{  //面板panel加到窗口Frame里
	private static final long serialVersionUID = -5572755464499211787L;
	public FrameAddPanel(String str){
		super(str);  //调用父类构造
	}
	public static void main(String[] args) {
		FrameAddPanel f=new FrameAddPanel("在Frame中添加Panel");
		Panel p=new Panel();  //创建并初始化panel面板
		f.setSize(400,300);  //设置大小
		f.setBackground(Color.BLUE);  //设置背景颜色
		f.setLayout(null);  //取消布局管理器，只有取消才可设置组件大小
		
		p.setSize(200,200); //设置面板大小
		p.setBackground(Color.RED);  //设置面板背景色
		
		f.add(p);  //将面板panel加到 frame中
		
		f.setVisible(true);  //显示frame
		f.addWindowListener(f.new Mywindowadapter());//为窗口添加监听器
	}
	//定义监听实现窗口关闭
	class Mywindowadapter extends WindowAdapter{
		public void windowClosing(WindowEvent we){//覆盖windowAdapter方法
			System.exit(0);  //退出函数
		}
	}

}
