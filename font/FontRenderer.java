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
	
	public void renderLetter(PixelImage img, int x, int y, Letter l, Color color) {
		if(img == null) { System.out.println("Error: PixelImage was null (FontRenderer.renderLetter"); return; }
		
		if(l==null) { return; }
		
		for(int j=0; j<l.getHeight(); j++) {
			for(int k=0; k<l.getWidth(); k++) {
				if(l.isPixelAt(k,j)) {
					img.setPixel(x+k, y+j, color);
				}
			}
		}
	}
	
	/**
	 * Renders a given string on a given instance of PixelImage
	 * 
	 * @param img - the {PixelImage} canvas to draw on
	 * @param x - x coordinate of top-left corner of text
	 * @param y - y coordinate of top-left corner of text
	 * @param text - the text to render (no automatic line break!)
	 * @param colors - array of text colors
	 */
	public void render(PixelImage img, int x, int y, String text, Color[] colors) {
		if(img == null) { System.out.println("Error: PixelImage was null (FontRenderer.render"); return; }
		if(colors.length == 0) { return; }
		
		for(int i=0; i<text.length(); i++) {
			Letter l = font.getLetter(text.charAt(i));
			
			if(l==null) { continue; }
			
			renderLetter(img, x, y, l, colors[i % colors.length]);
			
			x += l.getWidth() + charOffset;
		}
		
	}
	
	public void render(PixelImage img, int x, int y, String text, Color color) {
		Color[] colors = {color};
		render(img, x, y, text, colors);
	}
	
	public int getTextWidth(String text) {
		int length = 0;
		int n = 0;
		
		for(int i=0; i<text.length(); i++) {
			Letter l = font.getLetter(text.charAt(i));
			
			if(l==null) { continue; }
			else { n++; }
			
			length += l.getWidth() + charOffset;
		}
		
		if(n>0) { length -= 1 + charOffset; }
		
		return length;
	}
	
}
