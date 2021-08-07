package ca.uqac.lif.units;

import java.util.TreeMap;
import java.util.Comparator;
import java.util.Map;

public class Dimension
{
	/*@ non_null @*/ protected Map<String,Double> m_components;
	
	public Dimension()
	{
		super();
		m_components = new TreeMap<String,Double>();
	}

	/*@ non_null @*/ public Dimension add(/*@ non_null @*/ String q, double p)
	{
		if (!m_components.containsKey(q))
		{
			if (p != 0)
			{
				m_components.put(q, p);
			}
		}
		else
		{
			double new_p = m_components.get(q) + p;
			if (new_p == 0)
			{
				m_components.remove(q);
			}
			else
			{
				m_components.put(q, new_p);
			}
		}
		return this;
	}
	
	/*@ non_null @*/ public Dimension subtract(/*@ non_null @*/ String q, double p)
	{
		return add(q, -p);
	}

	/*@ non_null @*/ public Dimension add(/*@ non_null @*/ Dimension d)
	{
		for (Map.Entry<String,Double> e : d.m_components.entrySet())
		{
			add(e.getKey(), e.getValue());
		}
		return this;
	}
	
	/*@ non_null @*/ public Dimension subtract(/*@ non_null @*/ Dimension d)
	{
		for (Map.Entry<String,Double> e : d.m_components.entrySet())
		{
			subtract(e.getKey(), e.getValue());
		}
		return this;
	}

	@Override
	/*@ pure @*/ public int hashCode()
	{
		return m_components.size();
	}

	@Override
	/*@ pure @*/ public boolean equals(Object o)
	{
		if (o == null || !(o instanceof Dimension))
		{
			return false;
		}
		Dimension d = (Dimension) o;
		if (d.m_components.size() != m_components.size())
		{
			return false;
		}
		for (Map.Entry<String,Double> e : m_components.entrySet())
		{
			if (!d.m_components.containsKey(e.getKey()))
			{
				return false;
			}
			double exp = d.m_components.get(e.getKey());
			if (e.getValue() != exp)
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
		boolean first = true;
		for (Map.Entry<String,Double> e : m_components.entrySet())
		{
			if (first)
			{
				first = false;
			}
			else
			{
				out.append("⋅");
			}
			out.append(e.getKey()).append(exponent(e.getValue()));
		}
		return out.toString();
	}

	/**
	 * Pretty-prints the exponent of a quantity in a dimension.
	 * @param x The exponent
	 * @return A string representing the exponent
	 */
	protected static String exponent(double x)
	{
		if (x == 0.5)
		{
			return "\u00bd"; 
		}
		if (x == 1)
		{
			return ""; 
		}
		if (x == 2)
		{
			return "\u00b2"; 
		}
		if (x == 3)
		{
			return "\u00b3"; 
		}
		if (x == 4)
		{
			return "\u2074"; 
		}
		if (x == 5)
		{
			return "\u2075"; 
		}
		if (x == 6)
		{
			return "\u2076"; 
		}
		if (x == 7)
		{
			return "\u2077"; 
		}
		if (x == 8)
		{
			return "\u2078"; 
		}
		if (x == 9)
		{
			return "\u2079"; 
		}
		if (x == -0.5)
		{
			return "\u207b\u00bd"; 
		}
		if (x == -1)
		{
			return "\u207b\u00b1"; 
		}
		if (x == 2)
		{
			return "\u207b\u00b2"; 
		}
		if (x == 3)
		{
			return "\u207b\u00b3"; 
		}
		if (x == 4)
		{
			return "\u207b\u2074"; 
		}
		if (x == 5)
		{
			return "\u207b\u2075"; 
		}
		if (x == 6)
		{
			return "\u207b\u2076"; 
		}
		if (x == 7)
		{
			return "\u207b\u2077"; 
		}
		if (x == 8)
		{
			return "\u207b\u2078"; 
		}
		if (x == 9)
		{
			return "\u207b\u2079"; 
		}
		return Double.toString(x);
	}
	
	/**
	 * A comparator used to sort quantities by their name. 
	 */
	protected static class QuantityComparator implements Comparator<Quantity>
	{
		@Override
		public int compare(/*@ non_null @*/ Quantity arg0, /*@ non_null @*/ Quantity arg1)
		{
			return arg0.toString().compareTo(arg1.toString());
		}
	}
}
