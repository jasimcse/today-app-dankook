package com.TODAY.MainPackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.TODAY.R;
import com.TODAY.TimerBySebeomPark.HelloTimePicker;
import com.TODAY.UIByDaeyoungCho.MainLayout;


public class MainActivity extends Activity {
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn = (Button)findViewById(R.id.btn);
       
        LinearLayout tmp = (LinearLayout)findViewById(R.layout.main);
        
        
 
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ��遺���������Activity濡��깅������ ���댁���AndroidManifest File��諛���������Activity瑜�異�������
//				Intent intent = new Intent(getApplicationContext(), AlarmController.class);

				
				//Intent intent = new Intent(getApplicationContext(),TestingActivity.class);
//				Intent intent = new Intent(getApplicationContext(),HelloTimePicker.class);
//				startActivity(intent);
//
				
				Intent intent = new Intent(getApplicationContext(),MainLayout.class);
				startActivity(intent);
				
			}
		});
    }
}