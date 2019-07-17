package com.mikejack.engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mikejack.graphics.Font;
import com.mikejack.graphics.ImageRequest;
import com.mikejack.graphics.Sprite;

public class Screen {

    private GameContainer gc;
    private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();
    
    private int pW, pH;
    private int pixels[];
    private int zBuffer[];
    private int zDepth = 0;
    private boolean processing = false;

    private Font font = Font.STANDARD;

    public Screen(GameContainer gc) {
	this.gc = gc;
	pW = gc.getImageWidth();
	pH = gc.getImageHeight();
	pixels = ((DataBufferInt) gc.getImage().getRaster().getDataBuffer()).getData();
	zBuffer = new int[pixels.length];
    }

    public void process() {
	processing = true;
	Collections.sort(imageRequest, new Comparator<ImageRequest>() {
	    @Override
	    public int compare(ImageRequest i0, ImageRequest i1) {
		if (i0.zDepth < i1.zDepth) 
		    return -1;
		if (i0.zDepth > i1.zDepth)
		    return 1;
		return 0;
	    }
	    
	});
	
	for (int i = 0; i < imageRequest.size(); i++) {
	    ImageRequest ir = imageRequest.get(i);
	    setzDepth(ir.zDepth);
	    drawSprite(ir.sprite, ir.offX, ir.offY);
	}
	
	imageRequest.clear();
	processing = false;
    }
    
    private void setPixel(int x, int y, int colour) {
	int alpha = (colour >> 24) & 0xff;
	if ((x < 0 || x >= pW || y < 0 || y >= pH) || alpha == 0) {
	    return;
	}
	int index = x + y * pW;

	if (zBuffer[index] > zDepth)
	    return;
	
	zBuffer[index] = zDepth;

	if (alpha == 255) {
	    pixels[index] = colour;
	} else {
	    int pixelColour = pixels[index];
	    int newRed = ((pixelColour >> 16) & 0xff) - (int)((((pixelColour >> 16) & 0xff) - ((colour >> 16) & 0xff)) * (alpha / 255f));
	    int newGreen = ((pixelColour >> 8) & 0xff) - (int)((((pixelColour >> 8) & 0xff) - ((colour >> 8) & 0xff)) * (alpha / 255f));
	    int newBlue = (pixelColour & 0xff) - (int)(((pixelColour & 0xff) - (colour & 0xff)) * (alpha / 255f));
	    
	    pixels[index] = (255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
	}
    }

    public void drawText(String text, int offX, int offY, int colour) {

	text = text.toUpperCase();
	int offset = 0;

	for (int i = 0; i < text.length(); i++) {
	    int unicode = text.codePointAt(i) - 32;

	    for (int y = 0; y < font.getFontImage().getHeight(); y++) {

		for (int x = 0; x < font.getWidths()[unicode]; x++) {

		    if (font.getFontImage().getPixels()[(x + font.getOffsets()[unicode])
			    + y * font.getFontImage().getWidth()] == 0xffffffff) {
			setPixel(x + offX + offset, y + offY, colour);
		    }
		}
	    }

	    offset += font.getWidths()[unicode];
	}

    }

    public void drawSprite(Sprite sprite, int offX, int offY) {
	
	if (sprite.isAlpha() && !processing) {
	    imageRequest.add(new ImageRequest(sprite, zDepth, offX, offY));
	    return;
	}
	
	int newX = 0;
	int newY = 0;
	int newWidth = sprite.getWidth();
	int newHeight = sprite.getHeight();

	// Off Screen
	if (offX < -newWidth)
	    return;
	if (offY < -newHeight)
	    return;
	if (offX >= pW)
	    return;
	if (offY >= pH)
	    return;

	// Clipping Sprites
	if (offX < 0)
	    newX -= offX;
	if (offY < 0)
	    newY -= offY;
	if (offX + newWidth >= pW)
	    newWidth -= newWidth + offX - pW;
	if (offY + newHeight >= pH)
	    newHeight -= newHeight + offY - pH;

	for (int y = newY; y < newHeight; y++) {
	    for (int x = newX; x < newWidth; x++) {
		setPixel(x + offX, y + offY, sprite.getPixels()[x + y * sprite.getWidth()]);
	    }
	}
    }

    public void drawRect(int offX, int offY, int width, int height, int colour) {
	for (int y = 0; y <= height; y++) {
	    setPixel(offX, y + offY, colour);
	    setPixel(offX + width, y + offY, colour);
	}

	for (int x = 0; x < width; x++) {
	    setPixel(x + offX, offY, colour);
	    setPixel(x + offX, offY + height, colour);
	}
    }

    public void fillRect(int offX, int offY, int width, int height, int colour) {
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x <= width; x++) {
		setPixel(x + offX, y + offY, colour);
	    }
	}
    }

    public void clear() {
	for (int i = 0; i < pixels.length; i++) {
	    pixels[i] = 0;
	    zBuffer[i] = 0;
	}

    }

    public int getzDepth() {
	return zDepth;
    }

    public void setzDepth(int zDepth) {
	this.zDepth = zDepth;
    }

}
