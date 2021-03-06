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

public class Scalar extends DimensionValue 
{
	public static final transient Dimension DIMENSION = new Dimension();
	
	public Scalar(DimensionValue x) 
	{
		super(x);
	}
	
	public Scalar(double d) 
	{
		super(d);
	}
	
	protected Scalar()
	{
		super();
	}

	@Override
	public DimensionValue asBaseUnit()
	{
		return this;
	}

	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x;
	}

	@Override
	public Dimension getDimension() 
	{
		return DIMENSION;
	}
	
	@Override
	public String toString()
	{
		return m_value.toString();
	}
	
	@Override
	public String getUnitName()
	{
		return "";
	}

}
