package breakout.items;
import breakout.assets.*;
import breakout.physics.*;
import java.awt.Color;
public class Paddle extends PhysicsObject{

private double paddleWidth = 4*PIXELWIDTH;

private Color color = new Color (142,33,125);

public Paddle(){
  this.Position = new Vector2D(((28/2)-2)*PIXELWIDTH,13*PIXELHEIGHT)
}

public setPosition (double x){
  Position.setX(x);
}

public getWidth(){
  return this.paddleWidth;
}




}
