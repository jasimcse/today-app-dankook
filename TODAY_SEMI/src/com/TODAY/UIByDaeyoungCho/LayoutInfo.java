package com.TODAY.UIByDaeyoungCho;

import java.util.TreeMap;

import android.graphics.Color;

import com.TODAY.R;

public class LayoutInfo {
	private TreeMap<Integer, Integer> mappingCellLayout;
	private TreeMap<Integer, String> mappingCaption;
	private TreeMap<Integer, Integer> mappingColorForTV;
	private TreeMap<Integer, Integer> mappingColorForLV;
	private TreeMap<Integer, Integer> mappingTextColor;
	public LayoutInfo() {
		// TODO Auto-generated constructor stub
		mappingCellLayout = new TreeMap<Integer, Integer>();
		mappingCaption = new TreeMap<Integer,String>();
		mappingColorForTV = new TreeMap<Integer, Integer>();
		mappingColorForLV = new TreeMap<Integer, Integer>();
		mappingTextColor = new TreeMap<Integer, Integer>(); 
				
		initMappingTextColor(mappingTextColor);
		initMappingCellLayout(mappingCellLayout);
		initMappingCaption(mappingCaption);
		initMappingColorForLV(mappingColorForLV);
		initMappingColorForTV(mappingColorForTV);
	}

	public TreeMap<Integer, Integer> getMappingTextColor() {
		return mappingTextColor;
	}

	public void setMappingTextColor(TreeMap<Integer, Integer> mappingTextColor) {
		this.mappingTextColor = mappingTextColor;
	}

	public void initMappingTextColor(TreeMap<Integer,Integer> treeMap)
	{
		treeMap.put(2, Color.rgb(0x0D, 0x87, 0xFE));
		treeMap.put(1, Color.rgb(0x55, 0x3F, 0x3F));
		treeMap.put(0, Color.rgb(0xE8, 0x5B, 0x6D));
		
	}

	public TreeMap<Integer, Integer> getMappingCellLayout() {
		return mappingCellLayout;
	}


	public void setMappingCellLayout(TreeMap<Integer, Integer> mappingCellLayout) {
		this.mappingCellLayout = mappingCellLayout;
	}


	public TreeMap<Integer, String> getMappingCaption() {
		return mappingCaption;
	}


	public void setMappingCaption(TreeMap<Integer, String> mappingCaption) {
		this.mappingCaption = mappingCaption;
	}


	public TreeMap<Integer, Integer> getMappingColorForTV() {
		return mappingColorForTV;
	}


	public void setMappingColorForTV(TreeMap<Integer, Integer> mappingColorForTV) {
		this.mappingColorForTV = mappingColorForTV;
	}


	public TreeMap<Integer, Integer> getMappingColorForLV() {
		return mappingColorForLV;
	}


	public void setMappingColorForLV(TreeMap<Integer, Integer> mappingColorForLV) {
		this.mappingColorForLV = mappingColorForLV;
	}


	public void initMappingColorForTV(TreeMap<Integer, Integer> treeMap)
	{
		//	    		treeMap = new TreeMap<Integer, String>();
		treeMap.put(0, R.drawable.roundcorner_rrr);
		treeMap.put(1, R.drawable.roundcorner_yyy);
		treeMap.put(2, R.drawable.roundcorner_bbb);
	}
	
	public void initMappingColorForLV(TreeMap<Integer, Integer> treeMap)
	{
		treeMap.put(0, R.drawable.roundcorner_r);
		treeMap.put(1, R.drawable.roundcorner_y);
		treeMap.put(2, R.drawable.roundcorner_b);
	}

	public void initMappingCellLayout(TreeMap<Integer, Integer> treeMap)
	{
		//	    		treeMap = new TreeMap<Integer, String>();
		treeMap.put(0, R.layout.cellweather);
		treeMap.put(1, R.layout.cellfood);
		treeMap.put(2, R.layout.celltimetable);
		treeMap.put(3, R.layout.cellnews);
		treeMap.put(4, R.layout.cell_memo);
		treeMap.put(5, R.layout.cellannouncement);
	}


	public void initMappingCaption(TreeMap<Integer, String> treeMap)
	{
		treeMap.put(0, new String("Weather"));
		treeMap.put(1, new String("Cafeteria Menu"));
		treeMap.put(2, new String("Time-Table"));
		treeMap.put(3, new String("News"));
		treeMap.put(4, new String("Memo"));
		treeMap.put(5, new String("Announcement"));
	}
}
