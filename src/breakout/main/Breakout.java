package breakout.main;

import breakout.assets.BreakoutConstants;
import breakout.input.*;

public class Breakout {
	
	public static void main(String[] args) {
		
		System.out.println("Actual user input set on: "+ breakout.assets.BreakoutConstants.INPUT_SOURCE + 
				"default is Keyboard.");
		BreakoutInput userinput;
		if (breakout.assets.BreakoutConstants.INPUT_SOURCE.equals("Bot")){
			userinput = new BreakoutBot();
		}
		else if (breakout.assets.BreakoutConstants.INPUT_SOURCE.equals("Mouse")){
			userinput = new BasicMouseInput();
		}
		else {
			userinput = new KeyboardInput();
		}
		
				
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
