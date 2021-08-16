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
	 * Rounds a number not to exceed the precision of another number. The
	 * precision is defined as the smallest power of 10 with a non-zero digit.
	 * The actual value of the target number is irrelevant; only its precision
	 * is used. Some examples:
	 * <ul>
	 * <li>if the number to round is 123.456 and its target number is 0.03,
	 * the result is 123.45, since the target number has two decimals</li>
	 * <li>if the number to round is 123.456 and its target number is 0.03258,
	 * the result is 123.456, since the target number has five decimals and
	 * the original number has only three</li>
	 * <li>if the number to round is 123.456 and its target number is 50, the
	 * result is 120, as the target number is precise up to 10²</li>
	 * </ul> 
	 * @param x The number to round
	 * @param target The target number whose precision is to be matched
	 */
	public static double roundToPrecision(double x, double target)
	{
		return roundToPower(x, getPrecision(target));
	}

	/**
	 * Gets the precision of a number. The precision is defined as the smallest
	 * power of 10 with a non-zero digit.
	 * @param x The number to get the precision of
	 * @param target The exponent of the power of 10 corresponding to the
	 * precision
	 */
	public static int getPrecision(double x)
	{
		int pow = 0;
		if ((int) x == x)
		{
			while (((int) x) == x)
			{
				x /= 10;
				pow++;
			}
			return pow - 1;
		}
		while (((int) x) != x)
		{
			x *= 10;
			pow--;
		}
		return pow;
	}

	/**
	 * Rounds a number to the specified power of 10. For example, rounding
	 * 173.224 to 10¹ produces 170, rounding it to 10<sup>-1</sup> produces
	 * 173.2, etc. 
	 * @param x The number to round
	 * @param power The power of 10 to round to
	 * @return
	 */
	public static double roundToPower(double x, int power)
	{
		double pow = Math.pow(10, -power);
		int v1 = (int) Math.round(x * pow);
		return v1 / pow;
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
