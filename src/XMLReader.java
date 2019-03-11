import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	
	File xmlToRead;
	String xmlName;
	public XMLReader(String fileName) {
		xmlToRead=new File(fileName);
		xmlName=fileName;
	}
	public String readAllDatabases() throws IOException {
		String giveBack="";
		String read;
		BufferedReader input=new BufferedReader(new InputStreamReader(new FileInputStream(xmlToRead),"UTF-8"));
		while((read=input.readLine())!=null) {
			if(read.contains("<DataBase ")) {
				read=read.substring(read.indexOf("\"")+1);
				read=read.substring(0, read.length() - 2);
				giveBack+=read+"#";
			}
		}
		input.close();
		return giveBack;
	}
	public String readAllTablesOfDatabes(String databaseName) throws  SAXException, ParserConfigurationException, IOException {
		String giveBack="";
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	    Document document = documentBuilder.parse(xmlToRead);
		
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
	    	giveBack+=tmp.getAttribute("tableName")+"#";
	    } 
	    
		return giveBack;
	}
	public String readAllAtributeOfTable(String databaseName, String tableName) throws IOException, SAXException, ParserConfigurationException {
		String giveBack="";
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	    Document document = documentBuilder.parse(xmlToRead);
		
	    NodeList base=document.getElementsByTagName("DataBase");
	    NodeList tables=null;
	    NodeList args=null;
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
	    	if(base.item(i).getNodeType()==Node.ELEMENT_NODE) {
		    	Element tmp=(Element) tables.item(i);
		    	if(tmp.getAttribute("tableName").equalsIgnoreCase(tableName)) {
		    		args=tmp.getElementsByTagName("Attribute");
		    	}
	    	}
	    } 
		
	    for(int i=0;i<args.getLength();i++) {
	    		Element tmp=(Element) args.item(i);
	    		if(tmp.getAttribute("type").equalsIgnoreCase("char"))
	    			giveBack+=tmp.getAttribute("attributeName")+"#"+tmp.getAttribute("type")+"#"+tmp.getAttribute("length")+"#";
	    		else
	    			giveBack+=tmp.getAttribute("attributeName")+"#"+tmp.getAttribute("type")+"#";
	    }
	    
		return giveBack;
	}
	
}
