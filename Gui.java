import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * the playing window
 * @author user
 * @version 22/06/2016
 */
public class Gui extends JPanel implements KeyListener, ActionListener
{ 
	private JFrame f = new JFrame(); // the frame
	private JButton paddle = new JButton(); // the paddle
	private Graphics g;
	private Board board; // the board of the game
	private JLabel lScore = new JLabel("", SwingConstants.CENTER); // the label that is showing the player's score
	private Thread th; // the thread of the ball
	private JFrame f2; // the frame when the user loses or wins
	private JPanel p2; // the panel when the user loses or wins
	private JButton b1; // one of the buttons when the user loses
	private JButton b2; // one of the buttons when the user loses
	private JButton b3; // insert
	private JButton b4; // keep playing
	private JPanel Ps; // panel that is added on p2 at the south
	private JFrame f3 = new JFrame(); // the frame where the player writes its name
	private JPanel p3 = new JPanel(); 
	private JLabel l = new JLabel();
	private JLabel l1; // the label which shows the score (when inserting it to the champions chart) on f3
	private JTextField txt = new JTextField("enter your name");
	private JButton enter = new JButton("enter");
	private JButton back = new JButton("back");
	private Score current;
	private ChampionsChart cc;
	private boolean flag = false; // true: the game has started,	false: the game has not started yet

	/**
	 * @param score: the initial score of the player (in case the player has already won and wants to keep playing, the initial score will be the one he had at the end of the previous game)
	 * @param cc: the champions chart
	 */
	public Gui(int score, ChampionsChart cc) // constructor
	{
		this.cc = cc;
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setLayout(null);
		{
			this.board = new Board(8, 8, score);
			board.getBall().setGui(this);
			this.th = new Thread(board.getBall());
			paddle.setBounds(board.getPaddle().getLocation().getIntX(), this.board.getPaddle().getLocation().getIntY(), this.board.getPaddle().getWidth(), this.board.getPaddle().getHeight());
			this.add(paddle);
		} // setting the board and its variable
		{
			lScore.setBounds(0, this.board.getHeight() - 90, 100, 50);
			lScore.setFont(new Font("Arial", Font.PLAIN, 30));
			lScore.setForeground(Color.white);
			Border b = BorderFactory.createLineBorder(Color.white, 5);
			lScore.setBorder(b);
			lScore.setBackground(Color.black);
			this.add(lScore);
		} // setting the score graphic

		f.add(this);
		f.setSize(this.board.getWidth(), this.board.getHeight());
		f.setLocation(200, 50);
		f.setVisible(true);

		{
			this.f2 = new JFrame();
			this.p2 = new JPanel();
			this.p2.setLayout(new BorderLayout());
			this.Ps = new JPanel();
			this.Ps.setLayout(new BorderLayout());
			this.b1 = new JButton("ok");
			this.b2 = new JButton("start a new game");
			this.b3 = new JButton("insert your score to champions chart");
			this.b1.addActionListener(this);
			this.b2.addActionListener(this);
			this.b3.addActionListener(this);
		} // setting the variables
		{
			this.Ps.add(b1, BorderLayout.NORTH);
			this.Ps.add(b2, BorderLayout.CENTER);
			this.Ps.add(b3, BorderLayout.SOUTH);
			this.p2.add(l , BorderLayout.NORTH);
			this.p2.add(Ps, BorderLayout.CENTER);
		} // adding the buttons and label and their supplement panel

		this.f2.add(p2);
		this.f2.setSize(320, 150);
		long tempx = (long)(this.f.getLocation().getX() + ((this.f.getWidth() - this.f2.getWidth()) / 2)), tempy = (long)(this.f.getLocation().getY() + ((this.f.getHeight() - this.f2.getHeight()) / 2));
		int x = (int)tempx, y = (int)tempy;
		this.f2.setLocation(x, y);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//setting background
		Image img = getToolkit().getImage("galaxy.jpg");
		g.drawImage(img, 0, 0, this.board.getWidth(), this.board.getHeight(), null);
		
		//bricks
		for(int i = 0; i < this.board.getBricks().getArr().length; i++)
		{
			for(int j = 0; j < this.board.getBricks().getArr()[0].length; j++)
			{
				if(this.board.getBricks().getArr()[i][j].getHits() > 0)
				{
					switch (this.board.getBricks().getArr()[i][j].getHits())
					{
					case 1:
						g.setColor(new Color(255, 0, 255));
						break;
					case 2:
						g.setColor(Color.cyan);
					default:
						break;
					}
					g.fillRoundRect(this.board.getBricks().getArr()[i][j].getLocation().getIntX(), this.board.getBricks().getArr()[i][j].getLocation().getIntY(), this.board.getBricks().getArr()[i][j].getWidth(), this.board.getBricks().getArr()[i][j].getHeight(), 10, 10);
				}
			}
		}
		
		//checking if the ball hits something
		board.checkHit();
		
		//score updating
		String str = ""; str += this.board.getScore();
		this.lScore.setText(str);
		
		//ball
		g.setColor(this.board.getBall().getColor());
		g.fillOval(this.board.getBall().getLocation().getIntX(), this.board.getBall().getLocation().getIntY(), 15, 15);

		if (!th.isAlive())
		{
			repaint();
		}
		else
		{
			//lose & win
			if(this.board.lose())
			{
				this.th.stop();

				this.l.setText("boohoo! you lost!");
				this.f2.setVisible(true);
			}
			else if(this.board.win())
			{
				this.th.stop();
				
				this.b4 = new JButton("keep playing");
				this.b4.addActionListener(this);
				this.p2.add(b4, BorderLayout.SOUTH);
				this.l.setText("yay!!! you won!");
				this.f2.setSize(320, 188);
				this.f2.setVisible(true);
			}
		}
	}

	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case(39): //right
		{
			this.board.getPaddle().moveRight(this.board.getWidth());
			this.paddle.setLocation(this.board.getPaddle().getLocation().getIntX(), this.board.getPaddle().getLocation().getIntY());
			if(!flag)
			{
				this.board.getBall().setLocation(paddle.getLocation().getX() + (paddle.getWidth()) / 2 - 10, this.paddle.getLocation().getY() - 16);
			}
			break;
		}
		case(37): //left
		{
			this.board.getPaddle().moveLeft();
			this.paddle.setLocation(this.board.getPaddle().getLocation().getIntX(), this.board.getPaddle().getLocation().getIntY());
			if(!flag)
			{
				this.board.getBall().setLocation(paddle.getLocation().getX() + (paddle.getWidth()) / 2 - 10, this.paddle.getLocation().getY() - 16);
			}
			break;
		}
		case(32): //space
		{
			if(!flag)
			{
				this.flag = true;
				this.th.start();
			}
			break;
		}
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		this.board.getPaddle().setCountR(0);
		this.board.getPaddle().setCountL(0);
	}
	public void keyTyped(KeyEvent e) 
	{
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		JButton obtained = (JButton)arg0.getSource();
		if(obtained == this.b1) // ok
		{
			this.f2.setVisible(false);
			this.f.setVisible(false);
		}
		else if(obtained == this.b2) // new game
		{
			this.f2.setVisible(false);
			this.f.setVisible(false);
			Gui newGame = new Gui(0, this.cc);
		}
		else if(obtained == this.b3) // insert score (opens window)
		{
			this.f2.setVisible(false);
			
			this.l1 = new JLabel("your score is: " + this.lScore.getText(), SwingConstants.CENTER);
			l1.setFont(new Font("Arial", Font.PLAIN, 20));
			this.enter.addActionListener(this);
			
			this.p3.setLayout(new BorderLayout());
			this.p3.add(l1, BorderLayout.NORTH);
			this.p3.add(txt, BorderLayout.CENTER);
			this.p3.add(enter, BorderLayout.SOUTH);
			//setting the panel
			this.f3.add(p3);
			this.f3.setSize(320, 120);
			long tempx = (long)(this.f.getLocation().getX() + ((this.f.getWidth() - this.f3.getWidth()) / 2)), tempy = (long)(this.f.getLocation().getY() + ((this.f.getHeight() - this.f3.getHeight()) / 2));
			int x = (int)tempx, y = (int)tempy;
			this.f3.setLocation(x, y);
			this.f3.setVisible(true);
			// setting the frame
		}
		else if(obtained == b4) // keep playing
		{
			this.f2.setVisible(false);
			this.f.setVisible(false);
			Gui newGame = new Gui(this.board.getScore(), this.cc);

		}
		else if(obtained == enter) // insert to champions chart
		{
			this.f2.setVisible(false);
			this.f3.setVisible(false);
			
			this.back.addActionListener(this);
			current = new Score(txt.getText(), this.board.getScore());
			this.cc = new ChampionsChart(current, back);
		}
		else if(obtained == back)
		{
			this.f2.setVisible(true);
			this.cc.getF().setVisible(false);
		}
	}

	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}

	public JButton getPaddle() {
		return paddle;
	}

	public void setPaddle(JButton paddle) {
		this.paddle = paddle;
	}

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Thread getTh() {
		return th;
	}

	public void setTh(Thread th) {
		this.th = th;
	}

	public JFrame getF2() {
		return f2;
	}

	public void setF2(JFrame f2) {
		this.f2 = f2;
	}

	public JPanel getP2() {
		return p2;
	}

	public void setP2(JPanel p2) {
		this.p2 = p2;
	}

	public JButton getB1() {
		return b1;
	}

	public void setB1(JButton b1) {
		this.b1 = b1;
	}

	public JButton getB2() {
		return b2;
	}

	public void setB2(JButton b2) {
		this.b2 = b2;
	}

	public Score getCurrent()
	{
		return this.current;
	}

	public void setCurrent(Score current)
	{
		this.current = current;
	}
	public ChampionsChart getCc() 
	{
		return cc;
	}

	public void setCc(ChampionsChart cc) 
	{
		this.cc = cc;
	}
}
