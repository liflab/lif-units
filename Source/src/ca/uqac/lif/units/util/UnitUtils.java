/*
  Copyright 2021-2022 Sylvain Hallé
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
package ca.uqac.lif.units.util;

import ca.uqac.lif.numbers.FloatingPoint;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Meter;
import ca.uqac.lif.units.si.Second;

/**
 * A set of static utility methods to shorten the creation of common dimension
 * values. For example, using the methods in this class, it is possible to
 * write:
 * <pre>Second s = s(0.5, 0.1);</pre>
 * as a shorthand for:
 * <pre>Second s = new Second(FloatingPoint.get(0.5, 0.1));</pre>
 * @author Sylvain Hallé
 */
public abstract class UnitUtils
{
	/**
	 * Protected constructor.
	 */
	protected UnitUtils()
	{
		super();
	}
	
	/**
	 * Gets an instance of {@link FloatingPoint}.
	 * @param elements An array of doubles, which must be of size 1 or 2. The
	 * first (mandatory) element is the value, and the second (optional) is the
	 * uncertainty.
	 * @return The floating point instance
	 */
	/*@ non_null @*/ public static FloatingPoint f(double ... elements)
	{
		if (elements.length == 0)
		{
			return FloatingPoint.get(0);
		}
		if (elements.length == 1)
		{
			return FloatingPoint.get(elements[0]);
		}
		return FloatingPoint.get(elements[0], elements[1]);
	}
	
	/**
	 * Gets an instance of {@link Centimeter}.
	 * @param elements An array of doubles. See {@link #f(double...)} for their
	 * meaning.
	 * @return The instance
	 */
	/*@ non_null @*/ public static Centimeter cm(double ... elements)
	{
		return new Centimeter(f(elements));
	}
	
	/**
	 * Gets an instance of {@link Centimeter}.
	 * @param elements An array of doubles. See {@link #f(double...)} for their
	 * meaning.
	 * @return The instance
	 */
	/*@ non_null @*/ public static Meter m(double ... elements)
	{
		return new Meter(f(elements));
	}
	
	/**
	 * Gets an instance of {@link Inch}.
	 * @param elements An array of doubles. See {@link #f(double...)} for their
	 * meaning.
	 * @return The instance
	 */
	/*@ non_null @*/ public static Inch in(double ... elements)
	{
		return new Inch(f(elements));
	}
	
	/**
	 * Gets an instance of {@link Inch}.
	 * @param elements An array of doubles. See {@link #f(double...)} for their
	 * meaning.
	 * @return The instance
	 */
	/*@ non_null @*/ public static Second s(double ... elements)
	{
		return new Second(f(elements));
	}
}
