package ca.uqac.lif.units.functions;

import ca.uqac.lif.petitpoucet.function.FunctionException;
import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.number.Addition;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NoSuchUnitException;

public class UnitAdd extends Addition
{
	public UnitAdd(int in_arity)
	{
		super(in_arity);
	}
	
	public static DimensionValue get(Object ... inputs)
	{
		return (DimensionValue) new UnitAdd(inputs.length).evaluate(inputs)[0];
	}
	
	@Override
	public DimensionValue[] getValue(Object ... inputs)
	{
		DimensionValue[] out = new DimensionValue[1];
		Dimension target_dim = null;
		DimensionValue target_unit = null;
		double si_sum = 0;
		for (Object o : inputs)
		{
			if (!(o instanceof DimensionValue))
			{
				throw new InvalidArgumentTypeException("Operand must be a DimensionValue");
			}
			DimensionValue v = (DimensionValue) o;
			if (target_dim == null)
			{
				target_dim = v.getDimension();
				target_unit = v;
			}
			else
			{
				if (!target_dim.equals(v.getDimension()))
				{
					throw new InvalidArgumentTypeException("Cannot add " + target_dim + " with " + v.getDimension());
				}
			}
			si_sum += v.asBaseUnit().get();
		}
		try
		{
			DimensionValue v_temp = DimensionValue.instantiate(si_sum, target_unit.asBaseUnit().getClass());
			out[0] = DimensionValue.instantiate(v_temp, target_unit.getClass());
		}
		catch (NoSuchUnitException e)
		{
			// Not supposed to happen
			throw new FunctionException(e);
		}
		return out;
	}
	
	@Override
	public UnitAdd duplicate(boolean with_state)
	{
		UnitAdd ua = new UnitAdd(getInputArity());
		copyInto(ua, with_state);
		return ua;
	}
}
