package breakout.assets;

import java.awt.Color;
import java.util.Arrays;

public class PixelImage {
	
	public static final Color BGCOLOR = Color.BLACK;
	
	private int width = 0;
	private int height = 0;
	
	private Color[] imageData;
	
	public PixelImage(int width, int height) {
		if(width<0 || height<0) { throw new IllegalArgumentException(); }
		
		this.width = width;
		this.height = height;
		this.imageData = new Color[width*height];
	}
	
	public PixelImage() {
		this.width = 28;
		this.height = 14;
		this.imageData = new Color[width*height];
	}
	
	public PixelImage(PixelImage img) {
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.imageData = new Color[width*height];
		
		this.imageData = Arrays.copyOf(img.getColorArray(), img.getColorArray().length);
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return width; }
	
	public void clear() {
		imageData = new Color[width*height];
	}
	
	public void fill(Color color) {
		for(int i=0; i<imageData.length; i++) {
			if(imageData[i] == null) {
				imageData[i] = color;
			} else {
				imageData[i] = blendColors(imageData[i], color);
			}
		}
	} 
	
	private int getArrayIndex(int column, int row) {
		if(column<0 || width<=column) { return -1; }
		if(row<0 || height<=row) { return -1; }
		
		return (row*width + column);
	}
	
	public void setPixel(int column, int row, Color color) {
		int i = getArrayIndex(column, row);
			
		if(i>=0) { this.imageData[i] = color; }
	}
	
	public Color getPixel(int column, int row, Color emptyColor) {
		int i = getArrayIndex(column, row);
		
		if(i<0) { return null; }
		
		return this.imageData[i]==null ? emptyColor : this.imageData[i];
	}
	
	public Color getPixel(int x, int y) {
		return getPixel(x, y, BGCOLOR);
	}
	
	public static Color blendColors(Color bg, Color fg, double alpha, BlendingMode mode) {
		if(fg==null) { return bg; }
		if(bg==null) { return fg; }
		
		double a = Math.min(1, Math.max(alpha, 0));
		
		int r = 0, g = 0, b = 0;
		
		if(mode == BlendingMode.NORMAL) {
			r = (int)Math.round(a*fg.getRed() + (1-a)*bg.getRed());
			g = (int)Math.round(a*fg.getGreen() + (1-a)*bg.getGreen());
			b = (int)Math.round(a*fg.getBlue() + (1-a)*bg.getBlue());
		} 
		else if(mode == BlendingMode.ADDITIVE) {
			r = Math.min( (int)Math.round(a*fg.getRed() + bg.getRed()), 255);
			g = Math.min( (int)Math.round(a*fg.getGreen() + bg.getGreen()), 255);
			b = Math.min( (int)Math.round(a*fg.getBlue() + bg.getBlue()), 255);
		}
		else if(mode == BlendingMode.MULTIPLY) {
			//implement alpha!
			r = (int)Math.round( 255d * ((bg.getRed()/255d) * (fg.getRed()/255d)) );
			g = (int)Math.round( 255d * ((bg.getGreen()/255d) * (fg.getGreen()/255d)) );
			b = (int)Math.round( 255d * ((bg.getBlue()/255d) * (fg.getBlue()/255d)) );
		}
		
		return new Color(r,g,b);
		
	}
	
	public static Color blendColors(Color bg, Color fg, BlendingMode mode) {
		return blendColors(bg, fg, fg.getAlpha()/255d, mode);
	}
	
	public static Color blendColors(Color bg, Color fg) {
		return blendColors(bg, fg, fg.getAlpha()/255d, BlendingMode.NORMAL);
	}
	
	public Color[] getColorArray() { return imageData; }
	
	public byte[] getByteArray() {
		byte[] buf = new byte[width*height*3];
		
		for(int i=imageData.length-1; i>=0; i--) {
			Color clr = imageData[i]==null ? BGCOLOR : imageData[i];
	
			if(clr.getAlpha() < 255) { clr = blendColors(BGCOLOR, clr); }
			
			int r = clr.getRed();
			int g = clr.getGreen();
			int b = clr.getBlue();
			
			int buf_index = i*3;
			
			buf[buf_index] = (byte)r;
			buf[buf_index +1] = (byte)g;
			buf[buf_index +2] = (byte)b;
		}
		
		return buf;
	}
}
