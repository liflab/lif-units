package ca.uqac.lif.units.examples;

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.units.Length;
import ca.uqac.lif.units.functions.UnitAdd;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Millimeter;

/**
 * What is 1/4" plus 12.7 mm in inches?
 */
public class ExampleConversion5 
{
	public static void main(String[] args) 
	{
		Length len1 = new Inch(Rational.get(0.25));
		Length len2 = new Millimeter(Rational.get(12.7));
		Length distance = new Inch(UnitAdd.get(len1, len2));
		System.out.println(distance);
	}
}
