package com.yang.game.util;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound {
	public static final int DIG = 0X01;
	public static final int SOUND1 = 0X02;
	public static final int SOUND2 = 0X03;

	// ��Ч�أ����ԷŶ����Ч��Դ
	private SoundPool soundPool;

	private HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

	public Sound(Context context, int ids[]) {
		// TODO Auto-generated constructor stub
		// ��Ч���п�������3����Ч
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

		// ������ֵsoundId��ŵ�hashmap��
		hm.put(1, soundPool.load(context, ids[0], 1));
		hm.put(2, soundPool.load(context, ids[1], 1));
		hm.put(3, soundPool.load(context, ids[2], 1));
	}

	// ����ָ����Ч
	public void play(int index) {
		if (soundPool != null)
			soundPool.play(hm.get(index), 1.0f, 1.0f, 0, 0, 1.0f);
	}

	// �ͷ���Ч��Դ
	public void destroy() {
		if (soundPool != null)
			soundPool.release();
	}

}
