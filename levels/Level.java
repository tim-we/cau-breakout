package breakout.levels;

import java.util.Random;
import java.util.Scanner;

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
	};
	
	
	
	public String randomLevel(){
		Random rgen = new Random();
		int lvl = rgen.nextInt(10);
		return levels[lvl];
	}
	
	public String getLevelByIndex(int i){
		return levels[i];
	}
	
}
