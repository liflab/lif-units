Manipulate measurements with units and uncertainty
==================================================

In a nutshell: this Java library allows you to perform calculations such as this:

((50 ± 2) km/h + (10.5 ± 0.5 m/s)) ÷ 1 min = ?

The code you need looks like this:

```java
DimensionValue v = Unit.divide(
  Unit.add(
    new KilometersPerHour(FloatingPoint.get(50, 2)),
    new MetersPerSecond(FloatingPoint.get(10.5, 0.5))),
  new Minute(1));
System.out.println(NumberFormatter.printScientific(v.get()));
```

which prints: **(40 ± 5) × 10⁻² m/s²**.

You can also manipulate quantities symbolically: ¹/₄" + ¹⁰/₃ mm = ⁵⁸¹/₁₅₂₄"
--no floating point conversion.

Features
--------

*lif-units* is a Java library that serves three purposes:

1. Allow arithmetic operations on integers, rational numbers and floating point
   numbers to be performed at the highest **symbolic** level possible, avoiding
   unnecessary conversions to floating-point representations and providing
   more intuitive representation of rational values (e.g. ³/₃₅ instead of
   0.085714286...).
2. Provide means to optionally associate **uncertainty** to numerical
   measurements, and appropriately propagate uncertainty to values derived from
   arithmetical operations on uncertain values.
3. Represent numerical measurements in dimensional **units**, and correctly
   convert values of different units when combined together. Operations on
   incompatible units (e.g. adding meters to radians) are forbidden by the
   library and throw an exception.

Below are listed some of the distinctive features of the library.

### Apply arithmetic operations on quantities with different units

Instead of manipulating unit-less numbers, the library provides objects that
represent values in a specific unit. Therefore, instead of writing:

```java
double a = 1, b = 0.75;
double c = a + b; // c = 1.75
```

which producs the incorrect result if `a` is a length in centimeters and `b`
is in inches, you write:

```java
Length a = new Centimeter(1), b = new Inch(0.75);
Length c = Unit.add(a, b); // c = 2.905 cm
```

The library takes care of converting inches to centimeters before performing
the addition.

### Convert between units

A by-product of this behavior is that a value in some unit can be converted
to another unit of the same dimension:

```java
Temperature t1 = new Fahrenheit(32);
Temperature t2 = new Kelvin(t1); // t2 = 273.15 K

Velocity v1 = new KilometersPerHour(60);
Velocity v2 = new MPH(v1); // v2 = 37.28227 MPH
```

### Enforce sound dimensional analysis

Every unitary value in the library (even the ones you create yourself) is
associated with explicit powers of
[base quantities](https://en.wikipedia.org/wiki/International_System_of_Quantities#Base_quantities)
(that include those you create yourself). The library forbids you from adding
or subtracting values having incompatible dimensions:

```java
Velocity x = new KilometersPerHour(60);
Length y = new Meter(3);
Unit.add(x, y); // Throws an IncompatibleDimensions exception
```

The exception is thrown not because the relation between `KilometersPerHour`
and `Meter` is hard-coded as impossible for this operation, but rather because
the *declared dimension* of both objects is not the same (length · time⁻¹ vs.
length). Multiplying and dividing quantities is always possible, and the result
is reported in the appropriate dimension:

```java
Velocity v = new MetersPerSecond(120);
Time t = new Minute(1);
DimensionValue a = Unit.divide(v, t); // a = 2 m/s²
```

### Handle uncertainty on measurements

Quantities can be instantiated with a prespecified absolute uncertainty. Hence,

```java
Length x = new Meter(2.5, 0.1);
```

creates a value of (2.5 ± 0.1) m. What is more, uncertainties are properly
propagated when values are combined using arithmetic operations:

```java
Length y = new Meter(3, 0.2);
Length z = Unit.add(x, y);
```

results in `z` having a value of (5.5 ± 0.1) m. Significant digits and
precision are also automatically adjusted:

```java
Length z = Unit.add(new Meter(1.23, 0.01), new Meter(10.5, 1));
```

results in `z` having a value of (12 ± 2) m, and **not** (11.73 ± 1.01) m. That
is, uncertainty is always rounded up and expressed with 1 significant digit,
and values never have a precision greater than their uncertainty.

When values expressed in various units are combined together, the library takes
care of converting their absolute uncertainty as well. Absolute uncertainty is
also propagated in calculations involving multiplication, division and
exponentiation.

## Perform symbolic manipulations of rational values

To avoid rounding errors possibly incurred by the use of floating point
numbers, the library also allows you to define measurement as rational
numbers, using a class called `Rational`. The following piece of code creates a
duration of exactly ¹/₆₀ of an hour:

```java
Time h = new Hour(Rational.get(1, 60));
```

The duration is indeed displayed as such:

```java
System.out.println(h);
```

produces `¹/₆₀ h`.

When a value is created using rationals, all arithmetic operations on values
are done *symbolically*, and not through floating-point conversions. Thus:

```java
Time h2 = new Hour(Rational.get(1, 7));
Time h3 = Unit.add(h1, h2);
System.out.println(h3);
```

yields `⁶⁷/₅₄₀ h`, as expected. The actual decimal value can be obtained in
various ways:

```java
double x = h3.doubleValue(); // 0.124074074
Real y = FloatingPoint.get(h3.get());
```

Uncertainty on rationals is handled exactly as for decimal numbers. Hence:

```java
Length x = new Meter(Rational.get(1, 3, 0.1));
``` 

will actually end up with a value of (³/₁₀ ± ¹/₁₀) m, and **not**
(¹/₃ ± ¹/₁₀) m, since ¹/₃ has been truncated to the fraction matching the
precision of the associated uncertainty.

### Pretty-print measurements in various formats

The library heavily relies on [Unicode](https://en.wikipedia.org/wiki/Unicode)
for the display of mathematical symbols in an eye-pleasing way. As you have
seen above, fractions are printed using UTF-8 characters for subscript and
superscript numbers (hence `¹/₁₀` and not `1/10`), and the same applies for the few
other special characters (such as `±` instead of the ugly `+/-`).

It is also possible to print any numerical value using
[scientific notation](https://en.wikipedia.org/wiki/Scientific_notation):

```java
Real x = FloatingPoint.get(314.16, 0.1);
System.out.println(NumberFormatter.printScientific(x));
```

will produce (3.142 ± 1) × 10².

Compiling and Installing the Library
------------------------------------

First make sure you have the following installed:

- The Java Development Kit (JDK) to compile. Bullwinkle was developed and
  tested on version 11 of the JDK, but it is probably safe to use any
  later version.
- [Ant](http://ant.apache.org) to automate the compilation and build process

Download the sources for the library from
[GitHub](http://github.com/liflab/lif-units) or clone the repository
using Git:

    git clone git@github.com:liflab/lif-units.git

### Compiling

Compile the sources by simply typing:

    ant

This will produce a file called `lif-units.jar` in the folder. This
file is runnable and stand-alone, or can be used as a library, so it can be
moved around to the location of your choice.

In addition, the script generates in the `doc` folder the Javadoc
documentation for using the library. This documentation is also embedded in
the JAR file. To show documentation in Eclipse, right-click on the jar,
click "Properties", then fill the Javadoc location (which is the JAR
itself).

### Testing

The library can test itself by running:

    ant test

Unit tests are run with [jUnit](http://junit.org); a detailed report of
these tests in HTML format is availble in the folder `tests/junit`, which
is automatically created. Code coverage is also computed with
[JaCoCo](http://www.eclemma.org/jacoco/); a detailed report is available
in the folder `tests/coverage`.

About the author
----------------

Azrael was written by [Sylvain Hallé](https://leduotang.ca/sylvain), Full 
Professor at [Université du Québec à Chicoutimi](https://www.uqac.ca), Canada.