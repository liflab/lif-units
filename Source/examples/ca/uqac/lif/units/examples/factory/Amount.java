package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.Quantity;

public class Amount extends Quantity
{
	public static final Amount QUANTITY = new Amount();

	@Override
	public String toString()
	{
		return "amount";
	}
}