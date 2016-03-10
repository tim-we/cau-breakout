package breakout.levels;

import java.util.Random;

import breakout.main.Model;
import breakout.physics.Vector2D;
import breakout.assets.BreakoutConstants;
import breakout.items.Brick;

public class LevelLoader {
	
	/**
	 * Contains all Levels
	 */
	private static final Level[] levels = { 
			new Level("-222-222-"+"11-111-11"+"-0004000-"),
			new Level("-242-242-"+"33-191-33"+"-0081800-"),
			new Level("000353000434000647000363000"),
			new Level("101213141516171011121314161"),
			new	Level("000141000646222737111000111"),
			new Level("012012012012012012012012012"),
			new Level("010141010101030101010171010"),
			new	Level("121212121213746512121252121"),
			new Level("01234567--7654321001234567-"),
			new	Level("-1-1-1-1-1-1-5-1-1-3-1-1-4-"),
			new	Level("-0--1--2--3--4--5--6--7--0-"),
			
		//CAU Level:
		new	Level("?2$2$2--$$1--$$$0-0 "
				+ "?2$---$1$1-$0-0 "
				+ "?2--1-$$1-$$0-0 "
				+ "?2--1$1$1-$$0-0 "
				+ "?2$--1-10$0$0$0 "
				+ "?2$2$2-$$1-1-$0$0$ "),
		//IPN Level
		new Level("?2-$1$1--0--$$0-"
				+ "?2-$1-$$1--$$0$0-$0-"
				+ "?2-$1-$$1--$$00-$$0-"
				+ "?2-$1$1--0-$$00-"
				+ "?2-$1---$0-$0$0-"
				+ "?2-$1---$0--$$0-"),
		//Math Level
		new Level("?$2-2-1-$$0$0$0$2-$$2"
				+ "?$2$2$2$2-$1$1-$0-$$2-$$2"
				+ "?$222-$$1-$$1-$$0-$$2$2$2"
				+ "?$2-2-$$1$1$1-$$0-$$2-$$2"
				+ "?$2-21-10-$$2-$$2"
				+ "?$2-21-10-$$2-$$2"),
		//Info Level
		new Level("?21--$$10$0$0-$$$2$2-"
				+ "?21$1-$10--$$$2-$$2-"
				+ "?211-$$10$0-$$$2-2"
				+ "?21-$$110-$2-2"
				+ "?21-$1$10--$$$2-$$2-"
				+ "?21--$$10--$$2$2"),
		//NDR-Level
		new Level("?2--$$21$1-0$0--"
				+ "?2$2-$211-$0-$$0-"
				+ "?22-$$21-$10$0--"
				+ "?2-$$221-$10$0--"
				+ "?2-$2$211-$00-"
				+ "?2--$$21$1-0-$$0"),
		//KIEL-Level
		new Level("?2-$$2-$$10$0$02--"
				+ "?22-$10--$$2--"
				+ "?2$2-10$0-$2--"
				+ "?22-$10--$$2--"
				+ "?2-$$2-$$10--$$2--"
				+ "?2-$210$0$02$2$2-"),
		//UNI-Level
		new Level("?2-21--$$10--"
				+ "?2-21$1-$10--"
				+ "?2-211-$$10--"
				+ "?2-21-$$110--"
				+ "?2-21-$1$10--"
				+ "?-$2$2-$1--$$10--"),
		//triangle
		new Level("-0129210-"
				 +"--13831--"
				 +"---010---"
				 +"----1----",
				 new Vector2D(1 * BreakoutConstants.WINDOW_HEIGHT, 2 * BreakoutConstants.WINDOW_HEIGHT))
		/*
		new Level(""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""),
		new Level(""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""),*/
	};
	
	/* constructor */
	private LevelLoader() {}
	
	/**
	 * 
	 * @return a random level
	 */
	public static Level randomLevel() {
		Random rgen = new Random();
		int lvl = rgen.nextInt(levels.length);
		
		System.out.println("Loading level " + lvl);
		
		return levels[lvl];
	}
	
	/**
	 * 
	 * @param i index of the level in levels-Array
	 * @return the Level at i
	 */
	public static Level getLevelByIndex(int i) {
		if(i<0 || i>=levels.length) { return new Level(""); }
		
		return levels[i];
	}
	
	/* supported/functional characters of the level parser */
	public static final String brickChars = "0123456789";
	public static final String controlChars = ".$?;";
	
	/**
	 * A method which adds the bricks of a given Level to a Model.
	 * @param brickData The Level which should be displayed
	 * @param m The Model which gets the Bricks
	 */
	public static void setBricks(Level brickData, Model m) {
		/* remove all existing bricks */
		m.clearBricks();
		
		/* Positions of the bricks */
		double xPos;
		double yPos = BreakoutConstants.BRICK_Y_OFFSET;
		xPos = BreakoutConstants.BRICK_X_OFFSET;
		
		/* This loop parses the level data of the given level */
		for (int i = 0; i < brickData.getString().length(); i++) {
			
			char ci = brickData.getString().charAt(i);
			
			if(controlChars.indexOf(ci) >= 0) {
				
				/* current char is control character
				 * '.' => forced line break
				 * '$' => changes the X-Position of the next Brick
				 * '?' => changes the Y-Position of the next Brick one Window-Row up
				 * ';' => changes the Y-Position of the next Brick one Window-Row down			
				 */
				 
				switch (ci) {
					case '.': // forced line break
						xPos = BreakoutConstants.BRICK_X_OFFSET;
						yPos += Brick.brickHeight + BreakoutConstants.BRICK_Y_OFFSET;
						break;
					case '$':
						xPos -= BreakoutConstants.BRICK_X_OFFSET;
						break;
					case '?':
						yPos -= BreakoutConstants.BRICK_Y_OFFSET;
						break;
					case ';':
						yPos += BreakoutConstants.BRICK_Y_OFFSET;
						break;
				}
				
			} else {
				
				if(brickChars.indexOf(ci) >= 0) {
					/* current char represents a brick and can be parsed as an integer */
					int brickType = (byte) Character.getNumericValue(ci);
					
					m.addBrick(new Brick(xPos, yPos, (byte) brickType));
				}		
				
				/* unrecognized characters like '-' or ' ' will create a "whitespace" between blocks */
				xPos += Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET;
				
				if (xPos >= m.getWidth()) {
					xPos = BreakoutConstants.BRICK_X_OFFSET;
					yPos += Brick.brickHeight + BreakoutConstants.BRICK_Y_OFFSET;
				}
				
			}

		}
		
	}
	
	/**
	 * Spawns a Ball at default Position and with default Velocity
	 * or at Position and Velocity saved in the Level
	 * @param lvl The level in which the Ball should get added
	 * @param m The Model which gets the Ball
	 */
	public static void setBall(Level lvl, Model m) {
		
		Vector2D pos = lvl.getBallSpawnPos() == null ? new Vector2D(m.getWidth()/2, m.getHeight()/2) : lvl.getBallSpawnPos();
		Vector2D vel = lvl.getBallSpawnVel() == null ? new Vector2D(4 * BreakoutConstants.WINDOW_HEIGHT, 7 * BreakoutConstants.WINDOW_HEIGHT) : lvl.getBallSpawnVel();
		
		m.spawnBall(pos, vel);
	}
	
	/**
	 * Loads the given Level to the Model and adds the Ball
	 * @param lvl The level to load
	 * @param m the Model which gets the Level
	 */
	public static void loadLevel(Level lvl, Model m) {
		LevelLoader.setBricks(lvl, m);
		LevelLoader.setBall(lvl, m);
	}
	
	/**
	 * Loads the Level at given index to the Model and adds the Ball
	 * @param i index of the level in levels-Array
	 * @param m the Model which gets the Level
	 */
	public static void loadLevel(int i, Model m) {
		Level level = LevelLoader.getLevelByIndex(i);
		LevelLoader.loadLevel(level, m);
	}
	
	/**
	 * Loads a random Level to the given Model 
	 * @param m the Model which gets the Level
	 */
	public static void loadLevel(Model m) {
		Level level = LevelLoader.randomLevel();
		LevelLoader.loadLevel(level, m);
	}
}
