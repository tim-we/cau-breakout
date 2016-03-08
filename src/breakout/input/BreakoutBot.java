package breakout.input;

import breakout.items.Paddle;

public class BreakoutBot implements BreakoutInput {
	
	private breakout.main.Model model;
	
	private double max_speed = 6;
	
	public void init(breakout.main.Model model) {
		this.model = model;
	}
	
	public void update(Paddle paddle, int ms) {
		if(model.getBalls().size() > 0) {
			double xpos = model.getBalls().get(0).getPosition().getX();
			double yvel = model.getBalls().get(0).getVelocity().getY();
			double paddle_pos = paddle.getPosition().getX() + 0.5*paddle.getWidth();
			
			double d = xpos - paddle_pos;

			double max = max_speed;
			
			if(paddle.getReverse()) { 
				d = -d;
				d *= 0.8;
			}
			
			if(yvel < 0) { d = d * 0.02; }
			
			d = Math.max(-max, Math.min(d, max));
			
			paddle.move(d);
		}
	}
	
}
