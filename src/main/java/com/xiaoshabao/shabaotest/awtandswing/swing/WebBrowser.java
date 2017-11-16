package com.xiaoshabao.shabaotest.awtandswing.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebBrowser extends JFrame implements HyperlinkListener, PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Swing 组件
	JEditorPane textPane;// 显示HTML面板
	JLabel messageLine;// 最底下的状态栏
	JTextField urlField;// 网址输入栏
	JFileChooser fileChooser;// 文件选择器，打开本地文件时使用

	// 后退和前进
	JButton backButton;
	JButton forwardButton;

	@SuppressWarnings("rawtypes")
	List history = new ArrayList();// 保存历史记录的列表
	int currentHistoryPage = -1;// 当前页面在历史记录表中的位置
	public static final int MAX_HISTORY = 50;// 当历史纪录超过该值时，清除旧的历史
	static int numBrowserWindows = 0; // 当前已经打开的窗口数
	// 标识当所有浏览器窗口关闭时，是否退出应用程序
	static boolean exitWhenLastWindowClosed = false;
	String home = "http://www.baidu.com";// 默认主页

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * 默认构造函数
	 */
	public WebBrowser() {
		super("WebBrowser");
		// 新建显示HTML面板，并设置它不可编辑
		textPane = new JEditorPane();
		textPane.setEditable(false);

		textPane.addHyperlinkListener(this);// 注册事件处理器，用于超链接事件
		textPane.addPropertyChangeListener(this);// 注册事件处理器，用于改变时间。当页面加载结束时，触发该事件

		// 将HTML显示面板放入主窗口，居中显示
		this.getContentPane().add(new JScrollPane(textPane), BorderLayout.CENTER);
		// 创建状态显示栏，放在窗口底部
		messageLine = new JLabel(" ");
		this.getContentPane().add(messageLine, BorderLayout.SOUTH);
		// 初始化菜单和工具栏
		this.initMenu();
		this.initToolbar();
		// 将当前打开窗口数增加1
		WebBrowser.numBrowserWindows++;
		// 当窗口关闭时，调用close方法处理

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		this.setVisible(true);
	}

	/**
	 * 关闭当前窗口，如果所有窗口都关闭，则根据exitWhenLastWindowClosed属性 判断是否退出应用程序
	 */
	public void close() {
		this.setVisible(false);
		this.dispose();
		synchronized (WebBrowser.class) {
			WebBrowser.numBrowserWindows--;
			if ((numBrowserWindows == 0) && exitWhenLastWindowClosed) {
				System.exit(0);
			}
				
		}
	}

	/**
	 * 初始化菜单栏
	 */
	private void initMenu() {
		// 文件菜单栏下面有四个菜单项：新建，打开，关闭，退出
		JMenu fileMenu = new JMenu("文件");
		fileMenu.setMnemonic('F');

		JMenuItem newMenuItem = new JMenuItem("新建");
		newMenuItem.setMnemonic('N');
		// 当新建时打开一个新的窗口
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newBrowser();
			}
		});

		JMenuItem openMenuItem = new JMenuItem("打开");
		openMenuItem.setMnemonic('O');
		// 当选择“打开”时打开文件选择器，选择打开的文件
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openLocalPage();
			}
		});
	}

	/**
	 * 打开新的浏览器窗口
	 */
	public void newBrowser() {
		WebBrowser b = new WebBrowser();
		b.setSize(this.getWidth(), this.getHeight());
		b.setVisible(true);
	}

	/**
	 * 打开本地文件
	 */
	public void openLocalPage() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			FileFilter filter = new FileFilter() {

				@Override
				public boolean accept(File f) {
					String fn = f.getName();
					if (fn.endsWith(".html") || fn.endsWith(".htm")) {
						return true;
					}
					return false;
				}

				@SuppressWarnings("unused")
				public String getDescription() {
					return "HTML Files";
				}
			};
			fileChooser.setFileFilter((javax.swing.filechooser.FileFilter) filter);
			fileChooser.addChoosableFileFilter((javax.swing.filechooser.FileFilter) filter);
		}
	}

	private void initToolbar() {
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent arg0) {

	}

	public static void main(String[] args) {
		new WebBrowser();
	}

}