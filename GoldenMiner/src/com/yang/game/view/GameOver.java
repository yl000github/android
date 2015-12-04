package com.yang.game.view;



import com.yang.game.control.MySurfaceView;
import com.yang.game.util.Functions;
import com.yang.game.util.Res;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameOver  extends GameBase{

	int scores[];
	
	public GameOver() {
		
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
		canvas.drawText("你没有达到目标分数", MySurfaceView.R_X/2-board.getWidth()/2+10, MySurfaceView.R_Y/2-board.getHeight()/5, paint);
		canvas.drawText("重新开始", MySurfaceView.R_X/2-board.getWidth()/2+10, MySurfaceView.R_Y/2+board.getHeight()/5, paint);
		
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
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
