package ca.uqac.lif.units;

import ca.uqac.lif.numbers.Real;

public class Scalar extends DimensionValue 
{
	public static final Dimension DIMENSION = new Dimension();
	
	public Scalar(DimensionValue x) 
	{
		super(x);
	}
	
	public Scalar(double d) 
	{
		super(d);
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

	@Override
	public Dimension getDimension() 
	{
		return DIMENSION;
	}
	
	@Override
	public String toString()
	{
		return m_value.toString();
	}

}
