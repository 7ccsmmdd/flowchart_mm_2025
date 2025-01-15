package uk.ac.kcl.inf.mdd7.flowchart.tests;

import org.junit.Test;

public class ReferencePresence extends MMTest {

	@Test
	public void doTest() {
		var epMM = loadMetamodel();

		assertHasReference(epMM, "Fork", "nextSteps", "AbstractActivity");
		assertHasReference(epMM, "Step", "next", "AbstractActivity");
		assertHasReference(epMM, "FlowChart", "activities", "AbstractActivity", true);
	}
}
