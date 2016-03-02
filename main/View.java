package breakout.main;

import java.awt.Color;

import breakout.assets.PixelImage;
import breakout.physics.Vector2D;

public class View {
	
	private int ViewWidth;
	private int ViewHeight;
	private PixelImage frame;
	
	//constructor
	public View(int width, int height) {
		frame = new PixelImage(width, height);
		ViewWidth = width;
		ViewHeight = height;
	}
	
	public void update(Model model) {
		int paddleWidth = (int) Math.round(model.getPaddle().getWidth());
		int[] paddlePos = getPixelVector(model.getPaddle().getPosition());
		paintRect(paddlePos[0], paddlePos[1], paddleWidth, 1, model.getPaddle().getColor());
	}
	
	private int[] getPixelVector(Vector2D v) {
		int[] o = new int[2];
		
		o[0] = (int) Math.round(v.getX());
		o[1] = (int) Math.round(v.getY());
		
		return o;
	}
	
	private void paintRect(int x, int y, int width, int height, Color color) {
		assert (width>=0 && height>=0);
		
		for(int i=0; i<width; i++) {
			for(int k=0; k<height; k++) {
				frame.setPixel(x + i, y + k, color);
			}
		}
		
	}
}
