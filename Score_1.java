
/**
 * @author peter
 * @version Jun 23, 2016
 */
public class Score
{
	private String name; 
	private int points;
	
	public Score(String name, int points)
	{
		this.name = name;
		this.points = points;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getPoints()
	{
		return this.points;
	}
	public void setPoints(int points)
	{
		this.points = points;
	}
	public String toString()
	{
		return this.name + "\t" + this.points + "\n";
	}
	public String splitString()
	{
		return this.name + "_" + this.points;
	}
}
