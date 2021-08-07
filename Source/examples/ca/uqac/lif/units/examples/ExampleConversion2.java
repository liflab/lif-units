package ca.uqac.lif.units.examples;

import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.functions.UnitAdd;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Meter;

/**
 * How many centimeters are there in one meter plus 6 inches?
 */
public class ExampleConversion2 
{
	public static void main(String[] args) 
	{
		Length len1 = new Meter(1);
		Length len2 = new Inch(6);
		Length distance = new Centimeter(UnitAdd.get(len1, len2));
		System.out.println(distance);
	}

}
