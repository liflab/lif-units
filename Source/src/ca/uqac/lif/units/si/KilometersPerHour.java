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

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Velocity;

/**
 * A speed value derived from {@link MetersPerSecond}. One kilometer per hour
 * is 5/18 m/s.
 * 
 * @author Sylvain Hallé
 *
 */
public class KilometersPerHour extends Velocity
{
	protected static final Rational s_factor = Rational.get(5, 18);
	
	public KilometersPerHour(DimensionValue x)
	{
		super(x);
	}
	
	public KilometersPerHour(double x)
	{
		super(x);
	}
	
	@Override
	protected Real fromBaseUnit(Real x)
	{
		return x.divide(s_factor);
	}
	
	@Override
	public String toString()
	{
		return m_value + " km/h";
	}

	@Override
	public DimensionValue asBaseUnit() 
	{
		return new MetersPerSecond(m_value.multiply(s_factor));
	}
}
