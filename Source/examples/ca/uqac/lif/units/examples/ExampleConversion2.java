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
		Length len1 = new Meter(Whole.get(1));
		Length len2 = new Inch(Whole.get(6));
		Length distance = new Centimeter(UnitAdd.get(len1, len2));
		System.out.println(distance);
	}

}
