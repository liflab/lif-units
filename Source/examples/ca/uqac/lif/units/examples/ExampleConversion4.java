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

import ca.uqac.lif.numbers.Whole;
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
		Time duration = new Second(Whole.get(45));
		Velocity speed = new KilometersPerHour(Whole.get(3));
		Length distance = new Foot(UnitMultiply.get(duration, speed));
		System.out.println(distance);
	}
}