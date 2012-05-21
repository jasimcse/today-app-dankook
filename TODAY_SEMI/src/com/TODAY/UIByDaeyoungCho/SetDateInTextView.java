package com.TODAY.UIByDaeyoungCho;

import java.util.Calendar;
import java.util.TimeZone;


import android.widget.TextView;

public class SetDateInTextView {

	public SetDateInTextView()
	{
		
	}
	public static TextView setDate(TextView txt)
	{
		Calendar cal= Calendar.getInstance( ) ;
		String mDay = new String(DayInt2String(cal.get(Calendar.DAY_OF_WEEK))) ;

		TimeZone jst = TimeZone.getTimeZone ("JST") ;
		Calendar mCal = Calendar.getInstance ( jst ) ;
		txt.setText(mCal.get(Calendar.YEAR) + "년 " + (mCal.get(Calendar.MONTH)+1) + "월 " + mCal.get(Calendar.DATE) + "일 " + mDay + "요일\n");
		return txt; 
		
	}
	
	public static String DayInt2String(int iDay)
	{
		String mDay = new String() ;
		switch(iDay)
		{
		case 1:		mDay = "일" ; break ;
		case 2:		mDay = "월" ; break ;
		case 3:		mDay = "화" ; break ;
		case 4:		mDay = "수" ; break ;
		case 5:		mDay = "목" ; break ;
		case 6:		mDay = "금" ; break ;
		case 7:		mDay = "토" ; break ;
		default :	mDay = "일" ;
		}		
		return mDay ;
	}
}
