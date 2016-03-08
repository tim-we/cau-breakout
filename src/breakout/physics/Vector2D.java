package breakout.physics;

public class Vector2D {
	
	private double x;
	private double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D a) {
		this.x = a.getX();
		this.y = a.getY();
	}
	
	public Vector2D() {
		x = y = 0;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	
	public Vector2D scale(double factor) {
		return new Vector2D(x * factor, y * factor);
	}
	
	public double length() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double sqlength() {
		return x*x + y*y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public Vector2D inverse() {
		return new Vector2D(this.scale(-1));
	}
	
	public static Vector2D add(Vector2D a, Vector2D b) {
		return new Vector2D(a.getX() + b.getX(), a.getY() + b.getY());
	}
	
	public static Vector2D subtract(Vector2D a, Vector2D b) {
		return new Vector2D(a.getX() - b.getX(), a.getY() - b.getY());
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
