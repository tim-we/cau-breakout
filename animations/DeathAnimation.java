package breakout.animations;

import java.awt.Color;

import breakout.assets.BreakoutConstants;
import breakout.assets.PixelImage;

import breakout.font.HighriserFont;
import breakout.font.FontRenderer;

public class DeathAnimation extends Animation {
	
	private FontRenderer fr;
	
	private static final Color textColor = new Color(255,255,255);
	
	public DeathAnimation() {
		width = BreakoutConstants.WINDOW_COLUMNS;
		height = BreakoutConstants.WINDOW_ROWS;
		
		frames = 5;
		
		fr = new FontRenderer(new HighriserFont());
	}
	
	@Override
	public void renderNextFrame(PixelImage frame) {
		//clear Frame
		frame.clear();
		
		//render Text on frame
		fr.render(frame, 1, 0+currentFrame, "WASTED!", textColor);
		
		currentFrame++;
		
		if(currentFrame > frames) {
			finished = true;
			currentFrame = 0;
		}
	}
}
