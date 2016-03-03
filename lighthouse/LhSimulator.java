package breakout.lighthouse;

import java.awt.Color;

import breakout.assets.PixelImage;
import breakout.assets.BreakoutConstants;
import acm.graphics.*;
import acm.program.GraphicsProgram;

public class LhSimulator extends GraphicsProgram {

	private static final int WIDTH = 28;
	private static final int HEIGHT = 14;
	
	private static final int WINDOW_WIDTH = BreakoutConstants.WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT = BreakoutConstants.WINDOW_HEIGHT;
	private static final int BORDER_X = BreakoutConstants.WINDOW_X_OFFSET;
	private static final int BORDER_Y = BreakoutConstants.WINDOW_Y_OFFSET;
	
	private static final Color BGCOLOR = new Color(32,32,32);
	private static final Color WIN_OFF_CLR = new Color(0,0,0);
	
	private static final int WTF_OFFSET = 62; //no further explanation needed. DEAL WITH IT
	
	public void run() {		
		setSize(BORDER_X + WIDTH*(WINDOW_WIDTH + BORDER_X) + WTF_OFFSET - 42 -3, BORDER_Y + HEIGHT*(WINDOW_HEIGHT + BORDER_Y) + WTF_OFFSET);
		validate();
	}
	
	public void draw(PixelImage frame) {
	
		GCompound bb = new GCompound();
		
		//background
			GRect bg = new GRect(0, 0, BORDER_X + WIDTH*(WINDOW_WIDTH + BORDER_X), BORDER_Y + HEIGHT*(WINDOW_HEIGHT + BORDER_Y));
			bg.setFilled(true);
			bg.setColor(BGCOLOR);
			bb.add(bg);
		
		for(int row=0; row<HEIGHT; row++) {
			for(int column=0; column<WIDTH; column++) {
				int x = BORDER_X + column * (WINDOW_WIDTH + BORDER_X);
				int y = BORDER_Y + row * (WINDOW_HEIGHT + BORDER_Y);
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
