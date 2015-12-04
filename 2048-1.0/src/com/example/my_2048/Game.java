package com.example.my_2048;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable; 


import android.graphics.Canvas;
import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author Administrator 这个类管理游戏的主逻辑 1、数据的存储 2、数据的显示 3、数据的运算及变换
 *         initData作为一个临时的容器存放每个格子中的值，方便数据的操作
 */
public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[][] lastData = new int[4][4];
	int[][] intData = new int[4][4];
	Number[][] gameData = new Number[4][4];
	int num_width;
	public int score = 0;
	public int highscore = 0;
	public boolean isWin=false;
	public boolean isDead=false;
	public Game(int num_width) {
		// TODO Auto-generated constructor stub
		// 初始化数据
		this.num_width = num_width;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				intData[i][j] = 0;
			}
		}
		generateNum();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Number number = new Number(intData[i][j], j * num_width, i
						* num_width, num_width);
				gameData[i][j] = number;
			}
		}

	}

	public void saveLast() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				lastData[i][j] = intData[i][j];
			}
		}
	}

	public void restart() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				intData[i][j] = 0;
			}
		}
		generateNum();
		save();
	}

	public void setNum_width(int num_width) {
		this.num_width = num_width;
	}

	// 在空余的位置产生随机数，值为0表示空
	// 先统计剩余格子的个数，然后产生随机数确定位置，最后产生随机数确定2或4
	public void generateNum() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (intData[i][j] == 0) {
					sum++;
				}
			}
		}
		int t = (int) (Math.random() * sum);
		boolean n;
		if (Math.random() > 0.5) {
			n = true;
		} else {
			n = false;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (intData[i][j] == 0) {
					t--;
				} else {
					continue;
				}
				if (t == 0) {
					if (n) {
						intData[i][j] = 2;
					} else {
						intData[i][j] = 4;
					}
				}
			}
		}

	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				gameData[i][j].draw(canvas);
			}
		}
	}

	public void action() {

	}

	public void save() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				gameData[i][j].setValue(intData[i][j]);
			}
		}
	}

	public void flingLeft() {
		// TODO Auto-generated method stub
		Log.e("Direction", "left");
		saveLast();
		for (int i = 0; i < 4; i++) {
			int t[] = new int[4];
			for (int j = 0; j < t.length; j++) {
				t[j] = intData[i][j];
			}
			process(t);
			for (int j = 0; j < t.length; j++) {
				intData[i][j] = t[j];
			}
		}
		moveProcess();

	}

	public void flingRight() {
		// TODO Auto-generated method stub
		Log.e("Direction", "right");
		saveLast();
		for (int i = 0; i < 4; i++) {
			int t[] = new int[4];
			for (int j = 0; j < t.length; j++) {
				t[j] = intData[i][3 - j];
			}
			process(t);
			for (int j = 0; j < t.length; j++) {
				intData[i][3 - j] = t[j];
			}
		}
		moveProcess();
	}

	public void flingUp() {
		// TODO Auto-generated method stub
		Log.e("Direction", "up");
		saveLast();
		for (int i = 0; i < 4; i++) {
			int t[] = new int[4];
			for (int j = 0; j < t.length; j++) {
				t[j] = intData[j][i];
			}
			process(t);
			for (int j = 0; j < t.length; j++) {
				intData[j][i] = t[j];
			}
		}
		moveProcess();
	}

	// 总的逻辑为找寻移动后产生的数字，然后填充到数组中
	public void flingDown() {
		// TODO Auto-generated method stub
		Log.e("Direction", "down");
		saveLast();
		for (int i = 0; i < 4; i++) {
			int t[] = new int[4];
			for (int j = 0; j < t.length; j++) {
				t[j] = intData[3 - j][i];
			}
			process(t);
			for (int j = 0; j < t.length; j++) {
				intData[3 - j][i] = t[j];
			}
		}
		moveProcess();

	}
	public boolean checkChange(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(intData[i][j]!=lastData[i][j]){
					return true;
				}
			}
		}
		return false;
	}
	
	public void moveProcess(){
		updateScore();
		if(checkChange()){
			generateNum();
		}
		save();
		checkWin();
	}
	
	// 计算分数，公式应遵循数越大分越高的原则
	public void updateScore() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int t = intData[i][j];
				int n = (int) (Math.log(t) / Math.log(2));
				sum += Math.pow(3, n);
			}
		}
		score = sum;
	}
	public void checkDead(){
		//感觉无法以一种比较明了的方式写出来
	}
	public void checkWin(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(intData[i][j]==2048){
					isWin=true;
					return;
				}
			}
		}
	}
	public void undo() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				intData[i][j] = lastData[i][j];
			}
		}
		save();
	}

	// 将t中的数据向前移
	// 思路大体分为3步，去0，合并，去0
	public static void process(int[] t) {
		if (t.length != 4) {
			return;
		}
		int num = 0;
		for (int i = 0; i < t.length; i++) {
			if (t[i] == 0) {
				if (i == 3) {
					continue;
				}
				for (int j = i + 1; j < t.length; j++) {
					t[j - 1] = t[j];
				}
				t[3] = 0;
				if (t[i] != 0) {
					num++;
				} else {
					// 该数字后面又有0，那么要检测之后是否全0
					boolean f = true;
					for (int j = i; j < t.length; j++) {
						if (t[j] != 0) {
							f = false;
						}
					}
					if (!f) {
						i--;
					}
				}
			} else {
				num++;
			}
		}
		System.out.println(num);
		for (int i = 0; i < t.length; i++) {

			System.out.print(t[i] + ",");
		}
		System.out.println();
		for (int i = 0; i < num - 1; i++) {

			if (t[i] == t[i + 1]) {
				t[i] = 2 * t[i];
				num--;
				for (int j = i + 2; j < num; j++) {
					t[j - 1] = t[j];
				}
			}

		}
		for (int i = num; i < t.length; i++) {
			t[i] = 0;
		}

		// return t;

	}

	//
	public  static Game  read() {
		Game game = null;//需要为game分配空间吗
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
			File saveFile = new File(sdCardDir, "2048.ser");
//			if(saveFile.exists()){
//				
//			}
//			try {  
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile));  
				//Object的总数不好动态确定，只好事先设定好
						game=(Game) in.readObject();
						in.close();
					} catch (OptionalDataException e) {
						// TODO Auto-generated catch block
						Log.e("read", "OptionalDataException");
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						Log.e("read", "ClassNotFoundException");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.e("read", "IOException");
						e.printStackTrace();
					}
				
//				Log.e("read", "close");
//				} catch (Exception e) {  
//				e.printStackTrace();  
//				Log.e("read", "exception");
//				}
		}
		return game;

	}

	public  void  write() {
		
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
			// Environment.get
			File saveFile = new File(sdCardDir, "2048.ser");

			try {
				// 需要一个文件输出流和对象输出流；文件输出流用于将字节输出到文件，对象输出流用于将对象输出为字节
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(saveFile));
				out.writeObject(this);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				Log.e("write", e.getMessage());
			}
		}
	}
}
