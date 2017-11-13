package com.xiaoshabao.baseframework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class FileUtil {

	/**
	 * 获得文件md5值
	 */
	public static String getFileMD5(String path) throws IOException {
		try (InputStream in = new FileInputStream(path)){
			String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(in));
			return md5;
		}
	}

}
