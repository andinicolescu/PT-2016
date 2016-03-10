package logic;

import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
	private ArrayList<Term> monomials = new ArrayList<Term>();

	public void sort() {
		Collections.sort(monomials, new MonomCoparator());
	}

	public void addMono(double coeff, int degree) {
		monomials.add(new Term(coeff, degree));
	}

	public void addMono(double coeff, int degree, String sign) {
		boolean added = false;
		for (Term t : monomials) {
			if (t.getDegree() == degree) {
				if (sign.equals("+")) {
					t.setCoeff(t.getCoeff() + coeff);
				} else if (sign.equals("-")) {
					t.setCoeff(t.getCoeff() - coeff);
				}
				added = true;
			}
		}
		if (added == false) {
			if (sign.equals("+")) {
				monomials.add(new Term(coeff, degree));
			} else if (sign.equals("-")) {
				monomials.add(new Term(-coeff, degree));
			}
		}

	}

	public void seePolynomial() {
		for (Term t : monomials) {
			System.out.println(t.getDegree() + " " + t.getCoeff());
		}
	}

	public int getDegree() {
		int max = -1;
		for (Term t : monomials) {
			if ((t.getDegree() > max) && (t.getCoeff() != 0))
				max = t.getDegree();
		}
		return max;
	}

	public double getLC() {
		double lc = 0;
		for (Term t : monomials) {
			if (t.getDegree() == this.getDegree())
				lc = t.getCoeff();
		}
		return lc;
	}

	public Polynom oneTermPoly(Term t) {
		Polynom result = new Polynom();
		result.monomials.add(t);
		return result;
	}

	public ArrayList<Term> getMonomials() {
		return monomials;
	}

	public void setMonomials(ArrayList<Term> monomials) {
		this.monomials = monomials;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < monomials.size(); i++) {
			if ((monomials.get(i).getCoeff() != 0)) {
				sb.append(monomials.get(i).getCoeff());
				sb.append("x");
				sb.append("^");
				sb.append(monomials.get(i).getDegree());
				if (monomials.size() > i + 1)
					if (monomials.get(i + 1).getCoeff() > 0)
						sb.append("+");
			}
		}
		if (sb.toString().length() > 2)
			if (sb.toString().substring(sb.toString().length() - 2).contains("0")) {
				sb.delete(sb.length() - 3, sb.length());
			} else if (sb.toString().lastIndexOf('+') == sb.toString().length())
				sb.delete(sb.length() - 1, sb.length());
		String resultS = sb.toString();
		return resultS;
	}

}
