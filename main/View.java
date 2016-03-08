package breakout.main;

import java.awt.Color;

import breakout.animations.Animation;
import breakout.assets.BreakoutConstants;
import breakout.assets.PixelImage;
import breakout.items.*;
import breakout.physics.Vector2D;

import java.util.Observable;
import java.util.Observer;

public abstract class View extends Observable implements Observer {
	
	private static final int ViewWidth = BreakoutConstants.WINDOW_COLUMNS;
	private static final int ViewHeight = BreakoutConstants.WINDOW_ROWS;
	protected PixelImage frame;
	
	//constructor
	public View() {
		frame = new PixelImage(ViewWidth, ViewHeight);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		assert o instanceof Model;
		
		// Simply print the current score to the console
		System.out.println("Score: " + ((Model) o).getScore());
	}
	
	public void renderFrame(Model model) {
		PixelImage nextFrame = new PixelImage(ViewWidth, ViewHeight);
		
		double widthScale	= (double)ViewWidth / model.getWidth();
		double heightScale	= (double)ViewHeight / model.getHeight();
		
		//draw bricks
			int brickWidth = (int) Math.round( Brick.brickWidth * widthScale );
			int brickHeight = (int) Math.round( Brick.brickHeight * heightScale );
			
			for(Brick brick : model.getBricks()) {
				int[] brickPos = getViewCoordinates(brick.getPosition(), model);
				
				paintRect(nextFrame, brickPos[0], brickPos[1], brickWidth, brickHeight, brick.getColor());
			}
		
		//draw paddle
			int paddleWidth = (int) Math.round(model.getPaddle().getWidth() * widthScale);			
			int[] paddlePos = getViewCoordinates(model.getPaddle().getPosition(), model);
			
			paintRect(nextFrame, paddlePos[0], paddlePos[1], paddleWidth, 1, model.getPaddle().getColor());
		
		//render animations
			for(Animation anim : model.getAnimations()) {
				nextFrame = anim.renderFrame(nextFrame);
			}
		
		//draw ball(s)
			for(Ball ball : model.getBalls()) {
				int[] ballPos = getViewCoordinates(ball.getPosition(), model);
				
				nextFrame.setPixel(ballPos[0], ballPos[1], Ball.color);
			}	
			
		frame = nextFrame;		
	}
	
	public static int[] getViewCoordinates(Vector2D v, Model m) {
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
