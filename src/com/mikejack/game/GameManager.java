package com.mikejack.game;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Renderer;

public class GameManager extends AbstractGame{

	public GameManager() {
		
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		//test to see if action works
		if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			System.out.println("A was pressed");
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
