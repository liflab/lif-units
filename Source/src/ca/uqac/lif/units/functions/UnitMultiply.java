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

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
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
		Real si_prod = Whole.ONE;
		for (int i = 0; i < inputs.length; i++)
		{
			Object o = inputs[i];
			if (!(o instanceof DimensionValue))
			{
				throw new InvalidArgumentTypeException("Operand must be a DimensionValue");
			}
			DimensionValue v = (DimensionValue) o;
			target_dim.add(v.getDimension());
			Real val = v.asBaseUnit().get();
			if (val.doubleValue() == 0)
			{
				m_nulls[i] = true;
			}
			else
			{
				m_nulls[i] = false;
			}
			si_prod = si_prod.multiply(val);
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
