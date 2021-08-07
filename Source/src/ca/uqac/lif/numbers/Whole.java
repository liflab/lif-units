package ca.uqac.lif.numbers;

public class Whole implements Real
{
	public static final Whole ZERO = Whole.get(0);
	
	public static final Whole ONE = Whole.get(1);
	
	protected final int m_value;
	
	public static Whole get(int value)
	{
		return new Whole(value);
	}
	
	private Whole(int value)
	{
		super();
		m_value = value;
	}

	@Override
	public Real add(Real x) 
	{
		if (x.doubleValue() == (int) x.doubleValue())
		{
			return Whole.get(m_value + x.intValue());
		}
		return null;
	}

	@Override
	public Real subtract(Real x)
	{
		if (x.doubleValue() == (int) x.doubleValue())
		{
			return Whole.get(m_value - x.intValue());
		}
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
		if (x.doubleValue() == (int) x.doubleValue())
		{
			return Whole.get(m_value * x.intValue());
		}
		return null;
	}

	@Override
	public double doubleValue() 
	{
		return m_value;
	}

	@Override
	public float floatValue()
	{
		return m_value;
	}

	@Override
	public int intValue()
	{
		return m_value;
	}
	
	@Override
	public String toString()
	{
		return Integer.toString(m_value);
	}
}
