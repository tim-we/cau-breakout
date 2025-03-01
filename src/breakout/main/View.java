package breakout.main;

import java.awt.Color;

import breakout.animations.Animation;
import breakout.assets.BlendingMode;
import breakout.assets.PixelImage;
import breakout.items.*;
import breakout.physics.Vector2D;

import java.util.Observable;
import java.util.Observer;

public abstract class View extends Observable implements Observer {
	
	private static final int ViewWidth = Config.WINDOW_COLUMNS;
	private static final int ViewHeight = Config.WINDOW_ROWS;
	protected PixelImage frame;
	
	/*
	 * these variables will not be changed during runtime
	 * and are same for every instance, thus the 
	 * 'private static final' access modifier
	 * */
	private static final Color[] brickColors = {
			new Color(255,0,80),
			new Color(90,255,0),
			new Color(0,90,255),
			new Color(255,0,255),
			new Color(0,255,255),
			new Color(255,255,0), 
			new Color(255,137,2),
			new Color(63,255,146),
			new Color(0,225,255),
			new Color(100,100,100)
		};
	
	private static final Color[] paddleColors = {
			new Color(229,55,203),
			new Color(255,182,0)
		};
	
	/* used for the color shift of the flashing brick */
	private float brickColorHue = 0f;
	
	/* public BallColor accessed by SpawnBall animation */
	public static final Color ballColor = new Color(255,255,255);
	
	/**
	 * Constructor
	 */
	public View() {
		frame = new PixelImage(ViewWidth, ViewHeight);
	}
	
	/**
	 * Takes a Model and draws it as a PixelImage
	 * @param model - the Model to draw
	 */
	public void renderFrame(Model model) {
		PixelImage nextFrame = new PixelImage(ViewWidth, ViewHeight);
		
		double widthScale	= (double)ViewWidth / model.getWidth();
		double heightScale	= (double)ViewHeight / model.getHeight();
		
		//draw bricks
			int brickWidth = (int) Math.round( Brick.brickWidth * widthScale );
			int brickHeight = (int) Math.round( Brick.brickHeight * heightScale );
			
			for(Brick brick : model.getBricks()) {
				int[] brickPos = getViewCoordinates(brick.getPosition(), model);
				
				paintRect(nextFrame, brickPos[0], brickPos[1], brickWidth, brickHeight, getBrickColor(brick.getBrickType()));
			}
		
		//draw paddle
			if(!model.hasEnded()) {
				int paddleWidth = (int) Math.round(model.getPaddle().getWidth() * widthScale);			
				int[] paddlePos = getViewCoordinates(model.getPaddle().getPosition(), model);
				
				paintRect(nextFrame, paddlePos[0], paddlePos[1], paddleWidth, 1, getPaddleColor(model.getPaddle()));
			}
		//render animations
			for(Animation anim : model.getAnimations()) {
				nextFrame = anim.renderFrame(nextFrame);
			}
		
		//draw ball(s)
			for(Ball ball : model.getBalls()) {
				int[] ballPos = getViewCoordinates(ball.getPosition(), model);
				
				boolean speedy = ball.getVelocity().sqlength() > 20000d;
				
				//draw tail
				for(int i=0; i<ball.getTail().length; i++) {
					if(ball.getTail()[i] == null) { break; }
					
					int[] tpos = getViewCoordinates(ball.getTail()[i], model);
					
					Color c = speedy ? new Color(255,42,0,220/(i+1)) : new Color(42,255,0,160/(i+1));
					
					nextFrame.blendPixel(tpos[0], tpos[1], c, BlendingMode.ADDITIVE);
				}
				
				//draw ball
				nextFrame.setPixel(ballPos[0], ballPos[1], ballColor);
			}	
			
		frame = nextFrame;	
		
		brickColorHue += 0.05F;
		if(brickColorHue >= 2f*Math.PI) { brickColorHue = 0f; }
	}
	
	/**
	  * converts model coordinates to view coordinates
	  * @param v - the vector to convert
	  * @param m - the Model
	  * @return the converted Vector's components as an int Array
	  */
	public static int[] getViewCoordinates(Vector2D v, Model m) {
		int[] o = new int[2];
		
		o[0] = (int) Math.round((v.getX()/m.getWidth()) * ViewWidth);
		o[1] = (int) Math.round((v.getY()/m.getHeight()) * ViewHeight);
		
		return o;
	}
	
	/**
	 * Draws a rectangle on a given PixelImage
	 * @param pic - the PixelImage to draw on
	 * @param x - the X-Position of the top-left corner
	 * @param y - the Y-Position of the top-left corner
	 * @param width - the width of the rectangle
	 * @param height - the height of the rectangle
	 * @param color - the color of the rectangle
	 */
	private void paintRect(PixelImage pic, int x, int y, int width, int height, Color color) {
		assert (width>=0 && height>=0);
		
		for(int i=0; i<width; i++) {
			for(int k=0; k<height; k++) {
				pic.setPixel(x + i, y + k, color);
			}
		}		
	}
	
	/**
	 * Returns the Color of the Brick, in case of RandomBrick it changes the color.
	 * @return the Color of the Brick at the moment
	 */
	public Color getBrickColor(byte brickType) {
		if(brickType == 9){
			return Color.getHSBColor(brickColorHue, 1f, 1f);
		}
		else if(brickType<0) {
			return new Color(0,0,0,0);
		}
		
		return brickColors[brickType % brickColors.length];
	}
	
	/**
	 * Returns the Color of the Paddle
	 * @param p - the Paddle
	 * @return - the Color of the Paddle, in Case of ReverseMode its a different one
	 */
	public Color getPaddleColor(Paddle p) {
	  return p.isReversed() ? paddleColors[1] : paddleColors[0];
	}
}
