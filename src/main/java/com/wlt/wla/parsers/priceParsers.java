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


	
	public float getPriceAlie(String UrlString) {
		// Creating a HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// Creating a HttpGet object
		HttpGet httpget = new HttpGet(UrlString);
		// Executing the Get request
		HttpResponse httpresponse;
		String result;
		String currency;
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
		// regex to find currency on Aliex

		String rx = "\"currency\":\"(.*?)\",\"formatedAmoun";
		System.out.println(rx);
		Pattern p = Pattern.compile(rx);
		System.out.println("pattern compile");
		Matcher matcher = p.matcher(result);
		if (matcher.find()) {
			currency = matcher.group(1);
		} else
			return -1f; //currency not found
		
		System.out.println(currency);
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
        	Document  doc =  factory.newDocumentBuilder().parse(new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream());
			
        	
        	
        	
        	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
        	NodeList nList = doc.getElementsByTagName("Cube");
        			
        	System.out.println("----------------------------");

        	for (int temp = 0; temp < nList.getLength(); temp++) {

        		Node nNode = nList.item(temp);
        				
        		System.out.println("\nCurrent Element :" + nNode.getNodeName());
        				
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
System.out.println("1");
        			Element eElement = (Element) nNode;
        			System.out.println("2");
        			//System.out.println("Staff id : " + eElement.getAttribute("id"));
        			eElement.getElementsByTagName("currency").toString();
        			//System.out.println("Currency : " + eElement.getElementsByTagName("currency").item(0).getTextContent());
//        			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//        			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//        			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
        			System.out.println(eElement.getElementsByTagName("currency").toString());
        		}
        	}
        	
        	
        	
        	
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			return -1f;
			//e.printStackTrace();
		}
		
		
		

		rx = ",\"value\":(.*?)},\"maxAmount";
		p = Pattern.compile(rx);
		matcher = p.matcher(result);
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
