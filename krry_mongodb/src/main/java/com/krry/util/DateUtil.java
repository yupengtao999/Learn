package com.krry.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtil {

	private static DateUtil instance;

	private final SimpleDateFormat defaultFormat;

	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	//年月日时间格式，用于excel导入时时间转换
	public static final SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DateUtil() {
		defaultFormat = new SimpleDateFormat(DEFAULT_PATTERN);
	}

	public static DateUtil getInstance() {
		if (instance == null) {
			instance = new DateUtil();
		}
		return instance;
	}

	public String format(Date date) {
		return defaultFormat.format(date);
	}

	public String format(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public Date parse(String resource) {
		Date date = null;
		try {
			date = defaultFormat.parse(resource);
		} catch (ParseException ex) {
			Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return date;
	}

	public Date parse(String resource, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(resource);
		} catch (ParseException ex) {
			Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return date;
	}

	public static Date strictCheckYYYYMMDD(String str)throws Exception{
		if(TmStringUtils.isEmpty(str)){
			throw new Exception("字符串为空");
		}
		String[] split = str.split("-");
		if(split.length != 3){
			throw new Exception("格式有问题");
		}
		try{
			int yyyyInt = Integer.parseInt(split[0]);
			int mmInt = Integer.parseInt(split[1]);
			int ddInt = Integer.parseInt(split[2]);
			if(yyyyInt<=1970){
				throw new Exception();
			}
			if(mmInt<= 0 || mmInt>12){
				throw new Exception();
			}
			if(ddInt<= 0 || ddInt>31){
				throw new Exception();
			}
			//判断当前年，月最大日期
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, yyyyInt);
			calendar.set(Calendar.MONTH, mmInt-1);
			int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if(ddInt > actualMaximum){
				throw new Exception();
			}
			return yyyyMMddFormat.parse(str);
		}catch (Exception e){
			throw e;
		}
	}

	public static void main(String[] args)throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2019);
		calendar.set(Calendar.MONTH, 1);
//        calendar.add(Calendar.MONTH, 1);
//        calendar.set(Calendar.DAY_OF_MONTH, 0);
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("data:" + DateUtil.yyyyMMddFormat.format(calendar.getTime())+";actualMaximum:" + actualMaximum);
	}
}
