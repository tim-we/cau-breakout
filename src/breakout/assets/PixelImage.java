package breakout.assets;

import java.awt.Color;
import java.util.Arrays;

public class PixelImage {
	
	/* Farbe des Hintergrunds */
	public static final Color BGCOLOR = Color.BLACK;
	
	private int width = 0;
	private int height = 0;
	
	/* Color-Array contains the Colors for the Windows */
	private Color[] imageData;
	
	/**
	 * Creates a new PixelImage with given width and height
	 * @param width The width of the PixelImage
	 * @param height the height of the PixelImage
	 */
	public PixelImage(int width, int height) {
		if(width<0 || height<0) { throw new IllegalArgumentException(); }
		
		this.width = width;
		this.height = height;
		this.imageData = new Color[width*height];
	}
	
	/**
	 * PixelImage for lighthouse
	 */
	public PixelImage() {
		this.width = 28;
		this.height = 14;
		this.imageData = new Color[width*height];
	}
	
	/**
	 * Duplicates a given PixelImage
	 * @param img the PixelImage to duplicate
	 */
	public PixelImage(PixelImage img) {
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.imageData = new Color[width*height];
		
		this.imageData = Arrays.copyOf(img.getColorArray(), img.getColorArray().length);
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	/**
	 * clears the actual PixelImage
	 */
	public void clear() {
		imageData = new Color[width*height];
	}
	
	/**
	 * Sets the PixelImage to a color
	 * @param color the Color the PixelImage shall show
	 */
	public void fill(Color color) {
		for(int i=0; i<imageData.length; i++) {
			if(imageData[i] == null) {
				imageData[i] = color;
			} else {
				imageData[i] = blendColors(imageData[i], color);
			}
		}
	} 
	
	/**
	 * Gives the index of a column, row position in the imageData array
	 * @param column the requested column
	 * @param row the requested row
	 * @return The position in the Array
	 */
	private int getArrayIndex(int column, int row) {
		if(column<0 || width<=column) { return -1; }
		if(row<0 || height<=row) { return -1; }
		
		return (row*width + column);
	}
	
	/**
	 * Sets the column, row position on a color
	 * @param column the requested column
	 * @param row the requested row
	 * @param color the color for this position
	 */
	public void setPixel(int column, int row, Color color) {
		int i = getArrayIndex(column, row);
			
		if(i>=0) { this.imageData[i] = color; }
	}
	
	/**
	 * Gives the Color of a column, row position
	 * @param column the requested column
	 * @param row the requested row
	 * @param emptyColor the color to return if the position in the array is null
	 * @return the color at requested position or emptyColor if null
	 */
	public Color getPixel(int column, int row, Color emptyColor) {
		int i = getArrayIndex(column, row);
		
		if(i<0) { return null; }
		
		return this.imageData[i]==null ? emptyColor : this.imageData[i];
	}
	
	/**
	 * Returns the color of the requested position
	 * @param x the column
	 * @param y the row
	 * @return the color at x,y position or Backgroundcolor if none there yet
	 */
	public Color getPixel(int x, int y) {
		return getPixel(x, y, BGCOLOR);
	}
	
	/**
	 * Blending of two given Colors
	 * @param bg Background Color
	 * @param fg Foreground Color
	 * @param alpha the alpha value of the Color
	 * @param mode BlendingMode
	 * @return the Color which gets created by blending two Colors
	 */
	public static Color blendColors(Color bg, Color fg, double alpha, BlendingMode mode) {
		if(fg==null) { return bg; }
		if(bg==null) { return fg; }
		
		/* a has to be within 0 and 1 */
		double a = Math.min(1, Math.max(alpha, 0));
		
		int r = 0, g = 0, b = 0;
		
		/* Adds the Red,Green and Blue values of Foreground and Background with given weight a */
		if(mode == BlendingMode.NORMAL) {
			r = (int)Math.round(a*fg.getRed() + (1-a)*bg.getRed());
			g = (int)Math.round(a*fg.getGreen() + (1-a)*bg.getGreen());
			b = (int)Math.round(a*fg.getBlue() + (1-a)*bg.getBlue());
		} 
		/* Adds the Red,Green and Blue values of the Foreground multiplied with a to the background values, max 255 */
		else if(mode == BlendingMode.ADDITIVE) {
			r = Math.min( (int)Math.round(a*fg.getRed() + bg.getRed()), 255);
			g = Math.min( (int)Math.round(a*fg.getGreen() + bg.getGreen()), 255);
			b = Math.min( (int)Math.round(a*fg.getBlue() + bg.getBlue()), 255);
		}
		/* Multiplies the Background and Foreground Colorvalues */
		else if(mode == BlendingMode.MULTIPLY) {
			//implement alpha!
			r = (int)Math.round( 255d * ((bg.getRed()/255d) * (fg.getRed()/255d)) );
			g = (int)Math.round( 255d * ((bg.getGreen()/255d) * (fg.getGreen()/255d)) );
			b = (int)Math.round( 255d * ((bg.getBlue()/255d) * (fg.getBlue()/255d)) );
		}
		
		return new Color(r,g,b);
		
	}
	
	/**
	 * Blends two given Colors with given BlendingMode, alpha taken from foreground Color
	 * @param bg Background Color
	 * @param fg Foreground Color
	 * @param mode the chosen BlendingMode
	 * @return the new Color
	 */
	public static Color blendColors(Color bg, Color fg, BlendingMode mode) {
		return blendColors(bg, fg, fg.getAlpha()/255d, mode);
	}
	
	/**
	 * Blends two given Colors with BlendingMode normal, alpha taken from foreground Color
	 * @param bg Background Color
	 * @param fg Foreground Color
	 * @return
	 */
	public static Color blendColors(Color bg, Color fg) {
		return blendColors(bg, fg, fg.getAlpha()/255d, BlendingMode.NORMAL);
	}
	
	public Color[] getColorArray() { return imageData; }
	
	/**
	 * Calculates a byteArray, as needed for Lighthouse from the
	 * ImageData array which contains the Colors of our PixelImage
	 * @return The ByteArray as needed for Lighthouse
	 */
	public byte[] getByteArray() {
		byte[] buf = new byte[width*height*3];
		
		int i = 0;
		
		for(int row=height-1; row>=0; row--) {
			for(int col=0; col<width; col++) {
				Color clr = getPixel(col, row, BGCOLOR);
				
				int r = clr.getRed();
				int g = clr.getGreen();
				int b = clr.getBlue();				
				
				buf[i] = (byte)r;
				buf[i +1] = (byte)g;
				buf[i +2] = (byte)b;
				
				i = i+3;
			}
		}
		
		return buf;
	}
}
