package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class JTreeDemo implements ActionListener,TreeModelListener{
	private JTree tree=null;
	private DefaultTreeModel treemodel=null;
	private JPanel panel;
	private String nodeName=null;
	private JButton addSibling,addChild,delete;

	public JTreeDemo(){
		DefaultTreeModel model=new DefaultTreeModel(MakeRootTree());//创建一个树模型
		tree=new JTree(model);  //创建一个model类型的树
		tree.setEditable(true);//树设为可编辑状态
		tree.addMouseListener(new MouseHandle());//设置监听实现方法为内部类MouseHandle
		treemodel=(DefaultTreeModel)tree.getModel();  //返回树模型
		treemodel.addTreeModelListener(this);//添加树监听
		
		JFrame f=new JFrame("树组件演示");
		Container c=f.getContentPane();//获得主体面板
		JScrollPane scrollPane=new JScrollPane(tree);//给树创建一个带滚动条的模版
		c.add(scrollPane,"Center");//添加到内容面板的中间位置
		initPanel();  //调用内涵数初始化面板
		c.add(panel,"South");  //将initPanel()初始化的面板添加到下边
		//设置窗口关闭，自动隐藏并释放窗口
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		f.setVisible(true);
		
	}
	
	//创建节点的方法
	public DefaultMutableTreeNode MakeRootTree(){
		//创建，树数据结构中的通用节点
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode child1=new DefaultMutableTreeNode("第一层");
		DefaultMutableTreeNode child2=new DefaultMutableTreeNode("第二层");
		DefaultMutableTreeNode child3=new DefaultMutableTreeNode("第三层");
		root.add(child1);  //为root添加子节点child1
		child1.add(child2);
		child2.add(child3);
		return root;  //返回根节点
	}
	public void initPanel(){
		panel=new JPanel();
		addSibling=new JButton("添加元素");
		addChild=new JButton("添加节点");
		delete=new JButton("删除节点");
		addSibling.addActionListener(this);//添加监听
		addChild.addActionListener(this);
		delete.addActionListener(this);
		panel.add(addSibling);
		panel.add(addChild);
		panel.add(delete);	
	}
	//动作监听的实现
	public void actionPerformed(ActionEvent event){
		//获取选择选择树的节点
		DefaultMutableTreeNode selectedNode=
				(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if(selectedNode==null){
			return;
		}
		//删除节点
		if(event.getSource().equals(delete)){//判断数据源是否为delete
			if(selectedNode.getParent()!=null){//父节点不为空
				treemodel.removeNodeFromParent(selectedNode);//移除节点
			}
			return;
		}
		//添加元素操作，创建新节点
		DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("NEW");
		if(event.getSource().equals(addSibling)){
			//获取父节点
			DefaultMutableTreeNode parent=(DefaultMutableTreeNode)selectedNode.getParent();
			if(parent!=null){//如果父节点不为空
				//在父节点中获得选择节点的索引
				int selectedindex=parent.getIndex(selectedNode);
				//将新节点加到 索引selectindex+1处
				treemodel.insertNodeInto(newNode, parent, selectedindex+1);
			}
		}
		//添加节点
		if(event.getSource().equals(addChild)){
			//将新节点插入到getChildCount()
			treemodel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
		}
		//显示新节点
		TreeNode[] nodes=treemodel.getPathToRoot(newNode);//向上构建节点的父节点
		TreePath path=new TreePath(nodes);//根据nodes构造路径
		tree.scrollPathToVisible(path);//显示新节点
	}
	
	//树监听器实现的方法
	public void treeNodesChanged(TreeModelEvent event){//更改节点后调用
		TreePath treePath=event.getTreePath();//返回已更改的节点
		//返回指定位置的索引组件
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)treePath.getLastPathComponent();
		try{
			int[] index=event.getChildIndices();//返回索引的值
			node=(DefaultMutableTreeNode)node.getChildAt(index[0]);//返回子节点数组index[0]的子节点
		}catch(NullPointerException exc){
			
		}
		System.out.println(nodeName+"更改数据为："+(String)node.getUserObject());
	}
	

	public void treeNodesInserted(TreeModelEvent e) {//插入节点后调用
		System.out.println("插入节点");
	}

	public void treeNodesRemoved(TreeModelEvent e) {//删除节点后调用
		System.out.println("删除节点");
	}

	public void treeStructureChanged(TreeModelEvent e) { //给定节点开始向下的地方发生彻底更改之后调用
		System.out.println("结构变化");
	}
	
	//鼠标监听器实现的类
	class MouseHandle extends MouseAdapter{  //继承自鼠标适配类
		public void mousePressed(MouseEvent e){ //实现按下的函数
			try{
				JTree tree=(JTree)e.getSource();//获得事件的发生对象
				int rowLocation=tree.getRowForLocation(e.getX(), e.getY());
						//获得tree坐标，路径
				if(rowLocation<0)
					return;
				TreePath treepath=tree.getPathForRow(rowLocation);//返回指定行的路径
				//返回指定索引路径位置的组件
				TreeNode treenode=(TreeNode)treepath.getLastPathComponent();
				nodeName=treenode.toString();//节点转换城字符串信息
			}catch(NullPointerException ne){
				ne.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new JTreeDemo();
	}

}
