package ca.uqac.lif.units.examples;

import ca.uqac.lif.units.AngularVelocity;
import ca.uqac.lif.units.si.RPM;
import ca.uqac.lif.units.si.RadiansPerSecond;

/**
 * How many radians/second corresponds to 1000 RPM?
 */
public class ExampleConversion1 
{
	public static void main(String[] args) 
	{
		AngularVelocity v = new RadiansPerSecond(new RPM(1000));
		System.out.println(v);
	}

}
