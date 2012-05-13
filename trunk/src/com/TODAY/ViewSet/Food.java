package com.TODAY.ViewSet;

import com.TODAY.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class Food extends LinearLayout implements ParseInfo{

	View view;
	
	public Food(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_food, this, true);
		
	}

	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
		
	}

	
}
