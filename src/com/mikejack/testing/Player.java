package com.mikejack.testing;

import java.awt.event.KeyEvent;

import com.mikejack.engine.GameContainer;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Tag;

public class Player extends GameObject{

    public Player(int x, int y, int width, int height, Tag tag) {
	super(x, y, width, height, tag);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	if (gc.getInput().isKey(KeyEvent.VK_A)) {
	    velX = -5;
	}
	if (gc.getInput().isKey(KeyEvent.VK_D)) {
	    velX = 5;
	}
	if (gc.getInput().isKey(KeyEvent.VK_W)) {
	    velY = -5;
	}
	if (gc.getInput().isKey(KeyEvent.VK_S)) {
	    velY = 5;
	}
	if (!gc.getInput().isKey(KeyEvent.VK_A) && !gc.getInput().isKey(KeyEvent.VK_D)) {
	    velX = 0;
	}
	if (!gc.getInput().isKey(KeyEvent.VK_W) && !gc.getInput().isKey(KeyEvent.VK_S)) {
	    velY = 0;
	}
	x += velX;
	y += velY;
	
    }

    @Override
    public void render(GameContainer gc) {
	// TODO Auto-generated method stub
	gc.getScreen().drawSprite(sprite, x, y);
	gc.getScreen().fillRect(x, y, width, height, 0xffff00ff);
    }

}
