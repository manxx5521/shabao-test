package com.xiaoshabao.shabaotest.awtandswing.awt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

/**
 * 卡片布局管理 可以同过CardLayout(int x,int y);指定水平距离和垂直间距
 * 
 */
public class CardLayoutDemo extends Frame implements MouseListener {
	private static final long serialVersionUID = 2763169649589870328L;

	private Button first = new Button("第一页");
	private Button second = new Button("第二页");
	private Button third = new Button("第三页");
	private Panel cards = new Panel(); // 声明并初始化一个cards面板
	private CardLayout cl = new CardLayout(); // 实例化一个 card布局

	// 声明有参构造函数
	public CardLayoutDemo(String str) {
		super(str); // 调用父类构造方法
		init(); // 调用init（）方法
	}

	public void init() {
		setLayout(new BorderLayout());// 弄一边界编辑器，上下左右中的那个
		setSize(400, 300);
		Panel p = new Panel(); // 建一个面板，并初始化
		p.setLayout(new FlowLayout()); // 面板用顺序布局
		first.addMouseListener(this);// 为按钮添加鼠标监听
		second.addMouseListener(this);
		third.addMouseListener(this);
		p.add(first);// 把按钮添加到面板
		p.add(second);
		p.add(third);
		add("North", p);// 向边界布局的north添加p面板
		cards.setLayout(cl);// 将cards面板设置为卡片布局管理
		cards.add("First card", new Button("第一页内容"));// cards中添加 按钮
		cards.add("Second card", new Button("第二页内容"));
		cards.add("Third card", new Button("第三页"));
		add("Center", cards);// 将cards面板家到 边界布局的center
		// 注册监听的关闭功能
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {// 重写关闭方法
				setVisible(false); // 设置不可见
				dispose(); // 释放资源
				System.exit(0);

			}
		});
		setVisible(true); // 设置窗口显示
	}

	// 设置实现的监听方法
	public void mouseClicked(MouseEvent evt) { // 当点击时触发
		if (evt.getSource() == first) {// 当事件源为first时
			cl.first(cards); // 翻到第一章卡片
		}
		if (evt.getSource() == second) {// 当事件源为second时
			cl.first(cards); // 翻到第一章卡片
			cl.next(cards); // 翻到下一张
		}
		if (evt.getSource() == third) {// 当事件源为third时
			cl.last(cards); // 翻到最后卡片
		}
	}

	// 因为实现了mouselistener 有以下方法 空的
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public static void main(String[] args) {
		new CardLayoutDemo("创建一个CardLayout窗口");
	}

}
