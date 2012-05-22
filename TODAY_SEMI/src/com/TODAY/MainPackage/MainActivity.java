package com.TODAY.MainPackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.TODAY.R;
import com.TODAY.UIByDaeyoungCho.Main_Activity;


public class MainActivity extends Activity {
	
	Handler mHandler;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_logo);
       // Button btn = (Button)findViewById(R.id.btn);
       
        //LinearLayout tmp = (LinearLayout)findViewById(R.layout.main);
        mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				//super.handleMessage(msg);
				Intent intent = new Intent(getApplicationContext(),Main_Activity.class);
				
				startActivity(intent);
				
			}
        	
        };
        Thread th = new Thread(new Runnable()
        {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//mHandler.sendMessageDelayed(msg, delayMillis)
				Message msg = Message.obtain();
				mHandler.sendMessageDelayed(msg, 1000);
			}
        	
        });
        th.run();

        
//        
//        btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//
////
//				
//				Intent intent = new Intent(getApplicationContext(),Main_Activity.class);
//				startActivity(intent);
//				
//			}
//		});
    }
}