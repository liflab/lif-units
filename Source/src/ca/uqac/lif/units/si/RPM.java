package ca.uqac.lif.units.si;

import ca.uqac.lif.units.AngularVelocity;
import ca.uqac.lif.units.DimensionValue;

/**
 * An angular velocity value derived from {@link RadiansPerSecond}. One RPM
 * is 0.10472 rad/s.
 * 
 * @author Sylvain Hallé
 *
 */
public class RPM extends AngularVelocity
{
	public RPM(DimensionValue x)
	{
		super(x);
	}
	
	public RPM(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x / 0.10472;
	}
	
	@Override
	public String toString()
	{
		return m_value + " RPM";
	}

	@Override
	public AngularVelocity asBaseUnit()
	{
		return new RadiansPerSecond(m_value * 0.10472);
	}
}
