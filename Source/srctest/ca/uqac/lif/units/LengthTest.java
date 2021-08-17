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

import ca.uqac.lif.numbers.Rational;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.units.IncompatibleDimensions;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Kilogram;
import ca.uqac.lif.units.si.Meter;
import ca.uqac.lif.units.si.Millimeter;

public class LengthTest 
{
	@Test
	public void testConversion1()
	{
		Meter m = new Meter(0.254);
		Inch i = new Inch(m);
		assertEquals(10, i.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testConversion2()
	{
		Inch i = new Inch(1);
		Centimeter c = new Centimeter(i);
		assertEquals(2.54, c.get().doubleValue(), 0.0001);
	}
	
	@Test(expected=IncompatibleDimensions.class)
	public void testConversion3()
	{
		Kilogram k = new Kilogram(1);
		new Centimeter(k);
	}
	
	@Test
	public void testConversion4()
	{
		Meter m = new Meter(0.254);
		Centimeter c = new Centimeter(m);
		assertEquals(25.4, c.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testConversion5()
	{
		Inch i = new Inch(Whole.get(1));
		DimensionValue v = i.asBaseUnit();
		Real r = v.get();
		assertTrue(r instanceof Rational);
		assertEquals(((Rational) r).getNumeratorValue(), 127);
		assertEquals(((Rational) r).getDenominatorValue(), 5000);
	}
	
	@Test
	public void testConversion6()
	{
		Millimeter i = new Millimeter(Rational.get(12.7));
		DimensionValue v = i.asBaseUnit();
		Real r = v.get();
		assertTrue(r instanceof Rational);
		assertEquals(((Rational) r).getNumeratorValue(), 127);
		assertEquals(((Rational) r).getDenominatorValue(), 10000);
	}
}
