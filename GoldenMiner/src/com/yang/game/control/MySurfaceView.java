package com.yang.game.control;


import com.yang.game.util.Res;
import com.yang.game.view.GameMenu;
import com.yang.game.view.GameLoad;
import com.yang.game.view.GameOver;
import com.yang.game.view.GameOverWord;
import com.yang.game.view.GameShop;
import com.yang.game.view.Gaming;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * @author Administrator 游戏主逻辑
 */
public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	SurfaceHolder holder;
	Context context;
	
	boolean isRun;
	// 游戏中每一帧消耗的时间
	final int TIME = 100;
	
	Paint paint = new Paint();
	
	//美术资源的分辨率
	public static final int R_X = 480;
	public static final int R_Y = 320;
	
	//画布缩放比例
	public static float f_x, f_y;
	

	public MySurfaceView(Context context) {
		super(context);
		
		this.context = context;
		// TODO Auto-generated constructor stub
		holder = this.getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
//		f_x = this.getHeight()*1.0f/R_X;
//		f_y = this.getWidth()*1.0f/R_Y;
		
		//屏幕的宽永远指宽，x轴也永远是水平方向
		f_x = this.getWidth()*1.0f/R_X;
		Log.e("Test",String.valueOf(this.getWidth()));
		f_y = this.getHeight()*1.0f/R_Y;
		
		paint.setAntiAlias(true);
		isRun = true;
		//游戏开始进入菜单状态
		Res.load(true);
		Control.getInstance().changeStatus(new GameMenu());
		
		
		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	// 游戏主逻辑放在子线程体中
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRun) {

			long currentTime = System.currentTimeMillis();
			Canvas canvas = holder.lockCanvas();
			if (canvas == null)
				return;
			
			//缩放画布
			canvas.scale(f_x, f_y);
			
			//将画布刷为白色
			canvas.drawColor(Color.BLACK);
			Control.getInstance().render(canvas, paint);
			holder.unlockCanvasAndPost(canvas);
			long lastTime = System.currentTimeMillis();

			try {
				if (TIME > (lastTime - currentTime))
					Thread.sleep(TIME - (lastTime - currentTime));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Control.getInstance().getCurrentStatus().onTonch(event);
		return true;
	}

}
