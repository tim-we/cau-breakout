package breakout.animations;

import java.awt.Color;

import breakout.assets.PixelImage;
import breakout.font.FontRenderer;
import breakout.font.HighriserFont;

public class IntroAnimation extends Animation {
	
	private FontRenderer fr;
	
	private int titleLength;
	
	private static final String title = "BREAK0UT!";
	
	private static final Color[] titleColors = {
			new Color(100,0,255),
			new Color(255,42,0),
			new Color(182,255,0),
			new Color(0,255,100),
			new Color(0,150,255)
	};
	
	public IntroAnimation() {
		
		fr = new FontRenderer(new HighriserFont(), 2);
		fr.setCharOffset(1);
		
		titleLength = fr.getTextWidth(title);
		
		frames = (titleLength-20) * 3;	
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		
		frame = new PixelImage(frame);
		
		fr.render(frame, getXPos(), 2, title, titleColors);
		
		return frame;
	}
	
	private int getXPos() {
		int xpos = 5 - currentFrame/3;
		
		xpos = Math.max(20-titleLength , xpos);
		
		return xpos;
	}
}
