package ca.uqac.lif.units;

import ca.uqac.lif.units.functions.UnitAdd;
import ca.uqac.lif.units.functions.UnitDivide;
import ca.uqac.lif.units.functions.UnitMultiply;

public abstract class Unit
{
	public static final DimensionValue add(Object ... values)
	{
		return UnitAdd.get(values);
	}
	
	public static final DimensionValue multiply(Object ... values)
	{
		return UnitMultiply.get(values);
	}
	
	public static final DimensionValue divide(Object ... values)
	{
		return UnitDivide.get(values);
	}
}
