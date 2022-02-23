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

import ca.uqac.lif.petitpoucet.function.AtomicFunction;
import ca.uqac.lif.petitpoucet.function.FunctionException;
import ca.uqac.lif.petitpoucet.function.InvalidNumberOfArgumentsException;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NoSuchUnitException;

/**
 * Converts a dimension value into another one with a specified unit.
 * @author Sylvain Hallé
 */
public class ConvertTo extends AtomicFunction
{
	/**
	 * The unit to which input arguments should be converted.
	 */
	protected final Class<? extends DimensionValue> m_unit;
	
	/**
	 * Creates a new instance of the function
	 * @param unit The unit to which input arguments should be converted
	 */
	public ConvertTo(Class<? extends DimensionValue> unit)
	{
		super(1, 1);
		m_unit = unit;
	}

	@Override
	protected Object[] getValue(Object... inputs) throws InvalidNumberOfArgumentsException
	{
		if (!(inputs[0] instanceof DimensionValue))
		{
			throw new FunctionException("Input argument is not a DimensionValue");
		}
		try
		{
			DimensionValue out = DimensionValue.instantiate(((DimensionValue) inputs[0]), m_unit);
			return new Object[] {out};
		}
		catch (NoSuchUnitException e)
		{
			throw new FunctionException(e);
		}
	}
	
	@Override
	public String toString()
	{
		return DimensionValue.getUnitName(m_unit);
	}
	
	@Override
	public ConvertTo duplicate(boolean with_state)
	{
		ConvertTo ct = new ConvertTo(m_unit);
		super.copyInto(ct, with_state);
		return ct;
	}
}
