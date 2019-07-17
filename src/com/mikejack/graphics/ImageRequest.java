package com.mikejack.graphics;

public class ImageRequest {

    public Sprite sprite;
    public int zDepth, offX, offY;
    
    public ImageRequest(Sprite sprite, int zDepth, int offX, int offY) {
	this.sprite = sprite;
	this.zDepth = zDepth;
	this.offX = offX;
	this.offY = offY;
    }
    
}
