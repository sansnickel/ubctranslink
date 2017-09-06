package org.translink.main;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

	// flags to know what value we are reading when we encounter characters()
	boolean isRouteNo = false;
	boolean isExpectedLeaveTime = false;
	boolean isExpectedCountdown = false;
	boolean isError = false;
	
	// integers to store previous values for spacing of the printed times
	int prevCountdown = -2;
	int prevLength = 0;
	
	// stores the current string so we can print it later
	char ch[];
	int start;
	int length;

	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		// qName is the name of the XML Element (the tag itself)
		// set flags based on what we read
		
		if (qName.equals("RouteNo")) {
			isRouteNo = true;
		} else if (qName.equals("ExpectedLeaveTime")) {
			isExpectedLeaveTime = true;
		} else if (qName.equals("ExpectedCountdown")) {
			isExpectedCountdown = true;
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {

		if (isRouteNo) {						 		// if we read a RouteNo, then we can print it out
			System.out.println("\n" + new String(ch, start, length));
			System.out.print(' ');
			isRouteNo = false;  						// lower flag
			prevCountdown = -3; 				 		// set for initial spacing
			prevLength = 0;						 		// set for initial spacing
		} else if (isExpectedLeaveTime) {		 		// if we read the estimated leave time, save it
			this.ch = ch.clone();
			this.start = start;
			this.length = length;
			isExpectedLeaveTime = false;		 		// lower flag
		} else if (isExpectedCountdown) {		 		// if we read the time until arrival, print spaces based
														// on time, then print the actual time of arrival
			int countdown = Integer.parseInt(new String(ch, start, length));
			if (countdown >= 0) {					    // if the bus isn't already gone (but data has not updated)
				for (int i = 0; i < countdown - prevCountdown - (prevLength <= 1 ? 2 : (prevLength-2))/2; i++) {
														// spacing is proportional to the time between this bus
												 		// and the last bus, but also accounting for the length 
												 		// of the string of the previous time
					System.out.print("  ");
				}
				
				if (countdown - prevCountdown <= 2) { 	// if there are 2 buses within 2 minutes of each other
					System.out.print('*');				// we simply print a star
					prevLength = 1;						// therefore the length of the previous string is 1
				}
				else {									// otherwise print the time without am/pm
					System.out.print(new String(this.ch, this.start, this.length-2));
					prevLength = this.length;			// update the previous length
				}
			prevCountdown = countdown;					// update the previous time until arrival
			}
			isExpectedCountdown = false;				// lower flag
		}
	}
}