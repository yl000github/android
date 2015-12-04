package com.yang.game.util;


import android.content.Context;
import android.media.MediaPlayer;

/**
 * @author Administrator
 * ����������Դ
 * ��������
 * ��ͣ����
 * ��������
 */
public class MusicPlayer{
	MediaPlayer mediaPlayer;
	Context context;
	
	public MusicPlayer(Context context, int id) {
		// TODO Auto-generated constructor stub
		this.context = context;
		//����������Դ��ʽһ
		mediaPlayer = MediaPlayer.create(context, id);
		
		//����������Դ��ʽ��
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
		
		
		//����Ϊѭ������
		mediaPlayer.setLooping(true);
	}
	
	public void play(){
		if(mediaPlayer != null && !mediaPlayer.isPlaying()){
			mediaPlayer.start();  //�������֣����ֿ�ʼ����
		}
	}
	
	//��ͣ�����Ҫ�ָ����ţ����ϴ���ͣ��λ�ûָ�
	public void pause(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	
	//ֹͣ�����Ҫ�ָ����ţ���ͷ��ʼ����
	public void stop(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
	}
	
	//��������
	public void destroy(){
		if(mediaPlayer != null)
			mediaPlayer.release(); //�ͷ���Դ
	}

	
}
