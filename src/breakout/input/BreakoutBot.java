package breakout.input;

import breakout.items.Ball;
import breakout.items.Paddle;

public class BreakoutBot implements BreakoutInput {
	
	private breakout.main.Model model;
	
	private double max_speed = 7;
	
	private double last_speed = 0;
	
	public void init(breakout.main.Model model) {
		this.model = model;
	}
	
	public void update(Paddle paddle, int ms) {
		Ball ball = getBallOfInterest();
		
		if(ball != null) {
			double xpos = ball.getPosition().getX();
			double yvel = ball.getVelocity().getY();
			double paddle_pos = paddle.getPosition().getX() + 0.5*paddle.getWidth();
			
			double d = xpos - paddle_pos;

			double max = max_speed;
			
			if(paddle.getReverse()) { 
				d = -d;
				d *= 0.8;
			}
			
			if(yvel < 0) { d = d * 0.02; }
			
			d = Math.max(-max, Math.min(d, max));
			
			d = 0.8 * last_speed + 0.2 * d;
			
			paddle.move(d);
			last_speed = d;
		}
	}
	
	public Ball getBallOfInterest() {
		Ball ball = null;
		if(model.getBalls().size() > 0) {		
			ball = model.getBalls().get(0);
			
			if(ball.getVelocity().getY() < 0d) {
				for(int i=1; i<model.getBalls().size(); i++) {
					if(model.getBalls().get(i).getVelocity().getY() > 0.0) {
						ball = model.getBalls().get(i);
					}
				}
			}
		}
		
		return ball;
	}
	
}
