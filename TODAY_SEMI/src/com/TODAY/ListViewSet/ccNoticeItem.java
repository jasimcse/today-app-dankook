package com.TODAY.ListViewSet ;

import java.io.Serializable;

public class ccNoticeItem implements Serializable
{
	String NoticeTitle;
	String NoticeCampus;
	String NoticePart;

	public ccNoticeItem(String NoticeTitle, String NoticeCampus, String NoticePart)
	{
		this.NoticeTitle =NoticeTitle ;
		this.NoticeCampus = NoticeCampus ;
		this.NoticePart = NoticePart ;
	}
}