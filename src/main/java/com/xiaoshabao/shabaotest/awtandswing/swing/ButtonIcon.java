package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonIcon extends JPanel implements ActionListener{
	private static final long serialVersionUID = 7516560970575563882L;
	public static void main(String[] args){
		JFrame jf=new JFrame();  //创建窗口
		ButtonIcon bi=new ButtonIcon(); //创建图标对象bi
		Container c=jf.getContentPane();  //获得内容面板
		c.add(bi);   //添加bi
		jf.setSize(600,400);
		jf.setVisible(true);
		jf.addWindowListener(new WindowAdapter(){//窗口适配
			//匿名类注册监听
			@Override
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				System.exit(0);  //关闭窗口
			}
		});
	}
	
	JButton button[];
	ImageIcon imageIcon[];
	//声明构造方法
	public ButtonIcon(){//这个类其实是一个面板类
		super();
		button=new JButton[3];  //实例化数据
		imageIcon=new ImageIcon[3];
		imageIcon[0]=new ImageIcon("src/swing/images/100.gif");  //实例化数据
		imageIcon[1]=new ImageIcon("src/swing/images/101.jpg");
		imageIcon[2]=new ImageIcon("src/swing/images/102.jpg");
		button[0]=new JButton(imageIcon[0]);  //将上边的图片装载到button中
		button[1]=new JButton(imageIcon[1]);
		button[2]=new JButton(imageIcon[2]);
		add(button[0]);          //向面板中加入jbutton
		add(button[1]);
		add(button[2]);
		button[0].addActionListener(this);   //添加actionlistener监听
		button[1].addActionListener(this);  //因为继承了actionlistener接口
		button[2].addActionListener(this);   //所以重写他的方法就可以实现监听		
	}
	//实现actionlistener监听的方法
	@Override
	public void actionPerformed(ActionEvent e){
		ImageIcon temp;  //创建一个内部imageicon
		if((JButton)e.getSource()==button[0]){  //根据源判断
			for(int i=0;i<3;i++){
				button[i].setIcon(imageIcon[(i+1)%3]);  //调换显示
			}
			//更换图标
			temp=imageIcon[0];
			imageIcon[0]=imageIcon[1];
			imageIcon[1]=imageIcon[2];
			imageIcon[2]=temp;
			temp=null;
		}else if((JButton)e.getSource()==button[2]){
			for(int i=0;i<3;i++){
				button[i].setIcon(imageIcon[(i+2)%3]);  //调换显示
			}
			temp=imageIcon[2];
			imageIcon[2]=imageIcon[1];
			imageIcon[1]=imageIcon[0];
			imageIcon[0]=temp;
			temp=null;	
		}
	}
	
}
