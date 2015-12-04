package com.yang.game.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyButton {
	Rect rect;
	String text;
	public MyButton(Rect rect,String text) {
		// TODO Auto-generated constructor stub
		this.rect=rect;
		this.text=text;
	}
	public void paint(Canvas canvas,Paint paint){
		paint.setColor(Color.YELLOW);
		canvas.drawRect(rect, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(9);
		//文字的起点为左下角
		canvas.drawText(text, rect.left+rect.width()/8, rect.bottom-rect.height()/4, paint);
	}

}
