// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		Android 어플리케이션에서 인터넷에 접속하기 위해서는 인터넷 접근 권한이 필요하다
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		Apache Commons Library를 이용한 HTML 읽어오기
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		-	Java에서는 'URL Class'와 'URLConnection Class'를 이용해서 HTTP 통신을 할 수 있다
//		-	Android에서는 Commons의 'HttpClient Interface'를 구현한 Class인
//			DefaultHttpClient Class를 가지고 HTTP 통신을 사용할 수 있다
//		-	Android에는 'Apache HttpClient Library'가 기본으로 포함되어 있다
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		HttpGet(url)	
//		-	안드로이드에서 HTML을 읽어오는 방법으로 사용
//		-	로그인을 위해 Get 방식 사용 (주소에 ID와 비밀번호를 명시하여 전송)
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		Internet 접속이 안될 경우를 대비하여 Try/Catch를 이용한다
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//			입력스트림에 내용을 연결
//			내용을 한줄씩 읽기 위해서 BufferedReader와 연결
//			내용의 첫줄을 변수에 저장해서 null 인지 비교한뒤 문제가 없다면 가공을 시행한다
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

package com.TODAY.HtmlParserByJuyoungJin ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.TODAY.HtmlParserByJuyoungJin.*;

public class ccHTMLParsing
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private static String urlTT = "http://203.237.226.95:8080/mobile/login/login_ok.jsp?userid=32071467&userpw=jj119&returnUrl=../m7/m7_c1.jsp&instanceid=" ;
	private static String urlFD = "http://203.237.226.95:8080/mobile/m11/m11_c1_2.jsp?instanceid=/d1_3_1_2" ;
	private static String urlNT = "http://203.237.226.95:8080/mobile/m8/m8_c2.jsp?instanceid=/d1_2";
	
	public static String DownloadHtml(int option)
	{
		DefaultHttpClient client = new DefaultHttpClient() ;
		StringBuilder html = new StringBuilder() ;

		try
		{
			HttpGet httpget ;
			
			if(option == 1)
				httpget = new HttpGet (urlTT);
			else if(option == 2)
				httpget = new HttpGet(urlFD);
			else
				httpget = new HttpGet(urlNT);
			
			HttpResponse response = client.execute(httpget) ;
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent()) ;
			BufferedReader br = new BufferedReader(isr) ;
			String line = br.readLine() ;

			while (line != null)
			{
				html.append(line + '\n') ;
				line = br.readLine() ;
			}
			br.close() ;
		}
		catch (Exception e)
		{
			e.printStackTrace() ;
		}

		if (option == 1)
			return ccTimeTable.cleanData(html);
		else if(option == 2)
			return ccFood.cleanData(html);
		else
			return ccNotice.cleanData(html);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

}