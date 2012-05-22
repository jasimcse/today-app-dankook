package com.TODAY.ListViewSet;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.TODAY.R;

public class ccFoodAdapter extends BaseAdapter implements RemoveInterface
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	Context context ;	
	int layoutRes ;					//	Custom Cell View
	ArrayList<ccFoodItem> list ;		//	Custom Cell Contents
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
		
		TextView mFCName = (TextView)convertView.findViewById(R.id.FoodCourtName) ;
		TextView mFM = (TextView)convertView.findViewById(R.id.FoodMenu) ;

		mFCName.setText(list.get(INDEX).FoodCourtName) ;
		mFM.setText(list.get(INDEX).FoodMenu) ;

		return convertView;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		inflater ?
	//			[ CustomCell.xml ] ~>{ Convert }~> [ View ]
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccFoodAdapter(Context context, int layoutRes, ArrayList<ccFoodItem> list)
	{
		this.context = context ;
		this.layoutRes = layoutRes ;
		this.list = list ;
		inflater  = LayoutInflater.from(context) ;
	}
	
	public void addItem(ccFoodItem item)
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