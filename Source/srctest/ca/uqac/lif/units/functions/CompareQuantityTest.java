/*
  Copyright 2021-2022 Sylvain Hallé
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
package ca.uqac.lif.units.functions;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.units.functions.CompareQuantities.QuantityIsGreaterThan;
import ca.uqac.lif.units.functions.CompareQuantities.QuantityIsLessThan;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Meter;
import ca.uqac.lif.units.si.Second;

/**
 * Unit tests for descendants of {@link CompareQuantity}.
 */
public class CompareQuantityTest 
{
	@Test
	public void testGreater1()
	{
		QuantityIsGreaterThan f = new QuantityIsGreaterThan();
		assertTrue((boolean) f.evaluate(new Centimeter(1), new Centimeter(0))[0]);
	}
	
	@Test
	public void testGreater2()
	{
		QuantityIsGreaterThan f = new QuantityIsGreaterThan();
		assertFalse((boolean) f.evaluate(new Centimeter(0), new Centimeter(1))[0]);
	}
	
	@Test
	public void testGreater3()
	{
		QuantityIsGreaterThan f = new QuantityIsGreaterThan();
		assertFalse((boolean) f.evaluate(new Centimeter(1), new Meter(0.5))[0]);
	}
	
	@Test (expected = InvalidArgumentTypeException.class)
	public void testGreater4()
	{
		QuantityIsGreaterThan f = new QuantityIsGreaterThan();
		assertFalse((boolean) f.evaluate(new Centimeter(0), new Second(1))[0]);
	}
	
	@Test
	public void testLess1()
	{
		QuantityIsLessThan f = new QuantityIsLessThan();
		assertFalse((boolean) f.evaluate(new Centimeter(1), new Centimeter(0))[0]);
	}
	
	@Test
	public void testLess2()
	{
		QuantityIsLessThan f = new QuantityIsLessThan();
		assertTrue((boolean) f.evaluate(new Centimeter(0), new Centimeter(1))[0]);
	}
	
	@Test
	public void testLess3()
	{
		QuantityIsLessThan f = new QuantityIsLessThan();
		assertTrue((boolean) f.evaluate(new Centimeter(1), new Meter(0.5))[0]);
	}
	
	@Test (expected = InvalidArgumentTypeException.class)
	public void testLess4()
	{
		QuantityIsLessThan f = new QuantityIsLessThan();
		assertTrue((boolean) f.evaluate(new Centimeter(0), new Second(1))[0]);
	}
	
	@Test
	public void testDuplicate1()
	{
		QuantityIsGreaterThan f = new QuantityIsGreaterThan();
		f.duplicate(false);
	}
}
