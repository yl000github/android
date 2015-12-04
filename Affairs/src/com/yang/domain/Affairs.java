package com.yang.domain;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="affairs")
public class Affairs {
	@DatabaseField(generatedId = true)  
	private int id;
	@DatabaseField(columnName = "what") 
	private String what;
	 @DatabaseField(columnName = "why") 
	private String why;
	 @DatabaseField(columnName = "how") 
	private String how;
	 @DatabaseField(columnName = "comment") 
	private String comment;
	 @DatabaseField(columnName = "type") 
	private String type;
	 @DatabaseField(columnName = "status") 
	private String status;
	 @DatabaseField(columnName = "create_time") 
	private Date create_time;
	 @DatabaseField(columnName = "start_time") 
	private Date start_time;
	 @DatabaseField(columnName = "done_time") 
	private Date done_time;
	 @DatabaseField(columnName = "duration") 
	private String duration;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getDone_time() {
		return done_time;
	}
	public void setDone_time(Date done_time) {
		this.done_time = done_time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
