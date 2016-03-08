package breakout.animations;

import java.awt.Color;

import breakout.assets.BreakoutConstants;
import breakout.assets.PixelImage;
import breakout.font.FontRenderer;
import breakout.font.HighriserFont;

public class WastedAnimation extends Animation {
private FontRenderer fr;
	
	private PixelImage background;
	
	private int textLength;
	
	private Color textColor = new Color(255,255,255);
	
	public WastedAnimation() {
		width = BreakoutConstants.WINDOW_COLUMNS;
		height = BreakoutConstants.WINDOW_ROWS;
		
		frames = 90;
		
		background = new PixelImage(width, height);
		background.fill(Color.BLACK);
		
		fr = new FontRenderer(new HighriserFont(), 2);
		fr.setCharOffset(1);
		
		textLength = fr.getTextWidth("WASTeD");
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {		
		frame = new PixelImage(background);
		
		//red fill
			int red_alpha = (int)(Math.sin(currentFrame/3d) * 100);
			frame.fill( new Color(255, 0, 0, Math.max(0, Math.min(red_alpha, 255))));
		
		//render Text on frame
			if(currentFrame >= 2) {
				int pos = 5 - currentFrame/2;
				pos = Math.max(pos, 23 - textLength);
				fr.render(frame, pos, 2, "WASTeD!", textColor);
			}
		
		return frame;
	}
}
