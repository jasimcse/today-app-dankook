package com.TODAY.ListViewSet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

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
import com.TODAY.XMLParser.WeatherForecastCondition;

public class ccWeatherAdapter_For_Detail extends BaseAdapter
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	Context context ;	
	int layoutRes ;					//	Custom Cell View
	LayoutInflater inflater ;
	private ArrayList<WeatherForecastCondition> list ;		//	Custom Cell Contents
	private static String base_url = "http://www.google.com";
	

	private int getViewCount = 0 ;
	private int convertViewCount = 0 ;
	private int dateCounter = 0;
	private String date;				// 현재 날짜
	private int dateOfMonth;			// 마지막 날짜가 증가하는거
	private Calendar cal; 
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

		ImageView wIcon = (ImageView)convertView.findViewById(R.id.dtWicon) ;
		TextView wDay = (TextView)convertView.findViewById(R.id.dtWday) ;
		TextView wDate = (TextView)convertView.findViewById(R.id.dtWdate) ;
		TextView wWeather = (TextView)convertView.findViewById(R.id.dtWweather) ;
		TextView wLow = (TextView)convertView.findViewById(R.id.dtWlow) ;
		TextView wHigh = (TextView)convertView.findViewById(R.id.dtWhigh) ;

		String url = base_url + (list.get(INDEX).getIconURL());
		//String condition = list.get(INDEX).getIconURL();	
		try {
			setIconImage(new URL(url),wIcon);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//wIcon.setImageDrawable(list.get(INDEX).dtWicon);
		
		wDay.setText(list.get(INDEX).getDayofWeek());
		wDate.setText(date + String.valueOf(dateOfMonth + (dateCounter+1)));

		//textDate.setText(mCal.get(Calendar.YEAR) + "년 " + (mCal.get(Calendar.MONTH)+1) + "월 " + mCal.get(Calendar.DATE) + "일 " + mDay + "요일\n");
		//wDate.setText(list.get(INDEX).dtWdate) ;
		wWeather.setText(list.get(INDEX).getCondition()) ;
		wLow.setText(String.valueOf(list.get(INDEX).getTempMinCelsius())) ;
		wHigh.setText(String.valueOf(list.get(INDEX).getTempMaxCelsius())) ;

		return convertView;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		inflater ?
	//			[ CustomCell.xml ] ~>{ Convert }~> [ View ]
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	
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


	public ccWeatherAdapter_For_Detail(Context context, int layoutRes, ArrayList<WeatherForecastCondition> list)
	{
		this.context = context ;
		this.layoutRes = layoutRes ;
		this.list = list ;
		inflater  = LayoutInflater.from(context) ;
		

//		String mDay = new String(ccTimeTable.DayInt2String(cal.get(Calendar.DAY_OF_WEEK))) ;

		TimeZone jst = TimeZone.getTimeZone ("JST") ;
		Calendar mCal = Calendar.getInstance ( jst ) ;
		
		date = (mCal.get(Calendar.YEAR) + "-" + (mCal.get(Calendar.MONTH)+1) + "-");
		dateOfMonth = mCal.get(Calendar.DATE);
		Calendar.getInstance( ) ;
		
		
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public int getCount() { return list.size() ; }
	public Object getItem(int position) { return list.get(position) ; }
	public long getItemId(int position) { return position ; }

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
}