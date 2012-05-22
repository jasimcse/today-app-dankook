package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.TODAY.R;
import com.TODAY.XMLParser.SAX_Parser;
import com.TODAY.XMLParser.WeatherSet;

public class Weather extends LinearLayout implements ParseInfo{
		
	
	private Context context;
	private Detail_Weather detailView;
	ArrayList<WeatherSet> list;
	private WeatherSet weatherSet;
	public Weather(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_weather, this, true);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
		SAX_Parser sax_Parser = null;
		try {
			sax_Parser = new SAX_Parser("http://www.google.com/ig/api?weather=Seoul", "weather");
			sax_Parser.start_Parsing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		weatherSet = sax_Parser.getWeatherSet();
		weatherSet.getWeatherCurrentCondition().setCityName("Seoul");		// should be changes
		list = new ArrayList<WeatherSet>();
		list.add(weatherSet);
		
	}
	
	public WeatherSet getWeatherSet()
	{
		return weatherSet;
	}

	@Override
	public ArrayList<?> getArrayList() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public LinearLayout getDetailView() {
//		// TODO Auto-generated method stub
//		detailView = new Detail_Weather(context);
//		return detailView;
		return null;
	}
	

}
