package com.yang.game.control;

import com.yang.game.view.GameBase;

import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * @author Administrator
 * ��������Ⱦ
 * �����л�
 */
public class Control {
	
	//��ǰ����
	private GameBase currentStatus;
	
	private static Control instance;

	private Control() {
		// TODO Auto-generated constructor stub
	}
	
	public static Control getInstance(){
		if(instance == null)
			instance = new Control();
		return instance;
	}
	
	//��ǰ��������Ⱦ���߼�
	public void render(Canvas canvas, Paint paint){
		
		currentStatus.logic();
		currentStatus.paint(canvas, paint);
	}
	
	//ת����
	public void changeStatus(GameBase newStatus){
		if(currentStatus != null){
			currentStatus.clear();
		}
		currentStatus = newStatus;
	}
	
	//�õ���ǰ����
	public GameBase getCurrentStatus() {
		return currentStatus;
	}
}
