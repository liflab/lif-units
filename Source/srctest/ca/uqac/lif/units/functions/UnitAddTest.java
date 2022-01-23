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

import ca.uqac.lif.numbers.FloatingPoint;
import ca.uqac.lif.numbers.RealPart;
import ca.uqac.lif.petitpoucet.AndNode;
import ca.uqac.lif.petitpoucet.ComposedPart;
import ca.uqac.lif.petitpoucet.PartNode;
import ca.uqac.lif.petitpoucet.function.FunctionException;
import ca.uqac.lif.petitpoucet.function.NthInput;
import ca.uqac.lif.petitpoucet.function.NthOutput;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.imperial.Inch;
import ca.uqac.lif.units.si.Centimeter;
import ca.uqac.lif.units.si.Kilogram;
import ca.uqac.lif.units.si.Meter;

public class UnitAddTest 
{
	@Test
	public void testSame1()
	{
		Meter len1 = new Meter(1);
		Meter len2 = new Meter(2);
		UnitAdd f = new UnitAdd(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof Meter);
		assertEquals(3, out.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testSame2()
	{
		Meter len1 = new Meter(1);
		Centimeter len2 = new Centimeter(2);
		UnitAdd f = new UnitAdd(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof Meter);
		assertEquals(1.02, out.get().doubleValue(), 0.0001);
	}
	
	@Test
	public void testSame3()
	{
		Inch len1 = new Inch(2);
		Meter len2 = new Meter(1);
		UnitAdd f = new UnitAdd(2);
		DimensionValue out = (DimensionValue) f.evaluate(len1, len2)[0];
		assertNotNull(out);
		assertTrue(out instanceof Inch);
		assertEquals(41.3700, out.get().doubleValue(), 0.0001);
	}
	
	@Test(expected = FunctionException.class)
	public void testIncompatible1()
	{
		Inch len1 = new Inch(2);
		Kilogram len2 = new Kilogram(1);
		UnitAdd f = new UnitAdd(2);
		f.evaluate(len1, len2);
	}
	
	@Test
	public void testExplain1()
	{
		Centimeter len1 = new Centimeter(FloatingPoint.get(1, 1));
		Centimeter len2 = new Centimeter(FloatingPoint.get(2, 1));
		UnitAdd f = new UnitAdd(2);
		f.evaluate(len1, len2);
		PartNode root = f.getExplanation(ComposedPart.compose(RealPart.uncertainty, NthOutput.FIRST));
		assertEquals(1, root.getOutputLinks(0).size());
		AndNode and = (AndNode) root.getOutputLinks(0).get(0).getNode();
		assertEquals(2, and.getOutputLinks(0).size());
		{
			PartNode child = (PartNode) and.getOutputLinks(0).get(0).getNode();
			assertEquals(ComposedPart.compose(RealPart.uncertainty, NthInput.FIRST), child.getPart());
		}
		{
			PartNode child = (PartNode) and.getOutputLinks(0).get(1).getNode();
			assertEquals(ComposedPart.compose(RealPart.uncertainty, NthInput.SECOND), child.getPart());
		}
	}
}
