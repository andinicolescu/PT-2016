package logic;

import java.util.ArrayList;

import gui.GUI;

public class Derive implements Operation {

	public Polynom operation(Polynom p1, Polynom p2) {
		Polynom result=new Polynom();
		ArrayList<Term> monomials1=p1.getMonomials();
		for(Term t:monomials1){
			result.addMono(t.getCoeff()*(t.getDegree()),t.getDegree()-1);
		}
		GUI.displayResult(result,null);
		return result;
	}

}
