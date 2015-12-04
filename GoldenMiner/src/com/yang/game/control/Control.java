package com.yang.game.control;

import com.yang.game.view.GameBase;

import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * @author Administrator
 * 场景的渲染
 * 场景切换
 */
public class Control {
	
	//当前场景
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
	
	//当前场景的渲染和逻辑
	public void render(Canvas canvas, Paint paint){
		
		currentStatus.logic();
		currentStatus.paint(canvas, paint);
	}
	
	//转场景
	public void changeStatus(GameBase newStatus){
		if(currentStatus != null){
			currentStatus.clear();
		}
		currentStatus = newStatus;
	}
	
	//得到当前场景
	public GameBase getCurrentStatus() {
		return currentStatus;
	}
}
