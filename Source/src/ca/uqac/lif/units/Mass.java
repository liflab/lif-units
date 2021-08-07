package ca.uqac.lif.units;

public abstract class Mass extends DimensionValue
{
	/**
	 * The dimension of the mass value (i.e. <i>mass</i><sup>1</sup>).
	 */
	public static final Dimension DIMENSION = new Dimension().add(Quantity.MASS, 1);
	
	public Mass(double x)
	{
		super(x);
	}
	
	public Mass(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}