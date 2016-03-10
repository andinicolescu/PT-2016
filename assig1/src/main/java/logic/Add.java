package logic;


import gui.GUI;

public class Add implements Operation {

	public Polynom operation(Polynom p1, Polynom p2) {
		Polynom result = new Polynom();
		for(Term t1:p1.getMonomials()){
			result.getMonomials().add(new Term(t1.getCoeff(),t1.getDegree()));
		}
			
		for(Term t2:p2.getMonomials()){
				result.addMono(t2.getCoeff(), t2.getDegree(), "+");
		}

		result.sort();
		GUI.displayResult(result,null);
		return result;
	}

}
