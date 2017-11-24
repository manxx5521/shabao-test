package com.xiaoshabao.shabaotest.module.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * JDK实现方式
 */
public class IOJdkImpl implements IOInterface {

	@Override
	public void toInputStrean() {
	}

	// 理论上就是先生成文件
	@Override
	public void toInputStreanByPath(String path) {
		try (InputStream inputStrean = new FileInputStream(path)) {
			// 此处获得输入流

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void toInputStrean(File file) {
		try (InputStream inputStrean = new FileInputStream(file)) {
			// 此处获得输入流

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void toInputStreanByString(String content) {

		new ByteArrayInputStream(content.getBytes());

	}

}
