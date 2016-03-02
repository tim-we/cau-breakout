package breakout.main;

import java.util.Observable;
import java.util.Observer;

import breakout.items.*;
import breakout.lighthouse.LhSimulator;
import breakout.physics.*;
import breakout.input.BreakoutInput;

public class Controller implements Observer {
	
	public static final double WORLDWIDTH = 588;
	public static final double WORLDHEIGHT = 800;
	
	public static final int FPS = 30;
	
	public static final int MAX_PHYS_ITERATIONS = 5; //per frame
	
	private BreakoutInput InputHandler;
	
	public Controller(BreakoutInput input) {
		InputHandler = input;
	}
	
	public void runController() {
		
		Model model = new Model(WORLDWIDTH, WORLDHEIGHT);	
		
		LhSimulator lhs = new LhSimulator();
		View view = new View(28, 14, lhs);
		
		model.addView(view);
		
		PhysicsContext phys = new PhysicsContext(WORLDWIDTH, WORLDHEIGHT);
		
		phys.staticObjects.add(model.getPaddle());
		
		//TODO: add bricks as staticObjects
		
		boolean runLoop = true;
		int k;
		int pause_time = (int)(1000d/FPS);
		double time_remaining;
		
		//start LhSimulator (acm GraphicsProgram)
		lhs.start();
		
		while(runLoop) {
			
			InputHandler.update(model.getPaddle(), pause_time);
			
			for(Ball ball : model.getBalls()) {
				time_remaining = (double)pause_time/1000d;
				k = 0;
				
				 //loop to prevent double bounce errors
				while(time_remaining > 0 && k<MAX_PHYS_ITERATIONS) {
					time_remaining = phys.updateObject(ball, time_remaining) * time_remaining;
					k++;
				}
			}
			
			//model updates views
			model.updateViews();	
			
			try {
			    Thread.sleep(pause_time);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			    // -> change pause_time ?
			}
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println((String) arg);
	}
	
}
