package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Time;

/**
 * Unit of length derived from the second. There are 60 seconds in a
 * minute.
 * @author Sylvain Hallé
 */
public class Minute extends Time
{
	public Minute(double d)
	{
		super(d);
	}
	
	public Minute(/*@ non_null @*/ DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " min";
	}
	
	@Override
	protected double fromBaseUnit(double x) 
	{
		return x / 60;
	}
	
	@Override
	public Time asBaseUnit() 
	{
		return new Second(m_value * 60);
	}
}
