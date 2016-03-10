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
		
		if (lhs.isLeftArrowKeyPressed() == true){
			paddle.move(-maxSpeed);
			speed -= maxSpeed;
		}
		
		if (lhs.isRightArrowKeyPressed() == true){
			paddle.move(maxSpeed);
			speed += maxSpeed;
		}
		
		speed = Math.max(-maxSpeed , Math.min(speed, maxSpeed));
		speed = 0.4*speed+0.6*lastspeed;
		
		paddle.move(speed);
		
		lastspeed = speed;
	}

}
