package ca.uqac.lif.units;

public abstract class SurfaceValue extends DimensionValue
{
	/**
	 * The dimension of the length value (i.e. <i>length</i><sup>1</sup>).
	 */
	/*@ non_null @*/ public static final Dimension DIMENSION = new Dimension().add(Quantity.LENGTH, 2);
	
	public SurfaceValue(double x)
	{
		super(x);
	}
	
	public SurfaceValue(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	@Override
	/*@ non_null @*/ public Dimension getDimension()
	{
		return DIMENSION;
	}
}
