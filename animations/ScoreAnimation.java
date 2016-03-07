package breakout.animations;

import java.awt.Color;

import breakout.assets.BreakoutConstants;
import breakout.assets.PixelImage;

import breakout.font.HighriserFont;
import breakout.font.FontRenderer;

public class ScoreAnimation extends Animation {
	
	private FontRenderer fr;
	
	private PixelImage background;
	
	private int score;
	
	private static final Color textColor = new Color(255,255,255);
	
	public ScoreAnimation(int score) {
		width = BreakoutConstants.WINDOW_COLUMNS;
		height = BreakoutConstants.WINDOW_ROWS;
		
		frames = 15;
		
		fr = new FontRenderer(new HighriserFont());
		fr.setCharOffset(1);
		
		background = new PixelImage(width, height);
		background.fill(new Color(0,0,0));
		
		this.score = score;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		frame = new PixelImage(background);
		
		int displayScore = Math.max(0, score-frames+currentFrame);
		
		fr.render(frame, 2, 2, "SC0rE", textColor);
		fr.render(frame, 5, 8, displayScore+"", textColor );
		
		return frame;
	}
}