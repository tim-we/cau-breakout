package breakout.items;

import breakout.assets.*;
import java.awt.Color;
public class Brick extends PhysicsObject {
private Color[] color = {new Color(255,0,0),new Color(0,255,0),new Color(0,0,255)};
private byte brickType;
private static final brickWidth = 2*PixelSizes.PIXELWIDTH;
private static final brickHeight = PixelSizes.PIXELHEIGHT;

public Brick(double x, double y, byte brickType){
this.Position = new Vector2D(x,y);
setBBox(brickWidth,brickHeight);
this.brickType = brickType;
}

public setBrickType (byte brickType){
  this.brickType = brickType;
}

public byte getBrickType (){
  return this.brickType;
}

public Color getColor(){
  return this.color[brickType];
}



@Override
public void onCollision(PhysicsObejct obj){
  brickType--;
}


}
