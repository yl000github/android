package com.yang.model;

import java.util.Stack;

public class DataManager {
	Piece[][] data=new Piece[9][10];
	int[][][] initText=new int[][][]{
			{{1,0},{2,0},{3,0},{4,0},{5,0},{4,0},{3,0},{2,0},{1,0}},
			{{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1}},
			{{0,-1},{6,0},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{6,0},{0,-1}},
			{{7,0},{0,-1},{7,0},{0,-1},{7,0},{0,-1},{7,0},{0,-1},{7,0}},
			{{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1}},
			
			{{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1}},
			{{7,1},{0,-1},{7,1},{0,-1},{7,1},{0,-1},{7,1},{0,-1},{7,1}},
			{{0,-1},{6,1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{6,1},{0,-1}},
			{{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1},{0,-1}},
			{{1,1},{2,1},{3,1},{4,1},{5,1},{4,1},{3,1},{2,1},{1,1}}
	};
	public DataManager(){
		initial();
//		stack.add(new Data(data));
	}
	public Piece[][] getData(){
		return data;
	}
	public void initial(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				data[i][j]=new Piece(initText[j][i][0],initText[j][i][1]);
			}
		}
	}
	public void reset(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				data[i][j].setAttr(initText[j][i][0],initText[j][i][1]);
			}
		}
		selectedX=-1;selectedY=-1;isSelected=false;turn=1;
	}
	int selectedX=-1;
	int selectedY=-1;
	boolean isSelected=false;
	int turn=1;//��ʾ��ǰ��˭�ߣ�1Ϊ�죬0Ϊ��
	private boolean isOut(int x,int y){
		if(x<0||x>8||y<0||y>9)
			return true;
		else
			return false;
	}
	//�����������⣬1���Ƿ�ѡ�У�2���Ƿ�����
	public void select(int x, int y) {
		// TODO Auto-generated method stub
		
		Piece piece=data[x][y];
		if(piece.getSide()==-1){
			//ѡ�пհ�
			if(isSelected){
				//�ܲ��ܶ�Ҫ��moveto������ȥ�ж�
				moveTo(x, y);
			}
		}else if(piece.getSide()==turn){
			//ѡ�м�������
			if(selectedX>-1){
				data[selectedX][selectedY].setIfSel(false);
			}
			data[x][y].setIfSel(true);
			selectedX=x;
			selectedY=y;
			isSelected=true;
		}else{
			//ѡ�зǼ�������
			if(isSelected){
				moveTo(x, y);
			}
		}
	}
	//�ҳ�����䣬����Ϊֱ�ߣ������ӵĸ���
	private int getLineCount(int startX,int startY,int endX,int endY){
		if(selectedX==endX){
			int count=0;
			if(selectedY<endY){
				for (int i = 1; i < endY-selectedY; i++) {
					if(data[selectedX][selectedY+i].getId()!=0){
						count++;
					}
				}
			}else{
				for (int i = 1; i < -endY+selectedY; i++) {
					if(data[selectedX][endY+i].getId()!=0){
						count++;
					}
				}
			}
			return count;
		}else if(selectedY==endY){
			int count=0;
			if(selectedX<endX){
				for (int i = 1; i < endX-selectedX; i++) {
					if(data[selectedX+i][selectedY].getId()!=0){
						count++;
					}
				}
			}else{
				for (int i = 1; i < -endX+selectedX; i++) {
					if(data[endX+i][endY].getId()!=0){
						count++;
					}
				}
			}
			return count;
		}
		return -1;//��ʾ�д���
	}
	//�ж��Ƿ�Ϊ�Ǽ�������
	private boolean isNFriend(int x,int y){
		if(data[x][y].getSide()!=turn)
			return true;
		else return false;
	}
	private void change(int endX,int endY){
		stack.add(new Data(data));
		data[endX][endY].becomeSame(data[selectedX][selectedY]);
		data[selectedX][selectedY].becomeBlank();
		turn=1-turn;
		isSelected=false;
		
	}
	int[][] maDir=new int[][]{
			{-1,-2},{1,-2},{2,-1},{2,1},
			{1,2},{-1,2},{-2,1},{-2,-1}
	};
	int [][]maBie=new int[][]{
			{0,-1},{1,0},{0,1},{-1,0}
	};
	int [][]xiangDir=new int[][]{
			{2,-2},{2,2},{-2,2},{-2,-2}
	};
	int [][]xiangBie=new int[][]{
			{1,-1},{1,1},{-1,1},{-1,-1}
	};
	int[][] shiDir=new int[][]{
			{1,-1},{1,1},{-1,1},{-1,-1}
	};
	int[][] shuaiDir=new int[][]{
			{1,0},{-1,0},{0,1},{0,-1}
	};
	int[][] redBingDir=new int[][]{
			{1,0},{-1,0},{0,-1}
	};
	int[][] blackBingDir=new int[][]{
			{1,0},{-1,0},{0,1}
	};
	public void moveTo(int endX,int endY){
		if(isNFriend(endX,endY)){
			//ִ�б���������֮ǰ�Ѿ���ȷ��ѡ��������
			Piece piece=data[selectedX][selectedY];
			switch (piece.getId()) {
			case 1:
				//����Ҫ������Ŀ�����ͬһֱ���ϣ�����֮��û�б�����ӣ�Ŀ�������ӷǼ���
				if(getLineCount(selectedX, selectedY, endX, endY)==0){
					change(endX,endY);
				}
				break;
			case 2:
				//���Ҫ��ֻ��8��λ�ÿ�����������Ҫ���Ǳ��ȵ�����
				for (int i = 0; i < maDir.length; i++) {
					int tempX=selectedX+maDir[i][0];
					int tempY=selectedY+maDir[i][1];
					if(tempX==endX&&tempY==endY){
						int d=i/2;
						if(data[selectedX+maBie[d][0]][selectedY+maBie[d][1]].getSide()==-1){
							//��������
							change(endX,endY);
						}
					}
				}
				break;
			case 3:
				//���Ҫ��ֻ��4��λ�ÿ�����������Ҫ���Ǳ��ȵ�����
				for (int i = 0; i < xiangDir.length; i++) {
					int tempX=selectedX+xiangDir[i][0];
					int tempY=selectedY+xiangDir[i][1];
					if(tempX==endX&&tempY==endY){
						if(data[selectedX+xiangBie[i][0]][selectedY+xiangBie[i][1]].getSide()==-1){
							//��������
							change(endX,endY);
						}
					}
				}
				break;
			case 4:
				//ʿ�Ĺ����Щ�����ǲ��ܳ���
				if(endX<3||endX>5)
					break;
				if(turn==1){
					if(endY>=7&&endY<=9){
						int tempDesX,tempDesY;
						for (int i = 0; i < shiDir.length; i++) {
							tempDesX=selectedX+shiDir[i][0];
							tempDesY=selectedY+shiDir[i][1];
							if(tempDesX==endX&&tempDesY==endY){
								change(endX, endY);
								break;
							}
						} 
					}
				}else{
					if(endY>=0&&endY<=2){
						int tempDesX,tempDesY;
						for (int i = 0; i < shiDir.length; i++) {
							tempDesX=selectedX+shiDir[i][0];
							tempDesY=selectedY+shiDir[i][1];
							if(tempDesX==endX&&tempDesY==endY){
								change(endX, endY);
								break;
							}
						}
					}
				}
				break;
			case 5:
				//���Ĺ����Щ�����ǲ��ܳ���
				if(endX<3||endX>5)
					break;
				if(turn==1){
					if(endY>=7&&endY<=9){
						int tempDesX,tempDesY;
						for (int i = 0; i < shuaiDir.length; i++) {
							tempDesX=selectedX+shuaiDir[i][0];
							tempDesY=selectedY+shuaiDir[i][1];
							if(tempDesX==endX&&tempDesY==endY){
								change(endX, endY);
								break;
							}
						} 
					}
				}else{
					if(endY>=0&&endY<=2){
						int tempDesX,tempDesY;
						for (int i = 0; i < shuaiDir.length; i++) {
							tempDesX=selectedX+shuaiDir[i][0];
							tempDesY=selectedY+shuaiDir[i][1];
							if(tempDesX==endX&&tempDesY==endY){
								change(endX, endY);
								break;
							}
						}
					}
				}
				break;
			case 6:
				//�ڵ�Ҫ������Ŀ�����ͬһֱ���ϣ�
				//��Ŀ���û������ֱ����û��ʱ������Ŀ����ез���ֱ������һ���ӣ������ƶ�
				int count=getLineCount(selectedX, selectedY, endX, endY);
				int enemy=1-turn;
				if((count==1&&data[endX][endY].getSide()==enemy)||(count==0&&data[endX][endY].getSide()==-1)){
					
					change(endX,endY);
				}
				break;
			case 7:
				//���Ĺ������ڲ��ܵ���,���й��Ӳ��ܵ���
				
				if(turn==1){
					if(selectedY<5){
						int tempDesX,tempDesY;
						for (int i = 0; i < redBingDir.length; i++) {
							tempDesX=selectedX+redBingDir[i][0];
							tempDesY=selectedY+redBingDir[i][1];
							if(tempDesX==endX&&tempDesY==endY){
								change(endX, endY);
								break;
							}
						} 
					}else{
						if(endX==selectedX&&selectedY==(endY+1)){
							change(endX,endY);
						}
					}
				}else{
					if(selectedY>4){
						int tempDesX,tempDesY;
						for (int i = 0; i < blackBingDir.length; i++) {
							tempDesX=selectedX+blackBingDir[i][0];
							tempDesY=selectedY+blackBingDir[i][1];
							if(tempDesX==endX&&tempDesY==endY){
								change(endX, endY);
								break;
							}
						}
					}else{
						if(endX==selectedX&&selectedY==(endY-1)){
							change(endX,endY);
						}
					}
				}
				break;

			default:
				break;
			}
		}
		
		
		
	}
	boolean redMan=true;
	boolean blackMan=true;
	public void setRed(boolean b) {
		// TODO Auto-generated method stub
		redMan=b;
	}
	public void setBlack(boolean b) {
		// TODO Auto-generated method stub
		blackMan=b;
	}
	Stack<Data> stack=new Stack<Data>();
	private void setData(Data da){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				this.data[i][j].becomeSame(da.data[i][j]);
			}
		}
	}
	public void undo() {
		// TODO Auto-generated method stub
		if(!stack.isEmpty()){
			Data da=stack.pop();
			turn=1-turn;
			setData(da);
		}
	}
	int intelligentce=0;//0��1,2�ֱ�Ϊ�򵥣���ͨ������
	public void setIntell(int i) {
		// TODO Auto-generated method stub
		intelligentce=i;
	}
	
}
