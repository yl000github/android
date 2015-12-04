package com.yang.game.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.yang.game.control.Control;
import com.yang.game.control.MySurfaceView;
import com.yang.game.util.Functions;
import com.yang.game.util.Res;

public class GameShop extends GameBase {
	Rect next_button = new Rect();
	boolean isNextDown = false;
	boolean isNextUp = false;
	int level=1;
	int money=0;
	public GameShop(int level,int money) {
		// TODO Auto-generated constructor stub
		this.level=level;
		this.money=money;
	}

	@Override
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(Res.gameshop_background, 0, 0, paint);
		canvas.drawBitmap(Res.gameshop_boom, 50, 160, paint);
		canvas.drawBitmap(Res.gameshop_grass, 100, 160, paint);
		canvas.drawBitmap(Res.gameshop_strength, 170, 160, paint);
		canvas.drawBitmap(Res.gameshop_rocks, 240, 160, paint);
		canvas.drawBitmap(Res.gameshop_desk, 0, 320 - 106, paint);
		canvas.drawBitmap(Res.gameshop_man, MySurfaceView.R_X
				- Res.gameshop_man.getWidth(),
				320 - 106 - Res.gameshop_man.getHeight(), paint);
		canvas.drawBitmap(Res.gameshop_dialog, 2, MySurfaceView.R_Y / 4 - 20,
				paint);
		paint.setTextSize(15);
		canvas.drawText("点击物品购买，点击NEXT进入下一关", 10, 25 + Res.gameshop_next.getHeight()+40, paint);
		next_button.set(380, 25, 380 + Res.gameshop_next.getWidth(),
				25 + Res.gameshop_next.getHeight());
		canvas.drawBitmap(Res.gameshop_next, 380, 25, paint);
		paint.setTextSize(25);
		canvas.drawText("NEXT", 380, 25 + Res.gameshop_next.getHeight(), paint);
		// Bitmap board = Functions.zoomBitmap(Res.load_board, 0.7f, 0.7f);
		// canvas.drawBitmap(board, MySurfaceView.R_X/2-board.getWidth()/2,
		// MySurfaceView.R_Y/2-board.getHeight()/2, paint);
		// //绘制相关文字
		// paint.setTextSize(25);
		// paint.setColor(Color.GREEN);
		// canvas.drawText("你没有达到目标分数",
		// MySurfaceView.R_X/2-board.getWidth()/2+10,
		// MySurfaceView.R_Y/2-board.getHeight()/5, paint);
		// canvas.drawText("重新开始", MySurfaceView.R_X/2-board.getWidth()/2+10,
		// MySurfaceView.R_Y/2+board.getHeight()/5, paint);

	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		if(isNextUp){
			//进入下一关
			Control.getInstance().changeStatus(new GameLoad(level+1,money));
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTonch(MotionEvent event) {
		// TODO Auto-generated method stub
		int touch_x = (int) ((int) event.getX() * MySurfaceView.f_x);
		int touch_y = (int) ((int) event.getY() * MySurfaceView.f_y);
		// Log.e("Test","("+touch_x+","+touch_y+")");
		// 检测（x，y）,如果到达start区，start换一张图，点击start进行场景的跳转

		if (Functions.pointInRect(touch_x, touch_y, next_button)
				&& event.getAction() == MotionEvent.ACTION_DOWN) {
			// start图片切换
			isNextDown = true;

		} else if (event.getAction() == MotionEvent.ACTION_UP && isNextDown) {
			isNextUp=true;
			
		}

	}

}
