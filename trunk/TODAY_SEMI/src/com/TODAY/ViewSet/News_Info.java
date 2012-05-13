package com.TODAY.ViewSet;

public class News_Info {
	
	private String title;
	private String link;
	private String description;
	
	public News_Info()
	{
		
	}
	
	public News_Info(String _title, String _link, String _description)
	{
		title = _title;
		link = _link;
		description = _description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
