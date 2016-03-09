package breakout.main;

import java.util.Observable;
import java.util.Observer;

import acm.util.RandomGenerator;
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
	
	public static final int FPS = BreakoutConstants.FPS;
	
	public static final int MAX_PHYS_ITERATIONS = 4; //per frame
	
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
		
		if(InputHandler instanceof breakout.input.BreakoutBot) {
			((breakout.input.BreakoutBot)InputHandler).init(model);
		}
		else if(InputHandler instanceof breakout.input.KeyboardInput){
			((breakout.input.KeyboardInput)InputHandler).init(lhs);
		}
		
		//create views and register them with the model
		
			if(BreakoutConstants.HIGHRISER_VIEW_ENABLED) {
				net = new LhNetwork();
				lhv = new LhView(net);
				model.addObserver(lhv);
			}
			
			view = new ACMView(lhs);			
			model.addObserver(view);		
		
		//set up physics
			phys = new PhysicsContext(WORLDWIDTH, WORLDHEIGHT);
			phys.eventReceiver = this;
		
		int k;
		int pause_time = (int)(1000d/FPS);
		double time_remaining;
		
		
		if(BreakoutConstants.HIGHRISER_VIEW_ENABLED) {
			/*	Start the network connection in a separate thread. That's important,
			*	because our program should continue doing stuff instead of idling around
			*	while it waits for the server to send the next request
			*/
			
			new Thread(net).start();
		}
		
		playAnimation(new IntroAnimation());
		pause(1000);
		
		while(true) {
			
			LevelLoader.loadLevel(model);
			//LevelLoader.loadLevel(18,model);
			
			refreshStaticObjects();
			
			runLoop = true;
			
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
			
			if(model.getBricks().size() > 0) {			
				playAnimation(new WastedAnimation());
				pause(1000);
			}
			
			System.out.println("Game ended. Score: " + model.getScore());
			
			playAnimation(new ScoreAnimation(model.getScore()));
			
			pause(2500);
			
			if(model.getBricks().size() > 0) { model.resetScore(); }
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
	
	public void playAnimation(Animation anim) {
		int pause_time = (int)(1000d/FPS);
		
		model.addAnimation(anim);
		
		for(int i=0; i<anim.numFrames(); i++) {
			model.update();
			pause(pause_time);
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
				
				if(model.getBricks().size() == 0) {
					runLoop = false;
					playAnimation(new GreenShockwave(e.getCollisionPoint(), model));
					model.clearBalls();
				}
				
				byte typeOfBrick = brick.getBrickType();
				Vector2D p = e.getCollisionPoint();
				
				if (typeOfBrick == 9){
					RandomGenerator rgen = new RandomGenerator();
					typeOfBrick = (byte) (1+rgen.nextInt(8));
				}
				
				if (typeOfBrick == 3){
					model.addAnimation(new RedShockwave(p, model));
				}
				else if (typeOfBrick == 4){
					model.addAnimation(new BlueShockwave(p, model));
				}
				else if (typeOfBrick == 5){
					model.getPaddle().toggleReverse();
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
				else if (typeOfBrick == 6){
					if(model.getPaddle().getWidth()<=BreakoutConstants.normalPaddle){
						model.getPaddle().changePaddleWidth(BreakoutConstants.changeSizePaddle);
					}
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
				else if (typeOfBrick == 7){
					if(model.getPaddle().getWidth()>=BreakoutConstants.normalPaddle){
						model.getPaddle().changePaddleWidth(-BreakoutConstants.changeSizePaddle);
					}
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
				else if (typeOfBrick == 8){
					model.spawnBall(new Vector2D(model.getWidth()/2, model.getHeight()/2),new Vector2D(4 * BreakoutConstants.WINDOW_HEIGHT, 7 * BreakoutConstants.WINDOW_HEIGHT));
				}
				else {
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
			
			}
		} else if(x instanceof BarOfDeath) {
			
			if(e.getObjectA() instanceof Ball) { 
				Ball ball = (Ball) e.getObjectA();
				ball.scaleVelocity(0);
				ball.setDead();
			}
			if (model.getBalls().size() == 1){
				runLoop = false;
			}
			
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
