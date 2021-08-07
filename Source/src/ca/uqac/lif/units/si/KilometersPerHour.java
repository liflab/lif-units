package ca.uqac.lif.units.si;

import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Velocity;

/**
 * A speed value derived from {@link MetersPerSecond}. One kilometer per hour
 * is 0.2777&hellip; m/s.
 * 
 * @author Sylvain Hallé
 *
 */
public class KilometersPerHour extends Velocity
{
	public KilometersPerHour(DimensionValue x)
	{
		super(x);
	}
	
	public KilometersPerHour(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x / 0.27777777777778;
	}
	
	@Override
	public String toString()
	{
		return m_value + " km/h";
	}

	@Override
	public DimensionValue asBaseUnit() 
	{
		return new MetersPerSecond(m_value * 0.27777777777778);
	}
}
