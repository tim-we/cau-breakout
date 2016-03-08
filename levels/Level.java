package breakout.levels;

import breakout.physics.Vector2D;

public class Level {

	private final String levelstring;
	
	private final Vector2D ballSpawnPos = null;
	private final Vector2D ballSpawnVel = null;
	
	public Level(String string){
		this.levelstring = string;
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
