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

import ca.uqac.lif.numbers.FloatingPoint;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.Angle;
import ca.uqac.lif.units.DimensionValue;

/**
 * Unit of length derived from the radian. There are &pi;/180 radians in a degree.
 * @author Sylvain Hallé
 */
public class Degree extends Angle
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final FloatingPoint s_factor = FloatingPoint.get(180 / Math.PI);
	
	public Degree(double d)
	{
		super(d);
	}
	
	public Degree(DimensionValue x) 
	{
		super(x);
	}
	
	@Override
	public String toString()
	{
		return m_value + " cm";
	}
	
	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x.multiply(s_factor);
	}

	@Override
	public Angle asBaseUnit()
	{
		return new Radian(m_value.divide(s_factor));
	}
}
