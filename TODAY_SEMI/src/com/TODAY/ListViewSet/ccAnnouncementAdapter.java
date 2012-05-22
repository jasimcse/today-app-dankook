package com.TODAY.ListViewSet;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.TODAY.R;

public class ccAnnouncementAdapter extends BaseAdapter implements RemoveInterface
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	Context context ;	
	int layoutRes ;					//	Custom Cell View
	ArrayList<ccNoticeItem> list ;		//	Custom Cell Contents
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
		TextView title = (TextView)convertView.findViewById(R.id.Announcement_Title) ;
		TextView link = (TextView)convertView.findViewById(R.id.Announcment_Desc) ;
		
		

		//title.setText(list.get(INDEX).getTitle());
		//title.setText(list.get(INDEX).getLink());
		//mFCName.setText(list.get(INDEX).FoodCourtName) ;
		//mFM.setText(list.get(INDEX).FoodMenu) ;

		return convertView;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		inflater ?
	//			[ CustomCell.xml ] ~>{ Convert }~> [ View ]
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccAnnouncementAdapter(Context context, int layoutRes, ArrayList<ccNoticeItem> list)
	{
		this.context = context ;
		this.layoutRes = layoutRes ;
		this.list = list ;
		inflater  = LayoutInflater.from(context) ;
	}
	
	public void addItem(ccNoticeItem item)
	{
		list.add(item);
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