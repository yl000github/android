package com.yang.service;

import java.util.Date;

public class Duration {
	private Date start;
	private Date end;
	public Duration(Date start,Date end){
		this.start=start;
		this.end=end;
	}
	/**
	 * 仅仅显示天数和小时数
	 */
	public String toString(){
		long hours=(end.getTime()-start.getTime())/1000/60/60;
		if(hours<0){
			return "duration error";
		}else{
			long day=hours/24;
			long hour=hours%24;
			if(day>0){
				return day+"天"+hours+"小时";
			}else{
				return hour+"小时";
			}
		}
	}
}
