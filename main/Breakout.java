package breakout.main;

import breakout.input.*;
import breakout.lighthouse.LhSimulator;

public class Breakout {
	
	public static void main(String[] args) {
		BreakoutInput userinput = new BasicMouseInput();
		
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
