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

	public float getImgAlie(String UrlString) {
		// Creating a HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// Creating a HttpGet object
		HttpGet httpget = new HttpGet(UrlString);
		// Executing the Get request
		HttpResponse httpresponse;
		String result;
		String currency;
		String time;
		float rate = 0;
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
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(result);
		if (matcher.find()) {
			currency = matcher.group(1);
		} else
			return -1f; // currency not found

		// XML from ECB to find rate
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			Document doc = factory.newDocumentBuilder()
					.parse(new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream());

			NodeList nList = doc.getElementsByTagName("Cube");
			time = "";

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (currency.contentEquals(eElement.getAttribute("currency")))
						rate = Float.parseFloat(eElement.getAttribute("rate"));
					if (eElement.getAttribute("time") != "")
						time = eElement.getAttribute("time");
				}
			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			return -1f;
			// e.printStackTrace();
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
			else if (rate != 0)
				return f / rate;
			else
				return -1; // if rate for some reason =0
		} else
			return -1f; // if parser can't find value return -1

	}
}
