package com.yang.game.model;

import java.util.ResourceBundle;

import com.yang.game.util.Functions;
import com.yang.game.util.Res;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Gold extends Mineral {
	
	/**
	 * @param pos_x
	 * @param pos_y
	 * @param resource
	 * @param weight
	 * 金子的大小分为3种类型，大、中、小，
	 * 所含的金币分别为 500,100,50
	 * weight影响绳索上升的速度也取三个值 ，分别为2,1,0
	 * 它也影响对应的图片的放缩比  分别为 1.4，1.0,0.8
	 * 即可定义数组a[]={50,100,500}
	 */
	static int  weight_money[]={50,100,500};
	static float  weight_scale[]={0.4f,0.6f,1.0f};
	public Gold(int pos_x,int pos_y,int weight) {
		// TODO Auto-generated constructor stub
		resource=Functions.zoomBitmap(Res.gold, weight_scale[weight], weight_scale[weight]);
		setMoney(weight_money[weight]);
		setWeight(weight);
		setPoint(pos_x, pos_y);		
	}
	//定义解析properties中的字段 x,y,z,将其解析至一个数组中
	public static int[] decodeProperties(String s){
//		s.
		int[] a=new int[3];
		String ss[]=s.split(",");
		for (int i = 0; i < ss.length; i++) {
			a[i]=Integer.parseInt(ss[i]);
		}
		return a;
	}
	

}
