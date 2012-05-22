package com.TODAY.HtmlParserByJuyoungJin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;


public class ccNotice
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private String ntitle;
	String nnum;
	 private String ncampus;
	private String npart;
	String ndate;
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccNotice()
	{
		setNtitle(new String());
		nnum = new String();
		setNcampus(new String());
		setNpart(new String());
		ndate = new String();
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public ccNotice(String ntitle, String nnum, String ncampus, String npart, String ndate)
	{
		this.setNtitle(ntitle);
		this.nnum = nnum;
		this.setNcampus(ncampus);
		this.setNpart(npart) ;
		this.ndate = ndate;
	}
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public static ccNotice[] stringToClass()
	{
		StringTokenizer ST = new StringTokenizer(ccHTMLParsing.DownloadHtml(3), "\n");		
		ccNotice[] NN = new ccNotice[(ST.countTokens()/6)];
		int i = 0 ;
		
		while(ST.countTokens() > 0)
		{
			NN[i] = new ccNotice(
					ST.nextToken(),
					ST.nextToken(),
					ST.nextToken(),
					ST.nextToken(),
					ST.nextToken());
			ST.nextToken();
			i++ ;
		}
		return NN ;
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
	//	필요한 부분의 시작과 끝 인덱스를 구한뒤 잘라낸다

	public static String cleanData(StringBuilder html)
	{
		int SP = 0 ;
		int EP = 0 ;

		SP = html.indexOf("검색</a></li>") + 2 ;
		EP = html.indexOf("<div id=\"paging\">") ;

		String mResult = new String(html.toString().substring(SP, EP)) ;

		mResult = mResult.replaceAll("<li>", "") ;
		mResult = mResult.replaceAll("</li>", "") ;
		mResult = mResult.replaceAll("<div class=\"sub_data\">+", "") ;		
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*>+", "") ;
		mResult = mResult.replaceAll("</+[a-zA-Z0-9]*>+", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*=+\"[a-zA-Z0-9]*\">+", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*/>+", "") ;
		mResult = mResult.replaceAll("\t", "") ;
		mResult = mResult.replaceAll("   ", "") ;
		mResult = mResult.replaceAll("  ", "") ;
		mResult = mResult.replaceAll("\n\n\n", "\n") ;
		mResult = mResult.replaceAll("\n\n", "\n") ;

		String mRemain = new String(mResult);		
		StringBuilder nResult = new StringBuilder("");

		SP = 0;
		int CP = mRemain.indexOf("<");
		EP = mRemain.length();
		
		while(mRemain.length() > 1)
		{
			String mTemp = mRemain.substring(SP, CP);
			CP = mRemain.indexOf(">");
			mRemain = mRemain.substring(CP+1, EP);
			CP = mRemain.indexOf("<");
			EP = mRemain.length();
			nResult.append(mTemp);
		}
		
		return nResult.toString() ;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcampus() {
		return ncampus;
	}

	public void setNcampus(String ncampus) {
		this.ncampus = ncampus;
	}

	public String getNpart() {
		return npart;
	}

	public void setNpart(String npart) {
		this.npart = npart;
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

}