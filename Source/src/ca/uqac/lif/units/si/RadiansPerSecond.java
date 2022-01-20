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
import ca.uqac.lif.units.AngularVelocity;
import ca.uqac.lif.units.DimensionValue;

public class RadiansPerSecond extends AngularVelocity
{
	public RadiansPerSecond(DimensionValue x)
	{
		super(x);
	}
	
	public RadiansPerSecond(Real x)
	{
		super(x);
	}
	
	public RadiansPerSecond(double x)
	{
		super(x);
	}

	@Override
	public String getUnitName()
	{
		return "rad/s";
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
