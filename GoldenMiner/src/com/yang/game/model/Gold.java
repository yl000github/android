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
	 * ���ӵĴ�С��Ϊ3�����ͣ����С�С��
	 * �����Ľ�ҷֱ�Ϊ 500,100,50
	 * weightӰ�������������ٶ�Ҳȡ����ֵ ���ֱ�Ϊ2,1,0
	 * ��ҲӰ���Ӧ��ͼƬ�ķ�����  �ֱ�Ϊ 1.4��1.0,0.8
	 * ���ɶ�������a[]={50,100,500}
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
	//�������properties�е��ֶ� x,y,z,���������һ��������
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
