package uk.ac.kcl.inf.mdd7.flowchart.tests;

import org.junit.Test;

public class InheritanceTest extends MMTest {

	@Test
	public void doTest() {
		var epMM = loadMetamodel();
		
		assertHasSuperClass(epMM, "Join", "Step");
		assertHasSuperClass(epMM, "Step", "AbstractActivity");
		assertHasSuperClass(epMM, "Fork", "AbstractActivity");
	}
}
