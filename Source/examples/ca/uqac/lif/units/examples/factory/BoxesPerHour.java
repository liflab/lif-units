package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;
import ca.uqac.lif.units.Quantity;

public class BoxesPerHour extends DimensionValue
{
	public BoxesPerHour(DimensionValue x)
	{
		super(x);
	}
	
	public BoxesPerHour(double x)
	{
		super(x);
	}
	
	@Override
	protected double fromBaseUnit(double x)
	{
		return m_value / 12 * 3600;
	}
	
	@Override
	public Dimension getDimension() 
	{
		return new Dimension().add(Lightbulb.DIMENSION, 1).add(Quantity.TIME, -1);
	}
	
	@Override
	public String toString()
	{
		return m_value + "boxes/h";
	}

	@Override
	public DimensionValue asBaseUnit()
	{
		return new NamelessDimensionValue(m_value * 12 / 3600, getDimension());
	}
	
	
}
