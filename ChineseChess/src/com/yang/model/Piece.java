package com.yang.model;
/**
 * 
 * @author Owner
 *���������������ӱ��������е�һЩ���������е����Ӽ�����������
 *�հ�0����1����2����3��ʿ4����5����6����7
 *�ƺ�����һ�����������
 */
public class Piece {
	int id;
	String content; 
	boolean isSel=false;
//	boolean dir=true;//���ֵĳ���trueΪ����falseΪ��
	String []redContent=new String[]{
		"",	"܇","�R","��","��","��","�h","��"
	};
	String []blackContent=new String[]{
			"",	"܇","�R","��","ʿ","��","��","��"
		};
	int side;//1�t0�ڣ�-1��ʾ����
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
