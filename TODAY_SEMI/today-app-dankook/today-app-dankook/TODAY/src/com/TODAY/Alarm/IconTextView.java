package com.TODAY.Alarm;

import com.TODAY.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IconTextView extends LinearLayout {

	/**
	 * Icon
	 */
	private ImageView mIcon;

	/**
	 * TextView 01
	 */
	private TextView mText01;

	/**
	 * TextView 02
	 */
	private TextView mText02;

	/**
	 * TextView 03
	 */

	public IconTextView(Context context, IconTextItem aItem) {
		super(context);

		// Layout Inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.alarmlist, this, true);
		
		
		
		// Set Icon
		mIcon = (ImageView) findViewById(R.id.alamImgView);			// image
		mIcon.setImageDrawable(aItem.getIcon());

		// Set Text 01
		mText01 = (TextView) findViewById(R.id.timeTxt);			//  textView 
		mText01.setText(aItem.getData(0));

		// Set Text 02
		mText02 = (TextView) findViewById(R.id.repeatingDayTxt);			// mon, tues... something like that
		mText02.setText(aItem.getData(1));

		// Set Text 03


	}

	/**
	 * set Text
	 *
	 * @param index
	 * @param data
	 */
	public void setText(int index, String data) {
		if (index == 0) {
			mText01.setText(data);
		} else if (index == 1) {
			mText02.setText(data);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * set Icon
	 *
	 * @param icon
	 */
	public void setIcon(Drawable icon) {
		mIcon.setImageDrawable(icon);
	}

}
