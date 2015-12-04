package com.yang.utils;

import java.io.InputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
/**
 * 
 * @author Yang
 *
 */
public class BitmapUtils {
	/** 
	 * 
	 * 以最省内存的方式读取本地资源的图片 
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;//purge纯净
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
		
	}
	/**
	 * 获取一个图片的缩略图，防止oom,
	 * @param filePath
	 * @param width
	 * @return bitmap
	 * 
	 */
	public static Bitmap readBitMapSample( String filePath,int width) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;  
		BitmapFactory.decodeFile(filePath, opt);
//		int width=200;
//		int height=width*opt.outHeight/opt.outWidth; 
		if(width<opt.outWidth){
			int scale=Math.round(opt.outWidth/width);//四舍五入 
			opt.inSampleSize=scale;
		}
		opt.inJustDecodeBounds = false;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		Bitmap bmp =BitmapFactory.decodeFile(filePath, opt);
		if(bmp==null){
			L.d("bitmap为null，无法解析");
		}
		return bmp;
	}
	/**
	 * 获取一个图片，将其缩放成width，比例不变，防止oom,
	 * @param filePath
	 * @param width
	 * @return bitmap
	 * 
	 */
	public static Bitmap readBitMapTarget( String filePath,int width) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;  
		 BitmapFactory.decodeFile(filePath, opt);
		float scale_x=((float)width)/opt.outWidth;
		opt.inJustDecodeBounds = false;
		Bitmap bmp =BitmapFactory.decodeFile(filePath, opt);
		if(bmp==null){
			L.d("bitmap为null，无法解析");
			return null;
		}
//		L.d("放缩后、前"+bmp.getWidth());
		Bitmap nb=zoomBitmap(bmp,scale_x,scale_x);
//		L.d("放缩后"+nb.getWidth());
		return nb;
	}
	public static void release(ImageView imageView){
		 if(imageView !=  null &&  imageView.getDrawable() != null){     
		      Bitmap oldBitmap =  ((BitmapDrawable) imageView.getDrawable()).getBitmap();    
		       imageView.setImageDrawable(null);    
		      if(oldBitmap !=  null){    
		            oldBitmap.recycle();      
		            oldBitmap =  null;   
		      }     
		 }   
		 System.gc();
	}
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
}
