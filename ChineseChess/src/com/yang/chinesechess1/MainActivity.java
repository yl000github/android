package com.yang.chinesechess1;

import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {
	ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back=(ImageView) findViewById(R.id.backview);
        back.setBackgroundResource(R.drawable.start_back);
    }
    @Override 
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	Animation anim=AnimationUtils.loadAnimation(this, R.anim.alpha);
    	back.startAnimation(anim);

    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	try {     
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				Intent intent=new Intent();
		         intent.setClass(MainActivity.this, Game.class);
		         startActivity(intent);
			}
		}).start();
    	 
    }
}
