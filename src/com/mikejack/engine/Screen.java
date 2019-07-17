package com.mikejack.engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mikejack.graphics.Font;
import com.mikejack.graphics.ImageRequest;
import com.mikejack.graphics.Light;
import com.mikejack.graphics.Sprite;

public class Screen {

    private GameContainer gc;
    private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();

    private int pW, pH;
    private int pixels[];
    private int zBuffer[];
    private int lightMap[];
    private int lightBlock[];

    private int ambientColour = 0xff232323;
    private int zDepth = 0;
    private boolean processing = false;

    private Font font = Font.STANDARD;

    public Screen(GameContainer gc) {
	this.gc = gc;
	pW = gc.getImageWidth();
	pH = gc.getImageHeight();
	pixels = ((DataBufferInt) gc.getImage().getRaster().getDataBuffer()).getData();
	zBuffer = new int[pixels.length];
	lightMap = new int[pixels.length];
	lightBlock = new int[pixels.length];
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

	for (int i = 0; i < pixels.length; i++) {
	    float r = ((lightMap[i] >> 16) & 0xff) / 255f;
	    float g = ((lightMap[i] >> 8) & 0xff) / 255f;
	    float b = (lightMap[i] & 0xff) / 255f;

	    pixels[i] = ((int) (((pixels[i] >> 16) & 0xff) * r) << 16 | (int) (((pixels[i] >> 8) & 0xff) * g) << 8
		    | (int) ((pixels[i] & 0xff) * b));
	}

	imageRequest.clear();
	processing = false;
    }

    public void setPixel(int x, int y, int colour) {
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
	    int newRed = ((pixelColour >> 16) & 0xff)
		    - (int) ((((pixelColour >> 16) & 0xff) - ((colour >> 16) & 0xff)) * (alpha / 255f));
	    int newGreen = ((pixelColour >> 8) & 0xff)
		    - (int) ((((pixelColour >> 8) & 0xff) - ((colour >> 8) & 0xff)) * (alpha / 255f));
	    int newBlue = (pixelColour & 0xff) - (int) (((pixelColour & 0xff) - (colour & 0xff)) * (alpha / 255f));

	    pixels[index] = (newRed << 16 | newGreen << 8 | newBlue);
	}
    }

    public void setLightMap(int x, int y, int colour) {
	if (x < 0 || x >= pW || y < 0 || y >= pH) {
	    return;
	}
	int baseColour = lightMap[x + y * pW];
	int finalColour = 0;
	int maxRed = Math.max((baseColour >> 16) & 0xff, (colour >> 16) & 0xff);
	int maxGreen = Math.max((baseColour >> 8) & 0xff, (colour >> 8) & 0xff);
	int maxBlue = Math.max(baseColour & 0xff, colour & 0xff);

	lightMap[x + y * pW] = (maxRed << 16 | maxGreen << 8 | maxBlue);
    }
    
    public void setLightBlock(int x, int y, int value) {
	if (x < 0 || x >= pW || y < 0 || y >= pH) {
	    return;
	}

	if (zBuffer[x + y * pW] > zDepth)
	    return;
	
	lightBlock[x + y * pW] = value;
    }

    public void drawText(String text, int offX, int offY, int colour) {

	int offset = 0;

	for (int i = 0; i < text.length(); i++) {
	    int unicode = text.codePointAt(i);

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
		setLightBlock(x + offX, y + offY, sprite.getLightBlock());
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
	for (int y = 0; y <= height; y++) {
	    for (int x = 0; x <= width; x++) {
		setPixel(x + offX, y + offY, colour);
	    }
	}
    }

    public void drawLight(Light light, int offX, int offY) {
	for (int i = 0; i <= light.getDiameter(); i++) {
	    drawLightLine(light, light.getRadius(), light.getRadius(), i, 0, offX, offY);
	    drawLightLine(light, light.getRadius(), light.getRadius(), i, light.getDiameter(), offX, offY);
	    drawLightLine(light, light.getRadius(), light.getRadius(), 0, i, offX, offY);
	    drawLightLine(light, light.getRadius(), light.getRadius(), light.getDiameter(), i, offX, offY);
	}
    }

    private void drawLightLine(Light light, int x0, int y0, int x1, int y1, int offX, int offY) {
	// Bresenham's line algorithm
	int dx = Math.abs(x1 - x0);
	int dy = Math.abs(y1 - y0);

	int sx = x0 < x1 ? 1 : -1;
	int sy = y0 < y1 ? 1 : -1;

	int err = dx - dy;
	int err2;

	while (true) {
	    int screenX = x0 - light.getRadius() + offX;
	    int screenY = y0 - light.getRadius() + offY;
	    
	    if (screenX < 0 || screenX >= pW || screenY < 0 || screenY >= pH)
		return;
	    
	    int lightColour =light.getLightValue(x0, y0);
	    if (lightColour == 0)
		return;
	    
	    if (lightBlock[screenX + screenY * pW] == Light.FULL)
		return;
	    
	    // Drawing line
	    setLightMap(screenX, screenY, lightColour);
	    
	    if (x0 == x1 && y0 == y1)
		break;
	    // Calculate line
	    err2 = 2 * err;
	    if (err2 > -1*dy) {
		err -= dy;
		x0 += sx;
	    }
	    if (err2 < dx) {
		err += dx;
		y0 += sy;
	    }

	}
    }

    public void clear() {
	for (int i = 0; i < pixels.length; i++) {
	    pixels[i] = 0;
	    zBuffer[i] = 0;
	    lightMap[i] = ambientColour;
	    lightBlock[i] = 0;
	}

    }

    public int getzDepth() {
	return zDepth;
    }

    public void setzDepth(int zDepth) {
	this.zDepth = zDepth;
    }

    public int getAmbientColour() {
	return ambientColour;
    }

    public void setAmbientColour(int ambientColour) {
	this.ambientColour = ambientColour;
    }

}
