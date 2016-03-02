package breakout.items;

public class Brick extends PhysicsObject {

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

public getBrickType (){
  return this.brickType;
}

@Override
public void onCollision(PhysicsObejct obj){
  brickType--;
}


}
