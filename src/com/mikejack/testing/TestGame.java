package com.mikejack.testing;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Light;
import com.mikejack.graphics.Sprite;

public class TestGame extends AbstractGame {

    private Sprite sprite = new Sprite("/game/test.png");
    private Sprite sprite2 = new Sprite("/game/test2.png");
    private Light light = new Light(100, 0xff00ffff);
    public TestGame() {
	sprite.setLightBlock(Light.FULL);
    }
    
    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	
    }
    @Override
    public void render(GameContainer gc) {
	
	gc.getScreen().setzDepth(0);
	gc.getScreen().drawSprite(sprite2, 0, 0);
	gc.getScreen().drawSprite(sprite, 100, 100);
	gc.getScreen().drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(new TestGame());
	gc.start();
    }

}
