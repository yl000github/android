package com.yang.game.model;

import java.util.ResourceBundle;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yang.game.util.Functions;
import com.yang.game.util.Res;

public class Stone extends Mineral {
	/**
	 * @param pos_x
	 * @param pos_y
	 * @param resource
	 * @param weight
	 * ʯ�ӵĴ�С��Ϊ2�����ͣ���С��
	 * �����Ľ�ҷֱ�Ϊ 10,5
	 * weightӰ�������������ٶ�Ҳȡ����ֵ ���ֱ�Ϊ1,0
	 * ��ҲӰ���Ӧ��ͼƬ�ķ�����  �ֱ�Ϊ 1.0,0.4
	 * ������2��ʯͷ����ӦSTONE_1 ��STONE_2
	 */
	public static int STONE_1=0X0001;
	public static int STONE_2=0X0002;
	static int  weight_money[]={5,10};
	static float  weight_scale[]={0.4f,1.0f};
	public Stone(int pos_x,int pos_y,int weight,int stoneType) {
		// TODO Auto-generated constructor stub
		if(stoneType==STONE_1)
			resource=Functions.zoomBitmap(Res.stone_1, weight_scale[weight], weight_scale[weight]);
		else
			resource=Functions.zoomBitmap(Res.stone_2, weight_scale[weight], weight_scale[weight]);
			
		setMoney(weight_money[weight]);
		setWeight(weight);
		setPoint(pos_x, pos_y);		
	}
	//�������properties�е��ֶ� x,y,z,a���������һ��������
	public static int[] decodeProperties(String s){
//		s.
		int[] a=new int[4];
		String ss[]=s.split(",");
		for (int i = 0; i < ss.length; i++) {
			a[i]=Integer.parseInt(ss[i]);
		}
		return a;
	}
	
	
	
}
