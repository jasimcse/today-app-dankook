package com.TODAY.ListViewSet;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.TODAY.R;

public class ccTTAdapter extends BaseAdapter implements RemoveInterface
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	Context context ;	
	int layoutRes ;					//	Custom Cell View
	ArrayList<ccTTItem> list ;		//	Custom Cell Contents
	LayoutInflater inflater ;

	int getViewCount = 0 ;
	int convertViewCount = 0 ;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


	public View getView(int position, View convertView, ViewGroup parent)
	{
		getViewCount++ ;
		final int INDEX = position ;

		if(convertView == null)
		{
			convertViewCount++ ;
			convertView = inflater.inflate(layoutRes, parent, false) ;
		}
		convertView.setPadding(5, 5, 5, 5);
		TextView mTitle = (TextView)convertView.findViewById(R.id.TTtitle) ;
		TextView mTime = (TextView)convertView.findViewById(R.id.TTtime) ;
		TextView mRoom = (TextView)convertView.findViewById(R.id.TTroom) ;

		mTitle.setText(list.get(INDEX).TTtitle) ;
		mTime.setText(list.get(INDEX).TTtime) ;
		mRoom.setText(list.get(INDEX).TTroom) ;

		return convertView;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		inflater ?
	//			[ CustomCell.xml ] ~>{ Convert }~> [ View ]
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccTTAdapter(Context context, int layoutRes, ArrayList<ccTTItem> list)
	{
		this.context = context ;
		this.layoutRes = layoutRes ;
		this.list = list ;
		inflater  = LayoutInflater.from(context) ;
	}
	
	public void removeAllItems()
	{
		list.clear();
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public int getCount() { return list.size() ; }
	public Object getItem(int position) { return list.get(position) ; }
	public long getItemId(int position) { return position ; }
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
}