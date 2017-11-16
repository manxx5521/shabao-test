package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FlowLayoutDemo{
	private Button b1,b2,b3;   //声明三个按钮
	private Frame f;  //声明一个窗口
	//声明构造方法
	public FlowLayoutDemo(){
		b1=new Button("继续");  //构建按钮
		b2=new Button("取消");
		b3=new Button("确定");
	}
	public void show(){ //定义展现窗口的方法
		f=new Frame("FlowLayout布局");  //创建窗口
		f.setSize(300,240);
		//设置布局管理器Layout,居中，行 间隔30，列间隔20
		f.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
		f.add(b1);//添加按钮
		f.add(b2);
		f.add(b3);
		f.setVisible(true);  //显示窗口
		f.pack();  //设置窗口的大小为组件的大小，让窗口尽量的小。可选
		//添加监听
		f.addWindowListener(new WindowAdapter(){//窗口适配
			@Override
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				f.setVisible(false);  //设置窗口不可见
				f.dispose();   //释放窗口组件资源
				System.exit(0);  //关闭窗口
			}
			
		});
			
	}

	public static void main(String[] args) {
		FlowLayoutDemo fl=new FlowLayoutDemo();
		fl.show();
	}

}
