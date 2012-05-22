package com.TODAY.ListViewSet ;

import android.graphics.drawable.Drawable;

public class ccdtWeatherItem
{
	Drawable dtWicon ;	// 아이콘
	String dtWday;
	String dtWdate ; // 도시
	String dtWweather ;	// 날씨 상태
	String dtWlow;	// 온도
	String dtWhigh;	// 습도

	public ccdtWeatherItem(Drawable dtWicon, String dtWday, String dtWdate, String dtWweather, String dtWlow, String dtWhigh)
	{
		this.dtWicon = dtWicon;
		this.dtWday = dtWday;
		this.dtWdate = dtWdate;
		this.dtWweather = dtWweather;
		this.dtWlow = dtWlow;
		this.dtWhigh = dtWhigh;
	}
}