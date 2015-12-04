package com.yang.game.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Animation {
	
	private Bitmap bitmap;
	
	//动画位置坐标
	private int pos_x, pos_y;
	
	//动画总帧数
	private int frameNum;
	
	//动画的延迟,每一帧停滞的时间
	private int delay;
	//计时器
	private int count;
	
	//动画播放当前帧数
	private int currentFrame;
	
	//动画的宽高
	private int frameWidth, frameHeight;
	
	//动画数量
	private int aniNum;
	
	//当前播放的动画
	private int currentAni;
	
	public Animation(Bitmap bitmap, int pos_x, int pos_y, int frameNum, int delay) {
		// TODO Auto-generated constructor stub
		this.bitmap = bitmap;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.frameNum = frameNum;
		this.delay = delay;
		this.frameWidth = bitmap.getWidth()/frameNum;
		this.frameHeight = bitmap.getHeight();
		currentFrame = 0;
		aniNum = 1;
	}
	
	public Animation(Bitmap bitmap, int pos_x, int pos_y, int frameNum, int delay, int aniNum) {
		// TODO Auto-generated constructor stub
		this(bitmap, pos_x, pos_y, frameNum, delay);
		this.aniNum = aniNum;
		this.frameHeight = bitmap.getHeight()/aniNum;
	}
	
	public void paint(Canvas canvas, Paint paint){
		canvas.save();
		canvas.clipRect(pos_x, pos_y, pos_x+frameWidth, pos_y+frameHeight);
		canvas.drawBitmap(bitmap, pos_x-currentFrame*frameWidth, pos_y-frameHeight*currentAni, paint);
		canvas.restore();
	}
	
	public void logic(){
		count++;
		if(count > delay){
			currentFrame++;
			if(currentFrame >= frameNum){
				currentFrame = 0;
			}
			count = 0;
		}
	}
	
	//设置当前动画
	public void setCurrentAni(int currentAni) {
		this.currentAni = currentAni;
//		currentFrame = 0;
//		count = 0;
	}
	
	public void setAnimationPos(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
}
