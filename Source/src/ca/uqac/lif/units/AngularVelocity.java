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
 * A value expressing rotation speed.
 * @author Sylvain Hallé
 */
public abstract class AngularVelocity extends DimensionValue
{
	/**
	 * The dimension of the time value (i.e. <i>angle</i><sup>1</sup> &sdot; <i>time</i><sup>-1</sup>).
	 */
	public static final Dimension DIMENSION = new Dimension().add(Quantity.ANGLE, 1).add(Quantity.TIME, -1);

	public AngularVelocity(DimensionValue x) 
	{
		super(x);
	}
	
	public AngularVelocity(Real x) 
	{
		super(x);
	}
	
	public AngularVelocity(double x) 
	{
		super(x);
	}
	
	@Override
	public Dimension getDimension()
	{
		return DIMENSION;
	}
}
