package breakout.levels;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import breakout.main.Model;
import breakout.assets.BreakoutConstants;
import breakout.items.Brick;

public class Level {
	
	private double worldWidth;
	private ArrayList<Brick> bricks;
	
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
	};
	
	public Level (double w){
		this.bricks = new ArrayList<Brick>();
		this.worldWidth = w;
	}
	
	public String randomLevel(){
		Random rgen = new Random();
		int lvl = rgen.nextInt(10);
		return levels[lvl];
	}
	
	public String getLevelByIndex(int i){
		return levels[i];
	}
	
	public void setBricks(String brickData) {
		//remove all existing bricks
		bricks = new ArrayList<Brick>();
		
		System.out.println("loading level: " + brickData);
		
		int bricksPerRow = (int) Math.floor( (worldWidth - BreakoutConstants.BRICK_X_OFFSET) / (Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET) );
		
		int xk = 0;
		double xPos;
		double yPos = BreakoutConstants.BRICK_Y_OFFSET;
		xPos = BreakoutConstants.BRICK_X_OFFSET;
		for(int i=0; i<brickData.length(); i++) {
			byte brickType = -1;
			
			switch(brickData.charAt(i)) {
				case '0':
					brickType = 0; break;
				case '1':
					brickType = 1; break;
				case '2':
					brickType = 2; break;
				case '.': //forced line break
					xk=bricksPerRow-1;
				case '$':
					xPos -= BreakoutConstants.BRICK_X_OFFSET;
				case '?':
					yPos -= BreakoutConstants.BRICK_Y_OFFSET;
								
			}
			
			if (xPos>=worldWidth){
				xPos=BreakoutConstants.BRICK_X_OFFSET;
				yPos += Brick.brickHeight + BreakoutConstants.BRICK_Y_OFFSET;
			}
						
			if(brickType >= 0) { bricks.add(new Brick(xPos, yPos, (byte)brickType)); }
			if(brickData.charAt(i)!='$'&&brickData.charAt(i)!='?'){
			xPos +=Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET;}
			
		}
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
}
