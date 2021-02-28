import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author user
 * @version 22/06/2016
 */
public class ChampionsChart extends JPanel
{
	private Client c = new Client(this);
	private Score score;
	private String strChart;
	private boolean flag = false;
	private Graphics g;
	//graphic variables
	private JButton back;
	private JFrame f = new JFrame();
	private JLabel title = new JLabel("Champions Chart  ", SwingConstants.CENTER);
	private JLabel[] numbers = new JLabel[10];
	private JLabel[] arr = new JLabel[10];
	private JButton close = new JButton("close");
	private JLabel[] chart = new JLabel[10];

	/**
	 * @param score: the score of the player, which is inserted in the champions chart (in the right place)
	 * @param back: the button on the panel. the class needs to get it in the constructor because it can be opened in two different places
	 */
	public ChampionsChart(Score score, JButton back) // first constructor
	{
		this.back = back;
		this.score = score;
		this.c.setCc(this);
		this.strChart = "";
		this.sendScore();
		this.Gui();
	}
	/**
	 * there is no score becuse this constructor is opened when the user presses the champions chart in the openning window
	 * @param back: the button on the panel. (more explanation above)
	 */
	public ChampionsChart(JButton back) // second constructor
	{
		this.back = back;
		this.score = null;
		this.c.setCc(this);
		this.strChart = "";
		this.sendScore();
		this.Gui();
	}

	private void sendScore()
	{
		// sends the score to the server via the client
		if(score == null)
		{
			// in case of the seconds ctor
			c.sendScore(null);
		}
		else
		{
			c.sendScore(score.splitString());
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Image img = getToolkit().getImage("orangeGalaxy.jpg");
		g.drawImage(img, 0, 0, 500, 695, null);

		repaint();
	}
	public void Gui()
	{	
		// the function the sets all the graphic variables
		this.setLayout(null);

		this.back.setBounds(300, 600, 150, 55);
		this.add(back); // setting the back button

		title.setFont(new Font("Arial", Font.PLAIN, 50));
		title.setForeground(Color.white);
		title.setBounds(2, 0, 500, 52);
		this.add(title); // setting the title label

		this.setNumbers();

		if(flag)
		{ // checks if the server has sent the (updated) chart
			this.setChartText();
		}

		this.f.add(this);
		this.f.setVisible(true);
		this.f.setSize(500, 700);
	}
	public String getStrChart()
	{
		return this.strChart;
	}
	/**
	 * this function is called by the client. the client sends the string that the server sent
	 * @param strChart: gets the chart in type of string. the score is divided by "\" and in the score itslef the name and points are divided by "_"
	 */
	public void setStrChart(String strChart)
	{
		this.strChart = strChart;
		flag = true;
		this.Gui(); // calls the gui function again in order to add the chart itself
	}
	public void setNumbers()
	{
		// sets the locations of the numbers of the chart
		for(int i = 0; i < this.numbers.length; i++)
		{	
			this.numbers[i] = new JLabel("" + (i + 1));
			this.numbers[i].setFont(new Font("Arial", Font.PLAIN, 20));
			if(i < 9)
			{
				this.numbers[i].setSize(20, 20);
				this.numbers[i].setLocation(90, 70 + (i * 50));
			}
			else
			{
				this.numbers[i].setSize(50, 50);
				this.numbers[i].setLocation(85, 70 + (i * 50));
			}
			this.numbers[i].setForeground(Color.cyan);
			this.add(this.numbers[i]);
		}
	}

	public void setChartText()
	{
		// sets the chart on the panel from the string that was taken by the client from the server
		String[] chartS = strChart.split("/");
		for(int i = 0; i < chartS.length; i++)
		{
			String[] arri = chartS[i].split("_");
			String stri = arri[0];
			stri += "............................." + arri[1];
			this.chart[i] = new JLabel(stri);
			this.chart[i].setFont(new Font("Arial", Font.PLAIN,20));
			this.chart[i].setSize(400, 50);
			this.chart[i].setLocation(140, 55 + (i * 50));
			this.add(this.chart[i]);
			chart[i].setForeground(Color.cyan);
		}
	}

	public JLabel[] getArr()
	{
		return this.arr;
	}

	public void setArr(JLabel[] arr)
	{
		this.arr = arr;
	}

	public JButton getBack()
	{
		return this.back;
	}

	public void setBack(JButton back)
	{
		this.back = back;
	}

	public Client getC()
	{
		return this.c;
	}

	public void setC(Client c)
	{
		this.c = c;
	}

	public JLabel[] getChart()
	{
		return this.chart;
	}

	public void setChart(JLabel[] chart)
	{
		this.chart = chart;
	}

	public JButton getClose()
	{
		return this.close;
	}

	public void setClose(JButton close)
	{
		this.close = close;
	}

	public JFrame getF()
	{
		return this.f;
	}

	public void setF(JFrame f)
	{
		this.f = f;
	}

	public boolean isFlag()
	{
		return this.flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public Graphics getG()
	{
		return this.g;
	}

	public void setG(Graphics g)
	{
		this.g = g;
	}

	public JLabel[] getNumbers()
	{
		return this.numbers;
	}

	public void setNumbers(JLabel[] numbers)
	{
		this.numbers = numbers;
	}

	public Score getScore()
	{
		return this.score;
	}

	public void setScore(Score score)
	{
		this.score = score;
	}

	public JLabel getTitle()
	{
		return this.title;
	}

	public void setTitle(JLabel title)
	{
		this.title = title;
	}
}
