package com.wlt.wla.parsers;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.jdbc.core.JdbcTemplate;
import org.xml.sax.SAXException;

public class imgParsers {

	public String getImgSalidzini(String UrlString) {
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
			// if page can't be accessed return ""
			return "";
		}
		// regex to find price in salidzini.lv
		String rx = "<imgitemprop=\"image\"src=\"\\/\\/(.*?)\"";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(result);
		if (matcher.find()) {
			return "http://"+matcher.group(1);
		} else
			return ""; // if parser can't find value return ""

	}

	public String getImgAlie(String UrlString) {
		
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
			// if page can't be accessed return ""
			return "";
		}
		// regex to find price in salidzini.lv
		String rx = "<metaproperty=\"og:image\"content=\"(.*?)\"";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(result);
		if (matcher.find()) {
			return matcher.group(1);
		} else
			return ""; // if parser can't find value return ""
	}
}
