/*
  Copyright 2021-2023 Sylvain Hallé
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

import ca.uqac.lif.dag.LabelledNode;
import ca.uqac.lif.numbers.Real;
import ca.uqac.lif.numbers.RealPart.Uncertainty;
import ca.uqac.lif.numbers.Whole;
import ca.uqac.lif.petitpoucet.AndNode;
import ca.uqac.lif.petitpoucet.Part;
import ca.uqac.lif.petitpoucet.PartNode;
import ca.uqac.lif.petitpoucet.function.InvalidArgumentTypeException;
import ca.uqac.lif.petitpoucet.function.NthOutput;
import ca.uqac.lif.petitpoucet.function.RelationNodeFactory;
import ca.uqac.lif.petitpoucet.function.number.Multiplication;
import ca.uqac.lif.units.Dimension;
import ca.uqac.lif.units.DimensionValue;
import ca.uqac.lif.units.NamelessDimensionValue;

/**
 * Function that multiples a set of {@link DimensionValue}s.
 * @author Sylvain Hallé
 *
 */
public class UnitMultiply extends Multiplication
{
	public UnitMultiply(int in_arity)
	{
		super(in_arity);
	}
	
	public static DimensionValue get(Object ... inputs)
	{
		return (DimensionValue) new UnitMultiply(inputs.length).evaluate(inputs)[0];
	}

	@Override
	public DimensionValue[] getValue(Object ... inputs)
	{
		DimensionValue[] out = new DimensionValue[1];
		Dimension target_dim = new Dimension();
		Real si_prod = Whole.ONE;
		for (int i = 0; i < inputs.length; i++)
		{
			Object o = inputs[i];
			if (!(o instanceof DimensionValue))
			{
				throw new InvalidArgumentTypeException("Operand must be a DimensionValue");
			}
			DimensionValue v = (DimensionValue) o;
			target_dim.add(v.getDimension());
			Real val = v.asBaseUnit().get();
			if (val.doubleValue() == 0)
			{
				m_nulls[i] = true;
			}
			else
			{
				m_nulls[i] = false;
			}
			si_prod = si_prod.multiply(val);
		}
		out[0] = new NamelessDimensionValue(si_prod, target_dim);
		return out;
	}

	@Override
	public UnitMultiply duplicate(boolean with_state)
	{
		UnitMultiply um = new UnitMultiply(getInputArity());
		copyInto(um, with_state);
		return um;
	}
	
	@Override
	public PartNode getExplanation(Part d, RelationNodeFactory f)
	{
		PartNode root = f.getPartNode(d, this);
		int input = NthOutput.mentionedOutput(d);
		if (input < 0 || input > getInputArity())
		{
			root.addChild(f.getUnknownNode());
			return root;
		}
		Part tail = d.tail();
		if (tail == null)
		{
			return super.getExplanation(d, f);
		}
		Part t_head = tail.head();
		if (!(t_head instanceof Uncertainty))
		{
			return super.getExplanation(d, f);
		}
		// Uncertainty always depends on all inputs
		LabelledNode to_add = root;
		if (getInputArity() > 1)
		{
			AndNode and = f.getAndNode();
			to_add.addChild(and);
			to_add = and;
		}
		for (int i = 0; i < getInputArity(); i++)
		{
			Part new_p = NthOutput.replaceOutByIn(d, i);
			to_add.addChild(f.getPartNode(new_p, this));
		}
		return root;
	}
}
