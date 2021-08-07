package ca.uqac.lif.units.imperial;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.si.Meter;

/**
 * Unit of length of the Imperial Unit system, derived from the meter. An
 * inch is exactly 0.0254 meter.
 * 
 * @author Sylvain Hallé
 *
 */
public class Inch extends Length
{
	public Inch(double d)
	{
		super(d);
	}
	
	public Inch(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}

	@Override
	public String toString()
	{
		return m_value + "\"";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new Meter(m_value * 0.0254);
	}
	
	@Override
	protected double fromBaseUnit(double x) 
	{
		return x / 0.0254;
	}
}
