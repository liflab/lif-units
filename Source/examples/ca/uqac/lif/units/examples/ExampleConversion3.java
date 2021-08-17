package ca.uqac.lif.units.examples;

import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.functions.UnitAdd;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Meter;
import ca.uqac.lif.units.si.MetersPerSecond;

/**
 * You cannot add a length and a speed: these dimensions are incompatible.
 */
public class ExampleConversion3 
{
	public static void main(String[] args) 
	{
		Length len = new Meter(Whole.get(1));
		Velocity vel = new MetersPerSecond(Whole.get(6));
		Length distance = new Centimeter(UnitAdd.get(len, vel));
		System.out.println(distance);
	}
}
