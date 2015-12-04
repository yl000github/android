package com.yang.game.view;

import com.yang.game.control.Control;
import com.yang.game.control.MySurfaceView;
import com.yang.game.util.Functions;
import com.yang.game.util.Res;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameLoad extends GameBase {
//	Rect head=new Rect(160,50,320,150);
	//定义当前的难度
	int difficulty=1;
	int count=0;
	int goal=0;
	int level=1;
	int money=0;
	public GameLoad(int level) {
		// TODO Auto-generated constructor stub
		this.level=level;
		goal=Gaming.goal_level[level-1];
		this.money=0;
	}
	public GameLoad(int level,int money) {
		// TODO Auto-generated constructor stub
		this.level=level;
		goal=Gaming.goal_level[level-1];
		this.money=money;
	}

	@Override
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(Res.load_background, 0, 0, paint);
		Bitmap board = Functions.zoomBitmap(Res.load_board, 0.7f, 0.7f);
		canvas.drawBitmap(board, MySurfaceView.R_X/2-board.getWidth()/2, MySurfaceView.R_Y/2-board.getHeight()/2, paint);
		//绘制相关文字
		paint.setTextSize(30);
		paint.setColor(Color.GREEN);
		canvas.drawText("本关目标分", MySurfaceView.R_X/2-board.getWidth()/2+10, MySurfaceView.R_Y/2-board.getHeight()/5, paint);
		canvas.drawText("$"+goal, MySurfaceView.R_X/2-board.getWidth()/2+10, MySurfaceView.R_Y/2+board.getHeight()/5, paint);
		
		
		
		paint.setColor(Color.YELLOW);
		canvas.drawText("XX黄金挖矿工", 160, 50, paint);
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		//0.6s后跳转游戏页面
		if(count>6){
			Control.getInstance().changeStatus(new Gaming(level,money));
		}
		count++;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTonch(MotionEvent event) {
		// TODO Auto-generated method stub

	}

}
