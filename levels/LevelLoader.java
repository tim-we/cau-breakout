package breakout.levels;

import java.util.ArrayList;
import java.util.Random;

import breakout.main.Model;
import breakout.assets.BreakoutConstants;
import breakout.items.Brick;
import breakout.levels.*;

public class LevelLoader {
	
	private static final Level[] levels = { 
			new Level("-222-222-11-111-11-0005000-"), 
			new Level("000003434340003434000006700"),
			new Level("111111111111111115111151666"), 
			new	Level("222222222222222222222777222"), 
			new Level("012012012012012012512016012"),
			new Level("010101010101010101510107010"), 
			new	Level("121212121212121212121212121"), 
			new Level("020202020202020202520202020"),
			new	Level("-1-1-1-1-1-1-1-1-1-151-1-1-"), 
			new	Level("-1--1--1--1--1--1--1-51--1-"),
			
			//CAU Level:
		new	Level("?2$2$2--$$1--$$$0-0 "
				+ "?2$---$1$1-$0-0 "
				+ "?2--1-$$1-$$0-0 "
				+ "?2--1$1$1-$$0-0 "
				+ "?2$--1-10$0$0$0 "
				+ "?2$2$2-$$1-1-$0$0$ "),
		new Level("?2-$1$1--0--$$0-"
				+ "?2-$1-$$1--$$0$0-$0-"
				+ "?2-$1-$$1--$$00-$$0-"
				+ "?2-$1$1--0-$$00-"
				+ "?2-$1---$0-$0$0-"
				+ "?2-$1---$0--$$0-"),
		new Level("?$2-2-1-$$0$0$0$2-$$2"
				+ "?$2$2$2$2-$1$1-$0-$$2-$$2"
				+ "?$222-$$1-$$1-$$0-$$2$2$2"
				+ "?$2-2-$$1$1$1-$$0-$$2-$$2"
				+ "?$2-21-10-$$2-$$2"
				+ "?$2-21-10-$$2-$$2"),
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
				+ ""),
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
				+ ""),
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
				+ ""),
	};

	private LevelLoader(double w) {
	}
	
	public static Level randomLevel() {
		Random rgen = new Random();
		int lvl = rgen.nextInt(levels.length);
		
		System.out.println("Loading level " + lvl);
		
		return levels[lvl];
	}

	public static Level getLevelByIndex(int i) {
		if(i<0 || i>=levels.length) { return new Level(""); }
		
		return levels[i];
	}

	public static void setBricks(Level brickData, Model m) {
		// remove all existing bricks
		m.clearBricks();
		
		//for debugging:
		//System.out.println("loading level: " + brickData);

		double xPos;
		double yPos = BreakoutConstants.BRICK_Y_OFFSET;
		xPos = BreakoutConstants.BRICK_X_OFFSET;

		for (int i = 0; i < brickData.getString().length(); i++) {
			byte brickType = -1;
			boolean destroyed=true;
			switch (brickData.getString().charAt(i)) {
			case '0':
				brickType = 0;
				destroyed=false;
				break;
			case '1':
				brickType = 1;
				destroyed=false;
				break;
			case '2':
				brickType = 2;
				destroyed=false;
				break;
			case '3':
				brickType = 3;
				destroyed=false;
				break;
			case '4':
				brickType = 4;
				destroyed=false;
				break;
			case '5':
				brickType = 5;
				destroyed=false;
				break;
			case '6':
				brickType = 6;
				destroyed = false;
				break;
			case '7':
				brickType = 7;
				destroyed = false;
				break;
			case '.': // forced line break
				//xk = bricksPerRow - 1;
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

			if (xPos >= m.getWidth()) {
				xPos = BreakoutConstants.BRICK_X_OFFSET;
				yPos += Brick.brickHeight + BreakoutConstants.BRICK_Y_OFFSET;
			}

			if (!destroyed) {
				m.addBrick(new Brick(xPos, yPos, (byte) brickType, destroyed));
			}

			if (brickData.getString().charAt(i) != '$' && brickData.getString().charAt(i) != '?' && brickData.getString().charAt(i) != ';') {
				xPos += Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET;
			}

		}
		
	}
	
	public static void loadLevel(int i, Model m) {
		Level level = LevelLoader.getLevelByIndex(i);
		LevelLoader.setBricks(level, m);
	}
	
	public static void loadLevel(Model m) {
		Level level = LevelLoader.randomLevel();
		LevelLoader.setBricks(level, m);
	}
}
