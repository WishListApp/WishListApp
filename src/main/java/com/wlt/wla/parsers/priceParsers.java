package com.wlt.wla.parsers;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class priceParsers {
	
	public float getPriceSalidzini(String UrlString) {
		// Creating a HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// Creating a HttpGet object
		HttpGet httpget = new HttpGet(UrlString);
		// Executing the Get request
		HttpResponse httpresponse;
		String result;
		try {
			httpresponse = httpclient.execute(httpget);
			Scanner sc = new Scanner(httpresponse.getEntity().getContent());
			StringBuffer sb = new StringBuffer();
			while (sc.hasNext()) {
				sb.append(sc.next());
			}
			result = sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
			// if page can't be accessed return -1
			return -1;
		}
		// regex to find price in salidzini.lv
		String rx = "itemprop=\"lowPrice\">(.*?)&nbsp;&euro;";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(result);
		if (matcher.find()) {
			float f = Float.parseFloat(matcher.group(1));
			Double y = new Double(f);
			// if for some reason parsed value not float return -1
			if (y.isNaN())
				return -1f;
			else
				return f;
		} else
			return -1f; // if parser can't find value return -1

	}

}
