package breakout.main;

import java.util.Observable;
import java.util.Observer;

import breakout.items.*;
import breakout.lighthouse.*;
import breakout.physics.*;
import breakout.input.BreakoutInput;
import breakout.animations.*;
import breakout.assets.BreakoutConstants;
import breakout.levels.*;


public class Controller implements Observer, PhysicsEventReceiver {
	
	public static final double WORLDWIDTH = BreakoutConstants.WINDOW_COLUMNS * BreakoutConstants.WINDOW_WIDTH;
	public static final double WORLDHEIGHT = BreakoutConstants.WINDOW_ROWS * BreakoutConstants.WINDOW_HEIGHT;
	
	public static final int FPS = 30;
	
	public static final int MAX_PHYS_ITERATIONS = 5; //per frame
	
	private BreakoutInput InputHandler;
	
	private Model model;
	
	private View view;
	
	private LhView lhv;
	
	private PhysicsContext phys;
	
	private LhSimulator lhs;
	
	private LhNetwork net;
	
	private boolean runLoop = false;
	
	//constructor
	public Controller(BreakoutInput input) {
		InputHandler = input;
		
		lhs = new LhSimulator();
		lhs.start();
	}
	
	public void runController() {
			
		model = new Model(WORLDWIDTH, WORLDHEIGHT);	
		
		
		//create views and register them with the model
			//net = new LhNetwork();
			//lhv = new LhView(BreakoutConstants.WINDOW_COLUMNS, BreakoutConstants.WINDOW_ROWS, net);
			//model.addObserver(lhv);
			
			view = new ACMView(BreakoutConstants.WINDOW_COLUMNS, BreakoutConstants.WINDOW_ROWS, lhs);				
			model.addObserver(view);
		
		
		phys = new PhysicsContext(WORLDWIDTH, WORLDHEIGHT);
		phys.eventReceiver = this;

		//LevelLoader.loadLevel(2, model);
		
		int k;
		int pause_time = (int)(1000d/FPS);
		double time_remaining;
		
		/*	Start the network connection in a separate thread. That's important,
		*	because our program should continue doing stuff instead of idling around
		*	while it waits for the server to send the next request
		*/
				 
		//new Thread(net).start();
		
		while(true) {
			
			LevelLoader.loadLevel(model);

			refreshStaticObjects();
			
			runLoop = true;
			
			model.spawnBall(
					new Vector2D(WORLDWIDTH/2, WORLDHEIGHT/2), 
					new Vector2D(3 * BreakoutConstants.WINDOW_HEIGHT, 6 * BreakoutConstants.WINDOW_HEIGHT),
					view
				);
			
			while(runLoop) {
				
				InputHandler.update(model.getPaddle(), pause_time);
				
				int n = model.getBalls().size();
				for(int i=0; i<n; i++) {
					Ball ball = model.getBalls().get(i);
					if(ball==null) { continue; }
					
					time_remaining = (double)pause_time/1000d;
					k = 0;
					
					//loop to prevent double bounce errors
					while(time_remaining > 0 && k<MAX_PHYS_ITERATIONS) {
						time_remaining = phys.updateObject(ball, time_remaining) * time_remaining;
						k++;
					}
				}
				
				//update model -> update views
				model.update();	
				
				pause(pause_time);
			}
			
			//temporary solution:
			Animation death_anim = new DeathAnimation();
			model.addAnimation(death_anim);
			
			for(int i=0; i<death_anim.numFrames(); i++) {
				model.update();
				pause(pause_time);
			}
			
			System.out.println("Game ended. Score: " + model.getScore());
			
			pause(1500);
			
			model.reset();
		}
		
	}
	
	public void pause(int ms) {
		try {
		    Thread.sleep(ms);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		   // Thread.currentThread().interrupt();
		}
	}
	
	public void onCollision(CollisionEvent e) {
		if(e.isWallCollision()) { return; }
		
		PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectB() : e.getObjectA();
		
		if(x instanceof Brick) {
			Brick brick = (Brick)x;
			
			if(brick.isDestroyed()) {
				
				model.getBricks().remove(x);
				
				model.addPoints(((Brick)x).getHitPoints());
				
				refreshStaticObjects();
				
				Vector2D p = e.getCollisionPoint();
				if (brick.getBrickType() == 3){
					model.addAnimation(new RedShockwave(p, model, view));
				}
				else if (brick.getBrickType() == 4){
					model.addAnimation(new BlueShockwave(p, model, view));
				}
				else if (brick.getBrickType()==5){
					model.getPaddle().toggleReverse();
				}
				else if (brick.getBrickType()==6){
					if(model.getPaddle().getWidth()<=BreakoutConstants.normalPaddle){
						model.getPaddle().changePaddleWidth(BreakoutConstants.changeSizePaddle);
					}
				}
				else if (brick.getBrickType()==7){
					if(model.getPaddle().getWidth()>=BreakoutConstants.normalPaddle){
					model.getPaddle().changePaddleWidth(-BreakoutConstants.changeSizePaddle);
					}
				}
				else {
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
			
			}
		} else if(x instanceof BarOfDeath) {
			//TODO: remove ball, if no balls left trigger game over
			
			model.clearBalls();
			
			if(e.getObjectA() instanceof Ball) { 
				
				((Ball)e.getObjectA()).scaleVelocity(0); 
			}
			
			runLoop = false;
		}
	}
	
	private void refreshStaticObjects() {
		phys.staticObjects.clear();
		
		phys.staticObjects.add(model.getPaddle());
		
		phys.staticObjects.add(model.getBarOfDeath());
		
		for(Brick brick : model.getBricks()) {
			phys.staticObjects.add(brick);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println((String) arg);
	}
	
}
