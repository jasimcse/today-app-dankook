package com.TODAY.TimerBySebeomPark;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.HTML2XMLBySebeomPark.*;

public class HelloTimePicker extends Activity{

	
	private TextView mTimeDisplay;
	private Button mPickTime;
	private int mHour;
	private int mMinute;
	
	
	AlarmModule am; 
	static final int TIME_DIALOG_ID = 0;
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
			new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(android.widget.TimePicker view, int hourOfDay,
				int minute) {
			// TODO Auto-generated method stub
			   mHour = hourOfDay;
	            mMinute = minute;
	            updateDisplay();
	            getDifferTime(mHour,mMinute);
	            // 이 부분에서 현재시간과의 difference들을 보여주면 될것 같다.
		}
    };
    
    
    // get the time difference between Alarm and current Time
    public int getDifferTime(int mHour,int mMinute)
    {
		// current 시간을 가져오기 위한 Calendar class
		final Calendar c = Calendar.getInstance();
		int curHour = c.get(Calendar.HOUR_OF_DAY);
		int curMin = c.get(Calendar.MINUTE);

		// converting the Hour, minute system to only minutes system;
		int curTotalMin = curHour * 60 + curMin;
		Log.i("curTime", String.valueOf(curTotalMin));

		int alarmTotalMin = mHour * 60 + mMinute;
		Log.i("AlarmTime", String.valueOf(alarmTotalMin));

		int differTimeResult = 0;

		if (alarmTotalMin - curTotalMin < 0) // 만약 현재 시간이 alarmTotalMin보다 작다면
		{
			alarmTotalMin += (60 * 24); // add the 24 hours as Min
		}

		differTimeResult = alarmTotalMin - curTotalMin;
		TextView tmp = (TextView) findViewById(R.id.diffTime);
		tmp.setText("The time differ : " + String.valueOf(differTimeResult));
		
		// 그 시간뒤에 출력되게 한다. 3rd parameter must be represented as "SECOND" NOT MIN
		setTheAlarm(differTimeResult);
		return mMinute;
    }
    
    public void setTheAlarm(int timeInMin)
    {
    	am.setTimerWithTask(this, OneShotAlarm.class, timeInMin * 60);
    }

    
    //showDialog(id)가 설정되면 일로 return값이 오나보당
    
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:			// call the TimePickerDialog
            return new TimePickerDialog(this,mTimeSetListener, mHour, mMinute, false);
        }
        return null;
    }
    
    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmbtn);

        // initialization the alarmmodule
        am = new AlarmModule();
        
        // capture our View elements
        mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
        mPickTime = (Button) findViewById(R.id.pickTime);
        
        
        
        // add a click listener to the button
        // 
        
        mPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
                
                // for testing by Sebeom Park
                // Call the HTML2XML parser
                
                
                HtmlToXMLModule htx = new HtmlToXMLModule();
                try {
					htx.Html2Xml("http://203.237.226.95:8080/mobile/login/login_ok.jsp?userid=32071467&userpw=jj119&returnUrl=../m7/m7_c1.jsp&instanceid=");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
                // The end of this task.
                
            }
        });
        
/*
        // get the current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // display the current date
        updateDisplay();*/
    }
	
	
	private void updateDisplay()
	{
		// Show the selected Time
		mTimeDisplay.setText(new StringBuilder().append(pad(mHour)).append(":").append(pad(mMinute)));
		
			
	}
	
	
	private static String pad(int c) {
	    if (c >= 10)
	        return String.valueOf(c);
	    else
	        return "0" + String.valueOf(c);
	}

}
