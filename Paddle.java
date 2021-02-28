import java.awt.Color;

/**
 * @author user
 * @version 22/06/2016
 */
public class Paddle
{
	private Point location; // the current location of the left upper corner of the paddle
	private Color color; // the color of the paddle
	private int height; // the height of the paddle
	private int width; // the width of the paddle
	private int countR, countL; // auxiliary variable for the paddle's motion
	
	/**
	 * @param boardWidth: the width of the board
	 * @param boardHeight: the height of the board
	 */
	public Paddle(int boardWidth, int boardHeight) // constructor
	{
		this.color = Color.white;
		this.height = 20;
		this.width = 100;
		this.countR = 0;
		this.countL = 0;
		this.location = new Point((boardWidth - this.width) / 2, boardHeight - 150);
	}
	/**
	 * @param rightBorder: the value of the limit of the border
	 */
	public void moveRight(int rightBorder)
	{
		if(this.getLocation().getX() < rightBorder - this.getWidth() )
		{
			this.countL = 0;
			this.countR++;
			this.location.setX(this.getLocation().getX() + 3 + this.countR);
		}
	}
	public void moveLeft()
	{
		// moves the the x location of the location 
		if(this.getLocation().getX() > 0)
		{
			this.countL++;
			this.countR = 0;
			this.location.setX(this.getLocation().getX() - 3 - this.countL);
		}
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
	public Point getLocation()
	{
		return this.location;
	}
	public Color getColor()
	{
		return this.color;
	}
	public int getCountR()
	{
		return this.countR;
	}
	public int getCountL()
	{
		return this.countL;
	}
	public void setCountR(int countR)
	{
		this.countR = countR;
	}
	public void setCountL(int countL)
	{
		this.countL = countL;
	}
	public void setLocation(Point location)
	{
		this.location = location;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public int getHeight()
	{
		return this.height;
	}
	public void setHeight(int height)
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
