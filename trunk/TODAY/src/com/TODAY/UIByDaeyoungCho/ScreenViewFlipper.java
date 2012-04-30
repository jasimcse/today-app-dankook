package com.TODAY.UIByDaeyoungCho;


import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.TODAY.R;
import com.TODAY.XMLParser.XmlParser;

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

		buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.setOnTouchListener(this);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.leftMargin = 50;

		indexButtons = new ImageView[countIndexes];		// countIndex = 3;
		views = new TextView[countIndexes];

		XmlParser xp = new XmlParser("http://feeds.feedburner.com/naver_news_popular");
		ArrayList<String> tmp1 = xp.getListofInfo("title");
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<tmp1.size();j++)
		{
			sb.append(tmp1.get(j).toString());
			if(j == 8)
				break;
		}


		/*********** Temporary coding *******************/
		
		TextView curView = new TextView(context);

		curView.setText("View #" + 1 + "\n" + sb.toString());
		curView.setTextColor(Color.BLACK);
		curView.setTextSize(10);


		views[0] = curView;



		XmlParser xp1 = new XmlParser("http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=108");
		ArrayList<String> tmp2 = xp1.getListofInfo("wf");
		StringBuilder sb2 = new StringBuilder();
		for(int j=0;j<tmp2.size();j++)
		{
			sb.append(tmp2.get(j).toString());
			if(j == 1)
				break;
		}

		curView = new TextView(context);
		curView.setText("View #" + 2 + "\n" + sb2.toString());


		curView.setTextColor(Color.WHITE);
		curView.setTextSize(10);


		views[1] = curView;



		curView = new TextView(context);

		curView.setText("View #" + 3 + "\n" + "학교 공지사항");


		curView.setTextColor(Color.RED);
		curView.setTextSize(10);


		views[2] = curView;

		/*********** Temporary coding *******************/



		for(int i = 0; i < countIndexes; i++) {
			indexButtons[i] = new ImageView(context);

			if (i == currentIndex) {
				indexButtons[i].setImageResource(R.drawable.green);
			} else {
				indexButtons[i].setImageResource(R.drawable.white);
			}

			indexButtons[i].setPadding(10, 10, 10, 10);
			buttonLayout.addView(indexButtons[i], params);

			// 꼭 Textview일 필요는 없다. -> just add the textbox(for just testing)
			// xml parsing







			flipper.addView(views[i]);
		}
		
		


	}



	/**
	 * Update the display of index buttons
	 */
	private void updateIndexes() {
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
		if(v != flipper) return false;

		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			downX = event.getX();
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			upX = event.getX();

			if( upX < downX ) {  // in case of right direction

				//flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
				//		R.anim.push_left_in));
				//flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
				//		R.anim.push_left_out));



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
			} else if (upX > downX){ // in case of left direction

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



}
