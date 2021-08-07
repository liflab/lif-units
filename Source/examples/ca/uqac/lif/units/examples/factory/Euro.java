package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public class Euro extends DimensionValue 
{
	protected static final double s_exchangeRate = 1.2;
	
	public Euro(DimensionValue x)
	{
		super(x);
	}
	
	public Euro(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return x / s_exchangeRate;
	}
	
	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(USDollar.DIMENSION, 1);
	}
	
	@Override
	public String toString()
	{
		return m_value + " €";
	}

	@Override
	public USDollar asBaseUnit() 
	{
		return new USDollar(m_value * s_exchangeRate);
	}
}
