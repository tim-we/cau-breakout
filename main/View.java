package breakout.main;

import java.awt.Color;

import breakout.animations.Animation;
import breakout.assets.PixelImage;
import breakout.items.*;
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
		
		double widthScale	= (double)ViewWidth / model.getWidth();
		double heightScale	= (double)ViewHeight / model.getHeight();
		
		//draw bricks
			int brickWidth = (int) Math.round( Brick.brickWidth * widthScale );
			int brickHeight = (int) Math.round( Brick.brickHeight * heightScale );
			
			for(Brick brick : model.getBricks()) {
				int[] brickPos = getPixelVector(brick.getPosition(), model);
				
				paintRect(nextFrame, brickPos[0], brickPos[1], brickWidth, brickHeight, brick.getColor());
			}
			
		//draw ball(s)
			for(Ball ball : model.getBalls()) {
				int[] ballPos = getPixelVector(ball.getPosition(), model);
				
				nextFrame.setPixel(ballPos[0], ballPos[1], ball.getColor());
			}
		
		//draw paddle
			int paddleWidth = (int) Math.round(model.getPaddle().getWidth() * widthScale);			
			int[] paddlePos = getPixelVector(model.getPaddle().getPosition(), model);
			
			paintRect(nextFrame, paddlePos[0], paddlePos[1], paddleWidth, 1, model.getPaddle().getColor());
		
		//render animations
			for(Animation anim : model.getAnimations()) {
				nextFrame = anim.renderNextFrame(nextFrame);
			}
			
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
