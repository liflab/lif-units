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
package ca.uqac.lif.units;

import ca.uqac.lif.petitpoucet.Part;

/**
 * Common ancestor to all {@link Part}s of a {@link DimensionValue} object.
 * @author Sylvain Hallé
 *
 */
public abstract class DimensionValuePart implements Part
{
	/**
	 * Public instance of the {@link UnitName} part.
	 */
	public static final transient UnitName unitName = new UnitName();
	
	/**
	 * Public instance of the {@link ScalarPart} part.
	 */
	public static final transient ScalarPart scalar = new ScalarPart();
	
	@Override
	public boolean appliesTo(Object o)
	{
		return o instanceof DimensionValue;
	}
	
	@Override
	public DimensionValuePart head()
	{
		return this;
	}
	
	@Override
	public Part tail()
	{
		return null;
	}
	
	/**
	 * Part designating the name of the unit of a dimension value.
	 */
	public static class UnitName extends DimensionValuePart
	{
		protected UnitName()
		{
			super();
		}
		
		@Override
		public String toString()
		{
			return "Unit";
		}
	}
	
	/**
	 * Part designating the scalar of a dimension value.
	 */
	public static class ScalarPart extends DimensionValuePart
	{
		protected ScalarPart()
		{
			super();
		}
		
		@Override
		public String toString()
		{
			return "Scalar";
		}
	}
}
