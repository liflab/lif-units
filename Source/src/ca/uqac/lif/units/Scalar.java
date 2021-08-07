package ca.uqac.lif.units;

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
	protected double fromBaseUnit(double x) 
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
		return Double.toHexString(m_value);
	}

}
