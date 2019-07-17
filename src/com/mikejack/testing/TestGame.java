package com.mikejack.testing;

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
	gc.getScreen().drawSprite(sprite, gc.getInput().getMouseX(), gc.getInput().getMouseY());
	gc.getScreen().drawSprite(sprite2, 100, 100);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
    }

}
