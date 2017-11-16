package com.xiaoshabao.shabaotest.awtandswing.swing;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

	//进度条
public class JProgressBarDemo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7791847401713124430L;
	JProgressBar progress=new JProgressBar();//创建一个JProgressBar
	int count=0;
	//创建内部列task，设置滚动值
	class Task extends java.util.TimerTask{  //一个计时器任务
		//重写run方法
		@Override
		public void run(){
			progress.setValue(count++);//执行时的赋值
		}
	}
	public JProgressBarDemo(){
		/*阿里扫描报Timer 过时问题，暂时注释
		progress.setStringPainted(true);//按百分比显示
		this.getContentPane().add(progress,"North");//添加进度条
		Task task=new Task();  //
		java.util.Timer timer=new java.util.Timer();  //计算器
		timer.schedule(task, 100,100);  //安排任务task
		this.setSize(500,100);
		this.setVisible(true);
		//设置关闭窗口，自动隐藏并释放窗口
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
	}
	public void execute(){
		
	}
	public static void main(String[] args){
		new JProgressBarDemo();
	}
}
