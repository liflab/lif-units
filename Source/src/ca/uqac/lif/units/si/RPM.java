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
import ca.uqac.lif.units.AngularVelocity;
import ca.uqac.lif.units.DimensionValue;

/**
 * An angular velocity value derived from {@link RadiansPerSecond}. One RPM
 * is approximately 0.10472 rad/s.
 * 
 * @author Sylvain Hallé
 *
 */
public class RPM extends AngularVelocity
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final FloatingPoint s_factor = FloatingPoint.get(0.10472);
	
	public RPM(DimensionValue x)
	{
		super(x);
	}
	
	public RPM(double x)
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
		return m_value + " RPM";
	}

	@Override
	public AngularVelocity asBaseUnit()
	{
		return new RadiansPerSecond(m_value.multiply(s_factor));
	}
}
