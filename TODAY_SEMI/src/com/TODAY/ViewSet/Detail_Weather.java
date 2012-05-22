package com.TODAY.ViewSet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.ListViewSet.ccWeatherAdapter_For_Detail;
import com.TODAY.UIByDaeyoungCho.LayoutInfo;
import com.TODAY.UIByDaeyoungCho.ScreenViewFlipper;
import com.TODAY.XMLParser.WeatherCurrentCondition;
import com.TODAY.XMLParser.WeatherForecastCondition;
import com.TODAY.XMLParser.WeatherSet;

public class Detail_Weather extends LinearLayout{

	final int index = 0;
	private static String base_url = "http://www.google.com";
	public Detail_Weather(Context context, ArrayList<WeatherSet> list, ScreenViewFlipper flipper, LayoutInfo layoutInfo, int counter) {
		super(context);
		
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.detail_weather, this, true);

		// nOT detail Item_Layout..!
		
		//RelativeLayout layout = (RelativeLayout) findViewById(R.id.embeededWeather);
		
		

		ImageView mIcon = (ImageView)findViewById(R.id.Wicon) ;
		TextView mCity = (TextView)findViewById(R.id.Wcity) ;
		TextView mWeather = (TextView)findViewById(R.id.Wweather) ;
		TextView mTemp = (TextView)findViewById(R.id.Wtemp) ;
		TextView mHumi = (TextView)findViewById(R.id.Whumi) ;
		
		
		
		
		
				
		TextView tv = (TextView)findViewById(R.id.dtWeather);
		tv.setBackgroundResource(layoutInfo.getMappingColorForTV().get(counter));
		tv.setTextColor(layoutInfo.getMappingTextColor().get(counter));
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText(layoutInfo.getMappingCaption().get(index));		// ï¿½Ä´ï¿½
		tv.setShadowLayer(1, 1, 1, Color.argb(0xAA, 0xFD, 0xFD, 0xFF));
		
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		//tv.setText
		
		WeatherCurrentCondition currentCon = list.get(0).getWeatherCurrentCondition();
		
		try {
			setIconImage(new URL(base_url + currentCon.getIconURL()), mIcon);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mCity.setText(currentCon.getCityName()) ;
		mWeather.setText(currentCon.getCondition()) ;
		mTemp.setText(String.valueOf(currentCon.getTempCelcius())) ;
		Log.i("Humidity", currentCon.getHumidity());
		Scanner in = new Scanner(currentCon.getHumidity()).useDelimiter("[^0-9]+");
		int humidity = in.nextInt();
		mHumi.setText(String.valueOf(humidity) + "%") ;
		
		

		ListView lv = (ListView)findViewById(R.id.dtWeather1);
		lv.setBackgroundResource(layoutInfo.getMappingColorForLV().get(counter));
		lv.setPadding(15, 15, 15, 15);		
		

//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		inflater.inflate(R.layout., this, true);
//		
//	
//		ListView lv = (ListView)findViewById(R.id.dtFood1);
//		lv.setOnTouchListener(flipper);		// set the TouchListener
//		
//		
//		
		
		// ¿©±â¼­ Converting ÇØÁØ´Ù.
		
		// ¾Æ´Ô µÚ¿¡²¬ ¹Ù²Ù´øÁö..
		
		
		
		ccWeatherAdapter_For_Detail adapter = new ccWeatherAdapter_For_Detail(context, R.layout.cellweather_detail, list.get(0).getWeatherForecastConditions());
		lv.setAdapter(adapter);
//		adapter.notifyDataSetChanged();
		// TODO Auto-generated constructor stub
	}
	
	public void setIconImage(URL iconUrl, ImageView mWeatherImageView) {
		try {
			URLConnection conn = iconUrl.openConnection();
			conn.connect();

			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			Bitmap bm = BitmapFactory.decodeStream(bis);

			bis.close();
			is.close();

			mWeatherImageView.setImageBitmap(bm);
		} catch (IOException e) {
			//mWeatherImageView.setImageDrawable(getResources().getDrawable(R.drawable.info));
			
			Log.e("WeatherView", "Error", e);
		}
	}

}
