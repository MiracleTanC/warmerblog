/**
* @Title: DateTimeHelper.java
* @Package com.example.base
* @Description: TODO(用一句话描述该文件做什么)
* @author tc
* @date 2018年5月2日 上午10:28:54
* @version V1.0
*/
package warmer.star.blog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName: DateTimeHelper
 *
 */
public class DateTimeHelper {
	/**
	 * @param datdString
	 *            Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)
	 * @return 年月日;
	 */
	public static String parseTime(String datdString) {
		datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
		// 将字符串转化为date类型，格式2016-10-12
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
		Date dateTrans = null;
		try {
			dateTrans = format.parse(datdString);
			return new SimpleDateFormat("yyyy-MM-dd").format(dateTrans).replace("-", "/");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datdString;

	}

	public static String dateToShortStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static String dateToStr(String dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * @param datdString
	 *            "Tue Jul 12 12:10:11 GMT+08:00 2016";
	 * @return 时分秒
	 */
	public static String parseHour(String datdString) {

		datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
		Date dateTrans = null;
		try {
			dateTrans = format.parse(datdString);
			return new SimpleDateFormat("HH:mm:ss").format(dateTrans);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datdString;
	}

	public static String GetDateTimeNow() {
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		temp_str = sdf.format(dt);
		return temp_str;
	}

	/**
	 * 获取现在时间
	 *
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		//ParsePosition pos = new ParsePosition(18);
		Date currentTime_2 = null;
		try {
			currentTime_2 = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentTime_2;
	}
	/*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    public static String dateToStamp(Date date) {
        String res;
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        res = simpleDateFormat.format(date);
        return res;
    }
}
