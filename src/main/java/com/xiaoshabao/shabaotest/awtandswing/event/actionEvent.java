package com.xiaoshabao.shabaotest.awtandswing.event;

import java.awt.*;
import java.awt.event.*;

//动作事件，点击 回车等
public class actionEvent {

	public static void main(String[] args) {
		final Frame f=new Frame("演示动作事件");
		Button b=new Button("提交");
		
		//给Button设置一个actionlistener注册一个监听
		b.addActionListener(new ButtonHandler());//提交给ButtonHandler类，也可以直接在这个方法写实现的
		f.setLayout(new FlowLayout());
		f.add(b);
		f.setSize(100,100);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter(){//窗口适配
			@Override
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				f.setVisible(false);  //设置窗口不可见
				f.dispose();   //释放窗口组件资源
				System.exit(0);  //关闭窗口
			}
			
		});

	}
	
	
}
//这个类处理actionEvent
class ButtonHandler implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("提交一次");
	}
}

