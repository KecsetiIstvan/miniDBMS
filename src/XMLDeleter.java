import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDeleter {
	String xmlToRead;
	public XMLDeleter(String fileName) {
		xmlToRead=fileName;
	}
	
	public void deleteDatabase(String databaseName) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	     Document document = documentBuilder.parse(xmlToRead);
	     
	     Element toDelete=null;
	     NodeList base=document.getElementsByTagName("DataBase");
	     for(int i=0;i<base.getLength();i++) {
	    	 if(base.item(i).getNodeType()==Node.ELEMENT_NODE) {
	    		 Element data=(Element) base.item(i);
	    		 if(data.getAttribute("dataBaseName").equalsIgnoreCase(databaseName)) {
	    			 toDelete=data;
	    			 break;
	    		 }
	    	 } 
	     }
	     while(toDelete.hasChildNodes())
	    	 toDelete.removeChild(toDelete.getFirstChild());
	     toDelete.getParentNode().removeChild(toDelete);
	     
	     DOMSource source = new DOMSource(document);
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		 Transformer transformer = transformerFactory.newTransformer();
		 StreamResult result = new StreamResult(xmlToRead);
		 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		 transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		 transformer.transform(source, result);
	}
	public void deleteTable(String databaseName,String tableName) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	     Document document = documentBuilder.parse(xmlToRead);
	     
	     Element toDelete=null;
	     NodeList base=document.getElementsByTagName("DataBase");
	     NodeList tables=null;
	     for(int i=0;i<base.getLength();i++) {
	    	 if(base.item(i).getNodeType()==Node.ELEMENT_NODE) {
	    		 Element data=(Element) base.item(i);
	    		 if(data.getAttribute("dataBaseName").equalsIgnoreCase(databaseName)) {
	    			 tables=data.getElementsByTagName("Table");
	    			 break;
	    		 }
	    	 } 
	     }
	     
	     for(int i=0;i<tables.getLength();i++) {
	    	 Element tmp=(Element) tables.item(i);
	    	 if(tmp.getAttribute("tableName").equalsIgnoreCase(tableName)) {
	    		 toDelete=tmp;
	    		 break;
	    	 }
	     }
	     
	     while(toDelete.hasChildNodes())
	    	 toDelete.removeChild(toDelete.getFirstChild());
	     toDelete.getParentNode().removeChild(toDelete);
	     
	     DOMSource source = new DOMSource(document);
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		 Transformer transformer = transformerFactory.newTransformer();
		 StreamResult result = new StreamResult(xmlToRead);
		 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		 transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		 transformer.transform(source, result);
	}
	
}
