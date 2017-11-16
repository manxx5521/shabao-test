package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class NoteBook extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -4545806677987498712L;
	JMenu fileMenu=new JMenu("文件");//构造菜单栏菜单按钮
	JMenu editMenu=new JMenu("编辑");//构造菜单栏按钮
	JMenuItem newItem=new JMenuItem("新建");  //建立菜单项
	JMenuItem openItem=new JMenuItem("打开");  //建立菜单项
	JMenuItem saveItem=new JMenuItem("保存");  //建立菜单项
	JMenuItem saveAsItem=new JMenuItem("另存");  //建立菜单项
	JMenuItem exitItem=new JMenuItem("退出");  //建立菜单项
	JMenuItem selectItem=new JMenuItem("全选");  //建立菜单项
	JMenuItem copyItem=new JMenuItem("复制");  //建立菜单项
	JMenuItem cutItem=new JMenuItem("剪切");  //建立菜单项
	JMenuItem pasteItem=new JMenuItem("粘贴");  //建立菜单项
	//创建，并初始化打开文件对话框
	FileDialog openFileDialog=new FileDialog(this,"Open File",FileDialog.LOAD);
	//创建，并初始化保存对象
	FileDialog saveFileDialog=new FileDialog(this,"Save File As",FileDialog.SAVE);
	String filepath;//文件路径名字符窜
	String fileName=null;
	//String fileName="NoName.txt";  //创建默认文件名
	final JTextArea textArea=new JTextArea();  //创建一个文本域
	JMenuBar menuBar=new JMenuBar();  //创建菜单栏组
	String title="ERROR MESSAGE";  //
	int type=JOptionPane.ERROR_MESSAGE;//初始化整型变量，弹出错误信息
	
	//构造方法
	public NoteBook(){
		setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置关闭窗口
		JPanel panel=new JPanel();  //创建一个面板
		panel.setLayout(new GridLayout(1,1));  //设置为一个格子的网格布局管理
		panel.add(new JScrollPane(textArea));  //添加一个带有滚动条的文本域
		this.getContentPane().add(panel);//添加到主面板
		
		setJMenuBar(menuBar);  //设置菜单栏
		menuBar.add(fileMenu);  //往菜单添加  文件菜单组
		menuBar.add(editMenu);  //添加编辑菜单组
		//文件菜单
		fileMenu.add(newItem);   //向文件菜单，添加新建项
		fileMenu.add(openItem);
		fileMenu.addSeparator();  //添加分割线
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();  //添加分割线
		fileMenu.add(exitItem);
		//编辑菜单
		editMenu.add(selectItem);  //选择
		fileMenu.addSeparator();  //添加分割线    
		editMenu.add(copyItem);  //拷贝
		editMenu.add(cutItem);  //剪切
		editMenu.add(pasteItem);  //粘贴
		
		newItem.addActionListener(this);  //新建注册监听
		newItem.setMnemonic('N');  //为newItem菜单项设置 按钮'N'助记标
		newItem.setAccelerator(KeyStroke.getKeyStroke('N',java.awt.Event.CTRL_MASK,true));//设置加速器,激活按钮时启用
		openItem.addActionListener(this);
		openItem.setMnemonic('O');
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.Event.CTRL_MASK,true));
		saveItem.addActionListener(this);
		saveItem.setMnemonic('S');
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK,true));
		saveAsItem.addActionListener(this);
		saveAsItem.setMnemonic('T');
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke('T', java.awt.Event.CTRL_MASK,true));
		exitItem.addActionListener(this);
		exitItem.setMnemonic('Q');
		exitItem.setAccelerator(KeyStroke.getKeyStroke('Q', java.awt.Event.CTRL_MASK,true));
		selectItem.addActionListener(this);
		selectItem.setMnemonic('A');
		selectItem.setAccelerator(KeyStroke.getKeyStroke('A', java.awt.Event.CTRL_MASK,true));
		copyItem.addActionListener(this);
		copyItem.setMnemonic('C');
		copyItem.setAccelerator(KeyStroke.getKeyStroke('C', java.awt.Event.CTRL_MASK,true));
		cutItem.addActionListener(this);
		cutItem.setMnemonic('X');
		cutItem.setAccelerator(KeyStroke.getKeyStroke('X', java.awt.Event.CTRL_MASK,true));
		pasteItem.addActionListener(this);
		pasteItem.setMnemonic('P');
		pasteItem.setAccelerator(KeyStroke.getKeyStroke('P', java.awt.Event.CTRL_MASK,true));
		
		setVisible(true);//设置显示窗口
	}
	
	//监听
	@Override
	public void actionPerformed(ActionEvent e){
		Object eventSoure=e.getSource();  //获得监听源
		if(eventSoure==newItem){  //点击新建
			textArea.setText(""); 
		}else if(eventSoure==openItem){  //点击打开
			readFile();  //执行读方法
		}else if(eventSoure==saveItem){  //保存
			//fileName="NoName.txt";
			System.out.println("保存 能否获得文件名"+fileName);
			fileName=filepath;//filepath能获得路径
			writeFile(fileName);//调用写方法
		}else if(eventSoure==saveAsItem){  //另存为
			fileName=null;
			writeFile(fileName);
		}else if(eventSoure==exitItem){//退出
			System.exit(0);
		}else if(eventSoure==selectItem){//全选
			textArea.selectAll();
		}else if(eventSoure==copyItem){//拷贝
			textArea.copy();
		}else if(eventSoure==cutItem){//剪切
			textArea.cut();
		}else if(eventSoure==pasteItem){//粘贴
			textArea.paste();
		}
	}
	
	//读文件的方法
	public void readFile(){
		JFileChooser openfile=new JFileChooser();//文件选择器
		openfile.setDialogTitle("打开文件");  //设置对话框标题
		openfile.setApproveButtonText("打开");//设置ApproveButton文本
		openfile.showOpenDialog(this);  //弹出一个对话狂
		File file=openfile.getSelectedFile();//返回选中的文件
		if(file!=null){
			fileName=file.getName();
			
			FileInputStream inputfile=null;//创建文件输出流
			String message="文件找不到";  //初始化 信息字符
			try{  
				
				inputfile=new FileInputStream(file);//通过file文件，初始化inputfile
			}catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(this, message,title,type);//显示对话框
			}
			int readbytes;
			String message1="读文件发生错误";
			//读文件
			try{
				filepath=file.getCanonicalPath();  //路径名字符串
				while((readbytes=inputfile.read())!=-1){//读文件没有错误，读文件用的二进制所以用整形存储
					textArea.append(String.valueOf((char)readbytes));//追加到文本域
				}
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, message1,title,type);//显示对话框
			}
			String closemessage="文件关闭时发生错误";
			try{
				inputfile.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, closemessage,title,type);//显示对话框
			}
		}
		
	}
	//写文件的方法
	public void writeFile(String fileName){
		File filesa;
		String messagef="文件未找到";
		FileOutputStream outputfile=null;  //文件输出文件流
		if(fileName==null){
			JFileChooser savefile=new JFileChooser();//文件选择器
			savefile.setApproveButtonText("保存");  //设置按钮为保存
			savefile.setDialogTitle("保存文件");//设置标题
			savefile.showSaveDialog(this);  //显示保存对话框
			filesa=savefile.getSelectedFile();//返回选择的文件
			if(filesa==null){
				return;
			}
		}else{
			System.out.println("保存 能否获得文件名1"+fileName);
			filesa=new File(fileName);
		}
		
		try{
			outputfile=new FileOutputStream(filesa);//创建并初始化文件输出流
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(this, messagef,title,type);//显示对话框
		}
		String filecontext=textArea.getText();  //获得文本内容
		String wrmessage="文件错误";
		try{
			outputfile.write(filecontext.getBytes());//将内容写到filecontext中
		}catch(IOException e){
			JOptionPane.showMessageDialog(this, wrmessage,title,type);//显示对话框
		}
		String cmessage="文件流关闭错误";
		try{
			outputfile.close(); //关闭
		}catch(IOException e){
			JOptionPane.showMessageDialog(this, cmessage,title,type);//显示对话框
		}
	}

	public static void main(String[] args) {
		new NoteBook();

	}

}
