package com.mikejack.testing;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.mikejack.audio.AudioClip;
import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite sprite = Sprite.DEFAULT;
    private AudioClip music = new AudioClip("/sounds/loop2.wav");
    private Player player;
    private Sprite background = new Sprite("/game/background.png");
    public TestGame() {
	music.setVolume(-10);
	music.loop();
	player = new Player(100, 100, 16, 16, null);
    }
    
    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	player.update(gc);
	
    }
    @Override
    public void render(GameContainer gc) {
	gc.getScreen().drawSprite(background, 0, 0);
    	gc.getScreen().drawSprite(sprite, 100, 100);
    	player.render(gc);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
    }

}
