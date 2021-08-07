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

public class Rational implements Real
{
	/*@ non_null @*/ protected final Map<Number,Real> m_numerator;

	/*@ non_null @*/ protected final Map<Number,Real> m_denominator;

	public static Rational get(int numerator, int denominator)
	{
		return new Rational(numerator, denominator);
	}

	public static Rational get(double x)
	{
		return new Rational(x);
	}

	protected Rational()
	{
		super();
		m_numerator = new HashMap<Number,Real>();
		m_denominator = new HashMap<Number,Real>();
	}

	protected Rational(int numerator, int denominator)
	{
		this();
		m_numerator.put(numerator, Whole.get(1));
		m_denominator.put(denominator, Whole.get(1));
	}

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
		m_numerator.put(v, Whole.get(1));
		m_denominator.put(10, Whole.get(power_10));
	}
	
	protected Rational(/*@ non_null @*/ Map<Number,Real> numerator, /*@ non_null @*/ Map<Number,Real> denominator)
	{
		super();
		m_numerator = numerator;
		m_denominator = denominator;
	}

	/**
	 * Returns the value of the fraction as a double-precision floating
	 * point number.
	 * @return The value of the fraction
	 */
	public double doubleValue()
	{
		return getNumerator() / getDenominator();
	}

	protected double getNumerator()
	{
		double x = 1;
		for (Map.Entry<Number,Real> e : m_numerator.entrySet())
		{
			x *= Math.pow(e.getKey().doubleValue(), e.getValue().doubleValue());
		}
		return x;
	}

	protected double getDenominator()
	{
		double x = 1;
		for (Map.Entry<Number,Real> e : m_denominator.entrySet())
		{
			x *= Math.pow(e.getKey().doubleValue(), e.getValue().doubleValue());
		}
		return x;
	}

	/**
	 * Simplifies a fraction.
	 * @return A new fraction corresponding to the simplification of the
	 * current fraction
	 */
	public Rational simplify()
	{
		if (!isSimplifiable())
		{
			// Cannot simplify
			return this;
		}
		Map<Number,Real> numerator = new HashMap<Number,Real>();
		for (Map.Entry<Number,Real> e : m_numerator.entrySet())
		{
			primeFactors(e.getKey().intValue(), e.getValue().intValue(), numerator);
		}
		Map<Number,Real> denominator = new HashMap<Number,Real>();
		for (Map.Entry<Number,Real> e : m_denominator.entrySet())
		{
			primeFactors(e.getKey().intValue(), e.getValue().intValue(), denominator);
		}
		Map<Number,Real> new_num = new HashMap<Number,Real>();
		Map<Number,Real> new_den= new HashMap<Number,Real>();
		for (Map.Entry<Number,Real> e : numerator.entrySet())
		{
			if (denominator.containsKey(e.getKey()))
			{
				Real exp_num = e.getValue();
				Real exp_den = denominator.get(e.getKey());
				Real exp_new = exp_num.subtract(exp_den);
				if (exp_new.doubleValue() > 0)
				{
					new_num.put(e.getKey(), exp_num);
				}
				else if (exp_new.doubleValue() < 0)
				{
					new_den.put(e.getKey(), exp_num);
				}
			}
			else
			{
				new_num.put(e.getKey(), e.getValue());
			}
		}
		for (Map.Entry<Number,Real> e : denominator.entrySet())
		{
			if (!numerator.containsKey(e.getKey()))
			{
				new_den.put(e.getKey(), e.getValue());
			}
		}
		return new Rational(new_num, new_den);
	}
	
	/**
	 * Determines if this fraction is simplifiable. For a fraction to be
	 * simplifiable, its numerator and denominator must only contain integers
	 * raised to an integer power.
	 * @return {@code true} if the fraction is simplifiable, {@code false}
	 * otherwise
	 */
	public boolean isSimplifiable()
	{
		for (Map.Entry<Number,Real> e : m_numerator.entrySet())
		{
			if (e.getKey().intValue() != e.getKey().doubleValue() || e.getValue().doubleValue() != (int) e.getValue().doubleValue())
			{
				return false;
			}
		}
		for (Map.Entry<Number,Real> e : m_denominator.entrySet())
		{
			if (e.getKey().intValue() != e.getKey().doubleValue() || e.getValue().doubleValue() != (int) e.getValue().doubleValue())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		out.append(NumberFormatter.display(getNumerator()));
		out.append("/");
		out.append(NumberFormatter.display(getDenominator()));
		return out.toString();
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
			throw new UnsupportedOperationException("Cannot add to this number");
		}
		return r_x;
	}

	@Override
	public Real add(Real x) 
	{
		Rational r_x = asRational(x);
		
	}

	@Override
	public Real subtract(Real x) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real divide(Real x) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real multiply(Real x) 
	{
		// TODO Auto-generated method stub
		return null;
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
	public static void primeFactors(int number, int power, /*@ non_null @*/ Map<Number,Real> factoring) 
	{
		int n = number;
		for (int i = 2; i <= n/i; i++) 
		{ 
			while (n % i == 0) 
			{
				if (factoring.containsKey(i))
				{
					factoring.put(i, factoring.get(i).add(Whole.get(power))); 					
				}
				else
				{
					factoring.put(i, Whole.get(power));
				}
				n /= i;
			} 
		}
		if (n > 1)
		{
			if (factoring.containsKey(n))
			{
				factoring.put(n, factoring.get(n).add(Whole.get(power))); 					
			}
			else
			{
				factoring.put(n, Whole.get(power));
			}
		}
	}
}
