package com.yang.model;

public class Data {
	public Piece[][] data=new Piece[9][10];//像这种写法，就没有生成对象，至于空间分配了没，我有点不得而知
	public Data(Piece[][]data){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				this.data[i][j]=new Piece(0,0);
				this.data[i][j].becomeSame(data[i][j]);
			} 
		}
	}
}
