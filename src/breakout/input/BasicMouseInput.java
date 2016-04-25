package breakout.input;

import java.awt.MouseInfo;

import breakout.items.Paddle;
import breakout.main.Model;

public class BasicMouseInput implements BreakoutInput {
	
	private int lastMouseXPos = 0;
	
	private double mouseSpeed = 0.8;
	
	private boolean userInput = false;
	
	@Override
	public void update(Paddle paddle, int ms) {
		/* Saves the new Mouse Position and moves the Paddle 
		 * with the difference to the last Mouse Position */
		int newPosition = MouseInfo.getPointerInfo().getLocation().x;
		
		int delta_px = newPosition-lastMouseXPos;
		double d = mouseSpeed * delta_px;
		
		paddle.move (d);
		lastMouseXPos = newPosition;
		
		userInput = delta_px != 0;
	}

	public void init(Model m) {
		lastMouseXPos = MouseInfo.getPointerInfo().getLocation().x;
	}
	
	@Override
	public boolean activeInput() {
		return userInput;
	}
}
