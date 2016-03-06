package breakout.animations;

import breakout.assets.PixelImage;
import breakout.items.Ball;
import breakout.main.Model;
import breakout.main.View;
import breakout.physics.Vector2D;

public class SpawnBall extends Animation {
	
	private Vector2D Position;
	private Vector2D Velocity;
	
	private int[] viewPos = new int[2];
	
	private Model model;
	
	public SpawnBall(Vector2D pos, Vector2D vel, Model m, View v) {
		Position = pos;
		Velocity = vel;
		
		viewPos = v.getViewCoordinates(pos, m);
		
		frames = 20;
		
		model = m;
	}
	
	@Override
	public PixelImage renderNextFrame(PixelImage frame) {
		
		if((currentFrame/2) % 2 == 0) {
			frame.setPixel(viewPos[0], viewPos[1], Ball.color);
		}
		
		currentFrame++;
		
		if(currentFrame >= frames) {
			
			model.addBall(new Ball(Position, Velocity));
			
			finished = true;
			currentFrame = 0;
		}
		
		return frame;
	}
	
}
