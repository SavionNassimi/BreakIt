import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.corba.se.impl.orbutil.DenseIntMapImpl;

/**
 * opening window
 * @author user
 * @version 22/06/2016
 */
public class Game extends JPanel implements ActionListener
{
	public static void main(String[] args) 
	{
		Game game = new Game();
	}
	
	private JFrame frame = new JFrame(); // the frame of the opening window of the game
	private JButton[] bs = new JButton[3]; // the buttons on the opening window
	private JButton howTo = new JButton("How To Play");
	private JButton back; // the button that is sent to the champions chart
	private JLabel label = new JLabel(); // the label of the title
	private JLabel title = new JLabel("Break It", SwingConstants.CENTER);
	private Graphics g; // the graphic variable to paint the frame (background)
	private Gui gui; // one live try (the game itself)
	private Image img; // the image of the background
	private ChampionsChart cc; // the champions chart
	JButton hback = new JButton("back");
	JFrame fHow = new JFrame();
	
	public Game() // construtor
	{
		this.setLayout(null);
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setLocation(300, 70); // setting the frame
		
		
		for(int i = 0; i < this.bs.length; i++)
		{
			bs[i] = new JButton();
			bs[i].addActionListener(this);
			bs[i].setFont(new Font("Arial", Font.PLAIN, 25));
			bs[i].setBounds((this.frame.getWidth() - 250) / 2, 300 + 60*i, 250, 50);
			this.add(bs[i]);
		}
		bs[0].setBounds((this.frame.getWidth() - 250) / 2, 240, 250, 50);
		bs[0].setText("play!");
		bs[1].setText("champions chart");
		bs[2].setText("exit");
		this.howTo.setFont(new Font("Arial", Font.PLAIN, 25));
		this.howTo.setBounds((this.frame.getWidth() - 250) / 2, 300, 250, 50);
		this.howTo.addActionListener(this);
		this.add(this.howTo);
		// setting the buttons on the panel
		
		this.title.setFont(new Font("Bauhaus 93", Font.PLAIN, 100));
		this.title.setBounds(0, 50, this.frame.getWidth(), 100);
		this.title.setForeground(Color.cyan);
		this.add(title);
		//setting the title
		
		JPanel pHow = new JPanel(), phs = new JPanel();
		pHow.setBackground(Color.black);
		pHow.setLayout(new BorderLayout());
		Image img = new ImageIcon("instructions.png").getImage();
		ImageIcon img1 = new ImageIcon("instructions.png");
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		pHow.setSize(size);
		JLabel lHow = new JLabel();
		lHow.setIcon(img1);
		hback.addActionListener(this);
		phs.setBackground(Color.black);
		phs.add(hback);
		pHow.add(phs, BorderLayout.SOUTH);
		pHow.add(lHow);
		fHow.add(pHow);
		fHow.setLocation(350, 200);
		fHow.setSize(722, 420);
		//setting the window of the instructions
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//setting background
		Image img = getToolkit().getImage("stars.jpg");
		g.drawImage(img, 0, 0, 800, 600, null);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		JButton obtained = (JButton)e.getSource();
		if(obtained == this.bs[0]) // play
		{
			gui = new Gui(0, this.cc);
		}
		else if(obtained == this.bs[1]) // champions chart
		{
			this.back = new JButton("back");
			this.back.addActionListener(this);
			this.cc = new ChampionsChart(back);
		}
		else if(obtained == this.bs[2]) // exit
		{
			this.frame.setVisible(false);
		}
		else if(obtained == back) // back from the champions chart
		{
			this.cc.getF().setVisible(false);
		}
		else if(obtained == this.howTo) // how to play
		{
			fHow.setVisible(true);
		}
		else if(obtained == hback)
		{
			fHow.setVisible(false);
		}
	}
}
