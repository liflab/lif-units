package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Velocity;

public class MetersPerSecond extends Velocity
{
	public MetersPerSecond(DimensionValue x)
	{
		super(x);
	}
	
	public MetersPerSecond(double x)
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " m/s";
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
