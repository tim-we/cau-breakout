package breakout.main;

import breakout.lighthouse.LhNetwork;

import java.util.Observable;

public class LhView extends View {
	
	private LhNetwork net;
	
	public LhView(int width, int height, LhNetwork net) {
		super(width, height);
		
		this.net = net;
		
		// Be notified when the lighthouse requests a new image
		net.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg.toString());
		// We are registered as an observer both on the model and on the network
		if (o instanceof Model) {
			// The model has updated; generate a new picture we can send to the lighthouse
			// once requested
			Model m = (Model) o;
			renderFrame(m);
		} else if (o instanceof LhNetwork) {
			// The lighthouse sends a request. Send the current image
			sendBuffer();

			// The lighthouse always tells us how many images it still has in its buffer of
			// images to be displayed. If the buffer is empty, send the current image again
			// to prevent the buffer from running empty
			if (arg.toString().equals("bufLen=0")) {
				sendBuffer();
			}

			// Notify observers (this is mainly for debugging purposes)
			setChanged();
			notifyObservers(arg);
		}

	}
	
	/**
	 * Sends the current image over the network to the lighthouse.
	 */
	private synchronized void sendBuffer() {
		net.sendBuff(frame.getByteArray());
	}
	
	public void update(Model m) {
		/*deprecated*/
	}
}
