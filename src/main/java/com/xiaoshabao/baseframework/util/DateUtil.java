package com.xiaoshabao.baseframework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static DateFormat ddFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static DateFormat ddShortFormat = new SimpleDateFormat("yyyyMMdd");

	private static DateFormat ssFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static DateFormat ssFormat2 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSSS");

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 获得精准服务器时间
	 * 
	 * @return
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 根据字符串获得时间
	 * 
	 * @param dateStr
	 *            yyyy/MM/dd HH:mm:ss->2012/11/05 11:12:13
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr) throws ParseException {
		Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr);
		return date;
	}

	/**
	 * 获得当前时间的时间戳
	 * 
	 * @return
	 */
	public static long getTime() {
		return getDate().getTime();
	}

	/**
	 * 获得日期字符串 yyyy-MM-dd
	 * 
	 * @Title: getDateBydd
	 * @return 2008-08-08
	 */
	public String getDateFordd() {
		return getDateFordd(getDate());
	}

	/**
	 * 获得日期字符串 yyyy-MM-dd
	 * 
	 * @Title: getDateBydd
	 * @param date
	 *            时间
	 * @return 2008-08-08
	 */
	public String getDateFordd(Date date) {
		return ddFormat.format(date);
	}

	/**
	 * 获得日期字符串 yyyyMMdd
	 * 
	 * @return 20080808
	 */
	public String getDateForddshort() {
		return getDateForddshort(getDate());
	}

	/**
	 * 获得日期字符串 yyyyMMdd
	 * 
	 * @param date
	 *            时间
	 * @return 20080808
	 */
	public String getDateForddshort(Date date) {
		return ddShortFormat.format(date);
	}

	/**
	 * 获得日期字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 2008-08-08 12:11:02
	 */
	public String getDateForSS() {
		return getDateForSS(getDate());
	}

	/**
	 * 获得日期字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            时间
	 * @return 2008-08-08 12:11:02
	 */
	public String getDateForSS(Date date) {
		return ssFormat.format(date);
	}

	/**
	 * 获得日期字符串 yyyyMMdd_HHmmss_SSSS
	 * 
	 * @return 2008_08_08_12_11_02
	 */
	public String getDateForSS2() {
		return getDateForSS2(getDate());
	}

	/**
	 * 获得日期字符串 yyyyMMdd_HHmmss_SSSS
	 * 
	 * @param date
	 *            时间
	 * @return 2008_08_08_12_11_02
	 */
	public String getDateForSS2(Date date) {
		return ssFormat2.format(date);
	}

	/**
	 * 获得日期字符串 yyyy
	 * 
	 * @Title: getDateBydd
	 * @return 2008
	 */
	public String getDateForYear() {
		return getDateForYear(getDate());
	}

	/**
	 * 获得日期字符串 yyyy
	 * 
	 * @param date
	 *            时间
	 * @return 2008
	 */
	public String getDateForYear(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 字符串 yyyy-MM-dd 解析成日期
	 * 
	 * @param str
	 *            yyyy-MM-dd形式字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringBydd(String str) throws ParseException {
		return ddFormat.parse(str);
	}

	/**
	 * 字符串 yyyyMMdd 解析成日期
	 * 
	 * @param str
	 *            yyyyMMdd形式字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringByddShort(String str) throws ParseException {
		return ddShortFormat.parse(str);
	}

	/**
	 * 字符串yyyy-MM-dd HH:mm:ss 解析成日期
	 * 
	 * @param str
	 *            yyyy-MM-dd HH:mm:ss 形式字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringBySS(String str) throws ParseException {
		return ssFormat.parse(str);
	}

	/**
	 * 返回"yyyy-MM-dd hh:mm:ss"
	 * 
	 * @param days
	 *            偏移日期(加 填正值,减 填负值)
	 * @return 偏移后日期""yyyy-MM-dd hh:mm:ss"
	 */
	public static Date getAddDaySsDate(int days) {
		return getAddDaySsDate(getDate(), days);
	}

	/**
	 * 偏移时间 返回"yyyy-MM-dd hh:mm:ss"
	 * 
	 * @param date
	 *            标准日期
	 * @param days
	 *            偏移日期(加 填正值,减 填负值)
	 * @return 偏移后日期""yyyy-MM-dd hh:mm:ss"
	 */
	public static Date getAddDaySsDate(Date date, int days) {
		calendar.clear();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
		return calendar.getTime();
	}

	/**
	 * 偏移时间 返回"yyyy-MM-dd 00:00:00"
	 * 
	 * @param days
	 *            偏移日期(加 填正值,减 填负值)
	 * @return 偏移后日期"yyyy-MM-dd 00:00:00"
	 */
	public static Date getAddDaysDdDate(int days) {
		return getAddDaysDdDate(getDate(), days);
	}

	/**
	 * 偏移时间 返回"yyyy-MM-dd 00:00:00"
	 * 
	 * @param date
	 *            标准日期
	 * @param days
	 *            偏移日期(加 填正值,减 填负值)
	 * @return 偏移后日期"yyyy-MM-dd 00:00:00"
	 */
	public static Date getAddDaysDdDate(Date date, int days) {
		calendar.clear();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

}
