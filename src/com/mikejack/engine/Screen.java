package com.mikejack.engine;

import java.awt.image.DataBufferInt;

public class Screen {
    private int pW, pH;
    private int pixels[];
    
    
    public Screen(GameContainer gc) {
	pW = gc.getImageWidth();
	pH = gc.getImageHeight();
	pixels = ((DataBufferInt)gc.getImage().getRaster().getDataBuffer()).getData();
    }
    
    
    public void fillRect(int x, int y, int width, int height, int colour){
    	for(int i = y; i < height; i++) {
    		for(int j = x; j < width; j++) {
    			pixels[j + i*pW] = colour;
    		}
    	}
    }
    
    public void fillCirc(int x, int y, int radius, int colour) {
    	
    	
    }
    
    public void clear() {
	for (int i = 0; i < pixels.length; i++) {
	     pixels[i] = 0xff000000;
	}
	
    }
    
}
