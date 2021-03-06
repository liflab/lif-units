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
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Length;

/**
 * Unit of length derived from the meter. There are 100 centimeters in a
 * meter.
 * @author Sylvain Hallé
 */
public class Centimeter extends Length
{
	/**
	 * The conversion factor from the base unit.
	 */
	protected static final Whole s_factor = Whole.get(100);
	
	public Centimeter(double d)
	{
		super(d);
	}
	
	public Centimeter(DimensionValue x) 
	{
		super(x);
	}
	
	public Centimeter(Real x) 
	{
		super(x);
	}
	
	protected Centimeter()
	{
		super();
	}

	@Override
	public String getUnitName()
	{
		return "cm";
	}
	
	@Override
	protected Real fromBaseUnit(Real x) 
	{
		return x.multiply(s_factor);
	}

	@Override
	public DimensionValue asBaseUnit() 
	{
		return new Meter(m_value.divide(s_factor));
	}
}
