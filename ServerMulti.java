
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.w3c.dom.Document;

/**
 * @author peter
 * @version Jun 23, 2016
 */
public class ServerMulti
{
	ServerSocket serverSocket = null; //the object that is linking the server to the client
	Socket clientSocket = null;
	ArrayList<ThreadWithOneClient> thList = new ArrayList<ThreadWithOneClient>(); //in an array list so the server could connect to many clients at the same time
	private String chartStr; // champions chart in String
	private Score[] chartArr = new Score[10]; // champions chart in a Score array

	public static void main (String []args)
	{
		try
		{
			ServerMulti sm = new ServerMulti();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public ServerMulti() // constructor
	{
		
		try
		{
			serverSocket = new ServerSocket(2003); 
			System.out.println("I am online"); 
		}
		catch(IOException e)
		{
			System.err.println("Could not listen on port 2003");
		}
		int count = 0;
		while(true) // running over all the array and connects the server to the client
		{
			try
			{
				clientSocket = serverSocket.accept(); // sets the socket of the client to the socket that got accepted by the server's socket
				thList.add(new ThreadWithOneClient(this)); // each cell gets a new thread 
				thList.get(count).setClientSocket(clientSocket); //משנה את הצינור של הלקוח כדי שיוכל להתחבר לצינור של השרת
				Thread th = new Thread(thList.get(count));
				th.start(); //thread מתחילה את ה
				System.out.println("Server: Connection accepted");
				count++; 
			}
			catch(IOException e)
			{
				System.err.println("Accept failed");
			}
		}	
	}
	
	public ArrayList<ThreadWithOneClient> getThList()
	{
		return this.thList;
	}

	public void setThList(ArrayList<ThreadWithOneClient> thList)
	{
		this.thList = thList;
	}

	public Score[] getChartArr()
	{
		return this.chartArr;
	}

	public void setChartArr(Score[] chartArr)
	{
		this.chartArr = chartArr;
	}

	public String getChartStr()
	{
		return this.chartStr;
	}

	public void setChartStr(String chartStr)
	{
		this.chartStr = chartStr;
	}

}
