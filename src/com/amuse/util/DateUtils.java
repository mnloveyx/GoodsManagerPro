/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amuse.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author lzw
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	/**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN_NO_CON = "yyyyMMddHHmmss";
    public static final String TIME_PATTERN_NO_CON_MINI = "yyyyMMddHHmmssSSS";
    
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
    /**
     * dd日HH时mm分
     */
    public static final String DATE_PATTERN_DDHHMM = "dd日HH时mm分";
    /**
     * yyyyMMdd
     */
    public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
    /**
     * yyyyMM
     */
    public static final String DATE_PATTERN_YYYYMM = "yyyyMM";
    public static final String DATE_PATTERN_YYMM = "yyyy年MM月";
    /**
     * yyyy
     */
    public static final String DATE_PATTERN_YYYY = "yyyy";
    
    /**
     * MM
     */
    public static final String DATE_PATTERN_MM = "MM";
    
    /**
     * dd
     */
    public static final String DATE_PATTERN_dd = "dd";
    
    /**
     * HH:mm:ss
     */
    public static final String TIME_PATTERN_HHMMSS = "HH:mm:ss";
	
    /**
     * 将系统参数格式定义的字符串转换为Date类型的日期。
     * 
     * @param dateValue
     * @param pattern
     * @return
     * @throws ParseException 
     * @throws Exception
     */
    public static Date parse(String dateValue, String pattern)
    {
        if (StringUtils.isEmpty(dateValue))
            return null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            return null;
        }
    }
    
    /**
     * 字符串转换日期 格式（yyyy-MM-dd）
     */
    public static Date parseDate(String dateValue)
    {
        return parse(dateValue,DATE_PATTERN_DEFAULT);
    }
    
    /**
     * 字符串转换日期 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static Date parseTime(String dateValue)
    {
        return parse(dateValue,TIME_PATTERN_DEFAULT);
    }

    /**
     * 将Date类型的日期转换为系统参数定义的格式的字符串。
     * @param aTs_Datetime
     * @param as_Pattern
     * @return
     */
    public static String formatDate(Date d, String pattern)
    {
      if (d == null)
        return null;

      SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
      return dateFromat.format(d);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate(DATE_PATTERN_DEFAULT);
    }
    
    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return formatDate(new Date(), pattern);
    }
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd）
	 */
	public static String getDate(Date date) {
		return formatDate(date, DATE_PATTERN_DEFAULT);
	}
	

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), TIME_PATTERN_HHMMSS);
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), TIME_PATTERN_DEFAULT);
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), DATE_PATTERN_YYYY);
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), DATE_PATTERN_MM);
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), DATE_PATTERN_dd);
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
     * "一天之内  返回 x小时前/x分钟前"  "一天以上返回 yyyy-MM-dd HH:mm:ss"
     * @param date2
     * @return
     */
    public static String getStringTime(Date date2)
    {
        long between = 0;
        try {
            Date date= new Date();
            between = (date.getTime() - date2.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        if(between<=3600000){
            long min = between / (60 * 1000);
            if(min<1){
                min=1;
            }
            return min + "分钟前";
            
        }else if(between<=86400000){
            long hour = between / (60 * 60 * 1000);             
            return hour + "小时前";
        }else{
            return DateUtils.getStringDate(date2);
        }
    }
    
    /**
     * date 返回 "yyyy-MM-dd HH:mm"
     * @return
     */
    public static String getStringDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        String dateString = formatter.format(currentTime);  
        return dateString;  
    }


	/**
	 * 获取日期当天0时0分0秒
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFirstSec(Date date) {
		try {
			return DateUtils.parseDate(DateUtils.formatDate(date,"yyyy-MM-dd"),"yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取日期当天最后一秒
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateLastSec(Date date) {
		long dateLastSec = 1000*60*60*24 - 1000;
		return new Date(getDateFirstSec(date).getTime() + dateLastSec);
	}
	
	/**
     * 对两个日期进行比较，如日期1小于日期2则返回-1，相等则返回0，大于则返回1
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2)
    {
        if(date1 == null || date2 == null) return -1;
        
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        cal1.setTime(date1);     
        cal2.setTime(date2);
        
        return cal1.compareTo(cal2);
    }
    
    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();             
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();     
        long between_days=(time2-time1)/(1000*3600*24);
        
        return Integer.parseInt(String.valueOf(between_days));      
    }

	/**
	 * 时间戳转换为指定格式时间
	 * @param date 时间戳
	 * @param pattern 时间格式
	 * @return 指定格式时间
	 */
	public static final String format(Long date ,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将时间戳转成日期字符串
	 * @param timeStamp 时间戳的值,类型为：Long
	 * @param pattern 转成字符串的格式
	 * @return
	 */
	public static String getDateStringByTimeStamp(Long timeStamp,String pattern){
		String result = null;
		Date date = new Date(timeStamp*1000);
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		result = sd.format(date);
		return result;
	}
	
	/**
	 * 按照给定的时间日期模版，将时间戳转换成字符串形式
	 * @param dateTimeFormatter	时间日期模版
	 * @param timeStamp		时间戳
	 * @return
	 * ZoneId.systemDefault() 系统默认的时区
	 */
	public static String formatEpochSecond(Long timeStamp, String dateTimeFormatter) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timeStamp),ZoneId.systemDefault());
		return localDateTime.toString().replaceAll("T", " ");
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
//	public static void main(String[] args) throws ParseException {
////		System.out.println(formatDate(parseDate("2010/3/6")));
////		System.out.println(getDate("yyyy年MM月dd日 E"));
////		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
////		System.out.println(time/(24*60*60*1000));
//	    Date d1 = DateUtils.parseTime("2018-08-06 14:35:45");
//	    System.out.println(DateUtils.daysBetween(d1, new Date()));
//	}

}
