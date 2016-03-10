package breakout.font;

public class Letter {
	
	private String PixelData;
	
	private int width;
	private int height;
	
	/**
	 * Creates a new Letter
	 * @param width width of the Letter in Windows
	 * @param height height of the Letter in Windows
	 * @param data String which describes the Letter
	 */
	public Letter(int width, int height, String data) {
		if(data.length() == width*height) {		
			this.PixelData = data;
			this.width = width;
			this.height = height;
		} else {
			throw new IllegalArgumentException("letter data does not fit letter size");
		}
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	/**
	 * Checks if a Pixel at requested Position has to be filled for this letter
	 * @param x the column of the pixel
	 * @param y the row of the pixel
	 * @return true if this Pixel has to be set, false if not
	 */
	public boolean isPixelAt(int x, int y) {
		if(x<0 && x>=width) { return false; }
		if(y<0 && y>=height) { return false; }
		
		return PixelData.charAt(y*width + x) == '1';
	}
	
}
