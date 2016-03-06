package breakout.input;

import java.awt.MouseInfo;

import breakout.main.*;
import breakout.items.Paddle;

public class BasicMouseInput implements BreakoutInput {
	
	private int lastMouseXPos = 0;
	
	private double mouseSpeed = 0.8;
	
	public void update(Paddle paddle, int ms) {

		int newPosition = MouseInfo.getPointerInfo().getLocation().x;
		
		double d = mouseSpeed * (newPosition-lastMouseXPos);
		
		paddle.move (d);
		lastMouseXPos = newPosition;
		
	}
	
}
