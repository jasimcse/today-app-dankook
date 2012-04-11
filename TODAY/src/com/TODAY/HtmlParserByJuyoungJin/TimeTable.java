package com.JJ.ex120326a;

public class TimeTable
{
	private int tCode ;
	private int tDivision ;
	private int tPoint ;
	private String tProfessor ;
	private String tTitle ;
	private String tTime ; // 분리 함수 필요
	
	public TimeTable()
	{
		tCode = 0 ;
		tDivision = 0 ;
		tPoint = 0 ;
		tProfessor = new String() ;
		tTitle = new String() ;
		tTime = new String() ;
	}
	
	public TimeTable(int mCode, int mDivision, int mPoint, String mProfessor, String mTitle, String mTime)
	{
		tCode = mCode ;
		tDivision = mDivision ;
		tPoint = mPoint ;
		tProfessor = mProfessor ;
		tTitle = mTitle ;
		tTime = mTime ;
	}
	
	public void setCode(int mCode) { tCode = mCode ; }
	public void setDivision(int mDivision) { tDivision = mDivision ; }
	public void setPoint(int mPoint) { tPoint = mPoint ; }
	public void setProfessor(String mProfessor) { tProfessor = mProfessor ; }
	public void setTitle(String mTitle) { tTitle = mTitle ; }
	public void setTime(String mTime) { tTime = mTime ; }
	
	public int getCode() { return tCode ; }
	public int getDivision() { return tDivision ; }
	public int getPoint() { return tPoint ; }
	public String getProfessor() { return tProfessor ; }
	public String getTitle() { return tTitle ; }
	public String getTime() { return tTime ; }
}
