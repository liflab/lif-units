package ca.uqac.lif.units;

import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Kilogram;

/**
 * Exception thrown when attempting to instantiate a quantity from another
 * quantity with incompatible dimensions. For example, instantiating a
 * {@link Centimeter} from an {@link Inch} is valid (both have dimension
 * <i>length</i><sup>1</sup>), but passing it a {@link Kilogram} would throw
 * such an exception.
 *  
 * @author Sylvain Hallé
 *
 */
public class IncompatibleDimensions extends RuntimeException
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;
	
	public IncompatibleDimensions(Throwable t)
	{
		super(t);
	}
	
	public IncompatibleDimensions(String s)
	{
		super(s);
	}
}
