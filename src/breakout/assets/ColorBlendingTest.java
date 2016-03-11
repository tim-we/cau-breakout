package breakout.assets;

import java.awt.Color;

import acm.program.*;

public class ColorBlendingTest extends ConsoleProgram {
	/* A test for our Color blending */
	public void run() {
		Color a = new Color(0,0,255,128);
		Color b = new Color(255,0,0,128);
		
		Color c = PixelImage.blendColors(b, a, BlendingMode.NORMAL);
		
		println(c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ", " + c.getAlpha());
	}
	
}
