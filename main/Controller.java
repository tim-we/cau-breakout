package breakout.main;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
	
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void runController() {
		
		boolean runLoop = true;
		
		while(runLoop) {
			
			
			
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println((String) arg);
	}
	
}
