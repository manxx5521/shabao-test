package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
@SuppressWarnings("rawtypes")
public class HtmlJFrame extends JFrame implements ActionListener,javax.swing.event.ChangeListener{
	
	private static final long serialVersionUID = -7141249877382533447L;
	
	private URL urls[];  //URL对象数组
	private JComboBox combobox_url;  //组合框，输入或选择URL地址
	private JTextField text_attribute;    //文本行，显示文件属性
	private JTabbedPane tab;  //选项卡窗格，每页显示一个文件
	@SuppressWarnings("unchecked")
	public HtmlJFrame(){
		super("查看原文档");
		setBounds(300,240,640,480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] urls={"http://www.so.com",
				"http://www.goole.com",
				"http://java.sun.com"};
		combobox_url=new JComboBox(urls);  //组合框，显示urls
		combobox_url.setEditable(true);   //组合框地址可编辑
		this.getContentPane().add(combobox_url,"North");  //将组合框添加到主面板
		combobox_url.addActionListener(this);  //注册动作监听，单机事件监听	
		tab=new JTabbedPane();  //选项卡窗口
		tab.addChangeListener(this);//注册选择事件监听
		this.getContentPane().add(tab);  //添加选项卡
		text_attribute=new JTextField();  //文本行，显示文本属性
		this.getContentPane().add(text_attribute,"South");  //添加
		this.setVisible(true);  //显示可见
		this.urls=new URL[20];   //创建一个url数组
		actionPerformed(null); //事件处理方式初始化 
	}
	public void actionPerformed(ActionEvent e){
		String urlname=(String)combobox_url.getSelectedItem();//获得组合框选中的字符串
		int i=tab.getTabCount();  //tab标签当前的页数，即下一页的序号
		try{
			this.urls[i]=new URL(urlname);  //创建一个url对象
			InputStreamReader in=new InputStreamReader(this.urls[i].openStream());
			//由字节输入流创建字符输入流，若ruls[i]不存在，抛出异常，不添加tab
			String filename=(String)this.urls[i].toString();  //url的路径名
			for(int j=i-1;j>=0;j--){  //在已有的tab中查找
				if(tab.getTitleAt(j).equals(filename)){  //在已有的中查到，返回第j页标题
					tab.setSelectedIndex(j);  //若找到，选中
					return;
				}
			}
			//找不到
			JEditorPane preview=new JEditorPane(this.urls[i]);  //创建指定URL的编辑器窗格
			JTextArea text_content=new JTextArea();  //文本区，显示文件内容
			JSplitPane splitter_v=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//垂直      分割窗格
			splitter_v.setDividerLocation(200);  //设置水平分隔条的位置
			splitter_v.add(new JScrollPane(preview));  //创建带滚动条的JEditorPane编辑器窗格
			splitter_v.add(new JScrollPane(text_content));  //创建带滚动条的  文本域
			tab.addTab(filename,splitter_v);  //tab添加页，页中添加分割窗格
			tab.setSelectedIndex(tab.getTabCount()-1);  //tab指定新页为选中状态
			
			//读取文件的操作
			BufferedReader bin=new BufferedReader(in);  //通过字符输入流读取文件内容
			String aline=bin.readLine();
			while(aline!=null){
				text_content.append(aline+"\t\n");
				aline=bin.readLine();
			}
			bin.close();
			in.close();
		}catch(MalformedURLException murle){
			JOptionPane.showMessageDialog(this, "字符串指定未知协议错误");
		}catch(FileNotFoundException fe){
			JOptionPane.showMessageDialog(this, "\""+urls[i].getFile()+"\"文件不存在");
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(this, "IO错误读取文件不成购");
		}
	}
	
	//选项卡监听
	public void stateChanged(ChangeEvent e){
		String str="文件:";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  //获取系统时间
		int i=tab.getSelectedIndex();  //返回选项卡当前项数
		if(urls[i].getProtocol().equals("file")){  //当前对象使用的协议
			File afile=new File(urls[i].getFile());  //获得文件对象
			str+=afile.getAbsolutePath()+"\t";  //获得文件路径名
			str+=afile.length()+"B\t";  //加上文件长度
			str+=sdf.format(new Date(afile.lastModified()));  //加上文件修改时间
			try{
				InetAddress local=InetAddress.getLocalHost(); //InetAddress可以获得主机名和主机地址
				str+="本机名："+local.getHostName()+",本机IP地址："+local.getHostAddress();
			}catch(UnknownHostException ioe){
				JOptionPane.showMessageDialog(this, "找不到指定主机的ip地址");
			}
		}
		if(urls[i].getProtocol().equals("http")){
			try{
				URLConnection urlconn=urls[i].openConnection();	 
				str+=urls[i].toString()+"\t";  //获得url路径名
				str+=urlconn.getContentLength()+"B\t"; //获得文件长度
				str+=urlconn.getContentType()+"\t";  //获得文件类型
				str+=sdf.format(new Date(urlconn.getLastModified()));  //最后连接时间
				str+="\t主机名："+urls[i].getHost();
				str+="\tIP地址："+InetAddress.getByName(urls[i].getHost()).getHostAddress();
			}catch(UnknownHostException ioe){
				JOptionPane.showMessageDialog(this, "找不到指定主机的ip地址");
			}catch(IOException ioe){
				JOptionPane.showMessageDialog(this, "IO错误读取文件不成购");
			}
		}
		text_attribute.setText(str);
	}
	
	public static void main(String[] args){
		new HtmlJFrame();
	}
}

