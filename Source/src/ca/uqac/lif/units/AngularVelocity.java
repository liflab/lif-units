package ca.uqac.lif.units;

/**
 * A value expressing rotation speed.
 * @author Sylvain Hallé
 */
public abstract class AngularVelocity extends DimensionValue
{
	/**
	 * The dimension of the time value (i.e. <i>angle</i><sup>1</sup> &sdot; <i>time</i><sup>-1</sup>).
	 */
	public static final Dimension DIMENSION = new Dimension().add(Quantity.ANGLE, 1).add(Quantity.TIME, -1);

	public AngularVelocity(DimensionValue x) 
	{
		super(x);
	}
	
	public AngularVelocity(double x) 
	{
		super(x);
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}
