package com.xiaoshabao.shabaotest.module.file;

import java.io.File;

public class FilePathTest {

	private final static String FILE_NAME = "fileTestTxt.txt";

	/**
	 * 获得当前目录的文件
	 */
	public String getCurrentPathFile() {
		String path = FilePathTest.class.getClass().getResource("/").getPath();
		System.out.println(path);
		return path;
	}

	/**
	 * 工程目录
	 */
	public String getUserDir() {
		return System.getProperty("user.dir");
	}

	/**
	 * windows的分隔符
	 * <p>
	 * 对于windows来说，/和\\都能识别
	 * <p>
	 */
	public void winPathSp() {

		String path = "D:\\workspaces\\test\\src\\test\\file\\";
		// String path="D:/workspaces/test/src/test/file/";

		File file = new File(path + FILE_NAME);
		if (!file.exists()) {
			throw new RuntimeException("文件读取异常");
		}

	}

	public static void main(String[] args) {
		FilePathTest test = new FilePathTest();
		test.getUserDir();

		File file = new File(test.getUserDir());
		String filePath = file.getParent();
	}

}
