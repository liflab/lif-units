package ca.uqac.lif.numbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FloatingPointTest
{
	@Test
	public void testConstructor2()
	{
		Real x = new FloatingPoint(1, 0.5);
		assertEquals(1, x.intValue());
		assertEquals(1, x.floatValue(), 0.00001);
		assertEquals(1, x.doubleValue(), 0.00001);
		assertEquals(0.5, x.getUncertainty(), 0.00001);
		assertEquals("(1 \u00b1 0.5)", x.toString());
	}
	
	@Test
	public void testConstructor3()
	{
		Real x = new FloatingPoint(1, 0.333);
		assertEquals(1, x.intValue());
		assertEquals(1, x.floatValue(), 0.00001);
		assertEquals(1, x.doubleValue(), 0.00001);
		assertEquals(0.3, x.getUncertainty(), 0.00001);
		assertEquals("(1 \u00b1 0.3)", x.toString());
	}
	
	@Test
	public void testMultiply1()
	{
		Real x = new FloatingPoint(1d / 3);
		Real y = new FloatingPoint(5d / 8);
		Real z = x.multiply(y);
		assertTrue(z instanceof FloatingPoint);
		assertEquals(0.208333, z.doubleValue(), 0.0001);
	}
}
