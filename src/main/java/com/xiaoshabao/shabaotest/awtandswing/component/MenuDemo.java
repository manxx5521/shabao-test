package com.xiaoshabao.shabaotest.awtandswing.component;

import java.awt.*;
import java.awt.event.*;

public class MenuDemo extends Frame{
	private static final long serialVersionUID = -6919578898789601835L;
	PopupMenu pop;  //声明弹出型 菜单
	public MenuDemo(String str){
		super(str);  //调用父类构造
		
		addWindowListener(new WindowAdapter(){//窗口适配
			@Override
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				setVisible(false);  //设置窗口不可见
				dispose();   //释放窗口组件资源
				System.exit(0);  //关闭窗口
			}
			
		});
		setSize(400,300);
		
		 //创建初始化一个menu菜单
		Menu menu=new Menu("文件"); 
		menu.add("新建");
		menu.add("打开");
		menu.add("关闭");
		menu.add("退出");
		
		Menu irons=new Menu("编辑");  //创建初始化一个menu菜单
		irons.add("重写");
		irons.add("复制");
		irons.add("删除");
		irons.add("代替");
		irons.add("查找");
		irons.addSeparator();  //添加分割线
		irons.add("取消");
		irons.insert("粘贴",2);   //添加粘贴项，将粘贴选项添加到了irons的第3个位置
		
		//创建初始化  菜单栏
		MenuBar mb=new MenuBar();  
		mb.add(menu);
		mb.add(irons);  //在菜单栏添加两个菜单
		setMenuBar(mb);  //给frame窗口设置菜单栏
		
		//初始化弹出菜单
		pop=new PopupMenu("menu");  
		pop.add("新建");
		pop.add("粘贴");
		pop.add("取消");
		
		final TextArea p=new TextArea(100,100); //创建一个100，100的文本域不可改写
		p.setBackground(Color.BLUE);
		p.add(pop);  //给文本域加上弹出菜单
		
		//事件处理
		p.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(java.awt.event.MouseEvent evt){
				if(evt.isPopupTrigger()){
					System.out.println("popup trigger"); //输出字符串信息
					System.out.println(evt.getComponent());//输出是哪个组件
					System.out.println(""+evt.getX()+""+evt.getY());//输出坐标
					pop.show(p, evt.getX(), evt.getY());//显示悬浮窗口  窗口p
				}
			}
			
		});
		this.add(p,BorderLayout.CENTER);  //将面板p添加到 frame窗口
		setVisible(true);
	}

	public static void main(String[] args) {
		new MenuDemo("菜单窗口");
	}

}
