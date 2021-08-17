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

/**
 * An abstract numerical quantity allowing elementary arithmetical operations
 * to be applied to it.
 * 
 * @author Sylvain Hallé
 */
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
	 * Returns a real number of opposite sign to this number.
	 * @return The real with opposite sign
	 */
	public Real opposite();
	
	/**
	 * Returns a real number that is the inverse of this number.
	 * @return The inverse real
	 */
	public Real inverse();
	
	/**
	 * Raises a real number to a given power.
	 * @param x The power
	 * @return A new real number that represents the exponentiation
	 */
	public Real pow(Real x);
	
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
	
	/**
	 * Gets the absolute uncertainty associated to this real number.
	 * @return The uncertainty
	 */
	public double getUncertainty();
	
	/**
	 * Gets the relative uncertainty associated to this real number.
	 * @return The relative uncertainty. The the value itself is 0,
	 * the uncertainty is given as 0.
	 */
	public double getRelativeUncertainty();
}
