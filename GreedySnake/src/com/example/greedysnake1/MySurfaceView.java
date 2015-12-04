package com.example.greedysnake1;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	DataManager manager=new DataManager();
	SurfaceHolder holder;
	Context context;
	int width,height;
	int gridWidth,gridHeight;
	Paint paint=new Paint();
	int moveTime=500;
	int timeDelt=100;
	int moveCount;
	Handler handler;
	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		holder=this.getHolder();
		holder.addCallback(this);  
		this.context=context;
	}
	public MySurfaceView(Context context){
		super(context);
		holder=this.getHolder();
		holder.addCallback(this);
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//这里就开始绘制视图了
		width=getWidth();
		height=getHeight();
		gridHeight=height/manager.height;
		gridWidth=width/manager.width;
		moveCount=moveTime/TIME;
//		setZOrderOnTop(true);
		handler=new Handler();
		new Thread(this).start();
				
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	boolean isRun=true;
	int TIME=100;
	public void setIsRun(boolean b){
		isRun=b;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count=1;
		while(isRun){
			long now=System.currentTimeMillis();
//			invalidate();
			Canvas canvas=holder.lockCanvas();
			if(count%moveCount==0)
				logic();
			draw1(canvas);
			count++;
			holder.unlockCanvasAndPost(canvas);
			long last=System.currentTimeMillis();
			long dur=last-now;
			if(dur<TIME){
				try {
					Thread.sleep(TIME-dur);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void logic() {
		// TODO Auto-generated method stub
		int lastLen=manager.getSnakeLength();
		manager.move();
		int snaLen=manager.getSnakeLength();
		if(snaLen%7==0&&lastLen!=snaLen){
			Message msg=MainActivity.myHandler.obtainMessage();
			msg.arg1=1;
			MainActivity.myHandler.sendMessage(msg);
		}
		if(manager.isLose){
			isRun=false;
//			Looper.prepare();
//			Looper.loop();
//			
			MainActivity.myHandler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(context).setMessage("You lose!").create()
					.show();
				}
			});
//			Looper.quit();
		}
	} 
	public void draw1(Canvas canvas) {
//		getHolder().setFormat(PixelFormat.TRANSLUCENT); d 
//		canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
		//因为直线也是有宽度的，其起点为左上角的点
//		paint.setStrokeWidth(10);
//		paint.setColor(Color.GREEN);
//		canvas.drawLine(0, 0, 0, height, paint);
//		canvas.drawLine(0, 0, width, 0, paint); 
//		canvas.drawLine(width-1, 0, width-1, height, paint);
//		canvas.drawLine(0, height-1,width, height-1, paint);
		int[][]data=manager.getData();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				int key=data[i][j];
				int stX=i*gridWidth;
				int enX=stX+gridWidth;
				int stY=j*gridHeight;
				int enY=stY+gridHeight;
				RectF r=new RectF(stX,stY,enX,enY);
				//surfaceview竟然是局部刷新。。
				switch (key) {
				case 0:
					paint.setColor(Color.GRAY);
					canvas.drawRect(r, paint);
					break;
//					canvas.drawOval(r, paint);
				case 1:
					paint.setColor(Color.GREEN);
					canvas.drawOval(r, paint);
					break;
				case 2:
					paint.setColor(Color.YELLOW);
					canvas.drawOval(r, paint);
					break;
				case 3:
					paint.setColor(Color.BLUE);
					canvas.drawOval(r, paint);
					break;
				case 4:
					paint.setColor(Color.BLACK);
					canvas.drawRect(r, paint);
					break;

				default:
					break;
				}
			}
		}
	}
	public void setMoveDir(int i) {
		// TODO Auto-generated method stub
		manager.setMoveDir(i);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	public void restart() {
		// TODO Auto-generated method stub
		isRun=true;
		manager.restart();
		new Thread(this).start();
	}
	public void reduce() {
		// TODO Auto-generated method stub
		//减速相当于一次运动花费的时间增加
		int t=moveTime+timeDelt;
		if(t<=2000){
			moveTime=t;
			moveCount=moveTime/TIME;
		}
	}
	public void accel() {
		// TODO Auto-generated method stub
		int t=moveTime-timeDelt;
		if(t>=100){
			moveTime=t;
			moveCount=moveTime/TIME;
		}
	}
	public void tryAI() {
		// TODO Auto-generated method stub
		manager.tryAI();
	}
}
