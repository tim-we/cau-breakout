package breakout.lighthouse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

/**
 * Network connection to the lighthouse. This is a runnable class that should be started in a separate thread (see
 * {@link Controller} for an example). The network listens for incoming messages from the lighthouse. The lighthouse
 * will periodically inform the network of how many images it still has in its image buffer. The messages look like
 * this: {@code "bufLen=2"}. To be able to send stuff to the lighthouse, views will have to register as an observer with
 * the network. Once a lighthouse message is received, all registered observers are notified and can choose to react by
 * sending a new image to the lighthouse. See {@link LhView} for an example.
 */
public class LhNetwork extends Observable implements Runnable {
	
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private OutputStream out;
	private DataOutputStream dos;

	public LhNetwork() {
		try {
			// Open connection to the lighthouse or to the local server
			clientSocket = new Socket("localhost", 8000);
			
			// Fetch input and output streams
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = clientSocket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dos = new DataOutputStream(out);
	}

	@Override
	public void run() {
		System.out.println("Network thread running...");
		
		while (true) {
			String s = "???";
			
			try {
				// Wait for the server to send stuff
				s = inFromServer.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Notify observers
			setChanged();
			notifyObservers(s);
		}
	}

	/**
	 * Sends the given byte buffer to the lighthouse. The buffer must contain at least 1177 bytes.
	 * 
	 * @param b the buffer.
	 */
	public void sendBuff(byte[] b) {
		if (b.length < 1176) {
			throw new IllegalArgumentException("buffer contains " + b.length + " bytes; "
					+ "expected at least 1176 bytes");
		}
		
		try {
			dos.write(b, 0, 3 * 28 * 14);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
