package com.yang.game.util;

import java.util.ResourceBundle;

import com.yang.game.control.MySurfaceView;
import com.yang.game.model.Gold;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Functions {

	// 点是否在矩形中
	public static boolean pointInRect(int p_x, int p_y, Rect rect) {
		if (p_x <= rect.right && p_x >= rect.left && p_y <= rect.bottom
				&& p_y >= rect.top)
			return true;

		return false;

	}

	// 矩形碰撞
	public static boolean isCollision(Rect r_01, Rect r_02) {
		if (r_01.right < r_02.left)
			return false;
		else if (r_02.right < r_01.left)
			return false;
		else if (r_01.bottom < r_02.top)
			return false;
		else if (r_01.top > r_02.bottom)
			return false;

		return true;
	}

	// 圆形碰撞
	public static boolean isCircleCollision(int pos_01_x, int pos_01_y,
			int r_01, int pos_02_x, int pos_02_y, int r_02) {

		if (Math.pow(pos_01_x - pos_02_x, 2) + Math.pow(pos_01_y - pos_02_y, 2) > Math
				.pow(r_01 + r_02, 2))
			return false;
		return true;
	}

	/** 缩放图片 */
	public static Bitmap zoomBitmap(Bitmap bitmap, float scale_x, float scale_y) {
		Matrix matrix = new Matrix();
		matrix.setScale(scale_x, scale_y);
		Bitmap scaleBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return scaleBitmap;
	}

	public static Bitmap getRotateBitmap(Bitmap bitmap, int degrees) {
		Matrix matrix = new Matrix();
		matrix.setRotate(degrees);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return bitmap;
	}

	public static boolean ClickButton(MotionEvent event, Rect r) {

		// TODO Auto-generated method stub
		int touch_x = (int) ((int) event.getX() * MySurfaceView.f_x);
		int touch_y = (int) ((int) event.getY() * MySurfaceView.f_y);
		// Log.e("Test","("+touch_x+","+touch_y+")");
		// 检测（x，y）,如果到达start区，start换一张图，点击start进行场景的跳转
//		boolean isNextDown = false;
		if (Functions.pointInRect(touch_x, touch_y, r)
				&& event.getAction() == MotionEvent.ACTION_DOWN) {
			// start图片切换
//			isNextDown = true;
			return true;

		}
//		else if (event.getAction() == MotionEvent.ACTION_UP && isNextDown) {
//			return true;
//
//		}
		return false;
	}

}
