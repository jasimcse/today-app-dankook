package com.TODAY.UIByDaeyoungCho;

import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.TODAY.R;

public class MainLayout extends Activity
{
	final int MainLayout = 1000;
	TreeMap<Integer, Integer> indexMap;		// 여기에 Object를 할당하고


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlist);





		Button setupBtn = (Button) findViewById(R.id.setupBtn);
		setupBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), CategoryLayout.class);		// start Activity
				startActivity(intent);
				// to Show The List of Category

			}
		});



		// mapping the layout, TreeMap will have the information.

		indexMap = new TreeMap<Integer, Integer>();
		
		indexMap.put(0, R.layout.weatherlayout);
		indexMap.put(1, R.layout.cafeterialayout);
		indexMap.put(2, R.layout.timetablelayout);
		indexMap.put(3, R.layout.newslayout);
		indexMap.put(4, R.layout.memolayout);
		indexMap.put(5, R.layout.dankooknotification);
		
		// 여기에다가 R.layout이 아닌, 새로운 class를 전부다 생성하면 된다.
		
		// Linear Layout event 만들기... <-- when everything must be together. let's do this.
		



	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		restoreFormSavedState();

	}



	private void restoreFormSavedState() {
		// TODO Auto-generated method stub
		SharedPreferences pref = getSharedPreferences(CategoryLayout.categoryInfo,Activity.MODE_PRIVATE);

		if(pref == null)
		{		
			SharedPreferences.Editor prefEditor = pref.edit();
			prefEditor.putString(CategoryLayout.prefKey, "111000");			// 날씨, 식단, 시간표 현재
			prefEditor.commit();
			return;
		}

		if( (pref != null) && pref.contains(CategoryLayout.prefKey))
		{
			String itemInfo  = pref.getString(CategoryLayout.prefKey, "");
			showSelectedItemLayout(itemInfo);
			// handling

		}
	}

	public void showSelectedItemLayout(String itemInfo)
	{
				
		int totalIndex = 0;
		char sel = 0;
		for(int i=0;i<6;i++)
		{
			if(itemInfo.charAt(i) == '1')
			{
				switch(totalIndex++)
				{
					case 0:
					{
						LinearLayout first_Layout = (LinearLayout)findViewById(R.id.first_Layout);
						first_Layout.removeAllViews();
						LayoutInflater inflater = getLayoutInflater();
						first_Layout.addView(inflater.inflate(indexMap.get(i), null));
						

						break;
					}
					case 1:
					{
						LinearLayout second_Layout = (LinearLayout)findViewById(R.id.second_Layout);
						second_Layout.removeAllViews();
						LayoutInflater inflater = getLayoutInflater();
						second_Layout.addView(inflater.inflate(indexMap.get(i), null));
		
						
						break;
					}
	
					case 2:
					{
						LinearLayout third_Layout = (LinearLayout)findViewById(R.id.third_Layout);
						third_Layout.removeAllViews();
						LayoutInflater inflater = getLayoutInflater();
						third_Layout.addView(inflater.inflate(indexMap.get(i), null));
						break;
					}
				}
			}
		}

	}



}
