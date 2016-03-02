package breakout.items;

import breakout.assets.*;
import breakout.physics.*;
import java.awt.Color;

public class Paddle extends PhysicsObject{

	private double paddleWidth = 84d;
	
	private Color color = new Color (142,33,125);
	
	public Paddle(){
		this.Position = new Vector2D(
			((BreakoutConstants.WINDOW_COLUMNS/2d)-2) * BreakoutConstants.WINDOW_WIDTH,
			(BreakoutConstants.WINDOW_ROWS-1) * BreakoutConstants.WINDOW_HEIGHT
		);
		
		this.setBBox(paddleWidth, BreakoutConstants.WINDOW_HEIGHT);
	}
	
	public void setPosition (double x){
		//TODO: check if x>=0 && x<=worldWidth-paddleWidth
		Position.setX(x);
	}

	public double getWidth(){
	  return this.paddleWidth;
	}

	public Color getColor(){
	  return this.color;
	}

}
