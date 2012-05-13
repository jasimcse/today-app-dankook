package com.TODAY.ViewSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.TODAY.R;

public class Memo extends LinearLayout implements ParseInfo{
	
	public Memo(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_memo, this, true);
		
	}

	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
		
	}

}
