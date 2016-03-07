package breakout.font;

import java.awt.Color;

import breakout.assets.PixelImage;

public class FontRenderer {
	
	private Font font;
	
	private int charOffset = 0;
	
	public FontRenderer(Font font) {
		this.font = font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setCharOffset(int k) {
		charOffset = k;
	}
	
	/**
	 * Renders a given string on a given instance of PixelImage
	 * 
	 * @param img - the {PixelImage} canvas to draw on
	 * @param x - x coordinate of top-left corner of text
	 * @param y - y coordinate of top-left corner of text
	 * @param text - the text to render (no automatic line break!)
	 * @param color - the text color
	 */
	public void render(PixelImage img, int x, int y, String text, Color color) {
		if(img == null) { return; }
		
		for(int i=0; i<text.length(); i++) {
			Letter l = font.getLetter(text.charAt(i));
			
			if(l==null) { continue; }
			
			for(int j=0; j<l.getHeight(); j++) {
				for(int k=0; k<l.getWidth(); k++) {
					if(l.isPixelAt(k,j)) {
						img.setPixel(x+k, y+j, color);
					}
				}
			}
			
			x += l.getWidth() + charOffset;
		}
		
	}
	
}
