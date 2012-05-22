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
import com.TODAY.ListViewSet.ccMemoAdapter;
import com.TODAY.ListViewSet.ccNewsAdapter;
import com.TODAY.UIByDaeyoungCho.LayoutInfo;
import com.TODAY.UIByDaeyoungCho.ScreenViewFlipper;

public class Detail_Memo extends LinearLayout {
	
	final int index = 4;
	public Detail_Memo(Context context, ArrayList<String> list, ScreenViewFlipper flipper, LayoutInfo layoutInfo, int counter) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.detail_item_layout, this, true);

		TextView tv = (TextView)findViewById(R.id.dt_Caption);
		tv.setBackgroundResource(layoutInfo.getMappingColorForTV().get(counter));
		tv.setTextColor(layoutInfo.getMappingTextColor().get(counter));
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText(layoutInfo.getMappingCaption().get(index));		// �Ĵ�
		tv.setShadowLayer(1, 1, 1, Color.argb(0xAA, 0xFD, 0xFD, 0xFF));

		
		//LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		ListView lv = (ListView)findViewById(R.id.dt_ListView);
		lv.setBackgroundResource(layoutInfo.getMappingColorForLV().get(counter));
		lv.setPadding(15, 15, 15, 15);		
		
		ccMemoAdapter adapter = new ccMemoAdapter(context,R.layout.cell_memo,list);
		lv.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
		
		//ccNewsAdapter adapter = new ccNewsAdapter(context, R.layout.cellnews, list);
	}

}
