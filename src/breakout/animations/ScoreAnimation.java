package breakout.animations;

import java.awt.Color;

import breakout.main.Config;
import breakout.assets.PixelImage;

import breakout.font.HighriserFont;
import breakout.font.FontRenderer;

public class ScoreAnimation extends Animation {
	
	private FontRenderer fr;
	
	private PixelImage background;
	
	private int score;
	
	private static final Color titleColor = new Color(255,255,255);
	private static final Color[] digitColors = {new Color(255,0,80), new Color(90,255,0), new Color(0,90,255)};
	
	/* constructor */
	public ScoreAnimation(int score) {
		width = Config.WINDOW_COLUMNS;
		height = Config.WINDOW_ROWS;
		
		frames = score>0 ? 15 : 1;
		
		fr = new FontRenderer(new HighriserFont());
		fr.setCharOffset(1);
		
		background = new PixelImage(width, height);
		background.fill(new Color(0,0,0));
		
		this.score = score;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		frame = new PixelImage(background);
		
		/* counting animation */
		int displayScore = Math.max(0, score-frames+currentFrame);
		
		/* align right */
		int scoreTextLength = fr.getTextWidth(displayScore+"");
		int xPos = Config.WINDOW_COLUMNS - 2 - scoreTextLength;
		
		fr.render(frame, 2, 1, "SC0rE", titleColor);
		fr.render(frame, xPos, 7, displayScore+"", digitColors );
		
		return frame;
	}
}