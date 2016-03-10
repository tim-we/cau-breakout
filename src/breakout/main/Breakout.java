package breakout.main;

import breakout.input.*;

public class Breakout {
	
	public static void main(String[] args) {
		
		Controller c = new Controller();
		
		//set up (user-)input
			String input = breakout.assets.BreakoutConstants.INPUT_SOURCE;
			System.out.println("Input set to: " + input);
			
			if(input.indexOf("Mouse") >= 0)		{ c.addInputHandler(new BasicMouseInput()); }
			if(input.indexOf("Keyboard") >= 0)	{ c.addInputHandler(new KeyboardInput()); }
			if(input.indexOf("Bot") >= 0)		{ c.addInputHandler(new BreakoutBot()); }
		
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
