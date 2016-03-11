package breakout.main;

import java.util.Observable;

import breakout.lighthouse.LhSimulator;

public class ACMView extends View {
	
	private LhSimulator display;
	
	/**
	 * Constructor: Creates a new ACMView with given LhSimulator
	 * @param lhs - the LhSimulator for the ACMView
	 */
	public ACMView(LhSimulator lhs) {
		super();
		
		this.display = lhs;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		assert o instanceof Model;
		
		renderFrame((Model) o);
		
		display.draw(frame);
	}

}
