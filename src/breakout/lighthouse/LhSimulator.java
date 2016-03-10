package breakout.lighthouse;

import java.awt.Color;
import java.awt.event.KeyEvent;

import breakout.assets.PixelImage;
import breakout.assets.BreakoutConstants;
import acm.graphics.*;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class LhSimulator extends GraphicsProgram {
	
	/* Number of Windows per row and column */
	private static final int WIDTH = 28;
	private static final int HEIGHT = 14;
	
	/*Height and Width of each Window and the distance between those */
	private static final int WINDOW_WIDTH = BreakoutConstants.WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT = BreakoutConstants.WINDOW_HEIGHT;
	private static final int BORDER_X = BreakoutConstants.WINDOW_X_OFFSET;
	private static final int BORDER_Y = BreakoutConstants.WINDOW_Y_OFFSET;
	
	private static final Color BGCOLOR = new Color(32,32,32);
	private static final Color WIN_OFF_CLR = new Color(0,0,0);
	
	private static final int WTF_OFFSET = 62; //no further explanation needed. DEAL WITH IT
	
	private boolean leftArrowKeyPressed = false;
	private boolean rightArrowKeyPressed = false;
	
	/**
	 * Sets the size and sets the Focus on the Window
	 */
	public void run() {		
		setSize(BORDER_X + WIDTH*(WINDOW_WIDTH + BORDER_X) + WTF_OFFSET - 42 -3, BORDER_Y + HEIGHT*(WINDOW_HEIGHT + BORDER_Y) + WTF_OFFSET);
		validate();
		
		requestFocus();
		requestFocusInWindow();
	}
	
	/**
	 * Draws the given PixelImage to a GCompound
	 * @param frame the PixelImage to draw 
	 */
	public void draw(PixelImage frame) {
	
		GCompound bb = new GCompound();
		
		/* Creates the background */
			GRect bg = new GRect(0, 0, BORDER_X + WIDTH*(WINDOW_WIDTH + BORDER_X), BORDER_Y + HEIGHT*(WINDOW_HEIGHT + BORDER_Y));
			bg.setFilled(true);
			bg.setColor(BGCOLOR);
			bb.add(bg);
			
		/* Draws the windows (rectangles) for each row and column */
		for(int row=0; row<HEIGHT; row++) {
			for(int column=0; column<WIDTH; column++) {
				int x = BORDER_X + column * (WINDOW_WIDTH + BORDER_X);
				int y = BORDER_Y + row * (WINDOW_HEIGHT + BORDER_Y);
				GRect wind = new GRect(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
				wind.setFilled(true);
				
				/* The rectangle gets the Color from the PixelImage */
				Color c = frame.getPixel(column, row, WIN_OFF_CLR);
				if(c.getAlpha() < 255) { c = PixelImage.blendColors(Color.BLACK, c); }
				
				wind.setColor(c);
				
				bb.add(wind);
			}		
		}
		
		/* make acm paint our generated gcompound */
		removeAll();
		add(bb);
	}
	
	public boolean isLeftArrowKeyPressed(){
		return this.leftArrowKeyPressed;
	}
	
	public boolean isRightArrowKeyPressed(){
		return this.rightArrowKeyPressed;
	}
	
	/**
	 * Adds a new KeyListener and requests Focus
	 */
	public void init(){
		addKeyListeners();
		requestFocus();
	}
	
	/**
	 * Checks if LeftArrowKey and RightArrowKey are Pressed and 
	 * sets the boolean leftArrowKeyPressed and rightArrowKeyPressed on true
	 */
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				leftArrowKeyPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightArrowKeyPressed = true;
				break;
		}
	}
	
	/**
	 * Checks if LeftArrowKey and RightArrowKey are released and sets 
	 * the boolean leftArrowKeyPressed and rightArrowKeyPressed on false
	 */
	public void keyReleased(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				leftArrowKeyPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightArrowKeyPressed = false;
				break;
		}
	}
	
}
