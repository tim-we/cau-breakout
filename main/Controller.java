package breakout.main;

import java.util.Observable;
import java.util.Observer;

import breakout.items.*;
import breakout.physics.*;

public class Controller implements Observer {
	
	public static final double WORLDWIDTH = 600;
	public static final double WORLDHEIGHT = 800;
	
	public static final int FPS = 30;
	
	public static final int MAX_PHYS_ITERATIONS = 5; //per frame
	
	public Controller() {
	}
	
	public void runController() {
		
		Model model = new Model(WORLDWIDTH, WORLDHEIGHT);
		
		PhysicsContext phys = new PhysicsContext(WORLDWIDTH, WORLDHEIGHT);
		
		boolean runLoop = true;
		int k;
		double time_remaining;
		
		while(runLoop) {
			
			for(Ball ball : model.getBalls()) {
				time_remaining = (int)(1000d/FPS)/1000d;
				k = 0;
				
				 //loop to prevent double bounce errors
				while(time_remaining > 0 && k<MAX_PHYS_ITERATIONS) {
					time_remaining = phys.updateObject(ball, time_remaining) * time_remaining;
					k++;
				}
			}
			
			//model updates views
			model.update();	
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println((String) arg);
	}
	
}
