package com.TODAY.ListViewSet;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.TODAY.R;

public class ccMemoAdapter extends BaseAdapter implements RemoveInterface
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	Context context ;	
	int layoutRes ;					//	Custom Cell View
	ArrayList<String> list ;		//	Custom Cell Contents
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
		//TextView mFCName = (TextView)convertView.findViewById(R.id.FoodCourtName) ;
		//TextView mFM = (TextView)convertView.findViewById(R.id.FoodMenu) ;
		TextView txt = (TextView)convertView.findViewById(R.id.memoTxt);
		txt.setText(list.get(INDEX)) ;

		return convertView;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		inflater ?
	//			[ CustomCell.xml ] ~>{ Convert }~> [ View ]
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccMemoAdapter(Context context, int layoutRes, ArrayList<String> list)
	{
		this.context = context ;
		this.layoutRes = layoutRes ;
		this.list = list;	
		inflater  = LayoutInflater.from(context) ;
	}
	
	public void addItem(String item)
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