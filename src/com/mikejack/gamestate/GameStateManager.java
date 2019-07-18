package com.mikejack.gamestate;

import java.util.ArrayList;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	
	// Create final int for GameState IDS
	public static final int DEFUALTSTATE = 0;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		
		currentState = DEFUALTSTATE;
		//gameStates.add(new MenuState(this));
	}
		
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update(GameContainer gc) {
		gameStates.get(currentState).update(gc);
	}
	
	public void draw(GameContainer gc, Screen screen) {
		gameStates.get(currentState).render(gc, screen);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
}
