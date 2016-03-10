package breakout.physics;

public class Vector2D {
	
	private double x;
	private double y;
	
	/**
	 * Constructor: Creates a new Vector with given x and y value
	 * @param x - x value
	 * @param y - y value
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor: Creates a Vector with components on the value of the given Vector
	 * @param a - the given Vector
	 */
	public Vector2D(Vector2D a) {
		this.x = a.getX();
		this.y = a.getY();
	}
	
	/**
	 * Constructor: Creates a new Vector with value's 0
	 */
	public Vector2D() {
		x = y = 0;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	
	/**
	 * Scales the Vector
	 * @param factor - the factor to scale with
	 * @return a new Vector which contains the scaled values
	 */
	public Vector2D scale(double factor) {
		return new Vector2D(x * factor, y * factor);
	}
	
	/**
	 * Calculates the length of the Vector
	 * @return the length of the Vector
	 */
	public double length() {
		return Math.sqrt(x*x + y*y);
	}
	
	/**
	 * Calculates the square of the length of the Vector
	 * @return return the square of the length of the Vector
	 */
	public double sqlength() {
		return x*x + y*y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Inverses the Vector
	 * @return a new Vector which contains the values scaled by -1
	 */
	public Vector2D inverse() {
		return new Vector2D(this.scale(-1));
	}
	
	/**
	 * Vector addition of two given Vectors
	 * @param a - first Vector
	 * @param b - second Vector
	 * @return a new Vector which components contain the values of a and b summarized
	 */
	public static Vector2D add(Vector2D a, Vector2D b) {
		return new Vector2D(a.getX() + b.getX(), a.getY() + b.getY());
	}
	
	/**
	 * Vector substraction of two given Vectors
	 * @param a - first Vector
	 * @param b - second Vector
	 * @return a new Vector which components contain the values of a and b substracted
	 */
	public static Vector2D subtract(Vector2D a, Vector2D b) {
		return new Vector2D(a.getX() - b.getX(), a.getY() - b.getY());
	}
	
	/**
	 * Returns the Vector as String
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
