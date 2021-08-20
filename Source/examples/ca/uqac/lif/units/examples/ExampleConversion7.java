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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.uqac.lif.units.Velocity;
import ca.uqac.lif.units.imperial.FeetPerSecond;
import ca.uqac.lif.units.imperial.MilesPerHour;
import ca.uqac.lif.units.other.Knot;
import ca.uqac.lif.units.si.KilometersPerHour;
import ca.uqac.lif.units.si.MetersPerSecond;

/**
 * Order these velocities from slowest to fastest:
 * <ul>
 * <li>50 MPH</li>
 * <li>20 m/s</li>
 * <li>30 kts</li>
 * <li>80 km/h</li>
 * <li>60 ft/s</li>
 * </ul>
 */
public class ExampleConversion7 
{
	public static void main(String[] args) 
	{
		List<Velocity> velocities = new ArrayList<Velocity>();
		velocities.add(new MilesPerHour(50));
		velocities.add(new MetersPerSecond(20));
		velocities.add(new Knot(30));
		velocities.add(new KilometersPerHour(80));
		velocities.add(new FeetPerSecond(60));
		Collections.sort(velocities);
		System.out.println(velocities);
	}
}
