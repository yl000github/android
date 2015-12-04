package com.yang.game.view;

import com.yang.game.control.Control;
import com.yang.game.control.MySurfaceView;
import com.yang.game.util.Functions;
import com.yang.game.util.Res;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class GameMenu extends GameBase {
	int count = 0;
	int center_x = 85, center_y = 75;
	boolean navigate = false;
	boolean isclick = false;
	Rect start_button = new Rect(center_x - Res.menu_start1.getWidth() / 2,
			center_y - Res.menu_start1.getHeight() / 2, center_x
					+ Res.menu_start1.getWidth() / 2, center_y
					+ Res.menu_start1.getHeight() / 2);

	@Override
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		// Log.e("Test","("+(MySurfaceView.R_X-Res.menu_man.getWidth())+","+(MySurfaceView.R_Y-Res.menu_man.getHeight())+")");
		// ���ƹ�Ȧ���ԣ�85,70��Ϊ����

		// ȡ�����ű�0.7+count*0.2
		count = count % 14;
		float scale = (float) (0.7 + 0.25 * count) * 0.3f;
		Bitmap circle = Functions.zoomBitmap(Res.menu_circle, scale, scale);
		canvas.drawBitmap(circle, center_x - circle.getWidth() / 2, center_y
				- circle.getHeight() / 2, paint);
		// ���ư�ť
		if (navigate) {
			Log.e("Test", "menu_start2");
			canvas.drawBitmap(Res.menu_start2,
					center_x - Res.menu_start2.getWidth() / 2, center_y
							- Res.menu_start2.getHeight() / 2, paint);
		} else {
			canvas.drawBitmap(Res.menu_start1,
					center_x - Res.menu_start1.getWidth() / 2, center_y
							- Res.menu_start1.getHeight() / 2, paint);
		}

		// �������½�����
		Bitmap man = Functions.zoomBitmap(Res.menu_man, 1.4f, 1.1f);
		canvas.drawBitmap(man, MySurfaceView.R_X - man.getWidth(),
				MySurfaceView.R_Y - man.getHeight(), paint);
		
		
//		// ����instructions���˴�δд�ã�Ӧ����ͼƬ����ϧû�ҵ�
//		Rect rect = new Rect(20, 250, 20 + 140, 250 + 30);
//		paint.setColor(Color.YELLOW);
//		canvas.drawRect(rect, paint);
//		paint.setTextSize(15);
//		paint.setColor(Color.BLACK);
//		// ���ֵ����Ϊ����
//		canvas.drawText("�ϣ���ը�����·Ź�", 20, 270, paint);

		count++;

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
		int touch_x = (int) ((int) event.getX() * MySurfaceView.f_x);
		int touch_y = (int) ((int) event.getY() * MySurfaceView.f_y);
		// Log.e("Test","("+touch_x+","+touch_y+")");
		// ��⣨x��y��,�������start����start��һ��ͼ�����start���г�������ת

		if (Functions.pointInRect(touch_x, touch_y, start_button)
				&& event.getAction() == MotionEvent.ACTION_DOWN) {
			// startͼƬ�л�
			navigate = true;
			isclick = true;

		} else {
			navigate = false;
		}
		if (event.getAction() == MotionEvent.ACTION_UP && isclick) {
			Control.getInstance().changeStatus(new GameLoad(1));
		}

	}

}
