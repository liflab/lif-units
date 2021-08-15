package ca.uqac.lif.units.functions;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.number.Multiplication;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;

public class UnitDivide extends Multiplication
{
	public UnitDivide()
	{
		super(2);
	}

	public static DimensionValue get(Object ... inputs)
	{
		return (DimensionValue) new UnitDivide().evaluate(inputs)[0];
	}

	@Override
	public DimensionValue[] getValue(Object ... inputs)
	{
		DimensionValue[] out = new DimensionValue[1];
		Dimension target_dim = new Dimension();
		Object o1 = inputs[0];
		if (!(o1 instanceof DimensionValue))
		{
			throw new InvalidArgumentTypeException("Operand must be a DimensionValue");
		}
		DimensionValue v1 = (DimensionValue) o1;
		target_dim.add(v1.getDimension());
		Real si_prod = v1.asBaseUnit().get();
		Object o2 = inputs[1];
		if (!(o2 instanceof DimensionValue))
		{
			throw new InvalidArgumentTypeException("Operand must be a DimensionValue");
		}
		DimensionValue v2 = (DimensionValue) o2;
		target_dim.subtract(v2.getDimension());
		Real val2 = v2.asBaseUnit().get();
		out[0] = new NamelessDimensionValue(si_prod.divide(val2), target_dim);
		return out;
	}

	@Override
	public UnitDivide duplicate(boolean with_state)
	{
		UnitDivide um = new UnitDivide();
		copyInto(um, with_state);
		return um;
	}
}
