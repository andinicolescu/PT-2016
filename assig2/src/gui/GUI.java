package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.OrderDisplay;
import model.Product;

public class GUI extends JFrame {

	private static final int ROWS = 20;
	private Object[][] data = new Object[ROWS][5];
	private JTable table;
	private int selectedID;
	private static final long serialVersionUID = 1L;
	private final JTextField textBox1 = new JTextField(30);
	private final JTextField textBox2 = new JTextField(30);
	private final JTextField textBox3 = new JTextField(30);
	private ClientBLL clientAccess = new ClientBLL();
	private ProductBLL productAccess = new ProductBLL();
	private OrderBLL orderAccess = new OrderBLL();
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

		String[] columnNames = { "Waiting ", "for", "Command", "", "" };

		table = new JTable(data, columnNames);
		output.add(new JScrollPane(table));
		return output;
	}

	public JPanel createInputPerson() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3, 1));

		JPanel personPanel = new JPanel();

		personPanel.setLayout(new FlowLayout());
		personPanel.add(new JLabel("                  ID/Product:                "));
		personPanel.add(textBox1);
		personPanel.add(new JLabel("                 Address/Quantity:    "));
		personPanel.add(textBox2);
		personPanel.add(new JLabel("                 Price:                          "));
		personPanel.add(textBox3);
		JPanel buton = createSubmitPanel();
		personPanel.add(buton);

		news = new JButton();
		news.setBackground(Color.LIGHT_GRAY);
		news.setForeground(Color.BLACK);
		news.setBorder(null);
		news.setText("Select customer or product for order or modification of data");
		this.add(news, BorderLayout.SOUTH);
		inputPanel.add(personPanel);
		inputPanel.add(buton);
		inputPanel.add(news);
		return inputPanel;
	}

	public JPanel createSubmitPanel() {
		JPanel viewsPanel = new JPanel();

		JButton clients = new JButton();
		JButton addNewClient = new JButton();
		JButton removeClient = new JButton();
		JButton addNewOrder = new JButton();
		JButton editClient = new JButton();

		JButton addNewProduct = new JButton();
		JButton removeProduct = new JButton();
		JButton orders = new JButton();
		JButton editProduct = new JButton();
		JButton products = new JButton();

		JButton removeOrder = new JButton();
		JButton editOrder = new JButton();

		editClient.setText("Edit Client");
		removeClient.setText("Remove Client");
		addNewOrder.setText("Add new Order");
		addNewClient.setText("Add new Account");
		clients.setText("See Clients");

		products.setText("See Products");
		editProduct.setText("Edit Product");
		removeProduct.setText("Remove Product");
		orders.setText("See Orders");
		addNewProduct.setText("Add new Product");

		removeOrder.setText("Remove Order");
		editOrder.setText("Edit Order");

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					try {
						selectedID = Integer.valueOf((String) (table.getValueAt(table.getSelectedRow(), 0)));
						if (table.getColumnModel().getColumn(2).getHeaderValue().equals("Quantity")) {
							news.setText("You have chosen " + table.getValueAt(table.getSelectedRow(), 1));
							productAccess.setSelectedProd(selectedID);
							displayProductsAll();

						} else if (table.getColumnModel().getColumn(2).getHeaderValue().equals("Address")) {
							news.setText(table.getValueAt(table.getSelectedRow(), 1) + " is now selected");
							clientAccess.setLogedID(selectedID);
							displayClientsAll();
						} else if (table.getColumnModel().getColumn(2).getHeaderValue().equals("Product")) {
							news.setText(table.getValueAt(table.getSelectedRow(), 1) + " "
									+ table.getValueAt(table.getSelectedRow(), 2) + " is now selected");
							orderAccess.setSelectedOrder(selectedID);
							displayOrdersAll();
						}

					} catch (NumberFormatException e) {
						news.setText("Selected Row is Empty");
					}

				}
			}
		});

		editOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (!orderAccess
							.update(new Order(productAccess.getSelectedProd(), Integer.parseInt(textBox2.getText()))))
						news.setText("No data entered");
					displayOrdersAll();
				} catch (NumberFormatException e1) {
					if (!orderAccess.update(new Order(productAccess.getSelectedProd(), 0)))
						news.setText("No data entered");
				}
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				displayOrdersAll();

			}

		});

		removeOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				orderAccess.delete();
				displayOrdersAll();
			}

		});

		editClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientAccess.update(new Client(textBox1.getText(), textBox2.getText()));
				displayClientsAll();
			}

		});

		editProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (!productAccess.update(new Product(textBox1.getText(), Integer.parseInt(textBox2.getText()),
							Integer.parseInt(textBox3.getText()))))
						news.setText("No data entered");
					displayProductsAll();
				} catch (NumberFormatException e1) {
					try {
						if (!productAccess
								.update(new Product(textBox1.getText(), Integer.parseInt(textBox2.getText()), 0)))
							news.setText("No data entered");
						displayProductsAll();
					} catch (NumberFormatException e2) {
						try {
							if (!productAccess
									.update(new Product(textBox1.getText(), 0, Integer.parseInt(textBox3.getText()))))
								news.setText("No data entered");
							displayProductsAll();
						} catch (NumberFormatException e3) {
							if (!productAccess.update(new Product(textBox1.getText(), 0, 0)))
								news.setText("No data entered");
							displayProductsAll();
						}
					}

				}
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				displayProductsAll();
			}

		});

		addNewOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textBox1.setEnabled(false);
				textBox2.setEnabled(false);
				try {
					Order order = new Order(productAccess.getSelectedProd(), clientAccess.getLogedID(),
							Integer.parseInt(textBox2.getText()));
					if (!orderAccess.insert(order))
						news.setText(
								"Product/Client not selected " + " or quantity ordered is more than stock quantity "
										+ productAccess.read(order.getProductID()).getQuantity());
				} catch (NumberFormatException e3) {
					news.setText("Quantity and Price are numbers");
				}
				displayProductsAll();
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox1.setEnabled(true);
				textBox2.setEnabled(true);
				textBox3.setEnabled(true);
				displayOrdersAll();
			}

		});

		orders.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				displayOrdersAll();
			}

		});

		removeClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!clientAccess.delete())
					news.setText("You selected an invalid user");
				else
					news.setText("User Removed");

				displayClientsAll();

			}

		});

		removeProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!productAccess.delete())
					news.setText("You entered an invalid ID");
				else
					news.setText("Product removed");
				displayProductsAll();
			}

		});

		addNewClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textBox1.setEnabled(false);
				textBox2.setEnabled(false);

				Client clientul = new Client(textBox1.getText(), textBox2.getText());
				if (!clientAccess.insert(clientul)) {
					news.setText("Invalid Data");
				}
				displayClientsAll();
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox1.setEnabled(true);
				textBox2.setEnabled(true);
				textBox3.setEnabled(true);

			}

		});

		addNewProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textBox1.setEnabled(false);
				textBox2.setEnabled(false);
				try {
					Product prod = new Product(textBox1.getText(), Integer.parseInt(textBox2.getText()),
							Integer.parseInt(textBox3.getText()));
					if (!productAccess.insert(prod)) {
						news.setText("Invalid Data");
					}
				} catch (NumberFormatException e1) {
					news.setText("Not entered valid Price/Quantity");
				}
				displayProductsAll();
				textBox1.setText("");
				textBox2.setText("");
				textBox3.setText("");
				textBox1.setEnabled(true);
				textBox2.setEnabled(true);
				textBox3.setEnabled(true);

			}

		});

		clients.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (textBox1.getText() != null) {
					if (!textBox1.getText().isEmpty()) {
						try {
							displayClient(clientAccess.read(Integer.parseInt(textBox1.getText())));

						} catch (NumberFormatException e1) {
							displayClientsAll();
						}
					} else
						displayClientsAll();
				} else
					displayClientsAll();
				textBox1.setText("");
			}

		});

		products.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (textBox1.getText() != null) {
					if (!textBox1.getText().isEmpty()) {
						try {
							displayProduct(productAccess.read(Integer.parseInt(textBox1.getText())));

						} catch (NumberFormatException e1) {
							displayProductsAll();
						}
					} else
						displayProductsAll();
				} else
					displayProductsAll();
				textBox1.setText("");
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
		viewsPanel.add(clients, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		viewsPanel.add(editClient, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		viewsPanel.add(removeClient, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		viewsPanel.add(addNewProduct, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		viewsPanel.add(products, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		viewsPanel.add(editProduct, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		viewsPanel.add(removeProduct, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		viewsPanel.add(addNewOrder, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		viewsPanel.add(orders, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		viewsPanel.add(editOrder, gbc);
		gbc.gridx = 3;
		gbc.gridy = 2;
		viewsPanel.add(removeOrder, gbc);

		return viewsPanel;
	}

	public void displayClient(Client read) {
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Name");
		table.getColumnModel().getColumn(2).setHeaderValue("Address");
		table.getColumnModel().getColumn(3).setHeaderValue("");
		table.getColumnModel().getColumn(4).setHeaderValue("");
		table.getTableHeader().repaint();
		data[0][4] = "";
		table.editCellAt(0, 4);
		data[0][3] = "";
		table.editCellAt(0, 3);
		data[0][1] = read.getName();
		table.editCellAt(0, 1);
		data[0][2] = read.getAddress();
		table.editCellAt(0, 2);
		data[0][0] = read.getId();
		table.editCellAt(0, 0);
		for (int j = 1; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
			data[j][2] = "";
			table.editCellAt(j, 2);
			data[j][3] = "";
			table.editCellAt(j, 3);
			data[j][3] = "";
			table.editCellAt(j, 3);
			data[j][4] = "";
			table.editCellAt(j, 4);
		}

	}

	public void displayProduct(Product prod) {
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Name");
		table.getColumnModel().getColumn(2).setHeaderValue("Quantity");
		table.getColumnModel().getColumn(3).setHeaderValue("Price/Unit");
		table.getColumnModel().getColumn(4).setHeaderValue("");
		table.getTableHeader().repaint();
		data[0][4] = "";
		table.editCellAt(0, 4);
		data[0][0] = prod.getId();
		table.editCellAt(0, 0);
		data[0][1] = prod.getName();
		table.editCellAt(0, 1);
		data[0][2] = prod.getQuantity();
		table.editCellAt(0, 2);
		data[0][3] = prod.getPrice();
		table.editCellAt(0, 3);
		for (int j = 1; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
			data[j][2] = "";
			table.editCellAt(j, 2);
			data[j][3] = "";
			table.editCellAt(j, 3);
			data[j][4] = "";
			table.editCellAt(j, 4);
		}

	}

	public void displayClientsAll() {

		Client[] clientii = clientAccess.readAll();
		int i = 0;
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Name");
		table.getColumnModel().getColumn(2).setHeaderValue("Address");
		table.getColumnModel().getColumn(3).setHeaderValue("");
		table.getColumnModel().getColumn(4).setHeaderValue("");
		table.getTableHeader().repaint();
		while (clientii[i] != null) {
			data[i][4] = "";
			table.editCellAt(i, 4);
			data[i][3] = "";
			table.editCellAt(i, 3);
			data[i][0] = clientii[i].getId();
			table.editCellAt(i, 0);
			data[i][1] = clientii[i].getName();
			table.editCellAt(i, 1);
			data[i][2] = clientii[i].getAddress();
			i++;
		}
		for (int j = i; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
			data[j][2] = "";
			table.editCellAt(j, 2);
			data[j][3] = "";
			table.editCellAt(j, 3);
			data[j][4] = "";
			table.editCellAt(j, 4);
		}
	}

	public void displayOrdersAll() {

		OrderDisplay[] orders = orderAccess.read();
		int i = 0;
		table.getColumnModel().getColumn(0).setHeaderValue("Order ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Customer");
		table.getColumnModel().getColumn(2).setHeaderValue("Product");
		table.getColumnModel().getColumn(3).setHeaderValue("Quantity");
		table.getColumnModel().getColumn(4).setHeaderValue("Total Cost");
		table.getTableHeader().repaint();
		while (orders[i] != null) {
			data[i][1] = orders[i].getCustomer();
			table.editCellAt(i, 1);
			data[i][2] = orders[i].getProduct();
			table.editCellAt(i, 2);
			data[i][3] = orders[i].getQuantity();
			table.editCellAt(i, 3);
			data[i][4] = orders[i].getPrice();
			table.editCellAt(i, 4);
			data[i][0] = orders[i].getOrderID();
			table.editCellAt(i, 0);
			i++;
		}

		for (int j = i; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
			data[j][2] = "";
			table.editCellAt(j, 2);
			data[j][3] = "";
			table.editCellAt(j, 3);
			data[j][4] = "";
			table.editCellAt(j, 4);
		}
	}

	public void displayProductsAll() {

		Product[] prod = productAccess.readAll();
		int i = 0;
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Name");
		table.getColumnModel().getColumn(2).setHeaderValue("Quantity");
		table.getColumnModel().getColumn(3).setHeaderValue("Price/Unit");
		table.getColumnModel().getColumn(4).setHeaderValue("");
		table.getTableHeader().repaint();
		while (prod[i] != null) {
				data[i][0] = prod[i].getId();
				table.editCellAt(i, 0);
				data[i][1] = prod[i].getName();
				table.editCellAt(i, 1);
				data[i][2] = prod[i].getQuantity();
				table.editCellAt(i, 2);
				data[i][3] = prod[i].getPrice();
				table.editCellAt(i, 3);
				data[i][4] = "";
				table.editCellAt(i, 4);
			i++;

		}
		for (int j = i; j < ROWS; j++) {
			data[j][0] = "";
			table.editCellAt(j, 0);
			data[j][1] = "";
			table.editCellAt(j, 1);
			data[j][2] = "";
			table.editCellAt(j, 2);
			data[j][3] = "";
			table.editCellAt(j, 3);
			data[j][4] = "";
			table.editCellAt(j, 4);
		}

	}
}
