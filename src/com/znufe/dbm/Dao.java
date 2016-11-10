package com.znufe.dbm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class Dao{
	Context context;
	private int duration;
	public String getHtml(String number,String key) {	
		String cookiescut = "";
		List<org.apache.http.cookie.Cookie> cookies;
		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse;

		String urlAPI = "http://202.114.224.81:7777/pls/wwwbks/bks_login2.login";
		String url1 = "http://202.114.224.81:7777/pls/wwwbks/xk.CourseView";
		String url2 = "http://202.114.224.81:7777/pls/wwwbks/bkscjcx.bxqkbcx";
		
		/* 建立HTTP Post连线 */
		HttpPost httpRequest = new HttpPost(urlAPI);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pwd", key)); // 这里的密码我用*取代了
		params.add(new BasicNameValuePair("stuid", number)); // 这是学号

		try {
			// 发出HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 取得HTTP response
			httpResponse = client.execute(httpRequest); // 执行
			// 若状态码为200
			if (httpResponse.getStatusLine().getStatusCode() == 200
					&& !(((AbstractHttpClient) client).getCookieStore()
							.getCookies().isEmpty())) { // 返回值正常
														// 获取返回的cookie
				cookies = ((AbstractHttpClient) client).getCookieStore()
						.getCookies();
				HttpGet get = new HttpGet(url2);

				// String timeValue=new
				// SimpleDateFormat("MMddHHmmss").format(Calendar.getInstance().getTime());
				// 正则
				Pattern p = Pattern.compile("[0-9]{20}");
				Matcher m = p.matcher(cookies.toString());
				while (m.find()) {
					cookiescut = m.group();
				}
				// text1.t
				get.setHeader("Cookie", "ACCOUNT=" + cookiescut);

				HttpResponse httpResponse2 = new DefaultHttpClient()
						.execute(get);
				if (httpResponse2.getStatusLine().getStatusCode() == 200) {
					StringBuffer sb = new StringBuffer();
					HttpEntity entity = httpResponse2.getEntity();
					InputStream is = entity.getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is, "GB2312"));
					String data = "";
					while ((data = br.readLine()) != null) {
						sb.append(data);
					}
					String result = sb.toString();
					return result;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "";
	}

	String filterHtmlSchedule1(String number,String source) {
		if (null == source) {
			return "";
		}
		ArrayList<String> text = new ArrayList<String>();
		StringBuffer sff = new StringBuffer();
		int i = 0, j = 1, k = 1, m = 1;
		Document doc = Jsoup.parse(source);// 把HTML代码加载到doc中
		try {
			Element table = doc.select("table[bgcolor=#F2EDF8]").first();
			Elements tds = table.select("td[width=112]");
			for (Element td : tds) {
				++i;
				text.add(td.text() + "\n");
			}
			for (; j <= 5; j++) {
				// sff.append("新的一天\n");
				for (k = 1; k <= i; k++)
					if (k % 7 == j) {
						String temp = k % 7 + "" + (m % 5 == 0 ? 5 : m % 5)
								+ text.get(k - 1);
						m++;
						String[] s = getData(temp);
					}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		String html = sff.toString();
		return html;
	}
	
	public String[] getData(String ifo) {
		if(ifo.length()<=10){
			return null;
		}
		String[] data = new String[4];
		String ps1 = "([0-9]{2})+";
		String ps2 = "([0-9]{2})(.*[)])+";
		String ps3 = "([0-9]{2})(.*[)])(.*[0-9]{3})+";
		String ps4 = "([0-9]{2})(.*[)])(.*[0-9]{3})([\\s].{1,3})[\\s]+";
		Pattern p = Pattern.compile(ps4);
		Matcher m = p.matcher(ifo);
		if (m.find()) {
			data[0] = m.group(1);//课程时间
			data[1] = m.group(2);//课程名
			data[2] = m.group(3);//教室
			data[3] = m.group(4);//老师
		}
		return data;
	}
	
	public String getWeek(String in){
		String s=null;
		if(in.substring(1)=="1"){
			s="8:00-9:35";
		}else if(in=="2"){
			s="10:00-11:35";
		}else if(in=="3"){
			s="14:00-15:35";
		}else if(in=="4"){
			s="16:00-18:35";
		}else if(in=="5"){
			s="19:30-21:15";
		}
		in=s;
		return in;	
	}
}
