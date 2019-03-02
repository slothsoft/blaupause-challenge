package de.slothsoft.blaupause;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.blaupause.contrib.ExampleContrib;
import de.slothsoft.blaupause.contrib1.A;
import de.slothsoft.blaupause.contrib1.B;
import de.slothsoft.blaupause.contrib1.C;
import de.slothsoft.blaupause.contrib2.Apple;
import de.slothsoft.blaupause.contrib2.Banana;
import de.slothsoft.blaupause.contrib2.Orange;
import de.slothsoft.blaupause.contrib2.Pear;
import de.slothsoft.blaupause.contrib3.One;
import de.slothsoft.blaupause.contrib3.Three;
import de.slothsoft.blaupause.contrib3.Two;

public class ContribsTest {

	@Test
	public void testGetClasses() throws Exception {
		List<Class<?>> result = Contribs.getClasses("de.slothsoft.blaupause.contrib1");
		Assert.assertEquals(Arrays.asList(A.class, B.class, C.class), result);
	}

	@Test
	public void testGetClassesUnknownPackage() throws Exception {
		List<Class<?>> result = Contribs.getClasses("de.slothsoft.unknown");
		Assert.assertEquals(new ArrayList<>(), result);
	}

	@Test
	public void testGetPositioners() throws Exception {
		List<Contrib> result = Contribs.fetchImplementations(Apple.class.getPackage());
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertTrue("Result has wrong type: " + result.get(0), result.get(0) instanceof Banana);
		Assert.assertTrue("Result has wrong type: " + result.get(1), result.get(1) instanceof Orange);
		Assert.assertTrue("Result has wrong type: " + result.get(2), result.get(2) instanceof Pear);
	}

	@Test
	public void testGetPositionersIgnoreAbstractClasses() throws Exception {
		List<Contrib> result = Contribs.fetchImplementations(One.class.getPackage());
		Assert.assertEquals(Arrays.asList(new Three(), new Two()), result);
	}

	@Test
	public void testGetDefaultPositioners() throws Exception {
		List<Contrib> result = Contribs.fetchAllImplementations();
		Assert.assertNotNull(result);
		Assert.assertTrue("Default contrib is missing: " + result,
				result.stream().filter(i -> i instanceof ExampleContrib).count() > 0);
	}

}
