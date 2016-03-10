package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartyStarter {

	private Polynom polynom1 = new Polynom();
	private Polynom polynom2 = new Polynom();

	public void processData(String poly1, String poly2) {

		Pattern p1 = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
		Matcher m1 = p1.matcher(poly1);
		while (m1.find()) {
			polynom1.addMono(Double.parseDouble(m1.group(1)), Integer.parseInt(m1.group(2)));
		}
		if (!poly1.substring(poly1.length() - 2).contains("^")) {
			String substring1 = poly1.length() > 2 ? poly1.substring(poly1.length() - 2) : poly1;
			polynom1.addMono(Double.parseDouble(substring1), 0);
		}
		Pattern p2 = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
		Matcher m2 = p2.matcher(poly2);
		while (m2.find()) {
			polynom2.addMono(Double.parseDouble(m2.group(1)), Integer.parseInt(m2.group(2)));
		}
		if (!poly2.substring(poly2.length() - 2).contains("^")) {
			String substring2 = poly2.length() > 2 ? poly2.substring(poly2.length() - 2) : poly2;
			polynom2.addMono(Double.parseDouble(substring2), 0);
		}
		
		polynom1.sort();
		polynom2.sort();

	}

	public Polynom getPolynom1() {
		return polynom1;
	}

	public Polynom getPolynom2() {
		return polynom2;
	}

}
