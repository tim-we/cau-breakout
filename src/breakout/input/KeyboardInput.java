package breakout.input;

import breakout.lighthouse.LhSimulator;
import breakout.main.Model;
import breakout.items.Paddle;

public class KeyboardInput implements BreakoutInput{

	private LhSimulator lhs; 
	private static final double maxSpeed = 5.5;
	private double lastspeed = 0;
	
	public void init(Model m) {}
	
	public void setLHS(LhSimulator lhs) {
		this.lhs = lhs;
	}
	
	@Override
	public void update(Paddle paddle, int ms) {
		if (lhs==null) { return; }
		
		double speed = 0;
		
		/* Moves the Paddle left or right as long as the Key is pressed */
		if (lhs.isLeftArrowKeyPressed() == true){
			paddle.move(-maxSpeed);
			speed -= maxSpeed;
		}
		
		if (lhs.isRightArrowKeyPressed() == true){
			paddle.move(maxSpeed);
			speed += maxSpeed;
		}
		
		/* Moves the paddle depending on the paddle's speed after the user released the key already */
		speed = Math.max(-maxSpeed , Math.min(speed, maxSpeed));
		speed = 0.4*speed+0.6*lastspeed;
		
		paddle.move(speed * ms/40d);
		
		lastspeed = speed;
	}

}
