import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author peter
 * @version Jun 23, 2016
 */
public class ThreadWithOneClient implements Runnable
{
	private BufferedWriter out;
	private BufferedReader in;
	private Socket clientSocket = null;
	private ServerMulti server;
	private connectFile cf;

	/**
	 * @param server: the server that includes this (array of) thread(s) 
	 */
	public ThreadWithOneClient(ServerMulti server) // constructor
	{
		this.server = server;
	}

	public void run()
	{
		try
		{
			listenClient();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void listenClient() throws IOException
	{
		String client_line;
		while ((client_line = in.readLine())!= null)
		{
			this.sendScoreChart(client_line);
		}
	}

	public Socket getClientSocket()
	{
		return this.clientSocket;
	}

	public void setClientSocket(Socket clientSocket)
	{                                          
		this.clientSocket = clientSocket;

		try
		{
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void sendScoreChart(String str)
	{
		try
		{
			this.updateChart(str);
			out.write(this.chartStr() + "\n");
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void updateChart(String str) throws IOException
	{
		Score[] arr = new Score[10];
		//this.server.setChartArr(arr);
		this.server.setChartArr(this.readFromXml());
		arr = server.getChartArr();
		boolean a = (str == null);
		if(!str.contains("_"))
		{
			this.cf = new connectFile(arr);
			this.server.setChartArr(arr);
		}
		else
		{
			String[] strArr = str.split("_");
			Score before = null , save = null, score = new Score(strArr[0],Integer.valueOf(strArr[1]));
			boolean flag = false, running = true;
			if(arr[0] == null || arr[0].getPoints() < score.getPoints())
			{
				flag = true;
				if(arr[1] != null)
				{
					save = arr[0];
				}
				arr[0] = score;
				for(int i = 2; i < arr.length; i++)
				{
					arr[i - 1] = arr[i];
				}
			}
			else
			{
				for(int i = 0; i < arr.length - 1 && running; i++)
				{
					if(flag)
					{
						before = save;
						if(arr[i + 1] == null)
						{
							running = false;
						}
						else
						{
							save = arr[i+1];
						}
						arr[i+1] = before;
					}
					else
						if(arr[i] != null)
						{
							if(arr[i+1] != null)
							{
								if(arr[i].getPoints() > score.getPoints())
								{
									if(arr[i+1].getPoints() < score.getPoints())
									{
										flag = true;
										save = arr[i+1];
										arr[i+1] = score;
									}
								}
							}
							else
							{
								flag = true;
								arr[i+1] = score;
							}
						}
						else
						{
							arr[i] = score;
						}
				}
			}
			this.cf = new connectFile(arr);
			this.server.setChartArr(arr);
		}
	}

	public String chartStr()
	{
		String str = "";
		for (int i = 0; i < this.server.getChartArr().length; i++)
		{
			if(this.server.getChartArr()[i] != null)
			{
				str += this.server.getChartArr()[i].splitString();
				str += "/";
			}
		}
		return str;
	}

	public static Score[] readFromXml()
	{
		Score[] arr = new Score[10];
		try 
		{
			File fXmlFile = new File("BreakIt.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			try
			{
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("player");
				for (int temp = 0; temp < nList.getLength(); temp++) 
				{
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
						Element eElement = (Element) nNode;
						if (eElement!=null)
						{
							String id1= eElement.getAttribute("id");
							String name= eElement.getElementsByTagName("name").item(0).getTextContent();
							String points =eElement.getElementsByTagName("points").item(0).getTextContent();
							arr[temp] =  new Score(name, Integer.valueOf(points));
						}
					}
				}
			}
			catch (Exception e)
			{
				return new Score[10];
				// TODO: handle exception
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return arr;
	}

	public BufferedReader getIn()
	{
		return this.in;
	}
	public void setIn(BufferedReader in)
	{
		this.in = in;
	}
}
