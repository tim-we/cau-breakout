package breakout.font;

public class Letter {
	
	private String PixelData;
	
	private int width;
	private int height;
	
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
	
	public boolean isPixelAt(int x, int y) {
		if(x<0 && x>=width) { return false; }
		if(y<0 && y>=height) { return false; }
		
		return PixelData.charAt(y*width + x) == '1';
	}
	
}
