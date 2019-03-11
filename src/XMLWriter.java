import java.io.IOException;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class XMLWriter {
	String xmlToRead;
	public XMLWriter(String fileName) {
		xmlToRead=fileName;
	}
	public void addDatabase(String databaseName) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	     Document document = documentBuilder.parse(xmlToRead);
	     Element root = document.getDocumentElement();
	     
	     Collection<Database> databases = new ArrayList<Database>();
	     databases.add(new Database());
	     
	     Element newDatabase = document.createElement("DataBase");
	     newDatabase.setAttribute("dataBaseName", databaseName);
	     newDatabase.setTextContent("\n");
	            
	     root.appendChild(newDatabase);
	     
	     DOMSource source = new DOMSource(document);
	     TransformerFactory transformerFactory = TransformerFactory.newInstance();
	     Transformer transformer = transformerFactory.newTransformer();
	     StreamResult result = new StreamResult(xmlToRead);
	     transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		 transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	     transformer.transform(source, result);

	}
	public void addTable(String databaseName,String tableName, String[] attr) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	     Document document = documentBuilder.parse(xmlToRead);
	     
	     Collection<Database> databases = new ArrayList<Database>();
	     databases.add(new Database());
	     Element toAdd=null;
	     NodeList base=document.getElementsByTagName("DataBase");
	     for(int i=0;i<base.getLength();i++) {
	    	 if(base.item(i).getNodeType()==Node.ELEMENT_NODE) {
	    		 Element data=(Element) base.item(i);
	    		 if(data.getAttribute("dataBaseName").equalsIgnoreCase(databaseName)) {
	    			 toAdd=data;
	    			 break;
	    		 }
	    	 } 
	     }
         Element port = document.createElement("Table");
         port.setTextContent("\n");
         port.setAttribute("fileName", tableName+".kv");
         port.setAttribute("rowLength", "500");
         port.setAttribute("tableName", tableName);
         
         Element structure=document.createElement("Structure");
         for(int i=0;i<attr.length;i++) {
        	 Element attribute=document.createElement("Attribute");
        	 attribute.setAttribute( "attributeName", attr[i]);
        	 i++;
        	 attribute.setAttribute("type", attr[i]);
         	 if(attr[i].equalsIgnoreCase("char")) {
         		 i++;
         	 	attribute.setAttribute("length", attr[i]);
         	 }
         	 structure.appendChild(attribute);
         }
         port.appendChild(structure);
        
         Element pk=document.createElement("primaryKey");
         Element pkattr=document.createElement("pkAttribute");
         pkattr.setTextContent(attr[0]);
         pk.appendChild(pkattr);
        
         port.appendChild(pk);
        
         toAdd.appendChild(port); 
		
		 DOMSource source = new DOMSource(document);
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		 Transformer transformer = transformerFactory.newTransformer();
		 StreamResult result = new StreamResult(xmlToRead);
		 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		 transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		 transformer.transform(source, result);
		
	}
	
	 public static class Database {
	    public String getName() { return "foo"; }
	 }
	
}
