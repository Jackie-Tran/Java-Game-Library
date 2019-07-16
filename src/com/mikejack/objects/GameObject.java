package com.mikejack.objects;

import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;

public abstract class GameObject {

    protected Sprite sprite = Sprite.DEFAULT;
    protected Tag tag;
    protected int x, y, width, height;
    protected float velX=0, velY=0;
    
    public GameObject(int x, int y, int width, int height, Tag tag) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.tag = tag;
    }
    
    public GameObject(int x, int y,  int width, int height, Tag tag, Sprite sprite) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.tag = tag;
	this.sprite = sprite;
    }
    
    public abstract void update(GameContainer gc);
    public abstract void render(GameContainer gc);

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }    
    
}
