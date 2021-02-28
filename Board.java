import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * @author user
 * @version 22/06/2016
 */
public class Board
{
	private Ball ball; // the ball 
	private Paddle paddle; // the paddle
	private BricksGrid bricks; // the matrix of bricks
	private int height; // the height of the board's panel
	private int width; // the width of the board's panel
	private int score; // the score of the player
	
	/**
	 * @param a: the number of column
	 * @param b: the number of lines
	 * @param score: the initial score (in case the player has already won and wants to keep playing, the initial score will be the one he had at the end of the previous game)
	 */
	public Board(int a, int b, int score) // constructor
	{
		this.ball = new Ball();
		this.height = 650;
		this.width = 1000;
		this.paddle = new Paddle(this.width, this.height);
		this.bricks = new BricksGrid(a, b);
		this.bricks.setLocationsI(this.width);
		this.score = score;
		ball.setLocation(paddle.getLocation().getX() + (paddle.getWidth()) / 2 - 10, this.paddle.getLocation().getY() - 16);
	}
	public void checkHit()
	{
		// checks if the ball is hitting one of the other objects on the board (bricks or the paddle) or the border of the frame
		// includes 3 functions. every object that required checking has its own function
		if(!this.checkBorders())
		{
			if(!this.checkBricksLocation())
			{
				checkpaddle();
			}
		}
	}

	private boolean checkBorders()
	{
		// chekcs if the ball hits specifically one of the frame's border 
		if(this.ball.getLocation().getX() == 0 || this.ball.getLocation().getX() >= this.width - 15 || this.ball.getLocation().getX() + 15 >= this.width - 15)
		{
			ball.setD(-1, 1);
			return true;
		}
		if(this.ball.getLocation().getY() == 0 || this.ball.getLocation().getY() + 15 == 0)
		{
			ball.setD(1, -1);
			return true;
		}
		return false;
	}

	private boolean checkBricksLocation()
	{
		//chekcs if the ball hits specifically one of the bricks in the bricks grid 
		for(int i = 0; i < bricks.getArr().length; i++)
		{
			for(int j = 0; j < bricks.getArr()[0].length; j++)
			{
				if(this.getBricks().getArr()[i][j].getHits() > 0)
				{
					if((this.ball.getLocation().getX() >= bricks.getArr()[i][j].getLocation().getX() && this.ball.getLocation().getX() <= bricks.getArr()[i][j].getLocation().getX() + (bricks.getArr()[i][j].getWidth())) || (this.ball.getLocation().getX() + 15 >= bricks.getArr()[i][j].getLocation().getX() && this.ball.getLocation().getX() + 15 <= bricks.getArr()[i][j].getLocation().getX() + (bricks.getArr()[i][j].getWidth())))
					{
						if((this.ball.getLocation().getY() >= bricks.getArr()[i][j].getLocation().getY() && this.ball.getLocation().getY() <= bricks.getArr()[i][j].getLocation().getY() + bricks.getArr()[i][j].getHeight()) || (this.ball.getLocation().getY() + 15 >= bricks.getArr()[i][j].getLocation().getY() && this.ball.getLocation().getY() + 15 <= bricks.getArr()[i][j].getLocation().getY() + bricks.getArr()[i][j].getHeight()))
						{
							switch (this.bricks.getArr()[i][j].getHits())
							{
							case 1:
								this.score += 100;
								break;
							case 0:
								this.score += 200;
							default:
								break;
							}
							bricks.getArr()[i][j].hit();
							ball.setD(1, -1);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private void checkpaddle()
	{
		 //chekcs if the ball hits specifically the paddle
		if ((this.ball.getLocation().getY() >= this.paddle.getLocation().getY() && this.ball.getLocation().getY() <= this.paddle.getLocation().getY() + this.paddle.getHeight() - 2) || (this.ball.getLocation().getY() + 15 >= this.paddle.getLocation().getY() && this.ball.getLocation().getY() + 15 <= this.paddle.getLocation().getY() + this.paddle.getHeight() - 2))
		{
			if ((ball.getLocation().getX() >= paddle.getLocation().getX() && ball.getLocation().getX() <= paddle.getLocation().getX() + paddle.getWidth()) || (ball.getLocation().getX() + 15 >= paddle.getLocation().getX() && ball.getLocation().getX() + 15 <= paddle.getLocation().getX() + paddle.getWidth()))
			{
					ball.setD(1, -1);
			}
		}
	}

	public boolean win()
	{
		// returns true if the player won, else returns false
		for(int i = 0; i < this.bricks.getArr().length; i++)
		{
			for(int j = 0; j < this.bricks.getArr()[i].length; j++)
			{
				if(this.bricks.getArr()[i][j].getHits() > 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	public boolean lose()
	{
		// returns true if the player lost, else returns false
		if(this.ball.getLocation().getY() == this.getHeight() - 20)
		{
			return true;
		}
		return false;
	}
	
	public Ball getBall() 
	{
		return ball;
	}
	public void setBall(Ball ball) 
	{
		this.ball = ball;
	}
	public Paddle getPaddle()
	{
		return this.paddle;
	}
	public void setPaddle(Paddle paddle)
	{
		this.paddle = paddle;
	}
	public BricksGrid getBricks()
	{
		return this.bricks;
	}
	public void setBricks(BricksGrid bg)
	{
		this.bricks = bg;
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
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
}
