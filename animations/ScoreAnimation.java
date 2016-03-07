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
		
		background = new PixelImage(width, height);
		background.fill(new Color(0,0,0));
		
		this.score = score;
	}
	
	@Override
	public PixelImage renderNextFrame(PixelImage frame) {
		frame = new PixelImage(background);
		
		int displayScore = Math.max(0, score-frames+currentFrame);
		
		fr.render(frame, 3, 2, "SC0rE", textColor);
		fr.render(frame, 3, 8, displayScore+"", textColor );
		
		currentFrame++;
		
		if(currentFrame > frames) {
			finished = true;
			currentFrame = 0;
		}
		
		return frame;
	}
}