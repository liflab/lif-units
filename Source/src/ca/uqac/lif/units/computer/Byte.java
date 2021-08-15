package ca.uqac.lif.units.computer;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;

public class Byte extends Memory
{
	public Byte(DimensionValue x)
	{
		super(x);
	}
	
	public Byte(Real x)
	{
		super(x);
	}
	
	@Override
	public String toString()
	{
		return m_value + " B";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return this;
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x;
	}
}
