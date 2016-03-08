package breakout.levels;

import breakout.physics.Vector2D;

public class Level {

	private final String levelstring;
	
	private final Vector2D ballSpawnPos;
	private final Vector2D ballSpawnVel;
	
	public Level(String string) {
		this.levelstring = string;
		
		ballSpawnPos = null;
		ballSpawnVel = null;
	}
	
	public Level(String lvlData, Vector2D bspos) {
		this.levelstring = lvlData;
		
		ballSpawnPos = bspos;
		ballSpawnVel = null;
	}
	
	public Level(String lvlData, Vector2D bspos, Vector2D bsvel) {
		this.levelstring = lvlData;
		
		ballSpawnPos = bspos;
		ballSpawnVel = bsvel;
	}
	
	public String getString(){
		return this.levelstring;
	}
	
	public Vector2D getBallSpawnPos() {
		return ballSpawnPos;
	}
	
	public Vector2D getBallSpawnVel() {
		return ballSpawnVel;
	}
}
