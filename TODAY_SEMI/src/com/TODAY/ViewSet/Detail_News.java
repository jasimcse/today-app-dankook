package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.TODAY.R;
import com.TODAY.ListViewSet.ccNewsAdapter;
import com.TODAY.UIByDaeyoungCho.LayoutInfo;
import com.TODAY.UIByDaeyoungCho.ScreenViewFlipper;
import com.TODAY.XMLParser.News_Info;

public class Detail_News extends LinearLayout{

	final int index = 3;
	private ArrayList<News_Info> News_List;
	Context curContext;
	public Detail_News(Context context) {
		super(context);
		
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.detail_news, this, true);
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	public Detail_News(Context context, ArrayList<News_Info> list, ScreenViewFlipper flipper, LayoutInfo layoutInfo, int counter) {
		super(context);
		News_List = list;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.detail_item_layout, this, true);
		

		//lv.setOnTouchListener(flipper);		// set the TouchListener

		// how to make the listview maeks flipper view eent
		

		TextView tv = (TextView)findViewById(R.id.dt_Caption);
		tv.setBackgroundResource(layoutInfo.getMappingColorForTV().get(counter));
		tv.setTextColor(layoutInfo.getMappingTextColor().get(counter));
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText(layoutInfo.getMappingCaption().get(index));		// �Ĵ�
		tv.setShadowLayer(1, 1, 1, Color.argb(0xAA, 0xFD, 0xFD, 0xFF));
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		//tv.setText

		
		final ListView lv = (ListView)findViewById(R.id.dt_ListView);
		lv.setBackgroundResource(layoutInfo.getMappingColorForLV().get(counter));
		lv.setPadding(15, 15, 15, 15);		
		curContext = context;
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(getContext(), lv.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(News_List.get(arg2).getLink()));
				curContext.startActivity(intent);
			}
		});
		
		
		ccNewsAdapter adapter = new ccNewsAdapter(context, R.layout.cellnews, list);
		lv.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
			
		
		// TODO Auto-generated constructor stub
	}

}
