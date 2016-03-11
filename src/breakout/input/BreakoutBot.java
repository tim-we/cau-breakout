package breakout.input;

import java.util.Random;

import breakout.items.Ball;
import breakout.items.Paddle;

public class BreakoutBot implements BreakoutInput {
	
	private breakout.main.Model model;
	
	private double max_speed = 6d;
	
	private double last_speed = 0;
	
	public void init(breakout.main.Model model) {
		this.model = model;
		
		/* random start position */
		Random rgen = new Random();
		
		model.getPaddle().move(-model.getWidth());
		model.getPaddle().move(rgen.nextDouble() * model.getWidth());
	}
	
	/**
	 * this method gets called in the game's main loop and should
	 * update the Paddle's x coordinate according to the Bot
	 * @param paddle
	 * @param ms - time in milliseconds since last frame (for more advanced implementations)
	 * 	 */
	public void update(Paddle paddle, int ms) {
		Ball ball = getBallOfInterest(paddle);
		
		if(ball != null) {
			double xpos = ball.getPosition().getX();
			double yvel = ball.getVelocity().getY();
			double paddle_pos = paddle.getPosition().getX() + 0.5*paddle.getWidth();
			
			double d = xpos - paddle_pos;

			double max = max_speed;
			/* If the ball is fast enough the Bot can move faster */
			if(ball.getVelocity().sqlength() > 20000d) { max += 3d; }
			
			/* If reverse-mode is activated the bot can't play as fast as normal */
			if(paddle.isReversed()) { 
				d = -d;
				d *= 0.9;
			}
			
			/* If the Ball moves up the Bot shall not follow the BallPosition as fast as normal */
			if(yvel < 0) { d = d * 0.02; }
			
			d = Math.max(-max, Math.min(d, max));
			
			/* The bot needs to accelerate the paddle first */
			d = 0.75 * last_speed + 0.25 * d;
			
			paddle.move(d * ms/40d);
			last_speed = d;
		}
	}
	
	/**
	 * If there is a Ball the bot will focus to follow this one, 
	 * if this ball is moving up he will focus another ball moving down at the moment
	 * @return the ball of Interest
	 */
	public Ball getBallOfInterest(Paddle paddle) {
		Ball ball = null;
		
		double min_dist2 = 900000000;
		double d2, dx, dy;
		
		for(int i=0; i<model.getBalls().size(); i++) {
			if(model.getBalls().get(i).getVelocity().getY() > 0.0) {
				
				dx = paddle.getPosition().getX() + 0.5*paddle.getWidth() - model.getBalls().get(i).getPosition().getX();
				dy = paddle.getPosition().getY() - model.getBalls().get(i).getPosition().getY();
				
				d2 = dx * dx + dy * dy;
				
				if(d2 < min_dist2) {
					ball = model.getBalls().get(i);
					min_dist2 = d2;
				}
			}
		}
		
		if(ball == null && model.getBalls().size() > 0) {
			ball = model.getBalls().get(0);
		}
		
		return ball;
	}
	
}
