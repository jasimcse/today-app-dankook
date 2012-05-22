package com.TODAY.HtmlParserByJuyoungJin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;


public class ccTimeTable
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	int code ;
	int division ;
	int point ;
	String professor ;
	private String title ;
	private String time ; // 분리 함수 필요

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccTimeTable()
	{
		code = 0 ;
		division = 0 ;
		point = 0 ;
		professor = new String() ;
		setTitle(new String()) ;
		setTime(new String()) ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccTimeTable(int code, int division, int point, String professor, String title, String time)
	{
		this.code = code ;
		this.division = division ;
		this.point = point ;
		this.professor = professor ;
		this.setTitle(title) ;
		this.setTime(time) ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public static String DayInt2String(int iDay)
	{
		String mDay = new String() ;
		switch(iDay)
		{
		case 1:		mDay = "일" ; break ;
		case 2:		mDay = "월" ; break ;
		case 3:		mDay = "화" ; break ;
		case 4:		mDay = "수" ; break ;
		case 5:		mDay = "목" ; break ;
		case 6:		mDay = "금" ; break ;
		case 7:		mDay = "토" ; break ;
		default :	mDay = "일" ;
		}		
		return mDay ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//	ccTimeTable 배열에서 오늘 날짜의 아이템만 뽑아낸다

	public static ccTimeTable[] setToday(ccTimeTable TT[])
	{
		//  일요일:1 ~ 토요일:7

		Calendar cal= Calendar.getInstance( ) ;
		String mDay = new String(DayInt2String(cal.get(Calendar.DAY_OF_WEEK))) ;				
		
		ArrayList<ccTimeTable> mList = new ArrayList<ccTimeTable> () ;

		for(int i=0; i<TT.length; i++)
		{
			if(TT[i].getTime().indexOf(mDay) > -1)
			{	
				mList.add(TT[i]) ;
			}
		}		
		return (ccTimeTable[])mList.toArray(new ccTimeTable[0]) ; 
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	//	String to ccTimeTable Array (2012-05-16)
	
	public static ccTimeTable[] stringToClass()
	{
		StringTokenizer ST = new StringTokenizer(ccHTMLParsing.DownloadHtml(1), "\n") ;
		ST.nextToken() ;
		ST.nextToken() ;

		ccTimeTable[] TT = new ccTimeTable[(ST.countTokens()-2)/6] ;
		int i = 0 ;

		while((ST.countTokens()-2) > 0) // -2 여유되면 제거하기
		{
			TT[i] = new ccTimeTable(
					Integer.parseInt(ST.nextToken()),
					Integer.parseInt(ST.nextToken()),
					Integer.parseInt(ST.nextToken()),
					ST.nextToken(),
					ST.nextToken(),
					ST.nextToken()) ;
			i++ ;
		}
				
		return setToday(TT) ;		
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	//	필요한 부분의 시작과 끝 인덱스를 구한뒤 잘라낸다

	public static String cleanData(StringBuilder html)
	{
		int SP = 0 ;
		int EP = 0 ;

		SP = html.indexOf("<tbody id=\"table_contents_1\">") + 31 ;
		EP = html.indexOf("</tbody>") ;
		String mResult = new String(html.toString().substring(SP, EP)) ;

		mResult = mResult.replaceAll("<tr>", "") ;
		mResult = mResult.replaceAll("</tr>", "") ;
		mResult = mResult.replaceAll("<td>", "") ;
		mResult = mResult.replaceAll("</td>", "") ;
		mResult = mResult.replaceAll("<th>", "") ;
		mResult = mResult.replaceAll("</th>", "") ;
		mResult = mResult.replaceAll("</a>", "") ;
		mResult = mResult.replaceAll("<th colspan=\"2\">", "") ;
		mResult = mResult.replaceAll("<th colspan=\"3\">", "") ;
		mResult = mResult.replaceAll("<td colspan=\"2\">", "") ;
		mResult = mResult.replaceAll("<td colspan=\"3\">", "") ;
		mResult = mResult.replaceAll("\t", "") ;
		mResult = mResult.replaceAll("\n\n\n", "\n") ;
		mResult = mResult.replaceAll("\n\n", "\n") ;
		
		return  mResult ;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
}
