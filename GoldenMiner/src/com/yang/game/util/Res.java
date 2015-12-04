package com.yang.game.util;

import java.io.IOException;
import java.io.InputStream;

import com.yang.game.Android_GameActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class Res {
	public static Bitmap menu_man;
	public static Bitmap menu_start1;
	public static Bitmap menu_start2;
	public static Bitmap menu_circle;	
	public static Bitmap load_background;	
	public static Bitmap load_board;	
	//gaming的图
	public static Bitmap gaming_head;	
	public static Bitmap gaming_background1;	
	public static Bitmap gaming_background2;	
	public static Bitmap gaming_background3;	
	public static Bitmap gaming_background4;	
	public static Bitmap gaming_background0;	
	public static Bitmap gaming_exit;	
	public static Bitmap gaming_man;	
	public static Bitmap gaming_hook;	
	
	//gameshop
	public static Bitmap gameshop_background;	
	public static Bitmap gameshop_desk;	
	public static Bitmap gameshop_dialog;	
	public static Bitmap gameshop_man;	
	public static Bitmap gameshop_next;	
	public static Bitmap gameshop_grass;	
	public static Bitmap gameshop_rocks;	
	public static Bitmap gameshop_boom;	
	public static Bitmap gameshop_strength;	
	
	//mineral
	public static Bitmap bag;	
	public static Bitmap gold;	
	public static Bitmap stone_1;	
	public static Bitmap stone_2;	
	public static Bitmap tool;	
	
	
	
	
	
	public static void load(boolean isLoad){
		
		if(isLoad){
			menu_man = createBimap("gamemenu/menu_man.png");
			menu_start1 = createBimap("gamemenu/menu_start1.png");
			menu_start2 = createBimap("gamemenu/menu_start2.png");
			menu_circle = createBimap("gamemenu/menu_circle.png");
			load_background = createBimap("gameload/load_background.png");
			load_board = createBimap("gameload/load_board.png");
			
			gaming_head = createBimap("gaming/gaming_head.png");
			gaming_background1 = createBimap("gaming/gaming_background1.png");
			gaming_background2 = createBimap("gaming/gaming_background2.png");
			gaming_background3 = createBimap("gaming/gaming_background3.png");
			gaming_background4 = createBimap("gaming/gaming_background4.png");
			gaming_background0 = createBimap("gaming/gaming_background0.png");
			gaming_exit = createBimap("gaming/gaming_exit.png");
			gaming_man = createBimap("gaming/man_all.png");
			gaming_hook = createBimap("gaming/hook.png");
						
			
			gameshop_background = createBimap("gameshop/gameshop_background.png");
			gameshop_desk = createBimap("gameshop/gameshop_desk.png");
			gameshop_dialog = createBimap("gameshop/gameshop_dialog.png");
			gameshop_next = createBimap("gameshop/gameshop_next.png");
			gameshop_man = createBimap("gameshop/gameshop_man.png");
			gameshop_grass = createBimap("gameshop/gameshop_grass.png");
			gameshop_rocks = createBimap("gameshop/gameshop_rocks.png");
			gameshop_boom = createBimap("gameshop/gameshop_boom.png");
			gameshop_strength= createBimap("gameshop/gameshop_strength.png");
			
			bag = createBimap("mineral/bag.png");
			gold = createBimap("mineral/gold.png");
			stone_1 = createBimap("mineral/stone_1.png");
			stone_2= createBimap("mineral/stone_2.png");
			tool= createBimap("mineral/tool.png");
			

			
		}
	}
	
	private static Bitmap createBimap(String name){
		Bitmap bitmap = null;
		try {
			InputStream is = Android_GameActivity.instance.getResources().getAssets().open(name);
			bitmap = BitmapFactory.decodeStream(is);
			//不是多理解下面的这句话有什么用
			BitmapFactory.decodeResource(Android_GameActivity.instance.getResources(), com.yang.game.R.drawable.hp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("Test","createBimap");
		return bitmap;
	}
	
}
