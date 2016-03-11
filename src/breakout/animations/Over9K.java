package breakout.animations;

import java.awt.Color;

import breakout.assets.PixelImage;
import breakout.font.FontRenderer;
import breakout.font.HighriserFont;
import breakout.main.Config;

public class Over9K extends Animation {
	
	private FontRenderer fr;
	
	private PixelImage background;
	
	private int textLength;
	
	private Color textColor = new Color(255,255,255);
	
	private String displayText = "OVER 9K!";
	
	/* constructor */
	public Over9K() {
		width = Config.WINDOW_COLUMNS;
		height = Config.WINDOW_ROWS;
		
		frames = 90;
		
		background = new PixelImage(width, height);
		background.fill(Color.BLACK);
		
		fr = new FontRenderer(new HighriserFont(), 2);
		fr.setCharOffset(1);
		
		/* text length in pixels */
		textLength = fr.getTextWidth("displayText");
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {		
		frame = new PixelImage(background);
		
		/* red fill */
			int red_alpha = (int)(Math.sin(currentFrame/3d) * 100);
			frame.fill( new Color(42, 255, 0, Math.max(0, Math.min(red_alpha, 255))));
		
		/* render Text on frame */
			if(currentFrame >= 2) {
				int pos = 5 - currentFrame/2;
				pos = Math.max(pos, 20 - textLength);
				fr.render(frame, pos, 2, displayText, textColor);
			}
		
		return frame;
	}
}
