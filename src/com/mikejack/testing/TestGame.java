package com.mikejack.testing;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.mikejack.audio.AudioClip;
import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite sprite = new Sprite("/game/test.png");
    private Sprite sprite2 = new Sprite("/game/test2.png");
    public TestGame() {
	sprite.setAlpha(true);
    }
    
    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	
    }
    @Override
    public void render(GameContainer gc) {
	for (int x = 0; x < sprite.getWidth(); x++) {
	    for (int y = 0; y < sprite.getHeight(); y++) {
		gc.getScreen().setLightMap(x, y, sprite.getPixels()[x + y * sprite.getWidth()]);
	    }
	}
	gc.getScreen().drawSprite(sprite2, gc.getInput().getMouseX(), gc.getInput().getMouseY());
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
    }

}
