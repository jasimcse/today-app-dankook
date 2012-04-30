package com.TODAY.TimerBySebeomPark;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.TODAY.R;

public class HelloTimePicker extends Activity{

	
	private TextView mTimeDisplay;
	private Button mPickTime;
	private int mHour;
	private int mMinute;
	
	private boolean []dayUpFlags;
	
	
	
	AlarmModule am; 
	static final int TIME_DIALOG_ID = 0;
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
			new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(android.widget.TimePicker view, int hourOfDay,
				int minute) {
			// TODO Auto-generated method stub
			   mHour = hourOfDay;			// hourOfDay를 구한다.
	            mMinute = minute;			// minute를 구한다 from Time Picker로 부터
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
		
		initializeDayBtns();
		
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
		if(closestRepeatDay < 0)		// 음수일 경우는, 6일을 더해야 가장 가까운 시ㅏㄱㄴ으 ㄹ구한다.
			closestRepeatDay += 7;
		
		/****************** Alam Module complete!!  *********************/
		/****************** Alam Module complete!!  *********************/
		/****************** Alam Module complete!!  *********************/
		
		
		
		if (alarmTotalMin - curTotalMin < 0) // 만약 현재 시간이 alarmTotalMin보다 작다면
		{
			alarmTotalMin += (60 * 24); // add the 24 hours as Min
		}
		
		
		// 하루에 대한 시간도 더해준다.
		
		differTimeResult = alarmTotalMin - curTotalMin + (closestRepeatDay*24*60);
		// Get the difference between current time and chosen time that user picked from TimePikcer
		
		TextView tmp = (TextView) findViewById(R.id.diffTime);
		tmp.setText("The time differ : " + String.valueOf(differTimeResult));
		
		// 그 시간뒤에 출력되게 한다. 3rd parameter must be represented as "SECOND" NOT MIN
		setTheAlarm(differTimeResult);
		return mMinute;
    }
    
    
    
    
    public void setTheAlarm(int timeInMin)
    {
    	//am.setTimerWithTask(this, OneShotAlarm.class, timeInMin * 60);
    	am.setTimerWithTask(this, OneShotAlarm.class, timeInMin * 5);
    	
    	// 5초 뒤에 실행 당분간은
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
    
    
    public void initializeDayBtns()
    {
    	CheckBox[] dayChks = new CheckBox[7];				// total 7 days.
    	dayChks[0] =  (CheckBox)findViewById(R.id.SunChk);
    	dayChks[1] =  (CheckBox)findViewById(R.id.MonChk);
    	dayChks[2] =  (CheckBox)findViewById(R.id.TueChk);
    	dayChks[3] =  (CheckBox)findViewById(R.id.WedChk);
    	dayChks[4] =  (CheckBox)findViewById(R.id.TueChk);
    	dayChks[5] =  (CheckBox)findViewById(R.id.FriChk);
    	dayChks[6] =  (CheckBox)findViewById(R.id.SatChk);
    	
    	
    	dayUpFlags = new boolean[7];
    	
    	for(int i=0;i<7;i++)
    	{
    		if(dayChks[i].isChecked())
    			dayUpFlags[i] = true;
    		else
    			dayUpFlags[i] = false;
    	}
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
        
        // initialize Day Btns
        
        
        // add a click listener to the button
        // 
        
        mPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
        
        Button runBtn = (Button)findViewById(R.id.alarm);
        runBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//setTheAlarm(1);		// for testing
				
				am.setTimerWithTask(HelloTimePicker.this, OneShotAlarm.class, 1);
				
				
//		        AlertDialog dialog = new AlertDialog.Builder(HelloTimePicker.this).create();
//		        dialog.setTitle("hello");
//		        dialog.setMessage("haha");
//		        dialog.setButton("ok", new Dialog.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// 
//						Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
//						
//					}
//				});
//		        dialog.show();
				
				
				
				// TODO Auto-generated method stub
				
			}
		});
        
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
