package ca.uqac.lif.units.si;

import ca.uqac.lif.units.Angle;
import ca.uqac.lif.units.DimensionValue;

public class Radian extends Angle
{
	public Radian(double d)
	{
		super(d);
	}
	
	public Radian(/*@ non_null @*/ DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + " rad";
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
