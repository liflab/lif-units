package ca.uqac.lif.numbers;

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
		assertEquals("1/2", x.toString());
	}
	
	@Test
	public void testConstructor2()
	{
		Real x = Rational.get(0.5);
		assertEquals(0, x.intValue());
		assertEquals(0.5, x.floatValue(), 0.00001);
		assertEquals(0.5, x.doubleValue(), 0.00001);
		assertEquals("5/10", x.toString());
	}
	
	@Test
	public void testPrimeFactors1()
	{
		Map<Number,Real> factoring = new HashMap<Number,Real>();
		Rational.primeFactors(2, 1, factoring);
		assertEquals(1, factoring.size());
		assertEquals(1, factoring.get(2).intValue());
	}
	
	@Test
	public void testPrimeFactors2()
	{
		Map<Number,Real> factoring = new HashMap<Number,Real>();
		Rational.primeFactors(2, 2, factoring);
		assertEquals(1, factoring.size());
		assertEquals(2, factoring.get(2).intValue());
	}
	
	@Test
	public void testPrimeFactors3()
	{
		Map<Number,Real> factoring = new HashMap<Number,Real>();
		Rational.primeFactors(4, 1, factoring);
		assertEquals(1, factoring.size());
		assertEquals(2, factoring.get(2).intValue());
	}
	
	@Test
	public void testPrimeFactors4()
	{
		Map<Number,Real> factoring = new HashMap<Number,Real>();
		Rational.primeFactors(12, 1, factoring);
		assertEquals(2, factoring.size());
		assertEquals(2, factoring.get(2).intValue());
		assertEquals(1, factoring.get(3).intValue());
	}
	
	@Test
	public void testPrimeFactors5()
	{
		Map<Number,Real> factoring = new HashMap<Number,Real>();
		Rational.primeFactors(12, 2, factoring);
		assertEquals(2, factoring.size());
		assertEquals(4, factoring.get(2).intValue());
		assertEquals(2, factoring.get(3).intValue());
	}
	
	@Test
	public void testPrimeFactors6()
	{
		Map<Number,Real> factoring = new HashMap<Number,Real>();
		Rational.primeFactors(1728, 1, factoring);
		assertEquals(2, factoring.size());
		assertEquals(6, factoring.get(2).intValue());
		assertEquals(3, factoring.get(3).intValue());
	}
	
	@Test
	public void testSimplify1()
	{
		Real x = Rational.get(1, 2).simplify();
		assertEquals("1/2", x.toString());
	}
	
	@Test
	public void testSimplify2()
	{
		Real x = Rational.get(3, 6).simplify();
		assertEquals("1/2", x.toString());
	}
	
	@Test
	public void testSimplify3()
	{
		Real x = Rational.get(0.21875).simplify();
		assertEquals("7/32", x.toString());
	}
}
