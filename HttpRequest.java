package org.translink.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {


    public static InputStream sendGet(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
    
            c.setRequestMethod("GET");
            c.setUseCaches(false);
    
            //int status = c.getResponseCode();
            return c.getInputStream();
        } catch (IOException e) {
            System.out.println("Connection error.");
            //e.printStackTrace();
            return null;
        }

        
      
    }
}
