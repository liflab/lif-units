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
package ca.uqac.lif.units.si;

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Frequency;

public class Hertz extends Frequency
{
	public Hertz(double d)
	{
		super(d);
	}
	
	public Hertz(DimensionValue x) 
	{
		super(x);
	}

	@Override
	public String getUnitName()
	{
		return "Hz";
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
}
