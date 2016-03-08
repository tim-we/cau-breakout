package breakout.animations;

import breakout.assets.PixelImage;
import breakout.items.Ball;
import breakout.main.Model;
import breakout.physics.Vector2D;

public class SpawnBall extends Animation {
	
	private Vector2D Position;
	private Vector2D Velocity;
	
	private int[] viewPos = new int[2];
	
	private Model model;
	
	private boolean spawnedBall = false;
	
	public SpawnBall(Vector2D pos, Vector2D vel, Model m) {
		Position = pos;
		Velocity = vel;
		
		viewPos = breakout.main.View.getViewCoordinates(pos, m);
		
		frames = 20;
		
		model = m;
	}
	
	@Override
	public PixelImage renderFrame(PixelImage frame) {
		
		if((currentFrame/2) % 2 == 0) {
			frame.setPixel(viewPos[0], viewPos[1], Ball.color);
		}
		
		if(currentFrame >= frames) {		
			if(!spawnedBall) {
				model.addBall(new Ball(Position, Velocity));
				spawnedBall = true;
			}
		}
		
		return frame;
	}
	
}
