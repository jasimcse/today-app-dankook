package com.JJ.ex120326a ;

import android.app.Activity ;
import android.os.Bundle ;
import android.os.StrictMode;
import android.widget.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.StringTokenizer;

import org.apache.http.* ;
import org.apache.http.client.methods.HttpGet ;
import org.apache.http.impl.client.DefaultHttpClient ;

public class HTTPParsingActivity extends Activity
{
	EditText text ;
	
	private String url = "http://203.237.226.95:8080/mobile/login/login_ok.jsp?userid=32071467&userpw=jj119&returnUrl=../m7/m7_c1.jsp&instanceid=" ;
	public String DownloadHtml()
	{				
		//---------------------------------------------------------------------------
		//	Android 어플리케이션에서 인터넷에 접속하기 위해서는 인터넷 접근 권한이 필요하다
		//---------------------------------------------------------------------------
		//	-	AndroidManifest.xml 파일에 접근 권한을 부여하는 명령을 추가한다 (그림 참조)
		//---------------------------------------------------------------------------

		//---------------------------------------------------------------------------
		//	Apache Commons Library를 이용한 HTML 읽어오기
		//---------------------------------------------------------------------------
		//	-	Java에서는 'URL Class'와 'URLConnection Class'를 이용해서 HTTP 통신을 할 수 있다
		//	-	Android에서는 Commons의 'HttpClient Interface'를 구현한 Class인
		//		DefaultHttpClient Class를 가지고 HTTP 통신을 사용할 수 있다
		//	-	Android에는 'Apache HttpClient Library'가 기본으로 포함되어 있다
		//---------------------------------------------------------------------------
		//	HttpGet(url)	
		//	-	안드로이드에서 HTML을 읽어오는 방법으로 사용
		//	-	로그인을 위해 Get 방식 사용 (주소에 ID와 비밀번호를 명시하여 전송)
		//---------------------------------------------------------------------------
		
		DefaultHttpClient client = new DefaultHttpClient() ;
		StringBuilder html = new StringBuilder() ;
		
		//---------------------------------------------------------------------------
		//	Internet 접속이 안될 경우를 대비하여 Try/Catch를 이용한다
		//---------------------------------------------------------------------------

		try
		{
			HttpGet httpget = new HttpGet(url) ;
			HttpResponse response = client.execute(httpget) ;
			
			// 입력스트림에 내용을 연결
			
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent()) ;
			
			// 내용을 한줄씩 읽기 위해서 BufferedReader와 연결
			
			BufferedReader br = new BufferedReader(isr) ;
			
			// 내용의 첫줄을 변수에 저장해서 null 인지 비교한뒤 문제가 없다면 가공을 시행한다
			
			String line = br.readLine() ;
			
			while (line != null)
			{
				// 한줄씩 읽어들이면서 끝부분에 New Line 삽입
				html.append(line + '\n') ;
				line = br.readLine() ;
			}
			br.close() ;
		}
		catch (Exception e)
		{
			e.printStackTrace() ;
		}
		
		//---------------------------------------------------------------------------
		//	필요한 시간표 부분의 시작과 끝 인덱스를 구한뒤 잘라낸다
		//---------------------------------------------------------------------------

		int SP = html.indexOf("<tbody id=\"table_contents_1\">") + 31 ;
		int EP = html.indexOf("</tbody>") ;
		String mResult = new String(html.toString().substring(SP, EP)) ;
		
		//---------------------------------------------------------------------------
		//	불필요한 HTML code 부분을 제거한다
		//---------------------------------------------------------------------------

		mResult = mResult.replaceAll("<tr>", "") ;
		mResult = mResult.replaceAll("</tr>", "") ;
		mResult = mResult.replaceAll("<td>", "") ;
		mResult = mResult.replaceAll("</td>", "") ;
		mResult = mResult.replaceAll("<th>", "") ;
		mResult = mResult.replaceAll("</th>", "") ;
		mResult = mResult.replaceAll("<th colspan=\"2\">", "") ;
		mResult = mResult.replaceAll("<th colspan=\"3\">", "") ;
		mResult = mResult.replaceAll("<td colspan=\"2\">", "") ;
		mResult = mResult.replaceAll("<td colspan=\"3\">", "") ;
		mResult = mResult.replaceAll("\t", "") ;
		mResult = mResult.replaceAll("\n\n\n", "\n") ;
		mResult = mResult.replaceAll("\n\n", "\n") ;
		
		//---------------------------------------------------------------------------
		
		StringTokenizer ST = new StringTokenizer(mResult, "\n") ;
		ST.nextToken() ;
		ST.nextToken() ;
		
		TimeTable[] TT = new TimeTable[ST.countTokens()] ;
		int i = 0 ;
//		while(ST.countTokens() > 0)
//		{
//			TT[i] = new TimeTable ;
//			TT[i] = new TimeTable(Integer.parseInt(ST.nextToken()), Integer.parseInt(ST.nextToken()), Integer.parseInt(ST.nextToken()), ST.nextToken(), ST.nextToken(), ST.nextToken()) ;
//			i++ ;
//		}

//		return TT[2].getProfessor() ;
		return mResult ;
	}
    public void onCreate(Bundle savedInstanceState)
    {
		text = (EditText)findViewById(R.id.mTV) ;

		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // HTTP Access를 위해 SDK 3.0 이후 버젼에서 반드시 해줘야 하는 설정
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);        

		text = (EditText)findViewById(R.id.mTV) ;		
		text.setText(DownloadHtml()) ;
    }
}
