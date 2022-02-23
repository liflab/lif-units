package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;

public class USDollarPerBox extends DimensionValue 
{
	protected static final Whole s_factor = Whole.get(12);
	
	public USDollarPerBox(DimensionValue x)
	{
		super(x);
	}
	
	public USDollarPerBox(double x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return m_value.multiply(s_factor);
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
	public String getUnitName()
	{
		return "$/box";
	}

	@Override
	public DimensionValue asBaseUnit() 
	{
		return new NamelessDimensionValue(m_value.divide(s_factor), getDimension());
	}
}
