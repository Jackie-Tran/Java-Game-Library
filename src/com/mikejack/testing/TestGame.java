package com.mikejack.testing;

import com.mikejack.audio.AudioClip;
import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.graphics.Light;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite background = new Sprite("/game/spritesheet.png");
    private Sprite block = new Sprite("/defaultSprite.png");
    private Light light = new Light(200, 0xffffffff);
    private int lightX = 0;
    private int velX = 5;
    
    public TestGame() {
	block.setLightBlock(Light.FULL);
    }
    
    @Override
    public void update(GameContainer gc, float dt) {
	lightX += velX;
	if (lightX <= 0 || lightX >= gc.getImageWidth()) {
	    velX *= -1;
	}
    }
    @Override
    public void render(GameContainer gc, Screen screen) {
	screen.drawSprite(background, 0, 0);
	screen.drawSprite(block, 100, 0);
	screen.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
	screen.fillRect(0, 0, 16, 16, 0xffffffff);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(430, 240, 3, new TestGame());
	gc.setFpsVisibility(false);
	gc.start();
    }

}
