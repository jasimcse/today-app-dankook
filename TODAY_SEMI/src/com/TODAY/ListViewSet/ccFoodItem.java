package com.TODAY.ListViewSet ;

import java.io.Serializable;

public class ccFoodItem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -373229382330438801L;
	String FoodCourtName;
	String FoodMenu ;

	public ccFoodItem(String FoodCourtName, String FoodMenu)
	{
		this.FoodCourtName = FoodCourtName ;
		this.FoodMenu = FoodMenu ;
	}
}