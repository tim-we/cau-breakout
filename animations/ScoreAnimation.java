package breakout.animations;

import java.awt.Color;

import breakout.assets.BreakoutConstants;
import breakout.assets.PixelImage;
import breakout.font.FontRenderer;
import breakout.font.HighriserFont;

public class ScoreAnimation extends Animation {
	
	private FontRenderer fr;
	
	private int score;
	
	private PixelImage background;
	
	private static final Color textColor = new Color(255,255,255);
	
	public ScoreAnimation(int score) {
		width = BreakoutConstants.WINDOW_COLUMNS;
		height = BreakoutConstants.WINDOW_ROWS;
		
		fr = new FontRenderer(new HighriserFont());
		
		frames = 42;
		
		this.score = score;
		
		background = new PixelImage(BreakoutConstants.WINDOW_COLUMNS, BreakoutConstants.WINDOW_ROWS);
		background.fill(new Color(0,0,0));
	}
	
	@Override
	public PixelImage renderNextFrame(PixelImage frame) {
		frame = background;
		
		fr.render(frame, 2, 2, "score", textColor);
		fr.render(frame, 2, 8, ""+score, textColor);
		
		currentFrame++;
		
		if(currentFrame > frames) {
			finished = true;
			currentFrame = 0;
		}
		
		return frame;
	}
	
}
