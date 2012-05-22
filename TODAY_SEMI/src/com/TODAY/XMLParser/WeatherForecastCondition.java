package com.TODAY.XMLParser;

import java.io.Serializable;

/**
 * Holds the information for <forecast_conditions> tag
 */
public class WeatherForecastCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6492844141030554960L;
	private String dayofWeek = null;
	private Integer tempMin = null;
	private Integer tempMax = null;
	private String iconURL = null;
	private String condition = null;

	public WeatherForecastCondition() {

	}

	public String getDayofWeek() {
		return dayofWeek;
	}

	public void setDayofWeek(String dayofWeek) {
		this.dayofWeek = dayofWeek;
	}

	public Integer getTempMinCelsius() {
		return tempMin;
	}

	public void setTempMinCelsius(Integer tempMin) {
		this.tempMin = tempMin;
	}

	public Integer getTempMaxCelsius() {
		return tempMax;
	}

	public void setTempMaxCelsius(Integer tempMax) {
		this.tempMax = tempMax;
	}

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
