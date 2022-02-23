package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public class Euro extends DimensionValue 
{
	protected static final Rational s_exchangeRate = Rational.get(1.2);
	
	public Euro(DimensionValue x)
	{
		super(x);
	}
	
	public Euro(double x)
	{
		super(x);
	}
	
	public Euro(Real x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x.divide(s_exchangeRate);
	}
	
	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(USDollar.DIMENSION, 1);
	}
	
	@Override
	public String getUnitName()
	{
		return "€";
	}

	@Override
	public USDollar asBaseUnit() 
	{
		return new USDollar(m_value.multiply(s_exchangeRate));
	}
}
