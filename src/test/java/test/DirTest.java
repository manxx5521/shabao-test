package test;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

/**
 * 文件夹
 */
public class DirTest {
	
	/**
	 * 创建文件夹
	 * @param src 系统目录要创建到的根目录
	 * @param path 想要创建的文件目录 web/WEB-INF/classes/com/linkage/sale/bean/customersale/onecounty
	 */
	public void makeDir(String src,String path){
		path=path.replace("/", File.separator);
		if(!src.endsWith(File.separator)){
			src+=File.separator;
		}
		File dirFile = new File(src+path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}
	
	@Test
	public void makeTest(){
		try {
			this.makeDir("E:\\11", "web/WEB-INF/classes/com/linkage/sale/bean/customersale/onecounty");
		} catch (Exception e) {
			e.printStackTrace();
			fail("程序异常终止");
		}
		
	}

}
