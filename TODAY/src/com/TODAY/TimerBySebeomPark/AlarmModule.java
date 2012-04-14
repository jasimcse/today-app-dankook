package com.TODAY.TimerBySebeomPark;


import java.util.Calendar;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;




public class AlarmModule {

	
	// One time Alarm Moudle
	// Need to add the repeating module because the alarm usuall repeat everyday.
	
	
	void setTimerWithTask(Object baseClass, Class execClass, int time)
	{
		Log.i("here","here");
		// first parameter is baseClass
		
		
		// AlarmController 쩌ㅃ쩌ㅃ
		Intent intent = new Intent((Activity)baseClass, execClass);
		
		PendingIntent sender = PendingIntent.getBroadcast((Activity)baseClass,0,intent,0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		
		// the alarm will go off in the "TIME"
		calendar.add(Calendar.SECOND, time);
		
		

		AlarmManager am = (AlarmManager)((Activity)baseClass).getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
		
		// Confirm Message
		
		Toast.makeText(((Activity)baseClass), "Alarm has been registered", Toast.LENGTH_LONG).show();
	
	}
	
	

	// This module is to select the time with date
	// for Date Picker, it refers to http://developer.android.com/resources/tutorials/views/hello-datepicker.html
	// for Time Picker, it refers to http://developer.android.com/reference/android/widget/TimePicker.html

	void showTimePicker()			
	{
		TimePickerDialog.OnTimeSetListener mTimeSetListener = new OnTimeSetListener(){
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}
