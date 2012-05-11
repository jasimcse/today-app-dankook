package com.TODAY.TimerBySebeomPark;


import java.util.Calendar;

import com.TODAY.R;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;





public class AlarmModule {
	
	// One time Alarm Moudle
	// Need to add the repeating module because the alarm usuall repeat everyday.
	
	
	/*
	The unit is "Minutes" not "Second"
 */
    public int getDifferTime(int mHour,int mMinute,boolean []dayUpFlags)
    {
    	
		// current �����媛���ㅺ린 ��� Calendar class
		final Calendar c = Calendar.getInstance();
		int curHour = c.get(Calendar.HOUR_OF_DAY);
		int curMin = c.get(Calendar.MINUTE);
		
		int curDay = c.get(Calendar.DAY_OF_WEEK);

		// converting the Hour, minute system to only minutes system;
		int curTotalMin = curHour * 60 + curMin;
		Log.i("curTime", String.valueOf(curTotalMin));

		int alarmTotalMin = mHour * 60 + mMinute;
		Log.i("AlarmTime", String.valueOf(alarmTotalMin));

		int differTimeResult = 0;
		
		
		
		/********** from here, start to get the cloest day ******************/
		/********** from here, start to get the cloest day ******************/
		/********** from here, start to get the cloest day ******************/
		
		
		// get the difference between Current Day and closest repeating day
		

		int closestRepeatDay = 0;
		int length = dayUpFlags.length;
		boolean foundRepeat = false;
		for(int i=0;i<length;i++)
		{
			if(dayUpFlags[i] == true)
			{
				closestRepeatDay = (i+1) - curDay;
				if(closestRepeatDay < 0)
					continue;
				else
					break;
			}
		}
		if(closestRepeatDay < 0)		// �����寃쎌��� 6�쇱� �����媛�� 媛��������긱����밴뎄���.
			closestRepeatDay += 7;
		
		/****************** Alam Module complete!!  *********************/
		/****************** Alam Module complete!!  *********************/
		/****************** Alam Module complete!!  *********************/
		
		
		
		if (alarmTotalMin - curTotalMin < 0) // 留�� ��� �����alarmTotalMin蹂대� ���硫�
		{
			alarmTotalMin += (60 * 24); // add the 24 hours as Min
		}
		
		
		// ��（����� ��������以��.
		
		differTimeResult = alarmTotalMin - curTotalMin + (closestRepeatDay*24*60);
		
		Log.i("Time differ", String.valueOf(differTimeResult));
		
		// 洹�����ㅼ� 異����� ���. 3rd parameter must be represented as "SECOND" NOT MIN
		return differTimeResult;
    }
    
    
	

	
	public void setTimerWithTask(Object baseClass, Class execClass, int time)
	{
		Log.i("here","here");
		// first parameter is baseClass
		
		
		// AlarmController 姨��姨��
		Intent intent = new Intent((Context)baseClass, execClass);
		PendingIntent sender = PendingIntent.getBroadcast((Context)baseClass,0,intent,0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		
		// the alarm will go off in the "TIME"
		calendar.add(Calendar.SECOND, time);


		AlarmManager am = (AlarmManager)((Context)baseClass).getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
		
		// Confirm Message
		
		
		//Toast.makeText(((Activity)baseClass), "Alarm has been registered", Toast.LENGTH_LONG).show();
	
	}
	
	

	
}
