package breakout.main;

import breakout.assets.BreakoutConstants;
import breakout.input.*;

public class Breakout {
	
	public static void main(String[] args) {
		BreakoutInput userinput = BreakoutConstants.ENABLE_BOT ? new BreakoutBot() :new BasicMouseInput();
		
		Controller c = new Controller(userinput);	

		System.out.println("Game starting...");
		
		c.runController();
	}
	
	public static void pause(int ms) {
		try {
		    Thread.sleep(ms);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		    // -> do something ?
		}
	}
}
