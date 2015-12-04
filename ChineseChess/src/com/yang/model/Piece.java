package com.yang.model;
/**
 * 
 * @author Owner
 *本类用来描述棋子本身所具有的一些东西，所有的棋子集成在这其中
 *空白0，车1，马2，相3，士4，将5，炮6，兵7
 *似乎还有一个朝向的问题
 */
public class Piece {
	int id;
	String content; 
	boolean isSel=false;
//	boolean dir=true;//文字的朝向，true为正，false为反
	String []redContent=new String[]{
		"",	"車","馬","相","仕","帥","砲","兵"
	};
	String []blackContent=new String[]{
			"",	"車","馬","象","士","將","炮","卒"
		};
	int side;//1紅0黑，-1表示中立
	public Piece(int id,int side){
		setAttr(id,side);
	}
	private void init() {
		// TODO Auto-generated method stub
		if(side==1){
			content=redContent[id];
		}else {
			content=blackContent[id];
		}
	}
	public void setAttr(int id,int side){
		this.id=id;
		this.side=side;
		init();
	}
	public void setIfSel(boolean sel){
		isSel=sel;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public String getContent() {
		// TODO Auto-generated method stub
		return content;
	}
	public int getSide() {
		// TODO Auto-generated method stub
		return side;
	}
	public boolean getSel() {
		// TODO Auto-generated method stub
		return isSel;
	}
	public void becomeSame(Piece piece) {
		// TODO Auto-generated method stub
		setAttr(piece.getId(), piece.getSide());
	}
	public void becomeBlank() {
		// TODO Auto-generated method stub
		setAttr(0, -1);
	}

}
