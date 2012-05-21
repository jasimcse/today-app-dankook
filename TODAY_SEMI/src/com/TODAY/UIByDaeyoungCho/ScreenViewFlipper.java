package com.TODAY.UIByDaeyoungCho;


import java.io.IOException;

import org.xml.sax.SAXException;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.TODAY.R;

/**
 * ScreenView Flipper
 *
 * @author Mike
 */
public class ScreenViewFlipper extends LinearLayout implements OnTouchListener {

	/**
	 * Count of index buttons. Default is 3
	 */
	public static int countIndexes = 3;

	/**
	 * Button Layout
	 */
	LinearLayout buttonLayout;

	/**
	 * Index button images
	 */
	ImageView[] indexButtons;

	/**
	 * Views for the Flipper
	 */
	View[] views;

	/**
	 * Flipper instance
	 */
	ViewFlipper flipper;

	/**
	 * X coordinate for touch down
	 */
	float downX;

	/**
	 * X coordinate for touch up
	 */
	float upX;

	/**
	 * Current index
	 */
	int currentIndex = 0;
	int indexForAdding = 0;
	ImageView lv;
	ImageView rv;
	/**
	 * Initialize
	 *
	 * @param context
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public ScreenViewFlipper(Context context) throws SAXException, IOException {
		super(context);

		this.setBackgroundColor(0xffbfbfbf);

		// Layout inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.screenview, this, true);

//		buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.setOnTouchListener(this);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.leftMargin = 50;

		indexButtons = new ImageView[countIndexes];		// countIndex = 3;
		views = new View[countIndexes];
		
		lv = (ImageView)findViewById(R.id.left_ImageView);
		rv = (ImageView)findViewById(R.id.right_ImageView);
		
		indexButtons[0] = (ImageView) findViewById(R.id.indexBtn1);
		indexButtons[1] = (ImageView) findViewById(R.id.indexBtn2);
		indexButtons[2] = (ImageView) findViewById(R.id.indexBtn3);
		OnClickListenerClass clickListenerClass = new OnClickListenerClass();
		rv.setOnClickListener(clickListenerClass);
		lv.setOnClickListener(clickListenerClass);
		
		
	}
	public void addViews(View view) throws Exception
	{
		Log.i("Index value" , String.valueOf(indexForAdding));
		views[indexForAdding++] = view;
		if(indexForAdding == 3)
		{
			registerViewsIntoFlipper();
		}	
	}
	
	public void registerViewsIntoFlipper() throws Exception
	{
		for(int i=0;i<3;i++)
		{
			if(views[i] == null)
				throw new Exception();
			else
				flipper.addView(views[i]);
		}
		
	}
	
	public void setCurIndexView(int index)		// for testing
	{
		currentIndex = index;
		for(int i=0;i<currentIndex;i++)
			flipper.showNext();
		updateIndexes();
		
	}

	/**
	 * Update the display of index buttons
	 */
	private void updateIndexes() {				// for testing
		for(int i = 0; i < countIndexes; i++) {
			if (i == currentIndex) {
				indexButtons[i].setImageResource(R.drawable.green);
			} else {
				indexButtons[i].setImageResource(R.drawable.white);
			}
		}
	}

	/**
	 * onTouch event handling
	 */
	public boolean onTouch(View v, MotionEvent event) {
//		if(v != flipper) return false;
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			downX = event.getX();
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			upX = event.getX();

			if( 5 < downX - upX ) {  // in case of left

				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_left_in));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_left_out));



				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.wallpaper_open_enter));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.wallpaper_open_exit));

				if (currentIndex < (countIndexes-1)) {
					flipper.showNext();

					// update index buttons
					currentIndex++;
					updateIndexes();
				}
			} else if (upX - downX > 5){ // in case of right direction

				
				
				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_right_in));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_right_out));

				if (currentIndex > 0) {
					flipper.showPrevious();

					// update index buttons
					currentIndex--;
					updateIndexes();
				}
			}
		}
		
	

		return true;
	}
	
	class OnClickListenerClass implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(rv.getId() == v.getId())		// right click 
			{
				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_left_in));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_left_out));



				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.wallpaper_open_enter));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.wallpaper_open_exit));

				if (currentIndex < (countIndexes-1)) {
					flipper.showNext();

					// update index buttons
					currentIndex++;
					updateIndexes();
				}
				else
				{
					Toast.makeText(getContext(), "The last index",Toast.LENGTH_SHORT).show();
					return;
				}
			}
			
			if(lv.getId() == v.getId())
			{
				flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_right_in));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
						R.anim.push_right_out));

				if (currentIndex > 0) {
					flipper.showPrevious();

					// update index buttons
					currentIndex--;
					updateIndexes();
				}
				else
				{
					Toast.makeText(getContext(), "The last index",Toast.LENGTH_SHORT).show();
					return;
				}
				
			}
		}
	}
	

}
