package com.yang.game;


import com.yang.game.control.MySurfaceView;

import android.app.Activity;
import android.os.Bundle;

public class Android_GameActivity extends Activity {
    /** Called when the activity is first created. */
	public static Android_GameActivity instance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(new MySurfaceView(this));
    }
}