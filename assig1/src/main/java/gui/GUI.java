package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Add;
import logic.Derive;
import logic.Divide;
import logic.Integrate;
import logic.Multiply;
import logic.PartyStarter;
import logic.Polynom;
import logic.Substract;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private String polynom1 = null;
	private String polynom2 = null;
	final JTextField poly1 = new JTextField(30);
	final JTextField poly2 = new JTextField(30);
	private PartyStarter start = new PartyStarter();
	private static JButton result1 = new JButton();
	private static JButton result2 = new JButton();

	public GUI() {

		JPanel inputPanel = createInputPanel();
		this.add(inputPanel);
		JPanel buttonPanel = createSubmitPanel();
		this.add(buttonPanel);
		JPanel resultPanel = createResultPanel();
		this.add(resultPanel);
		JPanel optionPanel = createOptionPanel();
		this.add(optionPanel);

		this.setTitle("Polynomial processing");
		this.setLayout(new GridLayout(2, 2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500, 260);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	public JPanel createInputPanel() {
		JPanel polyPanel = new JPanel();
		polyPanel.setBackground(Color.LIGHT_GRAY);

		polyPanel.setLayout(new GridLayout(3, 2));
		polyPanel.add(new JLabel(" First polynom :"));
		poly1.setSize(80, 30);
		poly1.setBackground(Color.CYAN);
		poly1.setForeground(Color.BLACK);
		poly1.setDisabledTextColor(Color.BLACK);
		polyPanel.add(poly1);
		polyPanel.add(new JLabel(" Second polynom :"));
		poly2.setSize(30, 30);
		poly2.setBackground(Color.CYAN);
		poly2.setForeground(Color.BLACK);
		poly2.setDisabledTextColor(Color.BLACK);
		polyPanel.add(poly2);
		polyPanel.add(new JLabel(" Polynom input style:"));
		polyPanel.add(new JLabel("3x^4+1x^3-3x^1-2"));
		return polyPanel;
	}

	public JPanel createSubmitPanel() {
		JPanel submitB = new JPanel();
		submitB.setBackground(Color.LIGHT_GRAY);
		final JButton submit = new JButton();
		submit.setText("Submit");
		submit.setBackground(Color.CYAN);
		submit.setForeground(Color.BLACK);
		submitB.add(submit, BorderLayout.CENTER);
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				submit.setEnabled(false);
				poly1.setEnabled(false);
				poly2.setEnabled(false);
				polynom1 = poly1.getText();
				polynom2 = poly2.getText();
				if ((polynom1 != null) && (polynom2 != null))
					if ((polynom1.length() == 1) || (polynom2.length() == 1)) {
						JOptionPane.showMessageDialog(null, "The program is made to use polynomials NOT numbers",
								"Oops", JOptionPane.OK_OPTION);
						System.exit(0);
					}
				start.processData(polynom1, polynom2);
			}

		});
		return submitB;
	}

	public JPanel createResultPanel() {

		JPanel resultPanel = new JPanel();
		resultPanel.setBackground(Color.LIGHT_GRAY);
		resultPanel.add(new JLabel("Result :"));
		result1.setBorder(null);
		resultPanel.add(result1);
		result2.setBorder(null);
		resultPanel.add(result2);

		return resultPanel;
	}

	public JPanel createOptionPanel() {

		JPanel optionPanel = new JPanel();
		optionPanel.setBackground(Color.LIGHT_GRAY);
		JButton choose[] = new JButton[6];
		for (int i = 0; i < 6; i++) {
			choose[i] = new JButton();
			choose[i].setBackground(Color.CYAN);
			choose[i].setForeground(Color.BLACK);
			optionPanel.add(choose[i]);

		}
		choose[0].setText("Add");
		choose[0].setName("Add");
		choose[1].setText("Substract");
		choose[1].setName("Substract");
		choose[2].setText("Multiply");
		choose[2].setName("Multiply");
		choose[3].setText("Divide");
		choose[3].setName("Divide");
		choose[4].setText("Derive Polynom 1");
		choose[4].setName("Derive");
		choose[5].setText("Integrate Polynom 1");
		choose[5].setName("Integrate");
		for (int i = 0; i < 6; i++) {
			choose[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					JButton source = (JButton) e.getSource();
					if (source.getName().equals("Add")) {
						Add addition = new Add();
						addition.operation(start.getPolynom1(), start.getPolynom2());
					}
					if (source.getName().equals("Substract")) {
						Substract substraction = new Substract();
						substraction.operation(start.getPolynom1(), start.getPolynom2());
					}
					if (source.getName().equals("Multiply")) {
						Multiply mult = new Multiply();
						mult.operation(start.getPolynom1(), start.getPolynom2());
					}
					if (source.getName().equals("Divide")) {
						Divide divid = new Divide();
						divid.operation(start.getPolynom1(), start.getPolynom2());
					}
					if (source.getName().equals("Derive")) {
						Derive deriv = new Derive();
						deriv.operation(start.getPolynom1(), start.getPolynom2());
					}
					if (source.getName().equals("Integrate")) {
						Integrate integ = new Integrate();
						integ.operation(start.getPolynom1(), start.getPolynom2());
					}
				}

			});
		}

		return optionPanel;
	}

	public static void displayResult(Polynom po1, Polynom po2) {
		result2.setText(null);
		result1.setBackground(Color.LIGHT_GRAY);
		result2.setBackground(Color.LIGHT_GRAY);
		result1.setForeground(Color.RED);
		result2.setForeground(Color.RED);
		result1.setText(po1.toString());
		if (po2 != null)
			
			result2.setText("Reminder: "+po2.toString());
	}
}
