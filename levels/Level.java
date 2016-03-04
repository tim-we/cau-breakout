package breakout.levels;

import java.util.ArrayList;
import java.util.Random;

import breakout.main.Model;
import breakout.assets.BreakoutConstants;
import breakout.items.Brick;

public class Level {

	private static final String[] levels = { 
			"-222-222-11-111-11-0000000-", 
			"000000000000000000000000000",
			"111111111111111111111111111", 
			"222222222222222222222222222", 
			"012012012012012012012012012",
			"010101010101010101010101010", 
			"121212121212121212121212121", 
			"020202020202020202020202020",
			"-1-1-1-1-1-1-1-1-1-1-1-1-1-", 
			"-1--1--1--1--1--1--1--1--1-",
			
			//CAU Level:
			"?2$2$2--$$1--$$$0-0 "
				+ "?2$---$1$1-$0-0 "
				+ "?2--1-$$1-$$0-0 "
				+ "?2--1$1$1-$$0-0 "
				+ "?2$--1-10$0$0$0 "
				+ "?2$2$2-$$1-10$0$0$0 "
	};

	private Level(double w) {
	}

	public static String randomLevel() {
		Random rgen = new Random();
		int lvl = rgen.nextInt(levels.length);
		return levels[lvl];
	}

	public static String getLevelByIndex(int i) {
		if(i<0 || i>=levels.length) { return ""; }
		
		return levels[i];
	}

	public static void setBricks(String brickData, Model m) {
		// remove all existing bricks
		m.clearBricks();
		
		//for debugging:
		//System.out.println("loading level: " + brickData);

		//int bricksPerRow = (int) Math.floor((worldWidth - BreakoutConstants.BRICK_X_OFFSET) / (Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET));

		//int xk = 0;
		double xPos;
		double yPos = BreakoutConstants.BRICK_Y_OFFSET;
		xPos = BreakoutConstants.BRICK_X_OFFSET;

		for (int i = 0; i < brickData.length(); i++) {
			byte brickType = -1;

			switch (brickData.charAt(i)) {
			case '0':
				brickType = 0;
				break;
			case '1':
				brickType = 1;
				break;
			case '2':
				brickType = 2;
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

			if (brickType >= 0) {
				m.addBrick(new Brick(xPos, yPos, (byte) brickType));
			}

			if (brickData.charAt(i) != '$' && brickData.charAt(i) != '?' && brickData.charAt(i) != ';') {
				xPos += Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET;
			}

		}
		
	}
	
	public static void loadLevel(int i, Model m) {
		String level = Level.getLevelByIndex(i);
		Level.setBricks(level, m);
	}
	
	public static void loadLevel(Model m) {
		String level = Level.randomLevel();
		Level.setBricks(level, m);
	}
}
