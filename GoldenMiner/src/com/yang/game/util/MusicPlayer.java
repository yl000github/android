package com.yang.game.util;


import android.content.Context;
import android.media.MediaPlayer;

/**
 * @author Administrator
 * 加载音乐资源
 * 播放音乐
 * 暂停音乐
 * 销毁音乐
 */
public class MusicPlayer{
	MediaPlayer mediaPlayer;
	Context context;
	
	public MusicPlayer(Context context, int id) {
		// TODO Auto-generated constructor stub
		this.context = context;
		//加载音乐资源方式一
		mediaPlayer = MediaPlayer.create(context, id);
		
		//加载音乐资源方式二
//		mediaPlayer = new MediaPlayer();
//		try {
//			mediaPlayer.setDataSource("/sdcard/a.mp3");
//			mediaPlayer.prepare();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//设置为循环播放
		mediaPlayer.setLooping(true);
	}
	
	public void play(){
		if(mediaPlayer != null && !mediaPlayer.isPlaying()){
			mediaPlayer.start();  //启动音乐，音乐开始播放
		}
	}
	
	//暂停，如果要恢复播放，从上次暂停的位置恢复
	public void pause(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	
	//停止，如果要恢复播放，从头开始播放
	public void stop(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
	}
	
	//销毁音乐
	public void destroy(){
		if(mediaPlayer != null)
			mediaPlayer.release(); //释放资源
	}

	
}
