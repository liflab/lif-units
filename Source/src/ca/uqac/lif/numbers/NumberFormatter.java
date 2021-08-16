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
package ca.uqac.lif.numbers;

/**
 * Provides utility classes to format numbers.
 * @author Sylvain Hallé
 *
 */
public class NumberFormatter 
{
	/**
	 * Private constructor, as this class is not meant to be instantiated.
	 */
	private NumberFormatter()
	{
		super();
	}

	/**
	 * Rounds number num to n significant figures.
	 * Found from <a href="http://stackoverflow.com/a/1581007">StackOverflow</a>
	 * @param num The number
	 * @param n The number of significant figures
	 * @return The resulting number
	 */
	public static double roundToSignificantFigures(double num, int n) 
	{
		if (num == 0) 
		{
			return 0;
		}
		final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
		final int power = n - (int) d;
		final double magnitude = Math.pow(10, power);
		final long shifted = Math.round(num*magnitude);
		return shifted/magnitude;
	}
	
	/**
	 * Calculates the ceiling of a number expressed to n significant figures.
	 * Found from <a href="http://stackoverflow.com/a/1581007">StackOverflow</a>
	 * @param num The number
	 * @param n The number of significant figures
	 * @return The resulting number
	 */
	public static double roundUpToSignificantFigures(double num, int n) 
	{
		if (num == 0) 
		{
			return 0;
		}
		final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
		final int power = n - (int) d;
		final double magnitude = Math.pow(10, power);
		final long shifted = (long) Math.ceil(num*magnitude);
		return shifted/magnitude;
	}

	/**
	 * Displays a number as a string in the most appropriate way. Typically,
	 * this means that an integer number will not show its decimals. A few
	 * constants, such as &pi; and <i>e</i> are also detected and printed as
	 * symbols.
	 * @param x The number to turn into a string
	 * @return The string
	 */
	public static String display(double x)
	{
		if (x == (int) x)
		{
			return Integer.toString((int) x);
		}
		if (x == Math.PI)
		{
			return "\u03c0";
		}
		if (x == Math.E)
		{
			return "e";
		}
		return Double.toString(x);
	}
}
