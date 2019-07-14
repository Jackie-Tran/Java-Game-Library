package com.mikejack.testing;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.mikejack.audio.AudioClip;
import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite sprite = Sprite.DEFAULT;
    private AudioClip jump = new AudioClip("/sounds/jump.wav");
    
    public TestGame() {
	jump.setVolume(-10);
    }
    
    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
	    jump.play();
	}
    }
    @Override
    public void render(GameContainer gc) {
    	gc.getScreen().drawSprite(sprite, 100, 100);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
    }

}
