package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;

public class FistFrame extends Frame{  //所有‘窗口’的框架继承于Frame
	private static final long serialVersionUID = 7702620011122755607L;
	public FistFrame(String string){
		super(string);  //调用父函数的构造方法
	}
	public static void main(String args[]){
		FistFrame f=new  FistFrame("这是第一个frame程序");//构造方法的调用
		f.setSize(300, 200);  //设置大小
		f.setBackground(Color.BLUE); //设置背景颜色
		f.setVisible(true);   //设置frame可见，默认不可见
		
		f.addWindowListener(f.new Mywindowadapter());//为窗口添加监听器
	}
	//定义监听实现窗口关闭
	class Mywindowadapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent we){//覆盖windowAdapter方法
			System.exit(0);  //退出函数
		}
	}
}
