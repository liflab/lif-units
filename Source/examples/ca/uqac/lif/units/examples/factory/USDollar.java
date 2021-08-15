package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public class USDollar extends DimensionValue 
{
	public static final String DIMENSION = "amount";
	
	public USDollar(DimensionValue x)
	{
		super(x);
	}
	
	public USDollar(double x)
	{
		super(x);
	}
	
	public USDollar(Real x)
	{
		super(x);
	}

	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(DIMENSION, 1);
	}
	
	@Override
	public String toString()
	{
		return "$" + m_value;
	}

	@Override
	public USDollar asBaseUnit()
	{
		return this;
	}

	@Override
	protected Real fromBaseUnit(Real x)
	{
		return m_value;
	}
}
