package breakout.lighthouse;

import java.awt.Color;

import breakout.assets.PixelImage;
import acm.graphics.*;
import acm.program.GraphicsProgram;

public class LhSimulator extends GraphicsProgram {

	private static final int WIDTH = 28;
	private static final int HEIGHT = 14;
	
	private static final int WINDOW_WIDTH = 10;
	private static final int WINDOW_HEIGHT = 25;
	private static final int BORDER = 5;
	
	private static final Color BGCOLOR = new Color(32,32,32);
	private static final Color WIN_OFF_CLR = new Color(0,0,0);
	
	private static final int WTF_OFFSET = 62; //no further explanation needed. DEAL WITH IT
	
	public void run() {		
		setSize(BORDER + WIDTH*(WINDOW_WIDTH + BORDER) + WTF_OFFSET - 42 -3, BORDER + HEIGHT*(WINDOW_HEIGHT + BORDER) + WTF_OFFSET);
		validate();
	}
	
	public void draw(PixelImage frame) {
	
		GCompound bb = new GCompound();
		
		//background
			GRect bg = new GRect(0, 0, BORDER + WIDTH*(WINDOW_WIDTH + BORDER), BORDER + HEIGHT*(WINDOW_HEIGHT + BORDER));
			bg.setFilled(true);
			bg.setColor(BGCOLOR);
			bb.add(bg);
		
		for(int row=0; row<HEIGHT; row++) {
			for(int column=0; column<WIDTH; column++) {
				int x = BORDER + column * (WINDOW_WIDTH + BORDER);
				int y = BORDER + row * (WINDOW_HEIGHT + BORDER);
				GRect wind = new GRect(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
				wind.setFilled(true);
				
				wind.setColor(frame.getPixel(column, row, WIN_OFF_CLR));
				
				bb.add(wind);
			}		
		}	
			
		removeAll();
		add(bb);
	}
	
}
