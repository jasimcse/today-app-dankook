package com.TODAY.Alarm;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.TODAY.R;
import com.TODAY.ObjectSerializer.ObjectSerializer;
import com.TODAY.TimerBySebeomPark.AlarmModule;
import com.TODAY.TimerBySebeomPark.AlarmTask;

/**
 * ListView
 *
 * @author Mike
 */
public class AlarmListViewActivity extends Activity {

	DataListView list;
	
	private boolean []dayUpFlags;		// dayUpFlags
	IconTextListAdapter adapter;
	
	static final int TIME_DIALOG_ID = 0;
	static final int DAY_DIALOG_ID = 1;
	static final String shared_id_alarm_info = "Alarm_INFOMATION";
	static final String alarm_Key= "AlarmKey";
	AlarmModule am = new AlarmModule();
	
	private int mHour;
	private int mMinute;
	
	
	// will be added into the preference
	private ArrayList<RegisteredAlarmInfo> alarmInfoList = new ArrayList<RegisteredAlarmInfo>();
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // window feature for no title - must be set prior to calling setContentView.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // create a DataGridView instance
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        list = new DataListView(this);

        // create an DataAdapter and a MTable
        adapter = new IconTextListAdapter(this);

		// add items
//        
//		Resources res = getResources();
//		//adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "추억의 테트리스", "30,000 다운로드", "900 원"));
//		
//		
//		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), "8:00pm","mon,tue"));
//		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), "9:00pm","mon,tue"));
//		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), "10:00pm","mon,tue"));
//		
//		
//		
		
		// call setAdapter()
		list.setAdapter(adapter);
		


		// use adapter.notifyDataSetChanged() to apply changes after adding items dynamically
		// adapter.notifyDataSetChanged();

		

		// set listener for each list.
		list.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v, int position, long id) {
				IconTextItem curItem = (IconTextItem) adapter.getItem(position);

				// update the the screen
				
				final int selectedPosition = position;
				
				AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
				builder.setTitle("Are you sure??");
				builder.setPositiveButton("Delete", new Dialog.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						alarmInfoList.remove(selectedPosition);
						adapter.removeItem(selectedPosition);
						/*******************
						 * please add the AlarmService cancel handler!
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 */
						refreshActivity(adapter);		// refresh
					}
				});
		    	builder.setNegativeButton("Cancel", new Dialog.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				});
				
				
				String[] curData = curItem.getData();
				Toast.makeText(getApplicationContext(), "Selected : " + curData[0], 2000).show();
				
			}
		});
		

		setContentView(R.layout.addalarm);
		
		LinearLayout layoutView = (LinearLayout)findViewById(R.id.alarmListLayout);
		layoutView.removeAllViews();
		layoutView.addView(list);
		
		
		
		// for adding Alarm dialog
		
		TextView alarmBtn = (TextView) findViewById(R.id.addAlarmBtn);
		alarmBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			}
		});	
    }
    
    
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			saveCurrentState();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		try {
			restoreFormSavedState();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onResume();
	}



	@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:			// call the TimePickerDialog
            return new TimePickerDialog(this,mTimeSetListener, mHour, mMinute, false);
        case DAY_DIALOG_ID:
        	return getRepeatingDaysDialog();
        	
        }
        return null;
    }
    
    
    
	
    
    // TimePickerdialog Listener
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
			new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(android.widget.TimePicker view, int hourOfDay,
				int minute) {
			// TODO Auto-generated method stub
			   mHour = hourOfDay;			// hourOfDay瑜�援ы���
	            mMinute = minute;			// minute瑜�援ы���from Time Picker濡�遺��
	            showDialog(DAY_DIALOG_ID);
	            //updateDisplay();
	            //getDifferTime(mHour,mMinute);
	            // ��遺����� ������怨쇱� difference�ㅼ� 蹂댁�二쇰㈃ ��� 媛��.
	            
	            // here, pop up the second Dialog to select the repeating days.z
	            	            
		}
    };
    
    public void initDayChkBoxes()
    {
    	dayUpFlags = new boolean[7];
    	for(int i=0;i<dayUpFlags.length;i++)			// initialize the check box.
    		dayUpFlags[i] = false;
    	
    }
    
    public Dialog getRepeatingDaysDialog()
    {
    	initDayChkBoxes();
    	
    	final CharSequence[] items = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Select the repeating day");
    	builder.setMultiChoiceItems(items, dayUpFlags, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				switch(which)
				{
					case 0:
						if(isChecked)
							dayUpFlags[0] = true;
						break;
					case 1:
						if(isChecked)
							dayUpFlags[1] = true;
						break;
					case 2:
						if(isChecked)
							dayUpFlags[2] = true;
						break;
					case 3:
						if(isChecked)
							dayUpFlags[3] = true;
						break;
					case 4:
						if(isChecked)
							dayUpFlags[4] = true;
						break;
					case 5:
						if(isChecked)
							dayUpFlags[5] = true;
						break;
						
					case 6:
						if(isChecked)
							dayUpFlags[6] = true;
						break;						
				}
			}
		});
    	builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Alarm has been registered", Toast.LENGTH_LONG).show();
				
				// register Alarm Task
				AlarmModule am = new AlarmModule();
				int differTime = am.getDifferTime(mHour, mMinute, dayUpFlags);

				// for testing!!!
				//am.setTimerWithTask(getApplicationContext(), AlarmTask.class, differTime*60);		// set the Alarm
				
				
				// for testing
				am.setTimerWithTask(getApplicationContext(), AlarmTask.class, 15);		// set the Alarm
				
				
				
				// add the information into the ArrayList
				alarmInfoList.add(new RegisteredAlarmInfo(mHour, mMinute, dayUpFlags));
				Resources res = getResources();
				adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), alarmInfoList.get(alarmInfoList.size()-1).getTime(),alarmInfoList.get(alarmInfoList.size()-1).getRepeatingDay()));
				
				
				refreshActivity(adapter);
			}
		});
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});

    	AlertDialog alert = builder.create();
    	return alert;
    }
    
	private void saveCurrentState() throws Exception {		// save the information of preference
		// TODO Auto-generated method stub
		SharedPreferences pref = getSharedPreferences(shared_id_alarm_info, Activity.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putString(alarm_Key, ObjectSerializer.serialize(alarmInfoList));
		prefEditor.commit();
	}

	
	@SuppressWarnings("unchecked")
	private void restoreFormSavedState() throws Exception {
		// TODO Auto-generated method stub
		SharedPreferences pref = getSharedPreferences(shared_id_alarm_info,Activity.MODE_PRIVATE);
		
		if( (pref != null) && pref.contains(alarm_Key))
		{
		
			alarmInfoList = (ArrayList<RegisteredAlarmInfo>)ObjectSerializer.deserialize(pref.getString(alarm_Key, ""));
			int size = alarmInfoList.size();
			Resources res = getResources();
			for(int i=0;i<size;i++)
			{
				adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), alarmInfoList.get(i).getTime(),alarmInfoList.get(i).getRepeatingDay()));
			}
		}
		
	}

    public void refreshActivity(BaseAdapter adapter)
    {
    	list.setAdapter(adapter);
    }
    
    
    
   

}