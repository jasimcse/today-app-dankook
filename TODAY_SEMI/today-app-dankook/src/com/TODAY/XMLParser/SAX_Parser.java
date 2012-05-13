package com.TODAY.XMLParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import com.TODAY.ViewSet.News_Info;


public class SAX_Parser {

	URL url;
	InputStream xmlStream;
	final static String NEWS = "news";
	final static String Weather = "weather";
	final static int NEWS_XML = 1;
	final static int WEATHER_XML = 2;
	private StringBuffer buffer = new StringBuffer();
	int type;
	SAXParserFactory factory;
	SAXParser saxParser;
	ArrayList<Object> list;

	public SAX_Parser(String addr, String delimeter) throws Exception
	{
		url = new URL(addr);
		xmlStream = getInputStream(url);
		if(delimeter == NEWS)
			type = NEWS_XML;
		else if(delimeter == Weather)
			type = WEATHER_XML;
		factory = SAXParserFactory.newInstance();
		
		saxParser = factory.newSAXParser();
		
		list = new ArrayList<Object>();
		
	}
	
	
	public void start_Parsing() throws SAXException, IOException
	{
		saxParser.parse(xmlStream, getHandeler(type));
	}


	public InputStream getInputStream(URL url) throws Exception
	{
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
		int responseCode = httpConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = httpConnection.getInputStream();
			return inputStream;
		}
		else
			throw new Exception();
	}

	
	public DefaultHandler getHandeler(int type)
	{
		DefaultHandler handler = null;
		
		switch(type)
		{
		case NEWS_XML:
			handler = new DefaultHandler() {

				boolean title_Element = false;
				boolean link_Element = false;
				boolean desc_Element = false;
				String title;
				String link;
				String desc;
				public void startElement(String uri, String localName,String qName, 
						Attributes attributes) throws SAXException {


					if (qName.equalsIgnoreCase("TITLE")) {
						title_Element = true;
					}

					if (qName.equalsIgnoreCase("LINK")) {
						link_Element = true;
					}

					if (qName.equalsIgnoreCase("DESCRIPTION")) {
						
						desc_Element = true;
					}
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					buffer = new StringBuffer();
					//System.out.println("End Element :" + qName);

				}

				public void characters(char ch[], int start, int length) throws SAXException {
					
					if (title_Element) {
						title = (buffer.append(new String(ch,start,length))).toString();
						//System.out.println("TItle Element" + tmp.getTitle());
						title_Element= false;
					}

					if (link_Element) {
						//tmp.setLink(new String(ch,start,length));
						link = (buffer.append(new String(ch,start,length))).toString();
						//link = new String(ch,start,length);
						link_Element = false;
					}

					if (desc_Element) {
						//tmp.setDescription(new String(ch,start,length));
						desc = (buffer.append(new String(ch,start,length))).toString();
						//desc = new String(ch,start,length);
						News_Info tmp = new News_Info(title,link,desc);
						desc_Element = false;
						list.add((Object)tmp);			// add the object
						Log.i("element",tmp.getTitle());
						if(list.size() > 10)
							throw new SAXException();
					}
					
				}
			};
			break;
		case WEATHER_XML:
			handler = new DefaultHandler();
			break;
		}

		return handler;
	}
		
	// you need explicit converting.
	public ArrayList<Object> getArrayList()
	{
		return list;
	}
}
