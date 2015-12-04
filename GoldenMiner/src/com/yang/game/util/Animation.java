package com.yang.game.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Animation {
	
	private Bitmap bitmap;
	
	//����λ������
	private int pos_x, pos_y;
	
	//������֡��
	private int frameNum;
	
	//�������ӳ�,ÿһ֡ͣ�͵�ʱ��
	private int delay;
	//��ʱ��
	private int count;
	
	//�������ŵ�ǰ֡��
	private int currentFrame;
	
	//�����Ŀ��
	private int frameWidth, frameHeight;
	
	//��������
	private int aniNum;
	
	//��ǰ���ŵĶ���
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
	
	//���õ�ǰ����
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
