package ca.uqac.lif.units;

import ca.uqac.lif.numbers.Real;

public abstract class Length extends DimensionValue
{
	/**
	 * The dimension of the length value (i.e. <i>length</i><sup>1</sup>).
	 */
	/*@ non_null @*/ public static final Dimension DIMENSION = new Dimension().add(Quantity.LENGTH, 1);
	
	public Length(double x)
	{
		super(x);
	}
	
	public Length(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	public Length(/*@ non_null @*/ Real x)
	{
		super(x);
	}
	
	@Override
	/*@ non_null @*/ public Dimension getDimension()
	{
		return DIMENSION;
	}
}
