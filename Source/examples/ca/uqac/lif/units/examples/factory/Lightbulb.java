package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public class Lightbulb extends DimensionValue 
{
	public static final String DIMENSION = "quantity";
	
	public Lightbulb(DimensionValue x)
	{
		super(x);
	}
	
	public Lightbulb(double x)
	{
		super(x);
	}
	
	public Lightbulb(Real x)
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
		return m_value + " lightbulb(s)";
	}

	@Override
	public Lightbulb asBaseUnit()
	{
		return this;
	}

	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x;
	}
}
