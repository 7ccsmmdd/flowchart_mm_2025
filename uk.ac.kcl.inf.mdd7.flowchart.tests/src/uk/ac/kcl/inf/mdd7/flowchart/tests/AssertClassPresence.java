package uk.ac.kcl.inf.mdd7.flowchart.tests;

import org.junit.Test;

public class AssertClassPresence extends MMTest {

	@Test
	public void doTest() {
		var epMM = loadMetamodel();
		
		assertClassExists(epMM, "FlowChart");
		assertClassExists(epMM, "Step");
		assertClassExists(epMM, "Fork");
		assertClassExists(epMM, "Join");
		assertClassExists(epMM, "AbstractActivity", true);
	}
}
