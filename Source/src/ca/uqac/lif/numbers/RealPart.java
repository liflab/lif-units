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
package ca.uqac.lif.numbers;

import ca.uqac.lif.petitpoucet.Part;

/**
 * Common ancestor to parts of a {@link Real} number.
 * @author Sylvain Hallé
 */
public abstract class RealPart implements Part
{
	/**
	 * Public instance of the {@link RealValue} part.
	 */
	public static final RealValue realValue = new RealValue();
	
	/**
	 * Public instance of the {@link Uncertainty} part.
	 */
	public static final Uncertainty uncertainty = new Uncertainty();
	
	@Override
	public RealPart head()
	{
		return this;
	}

	@Override
	public Part tail()
	{
		return null;
	}

	@Override
	public boolean appliesTo(Object o)
	{
		return o instanceof Real;
	}

	/**
	 * Part designating the real value of a number.
	 */
	public static class RealValue extends RealPart
	{
		protected RealValue()
		{
			super();
		}

		@Override
		public String toString()
		{
			return "Real value";
		}
	}

	/**
	 * Part designating the real value of a number.
	 */
	public static class Uncertainty extends RealPart
	{
		protected Uncertainty()
		{
			super();
		}

		@Override
		public String toString()
		{
			return "Uncertainty";
		}
	}
}
