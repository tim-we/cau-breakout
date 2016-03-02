package breakout.lighthouse;

import java.util.Observable;
import java.util.Observer;

import breakout.main.Model;

public class LhView extends Observable implements Observer {
	
	/** Our network connection to the lighthouse. */
	private LhNetwork net;
	/** The buffer that contains the current image to be sent to the lighthouse upon request. */
	private byte[] buf = new byte[1177];

	
	/**
	 * Creates a new view that uses the given network to access the lighthouse.
	 * 
	 * @param n
	 *            the network.
	 */
	public LhView(LhNetwork n) {
		// Save the network connection
		net = n;

		// Be notified when the lighthouse requests a new image
		net.addObserver(this);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// We are registered as an observer both on the model and on the network
		if (o instanceof Model) {
			// The model has updated; generate a new picture we can send to the lighthouse
			// once requested
			//Model m = (Model) o;
			//updateBuffer(m.getH(), m.getMin(), m.getSek());
		} else if (o instanceof LhNetwork) {
			// The lighthouse sends a request. Send the current image
			sendBuffer();

			// The lighthouse always tells us how many images it still has in its buffer of
			// images to be displayed. If the buffer is empty, send the current image again
			// to prevent the buffer from running empty
			if (arg.toString().equals("bufLen=0")) {
				sendBuffer();
			}

			// Notifiy observers (this is mainly for debugging purposes)
			setChanged();
			notifyObservers(arg);
		}

	}

	/**
	 * Draws a new image that shows the given time.
	 */
	private void updateBuffer(String h, String m, String s) {
		// We'll be working with a temporary image buffer and only make that the new official
		// buffer once we've finished drawing
		byte[] nbuf = new byte[1176];
		System.arraycopy(smiley, 0, nbuf, 0, nbuf.length);

		// Draw the clock's hands onto the current buffer
		int hi = 5 * Integer.parseInt(h);
		setTime(nbuf, 2, hi);
		int mi = Integer.parseInt(m);
		setTime(nbuf, 1, mi);
		int si = Integer.parseInt(s);
		setTime(nbuf, 0, si);

		// Apply the buffer
		synchronized (this) {
			buf = nbuf;
		}
	}

	private void set(byte[] nbuf, int pos, int n) {
		nbuf[pos + 3 * n] = (byte) 0xff;
	}

	private void setTime(byte[] nbuf, int pos, int data) {
		switch (data) {
		case 60:
		case 0:
			set(nbuf, pos, 28 * 13 + 14);
			set(nbuf, pos, 28 * 13 + 15);
			set(nbuf, pos, 28 * 13 + 12);
			set(nbuf, pos, 28 * 13 + 13);
			break;
		case 1:
			set(nbuf, pos, 28 * 13 + 14);
			set(nbuf, pos, 28 * 13 + 15);
			break;
		case 2:
			set(nbuf, pos, 28 * 13 + 16);
			set(nbuf, pos, 28 * 13 + 17);
			break;
		case 3:
			set(nbuf, pos, 28 * 13 + 18);
			set(nbuf, pos, 28 * 13 + 19);
			break;
		case 4:
			set(nbuf, pos, 28 * 13 + 20);
			set(nbuf, pos, 28 * 13 + 21);
			break;
		case 5:
			set(nbuf, pos, 28 * 13 + 22);
			set(nbuf, pos, 28 * 13 + 23);
			break;
		case 6:
			set(nbuf, pos, 28 * 13 + 24);
			set(nbuf, pos, 28 * 13 + 25);
			break;
		case 7:
			set(nbuf, pos, 28 * 13 + 26);
			set(nbuf, pos, 28 * 13 + 27);
			break;
		case 8:
			set(nbuf, pos, 28 * 14 - 1);
			break;
		case 9:
			set(nbuf, pos, 28 * 13 - 1);
			break;
		case 10:
			set(nbuf, pos, 28 * 12 - 1);
			break;
		case 11:
			set(nbuf, pos, 28 * 11 - 1);
			break;
		case 12:
			set(nbuf, pos, 28 * 10 - 1);
			break;
		case 13:
			set(nbuf, pos, 28 * 9 - 1);
			break;
		case 14:
			set(nbuf, pos, 28 * 8 - 1);
			break;
		case 15:
			set(nbuf, pos, 28 * 7 - 1);
			set(nbuf, pos, 28 * 8 - 1);
			break;
		case 16:
			set(nbuf, pos, 28 * 7 - 1);
			break;
		case 17:
			set(nbuf, pos, 28 * 6 - 1);
			break;
		case 18:
			set(nbuf, pos, 28 * 5 - 1);
			break;
		case 19:
			set(nbuf, pos, 28 * 4 - 1);
			break;
		case 20:
			set(nbuf, pos, 28 * 3 - 1);
			break;
		case 21:
			set(nbuf, pos, 28 * 2 - 1);
			break;
		case 22:
			set(nbuf, pos, 27);
			break;
		case 23:
			set(nbuf, pos, 27);
			set(nbuf, pos, 26);
			break;
		case 24:
			set(nbuf, pos, 25);
			set(nbuf, pos, 24);
			break;
		case 25:
			set(nbuf, pos, 23);
			set(nbuf, pos, 22);
			break;
		case 26:
			set(nbuf, pos, 21);
			set(nbuf, pos, 20);
			break;
		case 27:
			set(nbuf, pos, 19);
			set(nbuf, pos, 18);
			break;
		case 28:
			set(nbuf, pos, 17);
			set(nbuf, pos, 16);
			break;
		case 29:
			set(nbuf, pos, 15);
			set(nbuf, pos, 14);
			break;
		case 30:
			set(nbuf, pos, 12);
			set(nbuf, pos, 13);
			set(nbuf, pos, 14);
			set(nbuf, pos, 15);
			break;
		case 31:
			set(nbuf, pos, 13);
			set(nbuf, pos, 12);
			break;
		case 32:
			set(nbuf, pos, 11);
			set(nbuf, pos, 10);
			break;
		case 33:
			set(nbuf, pos, 9);
			set(nbuf, pos, 8);
			break;
		case 34:
			set(nbuf, pos, 7);
			set(nbuf, pos, 6);
			break;
		case 35:
			set(nbuf, pos, 5);
			set(nbuf, pos, 4);
			break;
		case 36:
			set(nbuf, pos, 3);
			set(nbuf, pos, 2);
			break;
		case 37:
			set(nbuf, pos, 1);
			set(nbuf, pos, 0);
			break;
		case 38:
			set(nbuf, pos, 28 * 0);
			break;
		case 39:
			set(nbuf, pos, 28 * 1);
			break;
		case 40:
			set(nbuf, pos, 28 * 2);
			break;
		case 41:
			set(nbuf, pos, 28 * 3);
			break;
		case 42:
			set(nbuf, pos, 28 * 4);
			break;
		case 43:
			set(nbuf, pos, 28 * 5);
			break;
		case 44:
			set(nbuf, pos, 28 * 6);
			break;
		case 45:
			set(nbuf, pos, 28 * 7);
			set(nbuf, pos, 28 * 6);
			break;
		case 46:
			set(nbuf, pos, 28 * 7);
			break;
		case 47:
			set(nbuf, pos, 28 * 8);
			break;
		case 48:
			set(nbuf, pos, 28 * 9);
			break;
		case 49:
			set(nbuf, pos, 28 * 10);
			break;
		case 50:
			set(nbuf, pos, 28 * 11);
			break;
		case 51:
			set(nbuf, pos, 28 * 12);
			break;
		case 52:
			set(nbuf, pos, 28 * 13);
			break;
		case 53:
			set(nbuf, pos, 28 * 13 + 0);
			set(nbuf, pos, 28 * 13 + 1);
			break;
		case 54:
			set(nbuf, pos, 28 * 13 + 2);
			set(nbuf, pos, 28 * 13 + 3);
			break;
		case 55:
			set(nbuf, pos, 28 * 13 + 4);
			set(nbuf, pos, 28 * 13 + 5);
			break;
		case 56:
			set(nbuf, pos, 28 * 13 + 6);
			set(nbuf, pos, 28 * 13 + 7);
			break;
		case 57:
			set(nbuf, pos, 28 * 13 + 8);
			set(nbuf, pos, 28 * 13 + 9);
			break;
		case 58:
			set(nbuf, pos, 28 * 13 + 10);
			set(nbuf, pos, 28 * 13 + 11);
			break;
		case 59:
			set(nbuf, pos, 28 * 13 + 12);
			set(nbuf, pos, 28 * 13 + 13);
			break;
		}
	}
	
	/**
	 * Sends the current image over the network to the lighthouse.
	 */
	private synchronized void sendBuffer() {
		net.sendBuff(buf);
	}

	/** A smiley image. */
	private byte[] smiley = new byte[] { ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 6), ((byte) 2), ((byte) 0), ((byte) 2),
			((byte) 2), ((byte) 1), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 2), ((byte) 3),
			((byte) 1), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 1), ((byte) 1), ((byte) 1), ((byte) 2), ((byte) 2), ((byte) 0), ((byte) 0),
			((byte) 1), ((byte) 0), ((byte) 0), ((byte) 2), ((byte) 0), ((byte) 0), ((byte) 27),
			((byte) 168), ((byte) 83), ((byte) 121), ((byte) 252), ((byte) 113), ((byte) 177),
			((byte) 255), ((byte) 117), ((byte) 190), ((byte) 255), ((byte) 116), ((byte) 187),
			((byte) 255), ((byte) 111), ((byte) 181), ((byte) 186), ((byte) 94), ((byte) 138),
			((byte) 24), ((byte) 4), ((byte) 40), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 2), ((byte) 0), ((byte) 1), ((byte) 1), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0),
			((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0), ((byte) 2), ((byte) 33), ((byte) 29),
			((byte) 14), ((byte) 206), ((byte) 179), ((byte) 14), ((byte) 255), ((byte) 255), ((byte) 3),
			((byte) 255), ((byte) 223), ((byte) 34), ((byte) 234), ((byte) 94), ((byte) 171),
			((byte) 233), ((byte) 97), ((byte) 172), ((byte) 235), ((byte) 102), ((byte) 165),
			((byte) 235), ((byte) 102), ((byte) 165), ((byte) 231), ((byte) 102), ((byte) 169),
			((byte) 230), ((byte) 83), ((byte) 181), ((byte) 255), ((byte) 201), ((byte) 51),
			((byte) 255), ((byte) 255), ((byte) 2), ((byte) 233), ((byte) 201), ((byte) 11), ((byte) 36),
			((byte) 32), ((byte) 10), ((byte) 0), ((byte) 0), ((byte) 4), ((byte) 0), ((byte) 0),
			((byte) 1), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 60), ((byte) 52), ((byte) 7), ((byte) 205),
			((byte) 190), ((byte) 11), ((byte) 255), ((byte) 214), ((byte) 5), ((byte) 255),
			((byte) 255), ((byte) 7), ((byte) 173), ((byte) 167), ((byte) 2), ((byte) 65), ((byte) 39),
			((byte) 49), ((byte) 242), ((byte) 103), ((byte) 173), ((byte) 241), ((byte) 101),
			((byte) 167), ((byte) 255), ((byte) 115), ((byte) 185), ((byte) 255), ((byte) 118),
			((byte) 190), ((byte) 234), ((byte) 100), ((byte) 164), ((byte) 251), ((byte) 109),
			((byte) 183), ((byte) 82), ((byte) 40), ((byte) 73), ((byte) 138), ((byte) 140), ((byte) 2),
			((byte) 255), ((byte) 255), ((byte) 2), ((byte) 255), ((byte) 221), ((byte) 3), ((byte) 211),
			((byte) 201), ((byte) 11), ((byte) 81), ((byte) 71), ((byte) 8), ((byte) 0), ((byte) 0),
			((byte) 3), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 1), ((byte) 0), ((byte) 2), ((byte) 39), ((byte) 35), ((byte) 11),
			((byte) 142), ((byte) 132), ((byte) 16), ((byte) 247), ((byte) 229), ((byte) 5),
			((byte) 164), ((byte) 155), ((byte) 14), ((byte) 10), ((byte) 7), ((byte) 18), ((byte) 0),
			((byte) 0), ((byte) 5), ((byte) 21), ((byte) 4), ((byte) 24), ((byte) 152), ((byte) 60),
			((byte) 121), ((byte) 157), ((byte) 72), ((byte) 140), ((byte) 6), ((byte) 0), ((byte) 22),
			((byte) 0), ((byte) 0), ((byte) 5), ((byte) 157), ((byte) 68), ((byte) 131), ((byte) 155),
			((byte) 68), ((byte) 132), ((byte) 45), ((byte) 18), ((byte) 44), ((byte) 0), ((byte) 0),
			((byte) 4), ((byte) 0), ((byte) 0), ((byte) 19), ((byte) 157), ((byte) 137), ((byte) 13),
			((byte) 239), ((byte) 224), ((byte) 4), ((byte) 161), ((byte) 149), ((byte) 13), ((byte) 52),
			((byte) 51), ((byte) 14), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 4), ((byte) 120), ((byte) 104),
			((byte) 5), ((byte) 255), ((byte) 250), ((byte) 2), ((byte) 244), ((byte) 210), ((byte) 6),
			((byte) 255), ((byte) 226), ((byte) 12), ((byte) 246), ((byte) 211), ((byte) 9),
			((byte) 233), ((byte) 199), ((byte) 4), ((byte) 231), ((byte) 202), ((byte) 4), ((byte) 233),
			((byte) 200), ((byte) 2), ((byte) 255), ((byte) 227), ((byte) 9), ((byte) 250), ((byte) 214),
			((byte) 10), ((byte) 234), ((byte) 198), ((byte) 5), ((byte) 231), ((byte) 202), ((byte) 2),
			((byte) 231), ((byte) 195), ((byte) 0), ((byte) 233), ((byte) 196), ((byte) 0), ((byte) 232),
			((byte) 197), ((byte) 0), ((byte) 231), ((byte) 198), ((byte) 0), ((byte) 231), ((byte) 200),
			((byte) 10), ((byte) 241), ((byte) 208), ((byte) 9), ((byte) 255), ((byte) 246), ((byte) 1),
			((byte) 172), ((byte) 147), ((byte) 12), ((byte) 0), ((byte) 0), ((byte) 4), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 3), ((byte) 119),
			((byte) 103), ((byte) 7), ((byte) 255), ((byte) 253), ((byte) 2), ((byte) 235), ((byte) 202),
			((byte) 4), ((byte) 71), ((byte) 67), ((byte) 17), ((byte) 89), ((byte) 76), ((byte) 15),
			((byte) 123), ((byte) 124), ((byte) 11), ((byte) 121), ((byte) 107), ((byte) 10),
			((byte) 119), ((byte) 103), ((byte) 12), ((byte) 47), ((byte) 39), ((byte) 13), ((byte) 159),
			((byte) 139), ((byte) 6), ((byte) 253), ((byte) 238), ((byte) 28), ((byte) 247),
			((byte) 231), ((byte) 122), ((byte) 255), ((byte) 255), ((byte) 255), ((byte) 170),
			((byte) 177), ((byte) 211), ((byte) 194), ((byte) 202), ((byte) 236), ((byte) 255),
			((byte) 255), ((byte) 255), ((byte) 242), ((byte) 226), ((byte) 104), ((byte) 243),
			((byte) 211), ((byte) 17), ((byte) 255), ((byte) 242), ((byte) 0), ((byte) 168),
			((byte) 145), ((byte) 14), ((byte) 0), ((byte) 0), ((byte) 3), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 2), ((byte) 121), ((byte) 107),
			((byte) 11), ((byte) 255), ((byte) 255), ((byte) 6), ((byte) 246), ((byte) 209), ((byte) 2),
			((byte) 245), ((byte) 211), ((byte) 5), ((byte) 247), ((byte) 212), ((byte) 4), ((byte) 246),
			((byte) 212), ((byte) 5), ((byte) 246), ((byte) 212), ((byte) 4), ((byte) 247), ((byte) 212),
			((byte) 5), ((byte) 246), ((byte) 212), ((byte) 2), ((byte) 244), ((byte) 203), ((byte) 0),
			((byte) 255), ((byte) 242), ((byte) 99), ((byte) 243), ((byte) 250), ((byte) 255),
			((byte) 7), ((byte) 9), ((byte) 13), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 46), ((byte) 49), ((byte) 60), ((byte) 255), ((byte) 255),
			((byte) 255), ((byte) 254), ((byte) 224), ((byte) 52), ((byte) 255), ((byte) 247),
			((byte) 1), ((byte) 171), ((byte) 152), ((byte) 18), ((byte) 0), ((byte) 0), ((byte) 3),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 1),
			((byte) 0), ((byte) 0), ((byte) 1), ((byte) 67), ((byte) 56), ((byte) 11), ((byte) 255),
			((byte) 255), ((byte) 7), ((byte) 251), ((byte) 216), ((byte) 0), ((byte) 242), ((byte) 207),
			((byte) 0), ((byte) 244), ((byte) 210), ((byte) 1), ((byte) 244), ((byte) 210), ((byte) 1),
			((byte) 244), ((byte) 210), ((byte) 1), ((byte) 244), ((byte) 210), ((byte) 1), ((byte) 243),
			((byte) 208), ((byte) 2), ((byte) 241), ((byte) 210), ((byte) 26), ((byte) 246),
			((byte) 228), ((byte) 116), ((byte) 255), ((byte) 255), ((byte) 255), ((byte) 178),
			((byte) 185), ((byte) 219), ((byte) 203), ((byte) 210), ((byte) 246), ((byte) 255),
			((byte) 255), ((byte) 255), ((byte) 245), ((byte) 224), ((byte) 95), ((byte) 255),
			((byte) 255), ((byte) 15), ((byte) 103), ((byte) 85), ((byte) 6), ((byte) 0), ((byte) 0),
			((byte) 4), ((byte) 1), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 1),
			((byte) 0), ((byte) 0), ((byte) 4), ((byte) 0), ((byte) 0), ((byte) 8), ((byte) 210),
			((byte) 184), ((byte) 12), ((byte) 255), ((byte) 225), ((byte) 7), ((byte) 249),
			((byte) 216), ((byte) 0), ((byte) 248), ((byte) 213), ((byte) 0), ((byte) 244), ((byte) 210),
			((byte) 1), ((byte) 244), ((byte) 210), ((byte) 1), ((byte) 244), ((byte) 210), ((byte) 1),
			((byte) 244), ((byte) 211), ((byte) 0), ((byte) 244), ((byte) 210), ((byte) 4), ((byte) 243),
			((byte) 204), ((byte) 0), ((byte) 244), ((byte) 206), ((byte) 0), ((byte) 247), ((byte) 209),
			((byte) 0), ((byte) 253), ((byte) 216), ((byte) 0), ((byte) 235), ((byte) 203), ((byte) 10),
			((byte) 13), ((byte) 6), ((byte) 13), ((byte) 0), ((byte) 0), ((byte) 3), ((byte) 3),
			((byte) 1), ((byte) 2), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 3), ((byte) 46), ((byte) 42), ((byte) 12), ((byte) 79),
			((byte) 76), ((byte) 17), ((byte) 117), ((byte) 101), ((byte) 14), ((byte) 255),
			((byte) 255), ((byte) 8), ((byte) 255), ((byte) 255), ((byte) 3), ((byte) 255), ((byte) 247),
			((byte) 4), ((byte) 255), ((byte) 247), ((byte) 4), ((byte) 255), ((byte) 249), ((byte) 5),
			((byte) 255), ((byte) 255), ((byte) 13), ((byte) 148), ((byte) 130), ((byte) 15),
			((byte) 79), ((byte) 74), ((byte) 10), ((byte) 57), ((byte) 50), ((byte) 9), ((byte) 0),
			((byte) 0), ((byte) 3), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 1), ((byte) 1), ((byte) 0), ((byte) 0), ((byte) 2),
			((byte) 0), ((byte) 0), ((byte) 2), ((byte) 0), ((byte) 0), ((byte) 4), ((byte) 0),
			((byte) 0), ((byte) 3), ((byte) 0), ((byte) 0), ((byte) 3), ((byte) 0), ((byte) 0),
			((byte) 4), ((byte) 0), ((byte) 0), ((byte) 1), ((byte) 0), ((byte) 0), ((byte) 2),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0), ((byte) 0),
			((byte) 0), ((byte) 0) };

}
