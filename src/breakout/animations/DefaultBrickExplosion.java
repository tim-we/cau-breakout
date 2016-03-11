package breakout.animations;

import java.awt.Color;
import java.util.Random;

import breakout.assets.BlendingMode;
import breakout.assets.PixelImage;
import breakout.main.Model;
import breakout.physics.Vector2D;

public class DefaultBrickExplosion extends Animation {
	
	private static final int width = 10;
	private static final int height = 4;
	
	/* explosion center */
	private int xpos;
	private int ypos;
	
	private double ratio = (double)height/(double)width;
	
	/* these variables are randomized in the constructor to make every instance unique */
	private int green_max;
	private int blue_max;
	private int green_falloff;
	private int blue_falloff;
	
	/* constructor */
	public DefaultBrickExplosion(double x, double y, Model m) {
		/* convert world coordinates to view coordinates */
		int[] tmp = breakout.main.View.getViewCoordinates(new Vector2D(x,y), m);
		
		xpos = tmp[0];
		ypos = tmp[1];
		
		frames = 12;
		
		Random rgen = new Random();
		green_max = 200 + rgen.nextInt(56);
		blue_max = 180 + rgen.nextInt(70);
		green_falloff = 6 + rgen.nextInt(15);
		blue_falloff = 30 + rgen.nextInt(70);
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

					frame.blendPixel(xpos+col, ypos+row, getColor(s * (1.0 - f)), BlendingMode.NORMAL);
				}
				
			}
		}
		
		return frame;
	}
	
	/* to make the explosion look more interesting the colors shift over time
	 * this function returns the current color based on the current frame and
	 * a given alpha value */
	private Color getColor(double f) {
		f = Math.max(0d, Math.min(f, 0.75));
		
		int alpha = (int)Math.round(255 * f);
		
		return new Color( 
				255,
				Math.max(green_max - (currentFrame*green_falloff), 0),
				Math.max(blue_max - (currentFrame*blue_falloff), 0),
				alpha
			);
	}
}
