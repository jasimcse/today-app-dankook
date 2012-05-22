package com.TODAY.ListViewSet;

import java.io.Serializable;

public class ccAnnouncementItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String link;
	
	public ccAnnouncementItem() {
		// TODO Auto-generated constructor stub
	}

	public ccAnnouncementItem(String title, String link) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
