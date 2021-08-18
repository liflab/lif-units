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

import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Kilogram;

/**
 * Exception thrown when attempting to instantiate a quantity from another
 * quantity with incompatible dimensions. For example, instantiating a
 * {@link Centimeter} from an {@link Inch} is valid (both have dimension
 * <i>length</i><sup>1</sup>), but passing it a {@link Kilogram} would throw
 * such an exception.
 *  
 * @author Sylvain Hallé
 *
 */
public class IncompatibleDimensions extends RuntimeException
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;
	
	public IncompatibleDimensions(Throwable t)
	{
		super(t);
	}
	
	public IncompatibleDimensions(String s)
	{
		super(s);
	}
}
