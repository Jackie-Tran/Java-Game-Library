package com.mikejack.testing;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.graphics.Light;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite background = new Sprite("/game/background.png");
    private Light light = new Light(100, 0xffffffff);
    private int lightX = 0;
    private int velX = 5;
    
    public TestGame() {
	Screen.setAmbientLight(0);
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
	screen.drawLight(light, lightX, 0);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(430, 240, 3, new TestGame());
	gc.setFpsVisibility(false);
	gc.start();
    }

}
