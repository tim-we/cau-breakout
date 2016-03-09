package breakout.main;

import breakout.input.*;

public class Breakout {
	
	public static void main(String[] args) {
		
		//set up (user-)input 
			System.out.println("Input set to: "+ breakout.assets.BreakoutConstants.INPUT_SOURCE);
			
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
