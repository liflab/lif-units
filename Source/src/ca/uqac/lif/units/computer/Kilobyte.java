package ca.uqac.lif.units.computer;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of memory derived from {@link Byte}. There are 1,000 bytes in a
 * kilobyte.
 * @author Sylvain Hallé
 */
public class Kilobyte extends Memory
{
	protected static final Whole s_factor = Whole.get(1000);
	
	public Kilobyte(DimensionValue x)
	{
		super(x);
	}
	
	public Kilobyte(double x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x.divide(s_factor);
	}
	
	@Override
	public String getUnitName()
	{
		return "kB";
	}
	
	@Override
	public Memory asBaseUnit()
	{
		return new Byte(m_value.multiply(s_factor));
	}
}
