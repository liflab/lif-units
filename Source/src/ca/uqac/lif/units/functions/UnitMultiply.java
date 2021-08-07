package ca.uqac.lif.units.functions;

import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.number.Multiplication;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;

public class UnitMultiply extends Multiplication
{
	public UnitMultiply(int in_arity)
	{
		super(in_arity);
	}
	
	public static DimensionValue get(Object ... inputs)
	{
		return (DimensionValue) new UnitMultiply(inputs.length).evaluate(inputs)[0];
	}

	@Override
	public DimensionValue[] getValue(Object ... inputs)
	{
		DimensionValue[] out = new DimensionValue[1];
		Dimension target_dim = new Dimension();
		double si_prod = 1;
		for (int i = 0; i < inputs.length; i++)
		{
			Object o = inputs[i];
			if (!(o instanceof DimensionValue))
			{
				throw new InvalidArgumentTypeException("Operand must be a DimensionValue");
			}
			DimensionValue v = (DimensionValue) o;
			target_dim.add(v.getDimension());
			double val = v.asBaseUnit().get();
			if (val == 0)
			{
				m_nulls[i] = true;
			}
			else
			{
				m_nulls[i] = false;
			}
			si_prod *= val;
		}
		out[0] = new NamelessDimensionValue(si_prod, target_dim);
		return out;
	}

	@Override
	public UnitMultiply duplicate(boolean with_state)
	{
		UnitMultiply um = new UnitMultiply(getInputArity());
		copyInto(um, with_state);
		return um;
	}
}
