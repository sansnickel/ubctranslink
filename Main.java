package org.translink.main;

import java.io.InputStream;


public class Main {

	/* UBC                  Bridgeport/Home
	   61935 - 99           61330 - 407/430 (from Bridgeport)
       60100 - 33           52084 - 407/430 (from Home)
       60117 - 41           61323 - 480 (from Bridgeport)
       61934 - 43/480
       61979 - 49
       
       60045 - 84
       59936 - 44
       61701 - 14
       61702 - 9/N17
       58895 - 4
	*/
	
    final static int[] ubcstopnos = { 61935, 60100, 60117, 61934, 61979 
                                      //58895, 61701
                                    };
    final static int[] homestopnos = { 52084, 61323 };
    

    private static final int TIME_FRAME = 80;
    private static final int COUNT = 10;

    private static final String host = "http://api.translink.ca/rttiapi/v1/stops/";
    private static final String est = "/estimates?apikey=";
    private static final String count = "&count=" + COUNT;
    private static final String time = "&timeframe=" + TIME_FRAME;
    
    
    // run with your own api key as args[0]
    public static void main(String[] args) {
        System.out.println("From UBC:");
        for (int stopno : ubcstopnos) {
            String url = host + stopno + est + args[0] + count + time;
            InputStream is = HttpRequest.sendGet(url);
            SAXParserStream.parseSax(is);
        }
      
        System.out.println("\n\nFrom Home:");
        for (int stopno : homestopnos) {
            String url = host + stopno + est + args[0] + count + time;
            InputStream is = HttpRequest.sendGet(url);
            SAXParserStream.parseSax(is);
        }
        System.out.println("\n");
    }

}
