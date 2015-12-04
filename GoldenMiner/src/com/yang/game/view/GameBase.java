package com.yang.game.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public abstract class GameBase {

	public abstract void paint(Canvas canvas, Paint paint);

	public abstract void logic();

	public abstract void onTonch(MotionEvent event);

	public abstract void clear();

}
