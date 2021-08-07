package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Time;

public class Second extends Time
{
	public Second(double d)
	{
		super(d);
	}
	
	public Second(DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " s";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return this;
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x;
	}
}
