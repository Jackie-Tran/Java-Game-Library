package com.mikejack.testing;

import java.awt.event.KeyEvent;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite sprite = Sprite.DEFAULT;
    private int spriteX = 0, spriteY = 0;
    
    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	spriteX = gc.getInput().getMouseX()-16;
	spriteY = gc.getInput().getMouseY()-16;
    }

    @Override
    public void render(GameContainer gc) {
    	gc.getScreen().fillRect(100, 100, 50, 50, 255);
    	gc.getScreen().drawSprite(sprite, spriteX, spriteY);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
}

}
