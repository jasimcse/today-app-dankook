package com.TODAY.ViewSet;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.ListViewSet.ccNewsAdapter;
import com.TODAY.ListViewSet.ccTTAdapter;
import com.TODAY.ListViewSet.ccTTItem;
import com.TODAY.UIByDaeyoungCho.LayoutInfo;
import com.TODAY.UIByDaeyoungCho.ScreenViewFlipper;

public class Detail_TimeTable extends LinearLayout{
	
	final int index = 2;
	public Detail_TimeTable(Context context, ArrayList<ccTTItem> list, ScreenViewFlipper flipper, LayoutInfo layoutInfo, int counter) throws SAXException, IOException {
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
		
		// layout~~~~~ 
		
		ccTTAdapter adapter = new ccTTAdapter(context, R.layout.celltimetable, list);
		lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	
		// TODO Auto-generated constructor stub
	}

}
