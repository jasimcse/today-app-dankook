package com.TODAY.TimerBySebeomPark;


import java.util.Calendar;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.app.Activity;




public class AlarmModule {

	void setTimerWithTask(Object baseClass, Object execClass, int time)
	{
		Log.i("here","here");
		// first parameter is baseClass
		
		
		// AlarmController 쩌ㅃ쩌ㅃ
		Intent intent = new Intent((Activity)baseClass, execClass.getClass());
		
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
	
}
