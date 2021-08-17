package ca.uqac.lif.units.imperial;

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
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
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Rational s_factor = Rational.get(0.0254);
	
	public Inch(double d)
	{
		super(d);
	}
	
	public Inch(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	public Inch(/*@ non_null @*/ Real x)
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
		return new Meter(m_value.multiply(s_factor));
	}
	
	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x.divide(s_factor);
	}
}
