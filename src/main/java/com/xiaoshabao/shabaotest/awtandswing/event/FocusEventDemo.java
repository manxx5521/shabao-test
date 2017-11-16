package com.xiaoshabao.shabaotest.awtandswing.event;

import java.awt.*;
import java.awt.event.*;

public class FocusEventDemo extends Frame{
	private static final long serialVersionUID = 3762495049449870227L;
	TextArea textarea;
	TextField tf;
	public FocusEventDemo(String str){
		super(str);
		init();
	}
	public void init(){
		setLayout(new GridLayout(2,1));//网格布局
		textarea=new TextArea();  //初始化文本域
		textarea.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent eve){
				textarea.setText("文本区获得聚焦");//设置内容
			}
			@Override
			public void focusLost(FocusEvent eve){
				textarea.setText("文本区失去聚焦");//设置内容
			}
		});
		
		tf=new TextField();//初始化文本
		tf.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent eve){
				tf.setText("文本区获得聚焦");//设置内容
			}
			@Override
			public void focusLost(FocusEvent eve){
				tf.setText("文本区失去聚焦");//设置内容
			}
		});
		
		add(textarea);
		add(tf);
		setSize(300,200);
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
		new FocusEventDemo("点击事件");
	}

}
