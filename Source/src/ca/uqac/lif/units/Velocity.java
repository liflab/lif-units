package ca.uqac.lif.units;

import ca.uqac.lif.numbers.Real;

/**
 * A speed value.
 * @author Sylvain Hallé
 */
public abstract class Velocity extends DimensionValue
{
	/**
	 * The dimension of the time value (i.e. <i>length</i><sup>1</sup> &sdot; <i>time</i><sup>-1</sup>).
	 */
	public static final Dimension DIMENSION = new Dimension().add(Quantity.LENGTH, 1).add(Quantity.TIME, -1);

	public Velocity(DimensionValue x) 
	{
		super(x);
	}
	
	public Velocity(Real x) 
	{
		super(x);
	}
	
	public Velocity(double x) 
	{
		super(x);
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}
