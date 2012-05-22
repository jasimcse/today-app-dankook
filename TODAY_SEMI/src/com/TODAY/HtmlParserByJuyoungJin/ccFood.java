package com.TODAY.HtmlParserByJuyoungJin;

import java.util.Calendar;
import java.util.StringTokenizer;


public class ccFood
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		(2012-05-22)

	private String store ;
	private String menu ;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		생성자 (2012-05-22)

	public ccFood()
	{
		setStore(new String()) ;
		setMenu(new String()) ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		생성자 (2012-05-22)

	public ccFood(String store, String menu)
	{
		store = store.replace("\\", " ") ;
		
		this.setStore(store) ;		
		this.setMenu(menu) ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		석식이나 중식 둘중 하나만 보여줄 수 있도록 자른다

	public static String removeMenu(String menu, int mOption)
	{
		String mRemove = new String("석식") ;
		String mRemain = new String(menu) ;
		StringBuilder mResult = new StringBuilder("") ;

		if(mOption == 2) { mRemove = "중식" ; }

		int SP = 0 ;
		int CP = mRemain.indexOf("\n") ;
		int EP = mRemain.length() ;
		int count = 0 ;

		while(mRemain.length() > 1)
		{
			if((mOption == 1) && (count == 8)) break ;
			if((mOption == 2) && (count == 6)) break ;
			String mTemp = mRemain.substring(SP, CP+1) ;
			mRemain = mRemain.substring(CP+1, EP) ;
			CP = mRemain.indexOf("\n") ;
			EP = mRemain.length() ;
			if(mTemp.indexOf(mRemove) > -1) { continue ; }
			mResult.append(mTemp) ;
			count ++ ;
		}		
		return mResult.toString().replaceAll((mOption==1)?"중식:":"석식:", "") ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		불필요한 부분 제거

	public static String removeFrom(String menu)
	{
		String mResult = new String(menu) ;

		int rSP = 0 ;
		int rEP = mResult.length() ;
		int SP = mResult.indexOf("(") ;
		int EP = mResult.indexOf(")") ;

		while(SP > -1)
		{
			StringBuilder mCut = new StringBuilder("") ;
			mCut.append(mResult.substring(rSP, SP)) ;
			mCut.append(mResult.substring(EP+1, rEP)) ;			
			mResult = mCut.toString() ; 
			rEP = mResult.length() ;
			SP = mResult.indexOf("(") ;
			EP = mResult.indexOf(")") ;
		}
		return mResult ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		오늘 날짜의 메뉴만 남긴다
	
	public static String setToday(String FD)
	{
		String mResult = new String(FD) ; 

		int SP = 0 ;
		int EP = 0 ;

		Calendar cal= Calendar.getInstance( ) ;
		switch(cal.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			SP = mResult.indexOf("일요일") + 18 ;
			EP = mResult.indexOf("비고") ;
			break ;
		case 2:
			SP = mResult.indexOf("월요일") + 18 ;
			EP = mResult.indexOf("화요일") ;
			break ;
		case 3:
			SP = mResult.indexOf("화요일") + 18 ;
			EP = mResult.indexOf("수요일") ;
			break ;
		case 4:
			SP = mResult.indexOf("수요일") + 18 ;
			EP = mResult.indexOf("목요일") ;
			break ;
		case 5:
			SP = mResult.indexOf("목요일") + 18 ;
			EP = mResult.indexOf("금요일") ;
			break ;
		case 6:
			SP = mResult.indexOf("금요일") + 18 ;
			EP = mResult.indexOf("토요일") ;
			break ;
		case 7:
			SP = mResult.indexOf("토요일") + 18 ;
			EP = mResult.indexOf("일요일") ;
			break ;
		default :
			break ;
		}				

		mResult = mResult.substring(SP+1, EP) ;		
		mResult = mResult.replaceAll("\n<한밥>", "\n<한밥>") ;
		mResult = mResult.replaceAll("<소담>", "\n<소담>") ;
		mResult = mResult.replaceAll("<알천>", "\n<알천>") ;
		mResult = mResult.replaceAll("<일품>", "\n<일품>") ;
		mResult = mResult.replaceAll("\\", "\n") ;
		mResult = mResult.replaceAll("중식", "\n중식") ;
		mResult = mResult.replaceAll("석식", "\n석식") ;

		mResult = removeFrom(mResult) ;
		mResult = removeMenu(mResult, 1) ;

		return mResult ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	//	String to ccFood Array (2012-05-22)
	
	public static ccFood[] stringToClass()
	{
		StringTokenizer ST = new StringTokenizer(setToday(ccHTMLParsing.DownloadHtml(2)), "\n") ;

		ccFood[] FD = new ccFood[(ST.countTokens())/2] ;
		int i = 0 ;

		while((ST.countTokens()) > 0)
		{
			FD[i] = new ccFood(ST.nextToken(), ST.nextToken()) ;
			i++ ;
		}
				
		return FD ;		
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		필요한 부분의 시작과 끝 인덱스를 구한뒤 잘라낸다

	public static String cleanData(StringBuilder html)
	{
		int SP = 0 ;
		int EP = 0 ;

		SP = html.indexOf("<tbody id=\"contents_1\">");
		EP = html.indexOf("</tbody>") ;

		String mResult = new String(html.toString().substring(SP, EP)) ;

		mResult = mResult.replaceAll("<tbody id=\"contents_1\">", "") ;
		mResult = mResult.replaceAll("<td class=\"tdleft\">", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*>+", "") ;
		mResult = mResult.replaceAll("</+[a-zA-Z0-9]*>+", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*=+\"[a-zA-Z0-9]*\">+", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*/>+", "") ;
		mResult = mResult.replaceAll("\t", "") ;
		mResult = mResult.replaceAll("   ", "") ;
		mResult = mResult.replaceAll("  ", "") ;
		mResult = mResult.replaceAll("\n\n\n", "\n") ;
		mResult = mResult.replaceAll("\n\n", "\n") ;
		
		return mResult.toString() ;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

}