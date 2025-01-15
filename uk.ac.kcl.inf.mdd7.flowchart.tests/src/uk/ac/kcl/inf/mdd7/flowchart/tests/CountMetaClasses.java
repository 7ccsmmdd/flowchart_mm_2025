package uk.ac.kcl.inf.mdd7.flowchart.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountMetaClasses extends MMTest {

	@Test
	public void doTest() {
		var epMM = loadMetamodel();
		
		assertEquals("Wrong number of meta-classes in your meta-model", 5, countClasses(epMM));
	}	
}