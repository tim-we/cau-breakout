package breakout.items;
import breakout.assets.*;
import breakout.physics.*;

public class Paddle extends PhysicsObject{

private double paddleWidth = 4*PIXELWIDTH;

public Paddle(){
  this.Position = new Vector2D(((28/2)-2)*PIXELWIDTH,13*PIXELHEIGHT)
}

public setPosition (double x){
  Position.setX(x);
}






}
