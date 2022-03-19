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
package ca.uqac.lif.units.other;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for {@link DayHourMinuteSecond}.
 */
public class DayHourMinuteSecondTest 
{
	@Test
	public void test1()
	{
		DayHourMinuteSecond x = new DayHourMinuteSecond(0);
		assertEquals("00:00:00:00.000", x.toString());
	}
	
	@Test
	public void test2()
	{
		DayHourMinuteSecond x = new DayHourMinuteSecond(0.5);
		assertEquals("00:00:00:00.500", x.toString());
	}
	
	@Test
	public void test3()
	{
		DayHourMinuteSecond x = new DayHourMinuteSecond(48.25);
		assertEquals("00:00:00:48.250", x.toString());
	}
	
	@Test
	public void test4()
	{
		DayHourMinuteSecond x = new DayHourMinuteSecond(5077);
		assertEquals("00:01:24:37.000", x.toString());
	}
}
