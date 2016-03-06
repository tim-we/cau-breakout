package breakout.input;

import breakout.items.Paddle;
import breakout.main.*;

public interface BreakoutInput {
	
	/**
	 * this method gets called in the game's main loop and should
	 * update the Paddle's x coordinate according to user input
	 * @param paddle
	 * @param ms - time in milliseconds since last frame (for more advanced implementations)
	 * @param reverse Reverses the Paddle control
	 * 	 */
	public void update(Paddle paddle, int ms, boolean reverse);
	
	public void init(Model model, View view);
}
