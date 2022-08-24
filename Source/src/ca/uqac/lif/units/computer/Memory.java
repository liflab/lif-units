package ca.uqac.lif.units.computer;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;

public abstract class Memory extends DimensionValue
{
	public static final transient String NAME = "memory";
	
	public static final transient Dimension DIMENSION = new Dimension().add(NAME, 1);
	
	public Memory(DimensionValue x)
	{
		super(x);
	}
	
	public Memory(double x)
	{
		super(x);
	}
	
	public Memory(Real x)
	{
		super(x);
	}
	
	protected Memory()
	{
		super();
	}

	@Override
	public Dimension getDimension() 
	{
		return DIMENSION;
	}
}
