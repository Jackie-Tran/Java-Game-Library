package com.mikejack.game;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;

public class GameManager extends AbstractGame {

	@Override
	public void update(GameContainer gc) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GameContainer gc) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
