package com.yang.game.util;

import android.R.color;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.yang.game.control.MySurfaceView;
import com.yang.game.model.Mineral;
import com.yang.game.view.Gaming;

public class Hook {
	int pos_x = MySurfaceView.R_X / 2;
	int pos_y = 52;
	// ���平�ӵĽ��ٶ�,0�Ƚ�Ϊ��ֱ�����
	int speed = 5;
	int length = 20;
	int linespeed = 0;
	int currentAngle = 0;
	// ����hook������״̬��ҡ�ڣ����죬�ջأ�����mineral�ջ�
	int currentStatus = 1;
	public static int SWING = 0X0001;
	public static int LONGER = 0X0002;
	public static int SHORTER = 0X0003;
	public static int MINERAL_SHORTER = 0X0004;

	Mineral mineral;
	Rect hookRect = new Rect();
	Gaming gaming;
	// ��ͬ��Ȩ�ض�Ӧ�Ų�ͬ�ķ��ص��ٶ�
	int[] weight_speed = new int[] { 10, 5, 3 };

	public Hook(Gaming gaming) {
		// TODO Auto-generated constructor stub
		this.gaming = gaming;
	}

	public void paint(Canvas canvas, Paint paint) {
		// �����߽Ƕȱ仯��Χ
		int stopX = (int) (pos_x + Math.sin(currentAngle * Math.PI / 180)
				* length);
		int stopY = (int) (pos_y + Math.cos(currentAngle * Math.PI / 180)
				* length);
		paint.setColor(Color.BLACK);
		canvas.drawLine(pos_x, pos_y, stopX, stopY, paint);
		// canvas.drawCircle(stopX,stopY,20, paint);
		Bitmap hook_rotate = Functions.getRotateBitmap(Res.gaming_hook,
				0 - currentAngle);
		hookRect.set(stopX - hook_rotate.getWidth() / 2,
				stopY - hook_rotate.getHeight() / 2,
				stopX + hook_rotate.getWidth() / 2,
				stopY + hook_rotate.getHeight() / 2);

		canvas.drawBitmap(hook_rotate, stopX - hook_rotate.getWidth() / 2,
				stopY - hook_rotate.getHeight() / 2, paint);
	}

	public void logic() {
		
		int stopX = (int) (pos_x + Math.sin(currentAngle * Math.PI / 180)
				* length);
		int stopY = (int) (pos_y + Math.cos(currentAngle * Math.PI / 180)
				* length);
		if (!Functions.pointInRect(stopX, stopY, new Rect(0, 0,
				MySurfaceView.R_X, MySurfaceView.R_Y))) {
			currentStatus=SHORTER;
		}
		// if(Functions.isCollision(r_01, r_02))
		// ��ɹ����Ƿ��������ײ���ж��������ײ������ײ���weight��money����
		int mineral_money=0,mineral_weight=0;
		int number=0;
		for (int i = 0; i < gaming.array_Mineral.size(); i++) {
			if (Functions.isCollision(hookRect, gaming.array_Mineral.get(i)
					.getRect())) {
				mineral = gaming.array_Mineral.get(i);
				mineral_money=mineral.getMoney();
				mineral_weight=mineral.getWeight();
				currentStatus=MINERAL_SHORTER;
				number=i;
//				linespeed = 0 - weight_speed[mineral.getWeight()];
//				isGo = false;
//				isRotate = false;
				break;

			}
		}

		if (currentStatus == SWING) {
			currentAngle += speed;
			if (currentAngle > 75) {
				currentAngle = 75;
				speed = -speed;
			} else if (currentAngle < -75) {
				currentAngle = -75;
				speed = -speed;
			}

		} else if (currentStatus == LONGER) {
			linespeed=10;
			length=length+linespeed;
		} else if (currentStatus == SHORTER) {
			linespeed=-30;
			length+=linespeed;
			if (length < 20){
				length=20;
				currentStatus=SWING;
			}
				
		} else if (currentStatus == MINERAL_SHORTER) {
			linespeed=0-weight_speed[mineral_weight];
			length+=linespeed;
			//����������Ͻǵ�����
			//������������
			
			int cX = (int) (stopX + Math.sin(currentAngle * Math.PI / 180)
					* (mineral.getHeight()/2+7));
			int cY = (int) (stopY + Math.cos(currentAngle * Math.PI / 180)
					* (mineral.getHeight()/2+7));
			
			mineral.setPoint(cX-mineral.getWidth()/2, cY-mineral.getHeight()/2);
			if (length < 30){
				length=30;
				gaming.setMoney(gaming.getMoney()+mineral_money);
				gaming.array_Mineral.remove(number);
				currentStatus=SWING;
			}
		} 

	}

public void setCurrentStatus(int currentStatus) {
	this.currentStatus = currentStatus;
}
	

	

}
