package com.example.greedysnake1;

import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 
 * @author Owner
 *data的对应表
 *0空白，1蛇身，2蛇头，3食物，4墙
 */
public class DataManager {
	int [][]data;
	public int width;//这里的宽高指的是格子的数量
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
	//将snake中的数据更新到data中
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
		 
		//就算只走一步也不是那么的容易，要考虑食物的方位，并且要考虑蛇身、墙，还是放放吧
	}

	public int[][] getData() {
		// TODO Auto-generated method stub
		return data;
	}
	public boolean isLose=false;
	public void move() {
		// TODO Auto-generated method stub
		//还要做越界检查
		//还要检查是否吃到食物，吃到后蛇的长度加1，并产生一个新的食物
	//下一个要走的点
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
