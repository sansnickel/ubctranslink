package org.translink.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public final class HttpRequest {

	private static final int TIME_FRAME = 75;
	private static final int COUNT = 10;

	private static final String url = "http://api.translink.ca/rttiapi/v1/stops/";
	private static final String api = "/estimates?apikey=VxujSiOu28llUoMXPgmw";
	private static final String count = "&count=" + COUNT;
	private static final String time = "&timeframe=" + TIME_FRAME;

	private HttpRequest() {
	};

	public static void sendGet(int stopno) throws IOException, SAXException, ParserConfigurationException {
		URL u = new URL(url + stopno + api + count + time);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();

		c.setRequestMethod("GET");
		c.setUseCaches(false);
		
		int status = c.getResponseCode();
		if (status == 200) {
			InputStream is = c.getInputStream();
			SAXParserStream.parseSax(is);
		}
		else {
			System.out.println("\nNo buses found for stop " + stopno + " within the next " + TIME_FRAME + " minutes.");
		}
	}
}
