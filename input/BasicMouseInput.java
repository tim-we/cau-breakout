package breakout.input;

import java.awt.MouseInfo;

import breakout.main.*;
import breakout.items.Paddle;

public class BasicMouseInput implements BreakoutInput {
	
	public void init(Model model, View view) {}
	
	private int lastMouseXPos = 0;
	
	public void update(Paddle paddle, int ms) {

		int newPosition = MouseInfo.getPointerInfo().getLocation().x;
		
		int d = newPosition-lastMouseXPos;
		
		paddle.setPosition( paddle.getPosition().getX() + d );
		
		lastMouseXPos = newPosition;
	}
}
