package com.yang.utils;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
SimpleDateFormat函数语法：

G 年代标志符
y 年
M 月
d 日
h 时 在上午或下午 (1~12)
H 时 在一天中 (0~23)
m 分
s 秒
S 毫秒
E 星期
D 一年中的第几天
F 一月中第几个星期几
w 一年中第几个星期
W 一月中第几个星期
a 上午 / 下午 标记符 
k 时 在一天中 (1~24)
K 时 在上午或下午 (0~11)
z 时区
*/
@SuppressLint("SimpleDateFormat")
public class DateUtil {
	public static String getFormat(String format,Date d){
		SimpleDateFormat f=new SimpleDateFormat(format);
		return f.format(d);
	}
	public static String getFormat(String format){
		Date d=new Date();
		SimpleDateFormat f=new SimpleDateFormat(format);
		return f.format(d);
	} 
	public static String getDefaultFormat(Date d){
		if(d==null){
			return "";
		}
		DateFormat df = DateFormat.getDateTimeInstance ();
		return df.format(d);
	}
	public static String getMD(){
		return getFormat("MM月dd日");
	}
	public static String getMD(Date d){
		return getFormat("MM月dd日",d);
	}
}
