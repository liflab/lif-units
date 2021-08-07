package ca.uqac.lif.units.examples;

import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.Time;
import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.functions.UnitMultiply;
import ca.uqac.lif.units.imperial.Foot;
import ca.uqac.lif.units.si.KilometersPerHour;
import ca.uqac.lif.units.si.Second;

/**
 * How many feet do you travel during 45 seconds at a speed of 3 km/h?
 */
public class ExampleConversion4 
{
	public static void main(String[] args)
	{
		Time duration = new Second(45);
		Velocity speed = new KilometersPerHour(3);
		Length distance = new Foot(UnitMultiply.get(duration, speed));
		System.out.println(distance);
	}
}