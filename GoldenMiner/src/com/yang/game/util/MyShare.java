package com.yang.game.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class MyShare {
	SharedPreferences share;
	Context context;
	String name;
	public MyShare(Context context, String name) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.name = name;
	}
	
	//写数据
	public void save(int scores[]){
		
		share = context.getSharedPreferences(name, Context.MODE_WORLD_WRITEABLE);
		Editor edit = share.edit();
		edit.putInt("01", scores[0]);
		edit.putInt("02", scores[1]);
		edit.putInt("03", scores[2]);
		edit.commit();
		Log.e("Test", "write");
	}
	
	//读数据
	public int[] read(){
		
		share = context.getSharedPreferences(name, Context.MODE_WORLD_READABLE);
		int i_01 = share.getInt("01", 0);
		int i_02 = share.getInt("02", 0);
		int i_03 = share.getInt("03", 0);
		
		return new int[]{i_01, i_02, i_03};
	}
	
}
