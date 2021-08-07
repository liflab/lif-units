package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Mass;

public class Kilogram extends Mass
{
	public Kilogram(double d)
	{
		super(d);
	}
	
	public Kilogram(DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " kg";
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
