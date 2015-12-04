package com.yang.chinesechess1;

import com.yang.model.DataManager;
import com.yang.model.Piece;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	Paint paint=new Paint();
	int gridWidth;
	int gameWidth;
	int gameHeight; 
	int verticalSpace; 
	int pieceSpace=1; 
	int horLen;
	int verLen;
	int halfVerLen;
	int stX;
	int stY;
	int pieceStX;
	int pieceStY;
	int pieceRadius;
	DataManager manager=new DataManager();
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		calAttr();
		drawBackground(canvas);
		drawPiece(canvas);
	}
	private void drawPiece(Canvas canvas) {
		// TODO Auto-generated method stub
		Piece [][]data=manager.getData();
		int x,y; 
		 
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
//				paint.setStyle(Style.STROKE);
				Piece piece=data[i][j];
				if(piece.getId()==0)
					continue;
				paint.setColor(Color.YELLOW);
				x=stX+gridWidth*i-gridWidth/2;
				y=stY+gridWidth*j-gridWidth/2;
				//画圆，并且写字
				int ovalStX=x+pieceSpace;
				int ovalStY=y+pieceSpace;
				int ovalEnX=x+gridWidth-pieceSpace;
				int ovalEnY=y+gridWidth-pieceSpace;
				RectF oval=new RectF(ovalStX,ovalStY,ovalEnX,ovalEnY);
				canvas.drawOval(oval, paint);
				//是否选中
				if(piece.getSel()){
					paint.setColor(Color.BLACK);
					paint.setStyle(Style.STROKE);
					canvas.drawRect(oval, paint);
				}
				if(piece.getSide()==1){
					paint.setColor(Color.RED);
				}else{
					paint.setColor(Color.BLACK);
				}
				paint.setStyle(Style.FILL);
				paint.setTextSize(pieceRadius*2*0.75f);
				//这个居中是正对水平方向而言的
				paint.setTextAlign(Paint.Align.CENTER);
				float fX=pieceRadius;
				FontMetrics fm=paint.getFontMetrics();
				float offsetY=-(fm.ascent+fm.descent)/2;
				float fY=pieceRadius+offsetY;
//				canvas.drawText(piece.getContent(), x+pieceSpace+fX, y+pieceSpace+fY, paint);
				if(piece.getSide()==1){
//					canvas.drawLine(ovalStX+fX, ovalStY+fY, ovalStX+fX+80, ovalStY+fY, paint);
					canvas.drawText(piece.getContent(), ovalStX+fX, ovalStY+fY, paint);
				}else {
					canvas.rotate(180, ovalStX+pieceRadius, ovalStY+pieceRadius);
//					canvas.drawLine(x+pieceSpace+fX, ovalStY+pieceRadius+offsetY, x+pieceSpace+fX+100, ovalStY+pieceRadius+offsetY, paint);
					canvas.drawText(piece.getContent(), x+pieceSpace+fX, ovalStY+pieceRadius+offsetY, paint);
					canvas.rotate(180, ovalStX+pieceRadius, ovalStY+pieceRadius);
				}
			}
		}
	}
//	private void drawText(Canvas canvas,String text,int angle,float x,float y){
//		if(angle!=0){
//			canvas.rotate(angle, x, y);
//		}
//		canvas.drawText(text, x, y, paint);
//		if(angle!=0){
//			canvas.rotate(-angle, x, y);
//		}
//	}
	private void drawBackground(Canvas canvas){
	
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(1);
		int x,y;
		for (int i = 0; i < 10; i++) {
			y=stY+i*gridWidth;
			canvas.drawLine(stX, y, stX+horLen, y, paint);
		}
		canvas.drawLine(stX, stY, stX, stY+verLen, paint);
		x=stX+horLen;
		canvas.drawLine(x, stY, x, stY+verLen, paint);
		y=stY+halfVerLen+gridWidth;
		for (int i = 1; i <= 7; i++) {
			x=stX+i*gridWidth;
			canvas.drawLine(x, stY, x, stY+halfVerLen, paint);
			canvas.drawLine(x, y, x, y+halfVerLen, paint);
		}
		paint.setStrokeWidth(4);
		int diff=9;
		x=stX-diff;y=stY-diff;
		canvas.drawLine(x, y, x, y+verLen+diff*2, paint);
		canvas.drawLine(x, y, x+horLen+diff*2, y, paint);
		x=stX+horLen+diff;
		canvas.drawLine(x, y, x, y+verLen+diff*2, paint);
		x=stX-diff;y=stY+verLen+diff;
		canvas.drawLine(x, y, x+horLen+diff*2, y, paint);
		
		paint.setStrokeWidth(1);
		canvas.drawLine(stX+gridWidth*3, stY, stX+gridWidth*5, stY+2*gridWidth, paint);
		canvas.drawLine(stX+gridWidth*3, stY+2*gridWidth, stX+gridWidth*5, stY, paint);
		y=stY+7*gridWidth;
		canvas.drawLine(stX+gridWidth*3, y, stX+gridWidth*5, y+2*gridWidth, paint);
		canvas.drawLine(stX+gridWidth*3, y+2*gridWidth, stX+gridWidth*5, y, paint);
		
		//楚河汉界
		paint.setTextSize(gridWidth*0.75f);
		paint.setTextAlign(Align.CENTER);
		float fx=gridWidth/2;
		FontMetrics fm=paint.getFontMetrics();
		float fy=gridWidth/2-(fm.ascent+fm.descent)/2;
		canvas.drawText("楚河", stX+gridWidth*1+fx, stY+gridWidth*4+fy, paint);
		canvas.drawText("漢界", stX+gridWidth*6+fx, stY+gridWidth*4+fy, paint);
		
		//几个折线，感觉好难画
		for (int i = 0; i < broLine.length; i++) {
			for (int j = 0; j < 4; j++) {
				drawBrokenLine(broLine[i][0],broLine[i][1],j,canvas);
			}
		}
		
		for (int j = 0; j < lrData.length; j++) {
			drawBrokenLine(lrData[j][0],lrData[j][1],lrData[j][2],canvas);
		}
	}
	int [][]lrData=new int[][]{
			{0,3,0},{0,3,1},{8,3,2},{8,3,3},
			{0,6,0},{0,6,1},{8,6,2},{8,3,3}
	};
	int [][]broLine=new int[][]{
			{1,2},{7,2},
			{2,3},{4,3},{6,3},
			{2,6},{4,6},{6,6},
			{1,7},{7,7}
	};
	int broLSpace=10;
	private void drawBrokenLine(int x,int y,int dir,Canvas canvas){
		if(dir<0||dir>3)
			return;
		int liLen=gridWidth/3;
		float []pts;
		int keyX,keyY;
		x=stX+gridWidth*x;
		y=stY+gridWidth*y;
		switch (dir) {
		case 0:
			keyX=x+broLSpace;
			keyY=y-broLSpace;
			pts=new float[]{keyX,keyY-liLen,keyX,keyY,keyX,keyY,keyX+liLen,keyY};
			break;
		case 1:
			keyX=x+broLSpace;
			keyY=y+broLSpace;
			pts=new float[]{keyX,keyY+liLen,keyX,keyY,keyX,keyY,keyX+liLen,keyY};
			break;
		case 2:
			keyX=x-broLSpace;
			keyY=y+broLSpace;
			pts=new float[]{keyX-liLen,keyY,keyX,keyY,keyX,keyY,keyX,keyY+liLen};
			break;
		case 3:
			keyX=x-broLSpace;
			keyY=y-broLSpace;
			pts=new float[]{keyX,keyY-liLen,keyX,keyY,keyX,keyY,keyX-liLen,keyY};
			break;
		default:
			pts=new float[]{};	
		}
		canvas.drawLines(pts, paint);
	}
	private void calAttr(){
		gameWidth=getWidth();
		gameHeight=getHeight();
		gridWidth=gameWidth/9;
		verticalSpace=(gameHeight-gridWidth*10)/2;
		stX=gridWidth/2;
		stY=verticalSpace+gridWidth/2;
		horLen=gridWidth*8;
		verLen=gridWidth*9;
		halfVerLen=gridWidth*4;
		pieceRadius=(gridWidth-pieceSpace)/2;
		pieceStX=stX-gridWidth/2;
		pieceStY=stY-gridWidth/2;
	}
	int selectedX=-1;
	int selectedY=-1;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			int x=(int) event.getX();
			int y=(int) event.getY();
			selectedX=(x-pieceStX)/gridWidth;
			selectedY=(y-pieceStY)/gridWidth;
			if(selectedX>=0&&selectedX<=8&&selectedY>=0&&selectedY<=9){
				manager.select(selectedX,selectedY);
			}
			invalidate();
		}
		return true;
	}
	public void newGame() {
		// TODO Auto-generated method stub
		manager.reset();
		invalidate();
	}
	public void undo() {
		// TODO Auto-generated method stub
		manager.undo();   
		invalidate();
	}
	public DataManager getManager() {
		// TODO Auto-generated method stub
		return manager;
	}

}
