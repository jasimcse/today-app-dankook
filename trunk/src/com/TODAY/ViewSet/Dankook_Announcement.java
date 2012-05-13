package com.TODAY.ViewSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.TODAY.R;

public class Dankook_Announcement extends LinearLayout implements ParseInfo{
	View view;
	public Dankook_Announcement(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		// Layout
		
		//for texting
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_announcement, this, true);
		
		
	}
	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
		
	}
	
}
