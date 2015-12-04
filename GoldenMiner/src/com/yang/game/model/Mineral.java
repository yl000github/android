package com.yang.game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Mineral {
	public int width,height;
	public int money;
	public int weight;
	//
	public int pos_x,pos_y;
	Bitmap resource;
	//图形资源所包含的矩形
	public Mineral() {
		// TODO Auto-generated constructor stub
	}
	
	public Mineral(int pos_x,int pos_y,Bitmap resource,int weight,int money) {
		// TODO Auto-generated constructor stub
		this.pos_x=pos_x;
		this.pos_y=pos_y;
		this.resource=resource;
		this.weight=weight;
		this.money=money;
		width=resource.getWidth();
		height=resource.getHeight();
	}
	public void setPoint(int pos_x,int pos_y){
		this.pos_x=pos_x;
		this.pos_y=pos_y;
	}
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	public double getLine(){
		return Math.sqrt(Math.pow(width, 2)+Math.pow(height, 2));
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Rect getRect(){
		Rect r=new Rect(pos_x,pos_y,pos_x+resource.getWidth(),pos_y+resource.getHeight());
		return r;
	}
	public void paint(Canvas canvas,Paint paint){
		canvas.drawBitmap(resource, pos_x,pos_y, paint);
	}
	
}
