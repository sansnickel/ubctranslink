package org.translink.main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	final static int[] ubcstopnos = { 61935, 60100, 60117, 61934, 61979 };
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
