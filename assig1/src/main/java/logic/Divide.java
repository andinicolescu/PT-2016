package logic;

import gui.GUI;

public class Divide implements Operation {

	public Polynom operation(Polynom p1, Polynom p2) {
		Add addition = new Add();
		Substract substraction=new Substract();
		Multiply multiplication=new Multiply();
		Polynom q = new Polynom();
		Polynom r = new Polynom();
		r = p1;
		int d = p2.getDegree();
		double c = p2.getLC();
		while (r.getDegree() >= d) {
			Polynom s = new Polynom();
			s.addMono(r.getLC() / c, r.getDegree() - d);
			q = addition.operation(q, s);
			r=substraction.operation(r, multiplication.operation(s, p2));
		}
		GUI.displayResult(q,r);
		return r;
	}

}
