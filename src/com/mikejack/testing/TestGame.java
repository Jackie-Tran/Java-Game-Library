package com.mikejack.testing;

import java.awt.event.KeyEvent;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;

public class TestGame extends AbstractGame {

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	if (gc.getInput().isKey(KeyEvent.VK_A)) {
	    System.out.println("A is pressed");
	}
    }

    @Override
    public void render(GameContainer gc) {
    	gc.getScreen().fillRect(10, 10, 100, 100, 255);

    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
}

}
