package ca.uqac.lif.units.si;

import ca.uqac.lif.units.Angle;
import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of length derived from the radian. There are &pi;/180 radians in a degree.
 * @author Sylvain Hallé
 */
public class Degree extends Angle
{
	public Degree(double d)
	{
		super(d);
	}
	
	public Degree(DimensionValue x) 
	{
		super(x);
	}
	
	@Override
	public String toString()
	{
		return m_value + " cm";
	}
	
	@Override
	protected double fromBaseUnit(double x) 
	{
		return x * 180 / Math.PI;
	}

	@Override
	public Angle asBaseUnit()
	{
		return new Radian(m_value * Math.PI / 180);
	}
}
