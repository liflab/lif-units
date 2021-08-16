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
 * A real number with no fractional part. Whole numbers have a simpler
 * internal representation, and arithmetic operations between whole numbers
 * are faster than operations involving rationals.
 * 
 * @author Sylvain Hallé
 */
public class Whole implements Real
{
	/**
	 * A constant representing the whole number 0.
	 */
	public static final Whole ZERO = Whole.get(0);
	
	/**
	 * A constant representing the whole number 1.
	 */
	public static final Whole ONE = Whole.get(1);
	
	/**
	 * A constant representing the whole number -1.
	 */
	public static final Whole MINUS_ONE = Whole.get(-1);
	
	/**
	 * The integer value of the number.
	 */
	protected final int m_value;
	
	/**
	 * The uncertainty associated to this number. Can be zero or any positive
	 * number.
	 */
	protected final double m_uncertainty;
	
	/**
	 * Creates a whole number out of a integer value.
	 * @param value The integer value of the number
	 * @return The whole number instance
	 */
	public static Whole get(int value)
	{
		return new Whole(value);
	}
	
	/**
	 * Creates a whole number out of a integer value and specifies its
	 * associated uncertainty.
	 * @param value The integer value of the number
	 * @param uncertainty The uncertainty associated to this number
	 * @return The whole number instance
	 */
	public static Whole get(int value, double uncertainty)
	{
		return new Whole(value, uncertainty);
	}
	
	/**
	 * Creates a whole number out of a integer value and specifies its
	 * associated uncertainty.
	 * @param value The integer value of the number
	 * @param uncertainty The uncertainty associated to this number
	 */
	private Whole(int value, double uncertainty)
	{
		super();
		m_value = value;
		m_uncertainty = NumberFormatter.roundUpToSignificantFigures(Math.abs(uncertainty), 1);
	}
	
	/**
	 * Creates a whole number out of a integer value.
	 * @param value The integer value of the number
	 */
	private Whole(int value)
	{
		this(value, 0);
	}
	
	@Override
	/*@ pure non_null @*/ public Real pow(Real x) 
	{
		double d_x = x.doubleValue();
		if (d_x == (int) d_x)
		{
			int new_value = (int) Math.pow(m_value, d_x);
			return Whole.get((int) Math.pow(m_value, d_x), d_x * new_value * getRelativeUncertainty());
		}
		double new_value = Math.pow(m_value, d_x);
		return FloatingPoint.get(new_value, d_x * new_value * getRelativeUncertainty());
	}

	@Override
	/*@ pure non_null @*/ public Real add(Real x) 
	{
		if (x instanceof Rational || x instanceof FloatingPoint)
		{
			return x.add(this);
		}
		return Whole.get(m_value + x.intValue(), m_uncertainty + x.getUncertainty());
	}

	@Override
	/*@ pure non_null @*/ public Real subtract(Real x)
	{
		if (x instanceof Rational || x instanceof FloatingPoint)
		{
			return x.subtract(opposite());
		}
		return Whole.get(m_value - x.intValue(), m_uncertainty + x.getUncertainty());
	}

	@Override
	/*@ pure non_null @*/ public Real divide(Real x)
	{
		if (x instanceof Rational || x instanceof FloatingPoint)
		{
			return x.divide(inverse());
		}
		double d_value = (double) m_value / x.doubleValue(); 
		if (d_value == (int) d_value)
		{
			return Whole.get((int) d_value, d_value * (getRelativeUncertainty() + x.getRelativeUncertainty()));
		}
		else
		{
			// Quotient is not a whole number
			return Rational.get(m_value, x.intValue(), d_value * (getRelativeUncertainty() + x.getRelativeUncertainty()));
		}
	}

	@Override
	/*@ pure non_null @*/ public Real multiply(Real x)
	{
		if (x instanceof Rational || x instanceof FloatingPoint)
		{
			return x.multiply(this);
		}
		int new_value = m_value * x.intValue();
		return Whole.get(new_value, (double) new_value * (getRelativeUncertainty() + x.getRelativeUncertainty()));
	}
	
	@Override
	/*@ pure @*/ public Real opposite() 
	{
		return Whole.get(-m_value, m_uncertainty);
	}

	@Override
	/*@ pure @*/ public double doubleValue() 
	{
		return m_value;
	}

	@Override
	/*@ pure @*/ public float floatValue()
	{
		return m_value;
	}

	@Override
	/*@ pure @*/ public int intValue()
	{
		return m_value;
	}
	
	@Override
	/*@ pure @*/ public double getUncertainty()
	{
		return m_uncertainty;
	}
	
	@Override
	/*@ pure @*/ public double getRelativeUncertainty()
	{
		return m_uncertainty / (double) m_value;
	}
	
	@Override
	public String toString()
	{
		if (m_uncertainty == 0)
		{
			return Integer.toString(m_value);
		}
		StringBuilder out = new StringBuilder();
		out.append("(").append(m_value).append(" \u00b1 ").append(m_uncertainty).append(")");
		return out.toString();
	}
	
	@Override
	public int hashCode()
	{
		return m_value;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof Whole))
		{
			return false;
		}
		Whole w = (Whole) o;
		return w.m_value == m_value && w.m_uncertainty == m_uncertainty;
	}

	@Override
	public Real inverse() 
	{
		if (m_value == 1)
		{
			return Whole.ONE;
		}
		if (m_value == -1)
		{
			return Whole.MINUS_ONE;
		}
		return Rational.get(1, 1).divide(this);
	}
}
