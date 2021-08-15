package ca.uqac.lif.units.imperial;

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.si.MetersPerSecond;

/**
 * A speed value derived from {@link MetersPerSecond}. One mile per hour
 * is 0.44707 m/s, or 3353/75000000.
 * 
 * @author Sylvain Hallé
 *
 */
public class MilesPerHour extends Velocity
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Rational s_factor = Rational.get(3353, 75000000);
	
	public MilesPerHour(DimensionValue x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x.divide(s_factor);
	}
	
	@Override
	public String toString()
	{
		return m_value + " MPH";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new MetersPerSecond(m_value.multiply(s_factor));
	}
}
