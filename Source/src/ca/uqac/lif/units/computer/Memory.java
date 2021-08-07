package ca.uqac.lif.units.computer;

import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public abstract class Memory extends DimensionValue
{
	public static final String NAME = "memory";
	
	protected static final Dimension DIMENSION = new Dimension().add(NAME, 1);
	
	public Memory(DimensionValue x)
	{
		super(x);
	}
	
	public Memory(double x)
	{
		super(x);
	}

	@Override
	public Dimension getDimension() 
	{
		return DIMENSION;
	}
}
