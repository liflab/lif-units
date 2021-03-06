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
package ca.uqac.lif.units;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.units.functions.UnitDivide;
import ca.uqac.lif.units.si.Meter;

public class UnitDivideTest 
{
	@Test
	public void testSameUnit1()
	{
		DimensionValue v = UnitDivide.get(new Meter(4), new Meter(2));
		DimensionValue m = v.asBaseUnit();
		assertEquals(2, m.get().doubleValue(), 0.0001);
	}
}
