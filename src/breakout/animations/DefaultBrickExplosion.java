package breakout.animations;

import java.awt.Color;

import breakout.assets.PixelImage;
import breakout.main.Model;
import breakout.physics.Vector2D;

public class DefaultBrickExplosion extends Animation {
	
	private static final int width = 10;
	private static final int height = 4;
	
	private int xpos;
	private int ypos;
	
	private double ratio = (double)height/(double)width;
	
	public DefaultBrickExplosion(double x, double y, Model m) {
		int[] tmp = breakout.main.View.getViewCoordinates(new Vector2D(x,y), m);
		
		xpos = tmp[0];
		ypos = tmp[1];
		
		frames = 12;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		double radius2 = 0.15*(double)Math.min(1+currentFrame*currentFrame, 42d);		
		
		double s = 1.0;
		if(currentFrame>=6) { s = 1.0 - 0.15*(currentFrame-6); }
		
		double d2,f;
		
		for(int row= -(height/2); row<height; row++) {
			for(int col= -(width/2); col<width; col++) {
				
				d2 = row*row + col*col*ratio;
				
				if(d2 < radius2) {
					f = (radius2 - d2)/radius2; //relative diff.

					Color c = frame.getPixel(xpos+col,  ypos+row);			
					c = PixelImage.blendColors(c, getColor(s * (1.0 - f)));
					
					frame.setPixel(xpos+col, ypos+row, c);
				}
				
			}
		}
		
		return frame;
	}
	
	private Color getColor(double f) {
		f = Math.max(0d, Math.min(f, 0.7));
		
		int alpha = (int)Math.round(255 * f);
		return new Color(255,255 - (currentFrame*15),0,alpha);
	}
}
