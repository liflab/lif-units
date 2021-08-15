package ca.uqac.lif.units.computer;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of memory derived from {@link Byte}. There are 1,000,000 bytes in a
 * kilobyte.
 * @author Sylvain Hallé
 */
public class Megabyte extends Byte
{
	protected static final Whole s_factor = Whole.get(1000000);
	
	public Megabyte(DimensionValue x)
	{
		super(x);
	}
	
	public Megabyte(Real x)
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
		return m_value + " MB";
	}
	
	@Override
	public Memory asBaseUnit()
	{
		return new Byte(m_value.multiply(s_factor));
	}
}
