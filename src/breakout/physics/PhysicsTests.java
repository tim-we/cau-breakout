package breakout.physics;

import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class PhysicsTests extends ConsoleProgram {
	
	public void run() {
		
		//PhysicsContext phys = new PhysicsContext(200,100);
		
		Vector2D a = new Vector2D(0,1);
		Vector2D b = new Vector2D(4,0);
		Vector2D c = new Vector2D(2,0);
		Vector2D d = new Vector2D(0,2);
		
		Vector2D result = PhysicsContext.lineSegmentIntersection(a, b, c, d);
		
		if(result == null) { println("Test 1 failed."); }
		else {
			println("Test 1 passed. (" + result + ")");
		}
	}
	
}
