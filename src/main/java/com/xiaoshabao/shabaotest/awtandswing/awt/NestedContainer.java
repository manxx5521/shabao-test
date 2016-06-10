package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.*;
import java.awt.event.*;

//嵌套程序
public class NestedContainer {
	private Frame f;
	private Panel pn,p;
	private Button bw,bc,bo;
	
	public void show(){
		f=new Frame("容器的嵌套使用");
		bo=new Button("打开");
		bc=new Button("关闭");
		pn=new Panel();
		pn.setBackground(Color.GREEN); //设置背景色 绿
		pn.add(bo);
		pn.add(bc);
		f.add(pn,"North");  //添加面板到边界布局
		bw=new Button("左西");
		f.add(bw,"West");   //添加到布局
		p=new Panel();
		p.setBackground(Color.RED);
		f.add(p,"Center");  //添加到中间
		f.setSize(400,300);
		f.setVisible(true);
		//添加监听
		f.addWindowListener(new WindowAdapter(){//窗口适配
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				f.setVisible(false);  //设置窗口不可见
				f.dispose();   //释放窗口组件资源
				System.exit(0);  //关闭窗口
			}
					
		});
	}
					
	

	public static void main(String[] args) {
		new NestedContainer().show();
	}

}
