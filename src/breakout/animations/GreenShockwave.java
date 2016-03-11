package breakout.animations;

import java.awt.Color;

import breakout.main.Config;
import breakout.assets.PixelImage;
import breakout.main.Model;
import breakout.physics.Vector2D;

public class GreenShockwave extends Animation {
	private static final int width = Config.WINDOW_COLUMNS;
	private static final int height = Config.WINDOW_ROWS;
	
	/* shockwave center */
	private int xpos;
	private int ypos;
	
	/* constructor */
	public GreenShockwave(Vector2D pos, Model m) {
		/* convert world coords to view coords */
		int[] tmp = breakout.main.View.getViewCoordinates(pos, m);
		
		xpos = tmp[0];
		ypos = tmp[1];
		
		frames = 20;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		double radius2 = 0.8*(double)Math.min(1+currentFrame*currentFrame, 10000);
		double ratio = (double)Config.WINDOW_HEIGHT/(double)Config.WINDOW_WIDTH;
		
		double s = 1.0;
		if(currentFrame>=10) { s = 1.0 - 0.1*(currentFrame-10); }
		
		double d,f;
		
		for(int row= 0; row<height; row++) {
			for(int col= 0; col<width; col++) {
				
				int x = col-xpos;
				int y = row-ypos;
				
				d = (x*x)*0.5d + y*y*ratio;
				
				if(d < radius2) {
					f = (radius2 - d)/radius2; //relative diff.
					
					Color c = frame.getPixel(col,  row);
					
					c = PixelImage.blendColors(c, getColor(s * (1.0 - f)));
					
					frame.setPixel(col, row, c);
				}
				
			}
		}
		
		return frame;
	}
	
	/* create color based on {currentFrame} and alpha factor */
	private Color getColor(double f) {
		f = Math.max(0.0, Math.min(f, 0.5));
		
		int alpha = (int)Math.round(255 * f);
		return new Color(Math.max(0, Math.min(currentFrame*12, 255)), 255, 5, alpha);
	}
}

