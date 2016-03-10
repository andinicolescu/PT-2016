package logic;

import java.text.DecimalFormat;
import java.util.ArrayList;

import gui.GUI;

public class Integrate implements Operation {

	public Polynom operation(Polynom p1, Polynom p2) {
		Polynom result = new Polynom();
		ArrayList<Term> monomials1 = new ArrayList<Term>();
		monomials1 = p1.getMonomials();
		DecimalFormat df = new DecimalFormat("#.##");
		for (Term t : monomials1) {
			result.getMonomials().add(new Term(Double.parseDouble(df.format(t.getCoeff() / (t.getDegree() + 1))), t.getDegree() + 1));
		}
		result.seePolynomial();
		GUI.displayResult(result, null);
		return result;
	}

}
