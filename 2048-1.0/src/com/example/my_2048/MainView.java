package com.example.my_2048;

import java.io.File;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.FontMetrics;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Toast;

public class MainView extends View implements OnGestureListener{
//	static MainView instance;
	Context context;
	GestureDetector gestureDetector;
	public int screen_width;
	public int screen_height;
	public int game_height;
	public int game_width;
	public int num_width;
	public int score=0;
	public int highscore=0;
	public Game game;
//	public int space=5;
	
	//各种颜色
	public static int num_2;
	public static int num_4;
	public static int num_8;
	public static int num_16;
	public static int num_32;
	public static int num_64;
	public static int num_128;
	public static int num_256;
	public static int num_512;
	public static int num_1024;
	public static int num_2048;
//	public static int num_2;
	
//	public static MainView getInstance(){
//		return instance;
//	}
	public MainView(Context context,int width) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		gestureDetector=new GestureDetector(context,this);
		this.setLongClickable(true);
		Log.e("Test", "MainView");
		Log.e("Test", String.valueOf(getWidth()));
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
			File saveFile = new File(sdCardDir, "2048.ser");
			if(saveFile.exists()){
				Log.e("mainview","readgame");
				game=Game.read(); 
				if(game==null){
					Log.e("mainview","未序列化成功");
					game=new Game(width/4);
				}
			}else{
				
				game=new Game(width/4);
			}
		}else{
			game=new Game(width/4);
			
		}
		
		num_2=getResources().getColor(R.color.num_2);
		num_4=getResources().getColor(R.color.num_4);
		num_8=getResources().getColor(R.color.num_8);
		num_16=getResources().getColor(R.color.num_16);
		num_32=getResources().getColor(R.color.num_32);
		num_64=getResources().getColor(R.color.num_64);
		num_128=getResources().getColor(R.color.num_128);
		num_256=getResources().getColor(R.color.num_256);
		num_512=getResources().getColor(R.color.num_512);
		num_1024=getResources().getColor(R.color.num_1024);
		num_2048=getResources().getColor(R.color.num_2048);
		
		
//		instance=this;
	}
//	onf
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.e("Test", "ondraw");
		Log.e("Test", String.valueOf(getWidth()));
		screen_width=getWidth();
		screen_height=getHeight();
		num_width=screen_width/4;
		game_height=screen_width;
		game_width=screen_width;
		//绘制背景
		Paint paint=new Paint();
		paint.setColor(getResources().getColor(R.color.background));
//		paint.setColor(Color.BLACK);
		canvas.drawRect(0, 0,screen_width,screen_height,paint);
//		canvas.drawRect(0, 0,100,100,paint);
		
		//画数字的白色背景
		
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				int x=i*num_width;
//				int y=j*num_width;
//				Number number=new Number(5, x, y, num_width);
//				number.draw(canvas); 
//			}
//		}
		game.draw(canvas);
		
		
		//画底部的计分板
		paint.setColor(getResources().getColor(R.color.background_black));
		int bottom_height=screen_height-game_height;
		int bottom_width=screen_width;
		int bottom_height_y=bottom_height/3+game_height;
		int score_width=num_width*4/5;
		RectF rectF1=new RectF(bottom_width/8,bottom_height_y,bottom_width/8+score_width,bottom_height_y+score_width);	
		canvas.drawRoundRect(rectF1, 20f, 20f, paint);
		
		Paint numberPaint=new Paint();
		numberPaint.setColor(Color.WHITE);
		numberPaint.setTextSize(score_width/2*0.75f);
		numberPaint.setStyle(Paint.Style.STROKE);
		//这个居中是正对水平方向而言的
		numberPaint.setTextAlign(Paint.Align.CENTER);
		
		float x=score_width/2;
		FontMetrics fm=numberPaint.getFontMetrics();
		float y=(score_width/2)/2-(fm.ascent+fm.descent)/2;
		
		canvas.drawText("Score", rectF1.left+x, rectF1.top+y, numberPaint);
		canvas.drawText(String.valueOf(game.score), rectF1.left+x, rectF1.top+y+score_width/2, numberPaint);
		
		int hightscrore_x=bottom_width*8/15;
		RectF rectF2=new RectF(hightscrore_x,bottom_height_y,hightscrore_x+score_width*2,bottom_height_y+score_width);
		canvas.drawRoundRect(rectF2 , 20f, 20f, paint);
		x=x*2;
		canvas.drawText("High Score", rectF2.left+x, rectF2.top+y, numberPaint);
		canvas.drawText(String.valueOf(game.highscore), rectF2.left+x, rectF2.top+y+score_width/2, numberPaint);
	}
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		  
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		int x1=(int) e1.getX();
		int x2=(int) e2.getX();
		int y1=(int) e1.getY();
		int y2=(int) e2.getY();
		if(Math.abs(velocityY)>Math.abs(velocityX)){
			if(y1<y2){
				flingDown();
			}else{
				flingUp();
			}
		}else{
			if(x1<x2){
				flingRight();
			}else{
				flingLeft();
			}
		}
		invalidate();
		checkWin();
		return false;
	}
	public void checkWin(){
		if(game.isWin){
			Builder builder=new AlertDialog.Builder(context);
			builder.setTitle("消息");
			builder.setMessage("You Win!");
			builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					game.restart();
					invalidate();
				}
			});
			builder.show();
		}
	}
	private void flingLeft() {
		// TODO Auto-generated method stub
		Log.e("Direction", "left");
		game.flingLeft();
	}
	private void flingRight() {
		// TODO Auto-generated method stub
		Log.e("Direction", "right");
		game.flingRight();
	}
	private void flingUp() {
		// TODO Auto-generated method stub
		Log.e("Direction", "up");
		game.flingUp();
	}
	private void flingDown() {
		// TODO Auto-generated method stub
		Log.e("Direction", "down");
		game.flingDown();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gestureDetector.onTouchEvent(event);
		return true;
	}

}
