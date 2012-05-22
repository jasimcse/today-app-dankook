package com.TODAY.TimerBySebeomPark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import com.TODAY.R;


public class ShowDialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_alert_dialog);

		Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		AlarmDialog am = new AlarmDialog(this,"Alarm","Get up people!!!","hello", vibe);
		AlertDialog dialog = am.getAlarmDialog();
		dialog.show();
	}
}