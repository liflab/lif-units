package ca.uqac.lif.units;

/**
 * A dimension value without a name, but which is assumed to be expressed in
 * the base units for each of its dimensions.
 * @author Sylvain Hallé
 */
public class NamelessDimensionValue extends DimensionValue
{
	/*@ non_null @*/ protected Dimension m_dimension;
	
	public NamelessDimensionValue(double x, /*@ non_null @*/ Dimension d)
	{
		super(x);
		m_dimension = d;
	}
	
	public NamelessDimensionValue(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
		m_dimension = x.getDimension();
	}

	@Override
	protected double fromBaseUnit(double x) 
	{
		return x;
	}

	@Override
	/*@ non_null @*/ public Dimension getDimension() 
	{
		return m_dimension;
	}

	@Override
	public DimensionValue asBaseUnit()
	{
		return this;
	}
}
