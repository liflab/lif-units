/*
  Copyright 2021 Sylvain Hallé
  Laboratoire d'informatique formelle
  Université du Québec à Chicoutimi, Canada

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package ca.uqac.lif.units.examples;

import ca.uqac.lif.numbers.FloatingPoint;
import ca.uqac.lif.numbers.NumberFormatter;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.Unit;
import ca.uqac.lif.units.si.KilometersPerHour;
import ca.uqac.lif.units.si.MetersPerSecond;
import ca.uqac.lif.units.si.Minute;

/**
 * What is ((50 ± 2) km/h + (10.5 ± 0.5 m/s)) ÷ 1 min? This is the example
 * from the introduction of the project's Readme file.
 */
public class ExampleConversion6 
{
	public static void main(String[] args) 
	{
		DimensionValue v = Unit.divide(
				Unit.add(
						new KilometersPerHour(FloatingPoint.get(50, 2)),
						new MetersPerSecond(FloatingPoint.get(10.5, 0.5))),
				new Minute(1));
		System.out.println(NumberFormatter.printScientific(v.get()));
	}
}
