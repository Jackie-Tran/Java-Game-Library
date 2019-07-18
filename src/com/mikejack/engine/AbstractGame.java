package com.mikejack.engine;

import com.mikejack.gamestate.GameStateManager;

public abstract class AbstractGame {
	
	protected GameStateManager gsm = new GameStateManager();
	
	public abstract void update(GameContainer gc, float dt);
	public abstract void render(GameContainer gc, Screen screen);

}
