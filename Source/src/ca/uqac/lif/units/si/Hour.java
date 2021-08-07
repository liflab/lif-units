package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Time;

/**
 * Unit of length derived from the second. There are 3600 seconds in an
 * hour.
 * @author Sylvain Hallé
 */
public class Hour extends Time
{
	public Hour(double d)
	{
		super(d);
	}
	
	public Hour(/*@ non_null @*/ DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " h";
	}
	
	@Override
	protected double fromBaseUnit(double x) 
	{
		return x / 3600;
	}

	@Override
	public Time asBaseUnit() 
	{
		return new Second(m_value * 3600);
	}
}
