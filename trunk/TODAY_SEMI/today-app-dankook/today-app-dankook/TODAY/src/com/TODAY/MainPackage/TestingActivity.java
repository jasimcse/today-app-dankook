package com.TODAY.MainPackage;

import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;

import com.TODAY.XMLParser.RequestMethod;
import com.TODAY.XMLParser.RestClient;
import com.TODAY.XMLParser.XmlParser;
import com.TODAY.TimerBySebeomPark.HelloTimePicker;
import com.TODAY.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestingActivity extends Activity {
	private ArrayList<String> result;
	private RestClient rc_market;	
	private XmlParser xp;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmlparerlayout);		// change this part

		Button btn1 = (Button)findViewById(R.id.parserBtn);
		Button btn2 = (Button)findViewById(R.id.resultBtn);


		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v){
			    try {
					xp = new XmlParser("http://rss.cbs.co.kr/nocutnews.xml");
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			    
			    Log.i("success","success");
			    	
			}
		});
	}
}