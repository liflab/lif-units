package ca.uqac.lif.units.imperial;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.si.MetersPerSecond;

/**
 * A speed value derived from {@link MetersPerSecond}. One mile per hour
 * is 0.44704 m/s.
 * 
 * @author Sylvain Hallé
 *
 */
public class MilesPerHour extends Velocity
{
	public MilesPerHour(DimensionValue x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x) 
	{
		return x / 0.44704;
	}
	
	@Override
	public String toString()
	{
		return m_value + " MPH";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new MetersPerSecond(m_value * 0.44704);
	}
}
