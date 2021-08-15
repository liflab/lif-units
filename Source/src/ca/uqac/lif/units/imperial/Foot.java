package ca.uqac.lif.units.imperial;

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.si.Meter;

public class Foot extends Length
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Rational s_factor = Rational.get(0.3048);
	
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
	protected Real fromBaseUnit(Real x)
	{
		return x.divide(s_factor);
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new Meter(m_value.multiply(s_factor));
	}
}
