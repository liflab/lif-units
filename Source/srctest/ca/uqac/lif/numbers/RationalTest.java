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

import static ca.uqac.lif.numbers.NumberFormatter.printFraction;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RationalTest 
{
	@Test
	public void testConstructor1()
	{
		Real x = Rational.get(1, 2);
		assertEquals(0, x.intValue());
		assertEquals(0.5, x.floatValue(), 0.00001);
		assertEquals(0.5, x.doubleValue(), 0.00001);
		assertEquals(printFraction(1, 2), x.toString());
	}
	
	@Test
	public void testConstructor2()
	{
		Real x = Rational.get(0.5);
		assertEquals(0, x.intValue());
		assertEquals(0.5, x.floatValue(), 0.00001);
		assertEquals(0.5, x.doubleValue(), 0.00001);
		assertEquals(printFraction(1, 2), x.toString());
	}
	
	@Test
	public void testPrimeFactors1()
	{
		Map<Integer,Integer> factoring = new HashMap<Integer,Integer>();
		Rational.primeFactors(2, 1, factoring);
		assertEquals(1, factoring.size());
		assertEquals(1, factoring.get(2).intValue());
	}
	
	@Test
	public void testPrimeFactors2()
	{
		Map<Integer,Integer> factoring = new HashMap<Integer,Integer>();
		Rational.primeFactors(2, 2, factoring);
		assertEquals(1, factoring.size());
		assertEquals(2, factoring.get(2).intValue());
	}
	
	@Test
	public void testPrimeFactors3()
	{
		Map<Integer,Integer> factoring = new HashMap<Integer,Integer>();
		Rational.primeFactors(4, 1, factoring);
		assertEquals(1, factoring.size());
		assertEquals(2, factoring.get(2).intValue());
	}
	
	@Test
	public void testPrimeFactors4()
	{
		Map<Integer,Integer> factoring = new HashMap<Integer,Integer>();
		Rational.primeFactors(12, 1, factoring);
		assertEquals(2, factoring.size());
		assertEquals(2, factoring.get(2).intValue());
		assertEquals(1, factoring.get(3).intValue());
	}
	
	@Test
	public void testPrimeFactors5()
	{
		Map<Integer,Integer> factoring = new HashMap<Integer,Integer>();
		Rational.primeFactors(12, 2, factoring);
		assertEquals(2, factoring.size());
		assertEquals(4, factoring.get(2).intValue());
		assertEquals(2, factoring.get(3).intValue());
	}
	
	@Test
	public void testPrimeFactors6()
	{
		Map<Integer,Integer> factoring = new HashMap<Integer,Integer>();
		Rational.primeFactors(1728, 1, factoring);
		assertEquals(2, factoring.size());
		assertEquals(6, factoring.get(2).intValue());
		assertEquals(3, factoring.get(3).intValue());
	}
	
	@Test
	public void testSimplify1()
	{
		Real x = Rational.get(1, 2);
		assertEquals(printFraction(1, 2), x.toString());
	}
	
	@Test
	public void testSimplify2()
	{
		Real x = Rational.get(3, 6);
		assertEquals(printFraction(1, 2), x.toString());
	}
	
	@Test
	public void testSimplify3()
	{
		Real x = Rational.get(0.21875);
		assertEquals(printFraction(7, 32), x.toString());
	}
	
	@Test
	public void testAdd1()
	{
		Real x = Rational.get(1, 2);
		Real y = Rational.get(1, 4);
		Real z = x.add(y);
		assertTrue(z instanceof Rational);
		assertEquals(0.75, z.doubleValue(), 0.0001);
		assertEquals(printFraction(3, 4), z.toString());
	}
	
	@Test
	public void testAdd2()
	{
		Real x = Rational.get(1, 3);
		Real y = Rational.get(5, 8);
		Real z = x.add(y);
		assertTrue(z instanceof Rational);
		assertEquals(0.958333, z.doubleValue(), 0.0001);
		assertEquals(printFraction(23, 24), z.toString());
	}
	
	@Test
	public void testMultiply1()
	{
		Real x = Rational.get(1, 3);
		Real y = Rational.get(5, 8);
		Real z = x.multiply(y);
		assertTrue(z instanceof Rational);
		assertEquals(0.208333, z.doubleValue(), 0.0001);
		assertEquals(printFraction(5, 24), z.toString());
	}
	
	@Test
	public void testDivide1()
	{
		Real x = Rational.get(1, 3);
		Real y = Rational.get(5, 8);
		Real z = x.divide(y);
		assertTrue(z instanceof Rational);
		assertEquals(0.53333, z.doubleValue(), 0.0001);
		assertEquals(printFraction(8, 15), z.toString());
	}
}
