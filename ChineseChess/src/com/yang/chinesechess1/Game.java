package com.yang.chinesechess1;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Game extends Activity {
	GameView game;
	Button newGame;
	Button set;
	Button undo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		game = (GameView) findViewById(R.id.gameview);
		initButton();
	}

	Button red;
	Button black;
	Button intelligence;
	private void initButton() {
		newGame = (Button) findViewById(R.id.newgame);
		set = (Button) findViewById(R.id.set);
		undo = (Button) findViewById(R.id.undo);
		newGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				game.newGame();
			}
		});
		set.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog dialog = new Dialog(Game.this);
				dialog.setContentView(R.layout.set);
				dialog.setTitle("����");
				red = (Button) dialog.findViewById(R.id.red);
				black = (Button) dialog.findViewById(R.id.black);
				intelligence=(Button) dialog.findViewById(R.id.intelligence);
				red.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						String str = (String) red.getText();
						if (str.compareTo("��") == 0) {
							red.setText("����");
							game.getManager().setRed(false);
						} else {
							red.setText("��");
							game.getManager().setRed(true);
						}

					}
				});
				black.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						String str = (String) black.getText();
						if (str.compareTo("��") == 0) {
							black.setText("����");
							game.getManager().setBlack(false);
						} else {
							black.setText("��");
							game.getManager().setBlack(true);
						}

					} 
				});
				intelligence.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						String str = (String) intelligence.getText();
						if (str.compareTo("��") == 0) {
							intelligence.setText("��ͨ");
							game.getManager().setIntell(1);
						} else if (str.compareTo("��ͨ") == 0){
							intelligence.setText("����");
							game.getManager().setIntell(2);
						}else{
							intelligence.setText("��");
							game.getManager().setIntell(0);
						}
 
					}
				});
				dialog.show();
			}
		});
		undo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				game.undo();
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return game.onTouchEvent(event);
	}
}
