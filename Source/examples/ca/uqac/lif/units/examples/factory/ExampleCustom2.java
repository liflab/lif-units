package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.functions.UnitMultiply;
import ca.uqac.lif.units.si.Minute;

/**
 * If the factory produces 2500 boxes per hour, and each box is worth $8.75,
 * what is the worth of 75 minutes of production in euros?
 */
public class ExampleCustom2 
{
	public static void main(String[] args)
	{
		BoxesPerHour rate = new BoxesPerHour(2500);
		USDollarPerBox upb = new USDollarPerBox(8.75);
		Minute min = new Minute(75);
		Euro lb = new Euro(UnitMultiply.get(rate, upb, min));
		System.out.println(lb);
	}	
}
