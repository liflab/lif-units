/*
  Copyright 2021-2022 Sylvain Hallé
  Laboratoire d'informatique formelle
  Université du Québec à Chicoutimi, Canada

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package ca.uqac.lif.units.functions;

import ca.uqac.lif.dag.NodeConnector;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.petitpoucet.function.Circuit;
import ca.uqac.lif.petitpoucet.function.Function;
import ca.uqac.lif.petitpoucet.function.FunctionException;
import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.number.Addition;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NoSuchUnitException;

/**
 * Function that adds a set of {@link DimensionValue}s.
 * @author Sylvain Hallé
 *
 */
public class UnitAdd extends Addition
{
	public static Function get(Function ... arguments)
	{
		if (arguments.length == 1)
		{
			return arguments[0];
		}
		Circuit c = new Circuit(arguments.length, 1);
		UnitAdd f = new UnitAdd(arguments.length);
		for (int i = 0; i < arguments.length; i++)
		{
			Function in_f = arguments[i];
			c.associateInput(0, f.getInputPin(0));
			NodeConnector.connect(in_f, 0, f, i);
		}
		c.associateOutput(0, f.getOutputPin(0));
		return c;
	}
	
	/**
	 * Creates a new instance of the function.
	 * @param in_arity The input arity of the function
	 */
	public UnitAdd(int in_arity)
	{
		super(in_arity);
	}
	
	/**
	 * Adds multiple dimension values and returns the result.
	 * @param inputs The dimension values to add
	 * @return The sum of all dimension values
	 */
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
		Real si_sum = Whole.ZERO;
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
			si_sum = si_sum.add(v.asBaseUnit().get());
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
