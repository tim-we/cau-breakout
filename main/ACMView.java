package breakout.main;

import breakout.lighthouse.LhNetwork;
import breakout.lighthouse.LhSimulator;

public class ACMView extends View {
	
	private LhSimulator display;
	
	public ACMView(int width, int height, LhSimulator lhs) {
		super(width, height);
		
		this.display = lhs;
	}
	
	public void update(Model m) {
		renderFrame(m);
		
		display.draw(frame);
	}

}
