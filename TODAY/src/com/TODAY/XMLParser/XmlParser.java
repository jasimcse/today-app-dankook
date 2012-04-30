package com.TODAY.XMLParser;




import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;




public class XmlParser {
	
	
	private Document xmlDoc = null;
	
	 private void createDomParser(InputStream inputStream) {
         // Use factory to create a DOM document
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setCoalescing(true);		// get rid of [!CDATA part
         factory.setIgnoringElementContentWhitespace(true);
         DocumentBuilder builder = null;
         try { // Get a DOM parser from the Factory
                 builder = factory.newDocumentBuilder();
         } catch (ParserConfigurationException e) {
                 e.printStackTrace();
                 return;
         }
         try { // Request the DOM parser to parse the file
                 xmlDoc = builder.parse(inputStream);
         } catch (SAXException e) {
                 e.printStackTrace();
                 return;
         } catch (IOException e) {
                 e.printStackTrace();
                 return;
         }
	 }
	public XmlParser(String urlString) throws SAXException, IOException
	{
		
		URL url = new URL(urlString);
		
		 URLConnection urlConnection = url.openConnection();
         HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
         int responseCode = httpConnection.getResponseCode();
         if (responseCode == HttpURLConnection.HTTP_OK) {
                 InputStream inputStream = httpConnection.getInputStream();
                 // Parsing XML Document
                 createDomParser(inputStream);
                 ArrayList<String> xmlData = getListofInfo("link");
                 for (int i = 0; i < xmlData.size(); i++) {
                         Log.i("Content",xmlData.get(i).toString());
                 }
         }

	}

	
	
	public ArrayList<String> getListofInfo(String tagName)
	{
		ArrayList<String> tmp = new ArrayList<String>();
		Log.i("First element",xmlDoc.getDocumentElement().getNodeName());
		NodeList nodes = xmlDoc.getElementsByTagName(tagName);
		
		if(nodes.getLength() > 0)
		{
			for(int i=0; i < nodes.getLength(); i++)
			{
				String strTmp = nodes.item(i).getTextContent() + "\n";		// get the string tmp
				tmp.add(strTmp);
			}
		}
		return tmp;
		
	}
	

}

