package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.ListViewSet.ccFoodAdapter;
import com.TODAY.ListViewSet.ccFoodItem;
import com.TODAY.UIByDaeyoungCho.*;


public class Detail_Food extends LinearLayout{

	final int index = 1;
	public Detail_Food(Context context, ArrayList<ccFoodItem> list, ScreenViewFlipper flipper, LayoutInfo layoutInfo, int counter) {
		super(context);

		//		TextView txt = new TextView(context);
		//		txt.setText("This is for testing");
		//		this.addView(txt);
		// TODO Auto-generated constructor stub

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.detail_item_layout, this, true);

		TextView tv = (TextView)findViewById(R.id.dt_Caption);
		tv.setBackgroundResource(layoutInfo.getMappingColorForTV().get(counter));
		tv.setTextColor(layoutInfo.getMappingTextColor().get(counter));
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText(layoutInfo.getMappingCaption().get(index));		// 占식댐옙
		tv.setShadowLayer(1, 1, 1, Color.argb(0xAA, 0xFD, 0xFD, 0xFF));
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		//tv.setText

		ListView lv = (ListView)findViewById(R.id.dt_ListView);
		lv.setBackgroundResource(layoutInfo.getMappingColorForLV().get(counter));
		lv.setPadding(15, 15, 15, 15);		
		
		




		ccFoodAdapter adapter = new ccFoodAdapter(context, layoutInfo.getMappingCellLayout().get(index), list);
		
		lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		// Parsing 占싸븝옙占쏙옙 占쏙옙占쏙옙鳴占�占쏙옙占쏙옙.

	}

}
