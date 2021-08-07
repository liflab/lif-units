package ca.uqac.lif.units.imperial;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.si.Meter;

public class Foot extends Length
{
	public Foot(double x)
	{
		super(x);
	}
	
	public Foot(DimensionValue d)
	{
		super(d);
	}
	
	@Override
	public String toString()
	{
		return m_value + " ft";
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x / 0.3048;
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new Meter(m_value * 0.3048);
	}
}
