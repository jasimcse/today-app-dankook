package com.TODAY.ViewSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.TODAY.R;

import com.TODAY.HtmlParserByJuyoungJin.ccTimeTable;
import com.TODAY.Note.*;

public class Memo extends LinearLayout implements ParseInfo{
	
	private Context context;
	private Detail_Memo detailView;
	private String memoTxt = "This is memo testing";
	private ArrayList<String> list;
	private NotesDbAdapter mDbHelper;
	
	public Memo(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_memo, this, true);
		this.context = context;
	}

	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
		
        mDbHelper = new NotesDbAdapter(context);
        mDbHelper.open();       
		
		list = new ArrayList<String>();
		
		String curDate = getCurrentDate();
		Cursor cur = mDbHelper.fetchNoteByDay(curDate);		/// need to modify
		if(cur.getCount() > 0)
		{
			Log.i("2012-5-2's Memo",cur.getString(cur.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
			memoTxt = cur.getString(cur.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY));
		}
		
		else		// empty Note
		{
			memoTxt = "오늘의 메모가 없습니다";
		}
			
		cur.close();
		list.add(memoTxt);
		mDbHelper.close();
	
	}
	
	public String getCurrentDate()
	{
		Calendar cal= Calendar.getInstance( ) ;
		String mDay = new String(ccTimeTable.DayInt2String(cal.get(Calendar.DAY_OF_WEEK))) ;

		TimeZone jst = TimeZone.getTimeZone ("Asia/Seoul") ;
		Calendar mCal = Calendar.getInstance ( jst ) ;
		String curDate =  mCal.get(Calendar.YEAR)+ "-" + (mCal.get(Calendar.MONTH)+1) + "-" + mCal.get(Calendar.DATE);

		Log.i("Current Date", curDate);
		
		return curDate;
		
	}

	public String getMemoText()
	{
		return memoTxt;
	}
	
	@Override
	public ArrayList<?> getArrayList() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public LinearLayout getDetailView() {
		// TODO Auto-generated method stub
		return null;
	}

}
