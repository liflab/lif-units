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
package ca.uqac.lif.units.imperial;

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.si.Meter;

/**
 * Unit of length of the Imperial Unit system, derived from the meter. A
 * mile is exactly 1609.344 meter, or 201168/125 m.
 * 
 * @author Sylvain Hallé
 *
 */
public class Mile extends Length
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Rational s_factor = Rational.get(201168, 125);
	
	public Mile(double x)
	{
		super(x);
	}
	
	public Mile(DimensionValue d)
	{
		super(d);
	}
	
	@Override
	public String getUnitName()
	{
		return "mi";
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x.divide(s_factor);
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new Meter(m_value.multiply(s_factor));
	}
}
