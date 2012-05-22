package com.TODAY.ListViewSet;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.XMLParser.WeatherCurrentCondition;

public class ccWeatherAdapter extends BaseAdapter implements RemoveInterface
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	Context context ;	
	int layoutRes ;					//	Custom Cell View
	ArrayList<WeatherCurrentCondition> list ;		//	Custom Cell Contents
	LayoutInflater inflater ;

	int getViewCount = 0 ;
	int convertViewCount = 0 ;
	
	private static String base_url = "http://www.google.com";

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public View getView(int position, View convertView, ViewGroup parent)
	{
		getViewCount++ ;
		final int INDEX = position ;

		if(convertView == null)
		{
			convertViewCount++ ;
			convertView = inflater.inflate(layoutRes, parent, false) ;
		}

		ImageView mIcon = (ImageView)convertView.findViewById(R.id.Wicon) ;
		TextView mCity = (TextView)convertView.findViewById(R.id.Wcity) ;
		TextView mWeather = (TextView)convertView.findViewById(R.id.Wweather) ;
		TextView mTemp = (TextView)convertView.findViewById(R.id.Wtemp) ;
		TextView mHumi = (TextView)convertView.findViewById(R.id.Whumi) ;

		try {
			setIconImage(new URL(base_url + list.get(INDEX).getIconURL()), mIcon);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mCity.setText(list.get(INDEX).getCityName()) ;
		mWeather.setText(list.get(INDEX).getCondition()) ;
		mTemp.setText(String.valueOf(list.get(INDEX).getTempCelcius())) ;
		Log.i("Humidity", list.get(INDEX).getHumidity());
		Scanner in = new Scanner(list.get(INDEX).getHumidity()).useDelimiter("[^0-9]+");
		int humidity = in.nextInt();
		mHumi.setText(String.valueOf(humidity) + "%") ;
		return convertView;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		inflater ?
	//			[ CustomCell.xml ] ~>{ Convert }~> [ View ]
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccWeatherAdapter(Context context, int layoutRes, ArrayList<WeatherCurrentCondition> list)
	{
		this.context = context ;
		this.layoutRes = layoutRes ;
		this.list = list ;
		inflater  = LayoutInflater.from(context) ;
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


	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public int getCount() { return list.size() ; }
	public Object getItem(int position) { return list.get(position) ; }
	public long getItemId(int position) { return position ; }

	@Override
	public void removeAllItems() {
		// TODO Auto-generated method stub
		list.clear();
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
}