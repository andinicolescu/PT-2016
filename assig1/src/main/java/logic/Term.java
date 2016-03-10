package logic;

public class Term {
	private double coeff;
	private int degree;
	
	public Term(double coeff,int degree){
		this.setCoeff(coeff);
		this.setDegree(degree);
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public double getCoeff() {
		return coeff;
	}

	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
}
