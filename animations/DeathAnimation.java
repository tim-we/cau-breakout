package breakout.animations;

import java.awt.Color;

import breakout.assets.BreakoutConstants;
import breakout.assets.PixelImage;

import breakout.font.HighriserFont;
import breakout.font.FontRenderer;

public class DeathAnimation extends Animation {
	
	private FontRenderer fr;
	
	private PixelImage background;
	
	public DeathAnimation() {
		width = BreakoutConstants.WINDOW_COLUMNS;
		height = BreakoutConstants.WINDOW_ROWS;
		
		frames = 15;
		
		fr = new FontRenderer(new HighriserFont());
	}
	
	@Override
	public PixelImage renderNextFrame(PixelImage frame) {
		//last frame as background
		if(currentFrame == 0) {
			frame.fill( new Color(0,0,0,200) );
			background = new PixelImage(frame); //clone image
		} else {
			frame = new PixelImage(background);
		}
		
		//red fill
			int red_alpha = (int)(Math.sin(currentFrame/3d) * 100);
			frame.fill( new Color(255, 0, 0, Math.max(0, red_alpha)) );
		
		//render Text on frame
			int v = currentFrame <= 6 ? (42 * currentFrame) : 255;
			Color textColor = new Color(v,v,v);
			fr.render(frame, 1, Math.min(currentFrame, 5)-1, "WASTeD!", textColor);
		
		currentFrame++;
		
		if(currentFrame > frames) {
			finished = true;
			currentFrame = 0;
		}
		
		return frame;
	}
}
