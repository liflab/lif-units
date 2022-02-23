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

/**
 * A time value.
 * @author Sylvain Hallé
 */
public abstract class Time extends DimensionValue
{
	/**
	 * The dimension of the time value (i.e. <i>time</i><sup>1</sup>).
	 */
	/*@ non_null @*/ public static final transient Dimension DIMENSION = new Dimension().add(Quantity.TIME, 1);
	
	public Time(double x)
	{
		super(x);
	}
	
	public Time(/*@ non_null @*/ DimensionValue x)
	{
		super(x);
	}
	
	public Time(/*@ non_null @*/ Real x)
	{
		super(x);
	}
	
	protected Time()
	{
		super();
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}