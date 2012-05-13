package com.TODAY.TimerBySebeomPark;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.TODAY.UIByDaeyoungCho.SampleViewFlipperActivity;

public class AlarmDialog implements OnClickListener, DialogInterface.OnClickListener
{

	Context context;
	String Caption;
	String btn1Txt;
	String btn2Txt;
	AlertDialog dialog;
	public AlarmDialog(Context context, String Caption, String btn1Txt,String btn2Txt)
	{
		this.context = context;
		this.Caption = Caption;
		this.btn1Txt = btn1Txt;
		this.btn2Txt = btn2Txt;
		dialog = new AlertDialog.Builder(context).create();
		dialog.setTitle(Caption);
		dialog.setMessage("This is a test");
		dialog.setButton("Ok", this);
		dialog.setButton2("Cancle", this);
//		dialog.show();
	}
	
	
	
	public AlertDialog getAlarmDialog()
	{
		return dialog;
	}
	
	public void onClick(DialogInterface dialog, int which)
	{
		switch(which)
		{
			case DialogInterface.BUTTON1:			// OK btn
				Toast.makeText(context, "OK btn clicked", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context,SampleViewFlipperActivity.class);
				context.startActivity(intent);
				break;
				
			case DialogInterface.BUTTON2:			// Cancle btn
				Toast.makeText(context, "Cancle btn clicked", Toast.LENGTH_SHORT).show();
				break;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub\
//		Builder dialog = new AlertDialog.Builder(context);
//		dialog.setTitle(Caption).setMessage("This is a test");
//		dialog.setPositiveButton(text, listener)
		
		
	}

	
}