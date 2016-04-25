package breakout.animations;

import java.awt.Color;
import java.util.Random;

import breakout.assets.BlendingMode;
import breakout.assets.PixelImage;
import breakout.main.Config;

public class FireworksParticle extends Animation {
	
	private static final int width = 10;
	private static final int height = 4;
	
	private static final double ratio = (double)height/(double)width;
	
	private Color color;
	
	/* explosion center */
	private int xpos;
	private int ypos;
	
	public FireworksParticle() {
		Random rgen = new Random();
		
		color = Color.getHSBColor((float) (2 * Math.PI * rgen.nextDouble()), 1f, 1f);
		
		xpos = 2 + (int) Math.round((Config.WINDOW_COLUMNS-4) * rgen.nextDouble());
		ypos = 2 + (int) Math.round((Config.WINDOW_ROWS-4) * rgen.nextDouble());
		
		frames = 14;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		double radius2 = 0.14*(double)Math.min(1+currentFrame*currentFrame, 42d);		
		
		double s = 1.0;
		if(currentFrame>=6) { s = 1.0 - 0.15*(currentFrame-6); }
		
		double d2,f;
		
		for(int row= -(height/2); row<height; row++) {
			for(int col= -(width/2); col<width; col++) {
				
				d2 = row*row + col*col*ratio;
				
				if(d2 < radius2) {
					f = (radius2 - d2)/radius2; //relative diff.
					
					frame.blendPixel(xpos+col, ypos+row, getColor(s * (1.0 - f)), BlendingMode.ADDITIVE);
				}
				
			}
		}
		
		return frame;
	}
	
	private Color getColor(double f) {
		f = Math.max(0d, Math.min(f, 0.8d)) * 0.6d;
		
		int alpha = (int)Math.round(255 * f);
		
		return new Color( 
				color.getRed(),
				color.getGreen(),
				color.getBlue(),
				alpha
			);
	}
	
}
