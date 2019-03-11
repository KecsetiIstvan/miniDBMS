import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class Server {

	
	public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException, TransformerException, InterruptedException {
		
		//Tesztecskék
		//		 |
		//       |
		//       |
		//       V
		
		//Connection server=new Connection(8087);
		//XMLReader xml=new XMLReader("XML/scheme.xml");
		//String s=xml.readAllDatabases();
		//String s=xml.readAllTablesOfDatabes("Premium");
		//String s =xml.readAllAtributeOfTable("Premium", "poi");
		//System.out.println(s);
		/*XMLWriter xml=new XMLWriter("XML/scheme.xml");
		xml.addDatabase("SecondTest");
		Thread.sleep(500);
		String attr[]=new String[10];
		attr[0]="Kulcsocska";
		attr[1]="Int";
		attr[2]="Elso";
		attr[3]="Char";
		attr[4]="255";
		attr[5]="Masodik";
		attr[6]="Int";
		attr[7]="Harmadik";
		attr[8]="char";
		attr[9]="60";
		xml.addTable("SecondTest","ElsoTblaTeszt", attr);
		*/
		//XMLDeleter xml=new XMLDeleter("XML/scheme.xml");
		//xml.deleteDatabase("SecondTest");
		//xml.deleteTable("SecondTest", "ElsoTblaTeszt");
		/*while(true) {
			server.acceptConnection();
			
			String received=server.receiveFromClient();
			
			server.sendToClient("Szia");
		}*/
	}
	
	
}
