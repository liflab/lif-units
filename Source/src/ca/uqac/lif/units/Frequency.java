package ca.uqac.lif.units;

public abstract class Frequency extends DimensionValue 
{
	/**
	 * The dimension of the frequency value (i.e. <i>time</i><sup>-1</sup>).
	 */
	public static final Dimension DIMENSION = new Dimension().add(Quantity.TIME, -1);
	
	public Frequency(double x)
	{
		super(x);
	}
	
	public Frequency(DimensionValue x)
	{
		super(x);
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}
