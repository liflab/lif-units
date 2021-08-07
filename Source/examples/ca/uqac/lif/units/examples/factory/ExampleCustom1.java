package ca.uqac.lif.units.examples.factory;

import ca.uqac.lif.units.functions.UnitDivide;

/**
 * How many lightbulbs can you buy with 20 euros, if each box sells at US $8.75?
 */
public class ExampleCustom1 
{
	public static void main(String[] args)
	{
		Euro amount = new Euro(20);
		USDollarPerBox upb = new USDollarPerBox(8.75);
		Lightbulb lb = new Lightbulb(UnitDivide.get(amount, upb));
		System.out.println(lb);
	}	
}
