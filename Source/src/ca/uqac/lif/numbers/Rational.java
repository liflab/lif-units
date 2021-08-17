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

import java.util.HashMap;
import java.util.Map;

/**
 * A real number represented as a product of integer numbers elevated at some
 * integer power.
 * @author Sylvain Hallé
 */
public class Rational implements Real
{
	/**
	 * The factors composing this rational number.
	 */
	/*@ non_null @*/ protected final Map<Integer,Integer> m_factors;
	
	/**
	 * The uncertainty associated to this number. Can be zero or any positive
	 * number.
	 */
	protected final double m_uncertainty;
	
	/**
	 * A flag indicating if the rational is positive or negative.
	 */
	protected boolean m_positive;

	/**
	 * Gets a rational number instance out of integer numerator and
	 * denominator and specifies its uncertainty. 
	 * @param numerator The numerator
	 * @param denominator The denominator
	 * @param uncertainty The uncertainty associated to this number
	 * @return The rational instance
	 */
	public static Rational get(int numerator, int denominator, double uncertainty)
	{
		return new Rational(numerator, denominator, uncertainty);
	}
	
	/**
	 * Gets a rational number instance out of integer numerator and
	 * denominator. 
	 * @param numerator The numerator
	 * @param denominator The denominator
	 * @return The rational instance
	 */
	public static Rational get(int numerator, int denominator)
	{
		return new Rational(numerator, denominator);
	}
	
	/**
	 * Gets a rational number instance out of a {@link double} precision
	 * floating point number. The fraction is obtained by directly turning the
	 * number into a decimal fraction; for example, 0.05 will become 5/100, and
	 * 0.142857 will become 142857/1000000.
	 * @param x The number
	 * @param uncertainty The uncertainty associated to this number
	 * @return The rational instance
	 */
	public static Rational get(double x, double uncertainty)
	{
		return new Rational(x, uncertainty);
	}

	/**
	 * Gets a rational number instance out of a {@link double} precision
	 * floating point number. The fraction is obtained by directly turning the
	 * number into a decimal fraction; for example, 0.05 will become 5/100, and
	 * 0.142857 will become 142857/1000000.
	 * @param x The number
	 * @return The rational instance
	 */
	public static Rational get(double x)
	{
		return new Rational(x);
	}

	/**
	 * Creates an empty rational number, with nothing specified for its
	 * factors.
	 */
	protected Rational()
	{
		super();
		m_factors = new HashMap<Integer,Integer>();
		m_positive = true;
		m_uncertainty = 0;
	}

	/**
	 * Gets a rational number instance out of integer numerator and
	 * denominator, and specifies its uncertainty.
	 * @param numerator The numerator
	 * @param denominator The denominator
	 * @param uncertainty The uncertainty associated to the number
	 * @return The rational instance
	 */
	protected Rational(int numerator, int denominator, double uncertainty)
	{
		super();
		m_factors = new HashMap<Integer,Integer>();
		addFactor(numerator, 1);
		addFactor(denominator, -1);
		m_positive = (numerator * denominator >= 0);
		m_uncertainty = NumberFormatter.roundUpToSignificantFigures(Math.abs(uncertainty), 1);
	}
	
	/**
	 * Gets a rational number instance out of integer numerator and
	 * denominator. 
	 * @param numerator The numerator
	 * @param denominator The denominator
	 * @return The rational instance
	 */
	protected Rational(int numerator, int denominator)
	{
		this(numerator, denominator, 0);
	}
	
	/**
	 * Gets a rational number instance out of a {@link double} precision
	 * floating point number, and specifies its uncertainty. The fraction is 
	 * obtained by directly turning the number into a decimal fraction; for
	 * example, 0.05 will become 5/100, and 0.142857 will become
	 * 142857/1000000.
	 * @param x The number
	 * @param uncertainty The uncertainty associated to the number
	 * @return The rational instance
	 */
	protected Rational(double x, double uncertainty)
	{
		super();
		m_factors = new HashMap<Integer,Integer>();
		double v = x;
		int power_10 = 0;
		while (v != (int) v)
		{
			v *= 10;
			power_10++;
		}
		addFactor((int) v, 1);
		addPrimeFactor(2, -power_10);
		addPrimeFactor(5, -power_10);
		m_positive = (x >= 0);
		m_uncertainty = NumberFormatter.roundUpToSignificantFigures(Math.abs(uncertainty), 1);
	}

	/**
	 * Gets a rational number instance out of a {@link double} precision
	 * floating point number. The fraction is obtained by directly turning the
	 * number into a decimal fraction; for example, 0.05 will become 5/100, and
	 * 0.142857 will become 142857/1000000.
	 * @param x The number
	 * @return The rational instance
	 */
	protected Rational(double x)
	{
		this();
		double v = x;
		int power_10 = 0;
		while (v != (int) v)
		{
			v *= 10;
			power_10++;
		}
		addFactor((int) v, 1);
		addPrimeFactor(2, -power_10);
		addPrimeFactor(5, -power_10);
		m_positive = (x >= 0);
	}
	
	/**
	 * Creates a rational number out of a factored representation of another
	 * rational.
	 * @param factors The factors
	 * @param positive Whether this number is positive or not
	 * @param uncertainty The uncertainty associated to the number
	 */
	protected Rational(/*@ non_null @*/ Map<Integer,Integer> factors, boolean positive, double uncertainty)
	{
		super();
		m_factors = factors;
		m_positive = positive;
		m_uncertainty = Math.abs(uncertainty);
	}
	
	public Rational addFactor(int number, int power)
	{
		Map<Integer,Integer> factors = new HashMap<Integer,Integer>();
		primeFactors(number, 1, factors);
		for (Map.Entry<Integer,Integer> e : factors.entrySet())
		{
			int new_pow = e.getValue() * power;
			addPrimeFactor(e.getKey(), new_pow);
		}
		return this;
	}
	
	protected Rational addPrimeFactor(int number, int power)
	{
		if (!m_factors.containsKey(number))
		{
			if (power != 0)
			{
				m_factors.put(number, power);
			}
		}
		else
		{
			int pow = m_factors.get(number);
			int new_pow = pow + power;
			if (new_pow != 0)
			{
				m_factors.put(number, new_pow);
			}
			else
			{
				m_factors.remove(number);
			}
		}
		return this;
	}

	/**
	 * Returns the value of the fraction as a double-precision floating
	 * point number.
	 * @return The value of the fraction
	 */
	public double doubleValue()
	{
		double x = 1;
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			x *= Math.pow(e.getKey(), e.getValue().doubleValue());
		}
		return x;
	}
	
	/**
	 * Gets the factoring that corresponds to the numerator of the rational
	 * number.
	 * @return The factoring of the numerator
	 */
	protected Map<Integer,Integer> getNumerator()
	{
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			double power = e.getValue().doubleValue();
			if (power > 0)
			{
				map.put(e.getKey(), e.getValue());
			}
		}
		return map;
	}

	/**
	 * Gets the value of the product of all factors with a positive power.
	 * This corresponds to the numerator when the rational is represented
	 * as a fraction.
	 * @return The value of the numerator
	 */
	public int getNumeratorValue()
	{
		boolean empty = true;
		int x = 1;
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			double power = e.getValue().doubleValue();
			if (power > 0)
			{
				x *= Math.pow(e.getKey().doubleValue(), power);
				empty = false;
			}
		}
		if (empty)
		{
			return 0;
		}
		return x;
	}
	
	/**
	 * Gets the factoring that corresponds to the denominator of the rational
	 * number.
	 * @return The factoring of the denominator
	 */
	protected Map<Integer,Integer> getDenominator()
	{
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			double power = e.getValue().doubleValue();
			if (power < 0)
			{
				map.put(e.getKey(), e.getValue());
			}
		}
		return map;
	}

	/**
	 * Gets the value of the product of all factors with a negative power.
	 * This corresponds to the denominator when the rational is represented
	 * as a fraction. For example, with the rational
	 * 2<sup>3</sup>&times;3<sup>-1</sup>&times;5<sup>-2</sup>, the denominator
	 * is 3<sup>1</sup>&times;5<sup>2</sup> = 75.
	 * @return The value of the denominator
	 */
	public int getDenominatorValue()
	{
		int x = 1;
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			double power = e.getValue().doubleValue();
			if (power < 0)
			{
				x *= Math.pow(e.getKey().doubleValue(), -power);
			}
		}
		return x;
	}

	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		if (m_uncertainty > 0)
		{
			out.append("(");
		}
		int factor = m_positive ? 1 : -1;
		out.append(NumberFormatter.toSuperscript(factor * getNumeratorValue()));
		out.append("/");
		out.append(NumberFormatter.toSubscript(getDenominatorValue()));
		if (m_uncertainty > 0)
		{
			out.append(NumberFormatter.U_PM).append(NumberFormatter.display(m_uncertainty));
			out.append(")");
		}
		return out.toString();
	}
	
	@Override
	public Real pow(Real x)
	{
		double d_x = x.doubleValue();
		int pow = (int) d_x;
		if (d_x == pow)
		{
			// Integer power
			Rational new_r = new Rational();
			for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
			{
				new_r.addPrimeFactor(e.getKey(), e.getValue() * pow);
				if (pow % 2 == 1 && !m_positive)
				{
					new_r.m_positive = false;
				}
				else
				{
					new_r.m_positive = true;
				}
			}
			return new_r;
		}
		return new FloatingPoint(Math.pow(doubleValue(), d_x));
	}
	
	protected static Rational asRational(Real x)
	{
		Rational r_x = null;
		if (x instanceof Whole)
		{
			r_x = Rational.get(x.intValue(), 1);
		}
		else if (x instanceof Rational)
		{
			r_x = (Rational) x;
		}
		if (r_x == null)
		{
			throw new UnsupportedOperationException("Cannot turn this number into a rational");
		}
		return r_x;
	}
	
	@Override
	public Rational opposite()
	{
		return new Rational(m_factors, !m_positive, m_uncertainty);
	}

	@Override
	public Real add(Real x) 
	{
		if (x instanceof FloatingPoint)
		{
			return x.add(this);
		}
		Rational r_x = asRational(x);
		int a = getNumeratorValue();
		int b = getDenominatorValue();
		int c = r_x.getNumeratorValue();
		int d = r_x.getDenominatorValue();
		return Rational.get(a * d + b * c, b * d, getUncertainty() + x.getUncertainty());
	}

	@Override
	public Real subtract(Real x) 
	{
		if (x instanceof FloatingPoint)
		{
			return x.add(this);
		}
		Rational r_x = asRational(x);
		int a = getNumeratorValue();
		int b = getDenominatorValue();
		int c = r_x.getNumeratorValue();
		int d = r_x.getDenominatorValue();
		return Rational.get(a * d - b * c, b * d, getUncertainty() + x.getUncertainty());
	}

	@Override
	public Real divide(Real x) 
	{
		if (x instanceof FloatingPoint)
		{
			return x.multiply(this);
		}
		Rational r_x = asRational(x);
		Rational new_r = new Rational();
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			new_r.addPrimeFactor(e.getKey(), e.getValue());
		}
		for (Map.Entry<Integer,Integer> e : r_x.m_factors.entrySet())
		{
			new_r.addPrimeFactor(e.getKey(), -e.getValue());
		}
		double new_value = new_r.doubleValue();
		double new_uncertainty = new_value * getRelativeUncertainty() * x.getRelativeUncertainty();
		return new Rational(new_r.m_factors, new_value >= 0, new_uncertainty);
	}

	@Override
	public Real multiply(Real x) 
	{
		if (x instanceof FloatingPoint)
		{
			return x.multiply(this);
		}
		Rational r_x = asRational(x);
		Rational new_r = new Rational();
		for (Map.Entry<Integer,Integer> e : m_factors.entrySet())
		{
			new_r.addPrimeFactor(e.getKey(), e.getValue());
		}
		for (Map.Entry<Integer,Integer> e : r_x.m_factors.entrySet())
		{
			new_r.addPrimeFactor(e.getKey(), e.getValue());
		}
		double new_value = new_r.doubleValue();
		double new_uncertainty = new_value * getRelativeUncertainty() * x.getRelativeUncertainty();
		return new Rational(new_r.m_factors, new_value >= 0, new_uncertainty);
	}

	@Override
	public float floatValue() 
	{
		return (float) doubleValue();
	}

	@Override
	public int intValue() 
	{
		return (int) doubleValue();
	}

	/**
	 * Finds the prime factors of an integer number.
	 * @param number The number to factor
	 * @param power An exponent assigned to the number
	 * @param factoring A mapping between primes and their power in the prime
	 * factoring of the number. The method writes into this map.
	 */
	public static void primeFactors(int number, int power, /*@ non_null @*/ Map<Integer,Integer> factoring) 
	{
		int n = number;
		for (int i = 2; i <= n/i; i++) 
		{ 
			while (n % i == 0) 
			{
				if (factoring.containsKey(i))
				{
					factoring.put(i, factoring.get(i) + power);
				}
				else
				{
					factoring.put(i, power);
				}
				n /= i;
			} 
		}
		if (n > 1)
		{
			if (factoring.containsKey(n))
			{
				factoring.put(n, factoring.get(n) + power); 					
			}
			else
			{
				factoring.put(n, power);
			}
		}
	}

	@Override
	/*@ pure @*/ public double getUncertainty()
	{
		return m_uncertainty;
	}

	@Override
	/*@ pure @*/ public double getRelativeUncertainty()
	{
		return m_uncertainty / doubleValue();
	}
	
	@Override
	/*@ pure non_null @*/ public Real inverse() 
	{
		return Rational.get(1, 1).divide(this); 
	}
}
