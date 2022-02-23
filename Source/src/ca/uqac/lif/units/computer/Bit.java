package ca.uqac.lif.units.computer;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of memory derived from {@link Byte}. There are 8 bits in a byte.
 * @author Sylvain Hallé
 */
public class Bit extends Memory
{
	protected static final transient Whole s_factor = Whole.get(8);
	
	public Bit(DimensionValue x)
	{
		super(x);
	}
	
	public Bit(double x)
	{
		super(x);
	}
	
	protected Bit()
	{
		super();
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x.multiply(s_factor);
	}
	
	@Override
	public String getUnitName()
	{
		return "b";
	}
	
	@Override
	public Memory asBaseUnit()
	{
		return new Byte(m_value.divide(s_factor));
	}
}
