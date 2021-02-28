
/**
 * @author peter
 * @version Jun 23, 2016
 */
public class Point 
{
	private double x;
	private double y;
	
	/**
	 * @param x: x variable of the point
	 * @param y: y variable of the point
	 */
	public Point(double x, double y) // constructor
	{
		this.x = x;
		this.y = y;
	}
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public int getIntX()
	{
		long temp = (long)this.x;
		return (int)temp;
	}
	public int getIntY()
	{
		long temp = (long)this.y;
		return (int)temp;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setY(double y)
	{
		this.y = y;
	}

	public String toString()
	{
		return ("x = " + this.x + "\t" + "y = " + this.y);
	}
}
