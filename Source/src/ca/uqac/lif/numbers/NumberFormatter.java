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
package ca.uqac.lif.numbers;

/**
 * Provides utility classes to format numbers.
 * @author Sylvain Hallé
 *
 */
public class NumberFormatter 
{
	/**
	 * The UTF-8 "plus or minus" symbol.
	 */
	public static final String U_PM = "\u00b1";

	/**
	 * The UTF-8 "times" symbol.
	 */
	public static final String U_TIMES = "\u00d7";

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
	 * The actual value of the target number is irrelevant (except for 0);
	 * only its precision is used. Some examples:
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
	 * @param target The target number whose precision is to be matched.
	 * If target = 0, no rounding is performed and x is returned as is.
	 */
	public static double roundToPrecision(double x, double target)
	{
		if (target == 0)
		{
			return x;
		}
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
		x = Math.abs(x);
		double fractional_part = x % 1;
		if (fractional_part == 0)
		{
			for (double div = 1; div < Double.MAX_VALUE; div *= 10)
			{
				if ((x / div) % 10 != 0)
				{
					return (int) Math.floor(Math.log10(div));
				}
			}
			throw new ArithmeticException("Overflow");
		}
		else
		{
			for (double div = 1; div < Double.MAX_VALUE; div *= 10)
			{
				if ((x * div) % 1 == 0)
				{
					return (int) -Math.floor(Math.log10(div));
				}
			}
			throw new ArithmeticException("Underflow");
		}
	}

	/**
	 * Gets the magnitude of a double <i>x</i>. The magnitude is defined as the
	 * largest integer <i>n</i> such that 10<sup><i>n</i></sup> &leq;
	 * |<i>x</i>| &lt; 10<sup><i>n</i>+1</sup>. For example, the magnitude of 123
	 * is 2, while the magnitude of 0.04 is -2.
	 * @param x The number to get the magnitude of
	 * @return The magnitude
	 */
	public static int getMagnitude(double x)
	{
		if (x == 0)
		{
			return 0;
		}
		return (int) Math.floor(Math.log10(Math.abs(x)));
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

	/**
	 * Prints the number as a string, using scientific notation, and supplying
	 * the power of 10 used as the base. In the
	 * latter case, the number (whatever its type)
	 * is displayed in the form <i>m</i> &times; 10<sup><i>x</i></sup> if no
	 * uncertainty is associated to the value, and
	 * (<i>m</i> &pm; <i>u</i>) &times; 10<sup><i>x</i></sup> otherwise.
	 * @param x The number to print
	 * @param pow The power of 10 used to display the number (i.e. the value
	 * of <i>x</i> in the example above)
	 * @return The printed number
	 */
	/*@ non_null @*/ public static String printScientific(/*@ non_null @*/ Real x, int pow)
	{
		double value = x.doubleValue();
		double uncertainty = x.getUncertainty();
		StringBuilder out = new StringBuilder();
		if (uncertainty == 0)
		{
			double base = Math.pow(10, pow);
			/* The next line deserves an explanation. The exact operation should be
			 * mantissa = value / base. However, due to the floating point nature of
			 * doubles, this causes rounding errors --e.g. 0.142857 * 10 produces
			 * 1.4285700000001. The workaround is to round mantissa relative to the
			 * precision of the original value of x.
			 */
			double mantissa = roundToPower(value / base, getPrecision(value) - pow); 
			out.append(display(mantissa)).append(U_TIMES).append("10").append(toSuperscript(pow));
		}
		else
		{
			double base = Math.pow(10, pow);
			// Same as above here
			double mantissa = roundToPower(value / base, getPrecision(value) - pow);
			double unc = uncertainty / base;
			out.append("(").append(display(mantissa)).append(" ").append(U_PM).append(" ").append(display(unc)).append(") ").append(U_TIMES).append("10").append(toSuperscript(pow));
		}
		return out.toString();
	}

	/**
	 * Prints the number as a string, using scientific notation. In the
	 * latter case, the number (whatever its type)
	 * is displayed in the form <i>m</i> &times; 10<sup><i>x</i></sup> if no
	 * uncertainty is associated to the value, and
	 * (<i>m</i> &pm; <i>u</i>) &times; 10<sup><i>x</i></sup> otherwise.
	 * @param x The number to print
	 * @return The printed number
	 */
	/*@ non_null @*/ public static String printScientific(/*@ non_null @*/ Real x)
	{
		double uncertainty = x.getUncertainty();
		if (uncertainty == 0)
		{
			return printScientific(x, getMagnitude(x.doubleValue()));
		}
		return printScientific(x, getPrecision(uncertainty));
	}

	/**
	 * Prints an integer value with UTF-8 superscript digits.
	 * @param value The value to print
	 * @return The UTF-8 string
	 */
	/*@ non_null @*/ public static String toSuperscript(int value)
	{
		String s_value = Integer.toString(value);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < s_value.length(); i++)
		{
			String c = s_value.substring(i, i+1);
			switch (c)
			{
			// One has to look out, as the characters are not contiguous in
			// the UTF-8 table!
			case "-":
				out.append("\u207b");
				break;
			case "0":
				out.append("\u2070");
				break;
			case "1":
				out.append("\u00b9");
				break;
			case "2":
				out.append("\u00b2");
				break;
			case "3":
				out.append("\u00b3");
				break;
			case "4":
				out.append("\u2074");
				break;
			case "5":
				out.append("\u2075");
				break;
			case "6":
				out.append("\u2076");
				break;
			case "7":
				out.append("\u2077");
				break;
			case "8":
				out.append("\u2078");
				break;
			case "9":
				out.append("\u2079");
				break;
			}
		}
		return out.toString();
	}
	
	/**
	 * Prints an integer value with UTF-8 subscript digits.
	 * @param value The value to print
	 * @return The UTF-8 string
	 */
	/*@ non_null @*/ public static String toSubscript(int value)
	{
		String s_value = Integer.toString(value);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < s_value.length(); i++)
		{
			String c = s_value.substring(i, i+1);
			switch (c)
			{
			case "-":
				out.append("\u208b");
				break;
			case "0":
				out.append("\u2080");
				break;
			case "1":
				out.append("\u2081");
				break;
			case "2":
				out.append("\u2082");
				break;
			case "3":
				out.append("\u2083");
				break;
			case "4":
				out.append("\u2084");
				break;
			case "5":
				out.append("\u2085");
				break;
			case "6":
				out.append("\u2086");
				break;
			case "7":
				out.append("\u2087");
				break;
			case "8":
				out.append("\u2088");
				break;
			case "9":
				out.append("\u2089");
				break;
			}
		}
		return out.toString();
	}
	
	/**
	 * Pretty-prints a fraction using UTF-8 subscript and superscript
	 * characters.
	 * @param numerator The numerator of the fraction
	 * @param denominator The denominator of the fraction
	 * @return The printed fraction
	 */
	/*@ non_null @*/ public static String printFraction(int numerator, int denominator)
	{
		return toSuperscript(numerator) + "/" + toSubscript(denominator);
	}
}
