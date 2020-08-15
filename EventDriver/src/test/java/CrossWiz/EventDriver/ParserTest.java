package CrossWiz.EventDriver;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.crosswiz.xml.parser.EventParser;

public class ParserTest {

	@Test
	public void testParser() {
		String filePath = "D:\\Crosswiz\\EventDriver\\resouces\\events.xml";
		EventParser parser = new EventParser(filePath);
		assertTrue(parser.getEvents().size() > 0);
	}

}
