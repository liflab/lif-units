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

import static ca.uqac.lif.numbers.NumberFormatter.roundToPrecision;
import static ca.uqac.lif.numbers.NumberFormatter.roundUpToSignificantFigures;

/**
 * A real represented by a floating point decimal number. This is the least
 * symbolic representation of a real number; any arithmetic operation involving
 * at least one floating point number produces a floating point result.
 * 
 * @author Sylvain Hallé
 *
 */
public class FloatingPoint implements Real
{
	/**
	 * The value of the floating point number.
	 */
	private final double m_value;
	
	/**
	 * The uncertainty associated to this number. Can be zero or any positive
	 * number.
	 */
	protected final double m_uncertainty;
	
	/**
	 * Creates a new floating point number and specifies its uncertainty.
	 * @param value The value of the floating point number
	 * @param uncertainty The uncertainty associated to this number
	 */
	/*@ non_null @*/ public static FloatingPoint get(double value, double uncertainty)
	{
		return new FloatingPoint(value, uncertainty);
	}
	
	/**
	 * Creates a new floating point number.
	 * @param uncertainty The uncertainty associated to this number
	 */
	/*@ non_null @*/ public static FloatingPoint get(double value)
	{
		return new FloatingPoint(value);
	}
	
	/**
	 * Creates a new floating point number and specifies its uncertainty.
	 * @param value The value of the floating point number
	 * @param uncertainty The uncertainty associated to this number
	 */
	protected FloatingPoint(double value, double uncertainty)
	{
		super();
		m_uncertainty = roundUpToSignificantFigures(Math.abs(uncertainty), 1);
		m_value = roundToPrecision(value, m_uncertainty);
	}
	
	/**
	 * Creates a new floating point number.
	 * @param value The value of the floating point number
	 */
	protected FloatingPoint(double value)
	{
		this(value, 0);
	}

	@Override
	public FloatingPoint add(Real x) 
	{
		return FloatingPoint.get(m_value + x.doubleValue(), m_uncertainty + x.getUncertainty());
	}

	@Override
	public FloatingPoint subtract(Real x) 
	{
		return FloatingPoint.get(m_value - x.doubleValue(), m_uncertainty + x.getUncertainty());
	}

	@Override
	public FloatingPoint divide(Real x) 
	{
		double new_value = m_value / x.doubleValue();
		return FloatingPoint.get(new_value, new_value * (getRelativeUncertainty() + x.getRelativeUncertainty()));
	}

	@Override
	public FloatingPoint multiply(Real x)
	{
		double new_value = m_value * x.doubleValue();
		return FloatingPoint.get(new_value, new_value * (getRelativeUncertainty() + x.getRelativeUncertainty()));
	}
	
	@Override
	public Real pow(Real x) 
	{
		double new_value = Math.pow(m_value, x.doubleValue());
		return FloatingPoint.get(new_value, x.doubleValue() * new_value * getRelativeUncertainty());
	}
	
	@Override
	public Real opposite()
	{
		return new FloatingPoint(-m_value, m_uncertainty);
	}

	@Override
	public double doubleValue() 
	{
		return m_value;
	}

	@Override
	public float floatValue() 
	{
		return (float) m_value;
	}

	@Override
	public int intValue()
	{
		return (int) m_value;
	}
	
	@Override
	public String toString()
	{
		if (m_uncertainty == 0)
		{
			return NumberFormatter.display(m_value);
		}
		StringBuilder out = new StringBuilder();
		out.append(NumberFormatter.display(m_value)).append(" \u00b1 ").append(NumberFormatter.display(m_uncertainty));
		return out.toString();
	}
	
	@Override
	public int hashCode()
	{
		return (int) m_value;
	}
	
	@Override
	public boolean equals(Object o)
	{  
		if (o == null || !(o instanceof FloatingPoint))
		{
			return false;
		}
		FloatingPoint f = (FloatingPoint) o;
		return f.m_value == m_value && f.m_uncertainty == m_uncertainty;
	}
	
	@Override
	/*@ pure @*/ public double getUncertainty()
	{
		return m_uncertainty;
	}
	
	@Override
	/*@ pure @*/ public double getRelativeUncertainty()
	{
		if (m_value == 0)
		{
			return 0;
		}
		return m_uncertainty / (double) m_value;
	}
	
	@Override
	/*@ pure non_null @*/ public Real inverse() 
	{
		return Rational.get(1, 1).divide(this); 
	}
	
	@Override
	public int compareTo(Real x) 
	{
		double v1 = doubleValue();
		double v2 = x.doubleValue();
		if (v1 == v2)
		{
			return 0;
		}
		if (v1 < v2)
		{
			return -1;
		}
		return 1;
	}
}
