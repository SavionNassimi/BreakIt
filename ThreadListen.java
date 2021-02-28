import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author peter
 * @version Jun 23, 2016
 */
public class ThreadListen implements Runnable
{
	private BufferedReader in = null;
	private Socket socket;
	private ChampionsChart cc;

	/**
	 * @param socket: the socket that connects the client to the server
	 * @param cc: the champions chart
	 */
	public ThreadListen(Socket socket, ChampionsChart cc) // constructor
	{
		this.socket = socket;
		this.cc = cc;
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		String str;
		try 
		{
			while ((str = in.readLine())!= null) 
			{
				this.cc.setStrChart(str);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public BufferedReader getIn()
	{
		return this.in;
	}
	public void setIn(BufferedReader in)
	{
		this.in = in;
	}
	public Socket getSocket()
	{
		return this.socket;
	}
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
}
