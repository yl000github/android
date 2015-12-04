package com.example.my_2048;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.FontMetrics;


public class Number implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int value;
	int start_x;
	int start_y;
	int width;
	int space=10; 
//	MainView mainView=MainView.getInstance();
	public Number(int value,int start_x,int start_y,int width) {
		// TODO Auto-generated constructor stub
		this.value=value;
		this.start_x=start_x;
		this.start_y=start_y;
		this.width=width;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void draw(Canvas canvas){ 
		//绘制白色背景
		Paint paint=new Paint();
		int color=R.color.background_white;
		paint.setColor(color);
		
		RectF rectF=new RectF(start_x+space,start_y+space,start_x+width-space,start_y+width-space);
		float radius=40f;
		canvas.drawRoundRect(rectF, radius, radius, paint);
		//绘制数字
		Paint numberPaint=new Paint();
		numberPaint.setColor(Color.BLACK);
		numberPaint.setTextSize(width*0.3f);
		numberPaint.setStyle(Paint.Style.STROKE);
		//这个居中是正对水平方向而言的
		numberPaint.setTextAlign(Paint.Align.CENTER);
		  
		float x=width/2;
		FontMetrics fm=numberPaint.getFontMetrics();
		float y=(width)/2-(fm.ascent+fm.descent)/2;
		if(value!=0){
			switch (value) {
			case 2:
//				col=mainView.getResources().getColor(R.color.num_2);
				paint.setColor(MainView.num_2);
//				paint.setColor(Color.RED);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 4:
				paint.setColor(MainView.num_4);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 8:
				paint.setColor(MainView.num_8);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 16:
				paint.setColor(MainView.num_16);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 32:
				paint.setColor(MainView.num_32);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 64:
				paint.setColor(MainView.num_64);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 128:
				paint.setColor(MainView.num_128);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 256:
				paint.setColor(MainView.num_256);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 512:
				paint.setColor(MainView.num_512);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 1024:
				paint.setColor(MainView.num_1024);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;
			case 2048:
				paint.setColor(MainView.num_2048);
				canvas.drawRoundRect(rectF, radius, radius, paint);
				break;

			default:
				break;
			}
			canvas.drawText(String.valueOf(value), start_x+x, start_y+y, numberPaint);
		}
	}
}
