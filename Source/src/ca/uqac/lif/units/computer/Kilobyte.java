package ca.uqac.lif.units.computer;

import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of memory derived from {@link Byte}. There are 1,000 bytes in a
 * kilobyte.
 * @author Sylvain Hallé
 */
public class Kilobyte extends Memory
{
	public Kilobyte(DimensionValue x)
	{
		super(x);
	}
	
	public Kilobyte(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x / 1000;
	}
	
	@Override
	public String toString()
	{
		return m_value + " kB";
	}
	
	@Override
	public Memory asBaseUnit()
	{
		return new Byte(m_value * 1000);
	}
}
