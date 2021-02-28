import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author user
 * @version 22/06/2016
 */
public class Client 
{
	private Socket socket = null;
	private BufferedWriter out = null; 
	private ThreadListen tl;
	private ChampionsChart cc;

	/**
	 * @param cc: the champions chart.
	 * the client gets the current score of the player (which is inserted in the champions chart) from the champions chart
	 * and gives it the updated chart (the it got form the server after sending the current score and inserting it in the chart)
	 */
	public Client(ChampionsChart cc) // constructor
	{
		this.cc = cc;
		try
		{
			socket = new Socket("127.0.0.1",2003);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			tl = new ThreadListen(socket, cc);
			Thread th = new Thread(tl);
			th.start();
			//write();
		}
		catch (Exception e) 
		{
			System.err.println("connection failed");
		}
	}

	/**
	 * @param str: the score the client sends the server in the type of string. the name and points are divided by "_"
	 */
	public void sendScore(String str)
	{
		try 
		{
			out.write(str + "\n");
			out.flush();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
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