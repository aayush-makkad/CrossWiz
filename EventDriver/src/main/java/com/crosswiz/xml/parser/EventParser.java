package com.crosswiz.xml.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.crosswiz.common.errors.PARSER_ERRORS;
import com.crosswiz.xml.parser.entity.Events;

public class EventParser {

	private List<Events> events;
	
	private final static String EVENT_TAG = "event";

	public List<Events> getEvents() {
		return events;
	}

	private static final Logger log = Logger.getLogger(EventParser.class);

	public EventParser(String filePath) {
		events = new ArrayList<>();
		try {
			bootstrap(filePath);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			log.warn(ExceptionUtils.getStackTrace(e));
			log.error(PARSER_ERRORS.PARSER_BOOTSTRAP_FAILED);
		}
	}

	private void bootstrap(String filePath) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		File xmlFile = new File(filePath);
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName(EVENT_TAG);
		for (int i = 0; i < nodeList.getLength(); i++) {
			events.add(new Events(nodeList.item(i).toString()));
		}
	}

}
