package breakout.main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import acm.util.RandomGenerator;

import breakout.items.*;
import breakout.lighthouse.*;
import breakout.physics.*;
import breakout.input.*;
import breakout.animations.*;
import breakout.levels.*;


public class Controller implements Observer, PhysicsEventReceiver {
	
	public static final double WORLDWIDTH = Config.WINDOW_COLUMNS * Config.WINDOW_WIDTH;
	public static final double WORLDHEIGHT = Config.WINDOW_ROWS * Config.WINDOW_HEIGHT;
	
	public static final int FPS = Config.FPS;
	
	public static final int MAX_PHYS_ITERATIONS = 4; //per frame
	
	private ArrayList<BreakoutInput> InputHandler = new ArrayList<BreakoutInput>();
	
	private Model model;
	
	private View view;
	
	private LhView lhv;
	
	private PhysicsContext phys;
	
	private LhSimulator lhs;
	
	private LhNetwork net;
	
	private boolean runLoop = false;
	
	//constructor
	public Controller() {
		lhs = new LhSimulator();
		lhs.start();
	}
	
	/**
	 * Adds a InputHandler
	 * @param input - the sort of InputHandler to add
	 */
	public void addInputHandler(BreakoutInput input) {
		InputHandler.add(input);
		
		if(input instanceof KeyboardInput) {
			((KeyboardInput)input).setLHS(lhs);
		}
	}
	
	/**
	 * Method to execute the Controller
	 */
	public void runController() {
			
		model = new Model(WORLDWIDTH, WORLDHEIGHT);	
		
		//init input handlers
			for(BreakoutInput input : InputHandler) {
				input.init(model);
			}
		
		//create views and register them with the model
		
			if(Config.HIGHRISER_VIEW_ENABLED) {
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
		
		
		if(Config.HIGHRISER_VIEW_ENABLED) {
			/*	Start the network connection in a separate thread. That's important,
			*	because our program should continue doing stuff instead of idling around
			*	while it waits for the server to send the next request
			*/
			
			new Thread(net).start();
		}
		
		playAnimation(new IntroAnimation());
		pause(1000);
		
		/* Loop which gets executed while the game is running */
		while(true) {
			
			/* Loads a random level */
			LevelLoader.loadLevel(model);
			//LevelLoader.loadLevel(25,model);
			
			refreshStaticObjects();
			
			runLoop = true;
			
			while(runLoop) {
				
				/* Updates the Paddle, depending on Input */
				for(BreakoutInput input : InputHandler) {
					input.update(model.getPaddle(), pause_time);
				}
				
				for(int i=0; i<model.getBalls().size(); i++) {
					Ball ball = model.getBalls().get(i);
					/* if there is no Ball left we want to end the level */
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
			
			if(model.getBricks().size() == 0) {
				/* Level complete bonus */
				model.addPoints(350);
				
				Random rgen = new Random();
				int nextParticle = 0;
				
				for(int i=0; i<100; i++) {
					if(nextParticle <= 0) {
						model.addAnimation(new FireworksParticle());
						nextParticle = 1 + rgen.nextInt(5);
					}
					
					nextParticle--;
					model.update();
					pause(pause_time);
				}
			}
			
			System.out.println("Level ended. Score: " + model.getScore());
			
			/* if there were still bricks left we will show a loose animation and then the score*/
			if(model.getBricks().size() > 0) {			
				playAnimation(new WastedAnimation());
				pause(1000);
				
				Animation anim = model.getScore() > 9000 ? new Over9K() : new ScoreAnimation(model.getScore());
				playAnimation(anim);
				pause(3000);
				
				model.resetScore();
			}
			
			model.reset();	
		}
		
	}
	
	/**
	 * Pauses the controller
	 * @param ms - how long the controller should be paused
	 */
	public void pause(int ms) {
		try {
		    Thread.sleep(ms);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		   // Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Plays the given animation
	 * @param anim - the animation to show
	 */
	public void playAnimation(Animation anim) {
		int pause_time = (int)(1000d/FPS);
		
		model.addAnimation(anim);
		
		for(int i=0; i<anim.numFrames(); i++) {
			model.update();
			pause(pause_time);
		}
	}
	
	/**
	 * CollisionListener
	 */
	public void onCollision(CollisionEvent e) {
		if(e.isWallCollision()) { return; }
		
		PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectB() : e.getObjectA();
		
		/* If it was a brick and it gets destroyed we want to remove it and add the points */
		if(x instanceof Brick) {
			Brick brick = (Brick)x;
			
			if(brick.isDestroyed()) {
				
				model.getBricks().remove(x);
				
				model.addPoints(((Brick)x).getHitPoints());
				
				refreshStaticObjects();
				
				/* If there are no bricks left, we want to show a clear level animation */
				if(model.getBricks().size() == 0) {
					runLoop = false;
					playAnimation(new GreenShockwave(e.getCollisionPoint(), model));
					model.clearBalls();
				}
				
				byte typeOfBrick = brick.getBrickType();
				Vector2D p = e.getCollisionPoint();
				
				/* if it was a randomBrick we will execute a random action */
				if (typeOfBrick == 9){
					RandomGenerator rgen = new RandomGenerator();
					typeOfBrick = (byte) (1+rgen.nextInt(8));
				}
				
				/* different Animations and actions depending on the Type of the brick */
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
					if(model.getPaddle().getWidth()<=Config.normalPaddle){
						model.getPaddle().changePaddleWidth(Config.changeSizePaddle);
					}
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
				else if (typeOfBrick == 7){
					if(model.getPaddle().getWidth()>=Config.normalPaddle){
						model.getPaddle().changePaddleWidth(-Config.changeSizePaddle);
					}
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
				else if (typeOfBrick == 8){
					model.spawnBall(new Vector2D(model.getWidth()/2, model.getHeight()/2),new Vector2D(4 * Config.WINDOW_HEIGHT, 7 * Config.WINDOW_HEIGHT));
				}
				else {
					model.addAnimation(new DefaultBrickExplosion(p.getX(), p.getY(), model));
				}
			
			}
			/* If it was a collision with the BarOfDeath the ball dies and will be removed, if it was the last ball the level ends */
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
	
	/**
	 * Refreshes the Paddle, BarOfDeath and the Bricks
	 */
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
