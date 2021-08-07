package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;

/**
 * Unit of length derived from the meter. There are 100 centimeters in a
 * meter.
 * @author Sylvain Hallé
 */
public class Centimeter extends Length
{
	public Centimeter(double d)
	{
		super(d);
	}
	
	public Centimeter(DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " cm";
	}
	
	@Override
	protected double fromBaseUnit(double x) 
	{
		return x * 100;
	}

	@Override
	public DimensionValue asBaseUnit() 
	{
		return new Meter(m_value / 100);
	}
}
