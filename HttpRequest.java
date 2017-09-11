package org.translink.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public final class HttpRequest {

    private static final int TIME_FRAME = 80;
    private static final int COUNT = 10;

    private static final String url = "http://api.translink.ca/rttiapi/v1/stops/";
    private static final String est = "/estimates?apikey=";
    private static final String count = "&count=" + COUNT;
    private static final String time = "&timeframe=" + TIME_FRAME;

    public static void sendGet(int stopno, String api) throws IOException, SAXException, ParserConfigurationException {
        URL u = new URL(url + stopno + est + api + count + time);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();

        c.setRequestMethod("GET");
        c.setUseCaches(false);

        int status = c.getResponseCode();
        if (status == 200) {
            InputStream is = c.getInputStream();
            SAXParserStream.parseSax(is);
        }
        /* 
        else {
            System.out.println("\nNo buses found for stop " + stopno + " within the next " + TIME_FRAME + " minutes.");
        }*/
    }
}
