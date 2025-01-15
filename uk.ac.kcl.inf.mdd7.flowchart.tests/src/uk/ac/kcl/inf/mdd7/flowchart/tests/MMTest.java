package uk.ac.kcl.inf.mdd7.flowchart.tests;

import static org.eclipse.xtext.xbase.lib.IteratorExtensions.exists;
import static org.eclipse.xtext.xbase.lib.IteratorExtensions.filter;
import static org.eclipse.xtext.xbase.lib.IteratorExtensions.size;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Test;

public abstract class MMTest {

	protected EPackage loadMetamodel() {
		var rs = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		// Register the package -- only needed for stand-alone!
		var ecorePackage = EcorePackage.eINSTANCE;

		var rMetaModel = rs.getResource(URI.createFileURI("../uk.ac.kcl.inf.mdd7.flowchart/model/flowchart.ecore"),
				true);

		return (EPackage) rMetaModel.getContents().get(0);
	}

	protected void assertHasSuperClass(EPackage epMM, String subClassName, String superClassName) {
		assertTrue("Expected " + subClassName + " to be a sub-class of " + superClassName,
				exists(mmClasses(epMM), ec -> {
					return ec.getName().equals(subClassName)
							&& IterableExtensions.exists(ec.getEAllSuperTypes(), ec2 -> {
								return ec2.getName().equals(superClassName);
							});
				}));
	}

	protected void assertHasReference(EPackage epMM, String srcClassName, String refName, String tgtClassName) {
		assertHasReference(epMM, srcClassName, refName, tgtClassName, false);
	}

	protected void assertHasReference(EPackage epMM, String srcClassName, String refName, String tgtClassName,
			boolean isContainment) {
		assertTrue("Expected reference \"" + refName + "\" from " + srcClassName + " to " + tgtClassName,
				exists(mmReferences(epMM), er -> {
					return ((EClass) er.eContainer()).getName().equals(srcClassName) && er.getName().equals(refName)
							&& er.getEType().getName().equals(tgtClassName) && (er.isContainment() == isContainment);
				}));
	}

	protected void assertHasAttribute(EPackage epMM, final String className, final String attrName,
			final String attrTypeName) {
		assertTrue("Expected " + className + " to have a " + attrName + " attribute of type " + attrTypeName,
				exists(mmClasses(epMM), ec -> {
					return ec.getName().equals(className) && IterableExtensions.exists(ec.getEAllAttributes(), ea -> {
						return ea.getName().equals(attrName) && ea.getEAttributeType().getName().equals(attrTypeName);
					});
				}));
	}

	protected void assertClassExists(EPackage epMM, String className) {
		assertClassExists(epMM, className, false);
	}

	protected void assertClassExists(EPackage epMM, final String className, final boolean isAbstract) {
		assertTrue("Expected to find a " + (isAbstract ? "abstract" : "non-abstract") + " " + className + " class",
				exists(mmClasses(epMM), ec -> {
					return ec.getName().equals(className) && (ec.isAbstract() == isAbstract);
				}));
	}

	protected int countClasses(EPackage metamodel) {
		return size(mmClasses(metamodel));
	}

	private Iterator<EClass> mmClasses(EPackage metamodel) {
		return filter(metamodel.eAllContents(), EClass.class);
	}

	private Iterator<EReference> mmReferences(EPackage metamodel) {
		return filter(metamodel.eAllContents(), EReference.class);
	}
}
