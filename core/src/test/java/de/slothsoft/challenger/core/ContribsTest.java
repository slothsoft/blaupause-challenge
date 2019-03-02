package de.slothsoft.challenger.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.challenger.core.contrib1.A;
import de.slothsoft.challenger.core.contrib1.B;
import de.slothsoft.challenger.core.contrib1.C;
import de.slothsoft.challenger.core.contrib2.Apple;
import de.slothsoft.challenger.core.contrib2.Banana;
import de.slothsoft.challenger.core.contrib2.Orange;
import de.slothsoft.challenger.core.contrib2.Pear;
import de.slothsoft.challenger.core.contrib3.One;
import de.slothsoft.challenger.core.contrib3.Three;
import de.slothsoft.challenger.core.contrib3.Two;

public class ContribsTest {

	@Test
	public void testGetClasses() throws Exception {
		final List<Class<?>> result = Contribs.getClasses("de.slothsoft.challenger.core.contrib1");
		Assert.assertEquals(Arrays.asList(A.class, B.class, C.class), result);
	}

	@Test
	public void testGetClassesUnknownPackage() throws Exception {
		final List<Class<?>> result = Contribs.getClasses("de.slothsoft.unknown");
		Assert.assertEquals(new ArrayList<>(), result);
	}

	@Test
	public void testGetPositioners() throws Exception {
		final List<Contrib> result = Contribs.fetchImplementations(Apple.class.getPackage(), Contrib.class);
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertTrue("Result has wrong type: " + result.get(0), result.get(0) instanceof Banana);
		Assert.assertTrue("Result has wrong type: " + result.get(1), result.get(1) instanceof Orange);
		Assert.assertTrue("Result has wrong type: " + result.get(2), result.get(2) instanceof Pear);
	}

	@Test
	public void testGetPositionersIgnoreAbstractClasses() throws Exception {
		final List<Contrib> result = Contribs.fetchImplementations(One.class.getPackage(), Contrib.class);
		Assert.assertEquals(Arrays.asList(new Three(), new Two()), result);
	}

}
