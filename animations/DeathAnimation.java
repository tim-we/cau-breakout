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
		
		background = new PixelImage(28,14);
		
		fr = new FontRenderer(new HighriserFont());
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		//last frame as background
		if(currentFrame == 0) {
			background = new PixelImage(frame); //clone image
		}
		
		frame = new PixelImage(background);
		
		int alpha = Math.min(50 * currentFrame, 200);
		frame.fill( new Color(0,0,0,alpha) );
		
		//red fill
			int red_alpha = (int)(Math.sin(currentFrame/3d) * 100);
			frame.fill( new Color(255, 0, 0, Math.max(0, red_alpha)) );
		
		//render Text on frame
			if(currentFrame >= 4) {
				int p = currentFrame - 4;
				int v = p <= 6 ? (42 * p) : 255;
				Color textColor = new Color(v,v,v);
				fr.render(frame, 1, Math.min(p, 5)-1, "WASTeD!", textColor);
			}
		
		return frame;
	}
}
