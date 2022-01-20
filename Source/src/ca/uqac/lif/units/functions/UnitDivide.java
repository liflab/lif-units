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
import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.number.Multiplication;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;

/**
 * Divides two dimension values.
 * @author Sylvain Hallé
 */
public class UnitDivide extends Multiplication
{
	/**
	 * Creates a new instance of the function.
	 */
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
