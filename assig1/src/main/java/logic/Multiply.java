package logic;

import java.util.ArrayList;

import gui.GUI;

public class Multiply implements Operation {

	public Polynom operation(Polynom p1, Polynom p2) {
		int length = p1.getMonomials().size();
		Polynom result[] = new Polynom[length];
		for (int i = 0; i < length; i++)
			result[i] = new Polynom();
		ArrayList<Term> monomials1 = new ArrayList<Term>();
		ArrayList<Term> monomials2 = new ArrayList<Term>();
		monomials1 = p1.getMonomials();
		monomials2 = p2.getMonomials();
		int i = 0;
		for (Term t1 : monomials1) {
			for (Term t2 : monomials2) {
				result[i].addMono(t1.getCoeff() * t2.getCoeff(), t1.getDegree() + t2.getDegree());
			}
			i++;
		}
		Add addition= new Add();
		while (length > 1) {
			Polynom aux = new Polynom();
			aux = result[0];
			result[0]=addition.operation(aux,result[length-1]);
			length--;
		}
		GUI.displayResult(result[0],null);
		return result[0];
	}

}
