package breakout.input;

import breakout.items.Paddle;
import breakout.main.Model;

public interface BreakoutInput {
	
	public void init(Model m);
	
	/**
	 * this method gets called in the game's main loop and should
	 * update the Paddle's x coordinate according to user input
	 * @param paddle
	 * @param ms - time in milliseconds since last frame (for more advanced implementations)
	 * @param reverse Reverses the Paddle control
	 * 	 */
	public void update(Paddle paddle, int ms);
	
	/*
	 * was there actual input last frame?
	 * */
	public default boolean activeInput() {
		return false;
	}
}
