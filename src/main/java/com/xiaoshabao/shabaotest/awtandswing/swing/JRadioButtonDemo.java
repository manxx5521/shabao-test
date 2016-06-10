package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRadioButtonDemo extends JPanel implements ActionListener{

	private static final long serialVersionUID = 4655927924504494807L;
	public static void main(String[] args) {
		JFrame jf=new JFrame();
		JRadioButtonDemo bi=new JRadioButtonDemo();
		jf.getContentPane().add(bi);
		jf.setSize(800,400);
		jf.setVisible(true);
		jf.addWindowListener(new WindowAdapter(){//窗口适配
			//匿名类注册监听
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				System.exit(0);  //关闭窗口
			}
		});
	}
	JRadioButton button[];
	ImageIcon imageIcon[];
	JTextField tf;
	Panel panel;
	
	public JRadioButtonDemo(){
		super();
		this.setLayout(new GridLayout(2,1));//网格布局管理器
		button=new JRadioButton[3];
		imageIcon=new ImageIcon[6];
		tf=new JTextField(20);
		add(tf);  //添加tf
		
		ButtonGroup bg=new ButtonGroup();  //创建一个单选组
		panel=new Panel();
		//初始化图标组
		imageIcon[0]=new ImageIcon("src/swing/images/101.jpg");
		imageIcon[1]=new ImageIcon("src/swing/images/102.jpg");
		imageIcon[2]=new ImageIcon("src/swing/images/103.jpg");
		imageIcon[3]=new ImageIcon("src/swing/images/201.jpg");
		imageIcon[4]=new ImageIcon("src/swing/images/202.jpg");
		imageIcon[5]=new ImageIcon("src/swing/images/203.jpg");
		//初始化按钮
		button[0]=new JRadioButton(imageIcon[0]);
		button[1]=new JRadioButton(imageIcon[1]);
		button[2]=new JRadioButton(imageIcon[2]);
		//将按钮添加到bg组里
		bg.add(button[0]);
		bg.add(button[1]);
		bg.add(button[2]);
		//将按钮添加到 panel面板
		panel.add(button[0]);
		panel.add(button[1]);
		panel.add(button[2]);
		//将这个面板添加到内容面板
		add(panel);
		button[0].addActionListener(this);//添加actionlistener监听
		button[1].addActionListener(this);
		button[2].addActionListener(this);	
	}
	public void actionPerformed(ActionEvent e){
		String select="";
		for(int i=0;i<button.length;i++){
			if(button[i].isSelected()){//isSelected返回按钮的状态
				select=select+(i==0?"凡人修真传":i==1?"仙逆":"遮天");
				button[i].setIcon(imageIcon[i+3]);
			}else{
				button[i].setIcon(imageIcon[i]);
			}
		}
		tf.setText(select);
	}

}
