package org.translink.main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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

    public static void main(String[] args) {
        try {
            System.out.println("From UBC:");
            for (int stopno : ubcstopnos) {
                HttpRequest.sendGet(stopno);
            }

            System.out.println("\n\nFrom Home:");
            for (int stopno : homestopnos) {
                HttpRequest.sendGet(stopno);
            }
            System.out.println("\n");

        } catch (IOException e) {
            System.out.println("Connection failure.\n");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Problem processing response.\n");
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            System.out.println("Problem creating parser.\n");
            e.printStackTrace();
        }
    }

}
