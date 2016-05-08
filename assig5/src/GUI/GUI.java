package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assig5.Dictionary;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JTextField textBox1 = new JTextField(30);
	private final JTextField textBox2 = new JTextField(30);
	private JButton results1;
	private JButton results2;
	private Dictionary dic = new Dictionary();

	public GUI() {
		this.setLayout(new GridLayout(1, 2));
		this.setSize(700, 700);
		JPanel inputPerson = createInputPerson();
		this.add(inputPerson);

		JPanel submit = createSubmitPanel();
		this.add(submit);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	private JPanel createSubmitPanel() {
		JPanel submitPanel = new JPanel();
		submitPanel.setLayout(new GridLayout(2, 1));
		JPanel output = new JPanel();
		JButton buttons[] = new JButton[4];
		buttons[0] = new JButton("Adaugare");
		buttons[1] = new JButton("Stergere");
		buttons[2] = new JButton("Listare");
		buttons[3] = new JButton("Cautare");

		for (int i = 0; i < 4; i++) {
			output.add(buttons[i]);
		}
		buttons[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String synonyms[] = textBox2.getText().split(" ");
				int i = 0;
				while (i < synonyms.length) {
					dic.addSynonym(textBox1.getText(), synonyms[i]);
					i++;
				}
				textBox1.setText("");
				textBox2.setText("");
			}

		});
		
		buttons[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dic.deleteSynonym(textBox1.getText());
				displayAll(dic.getSynonyms());
				textBox1.setText("");
			}
			
		});

		buttons[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayAll(dic.viewAll());
			}

		});

		buttons[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayAll(dic.searchWord(textBox1.getText()));
				textBox1.setText("");
			}

		});
		submitPanel.add(output);
		results2 = new JButton();
		results2.setBackground(Color.LIGHT_GRAY);
		results2.setForeground(Color.BLACK);
		results2.setBorder(null);
		submitPanel.add(results2);
		return submitPanel;
	}

	public JPanel createInputPerson() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 1));

		JPanel personPanel = new JPanel();

		personPanel.setLayout(new FlowLayout());
		personPanel.add(new JLabel("Word:"));
		personPanel.add(textBox1);
		personPanel.add(new JLabel("Synonyms:"));
		personPanel.add(textBox2);
		results1 = new JButton();
		results1.setBackground(Color.LIGHT_GRAY);
		results1.setForeground(Color.BLACK);
		results1.setBorder(null);
		this.add(results1, BorderLayout.SOUTH);
		inputPanel.add(personPanel);
		inputPanel.add(results1);
		return inputPanel;
	}

	public void displayAll(Map<String, Set<String>> result) {
		results1.setText("<html>");
		results2.setText("<html>");
		for (Entry<String, Set<String>> entry : result.entrySet()) {
			results1.setText(results1.getText() + entry.getKey() + "<br/>");
			results2.setText(results2.getText() + entry.getValue().toString() + "<br/>");
		}
		results1.setText(results1.getText() + "<html>");
		results2.setText(results2.getText() + "<html>");

	}
}
