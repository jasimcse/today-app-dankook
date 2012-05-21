package com.TODAY.UIByDaeyoungCho;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.TODAY.R;
import com.TODAY.Alarm.AlarmListViewActivity;
import com.TODAY.MainPackage.MainActivity;

public class CategoryLayout extends Activity{

	CheckBox[] chk;
	Button confirmBtn;
	Button cancelBtn;
	LinearLayout alarmLayout;
	final int MaxNum = 3;
	int numOfSelectedItem = 0 ;
	static final String categoryInfo = "categoryPref";
	static final String prefKey = "selectedItems";
	static final int Alarm_Request = 1;
	static final int MainActivity_Request = 0; 
	static final String WHOCALL = "whocall";
	String selectedItemInfo = "111000";
	String first_Items_For_Removing = "";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);


		// We should try to pass the informatino.. using what? intent? Message?

		initChckBtn();
		setEventListener();
	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		restoreFormSavedState();	
	
	}

	public void showSelectedChkboxes(String str)
	{
		char a = 0;
		for(int i=0;i<6;i++)
		{
			try
			{
				a = str.charAt(i);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.i("Here", "out of Exception occured the metehod name is showSelectedChkboxes");
			}
			if(a == '1')			// THIS PART SHOULD BE BEING DEBUGGED
				chk[i].setChecked(true);
			else
				chk[i].setChecked(false);
		}
	}

	private void restoreFormSavedState() {
		// TODO Auto-generated method stub
		SharedPreferences pref = getSharedPreferences(categoryInfo,Activity.MODE_PRIVATE);
		
		if( (pref != null) && pref.contains(prefKey))
		{
			String itemInfo  = pref.getString(prefKey, "");
			showSelectedChkboxes(itemInfo);
		}
		
	}



	public void initChckBtn()
	{
		chk = new CheckBox[6];
		
		chk[0] = (CheckBox) findViewById(R.id.chk1);		// Weather
		chk[1] = (CheckBox) findViewById(R.id.chk2);		// Cafeteria
		chk[2] = (CheckBox) findViewById(R.id.chk3);		// TimeTable
		chk[3] = (CheckBox) findViewById(R.id.chk4);		// News
		chk[4] = (CheckBox) findViewById(R.id.chk5);		// Memo
		chk[5] = (CheckBox) findViewById(R.id.chk6);		// School announcement
	}


	public void setEventListener() 
	{
		confirmBtn = (Button) findViewById(R.id.categoryConfirmBtn);
		cancelBtn = (Button) findViewById(R.id.categoryCancelBtn);
		alarmLayout = (LinearLayout) findViewById(R.id.alarmLinear);
		
		
		
		BtnEventHandler handler = new BtnEventHandler();
		confirmBtn.setOnClickListener(handler);			// register the event
		confirmBtn.setOnClickListener(handler);
		alarmLayout.setOnClickListener(handler);
	}
	
	
	private void saveCurrentState() {		// save the information of preference
		// TODO Auto-generated method stub
		SharedPreferences pref = getSharedPreferences(categoryInfo, Activity.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putString(prefKey, selectedItemInfo);
		prefEditor.commit();
	}


	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
		{
		case Alarm_Request:
			if(resultCode == RESULT_OK)
			{
				
			}
			break;
		
		}
	}




	public class BtnEventHandler implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == confirmBtn.getId())			// Confirm btn event handler
			{
				// send a message(intent)
				// then get-back
				
				// remove all child's parent's view
				
				
				
				numOfSelectedItem = 0;
				for(int i=0;i<chk.length;i++)
				{
					if(chk[i].isChecked())
						numOfSelectedItem++;
				}
				
				if(numOfSelectedItem != 3)
				{
					Toast.makeText(getApplicationContext(), "You must select three items", Toast.LENGTH_SHORT).show();
					return;
				}
			
				else		// save the sharedPreference
				{
					
					selectedItemInfo = getSelectedItemindex();
					saveCurrentState();
//					removeAllParent(first_Items_For_Removing);		// remove all hierachichary..
					finish();
				}
			}
			if(v.getId() == cancelBtn.getId())
			{	
				finish();
			}
			

			if(v.getId() == alarmLayout.getId())
			{
				Log.i("AlarmLayout,","AlarmLayout");
				Intent intent = new Intent(getApplicationContext(),AlarmListViewActivity.class);
				startActivityForResult(intent, Alarm_Request);			
			}
			
		}

		

		private String getSelectedItemindex() {
			// TODO Auto-generated method stub
			int length = chk.length;
			String tmp = "";			// total 6 characters
			for(int i=0;i<length;i++)
			{
				if(chk[i].isChecked())	// Check�� index�� 0�� �ȴ�.
					tmp +='1';
				else
					tmp +='0';
			}
				
			return tmp;
		}

	}
}


