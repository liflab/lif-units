package ca.uqac.lif.units;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.units.functions.UnitDivide;
import ca.uqac.lif.units.si.Meter;

public class UnitDivideTest 
{
	@Test
	public void testSameUnit1()
	{
		DimensionValue v = UnitDivide.get(new Meter(4), new Meter(2));
		DimensionValue m = v.asBaseUnit();
		assertEquals(2, m.get().doubleValue(), 0.0001);
	}
}
