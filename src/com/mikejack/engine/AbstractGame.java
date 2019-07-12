package com.mikejack.engine;

import com.mikejack.gamestate.GameStateManager;

public abstract class AbstractGame {
	
	protected GameStateManager gsm = new GameStateManager();
	
	public void update(GameContainer gc) {
		gsm.update();
	}
	
	public abstract void render(GameContainer gc);

}
