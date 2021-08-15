package ca.uqac.lif.units.functions;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.petitpoucet.function.FunctionException;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Kilogram;
import ca.uqac.lif.units.si.Meter;

public class UnitAddTest 
{
	@Test
	public void testSame1()
	{
		Meter len1 = new Meter(1);
		Meter len2 = new Meter(2);
		UnitAdd f = new UnitAdd(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof Meter);
		assertEquals(3, out.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testSame2()
	{
		Meter len1 = new Meter(1);
		Centimeter len2 = new Centimeter(2);
		UnitAdd f = new UnitAdd(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof Meter);
		assertEquals(1.02, out.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testSame3()
	{
		Inch len1 = new Inch(2);
		Meter len2 = new Meter(1);
		UnitAdd f = new UnitAdd(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof Inch);
		assertEquals(41.3700, out.get().doubleValue(), 0.0001);
	}
	
	@Test(expected = FunctionException.class)
	public void testIncompatible1()
	{
		Inch len1 = new Inch(2);
		Kilogram len2 = new Kilogram(1);
		UnitAdd f = new UnitAdd(2);
		f.evaluate(len1, len2);
	}
}
