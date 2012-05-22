package com.TODAY.XMLParser;

import java.io.Serializable;

public class Weather_Info implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double temp_c = 0.0;
	private double humidity = 0.0;
	private String desc = "";
	private String cityName = "";
	
	public Weather_Info(String cityName, String desc, double temp_c, double humidity) {
		// TODO Auto-generated constructor stub
		this.cityName = cityName;
		this.desc = desc;
		this.temp_c = temp_c;
		this.humidity = humidity;
		
	}

}
