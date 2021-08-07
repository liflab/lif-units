package ca.uqac.lif.units;

public abstract class Angle extends DimensionValue
{
	/**
	 * The dimension of the angle value (i.e. <i>algne</i><sup>1</sup>).
	 */
	/*@ non_null @*/ public static final Dimension DIMENSION = new Dimension().add(Quantity.ANGLE, 1);
	
	public Angle(double x)
	{
		super(x);
	}
	
	public Angle(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	@Override
	/*@ non_null @*/ public Dimension getDimension()
	{
		return DIMENSION;
	}
}
