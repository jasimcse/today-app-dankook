package com.TODAY.TimerBySebeomPark;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.TODAY.UIByDaeyoungCho.Main_Activity;
import com.TODAY.UIByDaeyoungCho.ViewFlipperActivity;

public class AlarmDialog implements OnClickListener, DialogInterface.OnClickListener
{

	Context context;
	String Caption;
	String btn1Txt;
	String btn2Txt;
	AlertDialog dialog;
	
	Vibrator vibe;
	public AlarmDialog(Context context, String Caption, String btn1Txt,String btn2Txt, Vibrator vt)
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
		
		vibe = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = {500, 200};
		vibe.vibrate(pattern,0);

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
				vibe.cancel();
				Intent intent = new Intent(context,Main_Activity.class);
				//intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				context.startActivity(intent);
				
				break;
				
			case DialogInterface.BUTTON2:			// Cancle btn
				Toast.makeText(context, "Cancle btn clicked", Toast.LENGTH_SHORT).show();
				vibe.cancel();
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