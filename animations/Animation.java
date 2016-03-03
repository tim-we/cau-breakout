package breakout.animations;

import breakout.assets.PixelImage;

public abstract class Animation {
	
	private int width;
	private int height;
	
	private int frames = 0;
	
	private int currentFrame = 0;
	
	public PixelImage getNextFrame() {
		return null;
	};
	
}
