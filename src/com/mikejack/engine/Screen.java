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
    
    public void clear() {
	for (int i = 0; i < pixels.length; i++) {
	     pixels[i] = 0xff000000;
	}
    }
    
}
