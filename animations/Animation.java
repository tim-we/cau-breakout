package breakout.animations;

import breakout.assets.PixelImage;

public abstract class Animation {
	
	protected int width;
	protected int height;
	
	protected int frames = 0;
	
	protected int currentFrame = 0;
	
	protected boolean finished = false;
	
	public int numFrames() { return frames; }
	
	public void renderNextFrame(PixelImage img) {};
	
	public boolean hasFinished() { return finished; }
}
