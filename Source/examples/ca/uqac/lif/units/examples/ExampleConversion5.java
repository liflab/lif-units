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
		Length len2 = new Millimeter(Rational.get(10, 3));
		Length distance = new Inch(UnitAdd.get(len1, len2));
		System.out.println(distance);
	}
}
