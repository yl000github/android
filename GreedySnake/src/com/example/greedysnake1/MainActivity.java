package com.example.greedysnake1;

import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Button;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends ActionBarActivity implements
		OnGestureListener {
	MySurfaceView sur;
	GestureDetector detector;
	Button newGame;
	Button tryAI;
	Button accel;
	Button reduce;
	WebView webView;
	String []gif=new String[]{
			"1.gif","2.gif","3.gif","4.gif","5.gif","6.gif","7.gif","8.gif"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		sur = (MySurfaceView) findViewById(R.id.surfaceview);
		// 下面两句话写在这里才有效果，不知道为什么，我对surfaceview的原理真的是知之甚少
		sur.setZOrderOnTop(true);
		sur.getHolder().setFormat(PixelFormat.TRANSPARENT);
		detector = new GestureDetector(this, this);
		initButton();
		myHandler=new MyHandler();
		// sur.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		// setContentView(new MySurfaceView(this));
	}

	private void initButton() {
		// TODO Auto-generated method stub
		newGame = (Button) findViewById(R.id.restart);
		tryAI = (Button) findViewById(R.id.ai);
		accel = (Button) findViewById(R.id.increase);
		reduce = (Button) findViewById(R.id.reduce);
		newGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sur.restart();
			}
		});
		tryAI.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sur.tryAI();
			}
		});
		accel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sur.accel();
			}
		});
		reduce.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sur.reduce();
			}
		});
		webView=(WebView) findViewById(R.id.webview);
		
	}
	int cur=-1;
	public void refreshGif(){
		int rand=(int) (Math.random()*gif.length);
		while(rand==cur){
			rand=(int) (Math.random()*gif.length);
		}
		String data=getWebViewData(gif[rand]);
		webView.loadDataWithBaseURL(null, data, "text/html	", "utf-8", null);
	}
	private String getWebViewData(String str) {
		// TODO Auto-generated method stub
		String str1="<html><body background='file:///android_asset/webviewback.png'><img src='file:///android_asset/";
		String str2="'/></body></html>";
		String img=str;
		return str1+img+str2;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sur.setIsRun(true);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sur.setIsRun(false);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
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
		if (Math.abs(velocityX) > Math.abs(velocityY)) {
			if (velocityX > 0) {
				sur.setMoveDir(1);
			} else {
				sur.setMoveDir(3);
			}
		} else {
			if (velocityY > 0) {
				sur.setMoveDir(2);
			} else {
				sur.setMoveDir(0);
			}
		}
		return true;
	}
	public static MyHandler myHandler;
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int t=msg.arg1;
			if(t==1){
				refreshGif();
			}
			
		}
	}

}
