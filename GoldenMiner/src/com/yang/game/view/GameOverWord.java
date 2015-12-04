package com.yang.game.view;

import com.yang.game.control.Control;
import com.yang.game.control.MySurfaceView;
import com.yang.game.util.Functions;
import com.yang.game.util.Res;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
//显示你没有达到目标分数并退出游戏
public class GameOverWord extends GameBase {
	boolean isGo=true;
	int level;
	int goal;
	int money;
	int count=0;
	public GameOverWord(int level,int goal,int money) {
		// TODO Auto-generated constructor stub
		this.level=level;
		this.goal=goal;
		this.money=money;
	}
	
	@Override
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(Res.load_background, 0, 0, paint);
		Bitmap board = Functions.zoomBitmap(Res.load_board, 0.7f, 0.7f);
		canvas.drawBitmap(board, MySurfaceView.R_X/2-board.getWidth()/2, MySurfaceView.R_Y/2-board.getHeight()/2, paint);
		//绘制相关文字
		paint.setTextSize(25);
		paint.setColor(Color.GREEN); 
		if(isGo){
			canvas.drawText("恭喜你过关", MySurfaceView.R_X/2-board.getWidth()/2+10, MySurfaceView.R_Y/2-board.getHeight()/5, paint);					
		}else{
			
			canvas.drawText("你没有达到目标分数", MySurfaceView.R_X/2-board.getWidth()/2+10, MySurfaceView.R_Y/2-board.getHeight()/5, paint);		
		}
		
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		count++;
		if(money>=goal)
		{
			isGo=true;
			if(count==10){
				Control.getInstance().changeStatus(new GameShop(level,money));
			}
		}else{
			isGo=false;
			if(count==10){
				Control.getInstance().changeStatus(new GameMenu());
			}
		}
			
	}

	@Override
	public void onTonch(MotionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
