package com.mikejack.gamestate;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
	    this.gsm = gsm;
	}
	
	public abstract void init();	
	public abstract void update(GameContainer gc);
	public abstract void render(GameContainer gc, Screen screen);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}
	
