package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public class LightbulbBox extends DimensionValue 
{
	protected static final Whole s_factor = Whole.get(12);
	
	public LightbulbBox(DimensionValue x)
	{
		super(x);
	}
	
	public LightbulbBox(double x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return m_value.divide(s_factor);
	}

	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(Lightbulb.DIMENSION, 1);
	}
	
	@Override
	public String getUnitName()
	{
		return "box(es)";
	}

	@Override
	public Lightbulb asBaseUnit() 
	{
		return new Lightbulb(m_value.multiply(s_factor));
	}
}
