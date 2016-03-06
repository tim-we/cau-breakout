package breakout.main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import breakout.items.*;
import breakout.lighthouse.LhSimulator;
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
	
	private PhysicsContext phys;
	
	private boolean runLoop = false;
	
	public Controller(BreakoutInput input) {
		InputHandler = input;
	}
	
	public void runController(LhSimulator lhs) {
			
		model = new Model(WORLDWIDTH, WORLDHEIGHT);	
		
		view = new View(28, 14, lhs);
		
		model.addView(view);
		
		InputHandler.init(model, view);
		
		phys = new PhysicsContext(WORLDWIDTH, WORLDHEIGHT);
		phys.eventReceiver = this;
		

		LevelLoader.loadLevel(13, model);
		//LevelLoader.loadLevel(model);

		//LevelLoader.loadLevel(2, model);
		LevelLoader.loadLevel(model);

		refreshStaticObjects();
		
		runLoop = true;
		int k;
		int pause_time = (int)(1000d/FPS);
		double time_remaining;
		
		model.spawnBall(new Vector2D(WORLDWIDTH/2, WORLDHEIGHT/2), new Vector2D(3 * BreakoutConstants.WINDOW_HEIGHT, 6 * BreakoutConstants.WINDOW_HEIGHT));
		
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
		
		pause(1000);
		
	}
	
	public void pause(int ms) {
		try {
		    Thread.sleep(ms);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		    // -> do something ?
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
