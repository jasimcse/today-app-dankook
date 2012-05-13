package com.TODAY.Alarm;

import java.io.Serializable;

public class RegisteredAlarmInfo implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String time = "";
	private String repeatingDay = "";
	
	
	public RegisteredAlarmInfo(int mHour, int mMinute, boolean[] chkDays)
	{
		time = String.valueOf(mHour) + " : " + String.valueOf(mMinute);
		for(int i=0;i<chkDays.length;i++)
		{
			if(chkDays[i] == true)
			{
				Day day = Day.MON;
				Day newDay = Day.values()[day.ordinal() + i];
				repeatingDay += newDay.name()+" ";
			}
		}
	}
	
	
	
	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getRepeatingDay() {
		return repeatingDay;
	}



	public void setRepeatingDay(String repeatingDay) {
		this.repeatingDay = repeatingDay;
	}



	public enum Day
	{
		MON, TUE, WED, THU, FRI, SAT, SUN
	}
}
