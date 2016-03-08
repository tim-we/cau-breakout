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
	
	private int scoreTextLength;
	
	private static final Color titleColor = new Color(255,255,255);
	private static final Color[] digitColors = {new Color(255,0,80), new Color(90,255,0), new Color(0,90,255)};
	
	public ScoreAnimation(int score) {
		width = BreakoutConstants.WINDOW_COLUMNS;
		height = BreakoutConstants.WINDOW_ROWS;
		
		frames = 15;
		
		fr = new FontRenderer(new HighriserFont());
		fr.setCharOffset(1);
		
		scoreTextLength = fr.getTextWidth(score+"");
		
		background = new PixelImage(width, height);
		background.fill(new Color(0,0,0));
		
		this.score = score;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		frame = new PixelImage(background);
		
		int displayScore = Math.max(0, score-frames+currentFrame);
		//align right
		int xPos = BreakoutConstants.WINDOW_COLUMNS - 2 - scoreTextLength;
		
		fr.render(frame, 2, 2, "SC0rE", titleColor);
		fr.render(frame, xPos, 8, displayScore+"", digitColors );
		
		return frame;
	}
}