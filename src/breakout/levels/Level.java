package breakout.levels;

import breakout.physics.Vector2D;

public class Level {
	
	private final String levelstring;
	
	private final Vector2D ballSpawnPos;
	private final Vector2D ballSpawnVel;
	
	/**
	 * Creates a Level without a specific Spawnposition and Spawnvelocity for the Ball
	 * @param string Describes the Level
	 */
	public Level(String string) {
		this.levelstring = string;
		
		ballSpawnPos = null;
		ballSpawnVel = null;
	}
	
	/**
	 * Creates a Level with a specific Spawnposition but without Spawnvelocity for the Ball
	 * @param lvlData Describes the level
	 * @param bspos specific Spawnposition for the Ball
	 */
	public Level(String lvlData, Vector2D bspos) {
		this.levelstring = lvlData;
		
		ballSpawnPos = bspos;
		ballSpawnVel = null;
	}
	
	/**
	 * Creates a Level with a specific Spawnposition and Spawnvelocity for the Ball
	 * @param lvlData Describes the level
	 * @param bspos specific Spawnposition for the Ball
	 * @param bsvel specific Spawnvelocity for the Ball
	 */
	public Level(String lvlData, Vector2D bspos, Vector2D bsvel) {
		this.levelstring = lvlData;
		
		ballSpawnPos = bspos;
		ballSpawnVel = bsvel;
	}
	
	/* Getter */
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
