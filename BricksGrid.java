import java.awt.Color;

/**
 * @author user
 * @version 22/06/2016
 */
public class BricksGrid
{
	private Brick[][] arr; // the bricks grid

	/**
	 * @param x: the number of the columns in the grid
	 * @param y: the number of lines in the grid
	 */
	public BricksGrid(int x, int y) // constructor
	{
		Color color;
		this.arr = new Brick[x][y];
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				int hit;
				if((j + 1) % 3 != 0)
				{
					color = new Color(255, 0, 255);
					hit = 1;
				}
				else
				{
					color = Color.CYAN;
					hit = 2;
				}
				arr[i][j] = new Brick(null, color, hit);
			}
		}
	}
	public Brick[][] getArr()
	{
		return this.arr;
	}

	public void setLocationsI(int boardWidth)
	{
		// sets the locations of every brick in the grid
		int numberOfBricks = this.arr.length,
			bricksWidth = this.arr[0][0].getWidth(),
			bricksHeight = this.getArr()[0][0].getHeight(),
			betweenW = 10,
			betweenH = 15,
			margin = (boardWidth - ((numberOfBricks * bricksWidth) + (betweenW * (numberOfBricks - 1)))) / 2;
		double x, y;
		{
			for(int i = 0; i < arr.length; i++)
			{
				for(int j = 0; j < arr[i].length; j++)
				{
					x = margin + i * (bricksWidth + betweenW);
					y = 50 + j * (bricksHeight + betweenH);
					this.arr[i][j].setLocation(x, y);
				}
			}
		}
	}
}
