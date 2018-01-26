package com.xiaoshabao.shabaotest.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

public class DoubleTest {
	@Test
	public void format() {
		double f = 12.039;

		// 格式话四舍五入
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format(f));

		// 格式话四舍五入
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(2, BigDecimal.ROUND_UP).doubleValue();
		System.out.println(f1);

		// 直接舍去
		BigDecimal bg2 = new BigDecimal(f);
		double f2 = bg2.setScale(2, RoundingMode.DOWN).doubleValue();
		System.out.println(f2);
	}

}
