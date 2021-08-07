package ca.uqac.lif.units.si;

import ca.uqac.lif.units.AngularVelocity;
import ca.uqac.lif.units.DimensionValue;

public class RadiansPerSecond extends AngularVelocity
{
	public RadiansPerSecond(DimensionValue x)
	{
		super(x);
	}
	
	public RadiansPerSecond(double x)
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " rad/s";
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
