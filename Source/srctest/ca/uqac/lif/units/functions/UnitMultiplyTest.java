package ca.uqac.lif.units.functions;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;
import ca.uqac.lif.units.Quantity;
import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.SurfaceValue;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.imperial.MilesPerHour;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Meter;

public class UnitMultiplyTest 
{
	@Test
	public void testSame1()
	{
		Meter len1 = new Meter(1);
		Meter len2 = new Meter(2);
		UnitMultiply f = new UnitMultiply(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof NamelessDimensionValue);
		assertEquals(2, out.get().doubleValue(), 0.0001);
		assertEquals(out.getDimension(), SurfaceValue.DIMENSION);
	}
	
	@Test
	public void testSame2()
	{
		Meter len1 = new Meter(1);
		Centimeter len2 = new Centimeter(2);
		UnitMultiply f = new UnitMultiply(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof NamelessDimensionValue);
		assertEquals(0.02, out.get().doubleValue(), 0.0001);
		assertEquals(out.getDimension(), SurfaceValue.DIMENSION);
	}
	
	@Test
	public void testSame3()
	{
		Inch len1 = new Inch(1);
		Meter len2 = new Meter(1);
		UnitMultiply f = new UnitMultiply(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof NamelessDimensionValue);
		assertEquals(0.0254, out.get().doubleValue(), 0.0001);
		assertEquals(out.getDimension(), SurfaceValue.DIMENSION);
	}
	
	@Test
	public void testOther1()
	{
		Meter len1 = new Meter(1);
		NamelessDimensionValue fre1 = new NamelessDimensionValue(1, new Dimension().add(Quantity.TIME, -1));
		UnitMultiply f = new UnitMultiply(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, fre1)[0];
		assertNotNull(out);
		assertTrue(out instanceof NamelessDimensionValue);
		assertEquals(1, out.get().doubleValue(), 0.0001);
		assertEquals(out.getDimension(), Velocity.DIMENSION);
		MilesPerHour mph = new MilesPerHour(out);
		assertEquals(2.2369, mph.get().doubleValue(), 0.0001);
	}
}
