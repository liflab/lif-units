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
import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.si.MetersPerSecond;

/**
 * A speed value derived from {@link MetersPerSecond}. One foot per second
 * is <em>exactly</em> 381/1250 meters per second.
 * 
 * @author Sylvain Hallé
 *
 */
public class FeetPerSecond extends Velocity
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Rational s_factor = Rational.get(381, 1250);
	
	public FeetPerSecond(DimensionValue x)
	{
		super(x);
	}
	
	public FeetPerSecond(double x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x.divide(s_factor);
	}
	
	@Override
	public String getUnitName()
	{
		return "ft/s";
	}
	
	@Override
	public DimensionValue asBaseUnit()
	{
		return new MetersPerSecond(m_value.multiply(s_factor));
	}
}
