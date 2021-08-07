package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;

public class USDollarPerBox extends DimensionValue 
{
	public USDollarPerBox(DimensionValue x)
	{
		super(x);
	}
	
	public USDollarPerBox(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return m_value * 12;
	}
	
	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(Lightbulb.DIMENSION, 1).add(USDollar.DIMENSION, -1);
	}
	
	@Override
	public String toString()
	{
		return "$" + m_value + "/box";
	}

	@Override
	public DimensionValue asBaseUnit() 
	{
		return new NamelessDimensionValue(m_value / 12, getDimension());
	}
}
