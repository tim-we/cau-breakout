package breakout.main;

import breakout.lighthouse.LhNetwork;

public class LhView extends View {
	
	private LhNetwork net;
	
	public LhView(int width, int height, LhNetwork net) {
		super(width, height);
		
		this.net = net;
	}
	
	public void update(Model m) {
		
	}
}
