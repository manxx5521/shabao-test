package com.xiaoshabao.shabaotest.awtandswing.component;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FontDemo extends Frame{
	private static final long serialVersionUID = -4116343475522358732L;

	public FontDemo(String str){
		super(str);
		addWindowListener(new WindowAdapter(){//窗口适配
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				setVisible(false);  //设置窗口不可见
				dispose();   //释放窗口组件资源
				System.exit(0);  //关闭窗口
			}
			
		});
		
	}
	//重载了paint方法，绘制了以下内容
	@SuppressWarnings("static-access")
	public void paint(Graphics g){
		Font font=new Font("Arial",Font.BOLD,18);  //设置字体
		g.setColor(Color.RED);//设置颜色
		g.setFont(font);//设置字体
		g.drawString("Font"+font.getName()+font.getSize(), 30, 50);  //输出的内容+坐标
		g.setFont(new Font("宋体",font.BOLD,36));  //设置字体
		g.setColor(new Color(0,0,0));  //设置新颜色
		g.drawString("2008 北京奥运会", 20, 120);
		
	}

	public static void main(String[] args) {
		FontDemo fd=new FontDemo("字体设置");
		fd.setBackground(Color.GREEN);
		fd.setSize(400,300);
		fd.setVisible(true);
		

	}

}
