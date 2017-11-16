package com.xiaoshabao.shabaotest.awtandswing.component;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScrollbarDemo extends Frame{
	private static final long serialVersionUID = 8391379408134208057L;
	private Scrollbar slider;  //声明滚动条 	
	private Label label;
	
	public ScrollbarDemo(){
		super("滚动条测试");
		init();
	}
	public void init(){
		setLayout(new GridLayout(1,3));//一行三列的网格布局
		slider=new Scrollbar(Scrollbar.HORIZONTAL,0,1,0,100);//水平布局0，1网格内， 分0，100大小
		label=new Label("0~100");
		label.setBackground(Color.CYAN);//背景色设为蓝绿
		add(label);
		add(slider);
		setSize(300,50);
		setVisible(true);
		addWindowListener(new WindowAdapter(){//窗口适配
			@Override
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				setVisible(false);  //设置窗口不可见
				dispose();   //释放窗口组件资源
				System.exit(0);  //关闭窗口
			}
			
		});
		
	}
	public static void main(String[] args) {
		new ScrollbarDemo();
	}

}
