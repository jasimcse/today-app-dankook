package com.TODAY.Note;
//
//import java.util.Calendar;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AnimationUtils;
//import android.widget.GridView;
//import android.widget.ViewFlipper;
//
//import com.TODAY.R;
//
//public class Calendar_Flipper extends Activity{
//	
//	
//	ViewFlipper flipper;
//	CalendarActivity calendar;
//	float downX;
//	float upX;
//	
//	float downY;
//	float upY;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.note_flipper);
//		flipper = (ViewFlipper) findViewById(R.id.Calendar_flipper_view);
//		
//		calendar = new CalendarActivity(this);
//	}
//
//	@Override
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		
//		Calendar thisMonth = Calendar.getInstance();
//		thisMonth.set(Calendar.DAY_OF_MONTH,1);
//		calendar.setmThisMonth(thisMonth);
//		calendar.getCalendar(thisMonth);
//		flipper.addView(calendar);
//		
//		
////		mThisMonthCalendar = Calendar.getInstance();
////		mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
////		getCalendar(mThisMonthCalendar);
////
////		GridView v1 = getGridView(mThisMonthCalendar);
////		((ViewGroup)v1.getParent()).removeView(v1);
////		
////		
////		flipper.addView(v1);
////		
////		GridView v2 = getGridView(getLastMonth(mThisMonthCalendar));
////		((ViewGroup)v2.getParent()).removeView(v2);
////		
////		flipper.addView(v2);
////		
////		GridView v3 = getGridView(getNextMonth(mThisMonthCalendar));
////		((ViewGroup)v3.getParent()).removeView(v3);
////		
////		flipper.addView(v3);
//	}
//	
//	
//	public boolean onTouch(View v, MotionEvent event) {
////		if(v != flipper) return false;
//		
//		if(event.getAction() == MotionEvent.ACTION_DOWN) {
//			downX = event.getX();
//		}
//		else if(event.getAction() == MotionEvent.ACTION_UP){
//			upX = event.getX();
//
//			if( 5 < downX - upX ) {  // in case of left
//
//				flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
//				flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
//						R.anim.push_left_out));
//
//
//
//				flipper.setInAnimation(AnimationUtils.loadAnimation(this,
//						R.anim.wallpaper_open_enter));
//				flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
//						R.anim.wallpaper_open_exit));
//
//				if (currentIndex < (countIndexes-1)) {
//					flipper.showNext();
//
//					// update index buttons
//					currentIndex++;
//					updateIndexes();
//				}
//			} else if (upX - downX > 5){ // in case of right direction
//
//				
//				
//				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
//						R.anim.push_right_in));
//				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
//						R.anim.push_right_out));
//
//				if (currentIndex > 0) {
//					flipper.showPrevious();
//
//					// update index buttons
//					currentIndex--;
//					updateIndexes();
//				}
//			}
//		}
//		
//	
//
//		return true;
//	}
//	
//}
