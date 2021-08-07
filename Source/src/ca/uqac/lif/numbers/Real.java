package ca.uqac.lif.numbers;

public interface Real 
{
	/**
	 * Adds a real number to another real number.
	 * @param x The other real number
	 * @return A new real number that represents the sum
	 */
	public Real add(Real x);
	
	/**
	 * Subtracts a real number to another real number.
	 * @param x The other real number
	 * @return A new real number that represents the difference
	 */
	public Real subtract(Real x);
	
	/**
	 * Divides a real number to another real number.
	 * @param x The other real number
	 * @return A new real number that represents the quotient
	 */
	public Real divide(Real x);
	
	/**
	 * Multiplies a real number to another real number.
	 * @param x The other real number
	 * @return A new real number that represents the product
	 */
	public Real multiply(Real x);
	
	/**
	 * Returns the value of the number as a double-precision floating
	 * point number.
	 * @return The value of the number
	 */
	public double doubleValue();
	
	/**
	 * Returns the value of the number as a single-precision floating
	 * point number.
	 * @return The value of the number
	 */
	public float floatValue();
	
	/**
	 * Returns the value of the number as an integer number.
	 * @return The value of the number
	 */
	public int intValue();
}
