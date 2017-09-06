package org.translink.main;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public final class SAXParserStream {

	private SAXParserStream() {
	};
	
	// creates a SAXParser so we can parse an inputstream using our userhandler
	public static void parseSax(InputStream is) throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		UserHandler userhandler = new UserHandler();
		saxParser.parse(is, userhandler);

	}
}
