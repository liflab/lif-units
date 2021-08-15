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
package ca.uqac.lif.numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class WholeTest 
{
	@Test
	public void testConstructor1()
	{
		Real x = Whole.get(1);
		assertEquals(1, x.intValue());
		assertEquals(1, x.floatValue(), 0.00001);
		assertEquals(1, x.doubleValue(), 0.00001);
		assertEquals("1", x.toString());
	}
	
	@Test
	public void testConstructor2()
	{
		Real x = Whole.get(1, 0.5);
		assertEquals(1, x.intValue());
		assertEquals(1, x.floatValue(), 0.00001);
		assertEquals(1, x.doubleValue(), 0.00001);
		assertEquals(0.5, x.getUncertainty(), 0.00001);
		assertEquals("(1 \u00b1 0.5)", x.toString());
	}
	
	@Test
	public void testConstructor3()
	{
		Real x = Whole.get(1, 0.333);
		assertEquals(1, x.intValue());
		assertEquals(1, x.floatValue(), 0.00001);
		assertEquals(1, x.doubleValue(), 0.00001);
		assertEquals(0.3, x.getUncertainty(), 0.00001);
		assertEquals("(1 \u00b1 0.3)", x.toString());
	}
	
	@Test
	public void testAddInteger1()
	{
		Real x = Whole.get(1);
		Real y = Whole.get(2);
		Real z = x.add(y);
		assertTrue(z instanceof Whole);
		assertEquals(3, z.intValue());
	}
}
