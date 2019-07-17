package com.mikejack.graphics;

public class Font {
	
	//will be standard font that will load in
	public static final Font STANDARD = new Font("/fonts/comic.png");
	
	private Sprite fontImage;
	private int[] offsets;
	private int[] widths;
	
	public Font(String path) {
		fontImage = new Sprite(path);
		
		offsets = new int[256];
		widths = new int[256];
		
		int unicode = 0;
		
		for(int i = 0; i < fontImage.getWidth(); i++) {
			//when reaches blue colour
			if(fontImage.getPixels()[i] == 0xff0000ff) {
				offsets[unicode] = i;
			}
			
			//when reaches yellow colour
			if(fontImage.getPixels()[i] == 0xffffff00) {
				widths[unicode] = i - offsets[unicode];
				unicode++;
			}
		}
	}

	public Sprite getFontImage() {
		return fontImage;
	}

	public void setFontImage(Sprite fontImage) {
		this.fontImage = fontImage;
	}

	public int[] getOffsets() {
		return offsets;
	}

	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}

	public int[] getWidths() {
		return widths;
	}

	public void setWidths(int[] widths) {
		this.widths = widths;
	}
	
}
