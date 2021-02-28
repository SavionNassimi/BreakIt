import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * @author user
 * @version 22/06/2016
 */
public class Ball implements Runnable
{
	private Point location; // the current location of the ball
	private Color color; // the color of the ball
	private Gui gui; // the graphic class
	private int dx; // the direction of the ball's motion of x
	private int dy; // the direction of the ball's motion of y
	private double sx; // angle's component of x
	private double sy; // angle's component of y
	private boolean flag = true; // the boolean variable of the run function (true: the thread is running, false: the thread is stopped)

	/**
	 * Ball class is the class of the ball in the game.
	 * the ball is a thread which runs during the run of the Game class.
	 * the Ball class is included in the Gui class.
	 * written by: Savion Nassimi
	 */
	public Ball() // constractor
	{
		this.color = Color.white;
		location = new Point(0,0);
		do {
			this.dx = (int)(Math.random()*3) - 1;
		} while (this.dx == 0);
		// setting the direction of the ball's randomly. cannot be 0, because it means no direction 
		this.dy = -1; // at the beginning the ball is moving upward
		this.sx = 1;
		this.sy = 1;
		// gui's setting is only by the function "setGui(Gui gui)"
	}

	/**
	 * @param multiplierX: the variable that multipliers the x object to change it 
	 * @param multiplierY; the variable that multipliers the y object to change it
	 */
	public void setD(int multiplierX, int multiplierY) 
	{
		// setting the direction of the ball's motion; x and y together, by multiplying the directions.
		this.dx = dx * multiplierX;
		this.dy = dy * multiplierY;
	}
	public void run()
	{
		while (flag)
		{
			// ball's next step
			this.setLocation(this.getLocation().getX() + (sx * dx), this.getLocation().getY() + (sy * dy));
			try 
			{
				Thread.sleep(8);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			// repainting (the checking is included in the repaint function)
			this.gui.repaint();
		}
	}
	/**
	 * @param x: the x variable in the location
	 * @param y; the y variable in the location
	 */
	public void setLocation(double x, double y)
	{
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public Gui getGui() 
	{
		return gui;
	}
	public void setGui(Gui gui) 
	{
		this.gui = gui;
	}
	public Point getLocation()
	{
		return this.location;
	}
	public Color getColor()
	{
		return this.color;
	}
	public double getSx()
	{
		return this.sx;
	}
	public double getSy()
	{
		return this.sy;
	}
	public int getDx()
	{
		return this.dx;
	}
	public int getDy()
	{
		return this.dy;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}

	public boolean isFlag()
	{
		return this.flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
}
