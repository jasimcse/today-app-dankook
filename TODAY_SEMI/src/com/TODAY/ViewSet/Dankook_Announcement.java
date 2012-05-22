package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


import com.TODAY.R;
import com.TODAY.HtmlParserByJuyoungJin.ccNotice;
import com.TODAY.ListViewSet.ccAnnouncementItem;
import com.TODAY.ListViewSet.ccNoticeItem;

public class Dankook_Announcement extends LinearLayout implements ParseInfo{
	View view;
	Detail_DankookAnnouncement detailView;
	ArrayList<ccNoticeItem> list;
	Context context;
	public Dankook_Announcement(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		// Layout
		
		//for texting
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_announcement, this, true);
		
	}
	@Override
	public void parsingInfo() {
		// need to modifiy
		// TODO Auto-generated method stub
		list = new ArrayList<ccNoticeItem>();
		ccNotice[] NN = ccNotice.stringToClass();
		for(int i=0;i<NN.length;i++)
		{
			list.add(new ccNoticeItem(NN[i].getNtitle(), NN[i].getNcampus(), NN[i].getNpart())) ;
		}
//		list.add(new ccNoticeItem("[국제]English Village기숙사입사생모집", "죽전", "학사"));
//		list.add(new ccNoticeItem("[창업]제5회장영실발명.창업대전", "천안", "일반"));
		//list.add(new ccAnnouncementItem("Announcement2", "test2"));
	}
	
	@Override
	public ArrayList<?> getArrayList() {
		
		// TODO Auto-generated method stub
		return list;
	}
	@Override
	public LinearLayout getDetailView() {
//		detailView = new Detail_DankookAnnouncement(context);
//		return detailView;
		return null;
	}
	
}
