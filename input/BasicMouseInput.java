package breakout.input;

import java.awt.MouseInfo;

import breakout.main.*;
import breakout.items.Paddle;

public class BasicMouseInput implements BreakoutInput {
	
	public void init(Model model, View view) {}
	
	public void update(Paddle paddle, int ms) {
		//Absolute Position, but relative Position needed.
		int newPosition;
		newPosition = MouseInfo.getPointerInfo().getLocation().x;
		paddle.setPosition(newPosition);
		
	}
}
