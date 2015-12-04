package com.example.greedysnake1;

import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 
 * @author Owner
 *data�Ķ�Ӧ��
 *0�հף�1����2��ͷ��3ʳ�4ǽ
 */
public class DataManager {
	int [][]data;
	public int width;//����Ŀ��ָ���Ǹ��ӵ�����
	public int height;
	Snake snake=new Snake();
	public DataManager(){
		width=height=20;
		data=new int[width][height];
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		isLose=false;
		snake.reset();
		update();
		generateFood(); 
	}
	//��snake�е����ݸ��µ�data��
	private void update() {
		// TODO Auto-generated method stub
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if(i==0||j==0||i==data.length-1||j==data[0].length-1){
					data[i][j]=4;
				}else if(data[i][j]==3){
//					continue;
				}else{
					data[i][j]=0;
				}
			}
		}
		Deque<Point> deque=snake.getDeque();
		for (Iterator iterator = deque.iterator(); iterator.hasNext();) {
			Point point = (Point) iterator.next();
			data[point.x][point.y]=1;
		}
		Point first=deque.getFirst();
		data[first.x][first.y]=2;
	}
	public void setMoveDir(int d){
		snake.setDir(d);
	}
	public void restart(){
		isLose=false;
		snake.reset();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if(i==0||j==0||i==data.length-1||j==data[0].length-1){
					data[i][j]=4;
				}else{
					data[i][j]=0;
				}
			}
		}
		Deque<Point> deque=snake.getDeque();
		for (Iterator iterator = deque.iterator(); iterator.hasNext();) {
			Point point = (Point) iterator.next();
			data[point.x][point.y]=1;
		}
		Point first=deque.getFirst();
		data[first.x][first.y]=2;
		generateFood();
	}
	public void tryAI(){
		 
		//����ֻ��һ��Ҳ������ô�����ף�Ҫ����ʳ��ķ�λ������Ҫ��������ǽ�����ǷŷŰ�
	}

	public int[][] getData() {
		// TODO Auto-generated method stub
		return data;
	}
	public boolean isLose=false;
	public void move() {
		// TODO Auto-generated method stub
		//��Ҫ��Խ����
		//��Ҫ����Ƿ�Ե�ʳ��Ե����ߵĳ��ȼ�1��������һ���µ�ʳ��
	//��һ��Ҫ�ߵĵ�
		Point first=snake.getNextP();
		int code=data[first.x][first.y];
		if(code==3){
			snake.eatFood(first);
			generateFood();
		}else if(code==0){
			snake.moveOne();
		}else{
			isLose=true;
		}
		update();
	}
	int foodX=0;
	int foodY=0;
	public void generateFood(){
		int c=width*height-snake.getDeque().size();
		int x,y;
		do{
			int rand=(int) (Math.random()*c);
			x=rand/height;
			y=rand%height;
		}while(data[x][y]!=0);
		foodX=x;
		foodY=y;
		data[x][y]=3;
	}
	public int getSnakeLength() {
		// TODO Auto-generated method stub
		return snake.getDeque().size();
	}
	
	
}
