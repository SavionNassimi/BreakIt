import java.awt.Color;

public class Brick
{
	private Point location; // the current location of the brick
	private Color color; // the color of the brick
	private int hits; // the number of the hits the brick can "handle" before braking
	public int height; // the height of the brick
	private int width; // the width of the brick
	
	/**
	 * @param location: the location of the brick
	 * @param color: the color of the brick 
	 * @param hits: the number of hits that are required to break the brick (it depends on the color of the brick) 
	 */
	public Brick(Point location, Color color, int hits) // constructor
	{
		this.location = location;
		this.color = color;
		this.hits = hits;
		this.height = 20;
		this.width = 100;
	}
	public void hit()
	{
		// this function is called when the ball hits the brick
		this.setHits(this.getHits() -1);
	}
	
	public Point getLocation()
	{
		return this.location;
	}
	public Color getColor()
	{
		return this.color;
	}
	public int getHits()
	{
		return this.hits;
	}
	public void setLocation(Point location)
	{
		this.location = location;
	}
	public void setLocation(int x, int y)
	{
		this.setLocation(new Point(0, 0));
		this.location.setX(x);
		this.location.setY(y);
	}
	public void setLocation(double x, double y)
	{
		this.setLocation(new Point(0, 0));
		this.location.setX(x);
		this.location.setY(y);
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public void setHits(int hits)
	{
		this.hits = hits;
	}	
	public int getHeight()
	{
		return this.height;
	}
	public void setHieght(int height)
	{
		this.height = height;
	}
	public int getWidth()
	{
		return this.width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	
}
