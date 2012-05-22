
package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.TODAY.R;
import com.TODAY.XMLParser.*;

public class News extends LinearLayout implements ParseInfo{
	
	private ArrayList<Object> newsInfo;
	private Context context;

	public News(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_news, this, true);
		this.context = context;
	}

	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
		
		newsInfo = new ArrayList<Object>();
		SAX_Parser sax_Parser = null;
		try {
			sax_Parser = new SAX_Parser("http://media.daum.net/syndication/today_sisa.rss", "news");
			sax_Parser.start_Parsing();
			
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			
			// Here we have to show the Text;
			newsInfo = sax_Parser.getArrayList();
			newsInfo.remove(0);			// first one is just a caption
			
			/********
			 * 
			 * temporary making this as comments
			 */
			
//			TextView txt = (TextView)findViewById(R.id.newsTxt);
//			int size = newsInfo.size();
//			for(int i=0;i<4;i++)
//			{
//				News_Info tmp = (News_Info)newsInfo.get(i);
//				txt.setText(txt.getText() + tmp.getTitle() + "\n");
//			}
		}
	}

	@Override
	public ArrayList<?> getArrayList() {
		// TODO Auto-generated method stub
		return newsInfo;
	}

	@Override
	public LinearLayout getDetailView() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	

}
