package breakout.main;

import java.util.Observable;
import java.util.Observer;

import breakout.items.*;
import breakout.lighthouse.LhSimulator;
import breakout.physics.*;
import breakout.input.BreakoutInput;
import breakout.assets.BreakoutConstants;
import breakout.levels.*;

public class Controller implements Observer, PhysicsEventReceiver {
	
	public static final double WORLDWIDTH = BreakoutConstants.WINDOW_COLUMNS * BreakoutConstants.WINDOW_WIDTH;
	public static final double WORLDHEIGHT = BreakoutConstants.WINDOW_ROWS * BreakoutConstants.WINDOW_HEIGHT;
	
	public static final int FPS = 30;
	
	public static final int MAX_PHYS_ITERATIONS = 5; //per frame
	
	private BreakoutInput InputHandler;
	
	private Model model;
	
	private PhysicsContext phys;
	
	public Controller(BreakoutInput input) {
		InputHandler = input;
	}
	
	public void runController() {
			
		model = new Model(WORLDWIDTH, WORLDHEIGHT);	
		
		LhSimulator lhs = new LhSimulator();
		View view = new View(28, 14, lhs);
		
		model.addView(view);
		
		InputHandler.init(model, view);
		
		phys = new PhysicsContext(WORLDWIDTH, WORLDHEIGHT);
		phys.eventReceiver = this;
		
		model.setBricks("-222-222-1$1-111-11-0000000-");
	
		refreshStaticObjects();
		
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
		
		System.out.println("Game ended. Score: " + model.getScore());
	}
	
	public void onCollision(CollisionEvent e) {		
		PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectB() : e.getObjectA();
		
		if(x instanceof Brick) {
			model.getBricks().remove(x);
			
			model.addPoints(((Brick)x).getHitPoints());
			
			refreshStaticObjects();
		}
	}
	
	private void refreshStaticObjects() {
		phys.staticObjects.clear();
		
		phys.staticObjects.add(model.getPaddle());
		
		for(Brick brick : model.getBricks()) {
			phys.staticObjects.add(brick);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println((String) arg);
	}
	
}
