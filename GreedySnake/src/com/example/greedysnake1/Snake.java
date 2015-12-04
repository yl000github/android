package com.example.greedysnake1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;
/**
 * 
 * @author Owner
 *队列竟然是一个接口，
 *不过其方法也差不多，add添加，poll出
 * 
 *默认队首为蛇头
 */

public class Snake {
	int[][]initFile=new int[][]{
			{1,1},{2,1},{3,1},{4,1}
	};
	public int dir=2;//0123依次为上右下左
	Deque<Point> deque=new ArrayDeque<Point>();
	public Snake(){
		init();
	}
	public void init() {
		// TODO Auto-generated method stub
		dir=2;
		deque.clear();
		for (int i = 0; i < initFile.length; i++) {
			deque.addFirst(new Point(initFile[i][0],initFile[i][1]));
		}
	}
	public Deque<Point> getDeque(){
		return deque;
	}
	int [][]moveDir=new int[][]{
			{0,-1},{1,0},{0,1},{-1,0}
	};
	public void moveOne(){
		Point np=getNextP();
		deque.addFirst(np);
		deque.removeLast();
//		Log.e("Test", String.valueOf(deque.size()));
	}
	public Point getNextP(){
		Point first=deque.getFirst();
		return new Point(first.x+moveDir[dir][0],first.y+moveDir[dir][1]);
	}
	public void setDir(int d){
		Iterator it=deque.iterator();
		Point first=(Point) it.next();
		Point second=(Point) it.next();
		int tX=second.x-first.x;
		int tY=second.y-first.y;
		if(tX==moveDir[d][0]&&tY==moveDir[d][1])
			return ;
		dir=d;
	}
	public void reset() {
		// TODO Auto-generated method stub
		init();
	}
	public void eatFood(Point first) {
		// TODO Auto-generated method stub
		deque.addFirst(first);
	}
}
