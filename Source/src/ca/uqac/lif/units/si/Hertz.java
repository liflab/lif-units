package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Frequency;

public class Hertz extends Frequency
{
	public Hertz(double d)
	{
		super(d);
	}
	
	public Hertz(DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " Hz";
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
