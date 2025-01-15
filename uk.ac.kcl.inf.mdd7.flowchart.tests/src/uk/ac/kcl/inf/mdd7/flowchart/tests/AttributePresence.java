package uk.ac.kcl.inf.mdd7.flowchart.tests;

import org.junit.Test;

public class AttributePresence extends MMTest {

	@Test
	public void doTest() {
		var epMM = loadMetamodel();
		
		assertHasAttribute(epMM, "AbstractActivity", "name", "EString");
	}
}
