package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import assig4.Account;
import assig4.Bank;
import assig4.Person;
import assig4.SavingsAccount;
import assig4.SpendingAccount;

public class GUI extends JFrame {

	private static final int ROWS = 20;
	private Object[][] data = new Object[ROWS][3];
	private JTable table;
	private static final long serialVersionUID = 1L;
	private final JTextField textBox1 = new JTextField(30);
	private final JTextField textBox2 = new JTextField(30);
	private final JTextField textBox3 = new JTextField(30);
	private final JTextField textBox4 = new JTextField(30);
	private final JTextField textBox5 = new JTextField(30);
	private Bank bank = new Bank();
	private JButton news;

	public GUI() {
		this.setLayout(new GridLayout(1, 2));
		this.setSize(1300, 700);
		JPanel output = createTablePanel();
		JPanel inputPerson = createInputPerson();
		this.add(inputPerson);

		this.add(output);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public JPanel createTablePanel() {
		JPanel output = new JPanel();

		String[] columnNames = { "Waiting ", "for", "Command" };

		table = new JTable(data, columnNames);
		output.add(new JScrollPane(table));
		return output;
	}

	public JPanel createInputPerson() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3, 1));

		JPanel personPanel = new JPanel();

		personPanel.setLayout(new FlowLayout());
		personPanel.add(new JLabel("                  Person ID:                    "));
		personPanel.add(textBox1);
		personPanel.add(new JLabel("                       Name:                      "));
		personPanel.add(textBox2);
		personPanel.add(new JLabel("                       Account ID:             "));
		personPanel.add(textBox3);
		personPanel.add(new JLabel("                              Sum:                 "));
		personPanel.add(textBox4);
		personPanel.add(new JLabel("                   Type of Account :     "));
		personPanel.add(textBox5);
		personPanel.add(new JLabel(
				"                                                (1 for Savings Account and 2 for Spending Account)"));
		JPanel buton = createSubmitPanel();
		personPanel.add(buton);

		news = new JButton();
		news.setBackground(Color.LIGHT_GRAY);
		news.setForeground(Color.BLACK);
		news.setBorder(null);
		news.setText("Insert data and select desired operation");
		this.add(news, BorderLayout.SOUTH);
		inputPanel.add(personPanel);
		inputPanel.add(buton);
		inputPanel.add(news);
		return inputPanel;
	}

	public JPanel createSubmitPanel() {
		JPanel viewsPanel = new JPanel();

		JButton seeClients = new JButton();
		JButton addNewClient = new JButton();
		JButton removeClient = new JButton();
		JButton removeAccount = new JButton();

		JButton addNewAccount = new JButton();
		JButton withdrawMoney = new JButton();
		JButton depositMoney = new JButton();
		JButton seeAccounts = new JButton();

		removeAccount.setText("Remove Account");
		removeClient.setText("Remove Client");
		addNewClient.setText("Add new Client");
		seeClients.setText("See Clients");

		seeAccounts.setText("See Client Accounts");
		depositMoney.setText("Deposit Money");
		withdrawMoney.setText("Withdraw Money");
		addNewAccount.setText("Add new Account");

		depositMoney.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					Person pers = new Person(textBox2.getText(), Integer.parseInt(textBox1.getText()));
					bank.depositMoney(Integer.parseInt(textBox4.getText()), Integer.parseInt(textBox3.getText()), pers);
					displayAccounts(pers);
				} catch (NumberFormatException e1) {
					news.setText("Invalid Data entered");
				}
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox4.setText("");
				textBox5.setText("");
			}

		});

		removeAccount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Person pers=new Person(textBox2.getText(),Integer.parseInt(textBox1.getText()));
				bank.deleteAccount(Integer.parseInt(textBox3.getText()), pers);
				displayAccounts(pers);
			}

		});

		removeClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try{
				bank.deletePerson(new Person(textBox2.getText(),Integer.parseInt(textBox1.getText())));
				displayClientsAll();
				} catch(NumberFormatException e1){
					news.setText("Invalid Data Entered");
				}
			}

		});

		withdrawMoney.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Person pers = new Person(textBox2.getText(), Integer.parseInt(textBox1.getText()));
					bank.withdrawMoney(Integer.parseInt(textBox4.getText()), Integer.parseInt(textBox3.getText()), pers);
					displayAccounts(pers);
				} catch (NumberFormatException e1) {
					news.setText("Invalid Data entered");
				}
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox4.setText("");
				textBox5.setText("");

			}

		});

		addNewClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textBox1.setEnabled(false);
				textBox2.setEnabled(false);
				try {
					Person pers = new Person(textBox2.getText(), Integer.parseInt(textBox1.getText()));
					if (Integer.parseInt(textBox5.getText()) == 1)
						bank.addAccountPerson(pers, new SavingsAccount(Integer.parseInt(textBox3.getText()),
								Integer.parseInt(textBox4.getText())));
					if (Integer.parseInt(textBox5.getText()) == 2)
						bank.addAccountPerson(pers, new SpendingAccount(Integer.parseInt(textBox3.getText()),
								Integer.parseInt(textBox4.getText())));
					displayAccounts(pers);
				} catch (NumberFormatException e1) {
					news.setText("Invalid Data Entered");
				}
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox4.setText("");
				textBox5.setText("");
				textBox1.setEnabled(true);
				textBox2.setEnabled(true);
				textBox3.setEnabled(true);
				textBox4.setEnabled(true);
			}

		});

		addNewAccount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textBox1.setEnabled(false);
				textBox2.setEnabled(false);
				try {
					Person pers = new Person(textBox2.getText(), Integer.parseInt(textBox1.getText()));
					if (Integer.parseInt(textBox5.getText()) == 1)
						bank.addAccountPerson(pers, new SavingsAccount(Integer.parseInt(textBox3.getText()),
								Integer.parseInt(textBox4.getText())));
					if (Integer.parseInt(textBox5.getText()) == 2)
						bank.addAccountPerson(pers, new SpendingAccount(Integer.parseInt(textBox3.getText()),
								Integer.parseInt(textBox4.getText())));
					displayAccounts(pers);
				} catch (NumberFormatException e1) {
					news.setText("Invalid Data Entered");
				}
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox4.setText("");
				textBox5.setText("");
				textBox1.setEnabled(true);
				textBox2.setEnabled(true);
				textBox3.setEnabled(true);
				textBox4.setEnabled(true);
			}

		});

		seeClients.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				displayClientsAll();
			}

		});

		seeAccounts.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					displayAccounts(new Person(textBox2.getText(), Integer.parseInt(textBox1.getText())));
				} catch (NumberFormatException e1) {
					news.setText("Invalid Data Format");
				}
			}

		});
		viewsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridx = 0;
		gbc.gridy = 0;
		viewsPanel.add(addNewClient, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		viewsPanel.add(seeClients, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		viewsPanel.add(removeAccount, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		viewsPanel.add(removeClient, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		viewsPanel.add(addNewAccount, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		viewsPanel.add(seeAccounts, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		viewsPanel.add(depositMoney, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		viewsPanel.add(withdrawMoney, gbc);

		return viewsPanel;
	}

	public void displayAccounts(Person read) {
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Money");
		table.getColumnModel().getColumn(2).setHeaderValue("Type of Account");
		Set<Account> accounts = bank.findAllAccounts(read);
		table.getTableHeader().repaint();
		int i = 0;
		for (Account acc : accounts) {
			data[i][0] = acc.getAccountID();
			table.editCellAt(i, 0);
			data[i][1] = acc.getMoney();
			table.editCellAt(i, 1);
			if (acc.getType() == 1)
				data[i][2] = "Saving Account";
			else
				data[i][2] = "Spending Account";
			table.editCellAt(i, 2);
			i++;
		}

		for (int j = i; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
			data[j][2] = "";
			table.editCellAt(j, 2);
		}

	}

	public void displayClientsAll() {

		ArrayList<Person> persons = bank.findAllPerson();
		int i = 0;
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Name");
		table.getColumnModel().getColumn(2).setHeaderValue("");
		table.getTableHeader().repaint();
		for (Person p : persons) {
			data[i][0] = p.getPersonID();
			table.editCellAt(i, 0);
			data[i][1] = p.getName();
			table.editCellAt(i, 1);
			data[i][2] = "";
			table.editCellAt(i, 2);
			i++;
		}
		for (int j = i; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
		}
	}

}
