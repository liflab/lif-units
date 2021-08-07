package ca.uqac.lif.units.computer;

import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of memory derived from {@link Byte}. There are 8 bits in a byte.
 * @author Sylvain Hallé
 */
public class Bit extends Memory
{
	public Bit(DimensionValue x)
	{
		super(x);
	}
	
	public Bit(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x * 8;
	}
	
	@Override
	public String toString()
	{
		return m_value + " MB";
	}
	
	@Override
	public Memory asBaseUnit()
	{
		return new Byte(m_value / 8);
	}
}
