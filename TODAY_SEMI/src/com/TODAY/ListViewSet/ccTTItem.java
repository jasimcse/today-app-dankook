package com.TODAY.ListViewSet ;

import java.io.Serializable;

public class ccTTItem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7083055106977459774L;
	String TTtitle ;
	String TTtime ;
	String TTroom ;

	public ccTTItem(String TTtitle, String TTtime, String TTroom)
	{
		this.TTtitle = TTtitle ;
		this.TTtime = TTtime ;
		this.TTroom = TTroom ;
	}
}