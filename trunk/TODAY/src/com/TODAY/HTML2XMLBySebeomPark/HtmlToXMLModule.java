package com.TODAY.HTML2XMLBySebeomPark;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.*;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.*;


import android.util.Log;

public class HtmlToXMLModule {

	
	HtmlCleaner cleaner;
	CleanerProperties props;
	TagNode node;
	public HtmlToXMLModule()
	{
		Log.i("HTmlToXMLModule", "True");
		cleaner = new HtmlCleaner();
		props = cleaner.getProperties();
		
		Log.i("HTmlToXMLModule2", "True");
	}
	
	public void Html2Xml(String url) throws MalformedURLException, IOException
	{	
		Log.i("HTmlToXMLModule3", "True");
		node = cleaner.clean(new URL("http://203.237.226.95:8080/mobile/login/login_ok.jsp?userid=32071467&userpw=jj119&returnUrl=../m7/m7_c1.jsp&instanceid="));
		new PrettyXmlSerializer(props).writeToFile(node, "test.xml","utf-8");
	}
	
	
		
	
}
