import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author peter
 * @version Jun 23, 2016
 */
public class  connectFile
{
	private File file = null;
	private Document doc;
	private int i;
	
	/**
	 * @param arr: the array that represents the champions chart
	 */
	public connectFile(Score[] arr) // constructor
	{
		file = new File("BreakIt.xml");
		try 
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("chart");
			doc.appendChild(rootElement);

			
			boolean flag = true;

			for(i = 0; i < arr.length && flag; i++)
			{
				if(arr[i] != null)
				{
					//staff elements
					Element player = doc.createElement("player");
					rootElement.appendChild(player);
					//set attribute to staff element
					Attr attr = doc.createAttribute("id");
					String strI = "" + i;
					attr.setValue(strI);
					player.setAttributeNode(attr);

					// shorten way
					// staff.setAttribute("id", "1");

					// firstname elements
					Element name = doc.createElement("name");
					name.appendChild(doc.createTextNode(arr[i].getName()));
					player.appendChild(name);

					// salary elements
					Element points = doc.createElement("points");
					String strP = "" + arr[i].getPoints();
					points.appendChild(doc.createTextNode(strP));
					player.appendChild(points);
				}
				else
				{
					flag = false;
				}
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		}
		catch (ParserConfigurationException pce) 
		{
			pce.printStackTrace();
		}
		catch (TransformerException tfe) 
		{
			tfe.printStackTrace();
		}
	}
}
