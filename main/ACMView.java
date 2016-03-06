package breakout.main;

import java.util.Observable;

import breakout.lighthouse.LhSimulator;

public class ACMView extends View {
	
	private LhSimulator display;
	
	public ACMView(int width, int height, LhSimulator lhs) {
		super(width, height);
		
		this.display = lhs;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		assert o instanceof Model;
		
		renderFrame((Model) o);
		
		display.draw(frame);
	}
	
	public void update(Model m) {
		/*deprecated*/
		
		renderFrame(m);
		
		display.draw(frame);
		
		System.out.println("deprecated method 1");
	}

}
