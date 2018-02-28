package breakout.main;

import java.io.IOException;
import java.util.Observable;

import breakout.lighthouse.LighthouseDisplay;

public class LhView extends View {
	
	private LighthouseDisplay net;
	
	public LhView(LighthouseDisplay net) {
		super();
		
		this.net = net;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// We are only interested if the lighthouse view is connected and if the update
		// request comes from the model
		if (net.isConnected() && o instanceof Model) {
			Model m = (Model) o;
			renderFrame(m);
			sendBuffer();
		}
	}
	
	/**
	 * Sends the current image over the network to the lighthouse.
	 */
	private synchronized void sendBuffer() {
		try {
		    net.send(frame.getFlippedByteArray());
		} catch (IOException e) {
		    System.out.println("Connection failed: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	
}
