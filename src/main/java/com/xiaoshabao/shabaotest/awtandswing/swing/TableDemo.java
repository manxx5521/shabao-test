package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TableDemo extends JFrame{
	private static final long serialVersionUID = 5959245885088784882L;
	public TableDemo(){
		super("table演示程序");
		TableModel model=new TableModel();//这是个内涵数
		JTable table=new JTable(model);//构建一个table组件,从model中获取资源
		//设置table组件的大小
		table.setPreferredScrollableViewportSize(new Dimension(400,200));
		//声明一个滚动条面板，设置显示table
		JScrollPane scrollPane=new JScrollPane(table);
		//将滚动条面板加到窗口
		this.getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		setVisible(true);
		pack();  //尽量小的适配窗口
		addWindowListener(new WindowAdapter(){//窗口适配
			//匿名类注册监听
			@Override
			public void windowClosing(WindowEvent evt){ //实现windowClosing
				System.exit(0);  //关闭窗口
			}
		});	
	}
	
	//这里设置个内部类用来得到表的数据
	class TableModel extends AbstractTableModel{
		private static final long serialVersionUID = 7307743351909185980L;
		//将列名保存在数组中
		final String[] columnName={"姓名","学号","年龄","籍贯","党员"};
		//将表格数据保存在Object数组中
		final Object[][] data={
			{"张三","11076",new Integer(23),"北京",new Boolean(false)},
			{"李四","11077",new Integer(20),"上海",new Boolean(true)},
			{"王二","11078",new Integer(21),"广州",new Boolean(true)},
			{"齐四","11079",new Integer(25),"北京",new Boolean(false)},
		};
		//获得有多少列
		@Override
		public int getColumnCount(){
			return columnName.length;
		}
		//获得有多少行
		@Override
		public int getRowCount(){
			return data.length;
		}
		//获得某列的名字
		@Override
		public String getColumnName(int col){
			return columnName[col];
		}
		//获得某行某列的类
		@Override
		public Object getValueAt(int row, int col){
			return data[row][col];
		}
		//获得指定列的类型
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Class getColumnClass(int col){
			return getValueAt(0,col).getClass();
		}
		//使表格具有可编辑性
		public boolean isCellEidtable(int row,int col){
			if(col<2){
				return false;
			}else{
				return true;
			}
		}
		//使表格可以修改数据
		@Override
		public void setValueAt(Object value,int row,int col){
			//用instanceof判断data[][] 是否是Integer类型
			if(data[0][col] instanceof Integer&&!(value instanceof Integer)){
				try{
					data[row][col]=new Integer(value.toString());//设置data的值
					fireTableCellUpdated(row,col);	//更新指定行列
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(TableDemo.this, 
							"The\""+getColumnName(col)+"\"本列只接受整型数据");
				}
			}else{
				data[row][col]=value;
				fireTableCellUpdated(row,col);	//更新指定行列
			}
		}
	}
	public static void main(String[] args) {
		new TableDemo();
	}

}
