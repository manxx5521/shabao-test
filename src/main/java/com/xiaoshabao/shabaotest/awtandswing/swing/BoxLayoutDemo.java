package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.*;
import javax.swing.*;

public class BoxLayoutDemo {
	 public static void main(String[] args){
		 JFrame f=new JFrame("盒子布局管理");
		 f.setBounds(80,60,300,230);//移动组件并调整大小
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
		 int size=3;
		 Container c=f.getContentPane();//获得主面板
		 c.setLayout(new BorderLayout(30,30));//具有组件间距的布局
		 Box boxes[]=new Box[4];  //创建一个为4的box数组
		 //初始化数组
		 boxes[0]=Box.createHorizontalBox();//创建一个水平显示的Box
		 boxes[1]=Box.createVerticalBox();//垂直显示的Box
		 boxes[2]=Box.createHorizontalBox();
		 boxes[3]=Box.createVerticalBox();
		 //在boxes[0]中添加组件
		 for(int i=0;i<size;i++){  //循环添加
			 boxes[0].add(new JButton("boxes[0]["+i+"]"));
		 }
		//在boxes[1]中添加组件
		 for(int i=0;i<size;i++){  //循环添加
			 boxes[1].add(new JButton("boxes[1]["+i+"]"));
		 }
		//在boxes[2]中添加组件
		 for(int i=0;i<size;i++){  //循环添加
			 boxes[2].add(new JButton("boxes[2]["+i+"]"));
		 }
		//在boxes[3]中添加组件
		 for(int i=0;i<size;i++){  //循环添加
			 boxes[3].add(new JButton("boxes[3]["+i+"]"));
		 }
		 c.add(boxes[0],BorderLayout.NORTH);
		 c.add(boxes[1],BorderLayout.EAST);
		 c.add(boxes[2],BorderLayout.SOUTH);
		 c.add(boxes[3],BorderLayout.WEST);
		 f.setVisible(true);
	}
}
