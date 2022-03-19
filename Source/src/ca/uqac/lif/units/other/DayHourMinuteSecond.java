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

import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.si.Second;

/**
 * A time duration expressed in the form <tt>dd:hh:mm:ss.xxx</tt>. In terms of
 * units, it is equivalent to a number of seconds.
 * @author Sylvain Hallé
 */
public class DayHourMinuteSecond extends Second
{
	/**
	 * Number of seconds in a minute.
	 */
	protected static final transient int s_minute = 60;
	
	/**
	 * Number of seconds in an hour.
	 */
	protected static final transient int s_hour = 3600;
	
	/**
	 * Number of seconds in an day.
	 */
	protected static final transient int s_day = 86400;
	
	public DayHourMinuteSecond(double d)
	{
		super(d);
	}
	
	public DayHourMinuteSecond(DimensionValue x) 
	{
		super(x);
	}
	
	public DayHourMinuteSecond(Real x) 
	{
		super(x);
	}
	
	protected DayHourMinuteSecond()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		float x = m_value.floatValue();
		int days = Math.floorDiv((int) x, s_day);
		x -= days * s_day;
		int hours = Math.floorDiv((int) x, s_hour);
		x -= hours * s_hour;
		int mins = Math.floorDiv((int) x, s_minute);
		x -= mins * s_minute;
		int secs = (int) Math.floor(x);
		x -= secs;
		int millis = (int) (x * 1000);
		return String.format("%02d:%02d:%02d:%02d.%03d", days, hours, mins, secs, millis);
	}
}
