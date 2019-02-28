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
import de.slothsoft.challenger.core.contrib4.MyContribImpl;

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
	public void testFetchContribImplementations() throws Exception {
		final List<Contrib> result = Contribs.fetchContribImplementations(Apple.class.getPackage());
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertTrue("Result has wrong type: " + result.get(0), result.get(0) instanceof Banana);
		Assert.assertTrue("Result has wrong type: " + result.get(1), result.get(1) instanceof Orange);
		Assert.assertTrue("Result has wrong type: " + result.get(2), result.get(2) instanceof Pear);
	}

	@Test
	public void testFetchContribImplementationsIgnoreAbstractClasses() throws Exception {
		final List<Contrib> result = Contribs.fetchContribImplementations(One.class.getPackage());
		Assert.assertEquals(Arrays.asList(new Three(), new Two()), result);
	}

	@Test
	public void testFetchImplementationsForClass() throws Exception {
		final List<MyContrib> result = Contribs.fetchImplementationsForClass(MyContribImpl.class.getPackage(),
				MyContrib.class);
		Assert.assertEquals(Arrays.asList(new MyContribImpl()), result);
	}

	@Test
	public void testFetchImplementationsForClassOtherContrib() throws Exception {
		final List<Contrib> result = Contribs.fetchImplementationsForClass(MyContribImpl.class.getPackage(),
				Contrib.class);
		Assert.assertEquals(new ArrayList<>(), result);
	}

}