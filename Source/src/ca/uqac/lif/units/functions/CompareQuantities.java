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
import ca.uqac.lif.petitpoucet.function.InvalidArgumentException;
import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.InvalidNumberOfArgumentsException;
import ca.uqac.lif.units.DimensionValue;

/**
 * A function that evaluates a Boolean condition involving two
 * {@link DimensionValue}s.
 * @author Sylvain Hallé
 *
 */
public abstract class CompareQuantities extends AtomicFunction
{
	/**
	 * Creates a new instance of the function.
	 */
	public CompareQuantities()
	{
		super(2, 1);
	}
	
	@Override
	protected Object[] getValue(Object ... args) throws InvalidNumberOfArgumentsException 
	{
		if (!(args[0] instanceof DimensionValue) || !(args[1] instanceof DimensionValue))
		{
			throw new InvalidArgumentException("Arguments must be dimension values");
		}
		DimensionValue dv1 = (DimensionValue) args[0];
		DimensionValue dv2 = (DimensionValue) args[1];
		if (!dv1.getDimension().equals(dv2.getDimension()))
		{
			throw new InvalidArgumentTypeException("Incomparable dimensions");
		}
		return new Object[] {compare(dv1, dv2)};
	}
	
	/**
	 * Compares two dimension values.
	 * @param dv1 The first value
	 * @param dv2 The second value
	 * @return The result of the comparison
	 */
	protected abstract boolean compare(DimensionValue dv1, DimensionValue dv2);
	
	/**
	 * Asserts that a dimension value is greater than another one, taking into
	 * account the unit in which each value is expressed.
	 */
	public static class QuantityIsGreaterThan extends CompareQuantities
	{
		/**
		 * Creates a new instance of the function.
		 */
		public QuantityIsGreaterThan()
		{
			super();
		}

		@Override
		protected boolean compare(DimensionValue dv1, DimensionValue dv2) 
		{
			return dv1.compareTo(dv2) > 0;
		}
		
		@Override
		public QuantityIsGreaterThan duplicate(boolean with_state)
		{
			return new QuantityIsGreaterThan();
		}
		
		@Override
		public String toString()
		{
			return "\u227b";
		}
	}
	
	/**
	 * Asserts that a dimension value is less than another one, taking into
	 * account the unit in which each value is expressed.
	 */
	public static class QuantityIsLessThan extends CompareQuantities
	{
		/**
		 * Creates a new instance of the function.
		 */
		public QuantityIsLessThan()
		{
			super();
		}

		@Override
		protected boolean compare(DimensionValue dv1, DimensionValue dv2) 
		{
			return dv1.compareTo(dv2) < 0;
		}
		
		@Override
		public QuantityIsGreaterThan duplicate(boolean with_state)
		{
			return new QuantityIsGreaterThan();
		}
		
		@Override
		public String toString()
		{
			return "\u227a";
		}
	}
	
	/**
	 * Asserts that a dimension value is equal to another one, taking into
	 * account the unit in which each value is expressed.
	 */
	public static class QuantityIsEqual extends CompareQuantities
	{
		/**
		 * Creates a new instance of the function.
		 */
		public QuantityIsEqual()
		{
			super();
		}

		@Override
		protected boolean compare(DimensionValue dv1, DimensionValue dv2) 
		{
			return dv1.compareTo(dv2) == 0;
		}
		
		@Override
		public QuantityIsGreaterThan duplicate(boolean with_state)
		{
			return new QuantityIsGreaterThan();
		}
		
		@Override
		public String toString()
		{
			return "\u2245";
		}
	}
}
