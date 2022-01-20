package ca.uqac.lif.units;

import ca.uqac.lif.petitpoucet.Part;

/**
 * Common ancestor to all {@link Part}s of a {@link DimensionValue} object.
 * @author sylvain
 *
 */
public abstract class DimensionValuePart implements Part
{
	public static final UnitName unitName = new UnitName();
	
	public static final RealValue realValue = new RealValue();
	
	@Override
	public boolean appliesTo(Object o)
	{
		return o instanceof DimensionValue;
	}
	
	@Override
	public DimensionValuePart head()
	{
		return this;
	}
	
	@Override
	public Part tail()
	{
		return null;
	}
	
	/**
	 * Part designating the name of the unit of a dimension value.
	 */
	public static class UnitName extends DimensionValuePart
	{
		protected UnitName()
		{
			super();
		}
		
		@Override
		public String toString()
		{
			return "Unit";
		}
	}
	
	/**
	 * Part designating the scalar value of a dimension value.
	 */
	public static class RealValue extends DimensionValuePart
	{
		protected RealValue()
		{
			super();
		}
		
		@Override
		public String toString()
		{
			return "Real value";
		}
	}
}
