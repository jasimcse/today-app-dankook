package com.TODAY.ViewSet;

import java.util.ArrayList;

import com.TODAY.R;
import com.TODAY.ListViewSet.ccAnnouncementAdapter;
import com.TODAY.ListViewSet.ccAnnouncementItem;
import com.TODAY.ListViewSet.ccNoticeAdapter;
import com.TODAY.ListViewSet.ccNoticeItem;
import com.TODAY.ListViewSet.ccWeatherAdapter_For_Detail;
import com.TODAY.UIByDaeyoungCho.LayoutInfo;
import com.TODAY.UIByDaeyoungCho.ScreenViewFlipper;
import com.TODAY.XMLParser.WeatherForecastCondition;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Detail_DankookAnnouncement extends LinearLayout{

	final int index = 5;
	public Detail_DankookAnnouncement(Context context, ArrayList<ccNoticeItem> list, ScreenViewFlipper flipper, LayoutInfo layoutInfo, int counter) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.detail_item_layout, this, true);

		TextView tv = (TextView)findViewById(R.id.dt_Caption);
		tv.setBackgroundResource(layoutInfo.getMappingColorForTV().get(counter));
		tv.setTextColor(layoutInfo.getMappingTextColor().get(counter));
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText(layoutInfo.getMappingCaption().get(index));		// �Ĵ�
		tv.setShadowLayer(1, 1, 1, Color.argb(0xAA, 0xFD, 0xFD, 0xFF));
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		//tv.setText

		ListView lv = (ListView)findViewById(R.id.dt_ListView);
		lv.setBackgroundResource(layoutInfo.getMappingColorForLV().get(counter));
		lv.setPadding(15, 15, 15, 15);		
		

		ccNoticeAdapter adapter = new ccNoticeAdapter(context, R.layout.cellnotice, list);
		lv.setAdapter(adapter);
		
	}

}
