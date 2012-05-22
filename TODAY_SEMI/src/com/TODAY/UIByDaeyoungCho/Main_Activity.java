package com.TODAY.UIByDaeyoungCho;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.TreeMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.HtmlParserByJuyoungJin.ccTimeTable;
import com.TODAY.ListViewSet.RemoveInterface;
import com.TODAY.ListViewSet.ccAnnouncementAdapter;
import com.TODAY.ListViewSet.ccAnnouncementItem;
import com.TODAY.ListViewSet.ccFoodAdapter;
import com.TODAY.ListViewSet.ccFoodItem;
import com.TODAY.ListViewSet.ccMemoAdapter;
import com.TODAY.ListViewSet.ccNewsAdapter;
import com.TODAY.ListViewSet.ccNewsSimpleAdapter;
import com.TODAY.ListViewSet.ccNoticeAdapter;
import com.TODAY.ListViewSet.ccNoticeItem;
import com.TODAY.ListViewSet.ccTTAdapter;
import com.TODAY.ListViewSet.ccTTItem;
import com.TODAY.ListViewSet.ccWeatherAdapter;
import com.TODAY.ObjectSerializer.ObjectSerializer;
import com.TODAY.ViewSet.Dankook_Announcement;
import com.TODAY.ViewSet.Food;
import com.TODAY.ViewSet.Memo;
import com.TODAY.ViewSet.News;
import com.TODAY.ViewSet.ParseInfo;
import com.TODAY.ViewSet.TimeTable;
import com.TODAY.ViewSet.Weather;
import com.TODAY.XMLParser.News_Info;
import com.TODAY.XMLParser.WeatherCurrentCondition;
import com.TODAY.XMLParser.WeatherSet;




public class Main_Activity extends Activity
{
	private TextView textDate;
	private TextView textA ;
	private TextView []caption;
	private ListView []listView;
	
	
	private String layout_Info = "111000";
	private OnClickEventClass onclickEventClass;
	private TreeMap<Integer, String> caption_TreeMap;
	Intent intent;
	private int caption_Index = 0;	
	private boolean isCalledViewflipper = false;
	
	private Thread[] thread;
	private int threadIndex = 0;
	private int curIndexForThread = 0;
	private BaseAdapter ba = null;
	private BaseAdapter []baseAdapter = null;
	private Handler mHandler;
	private ProgressDialog progressDialog;
	RelativeLayout mainLayout;
	
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState)
	{	
		Calendar cal= Calendar.getInstance( ) ;
		String mDay = new String(ccTimeTable.DayInt2String(cal.get(Calendar.DAY_OF_WEEK))) ;

		TimeZone jst = TimeZone.getTimeZone ("Asia/Seoul");
		Calendar mCal = Calendar.getInstance ( jst ) ;
		
		
		
		// HTTP Access를 위해 SDK 3.0 이후 버젼에서 반드시 해줘야 하는 설정
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);        

		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_main);
		 
		
		caption = new TextView[3];
		listView = new ListView[3];
		caption_TreeMap = new TreeMap<Integer, String>();
		
		initializeCaption(caption);
		initializeListView(listView);
		initializeTreeMap(caption_TreeMap);
		
		textDate = (TextView)findViewById(R.id.mTV);
		
		textDate.setText(mCal.get(Calendar.YEAR) + "년 " + (mCal.get(Calendar.MONTH)+1) + "월 " + mCal.get(Calendar.DATE) + "일 " + mDay + "요일\n");
		
		mainLayout = (RelativeLayout) findViewById(R.id.main_Layout);
		 		
		
		
		TextView setupBtn = (TextView) findViewById(R.id.setup_Btn);
		setupBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeElementListView(layout_Info);			// delete all listView's Items		
				Intent intent = new Intent(getApplicationContext(), CategoryLayout.class);		// start Activity
				intent.putExtra(CategoryLayout.WHOCALL, CategoryLayout.MainActivity_Request);
				startActivity(intent);
				// to Show The List of Category
			}
		});
		
		mHandler = new Handler()
		{
			int completedTaskNum = 0;
			@Override
			public void handleMessage(Message msg) {
				
				// TODO Auto-generated method stub
				
				Log.i("msg Index", String.valueOf(msg.arg1));
				caption[msg.arg2].setText(caption_TreeMap.get(msg.arg1));		// set the Caption
				//listView[msg.arg2].setOnItemClickListener(onclickEventClass);		// set the listener
				//listView[msg.arg2].setAdapter(ba);
				
				
				completedTaskNum++;
				//Log.i("compltedTaskNum",String.valueOf(completedTaskNum));
				if(completedTaskNum == 3)
				{
					for(int i=0;i<3;i++)
					{
						listView[i].setAdapter(baseAdapter[i]);
						listView[i].setOnItemClickListener(onclickEventClass);
					}
					Log.i("compltedTaskNum",String.valueOf(completedTaskNum));
					completedTaskNum = 0;

					mainLayout.setVisibility(View.VISIBLE);
					
					for(int i=0;i<3;i++)
					{
						
					}
					
					progressDialog.dismiss();
					
					
					
					//startActivity(intent);
				}
				
				
			}
			
			
		};
		
		onclickEventClass = new OnClickEventClass();
		intent = new Intent(getApplicationContext(), ViewFlipperActivity.class);		// start Activity
	}
	
	public void initializeTreeMap(TreeMap<Integer, String> treeMap)
	{
//		treeMap = new TreeMap<Integer, String>();
		treeMap.put(0, new String("날씨"));
		treeMap.put(1, new String("식단"));
		treeMap.put(2, new String("시간표"));
		treeMap.put(3, new String("뉴스"));
		treeMap.put(4, new String("메모"));
		treeMap.put(5, new String("공지사항"));
	}
	
	public void showSelectedItemLayout(String layout_Info) throws Exception
	{
		//layout_Info = "011100";
		int totalIndex = 0;
		
		for(int i=0;i<6;i++)
		{
			if(layout_Info.charAt(i) == '1')
			{
				curIndexForThread = i;
				thread[threadIndex] = new Thread(new Runnable()
				{
					int index = curIndexForThread;
					int numOfThread = threadIndex;
					@Override
					synchronized public void run() {
						// TODO Auto-generated method stub
						try {
							ba = getListViewAdapter(index);
							baseAdapter[numOfThread] = ba;
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//caption[numOfThread].setText(caption_TreeMap.get(index));		// set the Caption
						//listView[numOfThread].setOnItemClickListener(onclickEventClass);		// set the listener
						//listView[numOfThread].setAdapter(ba);
						//ba.notifyDataSetChanged();
						Log.d("Here is called", "called");
						Message msg = Message.obtain();
						msg.arg1 = index;
						msg.arg2 = numOfThread;
						mHandler.sendMessage(msg);			// Message..
					}
				});
				threadIndex++;
//				ba = getListViewAdapter(i);
//				caption[caption_Index++].setText(caption_TreeMap.get(i));		// set the Caption
//				listView[totalIndex].setOnItemClickListener(onclickEventClass);		// set the listener
//				listView[totalIndex++].setAdapter(ba);
//				ba.notifyDataSetChanged();
			}
		}
		for(int i=0;i<3;i++)
		{
			thread[i].start();
		}
		
		caption_Index = 0;			// must initilize this
	}
	
	public void removeElementListView(String currentIndex)
	{
		for(int i=0;i<3;i++)
		{
			((RemoveInterface)listView[i].getAdapter()).removeAllItems();
			
			// check out this if it works or not
			((BaseAdapter)listView[i].getAdapter()).notifyDataSetChanged();
			
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public BaseAdapter getListViewAdapter(int i) throws Exception {
		// TODO Auto-generated method stub
		BaseAdapter retAdapter = null;
		switch(i)
		{
		case 0:	// 날씨
		{
			Weather weather = new Weather(this);
			weather.parsingInfo();
			WeatherCurrentCondition tmp = weather.getWeatherSet().getWeatherCurrentCondition();
			ArrayList<WeatherCurrentCondition> tmpArray = new ArrayList<WeatherCurrentCondition>();
			//WeatherCurrentCondition abc = new WeatherCurrentCondition();
			tmpArray.add(tmp);
			Log.i("Weather Info", tmp.getCityName());
			retAdapter = new ccWeatherAdapter(this, R.layout.cellweather,tmpArray );		// it only has current weather Item
			/****
			 * 
			 * However weather has WeatherAdapter_For_Detail
			 * 
			 */
			
			intent.putExtra("WeatherArray", ObjectSerializer.serialize((ArrayList<WeatherSet>)weather.getArrayList()));
			//retAdapter = new ccFoodAdapter(this, R.id.foodmenu);
			
			break;
		}
		case 1:	// 식단
		{
			Food food = new Food(this);
			food.parsingInfo();
			//ArrayList<ccFoodItem> list = (ArrayList<ccFoodItem>) food.getArrayList();
			ArrayList<ccFoodItem> list = (ArrayList<ccFoodItem>)food.getArrayList();
			
			//list.add(new ccFoodItem("hello", "hello"));
			
			retAdapter = new ccFoodAdapter(this, R.layout.cellfood,list);
			intent.putExtra("FoodArray", ObjectSerializer.serialize((ArrayList<ccFoodItem>)food.getArrayList()));
			//saveArrayListForIntent((ArrayList<Object>)food.getArrayList());
			
			break;
		}
		case 2: // 시간표
		{
			TimeTable timeTable = new TimeTable(this);
			
			timeTable.parsingInfo();
			//ArrayList<ccTTItem> list = (ArrayList<ccTTItem>)timeTable.getArrayList();
			ArrayList<ccTTItem> list = (ArrayList<ccTTItem>)timeTable.getArrayList();
//			list.add(new ccTTItem("hi","hi","hi"));
			
			intent.putExtra("TimeTableArray", ObjectSerializer.serialize((ArrayList<ccTTItem>)timeTable.getArrayList()));
			retAdapter = new ccTTAdapter(this, R.layout.celltimetable, (ArrayList<ccTTItem>)timeTable.getArrayList());
			
			//saveArrayListForIntent((ArrayList<Object>)timeTable.getArrayList());
			break;
		}
		case 3: // 뉴스
		{
			News news = new News(this);
			news.parsingInfo();
			//ArrayList<News_Info> list = (ArrayList<News_Info>)news.getArrayList();
			ArrayList<News_Info> list = new ArrayList<News_Info>();
			//list.add(new News_Info("title", "link", "desc"));
			list = (ArrayList<News_Info>)news.getArrayList();
			
			//saveArrayListForIntent((ArrayList<Object>)news.getArrayList());
			intent.putExtra("NewsArray", ObjectSerializer.serialize((ArrayList<News_Info>)news.getArrayList()));
			
			retAdapter = new ccNewsSimpleAdapter(this, R.layout.cellnews_simple, list);
			break;
		}
		case 4: // 메모
			Memo memo = new Memo(this);
			memo.parsingInfo();
			retAdapter = new ccMemoAdapter(this, R.layout.cell_memo, (ArrayList<String>)memo.getArrayList());
			intent.putExtra("MemoArray", ObjectSerializer.serialize((ArrayList<String>)memo.getArrayList()));
			//saveArrayListForIntent((ArrayList<Object>)memo.getArrayList());
			break;
		case 5:	// 단국대 공지사항
			Dankook_Announcement announcement = new Dankook_Announcement(this);
			announcement.parsingInfo();
			
			retAdapter = new ccNoticeAdapter(this, R.layout.cellnotice, (ArrayList<ccNoticeItem>)announcement.getArrayList());
			intent.putExtra("AnnouncmentArray", ObjectSerializer.serialize((ArrayList<ccNoticeItem>)announcement.getArrayList()));
			//saveArrayListForIntent((ArrayList<Object>)announcement.getArrayList());
			break;
		}
		return retAdapter;
		
	}

	private void restoreFormSavedState() {
		// TODO Auto-generated method stub
		SharedPreferences pref = getSharedPreferences(CategoryLayout.categoryInfo,Activity.MODE_PRIVATE);

		if(pref == null)
		{		
			SharedPreferences.Editor prefEditor = pref.edit();
			
			// for testing
			prefEditor.putString(CategoryLayout.prefKey, "111000");			// 날씨, 식단, 시간표 현재
			prefEditor.commit();
			layout_Info = new String("111000");
			return;
		}

		if( (pref != null) && pref.contains(CategoryLayout.prefKey))
		{
			String itemInfo  = pref.getString(CategoryLayout.prefKey, "");
			
			layout_Info = new String(itemInfo);
		}
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if(isCalledViewflipper == true)
		{
			isCalledViewflipper = false;
			return;
		}
		
		mainLayout.setVisibility(View.INVISIBLE);
		progressDialog = ProgressDialog.show(this,"Running Program", "Wait for a while", true);
		thread = new Thread[3];		// Thread 초기화
		baseAdapter = new BaseAdapter[3];
		threadIndex= 0 ;			// Thread index 초기화	
		restoreFormSavedState();
		try {
			Log.i("Layout_Info",layout_Info);
			//layout_Info="011100";
			showSelectedItemLayout(layout_Info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		for(int i=0;i<passingArrayList.length;i++)
//		{
//			passingArrayList[i].clear();
//		}
//showSelectedItemLayout(layout_Info);
		
	}


	private void initializeListView(ListView[] listView) {
		// TODO Auto-generated method stub

		listView[0] = (ListView)findViewById(R.id.first_ListView);
		listView[1] = (ListView)findViewById(R.id.second_ListView);
		listView[2] = (ListView)findViewById(R.id.thrid_ListView);
		for(int i=0;i<3;i++)
		{
			listView[i].setOnItemClickListener(onclickEventClass);
		}

	}

	private void initializeCaption(TextView[] caption) {
		// TODO Auto-generated method stub
		caption[0] = (TextView)findViewById(R.id.mT1);
		caption[1] = (TextView)findViewById(R.id.mT2);
		caption[2] = (TextView)findViewById(R.id.mT3);

	}
	
	
	
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		SharedPreferences pref = getSharedPreferences("CategoryInfo", Activity.MODE_PRIVATE);
//		SharedPreferences.Editor prefEditor = pref.edit();
//		prefEditor.putString("CategoryString", layout_Info);
		intent.putExtra("CategoryInfo", layout_Info);
		
	}





	public class OnClickEventClass implements View.OnClickListener, OnItemClickListener
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub			
			intent.putExtra("CategoryInfo", layout_Info);

			int curIndex = 0;
			if(v.getId() == listView[0].getId())
				curIndex = 0;
			if(v.getId() == listView[1].getId())
				curIndex = 1;
			if(v.getId() == listView[2].getId())
				curIndex = 2;
			intent.putExtra("CurIndex", curIndex);
			startActivity(intent);
			isCalledViewflipper = true;
			
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			// save the information as Preference
			

			intent.putExtra("CategoryInfo", layout_Info);
			
			int curIndex = 0;
			
			if(arg0.getId() == listView[0].getId())
				curIndex = 0;
			if(arg0.getId() == listView[1].getId())
				curIndex = 1;
			if(arg0.getId() == listView[2].getId())
				curIndex = 2;
			intent.putExtra("CurIndex", curIndex);
			startActivity(intent);
			isCalledViewflipper = true;
		}
	}
	
	
	// Making thread after ...
	public class RunnableForThread implements Runnable
	{
		ParseInfo object;
		RunnableForThread(ParseInfo obj)
		{
			object = obj;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			object.parsingInfo();
		}
	}
	

}
