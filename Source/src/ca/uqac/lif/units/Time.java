package ca.uqac.lif.units;

/**
 * A time value.
 * @author Sylvain Hallé
 */
public abstract class Time extends DimensionValue
{
	/**
	 * The dimension of the time value (i.e. <i>time</i><sup>1</sup>).
	 */
	/*@ non_null @*/ public static final Dimension DIMENSION = new Dimension().add(Quantity.TIME, 1);
	
	public Time(double x)
	{
		super(x);
	}
	
	public Time(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}