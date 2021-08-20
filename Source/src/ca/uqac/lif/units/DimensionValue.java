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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ca.uqac.lif.numbers.FloatingPoint;
import ca.uqac.lif.numbers.Real;

/**
 * A scalar number accompanied by a dimension.
 * @author Sylvain Hallé
 *
 */
public abstract class DimensionValue implements Comparable<DimensionValue>
{
	/**
	 * The value in "native" units.
	 */
	/*@ non_null @*/ protected final Real m_value;
	
	/**
	 * Creates a new dimension value from a scalar number.
	 * @param d The scalar value in the native units of the class
	 */
	public DimensionValue(double d)
	{
		super();
		m_value = FloatingPoint.get(d);
	}
	
	/**
	 * Creates a new dimension value from a real scalar number.
	 * @param x The real value in the native units of the class
	 */
	public DimensionValue(/*@ non_null @*/ Real x)
	{
		super();
		m_value = x;
	}
	
	/**
	 * Creates a new dimension value from another dimension value. If that
	 * other value is not expressed in the same units, it is converted to the
	 * units of the current class.
	 * 
	 * @param x The other dimension value
	 * @throws IncompatibleDimensions If {@code x} does not have the same
	 * dimension as that of the current class.
	 */
	public DimensionValue(/*@ non_null @*/ DimensionValue x)
	{
		super();
		if (!x.getDimension().equals(getDimension()))
		{
			throw new IncompatibleDimensions("Cannot create a value of " + getDimension() + " out of a value of " + x.getDimension());
		}
		m_value = fromBaseUnit(x.asBaseUnit().get());
	}
	
	/**
	 * Gets the scalar of this dimension value, expressed in its native units.
	 * @return The scalar
	 */
	public final Real get()
	{
		return m_value;
	}
	
	/**
	 * Converts the value into another one expressed in the base units for each
	 * of its dimensions.
	 * @return Another dimension value expressed in base unit
	 */
	/*@ non_null @*/ public abstract DimensionValue asBaseUnit();
	
	/*@ non_null @*/ public static DimensionValue instantiate(DimensionValue x, /*@ non_null @*/ Class<? extends DimensionValue> unit) throws NoSuchUnitException
	{
		try
		{
			Constructor<? extends DimensionValue> constructor = unit.getConstructor(DimensionValue.class);
			DimensionValue v = constructor.newInstance(x);
			return v;
		} 
		catch (NoSuchMethodException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (SecurityException e) 
		{
			throw new NoSuchUnitException(e);
		} 
		catch (InstantiationException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (IllegalAccessException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (IllegalArgumentException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (InvocationTargetException e) 
		{
			throw new NoSuchUnitException(e);
		}
	}
	
	/*@ non_null @*/ public static DimensionValue instantiate(Real x, /*@ non_null @*/ Class<? extends DimensionValue> unit) throws NoSuchUnitException
	{
		try
		{
			Constructor<? extends DimensionValue> constructor = unit.getConstructor(Real.class);
			DimensionValue v = constructor.newInstance(x);
			return v;
		} 
		catch (NoSuchMethodException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (SecurityException e) 
		{
			throw new NoSuchUnitException(e);
		} 
		catch (InstantiationException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (IllegalAccessException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (IllegalArgumentException e) 
		{
			throw new NoSuchUnitException(e);
		}
		catch (InvocationTargetException e) 
		{
			throw new NoSuchUnitException(e);
		}
	}
	
	@Override
	public int compareTo(DimensionValue x)
	{
		return asBaseUnit().get().compareTo(x.asBaseUnit().get());
	}
	
	/**
	 * Converts a scalar from the base units to the dimension value's native units.
	 * @param x The scalar in base units
	 * @return The scalar in native units
	 */
	protected abstract Real fromBaseUnit(Real x);
	
	/**
	 * Gets the dimension of this value.
	 * @return The dimension
	 */
	public abstract Dimension getDimension();
}
