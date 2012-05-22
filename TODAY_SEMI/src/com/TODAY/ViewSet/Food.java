package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.TODAY.R;
import com.TODAY.HtmlParserByJuyoungJin.ccFood;
import com.TODAY.HtmlParserByJuyoungJin.ccHTMLParsing;
import com.TODAY.ListViewSet.ccFoodItem;

public class Food extends LinearLayout implements ParseInfo{

	View view;
	Context context;
	Detail_Food detailView;
	ArrayList<ccFoodItem> list;
	public Food(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_food, this, true);
		
		
	}
	


	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
//		ccHTMLParsing parser = new ccHTMLParsing();
//		String str = parser.DownloadHtml(0);
		list = new ArrayList<ccFoodItem>(); 	// for testing

		
		
		
		/************** will be used
		ccFood[] FD = ccFood.stringToClass();
		for(int i=0;i<FD.length;i++)
		{
			list.add(new ccFoodItem(FD[i].getStore(), FD[i].getMenu()));
		}
		
		************************/
		list.add(new ccFoodItem("ÇÐ»ý½Ä´ç", "ÇÑ¹ä 1,900 Áß½Ä:½Ò¹ä(½Ò:±¹³»»ê), ¾î¹¬±¹, °è¶õÂò, ÇØÃÊ¹«Ä§, ±èÄ¡(¹èÃß:±¹³»»ê) ¼®½Ä:½Ò¹ä(½Ò:±¹³»»ê), ¹Ì¿ª±¹, ²ÇÄ¡Á¶¸², ¼÷ÁÖ³ª¹°, ±èÄ¡(¹èÃß:±¹³»»ê)"));
		list.add(new ccFoodItem("ÇÐ»ý½Ä´ç", "¾ËÃµ 3,000 Áß½Ä:ÇÏÀÌ¶óÀÌ½º (µ·À°:¹Ì±¹»ê)&Ä¡Å²±î½º(°èÀ°:±¹³»»ê) ¼®½Ä:¿­¹«ºñºö¹ä"));
		list.add(new ccFoodItem("ÇÐ»ý½Ä´ç", "¼Ò´ã 3,000 Áß½Ä:³Ã¸Þ¹Ð¼Ò¹Ù*ÁÖ¸Ô¹ä ¼®½Ä:ÂüÄ¡±èÄ¡Âî°³(¹èÃß:±¹³»»ê)"));
		
		//Log.i("Food Parsing", str);
		
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
