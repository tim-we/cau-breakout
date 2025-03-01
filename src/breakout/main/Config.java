package breakout.main;

/**
 * Contains all Constants
 */
public class Config {
	public static final int WINDOW_WIDTH = 10;
	
	public static final int WINDOW_HEIGHT = 16;
	
	public static final int WINDOW_X_OFFSET = 3;
	
	public static final int WINDOW_Y_OFFSET = 12;
	
	public static final int WINDOW_ROWS = 14;
	
	public static final int WINDOW_COLUMNS = 28;
	
	public static final int BRICK_X_OFFSET = WINDOW_WIDTH;
	
	public static final int BRICK_Y_OFFSET = WINDOW_HEIGHT;
	
	public static final int FPS = 25;
	
	//public static final double BALL_BOUNCE_SPEED_FACTOR = 1.03;
	
	public static final double BALL_MAX_SPEED = 400;
	
	public static final boolean BALL_BOUNCE_ADVANCED_MECHANICS = true;
	
	public static final double BALL_VELOCITY_CHANGE = 42;
	
	public static final int TAIL_LENGTH = 4;
	
	public static final double changeSizePaddle = 2 * WINDOW_WIDTH;
	
	public static final double normalPaddle = 5 * WINDOW_WIDTH;
	
	public static final boolean HIGHRISER_VIEW_ENABLED = false;
	
	/*
	 * choose from: Keyboard, Bot, Mouse
	 */
	public static final String INPUT_SOURCE = "Mouse,Keyboard";
	
	public static final boolean AUTO_BOT = true;
	
	public static final String USER_NAME = "";
	public static final String USER_TOKEN = "";
}
