/*
  Copyright 2021 Sylvain Hallé
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

import ca.uqac.lif.numbers.Real;

/**
 * A dimension value without a name, but which is assumed to be expressed in
 * the base units for each of its dimensions.
 * @author Sylvain Hallé
 */
public class NamelessDimensionValue extends DimensionValue
{
	/*@ non_null @*/ protected Dimension m_dimension;
	
	public NamelessDimensionValue(double x, /*@ non_null @*/ Dimension d)
	{
		super(x);
		m_dimension = d;
	}
	
	public NamelessDimensionValue(/*@ non_null @*/ Real x, /*@ non_null @*/ Dimension d)
	{
		super(x);
		m_dimension = d;
	}
	
	public NamelessDimensionValue(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
		m_dimension = x.getDimension();
	}
	
	protected NamelessDimensionValue()
	{
		super();
	}

	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x;
	}

	@Override
	/*@ non_null @*/ public Dimension getDimension() 
	{
		return m_dimension;
	}

	@Override
	public DimensionValue asBaseUnit()
	{
		return this;
	}

	@Override
	public String getUnitName()
	{
		return m_dimension.toString();
	}
}
