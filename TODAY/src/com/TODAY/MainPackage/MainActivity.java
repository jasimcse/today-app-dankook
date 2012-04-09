package com.TODAY.MainPackage;

import java.util.Calendar;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



import com.TODAY.R;
import com.TODAY.TimerBySebeomPark.AlarmController;


public class MainActivity extends Activity {
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 이 부분을 자신의 Activity로 등록하세요. 단 이전에 AndroidManifest File에 반드시 자신의 Activity를 추가하세요
				Intent intent = new Intent(getApplicationContext(), AlarmController.class);
				startActivity(intent);
			}
		});
    }
}