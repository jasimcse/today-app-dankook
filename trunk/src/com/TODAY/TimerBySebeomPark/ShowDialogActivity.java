package com.TODAY.TimerBySebeomPark;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import com.TODAY.R;


public class ShowDialogActivity extends Activity {
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoralertdialog);
        
        AlarmDialog am = new AlarmDialog(this,"Alarm","Get up people!!!","hello");
        AlertDialog dialog = am.getAlarmDialog();
        dialog.show();
       
    }
}