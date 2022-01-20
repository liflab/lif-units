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
 * Unit of length of the Imperial Unit system, derived from the meter. An
 * inch is exactly 0.0254 meter, or 127/5000.
 * 
 * @author Sylvain Hallé
 *
 */
public class Inch extends Length
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Real s_factor = Rational.get(127, 5000);
	
	public Inch(double d)
	{
		super(d);
	}
	
	public Inch(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	public Inch(/*@ non_null @*/ Real x)
	{
		super(x);
	}
	
	@Override
	public String getUnitName()
	{
		return "\"";
	}

	@Override
	public String toString()
	{
		if (m_value.getUncertainty() == 0)
		{
			return m_value + "\"";
		}
		return "(" + m_value + ")\"";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new Meter(m_value.multiply(s_factor));
	}
	
	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x.divide(s_factor);
	}
}
