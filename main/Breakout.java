package breakout.main;

import breakout.input.*;

public class Breakout {
	
	public static void main(String[] args) {
		BreakoutInput userinput = new BasicMouseInput();
		
		Controller c = new Controller(userinput);
		
		while(true) {
			c.runController();
		}	
	}
}
