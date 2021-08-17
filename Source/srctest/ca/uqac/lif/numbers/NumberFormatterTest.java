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

import static org.junit.Assert.assertEquals;
import static ca.uqac.lif.numbers.NumberFormatter.getMagnitude;
import static ca.uqac.lif.numbers.NumberFormatter.getPrecision;
import static ca.uqac.lif.numbers.NumberFormatter.printScientific;
import static ca.uqac.lif.numbers.NumberFormatter.roundToPower;
import static ca.uqac.lif.numbers.NumberFormatter.roundToPrecision;
import static ca.uqac.lif.numbers.NumberFormatter.roundToSignificantFigures;
import static ca.uqac.lif.numbers.NumberFormatter.roundUpToSignificantFigures;
import static ca.uqac.lif.numbers.NumberFormatter.toSuperscript;
import static ca.uqac.lif.numbers.NumberFormatter.U_PM;
import static ca.uqac.lif.numbers.NumberFormatter.U_TIMES;

import org.junit.Test;

public class NumberFormatterTest 
{
	@Test
	public void testGetMagnitude1()
	{
		assertEquals(2, getMagnitude(123.456));
	}
	
	@Test
	public void testGetMagnitude2()
	{
		assertEquals(0, getMagnitude(0));
	}
	
	@Test
	public void testGetMagnitude3()
	{
		assertEquals(2, getMagnitude(-123.456));
	}
	
	@Test
	public void testGetMagnitude4()
	{
		assertEquals(-2, getMagnitude(0.04));
	}
	
	@Test
	public void testGetMagnitude5()
	{
		assertEquals(-2, getMagnitude(-0.04));
	}
	
	@Test
	public void testSignificantFigures1()
	{
		assertEquals(1, roundToSignificantFigures(1.3, 1), 0.0001);
	}
	
	@Test
	public void testSignificantFiguresUp1()
	{
		assertEquals(2, roundUpToSignificantFigures(1.3, 1), 0.0001);
	}
	
	@Test
	public void testGetPrecision1()
	{
		assertEquals(0, getPrecision(11));
	}
	
	@Test
	public void testGetPrecision2()
	{
		assertEquals(1, getPrecision(20));
	}
	
	@Test
	public void testGetPrecision3()
	{
		assertEquals(-2, getPrecision(20.54));
	}
	
	@Test
	public void testRoundToPower1()
	{
		assertEquals(120, roundToPower(123.456, 1), 0.0001);
	}
	
	@Test
	public void testRoundToPower2()
	{
		assertEquals(123, roundToPower(123.456, 0), 0.0001);
	}
	
	@Test
	public void testRoundToPower3()
	{
		assertEquals(123.5, roundToPower(123.456, -1), 0.0001);
	}
	
	@Test
	public void testRoundToPower4()
	{
		assertEquals(200, roundToPower(173.456, 2), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision1()
	{
		assertEquals(123.456, roundToPrecision(123.456, 0.003), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision2()
	{
		assertEquals(123.5, roundToPrecision(123.456, 0.3), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision3()
	{
		assertEquals(123, roundToPrecision(123.456, 1), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision4()
	{
		assertEquals(120, roundToPrecision(123.456, 50), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision5()
	{
		assertEquals(100, roundToPrecision(123.456, 200), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision6()
	{
		assertEquals(200, roundToPrecision(173.456, 200), 0.0001);
	}
	
	@Test
	public void testRoundToPrecision7()
	{
		assertEquals(0, roundToPrecision(123.456, 1000), 0.0001);
	}
	
	/* Since printScientific() only works on the double value of a Real, we
	 * only test it with FloatingPoints.
	 */
	
	@Test
	public void testPrintScientific1()
	{
		String expected = "1.24" + U_TIMES + "10" + toSuperscript(2);
		assertEquals(expected, printScientific(FloatingPoint.get(124)));
	}
	
	@Test
	public void testPrintScientific2()
	{
		String expected = "1.42857" + U_TIMES + "10" + toSuperscript(-1);
		assertEquals(expected, printScientific(FloatingPoint.get(0.142857)));
	}
	
	@Test
	public void testPrintScientific3()
	{
		String expected = "(143 " + U_PM + " 1) " + U_TIMES + "10" + toSuperscript(-3);
		assertEquals(expected, printScientific(FloatingPoint.get(0.142857, 0.001)));
	}
	
}
