package breakout.input;

import breakout.items.Paddle;

public interface BreakoutInput {
	
	/**
	 * this method gets called in the game's main loop and should
	 * update the Paddle's x coordinate according to user input
	 * @param paddle
	 * @param ms - time in milliseconds since last frame (for more advanced implementations)
	 */
	public void update(Paddle paddle, int ms);
	
}
