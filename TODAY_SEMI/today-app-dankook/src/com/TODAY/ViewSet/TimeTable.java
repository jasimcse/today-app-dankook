package com.TODAY.ViewSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.TODAY.R;

public class TimeTable extends LinearLayout implements ParseInfo{
	

	public TimeTable(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_timetable, this, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parsingInfo() {
		
		// TODO Auto-generated method stub
		
	}
	
}
