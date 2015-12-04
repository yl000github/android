package com.yang.game.view;

import java.util.ArrayList;
import java.util.ResourceBundle;

import com.yang.game.control.Control;
import com.yang.game.control.MySurfaceView;
import com.yang.game.model.Gold;
import com.yang.game.model.LuckBag;
import com.yang.game.model.Mineral;
import com.yang.game.model.Stone;
import com.yang.game.util.Animation;
import com.yang.game.util.Functions;
import com.yang.game.util.Hook;
import com.yang.game.util.Res;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Gaming extends GameBase {

	int money = 0;
	int goal = 0;
	int totalTime = 30;
	int level = 1;
	int count = 0;
	int content_h = MySurfaceView.R_Y - Res.gaming_head.getHeight();
	int content_w = MySurfaceView.R_X;
	Rect rect_exit=new Rect(350,15,350+Res.gaming_exit.getWidth(),15+Res.gaming_exit.getHeight());
	boolean isClickExit=false;
	public ArrayList<Mineral> array_Mineral = new ArrayList<Mineral>();
	public static int [] goal_level={650,1100};
//	ArrayList<Gold> array_Gold = new ArrayList<Gold>();
//	ArrayList<Stone> array_Stone = new ArrayList<Stone>();
//	ArrayList<LuckBag> array_LuckBag = new ArrayList<LuckBag>();
	Animation man;
	Hook hook;
	public Gaming(int level,int money) {
		// TODO Auto-generated constructor stub
		this.level=level;
		this.money=money;
		goal=goal_level[level-1];
		man=new Animation(Res.gaming_man,MySurfaceView.R_X/2-20,10,4,1,3);
		readProperties("gold",level, 10);
		readProperties("stone",level, 4);
		hook=new Hook(this);
		
	}

	// 获取配置文件中每个mineral的属性，并且渲染出来
	public  void readProperties(String name,int level, int num
			) {
		if (name == "gold") {
			for (int i = 1; i <= num; i++) {
				String s = ResourceBundle.getBundle(name+level).getString(name + i);
				int[] a = Gold.decodeProperties(s);
				Gold gold = new Gold(a[0], a[1], a[2]);
				array_Mineral.add(gold);
			}
		} else if (name == "stone") {
			for (int i = 1; i <= num; i++) {
				String s = ResourceBundle.getBundle(name+level).getString(name + i);
				int[] a = Stone.decodeProperties(s);
				Stone stone = new Stone(a[0], a[1], a[2], a[3]);
				array_Mineral.add(stone);
			}
		} 
//		else if (name == "LuckBag") {
//			for (int i = 1; i <= num; i++) {
//				String s = ResourceBundle.getBundle(name).getString(name + i);
//				int[] a = LuckBag.decodeProperties(s);
//				LuckBag luckbag = new Stone(a[0], a[1], a[2], a[4]);
//				stone.paint(canvas, paint);
//			}
//		}

	}

	@Override
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		// 绘制背景图片
		canvas.drawBitmap(Res.gaming_head, 0, 0, paint);
		// 下面的背景应该每一关都有所变化
		canvas.drawBitmap(Res.gaming_background1, 0, 70, paint);
		

		// 绘制相关文字
		paint.setTextSize(18);
		paint.setColor(Color.BLACK);
		canvas.drawText("Money", 0, 30, paint);
		canvas.drawText("Time:", 400, 30, paint);
		canvas.drawText("Goal", 25, 55, paint);
		canvas.drawText("Level:", 400, 55, paint);

		paint.setColor(Color.GREEN);
		canvas.drawText("$" + money, 70, 30, paint);
		paint.setColor(Color.GRAY);
		canvas.drawText("$" + goal, 70, 55, paint);

		paint.setColor(Color.BLUE);
		canvas.drawText(String.valueOf(totalTime), 460, 30, paint);
		canvas.drawText(String.valueOf(level), 460, 55, paint);
		canvas.drawBitmap(Res.gaming_exit, 350, 15, paint);

		for (int i = 0; i < array_Mineral.size(); i++) {
			array_Mineral.get(i).paint(canvas, paint);
		}
		man.paint(canvas, paint);
		paint.setStyle(Style.STROKE);
		
		hook.paint(canvas, paint);
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		count++;
		if (count % 10 == 0)
			totalTime--;
		if(totalTime==0){
			Control.getInstance().changeStatus(new GameOverWord(level,goal,money));
		}
		if(isClickExit)
			Control.getInstance().changeStatus(new GameOverWord(level,goal,money));
		man.logic();
		hook.logic();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTonch(MotionEvent event) {
		// TODO Auto-generated method stub
//		int touch_x = (int) ((int) event.getX() * MySurfaceView.f_x);
		int touch_y = (int) ((int) event.getY() * MySurfaceView.f_y);
		if(event.getAction()==MotionEvent.ACTION_DOWN&&touch_y>100){
			hook.setCurrentStatus(Hook.LONGER);
		}
		if(Functions.ClickButton(event, rect_exit))
			isClickExit=true;
		else
			isClickExit=false;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return money;
	}

}
