package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;

public class Meter extends Length
{
	public Meter(double d)
	{
		super(d);
	}
	
	public Meter(DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " m";
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
