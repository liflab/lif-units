package ca.uqac.lif.units;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.uqac.lif.units.IncompatibleDimensions;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Kilogram;
import ca.uqac.lif.units.si.Meter;

public class LengthTest 
{
	@Test
	public void testConversion1()
	{
		Meter m = new Meter(0.254);
		Inch i = new Inch(m);
		assertEquals(10, i.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testConversion2()
	{
		Inch i = new Inch(1);
		Centimeter c = new Centimeter(i);
		assertEquals(2.54, c.get().doubleValue(), 0.0001);
	}
	
	@Test(expected=IncompatibleDimensions.class)
	public void testConversion3()
	{
		Kilogram k = new Kilogram(1);
		new Centimeter(k);
	}
	
	@Test
	public void testConversion4()
	{
		Meter m = new Meter(0.254);
		Centimeter c = new Centimeter(m);
		assertEquals(25.4, c.get().doubleValue(), 0.0001);
	}
}
