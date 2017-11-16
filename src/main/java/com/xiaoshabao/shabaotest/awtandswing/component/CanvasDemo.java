package com.xiaoshabao.shabaotest.awtandswing.component;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//画布
public class CanvasDemo extends Panel{
	private static final long serialVersionUID = 1L;
	private Frame f;
	private MyCanvas mc;
	class MyCanvas extends Canvas{
		private static final long serialVersionUID = -2546405284547652865L;

		//重载paint（）方法  绘图方法
		@Override
		public void paint(Graphics g){
			g.setColor(Color.RED);  //设置绘图颜色
			g.drawRect(100, 40, 100, 100);//绘制矩形，前两个数字坐标，后两个长宽
			g.drawString("Canvas", 100, 50);//绘制字符和坐标
		}
	}
	public CanvasDemo(){  //构造
		f=new Frame("绘图");
		mc=new MyCanvas();  //初始化画布
		mc.repaint(0,0,100,100); //重新绘制一个画布坐标 0.0  大小100*100
		add("Center",mc);  //将画布添加到center位置
		f.add(mc);  //窗口中添加mc
		f.setSize(300,200);
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
	public static void main(String[] args){
		new CanvasDemo();
	}
}
