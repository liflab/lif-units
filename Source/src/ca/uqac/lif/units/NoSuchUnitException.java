package ca.uqac.lif.units;

public class NoSuchUnitException extends Exception
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSuchUnitException(Throwable t)
	{
		super(t);
	}
	
	public NoSuchUnitException(String s)
	{
		super(s);
	}

}
