package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public class LightbulbBox extends DimensionValue 
{
	public LightbulbBox(DimensionValue x)
	{
		super(x);
	}
	
	public LightbulbBox(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return m_value / 12;
	}

	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(Lightbulb.DIMENSION, 1);
	}
	
	@Override
	public String toString()
	{
		return m_value + " box(es)";
	}

	@Override
	public Lightbulb asBaseUnit() 
	{
		return new Lightbulb(m_value * 12);
	}
}
