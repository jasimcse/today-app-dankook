package com.TODAY.UIByDaeyoungCho;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.TODAY.R;
import com.TODAY.ListViewSet.ccAnnouncementItem;
import com.TODAY.ListViewSet.ccFoodItem;
import com.TODAY.ListViewSet.ccNoticeItem;
import com.TODAY.ListViewSet.ccTTItem;
import com.TODAY.ObjectSerializer.ObjectSerializer;
import com.TODAY.ViewSet.Detail_DankookAnnouncement;
import com.TODAY.ViewSet.Detail_Food;
import com.TODAY.ViewSet.Detail_Memo;
import com.TODAY.ViewSet.Detail_News;
import com.TODAY.ViewSet.Detail_TimeTable;
import com.TODAY.ViewSet.Detail_Weather;
import com.TODAY.XMLParser.News_Info;
import com.TODAY.XMLParser.WeatherSet;


/**
 * ViewFlipper
 *
 * @author Mike
 */
public class ViewFlipperActivity extends Activity {

	ScreenViewFlipper flipper;
	
	
	ArrayList<WeatherSet> weatherList;
	ArrayList<ccFoodItem> foodList;
	ArrayList<ccTTItem> timeTablelist;
	ArrayList<News_Info> newsList;
	ArrayList<String> memoList;
	ArrayList<ccNoticeItem> announcementList;
	LayoutInfo layoutInfo;
	
//	TreeMap<Integer, Integer> mapping

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInfo = new LayoutInfo();
    }
    
 
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerViewIntoFlipper();
		
		Log.i("View0", flipper.views[0].toString());
		Log.i("View1", flipper.views[1].toString());
		Log.i("View2", flipper.views[2].toString());
		
		
		
	}
    
    public void registerViewIntoFlipper()
    {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        try {
        	Intent intent = getIntent();
        	String str = intent.getStringExtra("CategoryInfo");
        	        			
        	weatherList = (ArrayList<WeatherSet>) ObjectSerializer.deserialize(intent.getStringExtra("WeatherArray"));
        	foodList = (ArrayList<ccFoodItem>) ObjectSerializer.deserialize(intent.getStringExtra("FoodArray"));
        	timeTablelist = (ArrayList<ccTTItem>) ObjectSerializer.deserialize(intent.getStringExtra("TimeTableArray"));
        	newsList = (ArrayList<News_Info>) ObjectSerializer.deserialize(intent.getStringExtra("NewsArray"));
        	memoList = (ArrayList<String>) ObjectSerializer.deserialize(intent.getStringExtra("MemoArray"));
        	announcementList = (ArrayList<ccNoticeItem>) ObjectSerializer.deserialize(intent.getStringExtra("AnnouncmentArray"));
        	
        	int curIndex = intent.getIntExtra("CurIndex", 0);
        	Log.i("CurrentIndex", String.valueOf(curIndex));
			flipper = new ScreenViewFlipper(this);
			getValidObject(str);
			//flipper.registerViewsIntoFlipper();
			flipper.setCurIndexView(curIndex);
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        setContentView(flipper, params);
        
    }



	public void getValidObject(String str) throws Exception
    {
    	int listCounter = 0;
    	for(int i=0;i<str.length();i++)
    	{
    		if(str.charAt(i) == '1')
    		{
    			int index = i;
	    		switch(index)
	    		{
	    		case 0:		 // 이 부분을 수정한다.
	    			// List<WeatherSet>.. form.
	    			Detail_Weather dw = new Detail_Weather(this, weatherList, flipper, layoutInfo, listCounter++);
	    			flipper.addViews(dw);
	    			break;
	    		case 1:
	    			Detail_Food df = new Detail_Food(this,foodList,flipper, layoutInfo,listCounter++);

	    			flipper.addViews(df);	    			
	    			break;
	    		case 2:
	    			Detail_TimeTable dt = new Detail_TimeTable(this,timeTablelist,flipper, layoutInfo, listCounter++);
	    			flipper.addViews(dt);
	    			break;
	    		case 3:
	    			Detail_News dn = new Detail_News(this,newsList,flipper,layoutInfo,listCounter++);
	    			flipper.addViews(dn);
	    			break;
	    		case 4:
	    			Detail_Memo memo = new Detail_Memo(this,memoList,flipper,layoutInfo,listCounter++);
	    			flipper.addViews(memo);
	    			break;
	    		case 5:
	    			Detail_DankookAnnouncement notice = new Detail_DankookAnnouncement(this, announcementList, flipper, layoutInfo, listCounter++);
	    			flipper.addViews(notice);
	    			break;
	    		}	    		
    		}
    	}
    	
    	
    }
	
	public void getResourceByCounter(int counter)
	{
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//flipper.removeAllViews();
	}
    
}