package com.mikejack.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    public static final Sprite DEFAULT = new Sprite("/defaultSprite.png");
    private int width, height;
    private BufferedImage image;
    private int pixels[];
    private boolean alpha = false;
    private int lightBlock = Light.NONE;
    
    public Sprite(String path) {
	image = null;
	try {
	    image = ImageIO.read(Image.class.getResourceAsStream(path));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	width = image.getWidth();
	height = image.getHeight();
	pixels = image.getRGB(0, 0, width, height, null, 0, width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

    public int getLightBlock() {
        return lightBlock;
    }

    public void setLightBlock(int lightBlock) {
        this.lightBlock = lightBlock;
    }

    public BufferedImage getImage() {
        return image;
    }
    
}
