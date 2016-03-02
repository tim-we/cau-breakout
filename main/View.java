package breakout.main;

import java.awt.Color;

import breakout.assets.PixelImage;
import breakout.lighthouse.LhSimulator;
import breakout.physics.Vector2D;

public class View {
	
	private int ViewWidth;
	private int ViewHeight;
	private PixelImage frame;
	private LhSimulator display;
	
	//constructor
	public View(int width, int height, LhSimulator lhs) {
		frame = new PixelImage(width, height);
		ViewWidth = width;
		ViewHeight = height;
		display = lhs;
	}
	
	public void update(Model model) {
		PixelImage nextFrame = new PixelImage(ViewWidth, ViewHeight);
		
		//draw paddle
			int paddleWidth = (int) Math.round((model.getPaddle().getWidth()/model.getWidth()) * ViewWidth);
			
			int[] paddlePos = getPixelVector(model.getPaddle().getPosition(), model);
			
			System.out.println(paddlePos[0] + ", " + paddlePos[1]);
			
			paintRect(nextFrame, paddlePos[0], paddlePos[1], paddleWidth, 1, model.getPaddle().getColor());
		
		frame = nextFrame;
		
		display.draw(frame);
	}
	
	private int[] getPixelVector(Vector2D v, Model m) {
		int[] o = new int[2];
		
		o[0] = (int) Math.round((v.getX()/m.getWidth()) * ViewWidth);
		o[1] = (int) Math.round((v.getY()/m.getHeight()) * ViewHeight);
		
		return o;
	}
	
	private void paintRect(PixelImage pic, int x, int y, int width, int height, Color color) {
		assert (width>=0 && height>=0);
		
		for(int i=0; i<width; i++) {
			for(int k=0; k<height; k++) {
				pic.setPixel(x + i, y + k, color);
			}
		}
		
	}
}
